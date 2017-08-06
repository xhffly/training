package com.strongit.ah.training.action.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public abstract class BaseActionSupport<T> extends SimpleActionSupport implements Preparable, ModelDriven<T> {
	private static final long serialVersionUID = 3434328345273358967L;
	
//	protected final Logger logger = Logger.getLogger(getClass());
	
	public static final String RELOAD = "reload";
	
	private JSONArray  resultArray;
	private JSONObject result;
	
	private int page;//分页时的当前页数
	private int rows;//分页时的每页记录数
	private String sort;//排序字段
	private String order;//顺序或倒叙
	
	
	public void rendText(String param) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			PrintWriter out = response.getWriter();
			out.write(param);
			out.flush();
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String execute() throws Exception {
		return list();
	}

	public abstract String list() throws Exception;

	public abstract String save() throws Exception ;

	public abstract String delete() throws Exception ;

	public abstract String input() throws Exception ;

	public T getModel() {
		return null;
	}

	public void prepareSave() throws Exception {
		prepareModel();
	}

	public void prepareInput() throws Exception {
		prepareModel();
	}

	public void prepare() throws Exception {
	}

	protected abstract void prepareModel() throws Exception ;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public JSONArray getResultArray() {
		return resultArray;
	}

	public void setResultArray(JSONArray resultArray) {
		this.resultArray = resultArray;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	

}