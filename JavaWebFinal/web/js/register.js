/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $(".icon-register").live("click", function () {
        $.showRegisterPanel();
    });
    
    $("#btnRegister").live("click", function () {
        var username = $("#inputRegUsername").val();
        var password = $("#inputRegPassword").val();
        var repeatPassword = $("#inputRegRepeatPassword").val();
        var age = $("#inputRegAge").val();
        var gender = $("#inputRegGender").val();
        var head = $("#inputRegHead").val();
        
        if(password != repeatPassword){
            $.alertInfo("两次输入密码不同");
            return;
        }
        
        if(head == ""){
            $.alertInfo("请选择头像");
            return;
        }
        
        $status = $.register(username, password, age, head, gender);
        if ($status == 1) {
            $.hideAllBottomItem();
            $.showLoginPanel();
            $.alertInfo("注册成功，请登录");
        }
    });
    
    $("#inputRegHeadFile").change(function () {
        if ($(this).val() != "") {
            var token = $.cookie("token");
            var formData = new FormData();
            formData.append('image', $('input#inputRegHeadFile')[0].files[0]);
            $.ajax({
                type: "POST",
                url: "./api?m=uploadImg&token="+token+"&random=" + Math.random(),
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                success: function (json) {
                    if(json.status == 1){
                        $("#inputRegHead").val(json.data[0].filename);
                        if(!$("#bottom-alert").hasClass("hidden")){
                            $("#bottom-alert").addClass("hidden");
                        }
                    }else{
                        $.alertInfo(json.msg);
                        $('input#inputRegHeadFile').val("");
                    }
                }
            });
        }
    });
    
});
