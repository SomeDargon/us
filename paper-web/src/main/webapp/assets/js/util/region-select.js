/*!
 * Officec v0.0.1
 * Author: thon.ju
 * Date: 2014-02-19 13:09
 * Description: display office data
 */

define(function(require, exports, module){
	
	var $ = require('jquery');
	var template = require('template');
	require('jquery.jstree');
	
	module.exports = Region;
	function Region(){
		this.options = [];
	};
	
	/*==加载数据=======================================================================*/
	// type 样式类型
	// selected 已选择项
	// callback 回调函数
	Region.prototype.load = function load(type,selected,callback) {
	
		//定义模板
		var source = '';
		
		var dataArray=[];
		var getting = $.get($CONFIG.base_url+"/api/region/list");
		getting.done(function(data) {
			$.each(data, function( key, value ) {
				value.state= {opened:true};

				value.icon = "fa fa-sitemap";
				if(value.data.grade == '3'){
					value.icon = "fa fa-home";
				}else if(value.data.grade == '4'){
					value.icon = "fa fa-road";
				}else if(value.data.grade == '5'){
					value.icon = "fa fa-circle";
				}
				dataArray.push(value);
			});
			
			var render = template.compile(source);
			var html = render(data);
			$('body').append('<div id="office-select-container"></div>');
			$('#office-select-container').empty().append(html);
			$('#office-select-modal').modal();
			
			if(type == 'checkbox'){
				$('#office-select-content').jstree({'plugins':["wholerow","checkbox"], 'checkbox':{'three_state': false},'core' : {
					'data' : dataArray
				}}).on("open_node.jstree", function (event, data) {
		            $(this).jstree("open_all");
		        });
			}else{
				$('#office-select-content').jstree({'core' : {
                	"multiple" : false,
					'data' : dataArray
				}}).on("open_node.jstree", function (event, data) {
		            $(this).jstree("open_all");
		        });
			}
			
			if(selected != null && selected.length > 0){
				$('#office-select-content').jstree(true).select_node(selected.split(","));
			}
			
			$('#office-select-content').on("changed.jstree", function (e, data) {
				var i, j, r = [];
				
				// 如果是type为checkbox,则选中父级
				if(type=='checkbox'){
					for (i = 0, j = data.selected.length; i < j; i++) {
						$('#office-select-content').jstree(true).select_node(data.instance.get_node(data.selected[i]).parents);
					}
				}
				
				for (i = 0, j = data.selected.length; i < j; i++) {
					r.push(data.instance.get_node(data.selected[i]).text);
				}
				if(typeof(callback) == "function"){
					callback(data.selected, r.join(', '),data.node.data.grade);
				}
			});
		});
	};
	
});
