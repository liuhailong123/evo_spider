// ===================================================LiveApi=================================================
/**
 * @apiDefine liveAPi 直播接口
 */

/**
 * @api {get,post} /api/live/info 直播详情
 * @apiName info
 * @apiGroup liveAPi
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} id 直播id
 * @apiParamExample {String} 请求样例
 * http://127.0.0.1:8080/evo-cms/api/live/info?id=40288ae664a6a8c70164a75562e20001
 *
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONObject} info 直播对象
 * @apiSuccess (info){String} id 直播id
 * @apiSuccess (info){String} name 直播名称
 * @apiSuccess (info){String} number 直播频道号
 * @apiSuccess (info){Integer} type 频道类型 1-导视 2-推荐 3-广告 4-内容
 * @apiSuccess (info){String} url 直播地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data":{
        "info":{
            "id":"40288ae664a6a8c70164a75562e20001",
            "name":"测试",
            "number":"114",
            "type":1,
            "url":"http://192.168.10.102:8082/qdd_sp_test.mp4"
        }
    },
    "extra":{

    },
    "message":"成功！",
    "status":"OK"
}
 *
 */