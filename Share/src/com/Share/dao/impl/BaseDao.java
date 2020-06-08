package com.Share.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Share.dao.IBaseDao;
import com.Share.util.Pagination;

public class BaseDao<T> implements IBaseDao<T> {
		
	private Class<T> clazz;
	
	@Autowired
	private SessionFactory sessionFactory;

	//获取泛型参数
	@SuppressWarnings("unchecked")
	public BaseDao(){
		clazz=(Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public Class<T> getClazz() {
		return clazz;
	}
     //insert
	@Override
	public Serializable save(T t) {
		return getCurrentSession().save(t);
	}
    //delete
	@Override
	public void delete(T t) {
		getCurrentSession().delete(t);
	}
    //update
	@Override
	public void update(T t) {
		getCurrentSession().update(t);
	}
    //insertOrupdate
	@Override
	public void saveOrUpdate(T t) {
		getCurrentSession().saveOrUpdate(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Serializable id) {
		return (T)getCurrentSession().get(clazz, id);
	}

	@Override
	public T get(String hql) {
		return get(hql, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(String hql, Object[] params) {
		Query query = getCurrentSession().createQuery(hql);
		setParameter(query, params);
		return (T)query.list().get(0);
	}

	@Override
	public List<T> find(String hql) {
		return find(hql, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Object[] params) {
		Query query = getCurrentSession().createQuery(hql);
		setParameter(query, params);
		return (List<T>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, int page, int rows) {
		Query query = getCurrentSession().createQuery(hql);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Object[] params, int page, int rows) {
		Query query = getCurrentSession().createQuery(hql);
		setParameter(query, params);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		return query.list();
	}

	@Override
	public Pagination<T> findPagination(String hql, int page, int rows) {
		return findPagination(hql,null,page, rows);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagination<T> findPagination(String hql, Object[] params, int page,int rows) {
		Query query = getCurrentSession().createQuery(hql);
		setParameter(query, params);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		List<T> data = query.list();
		int count = count("select count(*) " + hql, params).intValue();
		
		Pagination<T> pagination=new Pagination<T>();
		pagination.setData(data);
		pagination.setCount(count);
		pagination.setPage(page);
		pagination.setRows(rows);
		return pagination;
	}

	@Override
	public Long count(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	@Override
	public Long count(String hql, Object[] params) {
		Query q = this.getCurrentSession().createQuery(hql);
		setParameter(q, params);
		return (Long) q.uniqueResult();
	}

	@Override
	public int executeHql(String hql) {
		return getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public int executeHql(String hql, Object[] params) {
		Query query = getCurrentSession().createQuery(hql);
		setParameter(query, params);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findBySql(String sql) {
		return getCurrentSession().createSQLQuery(sql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findBySql(String sql, int page, int rows) {
		return getCurrentSession().createSQLQuery(sql)
				.setFirstResult((page-1)*rows)
				.setMaxResults(rows)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findBySql(String sql, Object[] params) {
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		setParameter(query, params);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findBySql(String sql, Object[] params, int page,
			int rows) {
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		setParameter(query, params);
		return query.list();
	}

	@Override
	public <K> Pagination<K> findSqlPagination(String querySql,
			String countSql, int page, int rows) {
		return findSqlPagination(querySql, countSql, null, page, rows);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K> Pagination<K> findSqlPagination(String querySql,
			String countSql, Object[] params, int page, int rows) {
		SQLQuery query = getCurrentSession().createSQLQuery(querySql);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		setParameter(query, params);
		List<K> data = query.list();
		int count = countBySql(countSql, params).intValue();
		
		Pagination<K> pagination=new Pagination<K>();
		pagination.setData(data);
		pagination.setCount(count);
		pagination.setPage(page);
		pagination.setRows(rows);
		
		return pagination;
	}

	@Override
	public int executeSql(String sql) {
		return getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public int executeSql(String sql, Object[] params) {
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		setParameter(query, params);
		return query.executeUpdate();
	}

	@Override
	public Long countBySql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return ((BigInteger) q.uniqueResult()).longValue();
	}

	@Override
	public Long countBySql(String sql, Object[] params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		setParameter(q, params);
		return ((BigInteger) q.uniqueResult()).longValue();
	}
	
	private void setParameter(Query query,Object[] params){
		if (params!=null) {
			for (int i=0;i<params.length;i++) {
				query.setParameter(i, params[i]);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByMax(String hql, Object[] params, int max) {
		Query query = this.getCurrentSession().createQuery(hql);
		setParameter(query, params);
		return query.setMaxResults(max).list();
	}

}
