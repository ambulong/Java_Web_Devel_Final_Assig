$(function () {
    $.extend({
        alertInfo: function (msg) {
            $("#bottom-alert .msg").html(msg);
            if ($("#bottom-alert").hasClass("hidden")) {
                $("#bottom-alert").removeClass("hidden")
            }
        }
    });

    $.extend({
        init: function () {
            var token = $.getToken();
            $.cookie("token", token);
            if ($.getUid() == 0) {
                $("#header .header-user").addClass("hidden");
                $.each($(".for-admin"), function (index, item) {
                    if (!$(item).hasClass("hidden")) {
                        $(item).addClass("hidden");
                    }
                });
                $.each($(".for-user"), function (index, item) {
                    if (!$(item).hasClass("hidden")) {
                        $(item).addClass("hidden");
                    }
                });
            } else {
                $("#header .header-guest").addClass("hidden");
                $.each($(".for-user"), function (index, item) {
                    if ($(item).hasClass("hidden")) {
                        $(item).removeClass("hidden");
                    }
                });
                if ($.getFlag() == 1) {
                    $.each($(".for-admin"), function (index, item) {
                        if ($(item).hasClass("hidden")) {
                            $(item).removeClass("hidden");
                        }
                    });
                } else if ($.getFlag() == 2) {
                    $.each($(".for-admin"), function (index, item) {
                        if (!$(item).hasClass("hidden")) {
                            $(item).addClass("hidden");
                        }
                    });
                }
            }
        }
    });

    $.extend({
        getToken: function () {
            var token = "";
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=getToken&html=1&random=" + Math.random()
            }).done(function (json) {
                if (json.status == 1) {
                    token = json.data[0].token;
                }
            });
            return token;
        }
    });

    $.extend({
        getUid: function () {
            var uid = "";
            var token = $.cookie("token");
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=getUid&html=1&random=" + Math.random(),
                data: {
                    "token": token
                }
            }).done(function (json) {
                if (json.status == 1) {
                    uid = json.data[0].id;
                }
                if (json.status == -1)
                    uid = 0;
            });
            return uid;
        }
    });

    $.extend({
        getFlag: function () {
            var flag = "";
            var token = $.cookie("token");
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=getFlag&html=1&random=" + Math.random(),
                data: {
                    "token": token
                }
            }).done(function (json) {
                if (json.status == 1) {
                    flag = json.data[0].flag;
                }
            });
            return flag;
        }
    });

    $.extend({
        getUserInfo: function () {
            var list = {};
            var token = $.cookie("token");
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=getUserInfo&html=1&random=" + Math.random(),
                data: {
                    "token": token
                }
            }).done(function (json) {
                if (json.status == 1) {
                    list = json.data[0];
                }
                if (json.status == -1)
                    $.alertInfo(json.msg);
            });
            return list;
        }
    });

    $.extend({
        register: function (usr, pwd, age, head, gender) {
            var token = $.cookie("token");
            var status = 0;
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=register&html=1&random=" + Math.random(),
                data: {
                    "token": token,
                    "username": usr,
                    "password": pwd,
                    "age": age,
                    "head": head,
                    "gender": gender
                }
            }).done(function (json) {
                status = json.status;
                if (status == -1)
                    $.alertInfo(json.msg);
            });
            return status;
        }
    });

    $.extend({
        login: function (username, password) {
            var status = false;
            var token = $.cookie("token");
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=login&html=1&random=" + Math.random(),
                data: {
                    "username": username,
                    "password": password,
                    "token": token
                }
            }).done(function (json) {
                if (json.status == 1) {
                    status = true;
                } else {
                    $.alertInfo(json.msg);
                }
            });
            return status;
        }
    });

    $.extend({
        logout: function () {
            var token = $.cookie("token");
            var status = -1;
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=logout&html=1&random=" + Math.random(),
                data: {
                    "token": token
                }
            }).done(function (json) {
                status = json.status;
                if (status == -1)
                    $.alertInfo(json.msg);
            });
            return status;
        }
    });

    $.extend({
        updatePassword: function (oldpassword, password) {
            var token = $.cookie("token");
            var status = 0;
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=updatePassword&html=1&random=" + Math.random(),
                data: {
                    "token": token,
                    "oldpassword": oldpassword,
                    "password": password
                }
            }).done(function (json) {
                status = json.status;
                if (status == -1)
                    $.alertInfo(json.msg);
            });
            return status;
        }
    });

    $.extend({
        updateProfile: function (age, head, gender) {
            var token = $.cookie("token");
            var status = 0;
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=updateProfile&html=1&random=" + Math.random(),
                data: {
                    "token": token,
                    "age": age,
                    "head": head,
                    "gender": gender
                }
            }).done(function (json) {
                status = json.status;
                if (status == -1)
                    $.alertInfo(json.msg);
            });
            return status;
        }
    });


    $.extend({
        getBoards: function () {
            var token = $.cookie("token");
            var list = {};
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=getBoards&html=1&random=" + Math.random(),
                data: {
                    "token": token
                }
            }).done(function (json) {
                if (json.status == 1)
                    list = json.data;
                else if (status == -1)
                    $.alertInfo(json.msg);

            });
            return list;
        }
    });

    $.extend({
        getTips: function (bid) {
            var token = $.cookie("token");
            var list = {};
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=getTips&html=1&random=" + Math.random(),
                data: {
                    "token": token,
                    "bid": bid
                }
            }).done(function (json) {
                if (json.status == 1)
                    list = json.data;
                else if (status == -1)
                    $.alertInfo(json.msg);

            });
            return list;
        }
    });

    $.extend({
        getTipDetail: function (id) {
            var token = $.cookie("token");
            var list = {};
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=getTipDetail&html=1&random=" + Math.random(),
                data: {
                    "token": token,
                    "id": id
                }
            }).done(function (json) {
                if (json.status == 1)
                    list = json.data;
                else if (status == -1)
                    $.alertInfo(json.msg);

            });
            return list;
        }
    });

    $.extend({
        getReplies: function (tid) {
            var token = $.cookie("token");
            var list = {};
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=getReplies&html=1&random=" + Math.random(),
                data: {
                    "token": token,
                    "tid": tid
                }
            }).done(function (json) {
                if (json.status == 1)
                    list = json.data;
                else if (status == -1)
                    $.alertInfo(json.msg);

            });
            return list;
        }
    });

    $.extend({
        addBoard: function (name, pid) {
            var token = $.cookie("token");
            var status = 0;
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=addBoard&html=1&random=" + Math.random(),
                data: {
                    "token": token,
                    "name": name,
                    "pid": pid
                }
            }).done(function (json) {
                status = json.status;
                if (status == -1)
                    $.alertInfo(json.msg);
            });
            return status;
        }
    });

    $.extend({
        updateBoard: function (id, name, pid) {
            var token = $.cookie("token");
            var status = 0;
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=updateBoard&html=1&random=" + Math.random(),
                data: {
                    "token": token,
                    "name": name,
                    "pid": pid,
                    "id": id
                }
            }).done(function (json) {
                status = json.status;
                if (status == -1)
                    $.alertInfo(json.msg);
            });
            return status;
        }
    });

    $.extend({
        deleteBoard: function (id) {
            var token = $.cookie("token");
            var status = 0;
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=deleteBoard&html=1&random=" + Math.random(),
                data: {
                    "token": token,
                    "id": id
                }
            }).done(function (json) {
                status = json.status;
                if (status == -1)
                    $.alertInfo(json.msg);
            });
            return status;
        }
    });

    $.extend({
        addTip: function (bid, title, content, realfile, makefile) {
            var token = $.cookie("token");
            var status = 0;
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=addTip&html=1&random=" + Math.random(),
                data: {
                    "token": token,
                    "bid": bid,
                    "title": title,
                    "content": content,
                    "realfile": realfile,
                    "makefile": makefile
                }
            }).done(function (json) {
                status = json.status;
                if (status == -1)
                    $.alertInfo(json.msg);
            });
            return status;
        }
    });

    $.extend({
        updateTip: function (id, bid, title, content, realfile, makefile) {
            var token = $.cookie("token");
            var status = 0;
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=updateTip&html=1&random=" + Math.random(),
                data: {
                    "token": token,
                    "id": id,
                    "bid": bid,
                    "title": title,
                    "content": content,
                    "realfile": realfile,
                    "makefile": makefile
                }
            }).done(function (json) {
                status = json.status;
                if (status == -1)
                    $.alertInfo(json.msg);
            });
            return status;
        }
    });

    $.extend({
        deleteTip: function (id) {
            var token = $.cookie("token");
            var status = 0;
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=deleteTip&html=1&random=" + Math.random(),
                data: {
                    "token": token,
                    "id": id
                }
            }).done(function (json) {
                status = json.status;
                if (status == -1)
                    $.alertInfo(json.msg);
            });
            return status;
        }
    });

    $.extend({
        addReply: function (tid, title, content, realfile, makefile) {
            var token = $.cookie("token");
            var status = 0;
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=addReply&html=1&random=" + Math.random(),
                data: {
                    "token": token,
                    "tid": tid,
                    "title": title,
                    "content": content,
                    "realfile": realfile,
                    "makefile": makefile
                }
            }).done(function (json) {
                status = json.status;
                if (status == -1)
                    $.alertInfo(json.msg);
            });
            return status;
        }
    });

    $.extend({
        deleteReply: function (id) {
            var token = $.cookie("token");
            var status = 0;
            $.ajax({
                type: "POST",
                async: false,
                dataType: "json",
                url: "api?m=deleteReply&html=1&random=" + Math.random(),
                data: {
                    "token": token,
                    "id": id
                }
            }).done(function (json) {
                status = json.status;
                if (status == -1)
                    $.alertInfo(json.msg);
            });
            return status;
        }
    });


    $.extend({
        hideAllBottomItem: function () {
            $.each($(".bottom-item"), function (index, item) {
                if (!$(item).hasClass("hidden")) {
                    $(item).addClass("hidden");
                }
            });
        }
    });
    $.extend({
        hideAllPage: function () {
            $.each($(".page"), function (index, item) {
                if (!$(item).hasClass("hidden")) {
                    $(item).addClass("hidden");
                }
            });
        }
    });
    $.extend({
        showLoginPanel: function () {
            $.hideAllBottomItem();
            $("#bottom-login").removeClass("hidden");
        }
    });
    $.extend({
        showRegisterPanel: function () {
            $.hideAllBottomItem();
            $("#bottom-register").removeClass("hidden");
        }
    });
    $.extend({
        showAddBoardPanel: function () {
            $.hideAllBottomItem();
            $("#bottom-addBoard").removeClass("hidden");
        }
    });
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
        showAddReplyPanel: function () {
            $.hideAllBottomItem();
            $("#bottom-addReply").removeClass("hidden");
        }
    });
});
