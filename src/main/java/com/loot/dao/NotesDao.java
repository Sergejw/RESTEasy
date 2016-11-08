package com.loot.dao;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

@DaoQualifier
public class NotesDao<E, U> extends Dao<E, U> {
	
	@Inject
	public NotesDao(InjectionPoint ip) throws ClassNotFoundException {
		super(ip);
	}
}