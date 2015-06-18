#企业BBS系统

JAVA WEB开发课程设计

##设计

###地址
 - ./index.html
 - ./api?m=login *登录*
  - req: username, password
  - res(json): status(0,未登录 1.执行成功 -1.执行失败), info, data(token)
 - ./api?m=logout *退出*
  - req: token
  - res(json): status, info, data
 - ./api?m=getBoards *获取板块列表*
 - ./api?m=getUid *获取当前用户ID*
 - ./api?m=getUserInfo *获取用户公开资料*
 - ./api?m=getTips *获取帖子列表*
 - ./api?m=getTipDetail *获取帖子详细内容*
 - ./api?m=getReplies *获取回复内容*
 - ./api?m=register *添加用户*
 - ./api?m=updateProfile *更改资料*
 - ./api?m=updatePassword *更改密码*
 - ./api?m=addBoard *添加板块*
 - ./api?m=updateBoard *更改板块*
 - ./api?m=deleteBoard *删除板块*
 - ./api?m=addTip *添加帖子*
 - ./api?m=updateTip *更改帖子*
 - ./api?m=deleteTip *删除帖子*
 - ./api?m=addReply *添加评论*
 - ./api?m=deleteReply *删除评论*
 
###模块

 > 注：
 > 用户名、标题记得去除前后空格（trim()）。  
 > 字符串比较不区分大小写，防止用户名重复。  
 > 防止出现全是空格的字符串。

 - com/z/util/BConnectDB 数据库连接类
 - com/z/util/BSession 会话类
 - com/z/util/BRouter 路径类
 - com/z/util/BConfig 配置类
 - com/z/action/BApi servlet
 - com/z/module/Login 登录操作
 - com/z/module/Logout 登出
 - com/z/module/...
 - com/z/lib/BFunctions
 - com/z/lib/BPasswordHash
 - com/z/lib/BUser 用户类
  - isExistID(uid) boolean *用户ID是否存在*
  - isExistName(name) boolean *用户名是否存在*
  - isAdmin(uid) boolean *是否管理员*
  - chkName(name) boolean *校验用户名是否符合要求* 只允许英文字母和数字，长度大于3
  - chkPassword(pwd) boolean *校验密码是否符合要求* 长度大于6
  - add(name, pwd, birth, avatar, gender) boolean *添加用户*
  - auth(name, pwd) boolean *校验用户名密码*
  - updatePassword(uid, pwd) boolean *更改密码*
  - updateProfile(birth, avatar, gender) boolean *更改资料*
  - getID(name) int *获取ID*
  - getDetail(uid) ArrayList *获取详细信息*
  - getPassword(uid) String *获取密码*
  - getAvatar(uid) String *获取头像*
  - getName(uid) String *获取用户名*
  - createHash(pwd) String *加密密码* 参考：http://drops.wooyun.org/papers/1066
  - validatePassword(pwd, hash) *比较密码和hash*
  
 - com/z/lib/BBoard 板块类
  - isExistID(id) boolean *板块ID是否存在*
  - isExistName(name) int 名称重复模块ID *板块名是否存在*
  - add(name, pid=0) boolean *添加板块*
  - getID(name) int *获取板块ID*
  - getName(id) String *获取板块名*
  - getChildren(id) int[] *获取子板块*
  - hasChildren(id) boolean *是否有子板块*
  - delete(id) boolean *删除板块*
  - update(id, name, pid=0) boolean *更新板块* 名称如果和本身重复则继续

- com/z/lib/BTip 帖子类
  - isExistID(id) boolean *帖子ID是否存在*
  - isExistTitle(title) int 标题重复帖子ID *标题是否存在*
  - add(title, content, uid, bid, realFile="", makeFile="") boolean *添加文章*
  - update(id, title, content, uid, bid, realFile="", makeFile="") boolean *更新文章* 标题如果和本身重复则继续
  - delete(id) boolean *删除帖子*
 
- com/z/lib/BReply
  - isExistID(id) boolean *评论是否存在*
  - delete(id) boolean *删除评论*
  - add(title, content, uid, tid, realFile="", mFile="") boolean *添加评论*
  
