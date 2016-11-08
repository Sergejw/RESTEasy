package com.loot.util;

import java.lang.annotation.Annotation;

import javax.enterprise.inject.spi.InjectionPoint;

import com.loot.dao.DaoQualifier;

@DaoQualifier
public class QualifierUtil {

	public static String getEntityClass(InjectionPoint ip) {
		for (Annotation annotation : ip.getQualifiers()) {
			if (annotation.annotationType().equals(DaoQualifier.class))
				return ((DaoQualifier) annotation).entityClass();
		}
		
		throw new IllegalStateException("No @Initialized on InjectionPoint");
	}
}
