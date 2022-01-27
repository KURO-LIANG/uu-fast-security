
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		productCategory: {},
        dataTree:[],
        dataList:[]
	},
    mounted(){
    },
	methods: {
		getData(){
            $.ajax({
                type: "POST",
                async: false,
                url: baseURL + "product/productcategory/list/",
                contentType: "application/json",
                success: function(r){
                    if(r.code === 0 && r.list){
                        vm.dataList = r.list;
                        vm.dataTree = vm.loopData()
                    }else{
                        layer.alert(r.msg);
                    }
                }
            });

            return vm.dataTree
        },
        add: function(){
            var html = '<form class="form-horizontal">' +
                '            <div class="form-group">' +
                '                <div class="col-sm-2 control-label">类别名称</div>' +
                '                <div class="col-sm-10">' +
                '                    <input type="text" class="form-control categoryName" value="" placeholder="类别名称"/>' +
                '                </div>' +
                '            </div>' +
                '        </form>'
            //示范一个公告层
            layer.open({
                type: 1
                ,title: "新增类别" //不显示标题栏
                ,closeBtn: false
                ,area: ['700px','400px']
                ,shade: 0.8
                ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                ,resize: false
                ,btn: ['确定', '取消']
                ,btnAlign: 'c'
                ,moveType: 1 //拖拽模式，0或者1
                ,content: html
                ,btn1: function(layero){
                    let categoryName = $(".categoryName").val()
                    if(!categoryName){
                        layer.msg("请输入类别名称！",{icon:7,time:2000});
                        return
                    }
                    vm.productCategory.categoryName = categoryName;
                    $('.layui-layer-btn0').attr('data-loading-text',"提交中...");
                    $(".layui-layer-btn1").hide();
                    $('.layui-layer-btn0').button('loading').delay(500).queue(function() {
                        $.ajax({
                            type: "POST",
                            url: baseURL + "product/productcategory/save",
                            contentType: "application/json",
                            data: JSON.stringify(vm.productCategory),
                            success: function(r){
                                if(r.code === 0){
                                    layer.msg("操作成功！", {icon: 1,time:1000},function () {
                                        location.replace(location.href)
                                    });
                                }else{
                                    $('.layui-layer-btn0').button('reset');
                                    $('.layui-layer-btn0').dequeue();
                                    $(".layui-layer-btn1").show();
                                    layer.alert(r.msg);
                                }
                            }
                        });
                    })
                },
                success:function(){

                }
            });
        },
        update: function () {
            $.ajax({
                type: "POST",
                url: baseURL + "product/productcategory/update",
                contentType: "application/json",
                data: JSON.stringify(vm.productCategory),
                success: function(r){
                    if(r.code === 0){
                        layer.msg("操作成功", {icon: 1,time:1000},function () {
                            location.replace(location.href)
                        });
                    }else{
                        $('.layui-layer-btn0').button('reset');
                        $('.layui-layer-btn0').dequeue();
                        $(".layui-layer-btn1").show();
                        layer.alert(r.msg);
                    }
                }
            });
        },
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.productCategory.id == null ? "product/productcategory/save" : "product/productcategory/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.productCategory),
                    success: function(r){
                        if(r.code === 0){
                            console.log(11111111)
                             layer.msg("操作成功", {icon: 1,time:1000},function () {
                                 location.replace(location.href)
                             });
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
			});
		},
		del: function (id) {
		    let ids = []
            ids.push(id)

            $.ajax({
                type: "POST",
                url: baseURL + "product/productcategory/delete",
                contentType: "application/json",
                data: JSON.stringify(ids),
                success: function(r){
                    if(r.code == 0){
                        layer.msg("操作成功", {icon: 1,time:1000},function () {
                            location.replace(location.href)
                        });
                    }else{
                        layer.alert(r.msg);
                    }
                }
            });
		},
        loopData: function(obj) {
            let parentId = !obj ? 0 : obj.id
            let list = vm.findChildren(parentId)
            if (list.length) {
                for (let item of list) {
                    item.children = vm.loopData(item)
                }
            }
            return list
        },
        getInfo: function(id){
            $.get(baseURL + "product/productcategory/info/"+id, function(r){
                vm.productCategory = r.productCategory;
            });
        },
        findChildren: function(parentId = 0) {
            if (!vm.dataList || !vm.dataList.length) return []
            let arr = []
            for (let item of this.dataList) {
                if (item.parentId === parentId) {
                    arr.push({
                        id: item.id,
                        parentId: item.parentId,
                        title: item.categoryName
                    })
                }
            }
            return arr
        },
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                page:page
            }).trigger("reloadGrid");
		}
	}
});

layui.use('tree', function(){
    var tree = layui.tree;
    var inst1 = tree.render({
        elem: '#test'  //绑定元素
        // ,showCheckbox: true // 显示复选框
        ,edit:['add', 'update', 'del'] //是否开启节点的操作图标。
        ,accordion: true //是否开启手风琴模式，默认 false
        , isopen: true  //加载完毕后的展开状态，默认值：true
        ,data: vm.getData()
        ,operate: function(obj){
            var type = obj.type; //得到操作类型：add、edit、del
            var data = obj.data; //得到当前节点的数据
            var elem = obj.elem; //得到当前节点元素

            //Ajax 操作
            var id = data.id; //得到节点索引
            console.log(id,vm.dataList.length)
            const productCategory = vm.dataList[id-1]

            console.log(productCategory)

            if(type === 'add'){ //增加节点
                //返回 key 值
                vm.productCategory.parentId = productCategory.id
                vm.add()
            } else if(type === 'update'){ //修改节点
                let categoryName = elem.find('.layui-tree-txt').html();
                console.log(categoryName); //得到修改后的内容
                vm.productCategory = productCategory;
                vm.productCategory.categoryName = categoryName
                vm.update()
            } else if(type === 'del'){ //删除节点
                vm.del(productCategory.id)
            }
        }
    });
});
