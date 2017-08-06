(function(a){
	if (!window.ui) {
		window.ui = {}
	}
	ui = {
			showMsg :function(msg){
				$.messager.show({
					title : "操作提示",
					msg : msg,
					showType:"show"
				});
			},
			showAlertMsg : function(msg){
				$.messager.alert("系统提示",msg,"error");
			},
			showConfirm : function(msg,action){
				var result = false;
				$.messager.confirm("系统提示", msg, function(r){
					if(r){
						action();
					}
				});
			}
	}
	
})(jQuery);

$.extend($.fn.validatebox.defaults.rules, {   
	mobile: {   
		validator: function(value, param){   
			return /^1[3|4|5|7|8][0-9]\d{8}$/.test(value);   
		},   
	    message: "请输入正确的手机号！" 
	}   
 });
