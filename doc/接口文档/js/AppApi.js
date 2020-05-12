// ===================================================AppApi=================================================
/**
 * @apiDefine app 应用类接口
 */

/**
 * @api {get,post} /api/app/info 应用详情
 * @apiName info
 * @apiGroup app
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} packageName 应用包名
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/app/info?packageName=com.evo.qinzi.tv.lj
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){JSONObject} app  应用对象
 * @apiSuccess (app){String} info 应用描述
 * @apiSuccess (app){String} name 应用名称
 * @apiSuccess (app){String} packageName 包名
 * @apiSuccess (app){String} publishDate 发布日期
 * @apiSuccess (app){String} versionCode 版本标识
 * @apiSuccess (app){String} versionName 版本名称
 * @apiSuccess (app){JSONArray} versionInfos 版本描述数组
 * @apiSuccess (versionInfos){String} versionInfo 版本描述
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 *{
    "data": {
        "app": {
            "info": "关注儿童，亲子教育，影视，图书",
            "name": "亲多多",
            "packageName": "com.evo.qinzi.tv.lj",
            "publishDate": "2019-03-04",
            "versionCode": "6",
            "versionInfos": [
                {
                    "versionInfo": "1. 解决bug"
                },
                {
                    "versionInfo": "2. 解决bug2"
                },
                {
                    "versionInfo": "3. 解决bug3"
                }
            ],
            "versionName": "v1.2.3"
        }
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */
