$.emoticons({
    'activeCls':'trigger-active'
},function(api){
    var $content = $('textarea[name="content"]');
    var $result = $('#result');
    $('#format').click(function(){
        $result.html(api.format($content.val()));
    });
});

var o_content = parent.$(".resText").text()
if(o_content){
    $(".edit_area").text(o_content)
}

$("#btn_confirm").click(function () {
    var resContent = $(".edit_area").val();
    if(resContent == ""){
        layer.msg("回复内容不能为空",{icon:7,time:2000})
        return;
    }

    parent.$(".send").find('.tab_content').eq(0).find('.inner').empty()

    var txtHtml = '<div class="weui-desktop-media">' +
        '<svg xmlns="http://www.w3.org/2000/svg" ' +
        'style="width: 0px; height: 0px; visibility: hidden; ' +
        'position: absolute; z-index: -1;">' +
        '<symbol id="common-edit" viewBox="0 0 16 17">' +
        '<path d="M8 1H2a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h11a2' +
        ' 2 0 0 0 2-2V8h-2v7H2V3h6V1z"></path>' +
        '<path d="M15.185 2.714l.448-.448c.3-.3.31-.81' +
        ' 0-1.118l-.296-.296a.787.787 0 0 0-1.118 0l' +
        '-.448.448 1.414 1.414zm-.707.707L8.414 9.485' +
        ' 7 8.071l6.064-6.064 1.414 1.414zm-8.15 6.21L7' +
        ' 8.071l1.414 1.414-1.56.673c-.515.222-.747-.016-.526-.527z"></path></symbol>' +
        '<symbol id="common-del" viewBox="0 0 16 18">' +
        '<path d="M1 5c-.556 0-1-.448-1-1 0-.556.448-1 1-1h14c.555 0 1 .' +
        '448 1 1 0 .556-.448 1-1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V5zm1' +
        '2 0H3v11h10V5zM5.99 0h4.02A1 1 0 0 1 11 1v1H5V1c0-.556.444-1 .9' +
        '9-1zM10 7c.556 0 1 .446 1 .997v6.006c0 .544-.448.997-1 .997-.556 0' +
        '-1-.446-1-.997V7.997C9 7.453 9.448 7 10 7zM6 7c.556 0 1 .446 1 .997' +
        'v6.006c0 .544-.448.997-1 .997-.556 0-1-.446-1-.997V7.997C5 7.453 5.' +
        '448 7 6 7z"></path></symbol></svg>' +
        '<div class="weui-desktop-media-text"><span style="margin-top: 9px;float: left;margin-right: 16px;" class="resText">'+resContent+'</span>' +
        ' <div class="weui-desktop-media__opr" style="display: none">' +
        '<div class="weui-desktop-tooltip__wrp weui-desktop-link">' +
        '<button type="button" class="weui-desktop-icon-btn weui-desktop-opr-btn weui-desktop-opr-btn_primary textbtn_update"><svg width="16" height="17" xmlns="http://www.w3.org/2000/svg"><use xlink:href="#common-edit"></use></svg></button> ' +
        '<span class="weui-desktop-tooltip weui-desktop-tooltip__down-center">编辑</span>' +
        '</div> <div class="weui-desktop-tooltip__wrp weui-desktop-link">' +
        '<button type="button" class="weui-desktop-icon-btn weui-desktop-opr-btn weui-desktop-opr-btn_primary textbtn_del"><svg width="16" height="18" xmlns="http://www.w3.org/2000/svg"><use xlink:href="#common-del"></use></svg></button> <span class="weui-desktop-tooltip weui-desktop-tooltip__down-center">删除</span></div></div></div></div>'

    parent.$(".send").find('.tab_content').eq(0).find('.inner').append(txtHtml);

    parent.vm.wxKey.msgType = "text";
    parent.vm.wxKey.content = resContent;

    parent.$(".send").css('display','block');
    parent.$(".msg_sender_area").css('display','none');

    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);//关闭窗口
})

function cancel(){
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);//关闭窗口
}

$("#btn_url").click(function () {
    var urlTransfer = $(".urlTransfer").val();
    var urlShow = $(".urlShow").val();
    if(urlTransfer == "" || urlShow == ""){
        layer.msg("转化链接及文本不能为空",{icon:7,time:2000})
        return;
    }
    var data = {
        url:urlTransfer
    }
    $.ajax({
        type: "POST",
        url: baseURL + "sys/wxkey/urlTransfer",
        data: data,
        success: function(r){
            if(r.code === 0){
                $(".edit_area").val($(".edit_area").val()+'<a href="'+ r.url +'">'+ urlShow +'</a>')
            }else{
                layer.alert(r.msg);
            }
        }
    });
})