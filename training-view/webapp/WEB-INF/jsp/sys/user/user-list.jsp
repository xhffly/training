<%@ page language="java"  contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${title }</title>
<script type="text/javascript">
	var gridView;
    $(function(){
    	gridView = $('#userList').datagrid({
		toolbar:"#toolButton",
		striped: true,
		fit: true,
		pagination:true,
		url:"${ctx}/sys/user/user!getList.action",
		idField:"id",
		singleSelect:false, //控制表格单选和多选
		pageNumber:1,
		pageSize:10,   
		fitColumns:true,
		nowrap:true,
		loadMsg:'数据加载中,请稍后……',
		remoteSort: false,
        rownumbers:true,
		frozenColumns:[[
               {field:"ckUser",checkbox:true},
               {title:"主键id",field:'id',width:60,align:'center',sortable:true,hidden:true}
             
        ]],
		columns:[[
			{title:"登录名",field:"loginName",width:40,align:"center"},
			{title:"姓名",field:"name",width:40,align:"center"},
			{title:"手机",field:"mobile",width:40,align:"center"},
			{title:"电话",field:"tel",width:40,align:"center"},
			{title:"年龄",field:"age",width:40,align:"center"},
			{title:"备注",field:"remark",width:200,align:"left"},
			{title:"状态",field:"status",width:40,align:"center",
				formatter:function(value,rowData,rowIndex){
					if(value == "disabled"){
						return "禁用";
					}else if(value == "enabled"){
						return "启用";
					}else{
						return value
					}
				}
			}
		]],
		
	    onDblClickRow:function(index,row){
	    	var selcId = row.id;
	    	openWindow("dlg","用户修改","user!input.action?user.id="+selcId,$(document).width()*0.6,$(document).height()*0.7);
	    },
	        
	   });
	   
 		 $("#toolButton").show();
 		
	 });
	function doSearch(){
		 var array = {
				 "user.name":$("#name").val(),
				 "user.loginName":$("#loginName").val()
		 }
	    gridView.datagrid("options").queryParams = array;
	    gridView.datagrid("reload");
	    gridView.datagrid("clearSelections");
	}
	function resetForm(){
		 $("#searchForm").form("clear");
		 gridView.datagrid("clearSelections");
	}
	function edit(){
		var rows = gridView.datagrid("getSelections");
		var selcId;
		if(rows.length == 0){
			ui.showAlertMsg("请选择要编辑的用户！");
			return;
		}
		if(rows.length > 1){
			ui.showAlertMsg("只能选择一个用户进行修改！");
			return;
		} else {
			selcId = rows[0].id;
			openWindow("dlg","用户修改","user!input.action?user.id="+selcId,$(document).width()*0.6,$(document).height()*0.7);
		}
	}
	
	function add(id,title,url,width,height){
		openWindow("dlg","用户新增","user!input.action",$(document).width()*0.6,$(document).height()*0.7);
	}
	  
	
	function openWindow(id,title,url,width,height){
		$.ajaxSetup({type : 'POST'});
 		$("#"+id).dialog({ 
 			title: title,   
 		    width: width,   
 		    height: height,   
 		    content:'<iframe id="iframe" src="'+url+'" frameborder="0" style="border: 0; width: 100%; height: 100%;"></iframe>',
 		    modal: true, 
 		   	resizable:true,
 			buttons:[{ 
 				text:'保存', 
 				iconCls:'icon-ok', 
 				handler:function(){ 
 					var iframe =  $("#iframe")[0].contentWindow;
			    	var validFlag = iframe.save();//调用子页面函数
			    	if(validFlag == "true"){
			    		ui.showMsg("操作成功！");
				    	$("#"+id).dialog("close");
				    	resetForm();
				    	gridView.datagrid("reload");
			    	}else if(validFlag == "unvalid"){
// 			    		ui.showAlertMsg("操作失败！");
			    	}else{
			    		ui.showAlertMsg("操作失败！");
			    	}
 				} 
 			},{ 
 				text:'关闭', 
 				handler:function(){ 
 					$("#"+id).dialog('close'); 
 				} 
 			}] 
 		});
	}
	
	function del(){
		var rows = gridView.datagrid("getSelections");
		if(rows.length == 0){
			ui.showAlertMsg("请选择要删除的用户！");
			return;
		}
		var ids = "";
		for(var i=0;i<rows.length;i++){
	        ids =ids + rows[i].id+",";
		}
		ids = ids.substr(0,ids.length - 1);
		
		ui.showConfirm("您确认删除吗？",function(){
			$.ajax({
				type : "POST",
				url: "${ctx }/sys/user/user!delete",
				async: false,
				data: {dataIds:ids},
				success:function(msg){
			 		if(msg=="success"){
			 			ui.showMsg("删除成功！");
						gridView.datagrid("reload");
						gridView.datagrid("clearSelections");
				    }else{
				    	ui.showAlertMsg("删除失败！");
					}
				},
				error:function(e){
					ui.showAlertMsg("删除失败！");
				}
			});
		});
			
		
	}
	
	
</script>
</head>

<body class="easyui-layout" data-options="fit:true"> 
   <div data-options="region:'north',split:true,title:'查询条件'"  style="height:90px;padding:5px; border-width: 0 0 1px 0;">
      <form id="searchForm" name="searchForm" action="" method="post">
      
	    <table width="100%" border="0" cellspacing="5" cellpadding="0">
	      <tr>
	        <td width="130"><input id="name" name="user.name" type="text"  value="${user.name}"  class="easyui-textbox" data-options="prompt:'姓名'" style="width:120px"></td>
	        <td width="130"><input id="loginName" name="user.loginName" type="text"  class="easyui-textbox" data-options="prompt:'登陆名'" value="${user.loginName}" style="width:120px"/></td>
	        <td width="10" class="right_">&nbsp;
	         </td>
	        <td width="150">
	        	<a  href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'"  onclick="doSearch()">查 询</a>&nbsp;&nbsp;
	        	<a  href="#"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-clear'"  onclick="resetForm()">重 置</a>
	        </td>
	        <td width="*">&nbsp;</td>
	      </tr>
	    </table>
      </form>
   </div>
  
   <div data-options="region:'center',title:'',tools:'#tt'" >
	 <table id="userList"></table>
   </div>
  
    <div id="toolButton" style="display: none;">
		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'"  onclick="add('dlg','用户新增','user!input.action',$(document).width()*0.8,$(document).height()*0.9)"><span>新增</span></a>
		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit()"><span >修改/查看</span></a>
		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="del()"><span >删除</span></a>
	</div>
	<div id="dlg" ></div>  
	
</body>

</html>