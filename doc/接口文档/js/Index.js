// ===================================================IndexApi=================================================
/**
 * @apiDefine index 首页接口
 */

/**
 * @api {get,post} /api/index Linux首页接口
 * @apiName index
 * @apiGroup index
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} userCardNo 用户智能卡号（非必填）
 * @apiParam {String} appId 应用id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/index?userCardNo=4264678666&appId=1
 *
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONArray} positionList 位置信息数组对象
 * @apiSuccess (positionList){String} bgImg 海报地址
 * @apiSuccess (positionList){String} blankUrl 跳转地址
 * @apiSuccess (positionList){String} down 距下距离
 * @apiSuccess (positionList){String} focusImg 选中图片
 * @apiSuccess (positionList){String} id 内容id
 * @apiSuccess (positionList){String} left 距左距离
 * @apiSuccess (positionList){String} name 内容名称
 * @apiSuccess (positionList){String} position 位置
 * @apiSuccess (positionList){String} right 距右距离
 * @apiSuccess (positionList){String} type 位置类型；1-直播；2-电影；3-剧集；4-栏目；5-其他
 * @apiSuccess (positionList){String} up 距上距离
 * @apiSuccess (data){JSONObject} live 直播对象
 * @apiSuccess (live){String} id 直播内容id
 * @apiSuccess (live){String} url 直播地址
 * @apiSuccess (live){String} episodeNumber 集数
 * @apiSuccess (live){JSONArray} cdn 直播地址
 * @apiSuccess (cdn){String} url cdn播放地址
 * @apiSuccess (data){JSONArray} notice 公告对象
 * @apiSuccess (notice){String} blankUrl 跳转地址
 * @apiSuccess (notice){String} content 公告内容
 * @apiSuccess (notice){String} count 内容长度
 * @apiSuccess (notice){String} id 公告主键
 * @apiSuccess (notice){String} type 公告类型；1-系统公告：1001-缴费通知；2-用户公告：2001-直播；2002-服务次数查询通知;2003-套餐预到期提醒通知
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "positionList": [
            {
                "bgImg": "http://192.168.10.102:8081/img/home/search.png",
                "blankUrl": "../pages/search.html",
                "down": 4,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_search.png",
                "id": null,
                "left": 0,
                "name": "搜索",
                "position": 1,
                "right": 2,
                "type": 5,
                "up": 0
            },
            {
                "bgImg": "http://192.168.10.102:8081/img/home/order.png",
                "blankUrl": "../pages/order.html",
                "down": 4,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_order.png",
                "id": null,
                "left": 1,
                "name": "订购",
                "position": 2,
                "right": 0,
                "type": 5,
                "up": 0
            },
            {
                "bgImg": "",
                "blankUrl": "./pages/play_vod.html",
                "down": 6,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_live.png",
                "id": "40288ae667d02c9c0167de38c0280007",
                "left": 0,
                "name": "小窗口频道",
                "position": 3,
                "right": 4,
                "type": 1,
                "up": 1
            },
            {
                "bgImg": "http://img.evomedia.cn/cms/image/20180606110224734.jpg",
                "blankUrl": "../pages/episode.html",
                "down": 9,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_recommend_content.png",
                "id": "ff80808162200379016416c7cee83553",
                "left": 3,
                "name": "超神游戏（第一季）",
                "position": 4,
                "right": 5,
                "type": 3,
                "up": 1
            },
            {
                "bgImg": "http://img.evomedia.cn/cms/image/20180417181451650.jpg",
                "blankUrl": "../pages/episode.html",
                "down": 11,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_recommend_content.png",
                "id": "ff808081622003790163aacd761e52a8",
                "left": 4,
                "name": "热闹一家",
                "position": 5,
                "right": 0,
                "type": 3,
                "up": 2
            },
            {
                "bgImg": "http://192.168.10.102:8081/img/home/history.png",
                "blankUrl": "../pages/history.html",
                "down": 12,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_btn.png",
                "id": null,
                "left": 0,
                "name": "观看记录",
                "position": 6,
                "right": 7,
                "type": 5,
                "up": 3
            },
            {
                "bgImg": "http://192.168.10.102:8081/img/home/cellect.png",
                "blankUrl": "../pages/cellect.html",
                "down": 13,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_btn.png",
                "id": null,
                "left": 6,
                "name": "我的收藏",
                "position": 7,
                "right": 8,
                "type": 5,
                "up": 3
            },
            {
                "bgImg": "http://192.168.10.102:8081/img/home/parent_center.png",
                "blankUrl": "../pages/parent.html",
                "down": 14,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_btn.png",
                "id": null,
                "left": 7,
                "name": "家长中心",
                "position": 8,
                "right": 9,
                "type": 5,
                "up": 3
            },
            {
                "bgImg": "http://192.168.10.102:8081/img/home/sedh.png",
                "blankUrl": "../pages/column.html",
                "down": 15,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_recommend_col.png",
                "id": "31",
                "left": 8,
                "name": "少儿动画",
                "position": 9,
                "right": 10,
                "type": 4,
                "up": 4
            },
            {
                "bgImg": "http://192.168.10.102:8081/img/home/dhmx.png",
                "blankUrl": "../pages/list.html",
                "down": 16,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_recommend_col.png",
                "id": "32",
                "left": 9,
                "name": "动画明星",
                "position": 10,
                "right": 11,
                "type": 4,
                "up": 4
            },
            {
                "bgImg": "http://192.168.10.102:8081/img/home/qzhd.png",
                "blankUrl": "../pages/activity.html",
                "down": 17,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_recommend_col.png",
                "id": "14",
                "left": 10,
                "name": "亲子活动",
                "position": 11,
                "right": 0,
                "type": 4,
                "up": 5
            },
            {
                "bgImg": "http://192.168.10.102:8081/img/home/book.png",
                "blankUrl": "../pages/list.html",
                "down": 0,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_col.png",
                "id": "47",
                "left": 0,
                "name": "图书馆",
                "position": 12,
                "right": 13,
                "type": 4,
                "up": 6
            },
            {
                "bgImg": "http://192.168.10.102:8081/img/home/pic_book.png",
                "blankUrl": "../pages/list.html",
                "down": 0,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_col.png",
                "id": "35",
                "left": 12,
                "name": "绘本馆",
                "position": 13,
                "right": 14,
                "type": 4,
                "up": 7
            },
            {
                "bgImg": "http://192.168.10.102:8081/img/home/ch_language.png",
                "blankUrl": "../pages/list.html",
                "down": 0,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_col.png",
                "id": "36",
                "left": 13,
                "name": "国学馆",
                "position": 14,
                "right": 15,
                "type": 4,
                "up": 8
            },
            {
                "bgImg": "http://192.168.10.102:8081/img/home/english.png",
                "blankUrl": "../pages/list.html",
                "down": 0,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_col.png",
                "id": "37",
                "left": 14,
                "name": "英语馆",
                "position": 15,
                "right": 16,
                "type": 4,
                "up": 8
            },
            {
                "bgImg": "http://192.168.10.102:8081/img/home/eco.png",
                "blankUrl": "../pages/list.html",
                "down": 0,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_col.png",
                "id": "38",
                "left": 15,
                "name": "科普馆",
                "position": 16,
                "right": 17,
                "type": 4,
                "up": 10
            },
            {
                "bgImg": "http://192.168.10.102:8081/img/home/child_song.png",
                "blankUrl": "../pages/list.html",
                "down": 0,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_col.png",
                "id": "40",
                "left": 16,
                "name": "儿歌馆",
                "position": 17,
                "right": 18,
                "type": 4,
                "up": 10
            },
            {
                "bgImg": "http://192.168.10.102:8081/img/home/geek.png",
                "blankUrl": "../pages/list.html",
                "down": 0,
                "focusImg": "http://192.168.10.102:8081/img/home/focus_col.png",
                "id": "39",
                "left": 17,
                "name": "创客馆",
                "position": 18,
                "right": 0,
                "type": 4,
                "up": 11
            }
        ],
        "live":{
            "id":"8a9384776833488c01683360a74a0011",
            "cdn":[
                {
                    "url":""
                },
                {
                    "url":""
                },
                {
                    "url":""
                }
            ],
            "episodeNumber":1,
            "url":"/home/video/ff8080816a97458e016a97e103ee00a3.ts"
        },
        "notice": [
            {
                "blankUrl": "http://xxxxxxxxxxx",
                "content": "亲多多线下亲子活动",
                "count": 9,
                "id": "1",
                "type": 1001
            },
            {
                "blankUrl": "",
                "content": "您还有20次借阅权利尚未使用！",
                "count": 15,
                "id": "e58a2840673e454f01673e54c0f60001",
                "type": 2002
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
 * @api {get,post} /api/index/ott/live 安卓首页直播频道接口
 * @apiName ottIndexLive
 * @apiGroup index
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} columnId 栏目id
 * @apiParam {String} appId 应用id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/index/ott/live?appId=58&columnId=61
 *
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONArray} live 直播频道数组对象
 * @apiSuccess (live){String} id 内容id
 * @apiSuccess (live){String} liveUrl 直播地址
 * @apiSuccess (live){String} name 频道名称
 * @apiSuccess (live){String} title 频道标题
 * @apiSuccess (live){String} type 内容类型 1电影 2剧集总集 3直播 4活动 5图书 6-栏目
 * @apiSuccess (live){JSONArray} pictures 图片数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "live": [
            {
                "id": "8a9384776833488c01683360a74a0011",
                "liveUrl": "rtsp://10.255.16.109:1554/xinjiang_sl_sd/TS_5800/20190116/347281_20190116000961.ts?Contentid=MOVE5420190116001307&isHD=1&isIpqam=0&token=766B3EB7495BBBB7C3D0F996884854605C21FE1857F36B60CD4B752AB1F48111471B06AF6E024DD039D1428F5DFF03466A8B2A60219E4CA7F18E0A89A6204D89900EFD645902CE6C02EA85C124B5279B9FA24ABE5CE54FED661BE6148BCEE816D10F186BDB380A8982904BC060EF55085D55D80924454DF1345917518172B88C1F86C6A9C02C852A351472ABF2A260DF0F10F516F7296D9AEB66F776EAF3DAA9EC466A663CCB099B99",
                "name": "新疆直播频道",
                "title": "标题",
                "pictures": [],
                "type": 3
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
 * @api {get,post} /api/index/ott/column 安卓首页推荐栏目接口
 * @apiName ottIndexColumn
 * @apiGroup index
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} columnId 栏目id
 * @apiParam {String} appId 应用id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/index/ott/column?appId=58&columnId=61
 *
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONArray} column 推荐栏目数组对象
 * @apiSuccess (column){String} id 内容id
 * @apiSuccess (column){String} liveUrl 直播地址
 * @apiSuccess (column){String} name 栏目名称
 * @apiSuccess (column){String} title 栏目标题
 * @apiSuccess (column){String} templateCode 栏目模板编码 1-视频类栏目；2-图书类栏目 注：该字段可能为空
 * @apiSuccess (column){String} type 内容类型 1电影 2剧集总集 3直播 4活动 5图书 6-栏目
 * @apiSuccess (column){Integer} episodeCount 剧集总集数
 * @apiSuccess (column){Integer} isHot 是否热门 1-是；0-否
 * @apiSuccess (column){Integer} isNew 是否最新 1-是；0-否
 * @apiSuccess (column){Integer} isRecommend 是否推荐 1-是；0-否
 * @apiSuccess (column){String} score 评分
 * @apiSuccess (column){JSONArray} pictures 图片数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "column": [
            {
                "episodeCount": 1,
                "id": "75",
                "isHot": 0,
                "isNew": 0,
                "isRecommend": 0,
                "liveUrl": "",
                "name": "早教启蒙",
                "pictures": [],
                "score": "8",
                "templateCode": "2",
                "title": "",
                "type": 6
            },
            {
                "episodeCount": 1,
                "id": "76",
                "isHot": 0,
                "isNew": 0,
                "isRecommend": 0,
                "liveUrl": "",
                "name": "思维训练",
                "pictures": [],
                "score": "8",
                "templateCode": "2",
                "title": "",
                "type": 6
            },
            {
                "episodeCount": 1,
                "id": "77",
                "isHot": 0,
                "isNew": 0,
                "isRecommend": 0,
                "liveUrl": "",
                "name": "性格养成",
                "pictures": [],
                "score": "8",
                "templateCode": "2",
                "title": "",
                "type": 6
            },
            {
                "episodeCount": 1,
                "id": "78",
                "isHot": 0,
                "isNew": 0,
                "isRecommend": 0,
                "liveUrl": "",
                "name": "育儿宝典",
                "pictures": [],
                "score": "8",
                "templateCode": "2",
                "title": "",
                "type": 6
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
 * @api {get,post} /api/index/ott/content 安卓首页推荐内容接口
 * @apiName ottIndexContent
 * @apiGroup index
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} columnId 栏目id
 * @apiParam {String} appId 应用id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/index/ott/content?appId=58&columnId=61
 *
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONArray} content 推荐内容数组对象
 * @apiSuccess (content){String} id 内容id
 * @apiSuccess (content){String} liveUrl 直播地址
 * @apiSuccess (content){String} name 内容名称
 * @apiSuccess (content){String} title 内容标题
 * @apiSuccess (content){String} type 内容类型 1电影 2剧集总集 3直播 4活动 5图书 6-栏目
 * @apiSuccess (content){Integer} episodeCount 剧集总集数
 * @apiSuccess (content){Integer} isHot 是否热门 1-是；0-否
 * @apiSuccess (content){Integer} isNew 是否最新 1-是；0-否
 * @apiSuccess (content){Integer} isRecommend 是否推荐 1-是；0-否
 * @apiSuccess (content){String} score 评分
 * @apiSuccess (content){JSONArray} pictures 图片数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "content": [
            {
                "episodeCount": 1,
                "id": "6343886dfea911e8b6b2525400cd5344",
                "isHot": 1,
                "isNew": 0,
                "isRecommend": 1,
                "liveUrl": "",
                "name": "民的1911",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://img.evomedia.cn/cms/image/747c4bc0db0b4fc79cbd622f59053b2e.jpg"
                    }
                ],
                "score": "8",
                "title": "",
                "type": 1
            },
            {
                "episodeCount": 20,
                "id": "402881e467ccafc80167ccb2ec0d004e",
                "isHot": 0,
                "isNew": 0,
                "isRecommend": 0,
                "liveUrl": "",
                "name": "熊熊乐园第二季",
                "pictures": [
                    {
                        "type": 1,
                        "url": "http://img.evomedia.cn/cms/image/1547194211997.jpg"
                    },
                    {
                        "type": 2,
                        "url": "http://img.evomedia.cn/cms/image/eb47576ab1a84236a21e2f6a409ed80e.jpg"
                    }
                ],
                "score": "8",
                "title": "",
                "type": 2
            },
            {
                "episodeCount": 1,
                "id": "ff80808167ee4a010167f00e65e2005e",
                "isHot": 0,
                "isNew": 0,
                "isRecommend": 0,
                "liveUrl": "",
                "name": "愤怒的龙",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://img.evomedia.cn/cms/image/20181227221544663.jpg"
                    }
                ],
                "score": "8.0",
                "title": "",
                "type": 5
            },
            {
                "episodeCount": 1,
                "id": "8a9384776833488c0168335147cf0003",
                "isHot": 0,
                "isNew": 0,
                "isRecommend": 0,
                "liveUrl": "",
                "name": "雪豹集锦",
                "pictures": [
                    {
                        "type": 1,
                        "url": "http://img.evomedia.cn/cms/image/805f5b64a41b4ba891d65d025fb1c33b.jpg"
                    }
                ],
                "score": "8.0",
                "title": "",
                "type": 3
            },
            {
                "episodeCount": 1,
                "id": "6358b831fea911e8b6b2525400cd5344",
                "isHot": 0,
                "isNew": 0,
                "isRecommend": 0,
                "liveUrl": "",
                "name": "孔雀西南飞",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://img.evomedia.cn/cms/image/72bb9d07845f45e7bc9c0cbecccc2a75.jpg"
                    }
                ],
                "score": "8",
                "title": "",
                "type": 1
            }
        ]
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */
