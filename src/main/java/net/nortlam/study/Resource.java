package net.nortlam.study;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Resource {
    
    @EJB
    private Service service;
    
    @GET @Path("/{ID}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findByID(@PathParam("ID")long ID) throws NotFoundException, ServerErrorException {
        return Response.ok(service.findByID(ID)).build();
    }
    
}
