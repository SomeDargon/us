define(function(require, exports, module){
	
	var $ = require('jquery');
    require('bootstrap');
	require('bootstrap.select');
    var List = require('../../util/list.js');
	
	$('.selectpicker').selectpicker({
        'selectedText': 'cat'
    });
    exports.loadList = function(param){
        var list = new List();
        list.options = {
            template : "list-template",//执行js的id
            container : "list-data",//存放的位置
            url : $CONFIG.base_url + "/api/user/list",//访问的数据
            param : param//参数
        };
        list.load();//执行
    };
    exports.loadList();//调用定义的js并传值

    $(".btn-search-submit").click(function(){
        var $form = $("#search-form");

        var postData = $form.serialize();
        exports.loadList(postData);
    });

    $("#list-data").on("click",".user-del",function(){
		var data = $.parseJSON($(this).attr("data"));
        var $this = $(this);
        layer.confirm('要删除该记录？', {
            btn: ['确认','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            var posting = $.post($CONFIG.base_url + "/api/user/del", data);
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
