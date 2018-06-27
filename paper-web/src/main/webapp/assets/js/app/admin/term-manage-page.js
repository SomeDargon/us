define(function(require, exports, module){
	
	var $ = require('jquery');
    require('bootstrap');
    require('bootstrap.select');
    var List = require('../../util/list.js');
	
	exports.loadList = function(param){
		var list = new List();
		list.options = {
			template : "list-template",
			container : "list-data",
			url : $CONFIG.base_url + "/api/post/term/list",
			param : param
		};
		list.load();
	};
	exports.loadList();
	
	$('.selectpicker').selectpicker({
        'selectedText': 'cat'
    });
	
	$(".btn-search-submit").click(function(){
		var $form = $("#search-form");
		
		var postData = $form.serialize();
		exports.loadList(postData);
	});

    $("#list-data").on("click",".term-del",function(){
		var data = $.parseJSON($(this).attr("data"));
		var $this = $(this);
		layer.confirm('要删除该记录？', {
			btn: ['确认','取消'], //按钮
			shade: false //不显示遮罩
		}, function(){
			var posting = $.post($CONFIG.base_url + "/api/post/term/del", data);
			posting.done(function(data){
				layer.msg('删除成功', {icon:9});
				$this.parents("tr").remove();
			}).fail(function() {
				layer.msg('无权限或出现错误，请联系管理员', {icon:9});
			})
		}, function(){
		});

	});
	
	$("#dict-sort-submit").click(function(){
		var $form = $("#dict-form");
		if(!$form.valid()){
			return;
		}

		var postData = $form.serialize();
		var posting = $.post($CONFIG.base_url + "/api/post/term/sort", postData);
		posting.done(function(data) {
			$.jBox.tip('保存成功');
			window.location.reload();
		});
	});
});
