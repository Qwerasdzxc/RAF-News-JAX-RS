package resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import models.User;
import requests.LoginRequest;
import services.UserService;

@Path("/users")
public class UserResource {

	@Inject
	private UserService service;
	
    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest) {
        Map<String, String> response = new HashMap<>();

        String jwt = service.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (jwt == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();
    }
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("userId") int userId) {
		return service.getUser(userId);
	}
	
	@GET
	@Path("/user-by-email")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserByEmail(@QueryParam("email") String email) {
		return service.getUserByEmail(email);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers(@QueryParam("page") int page) {
		return service.getUsers(page);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public User createUser(@Valid User user) {
		return service.createUser(user.getEmail(), user.getName(), user.getSurname(), user.getPassword(), user.isAdmin());
	}
	
	@PUT
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User updateUser(@PathParam("userId") int userId, @Valid User user) {
		return service.updateUser(userId, user.getEmail(), user.getName(), user.getSurname(), user.isAdmin());
	}
}
