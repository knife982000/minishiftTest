package perros;

import javax.persistence.EntityExistsException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/perros")
public class RecursoPerro {
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Perro getPerroById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Perro perro = DAOPerro.getInstance().findById(id);
		if(perro!=null)
			return perro;
		else
			throw new RecursoNoExiste(id);
	}

//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Perro createPerro(Perro perro) {
//		if(DAOPerro.getInstance().findById(perro.getId())!=null)
//			throw new RecursoDuplicado(perro.getId());
//		else{
//			Perro result= DAOPerro.getInstance().create(perro);
//			return result;
//		}
//
//	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPerroV2(Perro perro) {
		if(DAOPerro.getInstance().findById(perro.getId())!=null)
			throw new RecursoDuplicado(perro.getId());
		else{
			Perro result= DAOPerro.getInstance().create(perro);
			return Response.status(201).entity(perro).build();
		}

	}

	public class RecursoDuplicado extends WebApplicationException {
	     public RecursoDuplicado(int id) {
	         super(Response.status(Response.Status.CONFLICT)
	             .entity("El recurso con ID "+id+" ya existe").type(MediaType.TEXT_PLAIN).build());
	     }
	}
	
	public class RecursoNoExiste extends WebApplicationException {
	     public RecursoNoExiste(int id) {
	         super(Response.status(Response.Status.NOT_FOUND)
	             .entity("El recurso con id "+id+" no fue encontrado").type(MediaType.TEXT_PLAIN).build());
	     }
	}
	
	

}