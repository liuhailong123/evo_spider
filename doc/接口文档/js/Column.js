// ===================================================ColumnsApi=================================================
/**
 * @apiDefine column 栏目类接口
 */

/**
 * @api {get,post} /api/column/childs 目录id获取子目录
 * @apiName childs
 * @apiGroup column
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} id 栏目id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/column/childs?id=31
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){Stirng} name  栏目名称
 * @apiSuccess (data){boolean} hasChild  true
 * @apiSuccess (data){JSONArray} columns  目录数组对象
 * @apiSuccess (columns){String} classifyTags 分类标签
 * @apiSuccess (columns){String} id 目录id
 * @apiSuccess (columns){String} name 目录名称
 * @apiSuccess (columns){Integer} sort 排序
 * @apiSuccess (columns){Integer} type 栏目类型 1站点 2应用 3栏目 4专题 5剧集
 * @apiSuccess (columns){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "columns": [
            {
                "classifyTags": "",
                "id": "41",
                "name": "最新上线",
                "pictures": [],
                "sort": 1,
                "type": 3
            },
            {
                "classifyTags": "",
                "id": "42",
                "name": "热门推荐",
                "pictures": [],
                "sort": 2,
                "type": 3
            },
            {
                "classifyTags": "",
                "id": "43",
                "name": "动画电影",
                "pictures": [],
                "sort": 3,
                "type": 3
            },
            {
                "classifyTags": "",
                "id": "44",
                "name": "偶像剧场",
                "pictures": [],
                "sort": 4,
                "type": 3
            },
            {
                "classifyTags": "",
                "id": "45",
                "name": "男孩爱看",
                "pictures": [],
                "sort": 5,
                "type": 3
            },
            {
                "classifyTags": "",
                "id": "46",
                "name": "女孩爱看",
                "pictures": [],
                "sort": 6,
                "type": 3
            }
        ],
        "hasChild": true,
        "name": "少儿动画"
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */

/**
 * @api {get,post} /api/column/recommendByColumnId 栏目推荐(栏目id进行推荐)
 * @apiName recommendByColumnId
 * @apiGroup column
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} id 栏目id
 * @apiParam {Integer} pageSize 每页数据数
 * @apiParam {Integer} pageNum 页码
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/column/recommendByColumnId?columnId=31&pageSize=10&pageNum=1
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){JSONArray} recommendColumns  目录数组对象
 * @apiSuccess (recommendColumns){String} classifyTags 分类标签
 * @apiSuccess (recommendColumns){String} id 目录id
 * @apiSuccess (recommendColumns){String} name 目录名称
 * @apiSuccess (recommendColumns){Integer} sort 排序
 * @apiSuccess (recommendColumns){Integer} type 栏目类型 1站点 2应用 3栏目 4专题 5剧集
 * @apiSuccess (recommendColumns){JSONArray} pictures 海报数组对象
 * @apiSuccess (pictures){Integer} type 图片类型 1-横版；2-竖版
 * @apiSuccess (pictures){String} url 图片地址
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "recommendColumns": [
            {
                "classifyTags": "",
                "id": "41",
                "name": "最新上线",
                "pictures": [],
                "sort": 1,
                "type": 3
            },
            {
                "classifyTags": "",
                "id": "43",
                "name": "动画电影",
                "pictures": [],
                "sort": 3,
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
