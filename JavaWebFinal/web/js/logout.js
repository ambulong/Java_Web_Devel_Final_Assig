/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $(".icon-logout").live("click", function () {
        $.logout();
        location.reload();
    });
});

