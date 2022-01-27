var ue;

$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'product/product/list',
        datatype: "json",
        colModel: [
            {label: '商品名称', name: 'productName', index: 'product_name', width: 80},
            {label: '商品SKU', name: 'productSku', index: 'product_sku', width: 80},
            {
                label: '商品主图', name: 'productPic', index: 'product_pic', width: 80, formatter: function (cellValue) {
                    return '<img class="img-thumbnail" width="80px" src="/images/' + cellValue + '">';
                }
            },
            {
                label: '商品售价',
                name: 'productPrice',
                index: 'product_price',
                width: 80,
                formatter: function (cellValue) {
                    return '￥' + cellValue;
                }
            },
            {label: '商品真实销量', name: 'productSales', index: 'product_sales', width: 80},
            {label: '商品虚拟销量', name: 'productVirtualSales', index: 'product_virtual_sales', width: 80},
            {
                label: '商品状态',
                name: 'productState',
                index: 'product_state',
                width: 80,
                formatter: function (cellValue) {
                    if (cellValue === 1) {
                        return "<span class='label label-success radius'>已上架</span>";
                    } else {
                        return "<span class='label label-danger radius'>已下架</span>";
                    }
                }
            },
            {
                label: '添加时间',
                name: 'createTime',
                index: 'create_time',
                width: 130,
                formatter: function (cellValue, options, rowObject) {
                    var html = '创建时间：' + rowObject.createTime;
                    if (rowObject.updateTime) {
                        html += '<br>修改时间：' + rowObject.updateTime
                    }

                    if (rowObject.updateBy) {
                        html += '<br>修改管理员：' + rowObject.updateBy
                    }

                    return html;

                }
            },
        ],
        viewrecords: true,
        height: "100%",
        rowNum: 8,
        rowList: [8, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    // 文章内容 实例化百度富文本编辑器
    ue = UE.getEditor('baseDataEditor', {
        initialFrameWidth: 600
    });
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function (action) {
        if (action === 'uploadimage') {
            console.log("正在进行ueditor图片上传....");
            return '/market-admin/admin/upload/action';    /* 这里填上你自己的上传图片的接口地址 */
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    };
});


layui.use(['form', 'laydate', 'upload'], function () {

    var form = layui.form
        , layer = layui.layer
        , $ = layui.jquery
        , upload = layui.upload;

    // form.render();

    //拖拽上传 主图
    upload.render({
        elem: '#productImgIcon'
        , url: baseURL + "admin/upload/uploadFile.do" //改成您自己的上传接口
        , accept: 'image/*' //普通文件
        , done: function (res) {
            // layer.msg('上传成功');
            layui.$("#productImg").removeClass('layui-hide').find('img').attr('src', res.result.fileUrl);
            // console.log(res)
        }
    });
    //拖拽上传 属性图片
    upload.render({
        elem: '#productDetailPicIcon'
        , url: baseURL + "admin/upload/uploadFile.do" //改成您自己的上传接口
        , accept: 'image/*' //普通文件
        , done: function (res) {
            // layer.msg('上传成功');
            layui.$("#productDetailPic").removeClass('layui-hide').find('img').attr('src', res.result.fileUrl);
            // console.log(res)
        }
    });

    //多图片上传
    upload.render({
        elem: '#productBannerBtn'
        , url: baseURL + "admin/upload/uploadFile.do" //改成您自己的上传接口
        , accept: 'image/*' //普通文件
        , exts: 'jpg|jpeg' //只允许上传图片文件
        , multiple: true
        , done: function (res) {
            //上传完毕
            $('#productBanner').append('<img onclick="$(this).remove()" title="点击删除"  width="80" src="' + res.result.fileUrl + '" class="layui-upload-img banner" style="margin-right: 5px;">')
        }
    });

    //监听品类选择
    form.on('select(category)', function (data) {
        vm.product.productCateId = data.value;
    });

    //监听规格定制开关
    form.on('switch(switchDiy)', function (data) {
        if (this.checked) {
            vm.product.productDiyFlag = 1;
            $(".productDiyDiv").show()
            layer.tips('您可以设置想要的规格属性及对应的属性值，属性值可以单独设置默认和加收价格哦', data.othis, {area: ['500px', 'auto'], time: 10000})
        } else {
            vm.product.productDiyFlag = 0;
            $(".productDiyDiv").hide()
        }
    });

    //监听是否选中
    form.on('checkbox(keyitem)', function (obj) {
        //当前元素
        var data = $(obj.elem);
        //遍历父级tr，取第一个，然后查找第二个td，取值
        var allLen = data.parent().parent().find('.keyitem').find('input').length;
        var selectedLen = data.parent().parent().find('.keyitem').find("input:checkbox:checked").length;

        console.log(allLen + "," + selectedLen);
        if (this.checked) {
            //判断是否全部选中了
            if (allLen === selectedLen) {
                data.parent().parent().parent().find('.Takeall').find('input').prop('checked', true);
                form.render('checkbox');
            }
        } else {
            //取消全选的选中状态
            data.parent().parent().parent().find('.Takeall').find('input').prop('checked', false);
            form.render('checkbox');

        }

    });

    //监听全选
    form.on('checkbox(keyitemAll)', function (obj) {
        //当前元素
        var data = $(obj.elem);
        if (this.checked) {
            data.parent().parent().find('.attributeValueList').find('.keyitem').find('input').prop('checked', true);
            form.render('checkbox');
        } else {
            //取消全选的选中状态
            data.parent().parent().find('.attributeValueList').find('.keyitem').find('input').prop('checked', false);
            form.render('checkbox');
        }
    });


    //监听发布状态
    form.on('select(productState)', function (data) {
        vm.product.productState = data.value;
    });

    //自定义验证规则
    form.verify({
        productCateId: function (value) {
            console.log(value)
            if (!value || value === 0) {
                return '请选择商品品类!';
            }
        }
        , productPic: function (value) {
            let productPic = $(".productImg").attr("src");
            if (!productPic) {
                $(".productPic").addClass('blockquoteError');
                return '请上传商品主图！';
            } else {
                $(".productPic").removeClass('blockquoteError');
            }
        }
        , productBanner: function (value) {
            if ($("div[id=productBanner] img").length === 0) {
                $(".productBannerBlock").addClass('blockquoteError');
                return '请上传商品副图！';
            } else {
                $(".productBannerBlock").removeClass('blockquoteError');
            }
        }
    });

    //监听提交
    form.on('submit(productSubmit)', function (data) {
        if (!vm.product.productCateId || vm.product.productCateId <= 0) {
            layer.msg("请选择商品品类！", {icon: 7})
            return false;
        }

        console.log("商品类型：" + vm.product.productCateId);
        vm.product.productPic = $(".productImg").attr("src");
        if (!vm.product.productPic) {
            layer.msg("请上传商品主图！", {icon: 7})
            return false;
        }

        var productBanner = '';
        $("div[id=productBanner] img").each(function () {

            var src = $(this).attr("src");
            if (productBanner) {
                productBanner += ','
            }
            productBanner += src
        });


        if (!productBanner) {
            layer.msg("请上传商品副图！", {icon: 7})
            return false;
        }

        vm.product.productBanner = productBanner;

        var content = "";
        ue.ready(function () {
            content = ue.getContent();
        });
        if (!content) {
            layer.msg("请填写商品详情！", {icon: 7})
            return false;
        }
        vm.product.productDesc = content;

        if (vm.product.productDiyFlag === 1) {//规格定制
            var attributeLen = $("#attributeValueBox").find('.attributeItem').length;
            if (attributeLen === 0) {
                layer.msg("请设置规格！", {icon: 7})
                return false;
            }

            vm.attributeList = [];

            var attributeObjs = $("#attributeValueBox").find('.attributeItem').find('.key_wrap');
            $.each(attributeObjs, function (index, item) {
                var attributeObj = $(this).find('.attributeEntity');

                var valueList = [];
                var valueObjs = $(this).find('.attributeValueList').find('.keyitem');

                //判断是否是新添加的
                var attributeId = attributeObj.attr('data-aid');


                valueObjs.find("input:checkbox:checked").each(function (index, e) {
                    var valueObj = $(this);
                    var attributeValueId = valueObj.attr('data-avid');
                    var attributesValuePic = valueObj.attr('data-pic');
                    console.log("属性值id", attributeValueId)
                    var value = {
                        attributeValueId: (attributeValueId ? attributeValueId : 0),
                        attributeId: attributeId,
                        attributesValuePic: (attributesValuePic ? attributesValuePic : '')
                    };

                    console.log("属性值", value)
                    valueList.push(value);
                });

                var attribute = {
                    productAttribute: {attributeId: attributeId},
                    valueList: valueList
                };
                vm.attributeList.push(attribute);
            });

        }

        vm.saveOrUpdate();
        return false;
    });

});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        product: {},
        productBanner: [],
        cateList: [],
        selectedAttribute: false,//是否已选择规格属性
        attributeList: [], // 已经选中的规格属性 用于乘载请求数据
        queryAttributeList: [], // 修改时已有的规格属性，用于数据展示
        cancelSelectedAttributeValue: [] // 修改时取消勾选属性值
    },
    mounted: function () {
        this.getCateList();
    },
    methods: {
        getCateList: function (event) {
            $.get(baseURL + "product/productcategory/list", function (r) {
                setTimeout(() => {
                    vm.cateList = r.list;
                }, 200);
            });
        },
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.product = {productCateId: vm.cateList.length === 0 ? 0 : vm.cateList[0].id, productState: 1};

            getAllAttribute();
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            getAllAttribute(id)
            vm.getInfo(id)
        },

        saveOrUpdate: function (event) {
            $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function () {
                var data = {product: vm.product, attributeList: vm.attributeList};

                $.ajax({
                    type: "POST",
                    url: baseURL + "product/product/saveOrUpdate",
                    contentType: "application/json",
                    data: JSON.stringify(data),
                    headers: {
                        auth: "ueditor"
                    },
                    success: function (r) {
                        if (r.code === 0) {
                            layer.msg("操作成功", {icon: 1, time: 1000}, function () {
                                location.replace(location.href);
                            });
                        } else {
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
            if (ids == null) {
                return;
            }
            var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                if (!lock) {
                    lock = true;
                    $.ajax({
                        type: "POST",
                        url: baseURL + "product/product/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function (r) {
                            if (r.code == 0) {
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            } else {
                                layer.alert(r.msg);
                            }
                        }
                    });
                }
            }, function () {
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "product/product/info/" + id, function (r) {
                if (r.code !== 0) {
                    layer.alert(r.msg);
                    return
                }
                vm.product = r.product;
                vm.productBanner = r.productBanner;

                $("#cateType").val(vm.product.productCateId);
                $("#productState").val(vm.product.productState);

                if (r.product.productDesc) {
                    var content = r.product.productDesc;
                    ue.ready(function () {
                        ue.setContent(content);
                    });
                }

                $("#productDiyFlag").prop("checked", r.product.productDiyFlag == 1);

                if (r.product.productDiyFlag === 1) {//支持规格定制
                    vm.queryAttributeList = r.queryAttributeList;
                    vm.selectedAttribute = true;

                    $.each(vm.queryAttributeList, function (index, item) {
                        var ADHtml = '<a href="javascript:;" class="label"' +
                            '     <span lay-value="64">' + item.attributeName + '</span>' +
                            '     <input type="hidden" name="fileIds" id="' + item.attributeId + '" value="' + item.attributeName + '">' +
                            '     <i class="layui-icon attributeClose">x</i>' +
                            '</a>';

                        $(".selectedAttribute").find('.AD').append(ADHtml)

                        var valueBoxHtml = '<div class="attributeItem" id="attribute_' + item.attributeId + '" >' +
                            '    <label class="layui-form-label"></label>' +
                            '    <div class="key_wrap size_s layui-input-block">' +
                            '        <span class="closeKey" style="display: none;">' +
                            '            <i class="iconfont iconguanbi"></i>' +
                            '        </span>' +
                            '        <label class="attributeEntity" data-aname="' + item.attributeName + '" data-aid="' + item.attributeId + '">' +
                            '            ' + item.attributeName +
                            '        </label>' +
                            '        <div class="flex_row attributeValueList" style="flex-wrap: wrap;">';

                        $.each(item.valueList, function (index, item1) {
                            valueBoxHtml += '<div class="keyitem flex_row">' +
                                '                <input  ' + (item1.productAttributesValueId > 0 ? "checked=\"\"" : "") + ' type="checkbox" lay-filter="keyitem"' +
                                '   data-default="' + item1.defaultFlag + '" data-extraprice="' + item1.extraPrice + '"' +
                                '   data-avid="' + item1.id + '" name="attr[' + item.attributeId + ']" ' +
                                'data-pic="' + item1.attributesValuePic + '"' +
                                ' lay-skin="primary" title="' + item1.attributeValue + '">' +
                                '                <div class="layui-unselect layui-form-checkbox layui-form-checked" lay-skin="primary">' +
                                '<span>' + item1.attributeValue + '</span>' +
                                '<i class="layui-icon layui-icon-ok"></i>' +
                                '                </div>' +
                                '    <span class="editKey" style="display: none;">' +
                                '        <i class="iconfont iconxdd_edit_square"></i>' +
                                '    </span>' +
                                '                <span class="closeKey" style="display: none;">' +
                                '<i class="iconfont iconguanbi"></i>' +
                                '                </span>' +
                                '           </div>';
                        });

                        valueBoxHtml += '</div><span class="Takeall">' +
                            '            <input ' + (item.allSelect == 1 ? "checked=\"\"" : "") + ' type="checkbox" lay-filter="keyitemAll" data-attributeid="' + item.attributeId + '" name="attr[all]" lay-skin="primary" title="全选" >' +
                            '            <div class="layui-unselect layui-form-checkbox layui-form-checked" lay-skin="primary">' +
                            '                <span>全选</span>' +
                            '                <i class="layui-icon layui-icon-ok"></i>' +
                            '            </div>' +
                            '        </span>' +
                            '        <input maxlength="40" data-attributeid="' + item.attributeId + '" placeholder="请输入商品规格值按回车添加" class="el-input self_input" name="">' +
                            '    </div></div>';

                        $("#attributeValueBox").append(valueBoxHtml);
                    })

                }

                layui.form.render('select');
                layui.form.render('checkbox');
            });
        },
        reload: function (event) {
            location.replace(location.href);
        },
    },
    updated: function () {
        layui.use(['form'], function () {
            layui.form.render();
        });
    },
});

function JumpByEnter() {
    var lKeyCode = (navigator.appname == "Netscape") ? event.which : window.event.keyCode; //event.keyCode按的建的代码，13表示回车
    if (lKeyCode == 13) {
        var searchVal = encodeURIComponent($("#searchVal").val());
        var postJson = {searchVal: searchVal};

        //传入查询条件参数
        $("#jqGrid").jqGrid("setGridParam", {postData: postJson});
        //每次提出新的查询都转到第一页
        $("#jqGrid").jqGrid("setGridParam", {page: 1});
        //提交post并刷新表格
        $("#jqGrid").jqGrid("setGridParam", {url: baseURL + 'product/product/list'}).trigger("reloadGrid");
    }
}

$(document).on('click', '#search_btn', function () {
    var searchVal = encodeURIComponent($("#searchVal").val());
    var postJson = {
        searchVal: searchVal
    };

    //传入查询条件参数
    $("#jqGrid").jqGrid("setGridParam", {postData: postJson});
    //每次提出新的查询都转到第一页
    $("#jqGrid").jqGrid("setGridParam", {page: 1});
    //提交post并刷新表格
    $("#jqGrid").jqGrid("setGridParam", {url: baseURL + 'product/product/list'}).trigger("reloadGrid");
})


function getAllAttribute(productId) {
    productId = productId ? productId : 0;

    //获取已有规格属性
    $.get(baseURL + "product/attributes/all?productId=" + productId, function (r) {
        var tagData = [];
        if (r.code != 0) {
            layer.msg("获取规格数据失败！", {icon: 7})
            return;
        }

        for (var i = 0; i < r.list.length; i++) {
            tagData.push({id: r.list[i].id, name: r.list[i].attributeName})
        }

        //input name 为 fileIds
        $.myMethod("#attributeBox", tagData, "fileIds");
    });
}

// 添加新的规格属性
$(document).on('keypress', '.newAttribute', function (e) {
    if (e.keyCode == 13) {
        var newAttribute = $(".newAttribute").val();
        if (newAttribute) {
            var repeat = false;

            if ($(".AD").find('a').length > 0) {
                $("input[name='fileIds']").each(function (index, item) {
                    if (newAttribute == $(this).val()) {
                        console.log("选中的有重复值")
                        repeat = true;
                    }
                });
            }

            if (!repeat) {
                $.each(allTagData, function (index, item) {
                    if (newAttribute == item.name) {
                        console.log("有重复值")
                        repeat = true;
                    }
                })
            }

            if (!repeat) {
                var attribute = {attributeName: newAttribute};
                $.ajax({
                    type: "POST",
                    async: false,
                    url: baseURL + "product/attributes/save",
                    contentType: "application/json",
                    data: JSON.stringify(attribute),
                    success: function (r) {
                        if (r.code === 0) {
                            allTagData.push({id: r.id, name: newAttribute, newTag: 1});
                            $(".newAttribute").val('');
                            layer.msg("添加成功！", {icon: 1});
                        } else {
                            layer.msg(r.msg, {icon: 7});
                        }
                    }
                });

            } else {
                layer.msg("有重复属性！", {icon: 7})
            }
        }
    }
})

//添加新的规格属性值
$(document).on('keypress', '.self_input', function (e) {
    if (e.keyCode == 13) {
        var newAttributeValue = $(this).val();
        var attributeId = $(this).attr('data-attributeid');

        if (newAttributeValue) {

            var repeat = false;

            var valueList = $(this).parent().find('.attributeValueList').find('.keyitem');
            if (valueList.length > 0) {
                //有属性值 判断是否重复
                $.each(valueList, function (index, item) {
                    var attributeValue = $(this).find('input').attr('title');
                    console.log(attributeValue)
                    if (attributeValue == newAttributeValue) {
                        console.log("有重复值")
                        repeat = true;
                        return;
                    }
                })
            } else {
                $(this).parent().find('.attributeValueList').empty();
            }

            if (!repeat) {

                var html = '<fieldset class="layui-elem-field layui-field-title">' +
                    '  <legend>属性值【' + newAttributeValue + '】</legend>' +
                    '</fieldset>' +
                    '<div class="layui-form">' +
                    '<div class="layui-form-item">' +
                    '    <label class="layui-form-label">图片</label>' +
                    '    <div class="layui-input-block">' +
                    '        <div class="layui-upload-drag" id="productDetailPicIcon">' +
                    '            <i class="layui-icon"></i>' +
                    '            <p>点击上传，或将图片拖拽到此处</p>' +
                    '            <div class="layui-hide" id="productDetailPic">' +
                    '                <hr>' +
                    '                <img class="productDetailPic" src="" alt="上传成功后渲染" style="max-width: 196px">' +
                    '            </div>' +
                    '        </div>' +
                    '    </div>' +
                    '</div>' +
                    '<div class="layui-form-item">' +
                    '      <label class="layui-form-label">加收金额</label>' +
                    '      <div class="layui-input-inline">' +
                    '      <input type="number" min="0" value="0" placeholder="￥" autocomplete="off" class="layui-input extraPrice">' +
                    '  </div>' +
                    '</div>' +
                    '<div class="layui-form-item">' +
                    '      <label class="layui-form-label">默认</label>' +
                    '      <div class="layui-input-inline">' +
                    '      <input type="checkbox" lay-filter="curDefaultFlag" name="curDefaultFlag" lay-skin="switch" lay-text="开启|关闭">' +
                    '  </div>' +
                    '</div>' +
                    '</div> ';

                var self = $(this);
                layer.confirm(html, function (index) {

                    var defaultFlag = 0;
                    var attributesValuePic = $(".productDetailPic").attr('src');
                    console.log(attributesValuePic)
                    if (!attributesValuePic) {
                        attributesValuePic = ""
                    }

                    var attributeValue = {
                        attributeValue: newAttributeValue,
                        extraPrice: $(".extraPrice").val(),
                        defaultFlag: defaultFlag,
                        attributeId: attributeId,
                        attributesValuePic: attributesValuePic
                    };

                    $.ajax({
                        type: "POST",
                        async: false,
                        url: baseURL + "product/attributesvalue/save",
                        contentType: "application/json",
                        data: JSON.stringify(attributeValue),
                        success: function (r) {
                            if (r.code === 0) {
                                var itemhtml = '<div class="keyitem flex_row">' +
                                    '    <input type="checkbox" data-pic="' + attributesValuePic + '"  data-default="' + defaultFlag + '" data-extraprice="' + $(".extraPrice").val() + '" data-avid="' + r.id + '" name="attr[' + attributeId + ']" lay-skin="primary" title="' + newAttributeValue + '" checked="" />' +
                                    '    <span class="closeKey" style="display: none;">' +
                                    '        <i class="iconfont iconguanbi"></i>' +
                                    '    </span>' +
                                    '</div>';

                                self.parent().find('.attributeValueList').append(itemhtml);
                                self.val('');
                                layui.form.render('checkbox');

                                layer.close(index);
                            } else {
                                layer.msg(r.msg, {icon: 7});
                            }
                        }
                    });
                });

                layui.form.render('checkbox');
                // //监听默认开关
                layui.form.on('switch(curDefaultFlag)', function (data) {
                    if (this.checked) {
                        defaultFlag = 1;
                    } else {
                        defaultFlag = 0;
                    }
                    console.log("是否默认：" + defaultFlag)
                });

                layui.use('upload', function () {
                    var $ = layui.jquery
                        , upload = layui.upload;

                    //拖拽上传
                    upload.render({
                        elem: '#productDetailPicIcon'
                        , url: baseURL + "admin/upload/uploadFile.do" //改成您自己的上传接口
                        , accept: 'image/*' //普通文件
                        , acceptMime: 'image/jpg'
                        , exts: 'jpg|jpeg' //只允许上传图片文件
                        , done: function (res) {
                            // layer.msg('上传成功');
                            layui.$("#productDetailPic").removeClass('layui-hide').find('img').attr('src', res.result.fileUrl);
                            // console.log(res)
                        }
                    });

                });

            } else {
                layer.msg("有重复规格值！", {icon: 7})
            }
        }
    }
})

//属性值移入移除事件
$(document).on('mouseover mouseout', '.keyitem', function (e) {
    if (e.type == 'mouseover') {
        $(this).find('.closeKey').show();
        $(this).find('.editKey').show();
    } else {
        $(this).find('.closeKey').hide();
        $(this).find('.editKey').hide();
    }
})

//删除属性值
$(document).on('click', '.closeKey', function () {
    var valueLen = $(this).parent().parent().find('.keyitem').length;

    if (valueLen == 1) {
        $(this).parent().parent().append("尚未添加属性值")
    }
    $(this).parent().remove();
})

//编辑属性值
$(document).on('click', '.editKey', function () {
    var valueObj = $(this).parent().find('input')
    console.log(valueObj)
    var attributesValuePic = valueObj.attr('data-pic');
    var extraPrice = valueObj.attr('data-extraprice');
    var defaultFlag = valueObj.attr('data-default');
    var attributeValue = valueObj.attr('title');

    const html = '<fieldset class="layui-elem-field layui-field-title">' +
        '  <legend>属性值【' + attributeValue + '】</legend>' +
        '</fieldset>' +
        '<div class="layui-form">' +
        '<div class="layui-form-item">' +
        '    <label class="layui-form-label">图片</label>' +
        '    <div class="layui-input-block">' +
        '        <div class="layui-upload-drag" id="productDetailPicIcon">' +
        '            <i class="layui-icon"></i>' +
        '            <p>点击上传，或将图片拖拽到此处</p>' +
        '            <div class="' + (!attributesValuePic ? "layui-hide" : "") + ' " id="productDetailPic">' +
        '                <hr>' +
        '                <img class="productDetailPic" src="' + attributesValuePic + '" alt="上传成功后渲染" style="max-width: 196px">' +
        '            </div>' +
        '        </div>' +
        '    </div>' +
        '</div>' +
        '<div class="layui-form-item">' +
        '      <label class="layui-form-label">加收金额</label>' +
        '      <div class="layui-input-inline">' +
        '      <input type="number" min="0" value="' + extraPrice + '" placeholder="￥" autocomplete="off" class="layui-input extraPrice">' +
        '  </div>' +
        '</div>' +
        '<div class="layui-form-item">' +
        '      <label class="layui-form-label">默认</label>' +
        '      <div class="layui-input-inline">' +
        '      <input type="checkbox" lay-filter="curDefaultFlag" checked="' + (defaultFlag === 1) + '" name="curDefaultFlag" lay-skin="switch" lay-text="开启|关闭">' +
        '  </div>' +
        '</div>' +
        '</div> ';

    layer.confirm(html, function (index) {

        var attributesValuePic = $(".productDetailPic").attr('src');
        console.log(attributesValuePic)
        if (!attributesValuePic) {
            attributesValuePic = ""
        }

        valueObj.attr('data-pic', attributesValuePic);
        valueObj.attr('data-default', defaultFlag)
        valueObj.attr('data-extraprice', $(".extraPrice").val())

        layui.form.render('checkbox');

        layer.close(index);
    });

    layui.form.render('checkbox');
    // //监听默认开关
    layui.form.on('switch(curDefaultFlag)', function (data) {
        if (this.checked) {
            defaultFlag = 1;
        } else {
            defaultFlag = 0;
        }
        console.log("是否默认：" + defaultFlag)
    });

    layui.use('upload', function () {
        var $ = layui.jquery
            , upload = layui.upload;

        //拖拽上传
        upload.render({
            elem: '#productDetailPicIcon'
            , url: baseURL + "admin/upload/uploadFile.do" //改成您自己的上传接口
            , accept: 'image/*' //普通文件
            , acceptMime: 'image/jpg'
            , exts: 'jpg|jpeg' //只允许上传图片文件
            , done: function (res) {
                // layer.msg('上传成功');
                layui.$("#productDetailPic").removeClass('layui-hide').find('img').attr('src', res.result.fileUrl);
                // console.log(res)
            }
        });

    });
})



