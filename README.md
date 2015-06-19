#企业BBS系统

JAVA WEB开发课程设计

##设计

###地址
 - ./index.html
 - ./api?m=getToken
 - ./api?m=login *登录*
  - req: username, password
  - res(json): status(0,未登录 1.执行成功 -1.执行失败), msg, data(token)
 - ./api?m=logout *退出*
  - req: token
  - res(json): status, msg, data
 - ./api?m=getBoards *获取板块列表(需登录)*
 - ./api?m=getUid *获取当前用户ID*(需登录)
 - ./api?m=getUserInfo *获取当前用户资料(需登录)*
  - req:token
 - ./api?m=getTips *获取帖子列表(需登录)*
  - req:bid(default 0 get all)
 - ./api?m=getTipDetail *获取帖子详细内容(需登录)*
 - ./api?m=getReplies *获取回复内容(需登录)*
  - req: tid
 - ./api?m=register *添加用户*
 - ./api?m=updateProfile *更改资料(需登录)*
 - ./api?m=updatePassword *更改密码(需登录)*
 - ./api?m=addBoard *添加板块(需登录&管理)*
  - req: name, pid
 - ./api?m=updateBoard *更改板块(需登录&管理)*
  - req: id, name, pid
 - ./api?m=deleteBoard *删除板块(需登录&管理)*
 - ./api?m=addTip *添加帖子(需登录)*
  - req: bid，title，content, realfile, makefile
 - ./api?m=updateTip *更改帖子(需登录，防越权)*
  - req: id, bid，title，content, realfile, makefile
 - ./api?m=deleteTip *删除帖子(需登录，防越权)*
  - req: id
 - ./api?m=addReply *添加评论(需登录)*
  - req: tid，title，content, realfile, makefile
 - ./api?m=deleteReply *删除评论(需登录&管理)*
  - req: id
 - ./api?m=uploadAttachment *上传附件(需登录)*
 - ./api?m=uploadImg *上传图片*
 
###模块

 > 注：
 > 用户名、标题记得去除前后空格（trim()）。  
 > 字符串比较不区分大小写，防止用户名重复。  
 > 防止出现全是空格的字符串。

 - com/z/util/BConnectDB 数据库连接类
 - com/z/util/BSession 会话类
 - com/z/util/BRespJson 返回JSON类
 - com/z/util/BRouter 路径类
 - com/z/util/BConfig 配置类
 - com/z/action/BApi 入口（servlet）
 - com/z/module/Login 登录操作
 - com/z/module/Logout 登出
 - com/z/module/...
 - com/z/lib/BFunctions
 - com/z/lib/BPasswordHash
 - com/z/lib/BUserBean
  - chkName(name) boolean *校验用户名是否符合要求* 只允许英文字母和数字，长度大于3
  - chkPassword(pwd) boolean *校验密码是否符合要求* 长度大于6
  - validate() boolean *校验数据是否符合要求*
 - com/z/lib/BUser 用户类
  - isExistID(uid) boolean *用户ID是否存在*
  - isExistName(name) boolean *用户名是否存在*
  - add(BUserBean) boolean *添加用户*
  - chkLogin(name, pwd) boolean *校验用户名密码*
  - updatePassword(uid, pwd) boolean *更改密码*
  - updateProfile(BUserBean) boolean *更改资料*
  - isAdmin(uid) boolean *是否管理员*
  - getID(name) int *获取ID*
  - getDetail(uid) BUserBean *获取详细信息*
  - getHash(uid) String *获取密码*
  - getHead(uid) String *获取头像*
  - getUsername(uid) String *获取用户名*
 - com/z/lib/BBoardBean
 - com/z/lib/BBoard 板块类
  - isExistID(id) boolean *板块ID是否存在*
  - add(BBoardBean) boolean *添加板块*
  - getName(id) String *获取板块名*
  - getDetail(id) BBoardBean *获取板块信息*
  - getChildren(id) BBoardBean[] *获取子板块ID*
  - hasChildren(id) boolean *是否有子板块*
  - delete(id) boolean *删除板块*
  - update(BBoardBean) boolean *更新板块*
  - getBoardList() BBoardBean[] *获取全部板块*
 - com/z/lib/BTipBean
 - com/z/lib/BTip 帖子类
  - isExistID(id) boolean *帖子ID是否存在*
  - getTitle(id) String *获取标题*
  - getContent(id) String *获取内容*
  - getDetail(id) BTipBean *获取信息*
  - add(BTipBean) boolean *添加文章*
  - update(BTipBean) boolean *更新文章*
  - delete(id) boolean *删除帖子*
 - com/z/lib/BReplyBean
 - com/z/lib/BReply
  - isExistID(id) boolean *评论是否存在*
  - getTitle(id) String *获取标题*
  - getContent(id) String *获取内容*
  - getDetail(id) BReplyBean *获取信息*
  - delete(id) boolean *删除评论*
  - add(BReplyBean) boolean *添加评论*
  
