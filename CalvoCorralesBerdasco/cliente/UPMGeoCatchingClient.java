package client;
import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import client.UPMGeoCachingStub.AddUser;
import client.UPMGeoCachingStub.AddUserResponseE;
import client.UPMGeoCachingStub.GetMyFollowersResponse;
import client.UPMGeoCachingStub.Logout;
import client.UPMGeoCachingStub.RemoveUserResponse;

import java.util.*;


public class UPMGeoCatchingClient{



	static HashMap<String, String> contrasenas = new HashMap<String, String>();

	static UPMGeoCachingStub client(){
		try{ 
			UPMGeoCachingStub cliente = new UPMGeoCachingStub();
			cliente._getServiceClient().getOptions().setManageSession(true);
			cliente._getServiceClient().engageModule("addressing");
			return cliente;
		}
		catch (AxisFault e){
			e.printStackTrace();
			return null;
		}
		
	}



	static boolean login(UPMGeoCachingStub cliente, String nombre, String contrasena){
		client.UPMGeoCachingStub.Login lg = new client.UPMGeoCachingStub.Login();
		client.UPMGeoCachingStub.User usuario = new client.UPMGeoCachingStub.User();
		usuario.setPwd(contrasena);
		usuario.setName(nombre);
		lg.setArgs0(usuario);
		try{
			client.UPMGeoCachingStub.LoginResponse respuesta = cliente.login(lg);
			return respuesta.get_return().getResponse();
		}
		catch(RemoteException e){
			e.printStackTrace();
			return false;
		}
	}

	static void logout(UPMGeoCachingStub cliente){
		Logout lgo = new Logout();
		try{
			cliente.logout(lgo);
		}
		catch(RemoteException e){
			e.printStackTrace();
		}
	}

	static boolean removeUser(UPMGeoCachingStub cliente, String nombre) {
		RemoveUserResponse respuesta3;
		client.UPMGeoCachingStub.RemoveUser usuarioElim = new client.UPMGeoCachingStub.RemoveUser();
		client.UPMGeoCachingStub.Username usuario2 = new client.UPMGeoCachingStub.Username();
		usuario2.setUsername(nombre);
		usuarioElim.setArgs0(usuario2);
		try {
			respuesta3 = cliente.removeUser(usuarioElim);
			return respuesta3.get_return().getResponse();
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	static AddUserResponseE addUser(UPMGeoCachingStub cliente, String nombre) {
		client.UPMGeoCachingStub.AddUserResponseE respuesta2;
		client.UPMGeoCachingStub.AddUser usuario1 = new AddUser();
		client.UPMGeoCachingStub.Username nombre1 = new client.UPMGeoCachingStub.Username();
		nombre1.setUsername(nombre);
		usuario1.setArgs0(nombre1);
		try {
			respuesta2 = cliente.addUser(usuario1);
			return respuesta2;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	} 

	static boolean changePassword(UPMGeoCachingStub cliente, String contrasenaAnt, String contrasenaNueva) {
		client.UPMGeoCachingStub.ChangePasswordResponse respuesta4;
		client.UPMGeoCachingStub.ChangePassword cambioCont = new client.UPMGeoCachingStub.ChangePassword();
		client.UPMGeoCachingStub.PasswordPair par = new client.UPMGeoCachingStub.PasswordPair();
		par.setNewpwd(contrasenaNueva);
		par.setOldpwd(contrasenaAnt);
		cambioCont.setArgs0(par);
		try {
			respuesta4 = cliente.changePassword(cambioCont);
			return respuesta4.get_return().getResponse();
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	static boolean addFollower(UPMGeoCachingStub cliente, String seguidor) {
		client.UPMGeoCachingStub.AddFollowerResponse respuesta5;
		client.UPMGeoCachingStub.AddFollower seguidorAnadir = new client.UPMGeoCachingStub.AddFollower();
		client.UPMGeoCachingStub.Username nombre2 = new client.UPMGeoCachingStub.Username();
		nombre2.setUsername(seguidor);
		seguidorAnadir.setArgs0(nombre2);
		try {
			respuesta5 = cliente.addFollower(seguidorAnadir);
			return respuesta5.get_return().getResponse();
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	static boolean removeFollower(UPMGeoCachingStub cliente, String seguidor) {
		client.UPMGeoCachingStub.RemoveFollowerResponse respuesta6;
		client.UPMGeoCachingStub.RemoveFollower seguidorElim = new client.UPMGeoCachingStub.RemoveFollower();
		client.UPMGeoCachingStub.Username nombre3 = new client.UPMGeoCachingStub.Username();
		nombre3.setUsername(seguidor);
		seguidorElim.setArgs0(nombre3);
		try {
			respuesta6 = cliente.removeFollower(seguidorElim);
			return respuesta6.get_return().getResponse();
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	static GetMyFollowersResponse getMyFollowers(UPMGeoCachingStub cliente) {
		client.UPMGeoCachingStub.GetMyFollowersResponse respuesta7;
		client.UPMGeoCachingStub.GetMyFollowers listaSeguidores = new client.UPMGeoCachingStub.GetMyFollowers();
		try {
			respuesta7 = cliente.getMyFollowers(listaSeguidores);
			return respuesta7;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	static boolean createTreasure(UPMGeoCachingStub cliente, String tesoro, double alt, double lat) {
		client.UPMGeoCachingStub.CreateTreasureResponse respuesta8;
		client.UPMGeoCachingStub.CreateTreasure tesoroCreado = new client.UPMGeoCachingStub.CreateTreasure();
		client.UPMGeoCachingStub.Treasure tesoro1 = new client.UPMGeoCachingStub.Treasure();
		tesoro1.setName(tesoro);
		tesoro1.setAltitude(alt);
		tesoro1.setLatitude(lat);
		tesoroCreado.setArgs0(tesoro1);
		try {
			respuesta8 = cliente.createTreasure(tesoroCreado);
			return respuesta8.get_return().getResponse();
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	static boolean findTreasure(UPMGeoCachingStub cliente, String tesoro,double alt, double lat){
		client.UPMGeoCachingStub.FindTreasureResponse respuesta9;
		client.UPMGeoCachingStub.FindTreasure tesoroEncontrado = new client.UPMGeoCachingStub.FindTreasure();
		client.UPMGeoCachingStub.Treasure tesoro2 = new client.UPMGeoCachingStub.Treasure();
		tesoro2.setName(tesoro);
		tesoro2.setAltitude(alt);
		tesoro2.setLatitude(lat);
		tesoroEncontrado.setArgs0(tesoro2);
		try {
			respuesta9 = cliente.findTreasure(tesoroEncontrado);
			return respuesta9.get_return().getResponse();
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	static boolean getMyTreasuresFound(UPMGeoCachingStub cliente){
		client.UPMGeoCachingStub.GetMyTreasuresFoundResponse respuesta10;
		client.UPMGeoCachingStub.GetMyTreasuresFound tesorosEncontrados = new client.UPMGeoCachingStub.GetMyTreasuresFound();
		try {
			respuesta10 = cliente.getMyTreasuresFound(tesorosEncontrados); 
			return respuesta10.get_return().getResult();
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	static boolean getMyTreasuresCreated(UPMGeoCachingStub cliente){
		client.UPMGeoCachingStub.GetMyTreasuresCreatedResponse respuesta11;
		client.UPMGeoCachingStub.GetMyTreasuresCreated tesorosCreados = new client.UPMGeoCachingStub.GetMyTreasuresCreated();
		try {
			respuesta11 = cliente.getMyTreasuresCreated(tesorosCreados); 
			return respuesta11.get_return().getResult();
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	static boolean getMyFollowerTreasuresCreated(UPMGeoCachingStub cliente, String nombre){
		client.UPMGeoCachingStub.GetMyFollowerTreasuresCreatedResponse respuesta12;
		client.UPMGeoCachingStub.GetMyFollowerTreasuresCreated tesorosCreadosSeguidor = new client.UPMGeoCachingStub.GetMyFollowerTreasuresCreated();
		client.UPMGeoCachingStub.Username nombre4 = new client.UPMGeoCachingStub.Username();
		nombre4.setUsername(nombre);
		tesorosCreadosSeguidor.setArgs0(nombre4);
		try {
			respuesta12 = cliente.getMyFollowerTreasuresCreated(tesorosCreadosSeguidor);
			return respuesta12.get_return().getResult();
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}



	static void testLoginAddRemove(){
		UPMGeoCachingStub cliente = client();
		boolean ok;
		ok = login(cliente, "admin", "admin");
		if (!ok)
			System.out.println("Login administrador incorrecto -> MAL");
		else
			System.out.println("Login administrador correcto -> OK");

		logout(cliente);



		String usuario1 = "juan11";
		String usuario2 = "marta11";
		String contrasena2 = "1111";
		login(cliente, "admin", "admin");
		AddUserResponseE resp = addUser(cliente, usuario1);
		ok = resp.get_return().getResponse();
		String contrasena1 = resp.get_return().getPwd();
		if (!ok)
			System.out.println("No se ha creado correctamente el usuario -> MAL");
		else
			System.out.println("Se ha creado correctamente el usuario -> OK");

		logout(cliente);
		ok = login(cliente, usuario2, contrasena2);
		if (ok)
			System.out.println("Se hace login de un usuario que no existe -> MAL");
		else
			System.out.println("No se hace login porque el usuario no existe -> OK");
		
		ok = login(cliente, usuario1, contrasena1);
		if (!ok)
			System.out.println("Se hace login de un usuario erroneamente -> MAL");
		else
			System.out.println("Se hace login -> OK");

		login(cliente, "admin", "admin");
		ok = removeUser(cliente, usuario1);
		logout(cliente);
		if (!ok)
			System.out.println("No se ha borrado correctamente el usuario -> MAL");
		else
			System.out.println("Se ha borrado correctamente el usuario -> OK");
		AddUserResponseE resp2 = addUser(cliente, usuario2);
		ok = resp2.get_return().getResponse();
		if (ok)
			System.out.println("No se debería haber creado el usuario(esta fuera del login) -> MAL");
		else
			System.out.println("No se ha creado el usuario fuerea del login -> OK");
	}


	static void testChangePsw(){
		UPMGeoCachingStub cliente = client();
		boolean ok;

		String contrasena = "adminNuevo";
		login(cliente, "admin", "admin");
		ok = changePassword(cliente, "admin", contrasena);
		logout(cliente);
		if (!ok)
			System.out.println("No se ha cambiado correctamente la contraseña del administrador -> MAL");
		else
			System.out.println("Se ha cambiado correctamente la contraseña del administrador -> OK");
		
		if(!login(cliente, "admin", contrasena)){
			System.out.println("Error, no se pudo iniciar sesion con nueva contraseña de admin");
		}
		changePassword(cliente, contrasena, "admin");
		logout(cliente);
		String usuario1 = "pedro11";
		login(cliente, "admin", "admin");
		AddUserResponseE resp = addUser(cliente, usuario1);
		//System.out.println(resp.get_return().getResponse()+ "PRUEBA DE CLIEMNTE " + resp.get_return().getPwd());
		String contrasena1 = resp.get_return().getPwd();
		logout(cliente);
		if(!login(cliente, usuario1, contrasena1)){
			System.out.println("no se hizo bien el login");
		}
		ok = changePassword(cliente, contrasena1, "rabano");
		logout(cliente);
		if (!ok)
			System.out.println("No se ha cambiado correctamente la contraseña del usuario -> MAL");
		else
			System.out.println("Se ha cambiado correctamente la contraseña del usuario -> OK");

		ok = changePassword(cliente, contrasena1, contrasena);
		if (ok)
			System.out.println("No se puede cambiar la contraseña sin hacer login -> MAL");
		else
			System.out.println("No se ha cambiado la contraseña sin login -> OK");
		

		login(cliente, "admin", "admin");
		removeUser(cliente, usuario1);
		logout(cliente);

	}


	static void testAddRemoveFollower(){
		UPMGeoCachingStub cliente = client();
		boolean ok;
		String usuario1 = "paula4";
		String usuario2 = "angela5";
		login(cliente, "admin", "admin");
		AddUserResponseE resp = addUser(cliente, usuario1);
		String contrasena1 = resp.get_return().getPwd();
		logout(cliente);
		login(cliente, usuario1, contrasena1);
		ok = addFollower(cliente, usuario2);
		
		//test para comprobar si los errores van bien
		if (ok)
			System.out.println("No se puede añadir un seguidor que no está en la BBDD -> MAL");
		else
			System.out.println("No se ha añadido el seguidor -> OK");

		ok = removeFollower(cliente, usuario2);
		if (ok)
			System.out.println("No se puede borrar un seguidor que no está en la lista de seguidores -> MAL");
		else
			System.out.println("No se ha borrado el seguidor -> OK");

		logout(cliente);
		ok = addFollower(cliente, usuario2);	
		if (ok)
			System.out.println("No se puede añadir un seguidor sin hacer login -> MAL");
		else
			System.out.println("No se ha añadido el seguidor -> OK");

		login(cliente, "admin", "admin");
		AddUserResponseE resp2 = addUser(cliente, usuario2);
		String contrasena2 = resp2.get_return().getPwd();
		logout(cliente);
		login(cliente, usuario1, contrasena1);
		ok = addFollower(cliente, usuario2);
		if (!ok)
			System.out.println("No se ha añadido correctamente el seguidor -> MAL");
		else
			System.out.println("Se ha añadido correctamente el seguidor -> OK");

		ok = removeFollower(cliente, usuario2);
		if (!ok)
			System.out.println("No se ha borrado correctamente el seguidor -> MAL");
		else
			System.out.println("Se ha borrado correctamente el seguidor -> OK");

		ok = removeFollower(cliente, usuario2);
		if (ok)
			System.out.println("No se puede borrar un seguidor que ya se ha borrado -> MAL");
		else
			System.out.println("No se ha borrado el seguidor por segunda vez -> OK");

		addFollower(cliente, usuario2);
		ok = addFollower(cliente, usuario2);
		logout(cliente);
		if (ok)
			System.out.println("Ya se había añadido el seguidor -> MAL");
		else
			System.out.println("No se ha vuelto a añadir el seguidor -> OK");

		login(cliente, "admin", "admin");
		removeUser(cliente, usuario2);
		removeUser(cliente, usuario1);
		logout(cliente);
	}


	static void testGetMyFollowers(){
		UPMGeoCachingStub cliente = client();
		boolean ok;
		String usuario1 = "ana17";
		String usuario2 = "dani17";
		//String[] listaSeguidores;
		login(cliente, "admin", "admin");
		AddUserResponseE resp1 = addUser(cliente, usuario1);
		AddUserResponseE resp2 = addUser(cliente, usuario2);
		String contrasena1 = resp1.get_return().getPwd();
		String contrasena2 = resp2.get_return().getPwd();
		
		logout(cliente);
		login(cliente, usuario1, contrasena1);
		GetMyFollowersResponse resp = getMyFollowers(cliente);
		String[] aux = resp.get_return().getFollowers();
		if (!(resp.get_return().getFollowers().length==0))
			System.out.println("No devuelve la lista -> MAL");
		else
			System.out.println("Devuelve una lista vacía -> OK");

		addFollower(cliente, usuario2);
		GetMyFollowersResponse resp3 = getMyFollowers(cliente);
		if (resp3.get_return().getFollowers().length == 0)
			System.out.println("No devuelve la lista de seguidores -> MAL");
		else
			System.out.println("Devuelve la lista de seguidores -> OK");
		logout(cliente);
		login(cliente, "admin", "admin");
		removeUser(cliente, usuario2);
		removeUser(cliente, usuario1);
		logout(cliente);
	}

	static void testCreateFindTreasure(){//se realizaron cambios aqui y en la funcuión de arriba de createTreasure, por si hace falta modificarlo otra vez
		UPMGeoCachingStub cliente = client();
		boolean ok;
		String usuario = "antonio1";
		//String[] tesorosEncontrados;
		client.UPMGeoCachingStub.Treasure tesoro = new client.UPMGeoCachingStub.Treasure();
		client.UPMGeoCachingStub.Treasure tesoro1 = new client.UPMGeoCachingStub.Treasure();
		tesoro.setName("tesoro1");
		tesoro.setLatitude(126);
		tesoro.setAltitude(68);
		if(!login(cliente, "admin", "admin")){
			System.out.println("ERROR log admin");
		}
		AddUserResponseE respAdd = addUser(cliente, usuario);
		String contrasena = respAdd.get_return().getPwd();
		logout(cliente);
		ok = createTreasure(cliente, "tesoro1", 126.3, 56.2);
		if (ok)
			System.out.println("Crea el tesoro a pesar de estar sin el login -> MAL");
		else
			System.out.println("No crea el tesoro -> OK");

		ok = findTreasure(cliente, "tesoro1", 126.3, 56.2);
		if (ok)
			System.out.println("Añade el tesoro a la lista de tesoros encontrados sin haber hecho el login -> MAL");
		else
			System.out.println("No añade el tesoro a la lista de tesoros encontrados porque no se ha hecho el login -> OK");

		if(!login(cliente, usuario, contrasena)){
			System.out.println("ERROR log usuario");
		}
		ok = findTreasure(cliente, "tesoro1", 126.3, 56.2);
		if (ok)
			System.out.println("Añade el tesoro a la lista de tesoros encontrados aunque no se ha creado -> MAL");
		else
			System.out.println("No añade el tesoro a la lista de tesoros encontrados porque no se ha creado-> OK");

		tesoro1.setName("tesoro2");
		tesoro1.setLatitude(126);
		tesoro1.setAltitude(68);
		ok = createTreasure(cliente, "tesoro2",126.3, 56.2);
		if (!ok)
			System.out.println("No crea el tesoro -> MAL");
		else
			System.out.println("Crea el tesoro correctamente -> OK");

		ok = findTreasure(cliente, "tesoro2",126.3, 56.2);
		if (!ok)
			System.out.println("No añade el tesoro a la lista de tesoros encontrados -> MAL");
		else
			System.out.println("Añade el tesoro a la lista de tesoros encontrados -> OK");

		tesoro.setLatitude(-23.45);
		tesoro.setAltitude(30);
		ok = createTreasure(cliente, "tesoro1", -23.45, 30.00);
		logout(cliente);
		if (!ok)
			System.out.println("No actualiza el tesoro -> MAL");
		else
			System.out.println("Actualiza el tesoro correctamente -> OK");

		if(!login(cliente, "admin", "admin")){
			System.out.println("ERROR log admin");
		}
		removeUser(cliente, usuario);
		logout(cliente);
	}

	static void testgetMyTreasuresFoundCreated(){
		UPMGeoCachingStub cliente = client();
		boolean ok;
		String usuario = "antonio";
		
		//String[] tesorosEncontrados;
		client.UPMGeoCachingStub.Treasure tesoro = new client.UPMGeoCachingStub.Treasure();
		tesoro.setName("tesoro1");
		tesoro.setLatitude(126);
		tesoro.setAltitude(68);
		client.UPMGeoCachingStub.Treasure tesoro2 = new client.UPMGeoCachingStub.Treasure();
		tesoro.setName("tesoro2");
		tesoro.setLatitude(16);
		tesoro.setAltitude(-168.76);
		login(cliente, "admin", "admin");
		AddUserResponseE resp1 = addUser(cliente, usuario);
		String contrasena = resp1.get_return().getPwd();
		logout(cliente);
		login(cliente, usuario, contrasena);
		ok = getMyTreasuresCreated(cliente);
		if (!ok)
			System.out.println("No devuelve la lista vacía de tesoros creados -> MAL");
		else
			System.out.println("Devuelve lista vacía de tesoros creados -> OK");

		ok = getMyTreasuresFound(cliente);
		if (!ok)
			System.out.println("No devuelve la lista vacía de tesoros encontrados -> MAL");
		else
			System.out.println("Devuelve lista vacía de tesoros encontrados -> OK");

		createTreasure(cliente, "tesoro1", 126.00, 68.00);
		createTreasure(cliente, "tesoro2", 16.00, -168.76);
		findTreasure(cliente, "tesoro1", 126.00, 68.00);
		ok = getMyTreasuresCreated(cliente);
		if (!ok)
			System.out.println("No devuelve la lista de tesoros creados -> MAL");
		else
			System.out.println("Devuelve lista de tesoros creados -> OK");

		ok = getMyTreasuresFound(cliente);
		if (!ok)
			System.out.println("No devuelve la lista de tesoros encontrados -> MAL");
		else
			System.out.println("Devuelve lista de tesoros encontrados -> OK");

		logout(cliente);
		ok = getMyTreasuresCreated(cliente);
		if (ok)
			System.out.println("Devuelve la lista de tesoros creados sin login -> MAL");
		else
			System.out.println("No devuelve lista de tesoros creados sin login -> OK");

		ok = getMyTreasuresFound(cliente);
		if (ok)
			System.out.println("Devuelve la lista de tesoros encontrados sin login -> MAL");
		else
			System.out.println("No devuelve lista de tesoros encontrados sin login -> OK");

		login(cliente, "admin", "admin");
		removeUser(cliente, usuario);
		logout(cliente);
	}


	static void testGetMyFollowersTreasuresCreated(){
		UPMGeoCachingStub cliente = client();
		boolean ok;
		String usuario = "antonio";
		String usuario2 = "pedro";
		//String[] tesorosEncontrados;
		client.UPMGeoCachingStub.Treasure tesoro = new client.UPMGeoCachingStub.Treasure();
		tesoro.setName("tesoro");
		tesoro.setLatitude(126);
		tesoro.setAltitude(68);
		client.UPMGeoCachingStub.Treasure tesoro2 = new client.UPMGeoCachingStub.Treasure();
		tesoro.setName("tesoro2");
		tesoro.setLatitude(16);
		tesoro.setAltitude(-168.76);
		login(cliente, "admin", "admin");
		AddUserResponseE resp1 = addUser(cliente, usuario);
		AddUserResponseE resp2 = addUser(cliente, usuario2);
		String contrasena = resp1.get_return().getPwd();
		String contrasena2 = resp2.get_return().getPwd();
		logout(cliente);
		login(cliente, usuario, contrasena);
		ok = getMyFollowerTreasuresCreated(cliente, usuario2);
		if (ok)
			System.out.println("Devuelve la lista de tesoros de uno que no es seguidor -> MAL");
		else
			System.out.println("No devuelve lista de tesoros de un no seguidor -> OK");

		addFollower(cliente, usuario2);
		ok = getMyFollowerTreasuresCreated(cliente, usuario2);
		if (!ok)
			System.out.println("No devuelve la lista de tesoros del seguidor vacía -> MAL");
		else
			System.out.println("Devuelve lista de tesoros del seguidor vacía -> OK");

		logout(cliente);
		login(cliente, usuario2, contrasena2);
		createTreasure(cliente, "tesoro", 126.00, 68.00);
		createTreasure(cliente, "tesoro2", 16, -168.00);
		logout(cliente);
		login(cliente, usuario, contrasena);
		ok = getMyFollowerTreasuresCreated(cliente, usuario2);
		if (!ok)
			System.out.println("No devuelve la lista de tesoros creados del seguidor -> MAL");
		else
			System.out.println("Devuelve lista de tesoros creados del seguidor -> OK");

		logout(cliente);
		ok = getMyFollowerTreasuresCreated(cliente, usuario2);//fuera del login
		if (ok)
			System.out.println("Devuelve la lista de tesoros del seguidor sin login -> MAL");
		else
			System.out.println("No devuelve lista de tesoros del seguidor sin login -> OK");

		login(cliente, "admin", "admin");
		removeUser(cliente, usuario);
		removeUser(cliente, usuario2);
		logout(cliente);
	}

	public static void main(String[] args) throws AxisFault, RemoteException {

		client.UPMGeoCachingStub cliente;
		try {
			cliente = new UPMGeoCachingStub();
			cliente._getServiceClient().getOptions().setManageSession(true);
			cliente._getServiceClient().engageModule("addressing");
			testLoginAddRemove();
			testChangePsw();
			testAddRemoveFollower();
			testGetMyFollowers();
			testCreateFindTreasure();
			testgetMyTreasuresFoundCreated();
			testGetMyFollowersTreasuresCreated();

		} catch (AxisFault e) {
			e.printStackTrace();
		}
	}
}