<%@ page language="java"  contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${title }</title>
<link type="text/css" rel="stylesheet"  href="${ctx}/js/ztree/css/metroStyle/metroStyle.css"/>
<script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.exedit-3.5.min.js"></script>
<script type="text/javascript">
	var gridView;
	
	var zTree;
	
    $(function(){
    	
    	var t = $("#tree");
		t = $.fn.zTree.init(t, setting, zNodes);
		zTree = $.fn.zTree.getZTreeObj("tree");
		var rootNode=zTree.getNodeByParam("id", 0); 
		
		zTree.expandNode(rootNode, true, false);
		
		zTree.setting.edit.drag.isCopy = false;
		zTree.setting.edit.drag.isMove = true;
		zTree.setting.edit.drag.prev = true;
		zTree.setting.edit.drag.inner = true;
		zTree.setting.edit.drag.next = true;
    	
    	gridView = $("#deptList").datagrid({
		toolbar:"#toolButton",
		striped: true,
		fit: true,
		pagination:true,
		url:"${ctx}/sys/dept/department!getList.action?department.parentId=0",
		idField:"id",
		singleSelect:false, //控制表格单选和多选
		pageNumber:1,
		pageSize:10,   
		fitColumns:true,
		nowrap:true,
		loadMsg:"数据加载中,请稍后……",
		remoteSort: false,
        rownumbers:true,
		frozenColumns:[[
               {field:"ckDept",checkbox:true},
               {title:"主键id",field:'id',width:60,align:'center',sortable:true,hidden:true}
             
        ]],
		columns:[[
			{title:"编码",field:"code",width:40,align:"left"},
			{title:"名称",field:"name",width:40,align:"left"},
			{title:"成立时间",field:"establishTime",width:40,align:"center"},
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
	    	openWindow("dlg","部门修改","department!input.action?department.id="+selcId,$(document).width()*0.6,$(document).height()*0.7);
	    },
	        
	   });
	   
 		 $("#toolButton").show();
 		
	 });
    
    function doReload(url,parentId){
    	gridView.datagrid("options").url = url;
		gridView.datagrid("reload");
	    gridView.datagrid("clearSelections");
	}
    
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
			ui.showAlertMsg("请选择要编辑的部门！");
			return;
		}
		if(rows.length > 1){
			ui.showAlertMsg("只能选择一个部门进行修改！");
			return;
		} else {
			selcId = rows[0].id;
			openWindow("dlg","部门修改","department!input.action?department.id="+selcId,$(document).width()*0.6,$(document).height()*0.7);
		}
	}
	
	function add(){
		openWindow("dlg","部门新增","department!input.action",$(document).width()*0.6,$(document).height()*0.7);
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
				url: "${ctx }/sys/dept/department!delete",
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
	
	
	<!--多功能树结构start-->

	var setting = {
		edit: {
			enable: false,
			showRemoveBtn: false,
			showRenameBtn: false
		},
		view: {
			dblClickExpand: false,
			showLine: true,
			showIcon: false,
			selectedMulti: false,
			expandSpeed: "normal"
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				doReload("${ctx}/sys/dept/department!getList.action?department.parentId="+treeNode.id,treeNode.id);
				return true;
			}
		}
	};
	
	function beforeDrag(treeId, treeNodes) {
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {
				curDragNodes = null;
				return false;
			} else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
				curDragNodes = null;
				return false;
			}
		}
		return true;
	}
	//只能拖拽一个节点
	/**
	*treeId  树的id
	*treeNodes当前拖拽的节点数组
	*targeNode 目标节点
	*moveType  拖拽类型   prev,inner next
	*/
	function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
		if(treeNodes!=null && treeNodes.length > 0){
			$.ajax({
				   type: "POST",
				   url: "${ctx}/framework/sys/department!moveDepartment.action",
				   async: false,
				   data: {curNodeId:treeNodes[0].id,targetNodeId:targetNode.id,moveType:moveType},
				   success: function(msg){
					   if(msg!="success"){					   
						   alert(msg);
					   }
				   }
				});
		}
		
	}

	var zNodes =[
		 <s:if test='departmentList.size()>0'>
	     	{id:'0', pId:'-1', name:'部门管理',drag:false,open:true},
	     </s:if><s:else>
	     	{id:'0', pId:'-1', name:'部门管理',drag:false,open:true}
		 </s:else>	
		 <s:iterator value="departmentList" var="department" status="st">
			<s:if test="#st.isLast()">
				<s:if test='%{department.parentId == "0"}'>
					{id:'${department.id}', pId:'${department.parentId}', name:'${department.name}',open:false}
				</s:if> 
				<s:else> 
					{id:'${department.id}', pId:'${department.parentId}', name:'${department.name}',open:true}
				</s:else> 
			</s:if> 
			<s:else>
				<s:if test='%{department.parentId == "0"}'>
					{id:'${department.id}', pId:'${department.parentId}', name:'${department.name}',open:false},
				</s:if> 
				<s:else> 
					{id:'${department.id}', pId:'${department.parentId}', name:'${department.name}',open:true},
				</s:else> 
			</s:else> 
		</s:iterator>
	];


  </SCRIPT>
</head>

<body class="easyui-layout" data-options="fit:true"> 
   <div data-options="region:'north',split:true,title:'查询条件'"  style="height:90px;padding:5px; border-width: 0 0 1px 0;">
      <form id="searchForm" name="searchForm" action="" method="post">
      
	    <table width="100%" border="0" cellspacing="5" cellpadding="0">
	      <tr>
	        <td width="130"><input id="name" name="user.name" type="text"  value="${user.name}"  class="easyui-textbox" data-options="prompt:'部门编码'" style="width:120px"></td>
	        <td width="130"><input id="loginName" name="user.loginName" type="text"  class="easyui-textbox" data-options="prompt:'部门名称'" value="${user.loginName}" style="width:120px"/></td>
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
   <div data-options="region:'west',split:true,title:'部门管理'" style="width:220px;height: 300px;">
<!--  		<iframe id="navFrame" width="100%" height="95%" allowtransparency="true"  frameborder="0" scrolling="auto" src="department!listTree"></iframe> -->
   		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
	   		<td valign="top">
	         <div id="tree" class="ztree" />
			</td>
		</tr>
	</table>
   
   </div>
   <div data-options="region:'center',title:'',tools:'#tt'" >
	 <table id="deptList"></table>
   </div>
  
    <div id="toolButton" style="display: none;">
		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'"  onclick="add()"><span>新增</span></a>
		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit()"><span >修改/查看</span></a>
		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="del()"><span >删除</span></a>
	</div>
	<div id="dlg" ></div>  
	
</body>

</html>