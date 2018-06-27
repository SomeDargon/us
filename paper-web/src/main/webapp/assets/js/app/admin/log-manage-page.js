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
            url : $CONFIG.base_url + "/api/user/log/list",
            param : param
        };
        list.load();
    };
    exports.loadList();


});
