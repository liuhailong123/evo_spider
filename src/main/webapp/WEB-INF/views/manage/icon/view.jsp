<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<div class="row">
	<div class="col-lg-12">
		<div id="icons"></div>
	</div>
</div>
<script type="text/javascript">
jQuery(function($){
	 var icons = ["fa fa-tachometer", "fa fa-desktop","fa fa-envelope",
	             "fa fa-bar-chart-o","fa fa-weixin","fa fa-file-picture-o",
	             "fa fa-cogs","fa fa-users","fa fa-volume-up","fa fa-caret-right",
	             "icon iconfont icon-comments", "icon iconfont icon-process","icon iconfont icon-zhanghaoguanli",
	             "icon iconfont icon iconfont icon-quyudingwei1","icon iconfont icon-caidan","icon iconfont icon-zidian",
	             "icon iconfont icon-jiaoseguanli","icon iconfont icon-vr","icon iconfont icon-jigou","icon iconfont icon-yewuguanlirizhi",
	             "icon iconfont icon-bianjiICONCopy","icon iconfont icon--1", "icon iconfont icon-icon-xitongguanli"]
	 
	var contents = new Array();
    $.each(icons, function(){
        contents.push('<span class="btn btn-white btn-style"' +'" src="'+this+'"><i class="menu-icon '+this+'"></i></span>');
    });
    $('#icons').html(contents.join('')).find('span').on('click',function(e){
    	iconCall(this);
    });
});
</script>