package com.strongit.ah.training.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.strongit.ah.training.dao.DaoContext;


/**
 * 业务逻辑基类
 */
public class BaseServiceImpl implements BaseService { 

	@Autowired
	protected DaoContext dao;
	
	@Override
	@Transactional(readOnly = true)
	public <T> T get(Class<T> clazz, Serializable id) throws Exception{
		return dao.get(clazz, id);
	}
	
	@Override
	@Transactional
	public <T> void save(T entity) throws Exception{
		 dao.save(entity);
	}
	
	@Override
	@Transactional
	public <T> void saveOrUpdate(T entity) throws Exception{
		dao.saveOrUpdate(entity);
	}

	@Override
	@Transactional
	public <T> void delete(T entity) throws Exception {
		dao.delete(entity);
	}
}
