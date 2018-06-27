define(function(require, exports, module){
	
	var $ = require('jquery');
	var ImgCorp = require('../../util/img-corp.js');
	require('jquery.layer');
	
	$('.fileinput-button').click(function(){
		var imgCorp = new ImgCorp();
		imgCorp.init({sizes : {w : 200, h : 200}}, function(data){
			var html='<img class="img-thumbnail" src="'+ $CONFIG.base_url +'/commons/attachment/download/'+data.id+'?t='+Date.parse(new Date())+'"/>';
        	$('#file-preview').empty().append(html);
        	$('#avatar').val(data.id);
		});
	});

	$(".add-submit").click(function(){
		if($("#avatar").val() == ''){
			$.jBox.error('该图片已存在，请选择新图片后在保存!', '错误');	
		}else {
			var $form = $("#avatar-form");
			var posting = $.post($CONFIG.base_url + "/api/user/profile/avatar/update", $form.serialize());
			
			/* Put the results in a div */
			posting.done(function(data) {
				layer.msg('保存成功', {
					time: 4000 //4s后自动关闭
				});
				window.location.href = $CONFIG.base_url + "/admin/profile/avatar";
			});
		}
	}); 
});