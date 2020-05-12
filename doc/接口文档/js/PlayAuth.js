// ===================================================CheckApi=================================================
/**
 * @apiDefine playAuth 省网播放鉴权类接口
 */

/**
 * @api {get,post} /api/auth/flow 未来媒体
 * @apiDescription get请求时，需要对json进行URLEncoder的处理
 * @apiName evo-flow
 * @apiGroup playAuth
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {JSONObject} params 入参
 * @apiParam (params){String} cardNo 智能卡号
 * @apiParam (params){String} appId 应用id
 * @apiParam (params){String} contentId 内容id(电影详情id或者剧集详情id)
 * @apiParam (params){Integer} number 子集集数，剧集传具体子集集数；电影固定传1
 * @apiParam (params){String} childContentId 子集id，该字段与number互斥；电影固定传空
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/auth/flow?params=URLEncoder({"cardNo":"test","appId":"1","number":"2","contentId":"ff8080816a92a9b1016a958fb4871cad"})
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
 * @api {get,post} /api/auth/flow 新疆广电(SP2.0)
 * @apiDescription get请求时，需要对json进行URLEncoder的处理
 * @apiName xjnt-sp-2.0-flow
 * @apiGroup playAuth
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {JSONObject} params 入参
 * @apiParam (params){String} cardNo 智能卡号
 * @apiParam (params){String} stbNo 机顶盒号
 * @apiParam (params){String} stbIp 机顶盒ip
 * @apiParam (params){String} appId 应用id
 * @apiParam (params){String} contentId 内容id(电影详情id或者剧集详情id)
 * @apiParam (params){Integer} number 子集集数，剧集传具体子集集数；电影固定传1
 * @apiParam (params){String} childContentId 子集id，该字段与number互斥；电影固定传空
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/auth/flow?params=URLEncoder({"cardNo":"test","stbNo":"xxxx","stbIp":"0.0.0.0","appId":"1","number":"2","contentId":"ff8080816a92a9b1016a958fb4871cad"})
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
 * @api {get,post} /api/auth/flow 新疆广电(SP3.0)
 * @apiDescription get请求时，需要对json进行URLEncoder的处理
 * @apiName xjnt-sp-3.0-flow
 * @apiGroup playAuth
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {JSONObject} params 入参
 * @apiParam (params){String} cardNo 智能卡号
 * @apiParam (params){String} accesstoken 用户登录token
 * @apiParam (params){String} appId 应用id
 * @apiParam (params){String} contentId 内容id(电影详情id或者剧集详情id)
 * @apiParam (params){Integer} number 子集集数，剧集传具体子集集数；电影固定传1
 * @apiParam (params){String} childContentId 子集id，该字段与number互斥；电影固定传空
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/auth/flow?params=URLEncoder({"cardNo":"test","accesstoken":"xxxxxxx","appId":"1","number":"2","contentId":"ff8080816a92a9b1016a958fb4871cad"})
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
 * @api {get,post} /api/auth/flow 济南有线
 * @apiDescription get请求时，需要对json进行URLEncoder的处理
 * @apiName jnyx-sp-3.0-flow
 * @apiGroup playAuth
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {JSONObject} params 入参
 * @apiParam (params){String} cardNo 智能卡号
 * @apiParam (params){String} accesstoken 用户登录token
 * @apiParam (params){String} appId 应用id
 * @apiParam (params){String} contentId 内容id(电影详情id或者剧集详情id)
 * @apiParam (params){Integer} number 子集集数，剧集传具体子集集数；电影固定传1
 * @apiParam (params){String} childContentId 子集id，该字段与number互斥；电影固定传空
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/auth/flow?params=URLEncoder({"cardNo":"test","accesstoken":"xxxxxxx","appId":"1","number":"2","contentId":"ff8080816a92a9b1016a958fb4871cad"})
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
 * @api {get,post} /api/auth/flow 浙江华数
 * @apiDescription get请求时，需要对json进行URLEncoder的处理
 * @apiName wasu-sp-3.0-flow
 * @apiGroup playAuth
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {JSONObject} params 入参
 * @apiParam (params){String} cardNo 智能卡号
 * @apiParam (params){String} appId 应用id
 * @apiParam (params){String} contentId 内容id(电影详情id或者剧集详情id)
 * @apiParam (params){Integer} number 子集集数，剧集传具体子集集数；电影固定传1
 * @apiParam (params){String} childContentId 子集id，该字段与number互斥；电影固定传空
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/auth/flow?params=URLEncoder({"cardNo":"test","appId":"1","number":"2","contentId":"ff8080816a92a9b1016a958fb4871cad"})
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
