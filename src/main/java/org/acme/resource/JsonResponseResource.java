package org.acme.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/json")
public class JsonResponseResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String jsonResponse() {
        return "{\"message\": \"Hello from Quarkus JSON response\"}";
    }
}
