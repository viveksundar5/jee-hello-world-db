package net.nortlam.study;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServerErrorMapper implements ExceptionMapper<ServerErrorException> {

    @Override
    public Response toResponse(ServerErrorException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
