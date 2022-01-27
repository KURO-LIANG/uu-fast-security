

$.get(baseURL + "sys/wxpublicmenu/getOnlineWxMaterial?type=image", function(r){
    if(r.code === 0){
        $(".img_list").html('');
        var len = r.result.item.length;
        var html='';
        for(var i =0;i < len;i++){
            var mediaID = r.result.item[i].media_id;
            var name = r.result.item[i].name;
            var url = r.result.item[i].url;
            html += '<li class="img_item js_imageitem" data-mid="'+mediaID+'" data-url="'+url+'" data-oristatus="0" data-format="jpeg">'+
                '<label class="frm_checkbox_label img_item_bd">'+
                '<div class="pic_box">'+
                '<img class="pic js_pic" src="'+url+'" style="height: 117px;">'+
                '</div>'+
                '<span class="lbl_content">'+name+'</span>'+
                '<div class="selected_mask">'+
                '<div class="selected_mask_inner"></div>'+
                '<div class="selected_mask_icon"></div>'+
                '</div>'+
                '</label>'+
                '</li>';

        }
        $(".img_list").append(html)
        var count = r.result.total_count;
        var totalPage = Math.ceil(count/10);
        if(totalPage > 1){
            $(".page_num").find('label').eq(1).text(totalPage);
            $(".pageNavigator").css('display','block');
        }
    }else{
        layer.msg(r.msg,{icon:7,time:2000});
    }
});

Date.prototype.Format = function(fmt) { //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}

//上一页
$(document).on('click','.page_prev',function () {
    var curPage=$(".page_num").find('label').eq(0).text();
    curPage--;
    console.log(curPage)
    if(curPage == 1){
        $(this).css('display','none');
    }

    $(".page_num").find('label').eq(0).text(curPage);

    $(".page_next").attr('style','');

    $.get(baseURL + "sys/wxpublicmenu/getOnlineWxMaterial?type=image&pageNo=" + curPage, function(r){
        if(r.code === 0){
            $(".img_list").html('');

            var len = r.result.item.length;
            var html='';
            for(var i =0;i < len;i++){
                var mediaID = r.result.item[i].media_id;
                var name = r.result.item[i].name;
                var url = r.result.item[i].url;
                html += '<li class="img_item js_imageitem" data-mid="'+mediaID+'" data-url="'+url+'" data-oristatus="0" data-format="jpeg">'+
                    '<label class="frm_checkbox_label img_item_bd">'+
                    '<div class="pic_box">'+
                    '<img class="pic js_pic" src="'+url+'" style="height: 117px;">'+
                    '</div>'+
                    '<span class="lbl_content">'+name+'</span>'+
                    '<div class="selected_mask">'+
                    '<div class="selected_mask_inner"></div>'+
                    '<div class="selected_mask_icon"></div>'+
                    '</div>'+
                    '</label>'+
                    '</li>';

            }
            $(".img_list").append(html)

        }
    })
})

//下一页
$(document).on('click','.page_next',function () {
    var curPage=$(".page_num").find('label').eq(0).text();
    var totalPage=$(".page_num").find('label').eq(1).text();
    curPage++;
    $(".page_prev").attr('style','');
    if(curPage == totalPage){
        $(this).css('display','none');
    }

    $(".page_num").find('label').eq(0).text(curPage);


    $.get(baseURL + "sys/wxpublicmenu/getOnlineWxMaterial?type=image&pageNo=" + curPage, function(r){
        if(r.code === 0){
            $(".img_list").html('');
            var len = r.result.item.length;
            var html='';
            for(var i =0;i < len;i++){
                var mediaID = r.result.item[i].media_id;
                var name = r.result.item[i].name;
                var url = r.result.item[i].url;
                html += '<li class="img_item js_imageitem" data-mid="'+mediaID+'" data-url="'+url+'" data-oristatus="0" data-format="jpeg">'+
                    '<label class="frm_checkbox_label img_item_bd">'+
                    '<div class="pic_box">'+
                    '<img class="pic js_pic" src="'+url+'" style="height: 117px;">'+
                    '</div>'+
                    '<span class="lbl_content">'+name+'</span>'+
                    '<div class="selected_mask">'+
                    '<div class="selected_mask_inner"></div>'+
                    '<div class="selected_mask_icon"></div>'+
                    '</div>'+
                    '</label>'+
                    '</li>';

            }
            $(".img_list").append(html)

        }
    })
})

//点击素材 选中唯一一个
$(document).on('click','.img_item_bd',function () {
    $(".img_item_bd").removeClass('selected');
    $(this).addClass('selected');
})

//选中提交
$(".js_btn").click(function () {
    var index=$(this).attr('data-index');
    if(index == 1){
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);//关闭窗口
    }else{

        var mid=$(".img_list").find('.selected').parent().attr('data-mid');
        var mediaHtml = '<div class="msg_sender_media" data-mid ="'+mid+'" data-msgType="image" >';
        mediaHtml += $(".img_list").find('.selected').parent().html();

        mediaHtml += '<a href="javascript:;" class="jsmsgSenderDelBt link_dele" data-type="10" onclick="return false;">删除</a></div>'
        parent.$(".js_appmsgArea").find('.jsMsgSendTab').css('display','none');
        parent.$(".js_appmsgArea").append(mediaHtml);
        parent.$(".send").css('display','block');
        parent.$(".msg_sender_area").css('display','none');

        parent.$(".msg_sender_media").find(".img_item_bd").removeClass("selected")
        parent.$("#menuList").find(".current").attr('data-mid',mid);
        parent.$("#menuList").find(".current").attr('data-type','TYPE_media_id');

        if(parent.vm.wxKey != undefined){
            parent.vm.wxKey.msgType = "image";
            parent.vm.wxKey.mediaid = mid;
        }

        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);//关闭窗口
    }
})