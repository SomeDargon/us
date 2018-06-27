/*!
 * Author: 57
 * Description: 图片预览组件
 */

define(function(require, exports, module){
	
	var $ = require('jquery');
	var template = require('template');
	require('bootstrap');

	module.exports = ImgPreview;
	function ImgPreview(){
		this.options = {
			param: {id: 0}
		};
	};

	// 定义模板并渲染模板
	var source ='<div id="img-preview-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'
		+ '<div class="modal-dialog" style="width:970px;">'
		+ '<div class="modal-content" style="min-height:520px;">'
		+ '	<div class="modal-header" style="padding:5px;">'
		+ '		<button type="button" class="btn btn-sm btn-default" id="zoom-in" style="margin-right:10px;">'
		+ '			<span class="glyphicon glyphicon-zoom-in"></span>'
		+ '		</button>'
		+ '		<button type="button" class="btn btn-sm btn-default" id="zoom-out">'
		+ '			<span class="glyphicon glyphicon-zoom-out"></span>'
		+ '		</button>'
		+ '		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
		+ '	</div>'
		+ '	<div class="modal-body" style="margin:auto;height:500px;text-align:center;overflow:auto;" id="pre-img-body">'
		+ '		<img id="preview-pic" class="dragAble" src="' + $CONFIG.g_base + '/commons/attachment/download/{{= id }}" height="400px" />'
		+ '	</div>'
		+ '</div>'
		+ '</div>'
		+ '</div>';
	var render = template.compile(source);


	ImgPreview.prototype.load = function load(id) {
		exports.imgPreview(id);
	}

	$("body").on("click", ".img-preview-click", function(e){
		var id = $(this).attr("data-id");
		exports.imgPreview(id);
	})

	exports.imgPreview = function(id){
		$('div').remove('#img-preview-container');

		var html = render({id:id});
		$('body').append('<div id="img-preview-container"></div>');
		$('#img-preview-container').empty().append(html);
		$('#img-preview-modal').modal();

		//操作卡放大缩小
		$("#zoom-in").on("click", function () {
			exports.ImageChange(true);
		});
		$("#zoom-out").on("click", function () {
			exports.ImageChange(false);
		});
	};

	exports.ImageChange = function (args) {
		var oImg = $("#preview-pic");
		var oWidth=oImg.width(); //取得图片的实际宽度
		var oHeight=oImg.height(); //取得图片的实际高度
		if (args && oWidth* 1.2 <=950) {
			oImg.width(oWidth * 1.2);
			oImg.height(oHeight * 1.2);
		}else if(oWidth/1.2>100){
			oImg.width(oWidth / 1.2);
			oImg.height(oHeight / 1.2);
		}
	};
});
