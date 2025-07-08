package org.acme.exception;
import java.util.Map;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class GlobalExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof UserNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(Map.of("error", exception.getMessage()))
                .build();
        }
        if (exception instanceof EmailAlreadyExistsException) {
            return Response.status(Response.Status.CONFLICT)
                .entity(Map.of("error", exception.getMessage()))
                .build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(Map.of("error", "Internal server error"))
            .build();
    }

}
