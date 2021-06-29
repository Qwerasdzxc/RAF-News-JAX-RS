package resources;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import models.Category;
import services.CategoryService;

@Path("/categories")
public class CategoryResource {

	@Inject
	private CategoryService service;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Category findById(@PathParam("id") int categoryId) {
		return service.findById(categoryId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getCategories(@QueryParam("page") int page) {
		return service.getCategories(page);
	}
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	public Category createCategory(@Valid Category category) {
		return service.createCategory(category.getName(), category.getDescription());
	}
	
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Category updateCategory(@PathParam("id") int categoryId, @Valid Category category) {
		return service.updateCategory(categoryId, category.getName(), category.getDescription());
	}
	
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public Integer getCategoryCount() {
		return service.getCategoryCount();
	}
	
	
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public void deleteCategory(@PathParam("id") int categoryId) {
		service.deleteCategory(categoryId);
	}
}
