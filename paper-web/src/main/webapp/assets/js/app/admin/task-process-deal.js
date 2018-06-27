define(function(require, exports, module){

    var $ = require('jquery');
    var List = require('../../util/list.js');
    require('jquery.validate');
    var details = {
        h_div: $("<div class='col-lg-12 col-log-12-padding'>"),
        box :  $("<div class='ibox ibox-bottom float-e-margins border-bottom'>"),
        box_title: $("<div class='ibox-title'>"),
        box_title_h5: $("<h5 id='h-title'>taskName</h5>"),
        box_term :  $("<div class='ibox-tools'></div>"),
        box_content: $("<div class='ibox-content' style='display: block;'>"),
        box_content_row: $("<div class='row m-b-lg'>"),
        i:0,
        id:$("#p_id").val(),
        init:function()  /**初始化**/{
            this.queryInit(this.id);
            this.click_coll('collapse-link0');
        },
        click_coll:function(param) /**折叠方法**/{
            $('.'+param).on('click', function () {
                var ibox = $(this).closest('div.ibox');
                var button = $(this).find('i');
                var content = ibox.find('div.ibox-content');
                content.slideToggle(200);
                button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
                ibox.toggleClass('').toggleClass('border-bottom');
                setTimeout(function () {
                    ibox.resize();
                    ibox.find('[id^=map-]').resize();
                }, 50);
            })
        },
        queryInit:function(param)/**初始化流程框**/{
            var my = this;
            $.ajax({
                contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                type: "post",
                url: $CONFIG.base_url + "/api/task/find/id",
                data: {id: param},
                dataType: "json",
                async: false,
                success: function (datas) {
                    var data = datas.data;
                    $.each(data.flowInfoList, function() {
                        my.i++;
                        if(this.type == 0 ){
                            my.initIndex(this);
                        }else if(this.type ==1 ){
                            my.detailsInit('collapse-link'+my.i,'操作时间:'+my.numToDate(data.insertTime)+'&nbsp;操作人：'+data.initiator.name+'&nbsp;环节：审核',this);
                        }
                        my.click_coll('collapse-link'+my.i);
                    })
                    my.i++;
                    if(datas.data.status == 0 ){
                        my.deInit('collapse-link'+my.i, '审核');
                    }else if(datas.data.status == 1 ){
                        my.turnInit('collapse-link'+my.i, '重新发起');
                    }
                    my.click_coll('collapse-link'+my.i);
                    var html ='操作时间:'+my.numToDate(data.insertTime)+'&nbsp;'+data.initiator.name+'&nbsp;环节:发起';
                    $("#h-title").html(html);
                }
            })
        },
        initIndex:function(param)/**添加第一次标题**/{
            var pa = '公事';
            if(param.proType==1){
                pa = '私事'
            }
            $("#dealPeople").html(param.dealPeople.name);
            $("#insertTime").html(this.numToDate(param.insertTime));
            $("#type").html(pa);
            $("#x1").html(param.x1);
        },
        detailsInit:function(param, title, params)/**加载回显审核**/{
            this.groupInit(param, title);
            var pa = '驳回';
            if(params.proType!=null){
               pa='通过'
            }
            var col_12 = $('<div class="col-lg-12">');
            var table_1 = $('<table class="table table-bordered">');
            var tr_1 = $('<tr>');
            var tr_2 = $('<tr>');
            var td_1 = $('<td class="table-center">处理人</td>');
            var td_2= $('<td id="dealPeople">'+params.dealPeople.name+'</td>');
            var td_3 = $('<td class="table-center">发起时间</td>');
            var td_4 = $('<td id="insertTime">'+this.numToDate(params.insertTime)+'</td>');
            var td_5 = $('<td class="table-center">类型</td>');
            var td_6 = $('<td id="type">'+pa+'</td>');
            var td1_1 =  $('<td class="table-center">意见</td>');
            var td1_2 = $('<td colspan="5" id="x1">'+params.x1+'</td>');
            tr_1.append(td_1).append(td_2).append(td_3).append(td_4).append(td_5).append(td_6);
            tr_2.append(td1_1).append(td1_2);
            table_1.append(tr_1).append(tr_2);
            col_12.append(table_1);
            this.box_content_row.append(col_12)
            this.box_content.append(this.box_content_row);
            this.box.append(this.box_content);

        },
        deInit:function(param, title, params)/**加载输入审核内容框**/{
            this.groupInit(param, title);
            var id_1 = $(' <input type="hidden" value="'+this.id+'" name="instance.id" id="instance.id"/>');
            var form_1 = $('<form id="pro-form" style="background-color: rgba(0, 0, 0, 0);" class="form-horizontal" style="padding: 15px;"></form>');
            var dashed = $('<div class="hr-line-dashed"></div>');
            var group =$('<div class="form-group">');
            var label = $('<label class="col-sm-1 control-label">是否通过</label>');
            var sm_10 = $('<div class="col-sm-10"></div>');
            var option_1 = $('<label class="label_1"><input type="radio" name="x1" class="mgr" value="0" checked/>同意</label>');
            var option_2 = $('<label class="label_1"><input type="radio" name="x1" class="mgr" value="1"/>驳回</label>');
            var label_1 = $('<label class="col-sm-1 control-label">意见</label>');
            var sm_10_1 = $('<div class="col-sm-12"></div>');
            var textarea_1 = $('<textarea class="form-control" name="x2"  id="x2" rows="3" style="width: 80%"></textarea>');
            var sm_10_1 = $('<div class="col-sm-12"></div>');
            var btn_sub = '<button  style="margin-left: 180px;margin-top: 20px" type="button" id="pro_submit" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off">提交</button>';
            var btn_esc = '<button style="margin-left: 20px;margin-top: 20px" type="button" id="pro_cancel" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off">取消</button>';
            sm_10_1.append(label_1).append(textarea_1).append(btn_sub).append(btn_esc);
            sm_10.append(option_1).append(option_2);
            group.append(label).append(sm_10).append(sm_10_1);
            form_1.append(id_1).append(group);
            this.box_content.append(form_1);
            this.box_content.append(dashed);
            this.box_content.append(this.box_content_row);
            this.box.append(this.box_content);

            $("#pro_submit").on("click", function(){
                var $form = $("#pro-form");
                if(!$form.valid()){
                    return;
                }
                var postData = $form.serialize();
                var posting = $.post($CONFIG.base_url + "/api/task/pro/save", postData);
                posting.done(function(data) {
                    layer.msg('保存成功', {
                        time: 4000 //4s后自动关闭
                    });
                    window.location.href = $CONFIG.base_url + "/admin/task/manage";
                });
            })
        },
        groupInit:function(param, title)/**加载头部**/{
            this.setBox();
            var html = "<a class='className'><i class='fa fa-chevron-up'></i></a>";
            this.box_title_h5.html(this.box_title_h5.html().replace(/taskName/, title));
            html = html.replace(/className/, param);
            this.box_term.append(html);
            this.box_title.append(this.box_title_h5);
            this.box_title.append(this.box_term);
            this.box.append(this.box_title);
            $(".col-log-12-padding").append(this.box);
        },
        setBox: function()/**重新赋值**/{
            this.clearBox();
            this.h_div= $("<div class='col-lg-12 col-log-12-padding'>");
            this.box =  $("<div class='ibox ibox-bottom float-e-margins border-bottom'>");
            this.box_title= $("<div class='ibox-title'>");
            this.box_title_h5= $("<h5 id='h-title'>taskName</h5>");
            this.box_term =  $("<div class='ibox-tools'></div>");
            this.box_content= $("<div class='ibox-content' style='display= block;'>");
            this.box_content_row= $("<div class='row m-b-lg'>");
        },
        clearBox:function()/**清空绑定的div**/{
            this.h_div= '';
            this.box =  '';
            this.box_title= '';
            this.box_title_h5= '';
            this.box_term =  '';
            this.box_content= '';
            this.box_content_row= '';
        },
        format:function(format, param){
            var o = {
                "M+" : param.getMonth()+1, //month
                "d+" : param.getDate(), //day
                "h+" : param.getHours(), //hour
                "m+" : param.getMinutes(), //minute
                "s+" : param.getSeconds(), //second
                "q+" : Math.floor((param.getMonth()+3)/3), //quarter
                "S" : param.getMilliseconds() //millisecond
            }

            if(/(y+)/.test(format)) {
                format = format.replace(RegExp.$1, (param.getFullYear()+"").substr(4 - RegExp.$1.length));
            }

            for(var k in o) {
                if(new RegExp("("+ k +")").test(format)) {
                    format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
                }
            }
            return format;
        },
        numToDate:function(param){
            var date=new Date(param);
            return this.format("yyyy-MM-dd hh:mm:ss",date)
        },
        turnInit:function(param, title)/**加载驳回内容框**/{
            this.groupInit(param, title);
            var id_1 = $(' <input type="hidden" value="'+this.id+'" name="instance.id" id="instance.id"/>');
            var form_1 = $('<form id="pro-form" style="background-color: rgba(0, 0, 0, 0);" class="form-horizontal" style="padding: 15px;"></form>');
            var dashed = $('<div class="hr-line-dashed"></div>');
            var group =$('<div class="form-group">');
            var label_1 = $('<label class="col-sm-1 control-label">意见</label>');
            var sm_10_1 = $('<div class="col-sm-12"></div>');
            var textarea_1 = $('<textarea class="form-control" name="x1"  id="x1" rows="3" style="width: 80%"></textarea>');
            var sm_10_1 = $('<div class="col-sm-12"></div>');
            var btn_sub = '<button  style="margin-left: 180px;margin-top: 20px" type="button" id="pro_submit" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off">提交</button>';
            var btn_esc = '<button style="margin-left: 20px;margin-top: 20px" type="button" id="pro_cancel" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off">取消</button>';
            sm_10_1.append(label_1).append(textarea_1).append(btn_sub).append(btn_esc);
            group.append(sm_10_1);
            form_1.append(id_1).append(group);
            this.box_content.append(form_1);
            this.box_content.append(dashed);
            this.box_content.append(this.box_content_row);
            this.box.append(this.box_content);

            $("#pro_submit").on("click", function(){
                var $form = $("#pro-form");
                if(!$form.valid()){
                    return;
                }
                var postData = $form.serialize();
                var posting = $.post($CONFIG.base_url + "/api/task/return/save", postData);
                posting.done(function(data) {
                    layer.msg('保存成功', {
                        time: 4000 //4s后自动关闭
                    });
                    window.location.href = $CONFIG.base_url + "/admin/task/manage";
                });
            })
        },

    }
    details.init();

});