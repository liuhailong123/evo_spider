// ===================================================ActiveApi=================================================
/**
 * @apiDefine active 活动类接口
 */


/**
 * @api {get,post} /api/active/list 活动列表
 * @apiName list
 * @apiGroup active
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} columnId 栏目id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/active/list?columnId=31
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){JSONArray} actives  活动数组对象
 * @apiSuccess (actives){String} author 作者
 * @apiSuccess (actives){String} id id
 * @apiSuccess (actives){String} imgUrl 海报地址
 * @apiSuccess (actives){String} info 信息
 * @apiSuccess (actives){String} name 名称
 * @apiSuccess (actives){String} sponsor 主办方
 * @apiSuccess (actives){String} summary 简介
 * @apiSuccess (actives){String} endTime 失效时间
 * @apiSuccess (actives){String} startTime 生效时间
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 *{
    "data":{
        "actives":[
            {
                "author":"",
                "endTime":"2019-01-31 12:19:26",
                "id":"ff80808167de11750167ede78f5c0023",
                "imgUrl":"http://192.168.10.102:8080/cms_sp_picture/20181227223442668.jpg",
                "info":"",
                "name":"亲多多携手雪豹冬令营邀您共享畅滑体验",
                "sponsor":"亲多多、雪豹冬令营",
                "startTime":"2019-01-01 12:19:19",
                "summary":"新疆广电网络“亲多多”亲子欢乐畅滑体验"
            },
            {
                "author":"无",
                "endTime":"2019-01-31 22:38:25",
                "id":"ff80808167ee4a010167f01a43cc0087",
                "imgUrl":"",
                "info":"",
                "name":"国际正版精装绘本免费看",
                "sponsor":"亲多多",
                "startTime":"2018-12-27 22:38:18",
                "summary":"仅100套！国际正版精装绘本10本免费看！顺丰包邮送到家！"
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
 * @api {get,post} /api/active/info 活动详情
 * @apiName info
 * @apiGroup active
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} columnId 栏目id
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/active/info?id=e581284066f359420166f360b81e0000
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){JSONObject} info  活动数组对象
 * @apiSuccess (info){String} author 作者
 * @apiSuccess (info){String} id id
 * @apiSuccess (info){String} imgUrl 海报地址
 * @apiSuccess (info){String} info 信息
 * @apiSuccess (info){String} name 名称
 * @apiSuccess (info){String} publishTime 发布时间
 * @apiSuccess (info){String} sponsor 主办方
 * @apiSuccess (info){String} synopsis 简介
 * @apiSuccess (info){String} unValidTime 失效时间
 * @apiSuccess (info){String} validTime 生效时间
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 *{
    "data": {
        "info": {
            "author": "作者xx",
            "id": "e581284066f359420166f360b81e0000",
            "imgUrl": "",
            "info": "%3Chtml%3E%3Chead%3E%3Cstyle+type%3D%22text%2Fcss%22%3E%0D%0A%3Cmeta+http-equiv%3D%22Content-Type%22+content%3D%22text%2Fhtml%3B+charset%3Dutf-8%22+%2F%3E%0D%0A%40font-face+%7B%0D%0A++++font-family%3A+%27QDD%27%3B%0D%0A++++src%3A+url%28%27127.0.0.1%3A9030%2Fsp%2Fstatic%2Fassets%2Ffonts%2Ffont.ttf%27%29+format%28%27truetype%27%29%3B%0D%0A++++font-weight%3A+100%3B%0D%0A++++font-style%3A+normal%3B%0D%0A%7D%0D%0A%3C%2Fstyle%3E%0D%0A+%3C%2Fhead%3E%3Cbody+background%3D%27%27%3E%3Cblockquote+style%3D%22margin%3A+0+0+0+40px%3B+border%3A+none%3B+padding%3A+0px%3B%22%3E%3Cdiv%3E%3Cfont+size%3D%225%22+face%3D%22Comic+Sans+MS%22+color%3D%22%23f83a22%22%3Ebihlbbjiilnlnjbilbb%3C%2Ffont%3E%3C%2Fdiv%3E%3C%2Fblockquote%3E%3Cdiv%3E%3Cfont+size%3D%225%22+face%3D%22Comic+Sans+MS%22+color%3D%22%23f83a22%22%3E%3Cimg+src%3D%22data%3Aimage%2Fpng%3Bbase64%2CiVBORw0KGgoAAAANSUhEUgAAANEAAABXCAMAAACtDVAYAAAB41BMVEUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADAwOvr7AAAAC0tbXBwcLo6evt7vDr6%2BsfHx%2F19vbw8PCpqanz8%2FWoqanu7u%2BxsbH5%2BfrV1daPj5B1dXbr7O38%2FPz29%2Fng4ODd3uDT09TR0dK4uLi2t7gRc6%2F%2F%2F%2F%2F8%2Ff79%2Fv8PZ50Qcq4Oca4LcK0Hbqz1%2BfwEa6s2ibwVdrH6%2FP4Jb61Bj78ZeLIzh7oAaang7fUSdLDx9%2FoVa6Dq8%2FiHuddSmcU8jL73%2Bv3v9fkifbVkpMt9stRfockRcKvn8ffF3ezB2%2Bu31eex0eXS5PCny%2BFop80thLkogbfz%2BPvi7vXZ6fOszuQcebMAZ6je6%2FRWnMdNl8Qfe7QQbqmext9ZnshEkcAlf7bs9PnO4u%2BjyeBIlMJ2r9E5ir0od6jJ3%2B2bxN6XwtzV5vG92Om61ujN4e0hc6WBtdVwrM%2Fz9%2FuNvNlHirQ7g6%2Fk7%2FaTwNuItM%2BNt9Fsqc6mx9uewthnn8LE2uipzeOFss5inMBUk7q50%2BMAYqYdcKNqocOXvdVbl71KjbZCh7IzfqwQbaVPj7jluv8mAAAAOXRSTlMAAwYJDRMYJR4rPDRPRhAcIhYLMShDLzo3REA1SJFLl6TX4tdO7uKL64vgk%2FW8em3d9%2FHHxri2m5rXR3UPAAAM9ElEQVR42uzUu04CQRjFcRTvuKisC3uZeL9GjZ0XwC1tqWx4AUsbKzpo6AiJDRDDs3rm7CSTScwGkOxo4v8FvvxyJpP7L7eQbTn01w1pzROzaLt5sExL3m6ma3aOtpRKpSV74bp2zYRSHGpIKSYt20jdJowqhZraQw40CrIjW7ERL5MmWUQp05QecqihZIutZd8Wo4wqoKYzGZ5EQ8h20mrWqbukJSrDNCGInqL0UEPHLlq30y6ijSppKtJE0oQD0SPXoUZSymWXFbLOZeWyhFEll6JJzzTRQMoDDjBwRL5fQRvZV0G%2BH8EGFlDKNMFMGsSB6AHHBUZCwtBDAXOyKWAeCkNJA8sFiibOpElpIA6kPZEPjBc4QuyhTRvtISGcwAPLj7SJMxmkNFDiKWCdMBC3DzeHF9c1Vp%2B16o%2B6vji%2Bub8VQYilConJJKWDOJD0YB1xeVgzsiDSHZ8LLCVNnCmdZIAwkBtVQu9k%2F0hbLI6kO9s%2F8cJK5GImg5QOwouTA3ni4BSEX0aqnh4IT86El2eQUkEcyLlS%2Bzy3R8Nua%2FBks0GrOxy162qnK4czpZIIymsQBrqrsfdevzvuNOL40WZx3OiMu%2F3eS5XdYSZNypP0zUQahBenPoTXj8%2FWW7MZP9rvi9ky600iCsPwnTEaE437FjVqXC6M28184CwMDDAwUIaBQoGWpUBZSkvZWkuLLVprF6vVukSNf9XvHMggAxgveS8goUDn4V3msFNTCy9%2F7ha6E4HJ05GGmNTJHM42jgIFuk55AntvvWNB06Pyvt17A0TXKRLOA454J3cGi2iJzhzDlcPIXe4AfTzyTjHjpinv0ccO0mUMHi7esTOkSgaT9MwdP33uwtWuQ9LB9gIzjlrYPjB3Xbp64dzp43rujBZh5rBEF05e7AC92d0cP4O6Nm3uWjpIF09ewCp1cocURoswc1iii1ceUKBP3nEqUL9Y7yeKdO%2FKRawS5s5oErWI7BzN3KlbBGhvjIEI0h5Bun2K5I7uHTXJYBHdOczcDdKhxOY4AyHSZoJ06Qbmju6dbpLRorOXrlwjJ4WN7XHtkN6l7Q1yerh25dJZg0mUSG%2FRyRPnbyJQ9YNx5eyCKIouF8eMjRa%2BBxDp5vkTJ%2FUmUSI9dDh02CK06D4SvfcaTU45GmoxHA7%2BJxLy84rxHMPzgsCJosByPNt9DV9ghojrCt8gCqPi732PRPfRJGwSzp0eu17o6NDRFrmPjBYJ9Qgdl7DwXyFn%2FGV1lmd08Xa8dKU0a5VzjbJDKQVZCsLa5CWnwLD4V12U0ZajcjK2lfJSiRth0lGGNonOXS92vdB1ho626MDLGIkOa6utww0I2f7HJE6ZAUv5q9ARzyizst%2BvTvrq0dZSBlpyaM3Bc%2BRbV2KRZafdKfv%2FVonLhdshlC8ZnJ2GeEpgiYaYdECbROeObEM%2FEYaO7gK5uU58HjwriHbX169laTHIM7xgN4o3ENmakHktO6j8Ka60Fi1EPNTkxiokJt0Qm2d4JFInIL2lWGPv3ESFAj5k3iVZeRWo1h1%2Bt3lOUGxE7KBJn0mTruM20Nj1iLBGJHR0Fy4%2FRqIvLwc%2FTX4koTHRSglKamXFalBqkAgkC5XmqYvBGhBp2VpURqL5GQ2yYYVlOOdyFTTfvBv%2B1o5TXodAITsB2aWy2ZQoJ4nCucFrevkFAB5fxm2gsSNFokR6jTB0dxDIsvebGSo%2BVcj6XcFDzdOvamR6kMicrgaoqgnO2Y5PJ6rQqvD2%2FAZMV0oJC2RUERfUthyAtG9%2FbhE1l4ba4mK9XmTkOKypRV8gXtzROd%2FMD%2Bb9954GAHcwdnqResPQXbpH5MSN0z1UHF9LN1yVesHdrwAUFEOPmlDdUsNEk0kHz4p5l1KDeo5j8lGIWvOVejo%2BLzKIpISqMb9LJJrNQJvDZ16wxmGnXquvZ7dqpoj7XSQS8UBgCNHCB3IKf9RdO30aesOARzpyM9r9Nerumm9BUWRFV5%2FyQhtCOksuJctyynoImUlnhcgp8Mpso7GkZuHV%2FFLDH4NI0rEyORPyyzYsCBcMO0SGSs6Az0nyjkSmxVV4tSoVLJmdULJYLK%2BZhhFN%2FdoltyQ83OnToBORAwPZ7udI9OMlO4qoCUlhIM2VTFrvkaDOrEajH6MRkNZbUSKfyDqXNUnTzGBKa5omAVgsUsAdMDcr1rKqqik7M4woCtO4D1J0pvY6L7BhzYNEg0X6gUTPyH7r06ATdYfhIRJte5kRcrXBNzDfigot%2FTVxvwpm%2FA4kkPDJDBDLs7m2BUWI8MkEJnz0VD2WHWsCNM30ysaNIjJLra0qNPOsMzmUiPFuI9HD7jQMJ3qKlzH6jCpOavWgYanZUsKUZHvvmDAnQrp23DCdZ2xyOFwmkx0ul9U5ydMuqsszW2VZnpEkM0RLo4hqgTW1DYV5xmlDItVIRE1Corv%2FIjolIdFIi3C%2BIzjfhrlIaRO5v5irOB5cV6IcI0QMS1SDZo5VxH1PYEl07Uc2Gi5nI7kchdhQoiZ%2BMl5wlOYgHpp%2BbZ%2BUqil2CNE3JHpy6l9EJtSLkUT8bDbr7ydibUlI8H1ERTv93%2FTSooSIyvaHE3PbUROKwvAT9LJP0Sfgl%2B6NCohajEcYwigesHiO4jhqPIyTsSZO402btJd91a46U8ZJIJPMfyGEROALa639%2F3uG2nzfcAq2aiYHHsrNLN3Q2IQSbds1j4i%2Batox5tzCUSw1r4SVzmeQPrz%2FG3G%2BSgyTry9N78pFdkGULy9a8vJJrWL9TMRGfnNzg8SxHcOVh5NcBQ4KJyL3NpzoW5VQtn3NgjdcYCYX1Xw6%2Bhu9v4%2BEngMzy9mLuFQS%2B%2Fyy02zk8oFuYkTE0qdjnljO6s9nuF4l0HFTQkDEeQjRl7E3m4zhZAfINwciEb3RR%2B%2BZdUJ23z7uS9qFrHrsQXpNhBhNNFH990MYux4VJh1jIvqLYcuQdqBTp5Xi7JlI0Scnhb0i8hLbdn%2B9rTgeVov1TXtzgJ1mb826d6xHAmcbiIlLqXAU4RWRfTW3LFpnmgvLN%2BvoZAW9tKs9WH38MHQhxW9VoJORBqYhMCIib7TI9WV%2BQZTK3HlfkOvUKmURUEWUV05QdZHrERFFeYaf5BmixJR5odEJ1FgXJiN2WZYPubE73DotQqsXe8t6rEB9pBhG16ij0ZWS7iEPXGtJo1NZdyVpOkbfaaOiXRIJyijjALZp3CLfr1%2FXUZ7liCjKM3y89Axhvu4XhYkoxd1CzTRGxpNG%2Fo8fxexldm%2Fdi9vSlVi2fu8Re%2Bj5N1hNqSg5ZyPydfrIvE8AsIe%2F%2FTqIKJ6hGlTR33PGBDcgEnrmEYBXdC2%2F5RrDascR892wOPEr8HUBUYj3%2Fvb9T%2BT4njox2DQczkrK2xi2xZc2Yr2NikfFsvEYl6uotpQDEof4OaSnthQjOjZQHefF3ZQSRCHOU5McgNpQ6QpxSWv%2FJ8ouaZla25jN4yxJxlFpdcL6iLx3O8R7h%2BejSNOg3hbKndEZgnETThONZADEJ1%2Fh%2BVmCWcmpA272Se1IF%2BJKkQJpDg3itR%2FltAO1VlMTpyxLnQC14Cp%2BY7zZ7EQcnvpouVOx1x8SYlXWzc3VYrGpiFU9PB8Fg4GIKB9FZtgookGlv8JY4E8lOMGXGjbxoOaGHrBQJOkRuPptJTDmSgFwWHfcbpeBzXzXGBg8qc0gqqiT55bknU1%2FUCycFRso55F%2Bp6LhZo0GdtP0GokcjX6YPCLDfiLr%2FZJho%2FcZwsX0ArDynxkkdwzsWsGjeOYRjisRd21dSrauCpogLavbppBefFPLiWtfl12d04Tza4C6YHSqy0Ul9W8Y1u%2FvZ3f77vk2o2vsMhJLyRSt9KZXUdX83T7N3t5neGMvKFQpYziR9eDm0%2BIgI13Y8KmVERi9UmZKL9ulXmbK0k8LulscDAYl%2FXlbi3PZPE3OlpdxxumgyFqppMnPRofN98u%2FAtZWkPGSsCKg3lwXVyy9owWQsSB4okP4iPB4HQI4gMbbUHgosg5gJ9uBKDtgAQcWsgf1fkEAog8iDxSGsuECCCF7iFagnxBS2MbrQOPEkEFVxHgd5piqpAIoJ03IJ3M4Gpc4EKDx8ZhClNr8CaBcpCjJBR%2F4Hhbj3lbqqOPew2FuggtlbmI4zB9JIOaPhvgcXyq2Ob5hMA%2FLxY0cRYxDfa5cG32ufKivZ9CWhC%2FRgK9nILzmpHswrjnxgy7QwFxzgh5JQ3hdEKCdO0YBEIahMOzaC3QSnNxcO9VDeDInDywGAi6PlCwNr80NHl2%2Fv%2FJEpt0qVe66n3h2q6z%2FQfpEpq%2Fbwvm6qg7S8HWGgfTf6b0mAwkGQaea1akGm6RONWOnalviQJOwJfZ4766Tjh16b1lEZfK%2FQcN0E4RtC%2ByPUoj%2BKIH%2BaLBGjLHjI2wtCXtYxmaZsStnbP8p%2F2eg%2FEOj%2F75l3gulB4VshlkDkwAAAABJRU5ErkJggg%3D%3D%22%3E%3C%2Ffont%3E%3C%2Fdiv%3E%3C%2Fbody%3E%3C%2Fhtml%3E",
            "name": "亲自活动1",
            "publishTime": "2018-11-08 20:51:34",
            "sponsor": "主办方xx",
            "synopsis": "简介xx",
            "unValidTime": "2018-12-27 20:50:45",
            "validTime": "2018-11-08 20:50:42"
        }
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */

/**
 * @api {get,post} /api/active/apply 活动申请
 * @apiName apply
 * @apiGroup active
 * @apiVersion 1.0.0
 * @apiHeader Content-Type get:text/html;charset=UTF-8 post:application/x-www-form-urlencoded
 * @apiParam {String} id 活动id
 * @apiParam {String} phone 手机号
 * @apiParam {String} code 手机验证码
 * @apiParam {String} cardNo 智能卡号
 * @apiParamExample {String} 请求样例
 * http://localhost:8080/cms_sp/api/active/apply?id=e581284066f359420166f360b81e0000&phone=18710009588&code=7373&cardNo=4264678666
 * @apiSuccess {JsonObject} data  数据对象
 * @apiSuccess (data){Integer} applyResult 申请结果 0-申请成功；1-活动过期；2-验证码过期；3-验证码错误；4-未知的卡号；5-已报名
 * @apiSuccess {JSONObject} extra 扩展信息
 * @apiSuccess {String} message 接口消息
 * @apiSuccess {String} status 接口状态
 * @apiSuccessExample {json} 返回样例
 *{
    "data": {
        "applyResult": 1
    },
    "extra": {},
    "message": "成功！",
    "status": "OK"
}
 *
 */
