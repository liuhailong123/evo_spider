<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/assets/js/jeDate/skin/jedate.css"/>
<script type="text/javascript" src="${contextPath}/static/assets/js/jeDate/jquery.jedate.js"></script>
<div class="row">
	<div class="col-lg-12">
		<div id="formDlg" class="form-horizontal" >
			<div class="form-group">
				<label class="col-lg-3 control-label">频道号:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" readonly="readonly" id="number" name="number" value="${entity.number}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">频道名称:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" readonly="readonly" id="name" name="name" value="${entity.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">直播地址:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" readonly="readonly" id="url" name="url" value="${entity.url}" />
				</div>
			</div>
			<div class="form-group">
                <label class="col-lg-3 control-label">频道类型:</label>
                <div class="col-lg-8">
                    <x:dictData dictCode="LiveChannelType" var="liveChannelType">
                        <x:select defaultOption="请选择" hasDefault="true" var="liveChannelType" items="${liveChannelType}"
                                  id="type" name="type" className="form-control">
                            <x:option value="${liveChannelType.code }" text="${liveChannelType.name }"
                                      selected="${entity.type eq liveChannelType.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
			<div class="form-group">
				<label class="col-lg-3 control-label">上线时间:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" readonly="readonly" id="uptime" name="uptime" placeholder="请选择" value="${entity.uptime}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">下线时间:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" readonly="readonly" id="downtime" name="downtime" placeholder="请选择" value="${entity.downtime}" />
				</div>
			</div>
            <div class="form-group">
                <label class="col-lg-3 control-label">状态:</label>
                <div class="col-lg-8">
                    <x:dictData dictCode="LiveChannelStatus" var="liveChannelStatus">
                        <x:select defaultOption="请选择" hasDefault="true" var="liveChannelStatus" items="${liveChannelStatus}"
                                  id="status" name="status" className="form-control">
                            <x:option value="${liveChannelStatus.code }" text="${liveChannelStatus.name }"
                                      selected="${entity.status eq liveChannelStatus.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
		</div>
	</div>
</div>
<script type="text/javascript">
jQuery(function($) {
	
	/**
     * 日期选择
     */
    $("#uptime").jeDate({
        isTime:true,
        format: "YYYY-MM-DD hh:mm:ss",
        minDate:"2014-09-19 00:00:00"

    });
	
	/**
     * 日期选择
     */
    $("#downtime").jeDate({
        isTime:true,
        format: "YYYY-MM-DD hh:mm:ss",
        minDate:"2014-09-19 00:00:00"

    });

    $("#type").attr("disabled","disabled");
    $("#status").attr("disabled","disabled");

});
</script>