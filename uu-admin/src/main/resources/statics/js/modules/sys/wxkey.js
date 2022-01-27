layui.use('element', function(){
    var $ = layui.jquery
        ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
});

$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/wxkey/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '关键字', name: 'keyWord', index: 'key_word', width: 80 }, 			
			{ label: '回复类型', name: 'msgType', index: 'msg_type', width: 80 , formatter:function (cellValue) {
                    if(cellValue === "text"){
                        return "文本消息";
                    }else if(cellValue === "news"){
                        return "图文消息";
                    }else{
                        return "图片消息"
                    }
                } },
			{ label: '文本内容', name: 'content', index: 'content', width: 80 }, 			
			{ label: '媒体ID', name: 'mediaid', index: 'mediaID', width: 80 }			
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
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		wxKey: {},
        keys:[],
        resMsgArr:[],
        baseData:{}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.wxKey = {};
			vm.keys = [];

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
		    $('#btnSaveOrUpdate').button('loading').delay(500).queue(function() {

                var keywords = "";
                $.each($(".keywords"),function (index) {
                    if(index === 0){
                        keywords += $(".keywords").eq(index).val();
                    }else{
                        keywords += "," + $(".keywords").eq(index).val();
                    }
                })

                if (keywords === "") {
                    layer.msg("关键字不能为空",{icon:7,time:2000})
                    return;
                }

                if(vm.wxKey.msgType != "text"){
                    if(vm.wxKey.mediaid === undefined){
                        layer.msg("请选择图文",{icon:7,time:2000})
                        return;
                    }
                }

		        if((vm.wxKey.msgType != "text"
                    && (vm.wxKey.mediaid === undefined || vm.wxKey.mediaid === "")) ||
                    (vm.wxKey.msgType === "text" && (vm.wxKey.content === undefined || vm.wxKey.content === ""))){
                    layer.msg("请选择回复内容", {icon: 7});
                    $('#btnSaveOrUpdate').button('reset');
                    $('#btnSaveOrUpdate').dequeue();
                    return;
                }

                vm.wxKey.keyWord = keywords

		        var url = vm.wxKey.id == null ? "sys/wxkey/save" : "sys/wxkey/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.wxKey),
                    success: function(r){
                        if(r.code === 0){
                             layer.msg("操作成功", {icon: 1});
                            location.replace(location.href)
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
                        url: baseURL + "sys/wxkey/delete",
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
			$.get(baseURL + "sys/wxkey/info/"+id, function(r){
                vm.wxKey = r.wxKey;
                vm.keys = r.keys;

                if(vm.wxKey.msgType === "text"){
                    $(".send").css('display','block');
                    $(".jsMsgSendTab").css('display','none');
                }

            });
		},
		reload: function (event) {
            location.replace(location.href)
		}
	}
});

//关键词 删除按钮移入移除
$(document).on('mouseover mouseout','.keyword',function (event){
    var len = $(".keyword").length;
    if(len > 1){
        if(event.type == "mouseover"){
            $(this).find('.weui-desktop-opr-btn_remove').parent().css('display','initial');
        }else if(event.type == "mouseout"){
            $(this).find('.weui-desktop-opr-btn_remove').parent().css('display','none');
        }
    }
})
$(document).on('mouseover mouseout','.inner',function (event){
    $(".weui-desktop-media__opr").css('display','block')
    if(event.type == "mouseover"){
        $(".weui-desktop-media__opr").css('display','block')
    }else if(event.type == "mouseout"){
        $(".weui-desktop-media__opr").css('display','none')
    }
})

//回复内容选择条
$(document).on('mouseover mouseout','.msg_sender_area',function (event){
    if(event.type == "mouseover"){
        $(".msg_sender_wrp").css('display','initial');
    }else if(event.type == "mouseout"){
        $(".msg_sender_wrp").css('display','none');
    }
})

$(document).on('click','.keyword_add',function () {
    var addhtml = '<div class="col-sm-10 addFormControls keyword">' +
        '<input class="keywords form-control" autocomplete="off" placeholder="关键词" type="text"> ' +
        '<span class="weui-desktop-form__input-append-out"> <div class="weui-desktop-link-group"> ' +
        '<div class="weui-desktop-tooltip__wrp weui-desktop-link"> ' +
        '<button type="button" href="javascript:;" title="添加" class="weui-desktop-opr-btn weui-desktop-opr-btn_add keyword_add">添加</button> ' +
        '</div> <div class="weui-desktop-tooltip__wrp weui-desktop-link" style="display: none;"> ' +
        '<button type="button" href="javascript:;" title="删除" class="weui-desktop-opr-btn weui-desktop-opr-btn_remove keywords_del">删除</button> </div> ' +
        '</div> </span></div>';

    $(this).closest('.keyrow').append(addhtml);
    $(this).parent().css('display','none');
})
//关键字删除
$(document).on('click','.keywords_del',function () {
    $(this).closest('.formControls').remove();
    $(".keyword").eq(0).removeClass('addFormControls');
    var len = $(".keyword").length;
    if(len == 1){
        $(".keyword").eq(0).find('.keyword_add').parent().css('display','inline-block');
    }
    $(".jsMsgSendTab").css('display','block');
})
//回复内容 文字删除
$(document).on('click','.textbtn_del',function () {
    $(this).closest('.weui-desktop-media').remove();
    $(".send").css('display','none');
    var len = $(".keyword").length;
    if(len == 1){
        $('.msg_sender_area').css('display','block');
    }
})

//从素材库选择图文消息素材
$('.chooseMedia').on( 'click', function () {
    var url= baseURL + "sys/wxpublicmenu/wxMaterialForm";
    console.log(url)
    layer_show('选择素材',url,'1000','820');
});
//从素材库选择图片素材
$('.choosePic').on( 'click', function () {
    var url=baseURL + "sys/wxpublicmenu/wxPicForm";
    layer_show('选择素材',url,'800','640');
});

//添加图文回复
$(document).on('click','.weui-desktop-msg-sender__tab_appmsg',function () {
    var url= baseURL + "sys/wxpublicmenu/wxMaterialForm";
    console.log(url)
    layer_show('选择素材',url,'1000','720');
})
//添加图片回复
$(document).on('click','.weui-desktop-msg-sender__tab_pic',function () {
    var url= baseURL + "sys/wxpublicmenu/wxPicForm";
    console.log(url)
    layer_show('选择素材',url,'1000','720');
})

//添加文字回复
$(document).on('click','.weui-desktop-msg-sender__tab_text',function () {
    var url= baseURL + "sys/wxpublicmenu/addKeyWordTextForm";
    layer_show('添加回复文字',url,'800','600');
})
//修改文字回复
$(document).on('click','.textbtn_update',function () {
    var resText = $(".resText").text();
    resText = encodeURI(encodeURI(resText));
    var url= baseURL + "sys/wxpublicmenu/addKeyWordTextForm?resText=" + resText;
    layer_show('修改回复文字',url,'800','600');
})


//删除素材
$(document).on('click','.jsmsgSenderDelBt',function () {
    $(this).parent().remove();
    $(".send").css('display','none');
    $(".msg_sender_area").css('display','block');
})

//以下是自动回复的

$.get(baseURL + "sys/basedata/autoInfo/0", function(r){
    if(r.code === 0){
        vm.resMsgArr = r.resMsgList;
        if(r.baseData != null){
            vm.baseData = r.baseData;
        }
    }

});

$(document).on('click','.weui-desktop-msg-sender__tab',function () {
    $(this).parent().find('.weui-desktop-msg-sender__tab').removeClass('weui-desktop-msg-sender__tab_selected');
    $(this).addClass('weui-desktop-msg-sender__tab_selected');
    $(this).parent().parent().parent().find(".weui_tab").css('display','none');
    $(this).parent().parent().parent().find(".weui_tab").eq($(this).index()).css('display','block');

    var idx = $(this).index();
    if(idx == 0){
        $(this).closest('.row').attr('data-msgtype','text');
    }else if(idx == 1){
        $(this).closest('.row').attr('data-msgtype','image');
    }else{
        $(this).closest('.row').attr('data-msgtype','news');
    }
})


$("#btn_confirm").click(function () {
    var resMsgArr = [];

    var isPass = true;

    $(".careRes").find(".row").each(function () {
        var careResObj = {};
        var resText = $(this).find(".edit_area").val();
        var mediaID = $(this).find(".msg_sender_media").attr("data-mid");
        var msgType = $(this).attr("data-msgtype");

        console.log(resText)
        console.log(mediaID)
        console.log(msgType)

        if (mediaID === undefined && resText === "") {
            alert("回复内容不能为空")
            isPass = false;
            return false;
        }



        if(msgType == "text"){
            careResObj.msgType = msgType;
            careResObj.resText = resText;
        }else{
            careResObj.msgType = msgType;
            careResObj.mediaID = mediaID;
        }

        resMsgArr.push(careResObj);
    });

    if(isPass){
        vm.baseData.content = JSON.stringify(resMsgArr);
        vm.baseData.sourcetype = 0;

        console.log(vm.baseData.content)

        var url = vm.baseData.id == null ? "sys/basedata/save" : "sys/basedata/update";

        layer.confirm('确认提交？',function(index){
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.baseData),
                success: function(r){
                    if(r.code === 0){
                        layer.msg("操作成功", {icon: 1,time:1000});
                    }else{
                        layer.alert(r.msg);
                    }
                }
            });
        });
    }


})

$("#btn_confirm_add").click(function () {
    var length = $(".choosePic").length;

    var careResHtml = '<div class="row cl" data-msgType="text" style="margin-top:30px;padding: 0px 20px;">' +
        '<div style="height: 35px;border: 1px solid #E4E8EB;">' +
        '<ul class="weui-desktop-msg-sender__tabs">' +
        '<li class="weui-desktop-msg-sender__tab weui-desktop-msg-sender__tab_text weui-desktop-msg-sender__tab_selected">' +
        '文字</li><li class="weui-desktop-msg-sender__tab weui-desktop-msg-sender__tab_img">图片</li>' +
        '<li class="weui-desktop-msg-sender__tab weui-desktop-msg-sender__tab_audio">图文</li>' +
        '</ul><div class="layui-layer-btn" style="float: right;">' +
        '<a class="layui-layer-btn0 btn_del btn-danger"  style="background-color: #c62b26;border-color: #c62b26;">删除</a> </div></div>' +
        '<div class="emotion_editor publisher">' +
        '<div class="weui_tab">' +
        '<textarea name="content" class="edit_area" style="width: 100%;border: 0;resize:none" ></textarea>'+
        '<div class="editor_toolbar"><div class="weui-desktop-popover__wrp">' +
        '<span>' +
        '<a href="javascript:void(0);" class="icon_emotion emotion_switch trigger">表情</a>' +
        '</span>' +
        '</div>' +
        '<div class="emotion_wrp"></div></div></div>' +
        '<div class="weui_tab resPic" style="display: none;padding: 17px 20px;min-height: 215px;">' +
        '<div class="tab_cont_cover jsMsgSendTab" data-index="0" style="width: 100%;">' +
        '<div style="width: 48.6%;float: left;">' +
        '<span class="create_access choosePic" data-resIdx="'+ length +'">' +
        '<a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;" data-type="10" data-index="0">' +
        '<i class="icon36_common add_gray"></i>' +
        '<strong>从素材库中选择</strong></a></span></div>' +
        '<div style="width: 48.6%;float: right;">' +
        '<span class="create_access img_upload">' +
        '<a  class="add_gray_wrp create_new_appmsg" href="javascript:;">' +
        '<i class="icon36_common add_gray"></i>' +
        '<strong>上传图片</strong></a></span>' +
        '<div style="position: absolute; top: 42px; left: 78.5625px; width: 56px; height: 57px; overflow: hidden; bottom: auto; right: auto;"> ' +
        '<input class="wxPicUpload" type="file" accept="image/bmp,image/png,image/jpeg,image/jpg,image/gif" style="display: none;"> ' +
        '<label style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label> ' +
        '</div></div></div></div>' +
        '<div class="weui_tab resPicAndText" style="display: none;padding: 17px 20px;min-height: 215px;">' +
        '<div class="tab_cont_cover jsMsgSendTab" data-index="0" style="width: 100%;">' +
        '<div style="width: 48.6%;float: left;">' +
        '<span class="create_access chooseMedia" data-resIdx="'+ length +'">' +
        '<a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;" data-type="10" data-index="0">' +
        '<i class="icon36_common add_gray"></i>' +
        '<strong>从素材库中选择</strong></a>' +
        '</span>' +
        '</div><div style="width: 48.6%;float: right;"><span class="create_access">' +
        '<a target="_blank" class="add_gray_wrp create_new_appmsg" href="javascript:;">' +
        '<i class="icon36_common add_gray"></i>' +
        '<strong>新建图文消息</strong></a>' +
        '<a target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&amp;action=edit&amp;type=10&amp;isMul=1&amp;isNew=1&amp;lang=zh_CN&amp;token=2027588817"><i class="icon_appmsg_selfcreate"></i><strong>自建图文</strong></a> ' +
        '<a target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&amp;action=edit&amp;type=10&amp;isMul=1&amp;isNew=1&amp;share=1&amp;lang=zh_CN&amp;token=2027588817"><i class="icon_appmsg_share"></i><strong>分享图文</strong></a> ' +
        '</span>' +
        '</div></div></div></div></div>';
    $(".careRes").append(careResHtml);
})

$(document).on('click','.btn_del',function () {
    $(this).closest('.row').remove();
})

$.emoticons({
    'activeCls':'trigger-active'
},function(api){
    var $content = $('textarea[name="content"]');
    var $result = $('#result');
    $('#format').click(function(){
        $result.html(api.format($content.val()));
    });
});