package clase.recursos.bbdd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

import clase.datos.Link;
import clase.datos.Tesoro;
import clase.datos.Tesoros;

@Path("/tesoros")
public class TesorosRecurso {
	@Context
	private UriInfo uriInfo;

	private DataSource ds;
	private Connection conn;

	public TesorosRecurso() {
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

	//Devuelve un tesoro cercano a unas coordenadas dadas
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTesoroPos(@QueryParam("latitud") @DefaultValue("%")String latitud, 
			@QueryParam("longitud") @DefaultValue("%")String longitud,  
			@QueryParam("fecha") @DefaultValue("%")String fecha, 
			@QueryParam("start") @DefaultValue("1") String start, 
			@QueryParam("count") @DefaultValue("10") String count, 
			@QueryParam("dificultad") @DefaultValue("%") String dif){
		try{
			int st = Integer.parseInt(start);
			int c = Integer.parseInt(count);
			String sql = "SELECT idTesoro FROM Tesoro WHERE dificultad LIKE '" + dif + "' AND fecha LIKE '" + fecha + "' ORDER BY (ABS(latitud - '" + latitud+ "') + ABS(longitud - '" + longitud + "')) LIMIT " + (st - 1) + "," + c + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Tesoros t = new Tesoros();
			ArrayList<Link> tesoros = t.getTesoros();
			rs.beforeFirst();
			while (rs.next()) {
				tesoros.add(new Link(uriInfo.getAbsolutePath() + "/" + rs.getInt("idTesoro"),"self"));
			}
			return Response.status(Response.Status.OK).entity(t).build();
		}catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudieron convertir los índices a números").build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error de acceso a BBDD").build();
		}
	} 
	
	//Devuelve un listado con todos los tesoros publicados por un usuario
	@GET
	@Path("{idUsuario}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTesoros2(@QueryParam("start") @DefaultValue("1") String start, 
			@QueryParam("count") @DefaultValue("10") String count, 
			@PathParam("creador") String creador,
			@QueryParam("terreno") @DefaultValue("%") String terreno,
			@QueryParam("fecha") @DefaultValue("%") String fecha,
			@QueryParam("dificultad") @DefaultValue("%") String dificultad,
			@QueryParam("tamanio") @DefaultValue("%") String tamanio) {
		try {
			int st = Integer.parseInt(start);
			int c = Integer.parseInt(count);
			String sql = "SELECT idTesoro FROM Tesoro WHERE creador LIKE '%" + creador + "%' AND fecha LIKE '%" + fecha + "%' "
					+ "AND dificultad LIKE '%" + dificultad + "%' AND tamanio LIKE '%" + tamanio + "%' AND tipoTerreno LIKE '%" 
					+ terreno + "%' LIMIT " + (st - 1) + "," + c + ";";
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
	
	//Elimina un tesoro
	@DELETE
	@Path("{idTesoro}")
	public Response deleteTesoro(@PathParam("idTesoro") @DefaultValue("%") String id) {
		try {
			String sql = "DELETE FROM Tesoro WHERE idTesoro = " + id + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			int affectedRows = ps.executeUpdate();
			if (affectedRows == 1)
				return Response.status(Response.Status.NO_CONTENT).build();
			else 
				return Response.status(Response.Status.NOT_FOUND).entity("Elemento no encontrado").build();		
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudieron convertir los índices a números").build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se pudo eliminar el tesoro\n" + e.getStackTrace()).build();
		}
	}

	//Un usuario crea un tesoro
	@POST 
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTesoro(Tesoro tesoro) {
		try {
			String sql = "INSERT INTO Tesoro (tamanio, fecha, dificultad, tipoTerreno, latitud, longitud, creador, pista) VALUES ("
					+ tesoro.getTamanio() + ", '" + tesoro.getFecha() + "', '" + tesoro.getDificultad() + "', '" + tesoro.getTerreno() + "', " 
					+ tesoro.getLatitud() + ", " + tesoro.getLongitud() + ", " + tesoro.getCreador() + ", '" + tesoro.getPista() + "');";
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			int affectedRows = ps.executeUpdate();
			ResultSet generatedID = ps.getGeneratedKeys();
			if (generatedID.next()) {
				tesoro.setId(generatedID.getInt(1));
				String location = uriInfo.getAbsolutePath() + "/" + tesoro.getId();
				return Response.status(Response.Status.CREATED).entity(tesoro).header("Location", location).header("Content-Location", location).build();
			}
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se pudo crear el tesoro").build();

		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se pudo crear el tesoro\n" + e.getStackTrace()).build();
		}
	}
	
	//Un usuario modifica un tesoro
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{idTesoro}")
	public Response updateUsuario(@PathParam("idTesoro") String idTe, Tesoro nuevoTesoro) {
		try {
			int idT = Integer.parseInt(idTe);
			Tesoro tesoro;
			String sql = "SELECT * FROM Tesoro WHERE idTesoro = " + idT + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tesoro =  tesoroFromRS(rs);
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Elemento no encontrado").build();
			}
			tesoro.setFecha(nuevoTesoro.getFecha());
			tesoro.setTamanio(nuevoTesoro.getTamanio());
			tesoro.setDificultad(nuevoTesoro.getDificultad());
			tesoro.setTerreno(nuevoTesoro.getTerreno());
			tesoro.setPista(nuevoTesoro.getPista());
			tesoro.setLatitud(nuevoTesoro.getLatitud());
			tesoro.setLongitud(nuevoTesoro.getLongitud());
			sql = "UPDATE Tesoro SET " 
					+ "creador = " + tesoro.getCreador()
					+ ", fecha = '" + tesoro.getFecha() 
					+ "', tamanio = " + tesoro.getTamanio()
					+ ", dificultad = '" + tesoro.getDificultad() 
					+ "', tipoTerreno = '" + tesoro.getTerreno()
					+ "', pista = '" + tesoro.getPista()
					+ "', latitud = " + tesoro.getLatitud()
					+ ", longitud = " + tesoro.getLongitud()
					+ " WHERE idTesoro = " + idT +";";
			ps = conn.prepareStatement(sql);
			int affectedRows = ps.executeUpdate();

			String location = uriInfo.getBaseUri() + "tesoros/" + tesoro.getId();
			return Response.status(Response.Status.OK).entity(tesoro).header("Content-Location", location).build();			
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudieron convertir los índices a números").build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se pudo actualizar el tesoro\n" + e.getStackTrace()).build();
		}
	}


	private Tesoro tesoroFromRS(ResultSet rs) throws SQLException {
		Tesoro tesoro = new Tesoro(rs.getInt("creador"), LocalDate.now(), rs.getInt("tamanio"), rs.getString("dificultad"), rs.getString("tipoTerreno"), rs.getString("pista"), 
				rs.getDouble("latitud"), rs.getDouble("longitud"));
		tesoro.setId(rs.getInt("idTesoro"));
		return tesoro;
	}
}
