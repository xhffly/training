package com.strongit.ah.training.action.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class SimpleActionSupport extends ActionSupport {
	private static final long serialVersionUID = 3041101283373495109L;
//	protected final Log logger = LogFactory.getLog(getClass());
	
	private static final String CONTENT_TYPE_PLAIN = "text/plain;charset=UTF-8";
	private static final String CONTENT_TYPE_HTML  = "text/html;charset=UTF-8";
	private static final String CONTENT_TYPE_XML   = "text/xml;charset=UTF-8";
	

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}
}