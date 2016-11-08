package com.loot.util;

import javax.ws.rs.core.Response;

/**
 * 	This class represents an utility in relation of standard java response object/
 * 	class. Functionality allows to build empty or loaded response objects. Empty 
 * 	means an simple response object, which contains custom selection of HTTP status 
 * 	code. Loaded means first kind of object (simple) with additional custom object, 
 * 	which can be extracted from the body element of response object. 
 * 	@author (X)'ERG
 */
public class ResponseUtil {

    /**
     * 	Returns an simple response object with custom HTTP status code.
     * 	@param i Number, what defines the status code. 
     * 	@return Response object.
     */
    public static Response getResponse(int i) {
    	return Response.status(i).build();
    }
    
    /**
     * Returns an simple response object with negative or positive HTTP status 
     * code. Positive code means 200 and negative means 400.
     * @param b Boolean value for HTTP status code. (true=200/false=400).
     * @return Response object.
     */
    public static Response getResponse(boolean b) {
    	return (b) ? getResponse(200) : getResponse(400);
    }
    
    /**
     * 	Returns an response object with HTTP status and if available (resource 
     * 	!= null), so with resource object within body element of response object.
     * 	@param o Object, what should be included to the response body element. 
     * 	@return Response object.
     */
    public static Response getResponse(Object o) {
    	return (o != null) ? Response.ok(o).build() : getResponse(404);
    }
}
