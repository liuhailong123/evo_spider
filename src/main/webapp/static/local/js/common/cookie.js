$(document).ready(function() {

    // 校验
    function randomNumber(min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    };
    $('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(' '));
/*
    $('#defaultForm').bootstrapValidator({
        fields: {
            username: {
                message: 'The username is not valid',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '6-21'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '字母 数字 点 下划线'
                    },
                    remote: {
                        type: 'POST',
                        url: 'remote.php',
                        message: '用户名不可用'
                    },
                    different: {
                        field: 'password,confirmPassword',
                        message: '用户名密码不能相同'
                    }
                }
            },
            
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    
                    
                }
            }
        }
    });*/

 
    $('#validateBtn').click(function() {
       
        $('#defaultForm').bootstrapValidator('validate');

    });

    $('#resetBtn').click(function() {
        $('#defaultForm').data('bootstrapValidator').resetForm(true);
    });
   
     
});
function addCookie(name,value,days,path){   /**添加设置cookie**/  
        var name = escape(name);  
        var value = escape(value);  
        var expires = new Date();  
        expires.setTime(expires.getTime() + days * 3600000 * 24);  
        //path=/，表示cookie能在整个网站下使用，path=/temp，表示cookie只能在temp目录下使用  
        path = path == "" ? "" : ";path=" + path;  
        //GMT(Greenwich Mean Time)是格林尼治平时，现在的标准时间，协调世界时是UTC  
        //参数days只能是数字型  
        var _expires = (typeof days) == "string" ? "" : ";expires=" + expires.toUTCString();  
        document.cookie = name + "=" + value + _expires + path;  
    }  
    function getCookieValue(name){  /**获取cookie的值，根据cookie的键获取值**/  
        //用处理字符串的方式查找到key对应value  
        var name = escape(name);  
        //读cookie属性，这将返回文档的所有cookie  
        var allcookies = document.cookie;         
        //查找名为name的cookie的开始位置  
        name += "=";  
        var pos = allcookies.indexOf(name);      
        //如果找到了具有该名字的cookie，那么提取并使用它的值  
        if (pos != -1){                                             //如果pos值为-1则说明搜索"version="失败  
            var start = pos + name.length;                  //cookie值开始的位置  
            var end = allcookies.indexOf(";",start);        //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置  
            if (end == -1) end = allcookies.length;        //如果end值为-1说明cookie列表里只有一个cookie  
            var value = allcookies.substring(start,end); //提取cookie的值  
            return (value);                           //对它解码        
        }else{  //搜索失败，返回空字符串  
            return "";  
        } 
         
    }  
    function deleteCookie(name,path){   /**根据cookie的键，删除cookie，其实就是设置其失效**/  
        var name = escape(name);  
        var expires = new Date(0);  
        path = path == "" ? "" : ";path=" + path;  
        document.cookie = name + "="+ ";expires=" + expires.toUTCString() + path;  
    }  
      
    /**实现功能，保存用户的登录信息到cookie中。当登录页面被打开时，就查询cookie**/  
   /* window.onload = function(){
        var userNameValue = getCookieValue("userName");  
        document.getElementById("username").value = userNameValue;  
        var userPassValue = getCookieValue("userPass");  
        document.getElementById("password").value = userPassValue;  
    }  */
      
    function userLogin(){  
    /**用户登录，其中需要判断是否选择记住密码**/  
        var userName = document.getElementById("username").value;  
       
        var userPass = document.getElementById("password").value;  
            addCookie("userName",userName,7,"/");  
            addCookie("userPass",userPass,7,"/");  
    }  