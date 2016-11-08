package com.loot.rest;

import com.loot.dao.DaoQualifier;
import com.loot.dao.NotesDao;
import com.loot.entity.NoteDto;
import com.loot.util.ResponseUtil;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * This class represents an simple web service for HTTP methods GET, POST, PUT and DELETE. 
 * @author (X)'ERG
 */
@Path("/rest/")
@Produces("application/json")
public class NoteRest {

	/** Data access object for note resources. */
    @Inject 
    @DaoQualifier(entityClass="com.loot.entity.NoteDto") 
    private NotesDao<NoteDto, Long> dao;
    
    /**
     * Returns an note resource, which was specified within request by unique number. 
     * If the note resource does not exists, so the empty response object contains only 
     * HTTP status code 404 (not found). Otherwise the response get the HTTP status code 
     * 200 (OK) and the requested resource can be taken as JSON object from the body of 
     * response. 
     * @param i Unique number/identifier.
     * @return Specified note as JSON format.
     */
    @GET
    @Path("/note/{id}")
    public Response getById(@PathParam("id") long i) {
    	return (i >= 0) ? 	ResponseUtil.getResponse(dao.getResourceById(i)) : 
    						ResponseUtil.getResponse(400);
    }
    
    /**
     * Returns an response with collection of available note resources as JSON format. 
     * This resources can be extracted from the body element.
     * @return Collection of note resources.
     */
    @GET
    @Path("/notes/")
    public Response getAll() {
    	return ResponseUtil.getResponse(dao.getAllResources());
    }
    
    /**
     * Returns an response with an range of available note resources as JSON format. URI 
     * parameter 'min' and 'max' are boundaries of the range and are included into search 
     * request. Resources can be extracted from the body element of the response object. 
     * Example URI request: .../notes/range?min=1&max=3
     * @param min Boundary, at which id to start.
     * @param max Boundary, at which id to finish.
     * @return Collection of note resources.
     */
    @GET
    @Path("/notes/range/")
    public Response getRangeById(@QueryParam("min") long min, @QueryParam("max") long max) {
    	return ResponseUtil.getResponse(dao.getRangeOfResourcesById(min, max));
    }
    
    @GET
    @Path("/notes/bundle/")
    public Response getBundle() {
    	return null;
    }
    
    /**
     * Creates an new resource and returns response object with HTTP status code 200 if 
     * creation was successful and 400 if not.
     * @param note Object, which should be create as resource.
     * @return Response object with HTTP status code.
     */
    @PUT
	@Path("/note")
    @Consumes("application/json")
	public Response create(NoteDto note) {
    	return ResponseUtil.getResponse(dao.createResource(note));
	}
    
    /**
     * Deletes an by id specified resource and returns response object with HTTP status 
     * code 200 if removing resource was successful and 400 if not. 
     * @param id Unique number/identifier for resource, which should be removed.
     * @return Response object with HTTP status code.
     */
    @DELETE
    @Path("/note/{id}")
    public Response delete(@PathParam("id") long id) {
    	return ResponseUtil.getResponse(dao.deleteResourceById(id));
    }
}

