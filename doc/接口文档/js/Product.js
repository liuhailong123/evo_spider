// ===================================================ProductApi=================================================
/**
 * @apiDefine product 支付产品类接口
 */

/**
 * @api {get,post} /api/product/setMeallist 可订购产品列表
 * @apiName setMeallist
 * @apiGroup product
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} appId 应用id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/product/setMeallist?appId=1
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){JsonArray} products 产品数组对象
 * @apiSuccess (products){String} code 产品编码
 * @apiSuccess (products){String} currentPrice 优惠价
 * @apiSuccess (products){String} info 产品信息
 * @apiSuccess (products){String} name 产品名称
 * @apiSuccess (products){String} originalPrice 原价
 * @apiSuccess (products){String} pictureUrl 产品海报
 * @apiSuccess (products){JSONArray} payTypes 支付方式数组对象
 * @apiSuccess (payTypes){String} name 支付方式名称
 * @apiSuccess (payTypes){Integer} payType 支付方式类型 1-微信；2-支付宝；3-银联；4-boss
 * @apiSuccess (products){JSONArray} serverInfos 服务数组对象
 * @apiSuccess (serverInfos){Integer} ruleCount 规则次数 与规则单位组合表示具体业务意义
 * @apiSuccess (serverInfos){String} ruleInfo 规则说明
 * @apiSuccess (serverInfos){String} ruleNmae 规则名称
 * @apiSuccess (serverInfos){String} ruleUnit 规则单位 0-分钟;1-小时;2-天;3-月;4-年;5-次;6-本;
 * @apiSuccess (serverInfos){String} serverCode 服务编码 10001-视频观看服务；10002-图书借阅服务
 * @apiSuccess (serverInfos){String} serverInfo 服务说明
 * @apiSuccess (serverInfos){String} serverName 服务名称
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "products": [
            {
                "code": "100002",
                "currentPrice": "68800",
                "id": "40288ae6673f351501673fa55c8b0010",
                "info": "",
                "name": "亲多多包季套餐",
                "originalPrice": "79800",
                "payTypes": [],
                "pictureUrl": "",
                "serverInfos": []
            },
            {
                "code": "100003",
                "currentPrice": "88800",
                "id": "40288ae6673f351501673f81a9710002",
                "info": "",
                "name": "亲多多包年套餐",
                "originalPrice": "128800",
                "payTypes": [],
                "pictureUrl": "",
                "serverInfos": [
                    {
                        "ruleCount": 1,
                        "ruleInfo": "",
                        "ruleName": "包年",
                        "ruleUnit": "4",
                        "serverCode": "10001",
                        "serverInfo": "",
                        "serverName": "视频观看"
                    },
                    {
                        "ruleCount": 24,
                        "ruleInfo": "",
                        "ruleName": "图书借阅24次",
                        "ruleUnit": "5",
                        "serverCode": "10002",
                        "serverInfo": "",
                        "serverName": "图示借阅"
                    }
                ]
            },
            {
                "code": "100001",
                "currentPrice": "0",
                "id": "678a284066e3d49d0166e3d789e30004",
                "info": "",
                "name": "亲多多包月套餐",
                "originalPrice": "0",
                "payTypes": [
                    {
                        "name": "新疆省网-微信",
                        "payType": 1
                    },
                    {
                        "name": "新疆省网-boss",
                        "payType": 4
                    },
                    {
                        "name": "新疆省网-支付宝",
                        "payType": 2
                    }
                ],
                "pictureUrl": "http://192.168.10.102:8080/cms_sp_picture/20180120124043002.jpg",
                "serverInfos": [
                    {
                        "ruleCount": 2,
                        "ruleInfo": "",
                        "ruleName": "图书借阅2次",
                        "ruleUnit": "5",
                        "serverCode": "10002",
                        "serverInfo": "",
                        "serverName": "图示借阅"
                    },
                    {
                        "ruleCount": 1,
                        "ruleInfo": "",
                        "ruleName": "包月",
                        "ruleUnit": "3",
                        "serverCode": "10001",
                        "serverInfo": "",
                        "serverName": "视频观看"
                    }
                ]
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
 * @api {get,post} /api/product/detail 产品详情
 * @apiName detail
 * @apiGroup product
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} id 产品id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/product/detail?id=40288ae6673f351501673fa55c8b0010
 *
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){JsonObject} product 产品对象
 * @apiSuccess (product){String} code 产品编码
 * @apiSuccess (product){String} currentPrice 优惠价
 * @apiSuccess (product){String} info 产品信息
 * @apiSuccess (product){String} name 产品名称
 * @apiSuccess (product){String} originalPrice 原价
 * @apiSuccess (product){String} pictureUrl 产品海报
 * @apiSuccess (product){JSONArray} payTypes 支付方式数组对象
 * @apiSuccess (payTypes){String} name 支付方式名称
 * @apiSuccess (payTypes){Integer} payType 支付方式类型 1-微信；2-支付宝；3-银联；4-boss
 * @apiSuccess (product){JSONArray} serverInfos 服务数组对象
 * @apiSuccess (serverInfos){Integer} ruleCount 规则次数
 * @apiSuccess (serverInfos){String} ruleInfo 规则说明
 * @apiSuccess (serverInfos){String} ruleNmae 规则名称
 * @apiSuccess (serverInfos){String} ruleUnit 规则单位
 * @apiSuccess (serverInfos){String} serverCode 服务编码 10001-视频观看服务；10002-图书借阅服务
 * @apiSuccess (serverInfos){String} serverInfo 服务说明
 * @apiSuccess (serverInfos){String} serverName 服务名称
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 * {
    "data": {
        "product": {
            "code": "100001",
            "currentPrice": "0",
            "id": "678a284066e3d49d0166e3d789e30004",
            "info": "",
            "name": "亲多多包月套餐",
            "originalPrice": "0",
            "payTypes": [
                {
                    "name": "新疆省网-微信",
                    "payType": 1
                },
                {
                    "name": "新疆省网-boss",
                    "payType": 4
                },
                {
                    "name": "新疆省网-支付宝",
                    "payType": 2
                }
            ],
            "pictureUrl": "http://192.168.10.102:8080/cms_sp_picture/20180120124043002.jpg",
            "serverInfos": [
                {
                    "ruleCount": 2,
                    "ruleInfo": "",
                    "ruleName": "图书借阅2次",
                    "ruleUnit": "5",
                    "serverCode": "10002",
                    "serverInfo": "",
                    "serverName": "图示借阅"
                },
                {
                    "ruleCount": 1,
                    "ruleInfo": "",
                    "ruleName": "包月",
                    "ruleUnit": "3",
                    "serverCode": "10001",
                    "serverInfo": "",
                    "serverName": "视频观看"
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
