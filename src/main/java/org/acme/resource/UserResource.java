package org.acme.resource;

import org.acme.service.UserService;

import java.util.List;

import org.acme.dto.*;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    UserService userService;

    @GET
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public UserResponseDTO getUsersById(@PathParam("id") Long id) {
        return userService.getUserById(id);
    }

    @POST
    public Response createUser(@Valid UserCreatedDTO userCreatedDTO) {
        UserResponseDTO createdUser = userService.createUser(userCreatedDTO);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }

    @PUT
    @Path("/{id}")
    public UserResponseDTO updateUser(@PathParam("id") Long id, @Valid UserCreatedDTO userCreatedDTO) {
        return userService.updateUser(id, userCreatedDTO);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        userService.deleteUser(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/search")
    public List<UserResponseDTO> searchUsers(@QueryParam("name") String name) {
        if (name == null || name.trim().isEmpty()) {
            return userService.getAllUsers();
        }

        return userService.searchUsersByName(name);
    }
}
