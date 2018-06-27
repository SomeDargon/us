define(function(require, exports, module){
	
	var $ = require('jquery');
    require('bootstrap');
    require('bootstrap.select');
	require('bootstrap.datetimepicker');
	require('jquery.timeago');
	//查找
    var List = require('../../util/list.js');
	$(".timeago").timeago();
	exports.loadList = function(param){
		var list = new List();
		list.options = {
			template : "list-template",
			container : "list-data",
			url : $CONFIG.base_url + "/schedule/find/list",
			param : param
		};
		list.load();
	};

	exports.loadList({"userId":$CONFIG.uid});
	$('.form_datetime').datetimepicker({
		language:  'zh',
		minView:'month'
	});
	$('.selectpicker').selectpicker({
        'selectedText': 'cat'
    });

	$(".btn-search-submit").click(function(){

		var $form = $("#search-form");
		
		var postData = $form.serialize();
		exports.loadList(postData);
	});
});
