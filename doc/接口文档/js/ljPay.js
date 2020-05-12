// ===================================================支付相关PayApi=================================================
/**
 * @apiDefine ljPay 龙江广电支付类接口
 */

/**
 * @api {get,post} /api/lj/getPlayUrl 获取真实播放地址
 * @apiName getPlayUrl
 * @apiGroup ljPay
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} assetId 媒资id（一般是首页直播频道接口中的liveUrl中返回的三方媒资id）
 * @apiParam {String} stbNo 机顶盒号 type=1时该字段必填，其他情况传空字符串
 * @apiParam {Integer} type 支付类型 1 rtsp(有时效性) 2 hls(无时效性)
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/lj/getPlayUrl?type=1&cardNo=4264678666&assetId=xxxxxxxxxxxxxxxx&stbNo=
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){String} retCode 返回码， 0 成功；-1 无真实播放地址或获取失败
 * @apiSuccess (data){String} retMsg 返回消息
 * @apiSuccess (data){String} url 真实播放地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "retCode": "-1",
        "retMsg": "龙江获取真实播放地址失败:无真实播放地址：",
        "url": ""
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */


/**
 * @api {get,post} /api/lj/checkAreaCode 检测缴费区域
 * @apiName checkAreaCode
 * @apiGroup ljPay
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} code 区域码 一般前端在机顶盒上获取到该编码
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/lj/checkAreaCode?code=0001
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){String} retCode 返回码 0-未在缴费区域列表中(渠道码前不加Y);1-允许线下支付；2-允许线上支付；3-允许线上或线下支付
 * @apiSuccess (data){String} retMsg 返回消息
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "retCode": "0",
        "retMsg": "未在缴费区域列表中(渠道码前不加Y)"
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */

/**
 * @api {get,post} /api/lj/serverPay 支付接口
 * @apiName serverPay
 * @apiGroup ljPay
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} cardNo 智能卡号 一般前端在机顶盒上获取到信息
 * @apiParam {String} prodCode 产品编码 通过用户可订购产品接口获取
 * @apiParam {String} areaCode 区域码 一般前端在机顶盒上获取到该编码
 * @apiParam {String} appId 应用id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/lj/serverPay?cardNo=123456789&prodCode=1000012&areaCode=00001&appId=57
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){String} retCode 返回码 0-获取微信或支付宝二维码成功;-1-有未到期服务，请勿重复订购；-2-获取微信或支付宝二维码失败；-3-用户信息有误，暂不允许支付；-4-后台生成支付二维码失败；-5-询价失败，暂不允许支付；
 * @apiSuccess (data){JSONObject} retData 返回数据
 * @apiSuccess (retData){String} code 暂无实际意义。以外层retCode为准
 * @apiSuccess (retData){String} orderNo 订单号
 * @apiSuccess (retData){String} wxQR 微信二维码地址
 * @apiSuccess (retData){String} zfbQR 支付宝二维码地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "retCode": "0",
        "retData": {
            "code":"0",
            "orderNo":"B0013121316546798",
            "wxQR":"weixin://wxpay/bizpayurl?pr=DRXkMMw",
            "zfbQR":"https://qr.alipay.com/bax08046zxzwtq4egcvz20d9"
        }
    },
    "extra": {},
    "message": "获取微信或支付宝二维码成功！",
    "status": "OK"
}
 *
 */


/**
 * @api {get,post} /api/lj/isPay 检测订单是否已支付
 * @apiName isPay
 * @apiGroup ljPay
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} orderNo 订单号 支付接口中获取
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/lj/isPay?orderNo=B0013121316546798
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){String} retCode 返回码 0-支付成功;-1-该订单号不存在；-2-该订单未支付或支付失败；
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "retCode": "0",
        "retMsg": "支付成功"
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */
