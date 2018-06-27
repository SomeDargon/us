define(function(require, exports, module){
	
	var $ = require('jquery');

    require('bootstrap');
    require('bootstrap.select');
    require('jquery.icheck');
    var List = require('../../util/list.js');
	
	exports.loadList = function(param){
		var list = new List();
		list.options = {
			template : "list-template",
			container : "list-data",
			url : $CONFIG.base_url + "/api/post/list?s=10",
			param : param
		};
		list.load(function(){
			$(".title_th_desc").each(function(){
				$(this).on("click", function(){
					exports.loadList({"orderName":$(this).attr("name"),"order":"desc"});
				})
			})
			$(".title_th_asc").each(function(){
				$(this).on("click", function(){
					exports.loadList({"orderName":$(this).attr("name"),"order":"asc"});
				})
			})
            $('input').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%' // optional
            });
        });
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

    $("#list-data").on("click",".post-del",function(){
		var data = $.parseJSON($(this).attr("data"));
		var $this = $(this);
		layer.confirm('要删除该记录？', {
			btn: ['确认','取消'], //按钮
			shade: false //不显示遮罩
		}, function(){
			var posting = $.post($CONFIG.base_url + "/api/post/del", data);
			posting.done(function(data){
				layer.msg('删除成功', {icon:9});
				$this.parents("tr").remove();
			}).fail(function() {
				layer.msg('无权限或出现错误，请联系管理员', {icon:9});
			})
		}, function(){
		});
	});
});
