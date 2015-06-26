/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $.extend({
        profileChgPassword: function () {
            $("#inputProfileOldPassword").val("");
            $('.bs-getpassword-modal-sm').modal('show');
        }
    });

    $.extend({
        showProfilePanel: function () {
            $.hideAllBottomItem();
            var json = $.getUserInfo();
            $("#bottom-profile .profile-username p").text(json.username);
            $("#inputProfileAge").val(json.age);
            $("#inputProfileHead").val(json.head);
            if (json.gender == "男") {
                $("#inputProfileGender").find("option[value='0']").attr("selected", true);
            } else if (json.gender == "女") {
                $("#inputProfileGender").find("option[value='1']").attr("selected", true);
            } else {
                $("#inputProfileGender").find("option[value='2']").attr("selected", true);
            }
            $("#inputProfilePassword").val("");
            $("#inputProfileRepeatPassword").val("");
            $("#bottom-profile").removeClass("hidden");
        }
    });

    $(".icon-profile").live("click", function () {
        $.showProfilePanel();
    });

    $("#btnProfile").live("click", function () {
        var age = $("#inputProfileAge").val();
        var gender = $("#inputProfileGender").val();
        var head = $("#inputProfileHead").val();

        $status = $.updateProfile(age, head, gender);
        if ($status == 1) {
            $.alertInfo("基本资料修改成功");
        }

        var password = $("#inputProfilePassword").val();
        var repeatPassword = $("#inputProfileRepeatPassword").val();

        if (password != "") {
            if (password != repeatPassword) {
                $.alertInfo("两次输入密码不同");
                return;
            } else {
                $.profileChgPassword();
            }
        }
    });

    $("#btnProfileOldPassword").live("click", function () {
        var password = $("#inputProfilePassword").val();
        var repeatPassword = $("#inputProfileRepeatPassword").val();
        var oldPassword = $("#inputProfileOldPassword").val();

        if (password != "") {
            $('.bs-getpassword-modal-sm').modal('hide');
            if (password != repeatPassword) {
                $('.bs-getpassword-modal-sm').modal('hide');
                $.alertInfo("两次输入密码不同");
                return;
            } else if (oldPassword == "") {
                $('.bs-getpassword-modal-sm').modal('hide');
                $.alertInfo("原密码不许为空");
                return;
            } else {
                $('.bs-getpassword-modal-sm').modal('hide');
                $status = $.updatePassword(oldPassword, password);
                if ($status == 1) {
                    $.alertInfo("修改成功");
                }
            }
        }
    });
    
    $("#inputProfileHeadFile").change(function () {
        if ($(this).val() != "") {
            var token = $.cookie("token");
            var formData = new FormData();
            formData.append('image', $('input#inputProfileHeadFile')[0].files[0]);
            $.ajax({
                type: "POST",
                url: "./api?m=uploadImg&token="+token+"&random=" + Math.random(),
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                success: function (json) {
                    if(json.status == 1){
                        $("#inputProfileHead").val(json.data[0].filename);
                        if(!$("#bottom-alert").hasClass("hidden")){
                            $("#bottom-alert").addClass("hidden");
                        }
                    }else{
                        $.alertInfo(json.msg);
                        $('input#inputProfileHeadFile').val("");
                    }
                }
            });
        }
    });
});
