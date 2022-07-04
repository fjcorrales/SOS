package clase.recursos.bbdd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.NoSuchElementException;

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

import clase.datos.Aplicacion;
import clase.datos.Link;
import clase.datos.Tesoro;
import clase.datos.Tesoros;
import clase.datos.Usuario;

@Path("/aplicacion")
public class AplicacionRecurso {
	@Context
	private UriInfo uriInfo;

	private DataSource ds;
	private Connection conn;

	public AplicacionRecurso() {
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

	@GET
	@Path("{idUsuario}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getApp(@PathParam("idUsuario") String idU, Aplicacion app){
		try{	
			int idUs = Integer.parseInt(idU);
			Aplicacion a = new Aplicacion();
			String sql = "SELECT * FROM Usuario WHERE idUsuario = " + idUs + " ;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			String sql2 = "SELECT COUNT(Tesoro_idTesoro) FROM Usuario_encuentra_Tesoro WHERE creador = " + idUs + " ;";
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery();

			String sql3 = "SELECT idTesoro FROM Tesoro WHERE idTesoro = (SELECT idTesoro FROM Usuario_encuentra_Tesoro WHERE creador = " 
						+ idUs +  ") ORDER BY fecha DESC LIMIT 5;";
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			ResultSet rs3 = ps3.executeQuery();

			String sql4 = "SELECT COUNT(Usuario_idAmigo) FROM Usuario_esamigode_Usuario WHERE creador = " + idUs + " ;";
			PreparedStatement ps4 = conn.prepareStatement(sql4);
			ResultSet rs4 = ps4.executeQuery();

			String sql5 = "SELECT COUNT(creador) FROM Tesoro WHERE creador = " + idUs + " ;";
			PreparedStatement ps5 = conn.prepareStatement(sql5);
			ResultSet rs5 = ps5.executeQuery();

			a =  aplicacionFromRS(rs, rs2, rs3, rs4, rs5);

			a.setId(idUs);
			a.setEdad(app.getEdad());;
			a.setLocalidad(app.getLocalidad());
			a.setCorreo(app.getCorreo());
			a.setNTesorosE(app.getNTesorosE());
			a.setUltimos(app.getUltimos());
			a.setNAmigos(app.getNAmigos());
			a.setNTesorosA(app.getNTesorosA());

			return Response.status(Response.Status.OK).entity(a).build();
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudieron convertir los índices a números").build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error de acceso a BBDD").build();
		} catch (NoSuchElementException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("NoSuchElementException").build();
		}
	} 

	private Aplicacion aplicacionFromRS(ResultSet rs, ResultSet rs2, ResultSet rs3, ResultSet rs4, ResultSet rs5) throws SQLException {
		Aplicacion a = new Aplicacion(rs.getString("nombre"), rs.getString("correo"), rs.getInt("edad"), rs.getString("localidad"), 
				rs2.getInt("nTesorosE"), (ArrayList<Integer>) rs3.getArray("TesorosEnc"), rs4.getInt("nAmigos"), rs5.getInt("nTesorosA"));
		a.setId(rs.getInt("idUsuario"));
		return a;
	}
}




