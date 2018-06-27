define(function(require, exports, module){
	
	var $ = require('jquery');
    require('bootstrap');
    require('bootstrap.select');
	require('bootstrap.datetimepicker');
	require('jquery.timeago')
    var List = require('../../util/list.js');
	$(".timeago").timeago();
	exports.loadList = function(param){
		var list = new List();
		list.options = {
			template : "list-template",
			container : "list-data",
			url : $CONFIG.base_url + "/api/task/list",
			param : param
		};
		list.load();
	};

	exports.loadList({"nextPeople.id":$CONFIG.uid,"status":2});
	$('.form_datetime').datetimepicker({
		language:  'zh',
		minView:'month'
	});
	$('.selectpicker').selectpicker({
        'selectedText': 'cat'
    });

	$(".btn-search-submit").click(function(){
		var $form = $("#search-form");

		var postData =  "nextPeople.id="+$CONFIG.uid+"&status=2&";
		postData	+= $form.serialize();

		exports.loadList(postData);
	});
	$("#list-data").on("click",".btn-view", function(){
		var data = $.parseJSON($(this).attr("data"));
		params.init(data.row);
	})
    $("#list-data").on("click",".relation-del",function(){
		var data = $.parseJSON($(this).attr("data"));
		console.log(data)
		var $this = $(this);
		layer.confirm('要删除该记录？', {
			btn: ['确认','取消'], //按钮
			shade: false //不显示遮罩
		}, function(){
			var posting = $.post($CONFIG.base_url + "/inter/relation/del", data);
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

	});
});
