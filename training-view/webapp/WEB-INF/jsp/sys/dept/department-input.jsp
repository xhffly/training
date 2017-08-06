<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/common/header.jsp" %>
<script language="javascript" type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
<head>
<title>${title }</title>
	<script>
	 $(function(){
	    $("#deptTree").combotree({
			url : "${ctx }/sys/dept/department!deptTree",
			valueField : 'id',
			textField : 'text',
			required : false,
			editable : false
    	})    
    })
		function doBack(){
			window.history.go(-1);
		}
		
		function save(){
			var result;
			if($("#form1").form("validate")){
				$.ajax({
					type : "POST",
					url: "${ctx }/sys/dept/department!save",
					async: false,
					data: $("#form1").serialize(),
					success:function(msg){
					 	if(msg=="success"){
				        	result = "true";
					    }else{
					    	result = "false";
						}
					},
					error:function(e){
						result = "false";
					}
				});
				return result
			}else{
				return "unvalid";
			}
		}
		
	</script>
</head>
<body>
<div class="height10"></div>
<form id="form1" action="" method="post" name="form1" >
  <input type="hidden" name="department.id" value="${department.id}" />
  <br />

    <table width="90%" border="0" cellspacing="0" cellpadding="0" >
      <tr>
        <td width="15%" style="text-align: right;">编&nbsp;&nbsp;码：</td>
        <td width="35%"><input class="easyui-textbox" name="department.code" required="true" type="text"  value="${department.code}" /></td>
        <td width="15%" style="text-align: right;">名&nbsp;&nbsp;称：</td>
        <td width="35%"><input class="easyui-textbox" name="department.name" required="true" type="text"  value="${department.name}" /></td>
      </tr>
       <tr>
        <td width="15%" style="text-align: right;">成立日期：</td>
        <td colspan="3"  width="85%">
        	<input class="Wdate easyui-validatebox" name="department.establishTime" onClick="WdatePicker({skin:'whyGreen'})" required="true" type="text"  value="${department.establishTime}" />
        </td>
      </tr>
      <tr>
        <td width="15%" style="text-align: right;">上级部门：</td>
        <td colspan="3"  width="85%">
        	<input id="deptTree" class="easyui-combotree" value="${department.parentId}" name="department.parentId" style="width:200px;">
        </td>
      </tr>
      <tr>
        <td width="15%" style="text-align: right;">状&nbsp;&nbsp;态：</td>
        <td   width="35%">
			<s:select list="#{'enabled':'启用','disabled':'禁用'}" data-options="editable:false,panelHeight:70" required="true"   name="department.status" 
		        		headerKey=""
		        		headerValue=""
		        		cssClass="easyui-combobox "
		        		cssStyle="width:80px;"
        			/>
		</td>
      </tr>
      <tr>
        <td style="text-align: right;">备注:</td>
        <td colspan="3" ><textarea style="width: 95%;height: 50px;" class="textarea" rows="5"  name="department.remark">${department.remark}</textarea></td>
      </tr>
    </table>
 
</form>
</body>
</html>
