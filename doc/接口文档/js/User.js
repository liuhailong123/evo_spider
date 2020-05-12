// ===================================================UserApi=================================================
/**
 * @apiDefine user 用户类接口
 */

/**
 * @api {get,post} /api/user/verificationCode 获取验证码
 * @apiName verificationCode
 * @apiGroup user
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} phone 手机号
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/user/verificationCode?phone=18710009588
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess {String} retCode 返回码 0-成功
 * @apiSuccess {String} retMsg 返回消息
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "retCode": "0",
        "retMsg": "短信已发送"
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */

/**
 * @api {get,post} /api/user/setMeallist 用户已订购产品列表
 * @apiName setMeallist
 * @apiGroup user
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} appId 应用id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/user/setMeallist?cardNo=4264678666&appId=1
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){String} retCode 返回码 1-成功；0-无数据
 * @apiSuccess (data){String} retMsg 返回消息
 * @apiSuccess (data){JsonArray} products 产品数组对象
 * @apiSuccess (products){String} code 产品编码
 * @apiSuccess (products){String} currentPrice 优惠价
 * @apiSuccess (products){String} info 产品信息
 * @apiSuccess (products){String} name 产品名称
 * @apiSuccess (products){String} originalPrice 原价
 * @apiSuccess (products){String} pictureUrl 产品海报
 * @apiSuccess (products){JSONArray} payTypes 支付方式数组对象
 * @apiSuccess (payTypes){String} name 支付方式名称
 * @apiSuccess (payTypes){Integer} payType 支付方式类型 1-微信；2-支付宝；3-银联；4-boss
 * @apiSuccess (products){JSONArray} serverInfos 服务数组对象
 * @apiSuccess (serverInfos){Integer} ruleCount 规则次数
 * @apiSuccess (serverInfos){String} ruleInfo 规则说明
 * @apiSuccess (serverInfos){String} ruleNmae 规则名称
 * @apiSuccess (serverInfos){String} ruleUnit 规则单位
 * @apiSuccess (serverInfos){String} serverCode 服务编码 10001-视频观看服务；10002-图书借阅服务
 * @apiSuccess (serverInfos){String} serverInfo 服务说明
 * @apiSuccess (serverInfos){String} serverName 服务名称
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "retCode": "1",
        "retMsg": "获取成功",
        "products": [
            {
                "code": "100007",
                "currentPrice": "10800",
                "id": "40288ae6680282200168029547060008",
                "info": "",
                "name": "亲多多包季108元",
                "originalPrice": "10800",
                "payTypes": [
                    {
                        "name": "新疆省网-boss",
                        "payType": 4
                    }
                ],
                "pictureUrl": "",
                "serverInfos": [
                    {
                        "ruleCount": 3,
                        "ruleInfo": "",
                        "ruleName": "包季",
                        "ruleUnit": "3",
                        "serverCode": "10001",
                        "serverInfo": "",
                        "serverName": "亲多多视频点播"
                    }
                ]
            }
        ]
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */


/**
 * @api {get,post} /api/user/findUserInfo 获取用户信息
 * @apiName findUserInfo
 * @apiGroup user
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} appId 应用id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/user/findUserInfo?cardNo=4264678666&appId=1
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){String} retCode 返回码 1-成功；0-无数据
 * @apiSuccess (data){String} retMsg 返回消息
 * @apiSuccess (data){JsonArray} products 产品数组对象
 * @apiSuccess (products){String} code 产品编码
 * @apiSuccess (products){String} currentPrice 优惠价
 * @apiSuccess (products){String} info 产品信息
 * @apiSuccess (products){String} name 产品名称
 * @apiSuccess (products){String} originalPrice 原价
 * @apiSuccess (products){String} pictureUrl 产品海报
 * @apiSuccess (products){JSONArray} payTypes 支付方式数组对象
 * @apiSuccess (payTypes){String} name 支付方式名称
 * @apiSuccess (payTypes){Integer} payType 支付方式类型 1-微信；2-支付宝；3-银联；4-boss
 * @apiSuccess (products){JSONArray} serverInfos 服务数组对象
 * @apiSuccess (serverInfos){Integer} ruleCount 规则次数
 * @apiSuccess (serverInfos){String} ruleInfo 规则说明
 * @apiSuccess (serverInfos){String} ruleNmae 规则名称
 * @apiSuccess (serverInfos){String} ruleUnit 规则单位
 * @apiSuccess (serverInfos){String} serverCode 服务编码 10001-视频观看服务；10002-图书借阅服务
 * @apiSuccess (serverInfos){String} serverInfo 服务说明
 * @apiSuccess (serverInfos){String} serverName 服务名称
 * @apiSuccess (data){JsonArray} userInfo 用户信息
 * @apiSuccess (userInfo){String} accountNo 账号
 * @apiSuccess (userInfo){String} accountType 账号类型
 * @apiSuccess (userInfo){String} channelCode 渠道编码
 * @apiSuccess (userInfo){String} equipmentId 设备id
 * @apiSuccess (userInfo){String} createDate 创建时间
 * @apiSuccess (userInfo){String} modifyDate 修改时间
 * @apiSuccess (userInfo){String} equipmentName 设备名称
 * @apiSuccess (userInfo){String} equipmentType 设备类型
 * @apiSuccess (userInfo){String} productCode 产品编码
 * @apiSuccess (userInfo){String} userId 用户id
 * @apiSuccess (data){JsonArray} userFamily 用户家属信息
 * @apiSuccess (userFamily){String} id 家属id
 * @apiSuccess (userFamily){String} birthday 生日
 * @apiSuccess (userFamily){String} sex 性别 1-男；2-女；0-保密
 * @apiSuccess (userFamily){String} type 关系 1-子女；2-父母；3-其他
 * @apiSuccess (userFamily){String} createDate 创建时间
 * @apiSuccess (userFamily){String} modifyDate 修改时间
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "userInfo": {
            "accountNo": "4264678666",
            "accountType": 3,
            "channelCode": "0001",
            "createDate": "2018-11-15 19:02:56",
            "equipmentId": "dcsnavnsvnjdsv8hygbgy",
            "equipmentName": "xxx机顶盒",
            "equipmentType": 2,
            "id": "e58a28406716f91c01671709c6910003",
            "modifyDate": "2018-12-06 19:52:17",
            "productCode": "100001",
            "userId": "402883a064ad0da10164ad1b6b390001"
        },
        "userFamily": {},
        "retCode": "1",
        "retMsg": "获取成功",
        "products": [
            {
                "code": "100007",
                "currentPrice": "10800",
                "id": "40288ae6680282200168029547060008",
                "info": "",
                "name": "亲多多包季108元",
                "originalPrice": "10800",
                "pictureUrl": "",
                "serverInfos": [
                    {
                        "endTime": "2019-05-14 11:43:59",
                        "serverCode": "10001",
                        "serverName": "亲多多视频点播"
                    }
                ]
            }
        ]
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */


/**
 * @api {get,post} /api/user/findUserFamily 获取用户家属信息
 * @apiName findUserFamily
 * @apiGroup user
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} cardNo 智能卡号
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/user/findUserFamily?cardNo=13011112222
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){JSONObject} userFamily 用户家属信息
 * @apiSuccess (userFamily){String} id 家属id
 * @apiSuccess (userFamily){String} birthday 生日
 * @apiSuccess (userFamily){String} sex 性别 1-男；2-女；0-保密
 * @apiSuccess (userFamily){String} type 关系 1-子女；2-父母；3-其他
 * @apiSuccess (userFamily){String} createDate 创建时间
 * @apiSuccess (userFamily){String} modifyDate 修改时间
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "userFamily": {
            "birthday": "2000-09-01",
            "createDate": "2019-03-04 20:03:07",
            "id": "40288311694892dc01694893a2df0000",
            "modifyDate": "2019-03-04 20:04:34",
            "sex": 1,
            "type": 1,
            "userId": "4028831169488a2b0169488befd40000"
        }
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */


/**
 * @api {get,post} /api/user/saveUserFamily 保存用户家属信息
 * @apiName saveUserFamily
 * @apiGroup user
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {Integer} type 关系类型 1-子女；2-父母；3-其他
 * @apiParam {Integer} sex 性别 1-男；2-女；0-保密
 * @apiParam {String} birthday 生日
 * @apiParam {String} id 家属id（非必填，填入修改，不填新增）
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/user/saveUserFamily?cardNo=13011112222&type=1&sex=1&birthday=2000-09-01&id=40288311694892dc01694893a2df0000
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){JSONObject} userFamily 用户家属信息
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {},
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */


