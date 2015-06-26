/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {
    $(".board-m, .sub-board-m").live({
        mouseenter: function () {
            $(this).addClass("board-hover");
            if ($(".sub-board-" + $(this).attr('bid')).length > 0) {
                $(".sub-board").addClass("hidden");
                if ($(".sub-board-" + $(this).attr('bid')).hasClass("hidden")) {
                    $(".sub-board-" + $(this).attr('bid')).removeClass("hidden");
                }
            }

        },
        mouseleave: function () {
            $(this).removeClass("board-hover");
        }
    });

    $.extend({
        loadBoard: function () {
            var json = $.getBoards();
            $.each(json, function (index, item) {
                if (item.pid == 0) {
                    var html = '<div bid="' + item.id + '" class="board-item board-m" title="' + item.name + '"><span>' + item.name + '</span><div class="board-m-delete for-admin" title="删除">x</div></div>';
                    $("#board-add").before(html);
                } else {
                    var html = '<div bid="' + item.id + '" class="sub-board-item sub-board-m" title="' + item.name + '"><span>' + item.name + '</span><div class="sub-board-m-delete for-admin" title="删除">x</div></div>';
                    if (!($(".sub-board-" + item.pid).length > 0)) {
                        var subhtml = '<div pid="' + item.pid + '" class="sub-board sub-board-' + item.pid + ' hidden"></div>';
                        $("#sub-boards").append(subhtml);
                    }
                    $(".sub-board-" + item.pid).append(html);
                }
            });
        }
    });
    $.extend({
        showAddBoardPanel: function () {
            $.hideAllBottomItem();
            $("#bottom-addBoard").removeClass("hidden");
        }
    });

    $("#board-add").live("click", function () {
        //var pid = 0;
        //$("#pidAddBoardInput").val(pid);
        var json = $.getBoards();
        var html = '<option value="0">无</option>';
        $.each(json, function (index, item) {
            if (item.pid == 0)
                html = html + '<option value="' + item.id + '">' + item.name + '</option>';
        });
        $("#pidAddBoardInput").html(html);
        $.showAddBoardPanel();
    });

    $("#btnAddBoard").live("click", function () {
        var pid = $("#pidAddBoardInput").val();
        var name = $("#nameAddBoardInput").val();
        if ($.addBoard(name, pid) == 1) {
            location.reload();
        }
    });

    $(".board-m-delete").live("click", function () {
        var bid = $(this).closest(".board-m").attr("bid");
        var text = "你确定要删除板块 " + $(this).closest(".board-m").attr("title") + " 吗？";
        if (!confirm(text))
            return;
        if ($.deleteBoard(bid) == 1) {
            location.reload();
        }
    });

    $(".sub-board-m-delete").live("click", function () {
        var bid = $(this).closest(".sub-board-m").attr("bid");
        var text = "你确定要删除板块 " + $(this).closest(".sub-board-m").attr("title") + " 吗？";
        if (!confirm(text))
            return;
        if ($.deleteBoard(bid) == 1) {
            location.reload();
        }
    });

    $.loadBoard();
});