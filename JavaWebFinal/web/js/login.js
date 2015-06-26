/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $.extend({
        showLoginPanel: function () {
            $.hideAllBottomItem();
            $("#bottom-login").removeClass("hidden");
        }
    });
   
    $(".icon-login").live("click", function () {
        $.showLoginPanel();
    });
    $("#btnLogin").live("click", function () {
        var username = $("#inputLoginUsername").val();
        var password = $("#inputLoginPassword").val();
        if ($.login(username, password)) {
            location.reload();
        }
    });

    
});
