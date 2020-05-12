// ===================================================支付相关PayApi=================================================
/**
 * @apiDefine xjntPay 新疆广电支付类接口
 */

/**
 * @api {get,post} /api/xjnt/pay/payQrCode 二维码支付（新疆广电）
 * @apiName payQrCode
 * @apiGroup xjntPay
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} prodCode 产品编码
 * @apiParam {String} stbNo 机顶盒号
 * @apiParam {String} appId 应用id
 * @apiParam {Integer} type 支付类型 1微信 2支付宝 3电视互动-BOSS 4增值业务-sp 5银联
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/xjnt/pay/payQrCode?type=1&cardNo=4264678666&prodCode=100001&stbNo=08A910E01FFFF30FFFF0002431008888&appId=1
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){String} orderId 订单号
 * @apiSuccess (data){String} qrcode 二维码base64值 （type = 3或者4时，该字段值为空）
 * @apiSuccess (data){String} qrcodeUrl 二维码url（type = 3或者4时，该字段值为空）
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "orderId": "W31125020190102100025493",
        "qrcode": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAASwAAAEsAQAAAABRBrPYAAABWklEQVR42u3a2bHCMAxAUXem1lUSHZhFS2xhBQq4vIHJcw4/Httawpj/vB4DBoPBYFPH5yWvK3+/P65RWMPEPtUGHWtewzr2BnnjdWn/2SjsF7PFKLZQYX+zHFbb2rBfzLEtTZ/kftfD9vCh+18XZWAlXVE7/3xLt1kNLKc3b35ix/oWWM9kz1gi5Pr3YQ2bcfrZzhbNKDLW6YVVNtdCQkqiDGuZZinmSK6phd2wZT43o3X1wr7igh1+4gEkaoshsI5F9eWVbFQTV9YCOzL1bEUj0jrPjgDsyGbeHFZM2NdGTjHsyDSHo32SKQvshs3MTDRnWZqqDbZvZ81af2kI1HY67NwPiR7n3OMI7Myy3eTTm7Fj7FEGVthaes3vvAXWsSVsRFG2PECE3bPI9KKrXtomsIZdx58fhs2yhNXHN1m7bt1NWMPy8c1c1+aoVRus1qf80AgGg8Fu2RMeLoiGWHueMwAAAABJRU5ErkJggg==",
        "qrcodeUrl": "weixin://wxpay/bizpayurl?pr=dvG3Lgj"
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */


/**
 * @api {get,post} /api/xjnt/pay/payResult 根据订单号查询支付结果
 * @apiName payResult
 * @apiGroup xjntPay
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} orderNo 智能卡号
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/xjnt/pay/payResult?orderNo=W31125020190102100025493
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){Integer} payResult 支付结果 0-未支付;1-已支付；2-支付失败；3-已退款
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "payResult": 0
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */

