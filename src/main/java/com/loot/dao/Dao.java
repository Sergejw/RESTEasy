package com.loot.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import com.loot.util.QualifierUtil;

/** {@inheritDoc} */
public class Dao <E, U> implements DaoIntf<E, U> {
	
	/** Entity/DTO class object.*/
    protected Class<?> entity;
    
    /** Entity/DTO class name.*/
    protected final String entityName;
    
	/** Manager to handle entities.*/
    @PersistenceContext(unitName = "notes") 
    protected EntityManager entityManager;
    
    /** Object/pipe for communication between database and application */
	@Resource
	protected UserTransaction transaction;

	/**
	 * 
	 * @param ip
	 * @throws ClassNotFoundException
	 */
	@Inject
	public Dao(InjectionPoint ip) throws ClassNotFoundException {
		String className = QualifierUtil.getEntityClass(ip);
		entity =  Class.forName(className);
		entityName = entity.getName();
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<E> getAllResources() {
		String statement;
		Query query;
		
		statement = "from " + entityName;
		query = entityManager.createQuery(statement);
		
		return query.getResultList();
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<E> getRangeOfResourcesById(U min, U max) {
		String statement;
		List<E> result;
		
		statement = "from " + entityName + " where id between '" + min + "' and '" + max + "'";
		result = entityManager.createQuery(statement).getResultList();
		
		return (result.isEmpty()) ? null : result;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public E getResourceById(U id) {
		return (E) entityManager.find(entity, id);
	}

	/** {@inheritDoc} */
	@Override
	public boolean createResource(E o) {
		try {
			transaction.begin();
			entityManager.persist(o);		
			transaction.commit();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteResourceById(U id) {
		E resource;
		
        try {
        	transaction.begin();
        	resource = (E) entityManager.find(entity, id);
            entityManager.remove(resource);
            transaction.commit();
            
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            
            return false;
        }
	}
}
