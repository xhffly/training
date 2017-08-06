<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/common/header.jsp" %>
<head>
<title>${title }</title>
	<script>
		function doBack(){
			window.history.go(-1);
		}
		
		function save(){
			var result;
			if($("#form1").form("validate")){
				$.ajax({
					type : "POST",
					url: "${ctx }/sys/user/user!save",
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
  <input type="hidden" name="user.id" value="${user.id}" />
  <br />

    <table width="90%" border="0" cellspacing="0" cellpadding="0" >
      <tr>
        <td width="15%" style="text-align: right;">登陆名：</td>
        <td width="35%"><input class="easyui-textbox" name="user.loginName" required="true" type="text"  value="${user.loginName}" /></td>
        <td width="15%" style="text-align: right;">姓&nbsp;&nbsp;名：</td>
        <td width="35%"><input class="easyui-textbox" name="user.name" required="true" type="text"  value="${user.name}" /></td>
      </tr>
      <tr>
        <td width="15%" style="text-align: right;">电&nbsp;&nbsp;话：</td>
        <td width="35%"><input class="easyui-textbox" name="user.tel"  type="text"  value="${user.tel}" /></td>
        <td width="15%" style="text-align: right;">手&nbsp;&nbsp;机：</td>
        <td width="35%"><input class="easyui-textbox" name="user.mobile" validType="mobile" required="true"  type="text"  value="${user.mobile}" /></td>
      </tr>
      <tr>
        <td width="15%" style="text-align: right;">年&nbsp;&nbsp;龄：</td>
        <td colspan="3"  width="85%"><input class="easyui-textbox" name="user.age" type="text"  value="${user.age}" /></td>
      </tr>
      <tr>
        <td width="15%" style="text-align: right;">状&nbsp;&nbsp;态：</td>
        <td   width="35%">
<!--         	<select class="easyui-combobox" name="user.status" data-options="editable:false"  style="width:;" required="true"> -->
<!-- 				<option value="">&nbsp;</option> -->
<!-- 				<option value="enabled">启用</option> -->
<!-- 				<option value="disabled">禁用</option> -->
<!-- 			</select> -->
			<s:select list="#{'enabled':'启用','disabled':'禁用'}" data-options="editable:false,panelHeight:70" required="true"   name="user.status" 
		        		headerKey=""
		        		headerValue=""
		        		cssClass="easyui-combobox "
		        		cssStyle="width:80px;"
        			/>
		</td>
		<td width="15%" style="text-align: right;">部&nbsp;&nbsp;门：</td>
        <td  width="35%">
        	<select class="easyui-combobox" name="" >
				<option value="disabled">启用</option>
				<option value="enabled">禁用</option>
			</select>
		</td>
      </tr>
      <tr>
        <td style="text-align: right;">备注:</td>
        <td colspan="3" ><textarea style="width: 95%;height: 50px;" class="textarea" rows="5"  name="user.remark">${user.remark}</textarea></td>
      </tr>
    </table>
 
</form>
</body>
</html>
