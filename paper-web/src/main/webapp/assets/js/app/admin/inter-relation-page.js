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
			url : $CONFIG.base_url + "/inter/find/relation/list",
			param : param
		};
		list.load();
	};
	exports.loadList({"userId":$CONFIG.uid});
	
	$('.selectpicker').selectpicker({
        'selectedText': 'cat'
    });
	var params = {
		uId :'',
		sex:'',
		email:'',
		qq:'',
		weChat  :'',
		iphone:'',
		companyName:'',
		companyPosition :'',
		insertTime:'',
		industry :'',
		name:'',
		uname:'',
		remark:'',
		init:function(params){
			this.setParams(params);
			this.setDnt();
		},
		setParams:function(params){
			this.uId=params.uId;
			this.sex=params.sex;
			this.email=params.email;
			this.qq=params.qq;
			this.weChat=params.weChat;
			this.iphone=params.iphone;
			this.companyName=params.companyName;
			this.companyPosition=params.companyPosition;
			this.insertTime=params.insertTime;
			this.industry=params.industry;
			this.name=params.name;
			this.uname=params.uname;
			this.remark=params.remark;
		},
		setDnt:function(){
			$("#uId ").val(this.uId);
			$("#sex").val(this.sex);
			$("#email").val(this.email);
			$("#qq").val(this.qq);
			$("#weChat  ").val(this.weChat);
			$("#iphone").val(this.iphone);
			$("#companyName").val(this.companyName);
			$("#companyPosition ").val(this.companyPosition);
			$("#insertTime").val(this.insertTime);
			$("#industry ").val(this.industry);
			$("#username").val(this.name);
			$("#userEnName").val(this.uname);
			$("#remark").val(this.remark);

		}
	}
	$(".btn-search-submit").click(function(){
		var $form = $("#search-form");
		
		var postData = $form.serialize();
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
		var posting = $.post($CONFIG.base_url + "/api/post/term/sort", postData);
		posting.done(function(data) {
			$.jBox.tip('保存成功');
			window.location.reload();
		});
	});
});
