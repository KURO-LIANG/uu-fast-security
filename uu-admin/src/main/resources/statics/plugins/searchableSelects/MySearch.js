var allTagData;
$.extend({
    myMethod: function (id,tagData,name) {
		allTagData = tagData;

    	// var html  =  '<div class="layui-form-item selectedAttribute">';
		// 	html +=		 '<label class="layui-form-label">已选择规格</label>';
		// 	html +=		 '<div class="AD">';
		// 	html +=		 '</div>';
		// 	html +=	 '</div>';
		var html =	 '<div class="layui-form-item">';
			html +=				'<label class="layui-form-label">选择</label>';
			html +=	     '<div class="layui-input-inline fileId layui-form-select layui-form-selected" style="width: 300px">';
			html +=			'<input type="text" class="layui-input" placeholder="输入或选择已有规格" autocomplete="off">';
			html +=		   	'<dl style="display: none;" id="dlBox"></dl>';
			html +=		 '</div>';
			html +=	     '<div class="layui-input-inline" style="width: 400px">';
			html += 		'<div class="addAttributeBox">' +
							'   <input type="text" class="layui-input newAttribute" placeholder="您也可以输入自定义规格属性名按回车添加" style="width: 300px;float: left;"/>' +
							// '   <button type="button" class="layui-btn layui-btn-primary addAttributeBtn" style="margin-left: 10px;"><i class="fa fa-plus"></i>&nbsp;添加</button>' +
							'</div>';
			html +=		 '</div>';
			html +=	 '</div>';

    	$(id).append(html);
        // $(".AD").parent().hide();

	    //点击当前规格属性
	    $(".fileId").on("click","dl dd",function(){
	    	var id = $(this).attr("value");
	    	var newtag = $(this).attr("data-newtag");

			var attributeName = $(this).html();
			if(id != undefined){
	    		$(".AD").append('<a href="javascript:;" class="label"><span lay-value="64">'+ attributeName +'</span><input type="hidden" name="'+ name +'" id="'+ id +'" value="'+ attributeName +'"/><i class="layui-icon attributeClose">x</i></a>')
	    		$(".AD").parent().show();
	    		for(var i=0;i<allTagData.length;i++){
	    			if(allTagData[i].id == id){
	    				allTagData.splice(i,1);
	    			}
	    		}
	    	}
	    	$(".fileId dl").hide();
	    	$(".fileId input").val("");

	    	var html = 	'<div class="attributeItem" id="attribute_' + id + '">' +
						'    <label class="layui-form-label"></label>' +
						'    <div class="key_wrap size_s layui-input-block">' +
						'    <span class="closeKey" style="display: none;">' +
						'        <i class="iconfont iconguanbi"></i>' +
						'    </span>' +
						'        <label class="attributeEntity" data-aname="'+ attributeName + '" data-aid="'+ id +'">'+ attributeName +'</label>' +
						'        <div class="flex_row attributeValueList" style="flex-wrap: wrap;">';

	    				if(newtag != 1){
							//获取属性值列表
							$.ajax({
								type: "GET",
								async: false,
								url: baseURL + "product/attributesvalue/all?id="+id,
								contentType: "application/json",
								success: function(r){
									if(r.code === 0) {
										if(r.list.length > 0){
											for (let i = 0; i < r.list.length; i++) {
												var item = r.list[i];
												var priceHtml = '';
												if(item.extraPrice > 0){
													priceHtml += '&nbsp;&nbsp;&nbsp;&nbsp;<span>￥' + item.extraPrice + '</span>';
												}
												html += '<div class="keyitem flex_row">' +
													'    <input type="checkbox" lay-filter="keyitem" ' +
													'data-default="'+item.defaultFlag+'" ' +
													'data-extraprice="'+item.extraPrice+'" ' +
													'data-avid="'+ item.id + '" name="attr[' + id + ']" lay-skin="primary" ' +
													'title="' + item.attributeValue + priceHtml + '" checked="" />' +
													'    <span class="editKey" style="display: none;">' +
													'        <i class="iconfont iconxdd_edit_square"></i>' +
													'    </span>' +
													'    <span class="closeKey" style="display: none;">' +
													'        <i class="iconfont iconguanbi"></i>' +
													'    </span>' +
													'</div>';
											}
										}else{
											html += '尚未添加属性值';
										}
									}
								}
							});
						}else{
	    					html += '尚未添加属性值';
						}

						html += '        </div>' +
						'        <span class="Takeall">' +
						'            <input type="checkbox" lay-filter="keyitemAll" data-attributeid="'+ id + '" name="attr[all]" lay-skin="primary" title="全选" checked="">' +
						'        </span>' +
						'        <input maxlength="40" data-attributeid="'+ id + '" placeholder="请输入商品规格值按回车添加" class="el-input self_input">' +
						'    </div>' +
						'</div>';

			$("#attributeValueBox").append(html);
			vm.selectedAttribute = true;
			layui.form.render('checkbox');
		})

		function AD(name,id){
            this.name=name;
            this.id=id;
        }

	 	//删除当前广告
		$(".AD").on("click",".attributeClose",function(){
			$(this).parent().remove();

			if($(".AD").find('a').length == 0){
				// $(".selectedAttribute").hide();
				vm.selectedAttribute = false;
			}

			var id = $(this).parent().children("input").attr("id");
			var text = $(this).parent().children("input").val();
			allTagData.push(new AD(text,id))

			$("#attribute_" + id + "").remove();
		})

		//筛选
	    $(".fileId input").on("input propertychange",function(){
	    	$(".fileId dl dd").remove();
	    	$(".fileId dl").hide();
    		if(allTagData.length>0){
    			$(".fileId dl").show();
    			var sear = new RegExp($(this).val())
    			var temp=0;
	    		for(var i=0;i<allTagData.length;i++){
	    			if(sear.test(allTagData[i].name)){
	    				temp++
		    			$(".fileId dl").append('<dd value="'+allTagData[i].id+'">'+allTagData[i].name+'</dd>')
	    			}
	    		}
	    		if(temp==0){
	    			$(".fileId dl").append('<dd>暂无数据</dd>')
	    		}
    		}
	   	})

	   	//隐藏
	   	$(document).click(function(){
			$(".fileId dl").hide();
	    	$(".fileId input").val("");
		});

	 	//显示没被选择的
		$(".fileId input").click(function(event){
			$(".fileId dl dd").remove();
	    	if(allTagData.length==0){
				$(this).val("暂无数据")
	    	}else{
		    	$(".fileId dl").show()
	    	}
	    	for(var i=0;i<allTagData.length;i++){
	    		$(".fileId dl").append('<dd data-choosetype="'+ allTagData[i].chooseType + '" data-newtag="'+ allTagData[i].newTag + '" value="'+allTagData[i].id+'">'+allTagData[i].name+'</dd>')
    		}
			event.stopPropagation();
		});
    }
});
