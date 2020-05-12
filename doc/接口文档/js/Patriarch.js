// ===================================================PatriarchApi=================================================
/**
 * @apiDefine patriarch 儿童锁类接口
 */

/**
 * @api {get,post} /api/patriarch/appLockList 应用锁列表
 * @apiName appLockList
 * @apiGroup patriarch
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/patriarch/appLockList?appId=1
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){JsonArray} appLockList 应用锁信息数组对象
 * @apiSuccess (appLockList){String} appId 应用id
 * @apiSuccess (appLockList){String} contentId 加锁内容id
 * @apiSuccess (appLockList){String} contentName 加锁内容名称
 * @apiSuccess (appLockList){String} contentType 加锁内容类型 1-栏目
 * @apiSuccess (appLockList){String} id 主键
 * @apiSuccess (appLockList){Integer} status 标识 0-非默认加锁；1-默认加锁
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "appLockList": [
            {
                "appId": "1",
                "contentId": "31",
                "contentName": "少儿动画",
                "contentType": 1,
                "id": "e48a284067f4009d0167f40e70790001",
                "status": 0
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
 * @api {get,post} /api/patriarch/userAppLockSend 用户加锁上报
 * @apiName userAppLockSend
 * @apiGroup patriarch
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} userId 用户id
 * @apiParam {String} appLockConfIds 应用锁信息ids 多个id使用英文逗号分隔
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/patriarch/userAppLockSend?userId=402883a064ad0da10164ad1b6b390001&appLockConfIds=e48a284067f4009d0167f40e70790001
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){String} sendResult 结果标识
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "sendResult": "OK"
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */

/**
 * @api {get,post} /api/patriarch/userAppLockList 用户加锁信息列表
 * @apiName userAppLockList
 * @apiGroup patriarch
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} userId 用户id
 * @apiParam {String} appLockConfIds 应用锁信息ids 多个id使用英文逗号分隔
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/patriarch/userAppLockList?userId=402883a064ad0da10164ad1b6b390001&appId=1
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){JSONObject} userAppLockList 锁信息数组对象
 * @apiSuccess (userAppLockList){String} appId 应用id
 * @apiSuccess (userAppLockList){String} contentId 锁内容id
 * @apiSuccess (userAppLockList){String} contentName 锁内容名称
 * @apiSuccess (userAppLockList){String} contentType 锁内容类型；1-栏目
 * @apiSuccess (userAppLockList){String} id 用户锁对象主键
 * @apiSuccess (userAppLockList){String} status 标识；1-
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态 1-加锁 ;0-未加锁
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "userAppLockList": [
            {
                "appId": "1",
                "contentId": "31",
                "contentName": "少儿动画",
                "contentType": 1,
                "id": "e48a284067f4009d0167f40e70790001",
                "status": 1
            }
        ]
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */
