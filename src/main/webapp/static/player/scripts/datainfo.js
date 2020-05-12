 $(function() {
    getData();
    // getRecommend();
});
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null
}
var getmyid = GetQueryString("id");
var assetId = GetQueryString('assetId');
var type = GetQueryString('type');
var playUrlUrl = "http://cms.evomedia.cn/api/assetInfo/getPlayUrl";
var recommendUrl = "http://cms.evomedia.cn/api/asset/video/recommend";

function getData() {
    
    var params = {
        assetId: assetId,
        type: type
    };
    $.ajax({
        url: playUrlUrl,
        type: 'get',
        dataType: 'jsonp',
        timeout: 1000,
        data: params,
        jsonp:"callBack",
        beforeSend: function LoadFunction() {},
        error: function erryFunction() {
            alert("调用接口数据失败")
        },
        success: function succFunction(data) {
            var videourl = data.rows.url;
            $('title').text(data.rows.name);
            var videosrc = '<source id="tsy_vr_url" src="' + videourl + '" type="video/mp4" >';
            $('#video_html5_api').append(videosrc)
        }
    })
}

function getRecommend() {
    var params = {
        isRecommend: 1,
    };
    $.ajax({
        url: recommendUrl,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        data: params,
        beforeSend: function LoadFunction() {},
        error: function erryFunction() {
            alert("调用接口数据失败")
        },
        success: function succFunction(res) {
            var datas = res.rows.data;
            var imgtag;
            for (i = 0; i < datas.length; i++) {
                var imgtag = '<img class="imgdetails" id="' + datas[i].id + '" src="' + datas[i].pictureUrl + '" name="' + datas[i].type + '" >';
                $('#recimg').append(imgtag);
                var myid = datas[i].id
            }
            $('.imgdetails').click(function() {
                var url = "http%3a%2f%2fwww.tsytv.com.cn%2ftsy%2ftsy%2fdown%2fpc%2findex.html";
                window.location.href = url
            })
        }
    })
}

