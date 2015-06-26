/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {

    $.extend({
        showAddReplyPanel: function () {
            $.hideAllBottomItem();
            $("#btnAddReply").removeClass("disabled");
            $("#bottom-addReply").removeClass("hidden");
        }
    });

    $.extend({
        loadReplies: function (tid) {
            var json = $.getReplies(tid);
            $(".reply").remove();
            var html = '';
            $.each(json, function (index, item) {
                var content = item.content;
                content = content.replace(/\n/g,'<br />');
                if (item.realfile != "") {
                    var filename = item.realfile.substring(item.realfile.lastIndexOf("\\") + 1);
                    content = content + '<br><br><a href="./upload/attachments/' + item.makefile + '">' + filename + '</a>';
                }
                html = html + '<div class="reply"><div class="reply-title"><h3>' + item.title + '</h3><div rid="'+item.id+'" class="relpy-delete for-admin pull-right hidden">删除</div></div><hr /><div class="reply-author"><img src="./upload/images/' + item.authorhead + '" /><span>' + item.author + '</span></div><div class="reply-pubtime">' + item.pubtime + '</div><div class="reply-content">' + content + '</div></div>';
            });
            $("#tipdetail").append(html);
        }
    });

    $(".tip-reply").live("click", function () {
        $("#bottom-addReply .title span").text($(".tip-title h1").text());
        $("#inputAddReplyTitle").val("");
        $("#inputAddReplyContent").val("");
        $("#inputAddReplyAttach").val("");
        $("#inputAddReplyAttachment").val("");
        $("#inputAddReplyAttachmentInfo").text("无附件");
        $.showAddReplyPanel();
    });

    $("#inputAddReplyAttach").change(function () {
        if ($(this).val() != "") {
            var token = $.cookie("token");
            var formData = new FormData();
            formData.append('attach', $('input#inputAddReplyAttach')[0].files[0]);
            $.ajax({
                type: "POST",
                url: "./api?m=uploadAttachment&token=" + token + "&random=" + Math.random(),
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                success: function (json) {
                    if (json.status == 1) {
                        $("#inputAddReplyAttachment").val(json.data[0].filename);
                        var file = $('input#inputAddReplyAttach').val();
                        $("#inputAddReplyAttachmentInfo").text(file.substring(file.lastIndexOf("\\") + 1));
                        if (!$("#bottom-alert").hasClass("hidden")) {
                            $("#bottom-alert").addClass("hidden");
                        }
                    } else {
                        $.alertInfo(json.msg);
                        $('input#inputAddReplyAttach').val("");
                    }
                }
            });
        }
    });

    $("#btnAddReply").live("click", function () {
        $(this).addClass("disabled");

        var tid = $("#tipdetail .tip-id").text();
        var title = $("#inputAddReplyTitle").val();
        var content = $("#inputAddReplyContent").val();
        var realfile = $("#inputAddReplyAttach").val();
        var attach = $("#inputAddReplyAttachment").val();

        if (title == "") {
            $(this).removeClass("disabled");
            $.alertInfo("标题不许为空");
            return;
        }

        if (content == "") {
            $(this).removeClass("disabled");
            $.alertInfo("内容不许为空");
            return;
        }

        if ($.addReply(tid, title, content, realfile, attach) == 1) {
            $("#bottom-addReply").addClass("hidden");
            $.loadReplies(tid);
        } else {
            $(this).removeClass("disabled");
        }
    });
    
    $(".relpy-delete").live("click", function () {
        var rid = $(this).attr("rid");
        var tid = $("#tipdetail .tip-id").text();
        if($.deleteReply(rid) == 1){
            $.alertInfo("删除成功");
            $.loadReplies(tid);
            return;
        }
    });
});