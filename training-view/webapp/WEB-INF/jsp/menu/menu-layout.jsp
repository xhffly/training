<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/header.jsp" %>
<script type="text/javascript">
	$(function(){
		setTimeout(function () {
            $("#mainPanle").tabs('add', {
                title: '个人桌面',
                content:'<iframe src="${ctx}/sys/user/user!list" frameborder="0" style="border: 0; width: 100%; height: 98%;"></iframe>',
                closable: true
            });
		}, 1);
	});
	var index = 0;
	function addPanel(title,url){
		if (!$('#mainPanle').tabs('exists', title)){
			$('#mainPanle').tabs('add',{
				title: title,
				content: '<iframe src="'+url+'" frameborder="0" style="border: 0; width: 100%; height: 98%;"></iframe>',
				closable: true
			});
		} else {
			$('#mainPanle').tabs('select',title)
		}
	}
	function removePanel(){
		var tab = $('#mainPanle').tabs('getSelected');
		if (tab){
			var index = $('#mainPanle').tabs('getTabIndex', tab);
			$('#mainPanle').tabs('close', index);
		}
	}
</script>
<title>${title }</title>
</head>
<body class="easyui-layout" >
<div data-options="region:'north',split:true,border:false" style="min-width: 1000px;overflow: hidden;height: 70px">
<h1>思创安徽 </h1>
</div>
<!--左侧导航start-->
<div data-options="region:'west',split:true" title="<div class='blod'>系统导航</div>" style="width:200px;" >
  <div id='wnav' class="easyui-accordion" data-options="fit:false,border:false">
  	<div title="系统管理"  style="padding:10px;">
		<p><a href="javascript:void(0)" style="cursor: pointer;color: black;text-decoration: none" onclick="addPanel('人员管理','${ctx}/sys/user/user!list')">人员管理</a></p>	
		<p><a href="javascript:void(0)" style="cursor: pointer;color: black;text-decoration: none" onclick="addPanel('部门管理','${ctx}/sys/dept/department!list')">部门管理</a></p>
		<p><a href="javascript:void(0)" style="cursor: pointer;color: black;text-decoration: none"  onclick="addPanel('角色管理','${ctx}/sys/user/user!list')">角色管理</a></p>
		<p><a href="javascript:void(0)" style="cursor: pointer;color: black;text-decoration: none"  onclick="addPanel('资源管理','${ctx}/sys/user/user!list')">资源管理</a></p>
	
	</div>
<!-- 	<div title="考勤管理"  style="padding:10px;"> -->
<!-- 		<p>我的假条</p>  -->
<!-- 		<p>请假审批</p> 	 -->
<!-- 	</div> -->
  </div>
</div>
<!--左侧导航end--> 

<!--主面板start-->
<div  data-options="region:'center',split:true" >

<div id="mainPanle"  class="easyui-tabs" fit="true" border="false">
	
 </div>
</div>
<!--欢迎页end-->


<div data-options="region:'south',split:false" style="text-align:center; background:#e0ecff;color: #15428b;font-family: Arial, Helvetica, sans-serif;">   
	思创安徽分公司
</div>
</body>
</html>
