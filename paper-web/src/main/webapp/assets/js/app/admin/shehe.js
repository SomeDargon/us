define(function(require, exports, module) {
    exports.init = function(param, title, my){
        debugger
        my.groupInit(param, title);
        var id_1 = $(' <input type="hidden" value="'+my.id+'" name="instance.id" id="instance.id"/>');
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
        my.box_content.append(form_1);
        my.box_content.append(dashed);
        my.box_content.append(my.box_content_row);
        my.box.append(my.box_content);

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
    }
});