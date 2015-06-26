/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    $.extend({
        showAddTipPanel: function () {
            $.hideAllBottomItem();
            $("#bottom-addTip").removeClass("hidden");
        }
    });
    $.extend({
        showEditTipPanel: function () {
            $.hideAllBottomItem();
            $("#bottom-editTip").removeClass("hidden");
        }
    });
    $.extend({
        loadTips: function (bid) {
            var tips = $.getTips(bid);
            var uid = $.getUid();
            var flag = $.getFlag();
            var html = '';
            $.each(tips, function (index, item) {
                var ext = '';
                if (item.uid == uid || flag == 1) {
                    ext = '<div class="tip-delete for-user" title="删除">删除</div><div class="tip-edit for-user" title="编辑">编辑</div>';
                }
                html = html + '<tr tid="' + item.id + '"><td>' + item.title + '</td><td>' + item.board + '</td><td>' + item.author + '</td><td>' + ext + '</td></tr>';
            });
            $("#tips table tbody").html(html);
        }
    });
    $("#addTip").live("click", function () {
        $("#inputAddTipTitle").val("");
        $("#inputAddTipContent").text("");
        $("#inputAddTipAttachmentFile").text("");
        $("#inputAddTipAttachment").text("");
        var json = $.getBoards();
        var html = '';
        $.each(json, function (index, item) {
            html = html + '<option value="' + item.id + '">' + item.name + '</option>';
        });
        $("#inputAddTipBoard").html(html);
        $.showAddTipPanel();
    });

    $("#inputAddTipAttachmentFile").change(function () {
        if ($(this).val() != "") {
            var token = $.cookie("token");
            var formData = new FormData();
            formData.append('attach', $('input#inputAddTipAttachmentFile')[0].files[0]);
            $.ajax({
                type: "POST",
                url: "./api?m=uploadAttachment&token=" + token + "&random=" + Math.random(),
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                success: function (json) {
                    if (json.status == 1) {
                        $("#inputAddTipAttachment").val(json.data[0].filename);
                        if (!$("#bottom-alert").hasClass("hidden")) {
                            $("#bottom-alert").addClass("hidden");
                        }
                    } else {
                        $.alertInfo(json.msg);
                        $('input#inputAddTipAttachmentFile').val("");
                    }
                }
            });
        }
    });

    $("#btnAddTip").live("click", function () {

        $(this).addClass("disabled");

        var title = $("#inputAddTipTitle").val();
        var bid = $("#inputAddTipBoard").val();
        var content = $("#inputAddTipContent").val();
        var realfile = $("#inputAddTipAttachmentFile").val();
        var attach = $("#inputAddTipAttachment").val();

        if (title == "") {
            $(this).removeClass("disabled");
            $.alertInfo("标题不许为空");
            return;
        }

        if (bid == "") {
            $(this).removeClass("disabled");
            $.alertInfo("板块不许为空");
            return;
        }

        if (content == "") {
            $(this).removeClass("disabled");
            $.alertInfo("内容不许为空");
            return;
        }

        if ($.addTip(bid, title, content, realfile, attach) == 1) {
            location.reload();
        } else {
            $(this).removeClass("disabled");
        }
    });

    $("#board-all,.board-m,.sub-board-m").live("click", function () {
        var bid = $(this).attr("bid");
        $.loadTips(bid);
    });

    $(".tip-delete").live("click", function () {
        var tid = $(this).closest("tr").attr("tid");
        var text = "你确定要删除帖子 " + $(this).closest("tr").children("td").first().text() + " 吗？";
        if (!confirm(text))
            return;
        if ($.deleteTip(tid) == 1) {
            location.reload();
        }
    });
    
    $(".tip-edit").live("click", function () {
        var tid = $(this).closest("tr").attr("tid");
        var json = $.getTipDetail(tid, 0);
        $("#inputEditTipTitle").val(json.title);
        var findoption = "option[value='"+json.bid+"']";
        $("#inputEditTipBoard").find(findoption).attr("selected", true);
        $("#inputEditTipContent").val(json.content);
        $("#inputEditTipAttachment").val(json.makefile);
        $("#inputEditTipID").val(json.id);
        $.showEditTipPanel();
    });
    
    $("#btnEditTip").live("click", function () {
        $(this).addClass("disabled");
        
        var id = $("#inputEditTipID").val();
        var title = $("#inputEditTipTitle").val();
        var bid = $("#inputEditTipBoard").val();
        var content = $("#inputEditTipContent").val();
        var realfile = $("#inputEditTipAttachmentFile").val();
        var attach = $("#inputEditTipAttachment").val();

        if (title == "") {
            $(this).removeClass("disabled");
            $.alertInfo("标题不许为空");
            return;
        }

        if (bid == "") {
            $(this).removeClass("disabled");
            $.alertInfo("板块不许为空");
            return;
        }

        if (content == "") {
            $(this).removeClass("disabled");
            $.alertInfo("内容不许为空");
            return;
        }

        if ($.updateTip(id, bid, title, content, realfile, attach) == 1) {
            $.alertInfo("修改成功");
            return;
        } else {
            $(this).removeClass("disabled");
        }
    });
    
    $("#tips table tr td:first-child").live("click", function () {
        var tid = $(this).closest("tr").attr("tid");
        var json = $.getTipDetail(tid);
        if(typeof json.id == "undefined"){
            $.alertInfo("载入失败");
            return;
        }
            
        var content = json.content+"";
        content = content.replace(/\n/g,'<br />');
        if(json.realfile != ""){
            var filename = json.realfile.substring(json.realfile.lastIndexOf("\\")+1);
            content = content + '<br><br><a href="./upload/attachments/'+json.makefile+'">'+filename+'</a>';
        }
        $.hideAllPage();
        $("#tipdetail").html("");
        var tiphtml = '<div class="tip"><div class="tip-id hidden">'+json.id+'</div><div class="tip-title"><h1>'+json.title+'</h1></div><div class="tip-board"><span class="label label-info">'+json.board+'</span></div><div class="for-user tip-reply">回复</div><br><hr /><div class="tip-author"><img src="./upload/images/'+json.authorhead+'" /><span>'+json.author+'</span></div><div class="tip-pubtime">'+json.pubtime+'</div><div class="tip-content">'+content+'</div></div>';
        $("#tipdetail").append(tiphtml);
        $.loadReplies(tid);
        $("#tipdetail").removeClass("hidden");
        
    });

    $.loadTips(0);
});