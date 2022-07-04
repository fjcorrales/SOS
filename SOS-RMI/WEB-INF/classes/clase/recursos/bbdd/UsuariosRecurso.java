package clase.recursos.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.naming.NamingContext;

import clase.datos.*;

@Path("/usuarios")
public class UsuariosRecurso {

	@Context
	private UriInfo uriInfo;

	private DataSource ds;
	private Connection conn;

	public UsuariosRecurso() {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			NamingContext envCtx = (NamingContext) ctx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/GeoETSIINF");
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Devuelve todos los usuarios
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUsuarios2(@QueryParam("patron") @DefaultValue("%") String patron) {
		try {
			String sql = "SELECT * FROM Usuario WHERE nombre LIKE '" + patron + "' ;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Usuarios u = new Usuarios();
			ArrayList<Link> usuarios = u.getUsuarios();
			rs.beforeFirst();
			while (rs.next()) {
				usuarios.add(new Link(uriInfo.getAbsolutePath() + "/" + rs.getInt("idUsuario"),"self"));
			}
			return Response.status(Response.Status.OK).entity(u).build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error de acceso a BBDD").build();
		}
	}
	
	//Devuelve un usuario en concreto
	@GET
	@Path("{idUsuario}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUsuario(@PathParam("idUsuario") String id) {
		try {
			String sql = "SELECT * FROM Usuario WHERE idUsuario = " + id + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Usuario usuario =  usuarioFromRS(rs);
				return Response.status(Response.Status.OK).entity(usuario).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Elemento no encontrado").build();
			}
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error de acceso a BBDD").build();
		}
	}
	
	//Crea un usuario
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUsuario(Usuario usuario) {
		try {
			String sql = "INSERT INTO Usuario (nombre, correo, edad, localidad) " + "VALUES ('"
					+ usuario.getNombre() + "', '" + usuario.getCorreo() + "', " + usuario.getEdad() + ", '" + usuario.getLocalidad() + "');";
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			int affectedRows = ps.executeUpdate();
			ResultSet generatedID = ps.getGeneratedKeys();
			if (generatedID.next()) {
				usuario.setId(generatedID.getInt(1));
				String location = uriInfo.getAbsolutePath() + "/" + usuario.getId();
				return Response.status(Response.Status.CREATED).entity(usuario).header("Location", location).header("Content-Location", location).build();
			}
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se pudo crear el usuario").build();

		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se pudo crear el usuario\n" + e.getStackTrace()).build();
		}
	}
	
	//Modifica datos de un usuario en concreto
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{idUsuario}")
	public Response updateUsuario(@PathParam("idUsuario") String id, Usuario nuevoUsuario) {
		try {
			Usuario usuario;
			String sql = "SELECT * FROM Usuario WHERE idUsuario =" + id + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				usuario =  usuarioFromRS(rs);
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Elemento no encontrado").build();
			}
			usuario.setEdad(nuevoUsuario.getEdad());;
			usuario.setLocalidad(nuevoUsuario.getLocalidad());
			usuario.setCorreo(nuevoUsuario.getCorreo());
			sql = "UPDATE Usuario SET " 
					+ " edad = " + usuario.getEdad() 
					+ ", correo ='" + usuario.getCorreo()
					+ "', localidad ='" + usuario.getLocalidad() + "' WHERE idUsuario = " + id +";";
			ps = conn.prepareStatement(sql);
			int affectedRows = ps.executeUpdate();
			String location = uriInfo.getBaseUri() + "usuarios/" + usuario.getId();
			return Response.status(Response.Status.OK).entity(usuario).header("Content-Location", location).build();			
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se pudo actualizar el usuario\n" + e.getStackTrace()).build();
		}
	}
	
	//Elimina un usuario en concreto
	@DELETE
	@Path("{idUsuario}")
	public Response deleteUsuario(@PathParam("idUsuario") String id) {
		try {
			String sql = "DELETE FROM Usuario WHERE idUsuario =" + id + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			int affectedRows = ps.executeUpdate();
			if (affectedRows == 1)
				return Response.status(Response.Status.NO_CONTENT).build();
			else 
				return Response.status(Response.Status.NOT_FOUND).entity("Elemento no encontrado").build();		
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se pudo eliminar el usuario\n" + e.getStackTrace()).build();
		}
	}
	
	private Usuario usuarioFromRS(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario(rs.getString("nombre"), rs.getString("correo"), rs.getInt("edad"), rs.getString("localidad"));
		usuario.setId(rs.getInt("idUsuario"));
		return usuario;
	}
	//----------------------------------------AMIGOS---------------------------

	//Añade amigo a un usuario
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("{idUsuario}/amigos")
	public Response anadeAmigo(@PathParam("idUsuario") String idU, Usuario amigos) {
		try {
			String sql = "INSERT INTO Usuario_esamigode_Usuario (creador, Usuario_idAmigo) VALUES ("
					+ idU + ", " + amigos.getId() + ");";
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			int affectedRows = ps.executeUpdate();
			String location = uriInfo.getAbsolutePath() + "/" + amigos.getId();
			return Response.status(Response.Status.CREATED).entity(amigos).header("Location", location).header("Content-Location", location).build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se pudo añadir el amigo\n" + e.getStackTrace()).build();
		}
	}
	
	//Devuelve un listado de todos los amigos de un usuario
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{idUsuario}/amigos")
	public Response getAmigos(@PathParam("idUsuario") String idU, 
			@QueryParam("patron") @DefaultValue("%") String patron,
			@QueryParam("start") @DefaultValue("1") String start,
			@QueryParam("count") @DefaultValue("10") String count) {
		try {
			int st = Integer.parseInt(start);
			int c = Integer.parseInt(count);
			String sql = "SELECT * FROM Usuario WHERE idUsuario = (SELECT Usuario_idAmigo FROM Usuario_esamigode_Usuario WHERE creador = "
					+ idU + ") AND nombre LIKE '" + patron + "' LIMIT " + (st - 1) + "," + c + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Usuarios u = new Usuarios();
			ArrayList<Link> usuarios = u.getUsuarios();
			rs.beforeFirst();
			while (rs.next()) {
				usuarios.add(new Link(uriInfo.getAbsolutePath() + "/" + rs.getInt("idUsuario"),"self"));
			}
			return Response.status(Response.Status.OK).entity(u).build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error de acceso a BBDD").build();
		}
	}
	
	//Elimina un amigo
	@DELETE
	@Path("{idUsuario}/amigos/{idAmigo}")
	public Response deleteAmigo(@PathParam("idAmigo") String idA, @PathParam("idUsuario") String idU) {
		try {

			String sql = "DELETE FROM Usuario_esamigode_Usuario WHERE Usuario_idAmigo =" + idA + " AND creador = "+ idU + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			int affectedRows = ps.executeUpdate();
			if (affectedRows == 1)
				return Response.status(Response.Status.NO_CONTENT).build();
			else 
				return Response.status(Response.Status.NOT_FOUND).entity("Amigo no encontrado").build();		
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se pudo eliminar el amigo\n" + e.getStackTrace()).build();
		}
	}


	//---------------------------------------------ENCONTRADOS-----------------------------------------------
	
	//Un usuario añade un tesoro encontrado
	@POST 
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("{idUsuario}/encontrado")
	public Response createEncontrado(Tesoro tesoro, @PathParam("idUsuario") String idUsuario ) {
		try {
			String sql = "INSERT INTO Usuario_encuentra_Tesoro (creador, Tesoro_idTesoro) VALUES ("
					+ idUsuario + ", " + tesoro.getId() + ");";
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			int affectedRows = ps.executeUpdate();
			ResultSet generatedID = ps.getGeneratedKeys();
			String location = uriInfo.getAbsolutePath() + "/" + tesoro.getId();
			if(affectedRows >= 1)
				return Response.status(Response.Status.CREATED).entity(tesoro).header("Location", location).header("Content-Location", location).build();
			else
				return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se pudo añadir el tesoro encontrado\n" + e.getStackTrace()).build();
		}
	}

	//Devuelve un listado con todos los tesoros encontrados por un usuario
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{idUsuario}/encontrado")
	public Response getEncontrado(@QueryParam("start") @DefaultValue("1") String start, 
			@QueryParam("count") @DefaultValue("10") String count, 
			@PathParam("idUsuario") String idUsuario,
			@QueryParam("fecha") @DefaultValue("%") String fecha, 
			@QueryParam("dificultad") @DefaultValue("%") String dificultad,
			@QueryParam("tamanio") @DefaultValue("%") String tamanio,
			@QueryParam("terreno") @DefaultValue("%") String terreno) {
		try {
			int st = Integer.parseInt(start);
			int c = Integer.parseInt(count);
			String sql = "SELECT * FROM Tesoro WHERE idTesoro = (SELECT Tesoro_idTesoro FROM Usuario_encuentra_Tesoro WHERE creador = "
					+ idUsuario + ") AND creador LIKE " + idUsuario + " AND fecha LIKE '" + fecha + "' "
					+ "AND dificultad LIKE '" + dificultad + "' AND tamanio LIKE '" + tamanio + "' AND tipoTerreno LIKE '" 
					+ terreno + "' LIMIT " + (st - 1) + "," + c + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Tesoros t = new Tesoros();
			ArrayList<Link> tesoros = t.getTesoros();
			rs.beforeFirst();
			while (rs.next()) {
				tesoros.add(new Link(uriInfo.getAbsolutePath() + "/" + rs.getInt("idTesoro"),"self"));
			}
			return Response.status(Response.Status.OK).entity(t).build(); 
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudieron convertir los índices a números").build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error de acceso a BBDD").build();
		} 
	}

	
}