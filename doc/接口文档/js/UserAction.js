// ===================================================UserActionApi=================================================
/**
 * @apiDefine userAction 用户行为接口
 */

/**
 * @api {get,post} /api/userAction 用户行为数据（过时）
 * @apiDeprecated 该接口已过时现在使用<br/>
 * (#userAction:addLike)、(#userAction:queryLike)、(#userAction:delLike)、(#userAction:getLike)、<br/>
 * (#userAction:addBlack)、(#userAction:queryBlack)、(#userAction:delBlack)、(#userAction:getBlack)、<br/>
 * (#userAction:addPlayRecord)、(#userAction:queryPlayRecord)、(#userAction:delPlayRecord)、(#userAction:getPlayRecord)。<br/>
 *
 * @apiName userAction
 * @apiGroup userAction
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParam {String} cardNo 卡号
 * @apiParam {Integer} type 1-播放记录；2-收藏；3-签到；4-时长记录；5-黑名单; 6-搜索关键字
 * @apiParam {Integer} motion 动作 1-上报；2-删除；3-查询
 * @apiParam {String} contentId 内容id motion=1、2时必填
 * @apiParam {String} duration 时长(秒) motion=1时必填
 * @apiParam {String} number 集数 motion=1时必填
 * @apiParam {Integer} pageNum 页数 motion=3时必填
 * @apiParam {Integer} pageSize 每页条数 motion=3时必填
 * @apiParamExample {String} 请求样例
 * motion=1:
 * http://localhost:8080/cms_sp/api/userAction?appId=1&cardNo=4264678666&type=1&motion=1&contentId=402881e467ccafc80167ccb2ec0d004e&duration=20&number=1
 * motion=2:
 * http://localhost:8080/cms_sp/api/userAction?appId=1&cardNo=4264678666&type=1&motion=2&id=402881e467ccafc80167ccb2ec0d004e
 * motion=3:
 * http://localhost:8080/cms_sp/api/userAction?appId=1&cardNo=4264678666&type=1&motion=3&pageNum=1&pageSize=10
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){Long} total 记录总条数
 * @apiSuccess (data){JSONObject} records 查询对象
 * @apiSuccess (records){String} id 主键
 * @apiSuccess (records){String} name 名称
 * @apiSuccess (records){Integer} type 类型
 * @apiSuccess (records){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * motion=1、2
 * {
    "data": null,
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 motion=3
 {
     "data": {
         "records": [
             {
                 "id": "402881e467ccafc80167ccb2ec0d004e",
                 "name": "熊熊乐园第二季",
                 "pictures": [],
                 "type": 2
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
 * @api {get,post} /api/userAction/hotKeyword 获取热搜关键词接口
 * @apiName hotKeyword
 * @apiGroup userAction
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParam {Integer} count 数量
 * @apiParamExample {String} 请求样例
 * http://127.0.0.1:8080/evo-cms/api/userAction/hotKeyword?appId=1&count=10
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){Array} keyword 热搜关键词 [数组]
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data":{
        "keyword":[
            "xx",
            "天使"
        ]
    },
    "extra":{

    },
    "message":"成功！",
    "status":"OK"
}
 *
 */

/**
 * @api {get,post} /api/userAction/like/add 收藏记录上报接口
 * @apiName addLike
 * @apiGroup userAction
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} contentId 内容id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/sp/api/userAction/like/add?appId=1&cardNo=4264678666&contentId=ff8080816a9c25b8016a9c339d910002
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态 OK-成功；其他为失败
 * @apiSuccessExample {json} 返回样例
 * {
    "data":{
    },
    "extra":{

    },
    "message":"成功！",
    "status":"OK"
}
 *
 */

/**
 * @api {get,post} /api/userAction/like/query 收藏记录查询接口
 * @apiName queryLike
 * @apiGroup userAction
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {Integer} pageNum 页码
 * @apiParam {Integer} pageSize 每页数据条数
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/sp/api/userAction/like/query?appId=1&cardNo=4264678666&pageNum=1&pageSize=10
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){Integer} total  数据总数
 * @apiSuccess (data){String} retCode  返回码
 * @apiSuccess (data){String} retMsg  返回消息
 * @apiSuccess (data){JSONArray} records  查询数据
 * @apiSuccess (records){String} id 主键
 * @apiSuccess (records){String} contentId 内容id
 * @apiSuccess (records){String} name 名称
 * @apiSuccess (records){Integer} type 类型 1-电影；2-剧集
 * @apiSuccess (records){Integer} episodeCount 剧集总集数
 * @apiSuccess (records){Integer} nowEpisodeCount 当前集数
 * @apiSuccess (records){String} score 评分
 * @apiSuccess (records){String} templateCode 栏目编码
 * @apiSuccess (records){String} title 标题
 * @apiSuccess (records){Integer} isHot 是否最热
 * @apiSuccess (records){Integer} isNew 是否最新
 * @apiSuccess (records){Integer} isRecommend 是否推荐
 * @apiSuccess (records){String} liveUrl 直播地址
 * @apiSuccess (records){Integer} episodeCount 剧集总集数
 * @apiSuccess (records){String} info 简介
 * @apiSuccess (records){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态 OK-成功；其他为失败
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "total": 1,
        "records": [
            {
                "contentId": "ff8080816a92a9b1016a92bdaa5f0498",
                "episodeCount": 30,
                "id": "ff8080816a9c25b8016a9c339d910002",
                "info": "科幻小说家艾玛在小时候捡到了几颗外星晶石，她把晶石带回家，结果家里的电脑们和晶石产生了能量共鸣，变成了具有生命力的机器人。它们分别是主机博安、显示器歌美美、打印机腾少和扫描仪大嘴。这些电脑小伙伴们与艾玛从小一起长大，见证艾玛由一个小女生变成一名著名科幻小说家的经历，大家感情和睦，过着无忧无虑的生活。直到有一天，重病的作家突然离世，而不知缘由的电脑伙伴们也因为设备陈旧而被人类遗弃至小镇中的垃圾场，从此电脑伙伴们的生活发生了翻天覆地的转变。但电脑伙伴们不甘于被遗弃的命运，他们决定寻找主人。",
                "isHot": 0,
                "isNew": 0,
                "isRecommend": 0,
                "liveUrl": "",
                "name": "电脑总动员",
                "nowEpisodeCount": 0,
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://112.18.251.71:8084/image/20180408152831516.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://112.18.251.71:8084/image/20180408152833242.jpg"
                    }
                ],
                "score": "8",
                "templateCode": "",
                "title": "",
                "type": 2
            }
        ],
        "retCode": "1",
        "retMsg": "操作成功"
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */


/**
 * @api {get,post} /api/userAction/like/del 收藏记录删除接口
 * @apiName delLike
 * @apiGroup userAction
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} contentIds 内容ids，多个内容id使用英文逗号分隔
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/sp/api/userAction/like/del?appId=1&cardNo=4264678666&contentIds=ff8080816a9c25b8016a9c339d910002
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态 OK-成功；其他为失败
 * @apiSuccessExample {json} 返回样例
 * {
    "data":{
    },
    "extra":{

    },
    "message":"成功！",
    "status":"OK"
}
 *
 */


/**
 * @api {get,post} /api/userAction/like/get 某内容是否收藏接口
 * @apiName getLike
 * @apiGroup userAction
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} contentId 内容id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/sp/api/userAction/like/get?appId=1&cardNo=4264678666&contentId=ff8080816a9c25b8016a9c339d910002
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){Integer} isCollect  是否收藏 1-是；0-否
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态 OK-成功；其他为失败
 * @apiSuccessExample {json} 返回样例
 * {
    "data":{
        "isCollect":1
    },
    "extra":{

    },
    "message":"成功！",
    "status":"OK"
}
 *
 */


/**
 * @api {get,post} /api/userAction/black/add 黑名单上报接口
 * @apiName addBlack
 * @apiGroup userAction
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} contentId 内容id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/sp/api/userAction/black/add?appId=1&cardNo=4264678666&contentId=ff8080816a9c25b8016a9c339d910002
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态 OK-成功；其他为失败
 * @apiSuccessExample {json} 返回样例
 * {
    "data":{
    },
    "extra":{

    },
    "message":"成功！",
    "status":"OK"
}
 *
 */

/**
 * @api {get,post} /api/userAction/black/query 黑名单查询接口
 * @apiName queryBlack
 * @apiGroup userAction
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {Integer} pageNum 页码
 * @apiParam {Integer} pageSize 每页数据条数
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/sp/api/userAction/black/query?appId=1&cardNo=4264678666&pageNum=1&pageSize=10
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){Integer} total  数据总数
 * @apiSuccess (data){String} retCode  返回码
 * @apiSuccess (data){String} retMsg  返回消息
 * @apiSuccess (data){JSONArray} records  查询数据
 * @apiSuccess (records){String} id 主键
 * @apiSuccess (records){String} contentId 内容id
 * @apiSuccess (records){String} name 名称
 * @apiSuccess (records){Integer} type 类型 1-电影；2-剧集
 * @apiSuccess (records){Integer} episodeCount 剧集总集数
 * @apiSuccess (records){Integer} nowEpisodeCount 当前集数
 * @apiSuccess (records){String} score 评分
 * @apiSuccess (records){String} templateCode 栏目编码
 * @apiSuccess (records){String} title 标题
 * @apiSuccess (records){Integer} isHot 是否最热
 * @apiSuccess (records){Integer} isNew 是否最新
 * @apiSuccess (records){Integer} isRecommend 是否推荐
 * @apiSuccess (records){String} liveUrl 直播地址
 * @apiSuccess (records){Integer} episodeCount 剧集总集数
 * @apiSuccess (records){String} info 简介
 * @apiSuccess (records){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态 OK-成功；其他为失败
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "total": 1,
        "records": [
            {
                "contentId": "ff8080816a92a9b1016a92bdaa5f0498",
                "episodeCount": 30,
                "id": "ff8080816a9c25b8016a9c339d910002",
                "info": "科幻小说家艾玛在小时候捡到了几颗外星晶石，她把晶石带回家，结果家里的电脑们和晶石产生了能量共鸣，变成了具有生命力的机器人。它们分别是主机博安、显示器歌美美、打印机腾少和扫描仪大嘴。这些电脑小伙伴们与艾玛从小一起长大，见证艾玛由一个小女生变成一名著名科幻小说家的经历，大家感情和睦，过着无忧无虑的生活。直到有一天，重病的作家突然离世，而不知缘由的电脑伙伴们也因为设备陈旧而被人类遗弃至小镇中的垃圾场，从此电脑伙伴们的生活发生了翻天覆地的转变。但电脑伙伴们不甘于被遗弃的命运，他们决定寻找主人。",
                "isHot": 0,
                "isNew": 0,
                "isRecommend": 0,
                "liveUrl": "",
                "name": "电脑总动员",
                "nowEpisodeCount": 0,
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://112.18.251.71:8084/image/20180408152831516.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://112.18.251.71:8084/image/20180408152833242.jpg"
                    }
                ],
                "score": "8",
                "templateCode": "",
                "title": "",
                "type": 2
            }
        ],
        "retCode": "1",
        "retMsg": "操作成功"
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */


/**
 * @api {get,post} /api/userAction/black/del 黑名单删除接口
 * @apiName delBlack
 * @apiGroup userAction
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} contentIds 内容ids，多个内容id使用英文逗号分隔
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/sp/api/userAction/black/del?appId=1&cardNo=4264678666&contentIds=ff8080816a9c25b8016a9c339d910002
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态 OK-成功；其他为失败
 * @apiSuccessExample {json} 返回样例
 * {
    "data":{
    },
    "extra":{

    },
    "message":"成功！",
    "status":"OK"
}
 *
 */

/**
 * @api {get,post} /api/userAction/black/get 某内容是否黑名单接口
 * @apiName getBlack
 * @apiGroup userAction
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} contentId 内容id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/sp/api/userAction/black/get?appId=1&cardNo=4264678666&contentId=ff8080816a9c25b8016a9c339d910002
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){Integer} isBlack  是否黑名单 1-是；0-否
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态 OK-成功；其他为失败
 * @apiSuccessExample {json} 返回样例
 * {
    "data":{
        "isBlack":1
    },
    "extra":{

    },
    "message":"成功！",
    "status":"OK"
}
 *
 */

/**
 * @api {get,post} /api/userAction/playRecord/add 播放记录上报接口
 * @apiName addPlayRecord
 * @apiGroup userAction
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} contentId 内容id
 * @apiParam {Integer} number 当前集数
 * @apiParam {Integer} duration 播放时长（秒）
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/sp/api/userAction/playRecord/add?appId=1&cardNo=4264678666&contentId=ff8080816a9c25b8016a9c339d910002&number=2&duration=500
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态 OK-成功；其他为失败
 * @apiSuccessExample {json} 返回样例
 * {
    "data":{
    },
    "extra":{

    },
    "message":"成功！",
    "status":"OK"
}
 *
 */

/**
 * @api {get,post} /api/userAction/playRecord/query 播放记录查询接口
 * @apiName queryPlayRecord
 * @apiGroup userAction
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {Integer} pageNum 页码
 * @apiParam {Integer} pageSize 每页数据条数
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/sp/api/userAction/playRecord/query?appId=1&cardNo=4264678666&pageNum=1&pageSize=10
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){Integer} total  数据总数
 * @apiSuccess (data){String} retCode  返回码
 * @apiSuccess (data){String} retMsg  返回消息
 * @apiSuccess (data){JSONArray} records  查询数据
 * @apiSuccess (records){String} id 主键
 * @apiSuccess (records){String} contentId 内容id
 * @apiSuccess (records){String} name 名称
 * @apiSuccess (records){Integer} type 类型 1-电影；2-剧集
 * @apiSuccess (records){Integer} episodeCount 剧集总集数
 * @apiSuccess (records){Integer} nowEpisodeCount 当前集数
 * @apiSuccess (records){String} score 评分
 * @apiSuccess (records){String} templateCode 栏目编码
 * @apiSuccess (records){String} title 标题
 * @apiSuccess (records){Integer} isHot 是否最热
 * @apiSuccess (records){Integer} isNew 是否最新
 * @apiSuccess (records){Integer} isRecommend 是否推荐
 * @apiSuccess (records){String} liveUrl 直播地址
 * @apiSuccess (records){Integer} episodeCount 剧集总集数
 * @apiSuccess (records){String} info 简介
 * @apiSuccess (records){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态 OK-成功；其他为失败
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "total": 1,
        "records": [
            {
                "contentId": "ff8080816a92a9b1016a92bdaa5f0498",
                "episodeCount": 30,
                "id": "ff8080816a9c25b8016a9c339d910002",
                "info": "科幻小说家艾玛在小时候捡到了几颗外星晶石，她把晶石带回家，结果家里的电脑们和晶石产生了能量共鸣，变成了具有生命力的机器人。它们分别是主机博安、显示器歌美美、打印机腾少和扫描仪大嘴。这些电脑小伙伴们与艾玛从小一起长大，见证艾玛由一个小女生变成一名著名科幻小说家的经历，大家感情和睦，过着无忧无虑的生活。直到有一天，重病的作家突然离世，而不知缘由的电脑伙伴们也因为设备陈旧而被人类遗弃至小镇中的垃圾场，从此电脑伙伴们的生活发生了翻天覆地的转变。但电脑伙伴们不甘于被遗弃的命运，他们决定寻找主人。",
                "isHot": 0,
                "isNew": 0,
                "isRecommend": 0,
                "liveUrl": "",
                "name": "电脑总动员",
                "nowEpisodeCount": 0,
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://112.18.251.71:8084/image/20180408152831516.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://112.18.251.71:8084/image/20180408152833242.jpg"
                    }
                ],
                "score": "8",
                "templateCode": "",
                "title": "",
                "type": 2
            }
        ],
        "retCode": "1",
        "retMsg": "操作成功"
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */


/**
 * @api {get,post} /api/userAction/playRecord/del 播放记录删除接口
 * @apiName delPlayRecord
 * @apiGroup userAction
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} contentIds 内容ids，多个内容id使用英文逗号分隔
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/sp/api/userAction/playRecord/del?appId=1&cardNo=4264678666&contentIds=ff8080816a9c25b8016a9c339d910002
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态 OK-成功；其他为失败
 * @apiSuccessExample {json} 返回样例
 * {
    "data":{
    },
    "extra":{

    },
    "message":"成功！",
    "status":"OK"
}
 *
 */

/**
 * @api {get,post} /api/userAction/playRecord/get 某内容观看情况接口
 * @apiName getPlayRecord
 * @apiGroup userAction
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParam {String} cardNo 智能卡号
 * @apiParam {String} contentId 内容id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/sp/api/userAction/playRecord/get?appId=1&cardNo=4264678666&contentId=ff8080816a9c25b8016a9c339d910002
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){Integer} total  总集数
 * @apiSuccess (data){Integer} number  当前集数
 * @apiSuccess (data){Integer} duration  观看时长：秒
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态 OK-成功；其他为失败
 * @apiSuccessExample {json} 返回样例
 * {
    "data":{
        "total":20
        "number":1
        "duration":0
    },
    "extra":{

    },
    "message":"成功！",
    "status":"OK"
}
 *
 */


/**
 * @api {get,post} /api/userAction/todayTime/query 今日播放时长查询接口
 * @apiName queryTodayTime
 * @apiGroup userAction
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParam {String} cardNo 智能卡号
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/sp/api/userAction/todayTime/query?appId=1&cardNo=4264678666
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){String} watchDuration  观看时长（秒）
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态 OK-成功；其他为失败
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "watchDuration": "5600"
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */
