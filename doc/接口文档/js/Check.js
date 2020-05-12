// ===================================================CheckApi=================================================
/**
 * @apiDefine checked 鉴权类接口
 */
/**
 * @api {get,post} /api/check/serverAuth 服务鉴权检测
 * @apiName serverAuth
 * @apiGroup checked
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} appId 应用id
 * @apiParam {String} serverCode 服务编码
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/check/serverAuth?cardNo=4264678666&appId=1&serverCode=10001
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){boolen} orderFlag 是否过期；false-未过期；true-已过期
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "orderFlag": false
        "orderMsg": "未过期"
    },
    "extra": {},
    "message": "接口调用成功！",
    "status": "OK"
}
 *
 */

/**
 * @api {get,post} /api/auth/play 播放鉴权检测(过时)
 * @apiName play
 * @apiDeprecated 该接口已过时，现使用
 * @apiGroup checked
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} appId 应用id
 * @apiParam {String} contentId 内容id
 * @apiParam {String} [stbNo] 机顶盒号
 * @apiParam {String} [stbIp] 机顶盒ip
 * @apiParam {Integer} [number] 子集集数，非剧集时无需传递
 * @apiParam {Integer} [childContentId] 子集内容id，非剧集时无需传递，与number互斥
 * @apiParam {String} [accesstoken] 用户登录标识，济南有线、新疆广电视开项目时必填
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/auth/play?number=1&cardNo=8531104115198153&stbNo&stbIp&appId=80&contentId=ff8080816a9c25b8016a9c339d910002&accesstoken=R5C517975U209F2010K537296F3IFFFFFFFFP8M2FB3EB8V20213Z5A5B6W11EEF60072A51
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){String} retCode 返回码 0：限免通过；1：鉴权通过；-1：智能卡号为空；-2：平台省网配置错误；-3：暂不支持该省网；-4：无当前子集信息；-10001：服务开通-鉴权未通过；-10002：产品订购-鉴权未通过；-10003：用户未注册-鉴权未通过；-10004：进行云支付-鉴权未通过（四川移动）；
 * @apiSuccess (data){String} retMsg 返回消息
 * @apiSuccess (data){JSONArray} videos 视频对象 retCode = 0、1 时，该对象返回
 * @apiSuccess (videos){String} url 播放地址
 * @apiSuccess (videos){String} cdn1Url cdn1播放地址（四川移动使用）
 * @apiSuccess (videos){String} cdn2Url cdn1播放地址（四川移动使用）
 * @apiSuccess (videos){String} cdn3Url cdn1播放地址（四川移动使用）
 * @apiSuccess (videos){Integer} definition 清晰度  1:4K 2:蓝光 3:超清 4:高清 5:标清 6:手机
 * @apiSuccess (videos){Integer} type 视频类型 1:2D【平面】 2:3D【立体】 3:VR【全景】
 * @apiSuccess (videos){String} url 播放地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data":{
        "retCode":0,
        "retMsg":"限免通过"
        "videos":[
            {
                "definition": 4,
                "type": 1,
                "cdn1Url": "",
                "cdn2Url": "",
                "cdn3Url": "",
                "type": 1,
                "url": "rtsp://10.255.16.109:1554/xinjiang_sl_sd/TS_5800/20180711/298502_20180711000409.ts?Contentid=MOVE1520180711000685&isHD=1&isIpqam=0&token=3EB1352BD5CFC7C896F64382F8BE28242CA7EDB728B3FE0FC1DA80A095B1F582F770B307161E6A067FB25ABC66A0438C675E092142B5CD7F4C0B2759C61DA9434635B215CDAF260485BD597D3BA712ABAA31E0018BFCFC9FD548604BE35A8E299AA42F4298E1BBBCC3888A53A47811936E6D5119517FB1275C21E55A9DF60A01CC5E594DE44EE37CDFAF7084EA6B9C52B315525BA4108309D0151E60F9EC659A89A3A823F01D7D5D1FCD3D021B1C4E87161A75B9E7"
            }
        ]
    },
    "extra":{

    },
    "message":"播放鉴权接口调用成功！",
    "status":"OK"
}
 *
 */

/**
 * @api {get,post} /api/check/columnsAuth 栏目鉴权检测
 * @apiName columnsAuth
 * @apiGroup checked
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} appId 应用id
 * @apiParam {String} columnsId 栏目id
 * @apiParam {String} serverCode 服务编码
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/check/columnsAuth?cardNo=4264678666&appId=1&columnsId=31&serverCode=10001
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){boolen} orderFlag 是否过期；false-未过期；true-已过期
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "orderFlag": false
        "orderMsg": "未过期"
    },
    "extra": {},
    "message": "接口调用成功！",
    "status": "OK"
}
 *
 */


/**
 * @api {get,post} /api/check/contentAuth 内容鉴权检测
 * @apiName contentAuth
 * @apiGroup checked
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} appId 应用id
 * @apiParam {String} contentId 内容id
 * @apiParam {String} serverCode 服务编码
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/check/columnsAuth?cardNo=4264678666&appId=1&contentId=31&serverCode=10001
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){boolen} orderFlag 是否过期；false-未过期；true-已过期
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "orderFlag": false
        "orderMsg": "未过期"
    },
    "extra": {},
    "message": "接口调用成功！",
    "status": "OK"
}
 *
 */

