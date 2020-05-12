// ===================================================ContentApi=================================================
/**
 * @apiDefine content 内容类接口
 */

/**
 * @api {get,post} /api/content/bookInfo 图书详情
 * @apiName bookInfo
 * @apiGroup content
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} bookId 图书id
 * @apiParam {Integer} width 二维码宽
 * @apiParam {Integer} height 二维码高
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/content/bookInfo?bookId=ff80808165173607016517ab491a117e&width=100&height=100
 *
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONObject} book 图书对象
 * @apiSuccess (book){String} ageTag 年龄标签
 * @apiSuccess (book){String} author 作者
 * @apiSuccess (book){String} clcClassify 中图分类法
 * @apiSuccess (book){String} engineer 主编
 * @apiSuccess (book){String} id 主键
 * @apiSuccess (book){String} info 简介
 * @apiSuccess (book){String} name 书名
 * @apiSuccess (book){String} number 书号
 * @apiSuccess (book){String} publishDate 发布日期
 * @apiSuccess (book){String} qrCode 二维码
 * @apiSuccess (book){String} qrCodeUrl 二维码地址
 * @apiSuccess (book){String} smallClassify 细分类别
 * @apiSuccess (book){String} suitGroup 适用群体
 * @apiSuccess (book){String} supplier 供应商
 * @apiSuccess (book){String} tags 分类标签
 * @apiSuccess (book){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "book": {
            "ageTag": "0",
            "author": "安娜·米尔波恩",
            "clcClassify": null,
            "engineer": null,
            "id": "ff80808165173607016517ab491a117e",
            "info": "☆看里面家族新成员，幼儿启蒙必备！\r\n☆专为低幼儿童打造的艺术童书。\r\n☆偷偷看里面，小手抠洞洞，猜猜里面是什么。\r\n☆揭开翻翻页，哇，好可爱的动物！\r\n☆在阅读游戏中学习知识，锻炼手眼协调性。\r\n☆荣获2014年学前教育实践金奖\r\n      经典《看里面》系列，更适合低幼宝宝认知的翻翻书 巧妙的采用了洞洞和翻翻2种形式，紧紧贴近宝宝的认知和生理需求。每个洞洞、翻翻后面都隐藏了让宝宝感到惊奇的东西。\r\n      圆洞的设计让孩子透过一个个小洞猜猜洞洞后面隐藏着什么 从部分猜整体 培养孩子的观察力和想象力。\r\n      翻翻页的设计犹如让孩子猜谜一般，揭晓答案的时刻给孩子带来更多的乐趣和成就感。",
            "name": "偷偷看里面：农场",
            "number": "9787541957024",
            "pictures": [],
            "publishDate": "2016-01-01",
            "qrCode": "iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAIAAAD/gAIDAAACVUlEQVR42u2cW67CMAxE2f+muQuo\nQPNwkvZy8lUh0oYjnHjGhtebIY8XCIAFLGABC1jAAgGwgAUsYAELWOr75HGdpd/5+9zvz7rOytYM\nrBOw9PdclxIsTgT3/ZX+cwFrF6xPX2M3cD7N1e/sXmdAgXUnWO6ysk1XWQOw/hcs9zhfcfAD62mw\n9MXpG78ezsodHpln/TQsPSjuc/0AbfjTsMZcjkKUuGnB4/2sn4Clh16zefcJQbYhAOscLD1R6ANT\n3551M2ckYIG1LAzd8MnMv1n7pTcIgbUSlh4+vYHnGof60w9v8MAKZad+hLuFicwyPGbRACsMw95+\nUT6kO0vHvTXPAtZAMDaF+KbQkNnNmywaYFWw9IN/v0Wjpy/AOgHLLXNlsna2cH+7Uhiw7ITTFUON\ndG/gbkodgDVQsMjaj7LN2zV/dFsRWOdgNdIkM2FceZRZ3sA6Dau3mzMJ4pqLjYQC1jlYfeow24rW\nCJpNtjKwBkaztWftllNPB9ZpWK6dkhU+dZndWJKHqzvAMtK8rOknswazI2Kr3AHWWPKZCY7MJsxS\nhxu1HAFroJzlmi3ZBtw3JAHrHrBcQdvYu70Ub36yAqwTeVa/zU8Zey9zJHoOWGdcBzdsmxKpmxCM\nNKIDa1kYZsVON+3oJZcbpMA6DatJXF2prD/dhbjcVgbWe/WfYLjXK0phfcM5sJ4Aq7GAGnk00ngE\nrNvAypqH3HJDL7OAdQ9YGdCs/Tv7IUKTUgDrBKxG7sxaNFmDydZSGLAYb2ABC1jAAhawGMACFrCA\nBSxgMcTxB4z0iBVou2lTAAAAAElFTkSuQmCC",
            "smallClassify": null,
            "suitGroup": null,
            "supplier": "未来出版社",
            "tags": "成长认知,立体书,思维训练,艺术启蒙"
        }
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */

/**
 * @api {get,post} /api/content/bookRecommend 图书推荐(全文检索)
 * @apiName bookRecommend
 * @apiGroup content
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String}  appId 应用id
 * @apiParam {String}  id 图书id
 * @apiParam {Integer} pageNum 子集分页页码
 * @apiParam {Integer} pageSize 子集每页数据数
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/content/bookRecommend?id=ff80808167ee4a010167f00e65e2005e&appId=1&pageNum=1&pageSize=10
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONArray} recommend 推荐数组对象
 * @apiSuccess (recommend){String} id 图书id
 * @apiSuccess (recommend){String} name 图书名称
 * @apiSuccess (recommend){Integer} type 内容类型 1电影 2剧集总集 3直播 4活动 5图书
 * @apiSuccess (recommend){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data":{
        "total":23,
        "recommend":[
            {
                "id":"ff80808167ee4a010167f00affa80057",
                "liveUrl":"",
                "name":"大猫老师的绘本作文课二年级",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221217847.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00b85330058",
                "liveUrl":"",
                "name":"大猫老师的绘本作文课一年级",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221246109.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00c783b005a",
                "liveUrl":"",
                "name":"第五个",
                "pictures":[
                    {
                        "type":1,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221350369.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00d5c57005c",
                "liveUrl":"",
                "name":"儿童时间管理训练手册",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221503048.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f009d7f50055",
                "liveUrl":"",
                "name":"从尿布到约会：家长指南之养育性健康的儿童",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221120879.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00e65e2005e",
                "liveUrl":"",
                "name":"愤怒的龙",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221544663.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00bfe010059",
                "liveUrl":"",
                "name":"等待",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221319151.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00ce418005b",
                "liveUrl":"",
                "name":"儿童身体安全教育",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221438416.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00dbf9d005d",
                "liveUrl":"",
                "name":"飞鼠传奇",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221528085.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00a6ac90056",
                "liveUrl":"",
                "name":"打造轻松整理的房间",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221145651.jpg"
                    }
                ],
                "type":5
            }
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
 * @api {get,post} /api/content/bookRecommendRandom 图书推荐(随机推荐)
 * @apiName bookRecommendRandom
 * @apiGroup content
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String}  appId 应用id
 * @apiParam {String}  id 图书id
 * @apiParam {Integer} dataSize 需要数据数
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/content/bookRecommendRandom?id=ff80808167ee4a010167f00e65e2005e&dataSize=10&appId=58
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONArray} recommend 推荐数组对象
 * @apiSuccess (recommend){String} id 图书id
 * @apiSuccess (recommend){String} name 图书名称
 * @apiSuccess (recommend){Integer} type 内容类型 1电影 2剧集总集 3直播 4活动 5图书
 * @apiSuccess (recommend){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data":{
        "total":23,
        "recommend":[
            {
                "id":"ff80808167ee4a010167f00affa80057",
                "liveUrl":"",
                "name":"大猫老师的绘本作文课二年级",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221217847.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00b85330058",
                "liveUrl":"",
                "name":"大猫老师的绘本作文课一年级",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221246109.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00c783b005a",
                "liveUrl":"",
                "name":"第五个",
                "pictures":[
                    {
                        "type":1,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221350369.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00d5c57005c",
                "liveUrl":"",
                "name":"儿童时间管理训练手册",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221503048.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f009d7f50055",
                "liveUrl":"",
                "name":"从尿布到约会：家长指南之养育性健康的儿童",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221120879.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00e65e2005e",
                "liveUrl":"",
                "name":"愤怒的龙",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221544663.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00bfe010059",
                "liveUrl":"",
                "name":"等待",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221319151.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00ce418005b",
                "liveUrl":"",
                "name":"儿童身体安全教育",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221438416.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00dbf9d005d",
                "liveUrl":"",
                "name":"飞鼠传奇",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221528085.jpg"
                    }
                ],
                "type":5
            },
            {
                "id":"ff80808167ee4a010167f00a6ac90056",
                "liveUrl":"",
                "name":"打造轻松整理的房间",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20181227221145651.jpg"
                    }
                ],
                "type":5
            }
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
 * @api {get,post} /api/content/info 电影详情
 * @apiName info
 * @apiGroup content
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} contentId 电影id
 * @apiParam {String} cardNo 智能卡号（非必填）
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/content/info?contentId=402881e467ccafc80167ccb4348a0077&cardNo=4264678666
 *
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONObject} user 用户类对象
 * @apiSuccess (user){Integer} duration 时间点 秒
 * @apiSuccess (user){Integer} number 播放集数
 * @apiSuccess (user){Integer} isCollect 是否收藏 1-是；0-否
 * @apiSuccess (user){Integer} isBlack 是否黑名单 1-是；0-否
 * @apiSuccess (data){JSONObject} content 内容对象
 * @apiSuccess (content){String} actor 演员
 * @apiSuccess (content){String} area 区域
 * @apiSuccess (content){String} classify 分类
 * @apiSuccess (content){String} director 导演
 * @apiSuccess (content){String} grade 评分
 * @apiSuccess (content){String} id 主键
 * @apiSuccess (content){String} info 简介
 * @apiSuccess (content){String} language 语言
 * @apiSuccess (content){String} name 名称
 * @apiSuccess (content){String} sort 集数
 * @apiSuccess (content){String} title 标题
 * @apiSuccess (content){String} year 年代
 * @apiSuccess (content){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "user": {
            "duration": 0,
            "number": 1,
            "isCollect": 0,
            "isBlack": 0
        },
        "content": {
            "actor": "",
            "area": "",
            "classify": "动画",
            "director": "",
            "grade": "8",
            "id": "402881e467ccafc80167ccb4348a0077",
            "info": "",
            "language": "",
            "name": "熊熊乐园第二季11",
            "pictures": [],
            "sort": 11,
            "title": "",
            "year": ""
        }
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */

/**
 * @api {get,post} /api/content/episode 剧集详情
 * @apiName episode
 * @apiGroup content
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} episodeId 剧集id
 * @apiParam {Integer} pageNum 子集分页页码
 * @apiParam {Integer} pageSize 子集每页数据数
 * @apiParam {String} cardNo 智能卡号（非必填）
 * @apiParam {String} appId 应用id（非必填）
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/content/episode?episodeId=402881e467ccafc80167ccb4348a0077&pageNum=1&pageSize=10&cardNo=4264678666&appId=1
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONObject} user 用户类对象
 * @apiSuccess (user){Integer} duration 时间点 秒
 * @apiSuccess (user){Integer} number 播放集数
 * @apiSuccess (user){Integer} isCollect 是否收藏 1-是；0-否
 * @apiSuccess (user){Integer} isBlack 是否黑名单 1-是；0-否
 * @apiSuccess (data){JSONObject} episode 剧集对象
 * @apiSuccess (episode){String} actor 演员
 * @apiSuccess (episode){String} area 区域
 * @apiSuccess (episode){String} classify 分类
 * @apiSuccess (episode){String} director 导演
 * @apiSuccess (episode){String} grade 评分
 * @apiSuccess (episode){String} id 主键
 * @apiSuccess (episode){String} info 简介
 * @apiSuccess (episode){String} language 语言
 * @apiSuccess (episode){String} name 名称
 * @apiSuccess (episode){String} sort 集数
 * @apiSuccess (episode){String} title 标题
 * @apiSuccess (episode){String} year 年代
 * @apiSuccess (episode){Integer} total 实际总集数
 * @apiSuccess (episode){Integer} count 应有总集数
 * @apiSuccess (episode){Integer} freeNum 免费集数
 * @apiSuccess (episode){boolean} isLimitFree 是否限免 false-否；true-是
 * @apiSuccess (episode){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess (episode){JSONArray} childs 剧集子集对象
 * @apiSuccess (childs){Integer} number 子集集数
 * @apiSuccess (childs){String} id 子集id
 * @apiSuccess (childs){String} name 子集名称
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "user": {
            "duration": 0,
            "number": 1,
            "isCollect": 0,
            "isBlack": 0
        },
        "episode": {
            "actor": "",
            "area": "",
            "classify": "动画",
            "director": "",
            "grade": "8",
            "id": "402881e467ccafc80167ccb2ec0d004e",
            "info": "",
            "language": "",
            "name": "熊熊乐园第二季",
            "pictures": [],
            "sort": 11,
            "title": "",
            "year": "",
            "total":20,
            "count":20,
            "childs": [
                {
                    "id": "402881e467ccafc80167ccb3e212004f",
                    "name": "熊熊乐园第二季01",
                    "number": 1
                },
                {
                    "id": "402881e467ccafc80167ccb3ea400053",
                    "name": "熊熊乐园第二季02",
                    "number": 2
                },
                {
                    "id": "402881e467ccafc80167ccb415510057",
                    "name": "熊熊乐园第二季03",
                    "number": 3
                },
                {
                    "id": "402881e467ccafc80167ccb41944005b",
                    "name": "熊熊乐园第二季04",
                    "number": 4
                },
                {
                    "id": "402881e467ccafc80167ccb41cf3005f",
                    "name": "熊熊乐园第二季05",
                    "number": 5
                },
                {
                    "id": "402881e467ccafc80167ccb420840063",
                    "name": "熊熊乐园第二季06",
                    "number": 6
                },
                {
                    "id": "402881e467ccafc80167ccb4249c0067",
                    "name": "熊熊乐园第二季07",
                    "number": 7
                },
                {
                    "id": "402881e467ccafc80167ccb42802006b",
                    "name": "熊熊乐园第二季08",
                    "number": 8
                },
                {
                    "id": "402881e467ccafc80167ccb42dcb006f",
                    "name": "熊熊乐园第二季09",
                    "number": 9
                },
                {
                    "id": "402881e467ccafc80167ccb431460073",
                    "name": "熊熊乐园第二季10",
                    "number": 10
                }
            ]
        }
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */

/**
 * @api {get,post} /api/content/episodeNoPage 剧集详情(子集不分页)
 * @apiName episodeNoPage
 * @apiGroup content
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} episodeId 剧集id
 * @apiParam {String} cardNo 智能卡号（非必填）
 * @apiParam {String} appId 应用id（非必填）
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/content/episodeNoPage?episodeId=402881e467ccafc80167ccb4348a0077&cardNo=4264678666&appId
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONObject} user 用户类对象
 * @apiSuccess (user){Integer} duration 时间点 秒
 * @apiSuccess (user){Integer} number 播放集数
 * @apiSuccess (user){Integer} isCollect 是否收藏 1-是；0-否
 * @apiSuccess (user){Integer} isBlack 是否黑名单 1-是；0-否
 * @apiSuccess (data){JSONObject} episode 剧集对象
 * @apiSuccess (episode){String} actor 演员
 * @apiSuccess (episode){String} area 区域
 * @apiSuccess (episode){String} classify 分类
 * @apiSuccess (episode){String} director 导演
 * @apiSuccess (episode){String} grade 评分
 * @apiSuccess (episode){String} id 主键
 * @apiSuccess (episode){String} info 简介
 * @apiSuccess (episode){String} language 语言
 * @apiSuccess (episode){String} name 名称
 * @apiSuccess (episode){String} sort 集数
 * @apiSuccess (episode){String} title 标题
 * @apiSuccess (episode){String} year 年代
 * @apiSuccess (episode){Integer} total 实际总集数
 * @apiSuccess (episode){Integer} count 应有总集数
 * @apiSuccess (episode){Integer} freeNum 免费集数
 * @apiSuccess (episode){boolean} isLimitFree 是否限免 false-否；true-是
 * @apiSuccess (episode){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess (episode){JSONArray} childs 剧集子集对象
 * @apiSuccess (childs){Integer} number 子集集数
 * @apiSuccess (childs){String} id 子集id
 * @apiSuccess (childs){String} name 子集名称
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "episode": {
            "actor": "",
            "area": "",
            "childs": [
                {
                    "id": "402881e467ccafc80167ccb3e212004f",
                    "name": "熊熊乐园第二季01",
                    "number": 1
                },
                {
                    "id": "402881e467ccafc80167ccb3ea400053",
                    "name": "熊熊乐园第二季02",
                    "number": 2
                },
                {
                    "id": "402881e467ccafc80167ccb415510057",
                    "name": "熊熊乐园第二季03",
                    "number": 3
                },
                {
                    "id": "402881e467ccafc80167ccb41944005b",
                    "name": "熊熊乐园第二季04",
                    "number": 4
                },
                {
                    "id": "402881e467ccafc80167ccb41cf3005f",
                    "name": "熊熊乐园第二季05",
                    "number": 5
                },
                {
                    "id": "402881e467ccafc80167ccb420840063",
                    "name": "熊熊乐园第二季06",
                    "number": 6
                },
                {
                    "id": "402881e467ccafc80167ccb4249c0067",
                    "name": "熊熊乐园第二季07",
                    "number": 7
                },
                {
                    "id": "402881e467ccafc80167ccb42802006b",
                    "name": "熊熊乐园第二季08",
                    "number": 8
                },
                {
                    "id": "402881e467ccafc80167ccb42dcb006f",
                    "name": "熊熊乐园第二季09",
                    "number": 9
                },
                {
                    "id": "402881e467ccafc80167ccb431460073",
                    "name": "熊熊乐园第二季10",
                    "number": 10
                },
                {
                    "id": "402881e467ccafc80167ccb4348a0077",
                    "name": "熊熊乐园第二季11",
                    "number": 11
                },
                {
                    "id": "402881e467ccafc80167ccb4379e007b",
                    "name": "熊熊乐园第二季12",
                    "number": 12
                },
                {
                    "id": "402881e467ccafc80167ccb43b05007f",
                    "name": "熊熊乐园第二季13",
                    "number": 13
                },
                {
                    "id": "402881e467ccafc80167ccb43e420083",
                    "name": "熊熊乐园第二季14",
                    "number": 14
                },
                {
                    "id": "402881e467ccafc80167ccb440d50087",
                    "name": "熊熊乐园第二季15",
                    "number": 15
                },
                {
                    "id": "402881e467ccafc80167ccb443f8008b",
                    "name": "熊熊乐园第二季16",
                    "number": 16
                },
                {
                    "id": "402881e467ccafc80167ccb446d6008f",
                    "name": "熊熊乐园第二季17",
                    "number": 17
                },
                {
                    "id": "402881e467ccafc80167ccb449cb0093",
                    "name": "熊熊乐园第二季18",
                    "number": 18
                },
                {
                    "id": "402881e467ccafc80167ccb44e640097",
                    "name": "熊熊乐园第二季19",
                    "number": 19
                },
                {
                    "id": "402881e467ccafc80167ccb4550b009b",
                    "name": "熊熊乐园第二季20",
                    "number": 20
                }
            ],
            "classify": "动画",
            "count": 20,
            "director": "",
            "grade": "8",
            "id": "402881e467ccafc80167ccb2ec0d004e",
            "info": "",
            "language": "",
            "name": "熊熊乐园第二季",
            "pictures": [
                {
                    "type": 1,
                    "url": "null1547194211997.jpg"
                },
                {
                    "type": 2,
                    "url": "nulleb47576ab1a84236a21e2f6a409ed80e.jpg"
                }
            ],
            "sort": 1,
            "title": "",
            "total": 20,
            "year": "2018"
        },
        "user": {
            "duration": 0,
            "number": 1,
            "isBlack": 1,
            "isCollect": 1
        }
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */

/**
 * @api {get,post} /api/content/recommend 内容推荐(全文检索)
 * @apiName recommend
 * @apiGroup content
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String}  appId 应用id
 * @apiParam {String}  id 内容id
 * @apiParam {Integer} pageNum 子集分页页码
 * @apiParam {Integer} pageSize 子集每页数据数
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/content/recommend?id=402881e467ccafc80167ccb4348a0077&pageNum=1&pageSize=10&appId=1
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONArray} recommend 推荐数组对象
 * @apiSuccess (recommend){String} id 内容id
 * @apiSuccess (recommend){String} name 内容名称
 * @apiSuccess (recommend){Integer} type 内容类型 1电影 2剧集总集 3直播 4活动 5图书
 * @apiSuccess (recommend){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "recommend": [
            {
                "id": "402881e467ccafc80167ccb6fb950201",
                "name": "疯狂十万",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180427153551098.jpg"
                    },
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180521182839221.jpg"
                    }
                ],
                "type": 2
            },
            {
                "id": "402881e467ccafc80167ccb6db16018c",
                "name": "逗逗虎美食小当家",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180408152832569.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180408152834202.jpg"
                    }
                ],
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
 * @api {get,post} /api/content/recommendRandom 内容推荐(随机推荐)
 * @apiName recommendRandom
 * @apiGroup content
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String}  appId 应用id
 * @apiParam {String}  id 内容id
 * @apiParam {Integer} dataSize 需要数据数
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/content/recommendRandom?id=ff8080816975d882016976502633007d&dataSize=10&appId=58
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONArray} recommend 推荐数组对象
 * @apiSuccess (recommend){String} id 内容id
 * @apiSuccess (recommend){String} name 内容名称
 * @apiSuccess (recommend){Integer} type 内容类型 1电影 2剧集总集 3直播 4活动 5图书
 * @apiSuccess (recommend){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "recommend": [
            {
                "id": "402881e467ccafc80167ccb6fb950201",
                "name": "疯狂十万",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180427153551098.jpg"
                    },
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180521182839221.jpg"
                    }
                ],
                "type": 2
            },
            {
                "id": "402881e467ccafc80167ccb6db16018c",
                "name": "逗逗虎美食小当家",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180408152832569.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180408152834202.jpg"
                    }
                ],
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
 * @api {get,post} /api/content/recommendByColumnId 内容推荐(栏目id进行推荐)
 * @apiName recommendByColumnId
 * @apiGroup content
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String}  columnId 栏目id
 * @apiParam {Integer} pageNum 子集分页页码
 * @apiParam {Integer} pageSize 子集每页数据数
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/content/recommendByColumnId?columnId=31&pageSize=2&pageNum=1
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONArray} recommendContents 推荐数组对象
 * @apiSuccess (recommendContents){String} id 内容id
 * @apiSuccess (recommendContents){String} name 内容名称
 * @apiSuccess (recommendContents){Integer} type 内容类型 1电影 2剧集总集 3直播 4活动 5图书
 * @apiSuccess (recommendContents){String} live 直播地址 当tppe=3时返回
 * @apiSuccess (recommendContents){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "recommendContents": [
            {
                "id": "ff808081622003790163d975b898032f",
                "liveUrl": "",
                "name": "剪刀石头布（第一季）",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110045896.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110226608.jpg"
                    }
                ],
                "type": 2
            },
            {
                "id": "6358b831fea911e8b6b2525400cd5344",
                "liveUrl": "",
                "name": "孔雀西南飞",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/72bb9d07845f45e7bc9c0cbecccc2a75.jpg"
                    }
                ],
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

/**
 * @api {get,post} /api/content/search 内容搜索(全文检索)
 * @apiName search
 * @apiGroup content
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String}  appId 应用id
 * @apiParam {String}  keyword 搜索值
 * @apiParam {Integer} pageNum 子集分页页码
 * @apiParam {Integer} pageSize 子集每页数据数
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/content/search?keyword=xx&pageNum=1&pageSize=10&appId=1
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONArray} contents 搜索数组对象
 * @apiSuccess (data){Long} total 总条数
 * @apiSuccess (contents){String} id 内容id
 * @apiSuccess (contents){String} name 内容名称
 * @apiSuccess (contents){Integer} type 内容类型 1电影 2剧集总集 3剧集子集 4专题 5图书
 * @apiSuccess (contents){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "contents": [
            {
                "id": "402881e467ccafc80167ccb6fb950201",
                "name": "疯狂十万",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180427153551098.jpg"
                    },
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180521182839221.jpg"
                    }
                ],
                "type": 2
            },
            {
                "id": "402881e467ccafc80167ccb6db16018c",
                "name": "逗逗虎美食小当家",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180408152832569.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180408152834202.jpg"
                    }
                ],
                "type": 2
            }
        ],
        "total":2
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */

/**
 * @api {get,post} /api/content/videos 内容下视频资源列表
 * @apiName videos
 * @apiGroup content
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String}  contentId 内容id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/content/videos?contentId=40289f15679849f101679853b2b90412
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){JSONArray} videos 视频数组对象
 * @apiSuccess (videos){String} url 视频地址
 * @apiSuccess (videos){Integer} definition 清晰度  1:4K 2:蓝光 3:超清 4:高清 5:标清 6:手机
 * @apiSuccess (videos){Integer} type 视频类型 1:2D【平面】 2:3D【立体】 3:VR【全景】
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 *{
    "data": {
        "videos": [
            {
                "definition": 4,
                "type": 1,
                "url": "rtsp://10.255.16.109:1554/xinjiang_sl_sd/TS_5800/20180711/298502_20180711000409.ts?Contentid=MOVE1520180711000685&isHD=1&isIpqam=0&token=3EB1352BD5CFC7C896F64382F8BE28242CA7EDB728B3FE0FC1DA80A095B1F582F770B307161E6A067FB25ABC66A0438C675E092142B5CD7F4C0B2759C61DA9434635B215CDAF260485BD597D3BA712ABAA31E0018BFCFC9FD548604BE35A8E299AA42F4298E1BBBCC3888A53A47811936E6D5119517FB1275C21E55A9DF60A01CC5E594DE44EE37CDFAF7084EA6B9C52B315525BA4108309D0151E60F9EC659A89A3A823F01D7D5D1FCD3D021B1C4E87161A75B9E7"
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
 * @api {get,post} /api/content/outRecommend 退出推荐
 * @apiName outRecommend
 * @apiGroup content
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String}  columnId 栏目id
 * @apiParam {Integer} pageNum 子集分页页码
 * @apiParam {Integer} pageSize 子集每页数据数
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/content/outRecommend?columnId=79&pageNum=1&pageSize=1
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){Integer} total 总数
 * @apiSuccess (data){String} name 栏目名称
 * @apiSuccess (data){JSONArray} contents 内容数组对象
 * @apiSuccess (contents){String} id 内容id
 * @apiSuccess (contents){String} name 内容名称
 * @apiSuccess (contents){Integer} type 内容类型 1电影 2剧集总集 3剧集子集 4专题 5图书
 * @apiSuccess (contents){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 *{
    "data":{
        "total":1,
        "contents":[
            {
                "episodeCount":23,
                "id":"6826be43fea911e8b6b2525400cd5344",
                "isHot":0,
                "isNew":0,
                "isRecommend":0,
                "liveUrl":"",
                "name":"YOYO绘本屋",
                "nowEpisodeCount":0,
                "pictures":[
                    {
                        "type":2,
                        "url":"http://img.evomedia.cn/cms/image/20180926163308656.jpg"
                    }
                ],
                "score":"8",
                "templateCode":"",
                "title":"",
                "type":2
            }
        ],
        "name":"退出推荐"
    },
    "extra":{

    },
    "message":"成功！",
    "status":"OK"
}
 *
 */

/**
 * @api {get,post} /api/content/listByColumnId 栏目下内容列表
 * @apiName listByColumnId
 * @apiGroup content
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String}  columnId 栏目id
 * @apiParam {Integer} pageNum 子集分页页码
 * @apiParam {Integer} pageSize 子集每页数据数
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/content/listByColumnId?columnId=44&pageNum=1&pageSize=10
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){Integer} total 总数
 * @apiSuccess (data){String} name 栏目名称
 * @apiSuccess (data){JSONArray} contents 内容数组对象
 * @apiSuccess (contents){String} id 内容id
 * @apiSuccess (contents){String} name 内容名称
 * @apiSuccess (contents){Integer} type 内容类型 1电影 2剧集总集 3剧集子集 4专题 5图书
 * @apiSuccess (contents){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 *{
    "data": {
        "total": 9,
        "contents": [
            {
                "id": "ff808081622003790163fc611e622139",
                "name": "嘟拉十万个为什么",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110044974.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110225729.jpg"
                    }
                ],
                "type": 2
            },
            {
                "id": "ff808081622003790163fc6450252144",
                "name": "嘟拉睡前故事",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110045088.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110225820.jpg"
                    }
                ],
                "type": 2
            },
            {
                "id": "ff808081622003790163fc6c61d6215d",
                "name": "嘟拉益智故事",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110045312.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110226043.jpg"
                    }
                ],
                "type": 2
            },
            {
                "id": "ff808081622003790163f71385a41b13",
                "name": "宝贝生活日记（第一季）",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110043716.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110224540.jpg"
                    }
                ],
                "type": 2
            },
            {
                "id": "ff808081622003790163fc54740e20f6",
                "name": "嘟拉3D儿歌",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110044616.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110225383.jpg"
                    }
                ],
                "type": 2
            },
            {
                "id": "ff808081622003790163fc5ecd0b2125",
                "name": "嘟拉成语故事",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110044871.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110225616.jpg"
                    }
                ],
                "type": 2
            },
            {
                "id": "ff808081622003790163ed899a7b10ab",
                "name": "MOMO欢乐谷（第七季）",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110042979.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110223744.jpg"
                    }
                ],
                "type": 2
            },
            {
                "id": "ff808081622003790163ed8dae4f10ad",
                "name": "MOMO欢乐谷（第六季）",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180605145205811.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180605145402474.jpg"
                    }
                ],
                "type": 2
            },
            {
                "id": "ff808081622003790163dd6805b107f3",
                "name": "MOMO欢乐谷（第四季）",
                "pictures": [
                    {
                        "type": 2,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110043251.jpg"
                    },
                    {
                        "type": 1,
                        "url": "http://192.168.10.102:8080/cms_sp_picture/20180606110223974.jpg"
                    }
                ],
                "type": 2
            }
        ],
        "name": "偶像剧场"
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */

/**
 * @api {get,post} /api/content/likeLook 获取用户播放次数最多的 内容列表-热播
 * @apiName likeLook
 * @apiGroup content
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String}  appId 应用id
 * @apiParam {Integer} pageNum 页码
 * @apiParam {Integer} pageSize 每页数据数
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/content/likeLook?appId=1&pageNum=1&pageSize=10
 * @apiSuccess {JSONObject} data  数据对象
 * @apiSuccess (data){Integer} total 总数
 * @apiSuccess (data){JSONArray} contents 内容数组对象
 * @apiSuccess (contents){String} id 内容id
 * @apiSuccess (contents){String} name 内容名称
 * @apiSuccess (contents){Integer} type 内容类型 1电影 2剧集总集 3直播 4活动 5图书
 * @apiSuccess (contents){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 *{
    "data":{
        "total":1,
        "contents":[
            {
                "id":"ff80808162200379016416c7cee83553",
                "name":"超神游戏（第一季）",
                "pictures":[
                    {
                        "type":2,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20180606110043947.jpg"
                    },
                    {
                        "type":1,
                        "url":"http://192.168.10.102:8080/cms_sp_picture/20180606110224734.jpg"
                    }
                ],
                "type":2
            }
        ]
    },
    "extra":{

    },
    "message":"成功！",
    "status":"OK"
}
 *
 */