$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'active/activespecialprice/list',
        datatype: "json",
        colModel: [
            { label: '商品主图', name: 'productPic', index: 'product_pic', width: 80 ,formatter:function (cellValue) {
                    return '<img class="img-thumbnail" width="80px" src="/images/'+cellValue+'">';
                }},
			{ label: '商品名称', name: 'productName', index: 'product_name', width: 80 },
            { label: '商品售价', name: 'productPrice', index: 'product_price', width: 80  ,formatter:function (cellValue) {
                    return '￥' + cellValue;
                }},
			{ label: '活动价格', name: 'activePrice', index: 'active_price', width: 80  ,formatter:function (cellValue) {
                    return '￥' + cellValue;
                }},
			{ label: '活动开始时间', name: 'startTime', index: 'start_time', width: 80 },
			{ label: '活动结束时间', name: 'endTime', index: 'end_time', width: 80 },
			{ label: '活动状态', name: 'status', index: 'status', width: 80  ,formatter:function (cellValue) {
                    if(cellValue === 0){
                        return "尚未开始"
                    }else if(cellValue === 1){
                        return "活动进行中"
                    }else{
                        return "活动已结束"
                    }
                }},
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });

    layui.use('laydate', function(){
        var laydate = layui.laydate;

        var nowTime = new Date();
        //时间有效范围设定在: 上午九点半到下午五点半
        var start = laydate.render({
            elem: '#datetimeStart'
            ,type: 'datetime'
            ,format: 'yyyy-MM-dd HH:mm:ss'
            ,min:'nowTime'
            ,done: function(value, date, endDate){ //选择日期完毕的回调
                console.log(value); //得到日期生成的值，如：2017-08-18
                nowTime = end.config.min={
                    year:date.year,
                    month:date.month-1,//关键(多了一个月，需要减1)
                    date:date.date,
                    hours:date.hours,
                    minutes:date.minutes
                };
            }
        });

        //时间有效范围设定在: 上午九点半到下午五点半
        var end = laydate.render({
            elem: '#datetimeEnd'
            ,type: 'datetime'
            ,format: 'yyyy-MM-dd HH:mm:ss'
            ,min:'nowTime'
            ,done: function(value, date, endDate){ //选择日期完毕的回调
                console.log(value); //得到日期生成的值，如：2017-08-18
            }
        });
    });

    layui.use(['form', 'upload','element'], function() {
        var form = layui.form
            , layer = layui.layer
            , $ = layui.jquery
            ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块;

        //监听品类选择
        form.on('select(productId)', function(data){
            vm.activeSpecialPrice.productId = data.value;
        });

        form.on('select(status)', function(data){
            vm.activeSpecialPrice.status = data.value;
        });

        //自定义验证规则
        form.verify({
            startTime: function (value) {
                console.log(value)
                if (!value) {
                    return '请选择活动开始时间';
                }
            },
            endTime: function (value) {
                console.log(value)
                if (!value) {
                    return '请选择活动结束时间';
                }
            }
        })
        //监听提交
        form.on('submit(btnSubmit)', function(data) {
            vm.saveOrUpdate();
            return false;
        })
    })
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		activeSpecialPrice: {},
        productList:[]
	},
    mounted(){
	    this.getProductList()
    },
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			let productId = vm.productList.length > 0 ? vm.productList[0].id : 0
			vm.activeSpecialPrice = {productId:productId};
		},
        getProductList:function(){
            $.get(baseURL + "product/product/all", function(r){
                vm.productList = r.list;
            });
        },
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.activeSpecialPrice.id == null ? "active/activespecialprice/save" : "active/activespecialprice/update";
                vm.activeSpecialPrice.startTime = $("#datetimeStart").val();
                vm.activeSpecialPrice.endTime = $("#datetimeEnd").val();


                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.activeSpecialPrice),
                    success: function(r){
                        if(r.code === 0){
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
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "active/activespecialprice/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(id){
			$.get(baseURL + "active/activespecialprice/info/"+id, function(r){
                vm.activeSpecialPrice = r.activeSpecialPrice;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                page:page
            }).trigger("reloadGrid");
		}
	},
    updated: function () {
        layui.use(['form'], function () {
            layui.form.render();
        });
    },
});
