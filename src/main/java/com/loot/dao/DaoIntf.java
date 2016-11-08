package com.loot.dao;

import java.util.List;

import javax.enterprise.context.SessionScoped;

/**
 * This class represents an generic interface for data access object. Functionality 
 * allows to get, delete and create resources.  
 * @author (X)'ERG
 *
 * @param <E> Type for resource.
 * @param <U> Type for parameter like id or boundaries.
 */
@SessionScoped
public interface DaoIntf<E, U> {
	
	/**
	 * Returns collection of available resources. 
	 * @return Collection of available resources.
	 */
	public List<E> getAllResources();
	
	/**
	 * Returns an ranged collection of available resources. The range is specified 
	 * by parameter 'min' and 'max', which are included too as resources.  
	 * @param min Lower boundary of range.
	 * @param max Upper boundary of range. 
	 * @return Ranged collection of available resources
	 */
	public List<E> getRangeOfResourcesById(U min, U max);
	
	/**
	 * Returns an by unique number specified resource.
	 * @param id Unique number/identifier, which specifies the resource.
	 * @return Resource.
	 */
	public E getResourceById(U id);
	
	/**
     * Deletes an by id specified resource and returns boolean. True stands for 
     * stands for successful operation. False for not.
	 * @param id Unique number/identifier, which specifies the resource.
	 * @return Boolean for deleted or not.
	 */
	public boolean deleteResourceById(U id);
	
	/**
	 * Creates a new resource and returns an boolean. True stands for successful 
	 * creation. False stands for not.
	 * @param o Object, which should be created as resource.
	 * @return Boolean for created or not.
	 */
	public boolean createResource(E o);
}
