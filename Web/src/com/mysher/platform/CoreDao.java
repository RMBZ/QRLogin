package com.mysher.platform;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class CoreDao extends HibernateDaoSupport implements IDao {
	
	public Object getObject(Class entityClass, String id) {
		return getHibernateTemplate().get(entityClass, id);
	}
	
	public Object getObject(Class entityClass, String hql, Object param) {
		List list = null;
		
		if(param == null) {
			list = getAllByHql(hql); 
		} else {
			list = getAllByHql(hql, param);
		}
		
		try {
			return list.isEmpty() ? entityClass.newInstance() : list.get(0);
		} catch(Exception e) {}
		
		return null;
	}
	
	public Object getObject(Class entityClass, String hql, Object[] params) {
		List list = getAllByHql(hql, params);
		
		try {
			return list.isEmpty() ? entityClass.newInstance() : list.get(0);
		} catch(Exception e) {}
		
		return null;
	}

	public void saveObject(Object entity) {  
        getHibernateTemplate().saveOrUpdate(entity);
    }
	
	public List getAllByHql(String hql) {
		return getHibernateTemplate().find(hql);
	}
	
	public List getAllByHql(String hql, Object param) {
		return getHibernateTemplate().find(hql, param);
	}
	
	public List getAllByHql(String hql, Object[] params) {
		return getHibernateTemplate().find(hql, params);
	}
	
	public void removeObject(Object entity) {
		getHibernateTemplate().delete(entity);
	}
	
	public void removeById(Class entityClass, String id) {
		Object entity = getObject(entityClass, id);
		removeObject(entity);
	}
	
	public void removeByHql(String hql) {
		getHibernateTemplate().bulkUpdate(hql);
	}
	
	public void removeByHql(String hql, Object param) {
		getHibernateTemplate().bulkUpdate(hql, param);
	}
	
	public void removeByHql(String hql, Object[] params) {
		getHibernateTemplate().bulkUpdate(hql, params);
	}
	
	public void updateByHql(String hql) {
		getHibernateTemplate().bulkUpdate(hql);
	}
	
	public void updateByHql(String hql, Object param) {
		getHibernateTemplate().bulkUpdate(hql, param);
	}
	
	public void updateByHql(String hql, Object[] params) {
		getHibernateTemplate().bulkUpdate(hql, params);
	}
}
