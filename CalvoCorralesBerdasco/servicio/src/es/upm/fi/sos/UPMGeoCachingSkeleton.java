/**
 * UPMGeoCachingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package es.upm.fi.sos;


import java.rmi.RemoteException;
import java.util.*;

import es.upm.fi.sos.model.xsd.*;
import es.upm.fi.sos.t3.backend.*;
import es.upm.fi.sos.t3.backend.xsd.*;
import es.upm.fi.sos.t3.backend.xsd.Username;
import UpmAuthenticationAutorization.*;
import UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.AddUser;
import UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.UserBackEnd;

import org.apache.axis2.AxisFault;
/**
 * UPMGeoCachingSkeleton java skeleton for the axisService
 */
public class UPMGeoCachingSkeleton {

	//Declaracion de variables
	private static String Admin = "admin";
	private static String AdminPwd = "admin";
	private static ArrayList<String> usuarios = new ArrayList<String>();					//Lista que guarda los nombres de usuarios de aquellos que han hecho login
	private static ArrayList<String> tesoros = new ArrayList<String>();					//Lista de los nombres de todos los tesoros existentes 
	private static HashMap<String, ArrayList<String>> seguidoresDeUsuarios = new HashMap<String, ArrayList<String>>(); //Mapa que asocia usuarios con su lista de seguidores
	private static HashMap<String, Integer> sesionesIni = new HashMap<String, Integer>(); //Mapa que me relaciona un usuario con el numero de sesiones que tiene iniciadas
	private static HashMap<String, ArrayList<Treasure>> tesorosAUsuario = new HashMap<String, ArrayList<Treasure>>(); //Mapa que me relaciona usuarios con su lista de tesoros creados
	private static HashMap<String, ArrayList<Treasure>> encontradosPorUsuario = new HashMap<String, ArrayList<Treasure>>();//Mapa que me asocia un usuario a su lista de tesoros encontrados
	private static HashMap<String, String> usuarioContrasena = new HashMap<String, String>(); //Mapa que asocia usuarios con su lista de seguidores
	

	private static UPMAuthenticationAuthorizationWSSkeletonStub stub;
	/**
	 * Auto generated method signature
	 * Este metodo se encargara de gestionar los logouts de los usuarios, segun nos explican el funcionamiento es el siguiente:
	 * 		Si no existe una sesion iniciada, no se hace nada
	 * 		Si existe más de una sesión, solo se cierra una
	 * 		Si ya no hay sesiones se elimina al usuario de la lista de sesiones iniciadas
	 * @param logout
	 * @return
	 */
	User usuario = new User();

	public UPMGeoCachingSkeleton() throws AxisFault{	
		stub = new UPMAuthenticationAuthorizationWSSkeletonStub();
		stub._getServiceClient().getOptions().setManageSession(true);
		stub._getServiceClient().engageModule("addressing");
		seguidoresDeUsuarios.put("admin", new ArrayList<String>());
		tesorosAUsuario.put("admin", new ArrayList<Treasure>());
		encontradosPorUsuario.put("admin", new ArrayList<Treasure>());
		usuarioContrasena.put(Admin, AdminPwd);

	}

	//Comienzo del desarrollo del trabajo
	public void logout(es.upm.fi.sos.Logout logout) {

			usuario.setName(null);
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param removeFollower
	 * @return removeFollowerResponse
	 */

	public es.upm.fi.sos.RemoveFollowerResponse removeFollower(es.upm.fi.sos.RemoveFollower removeFollower) {

		
		String eliminar = removeFollower.getArgs0().getUsername();		//Nombre del usuario a eliminar
		RemoveFollowerResponse respuesta = new RemoveFollowerResponse();//Creamos el objeto a devolver
		Response aux = new Response();

		if(usuario!=null){
			String nombre = usuario.getName();								//Nombre del usuario que quiere eliminar un seguidor
			try{ 
				if(usuarios.contains(eliminar) && usuarios.contains(nombre)){		//comprueba si tanto el amigo como el usuario estan dados de alta
					if(seguidoresDeUsuarios.get(nombre).contains(eliminar)){	//comprueba si el usuario a eliminar esta en la lista de followers del usuario
						seguidoresDeUsuarios.get(nombre).remove(eliminar);
						aux.setResponse(true);
						respuesta.set_return(aux);
					}else{
						System.out.println("ERROR, el usuario a eliminar no es follower");
						aux.setResponse(false);
						respuesta.set_return(aux);
					}
				}else{
					System.out.println("ERROR, el usuario o amigo no existen o no estamos logueados");
					aux.setResponse(false);
					respuesta.set_return(aux);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return respuesta;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getMyTreasuresFound
	 * @return getMyTreasuresFoundResponse
	 */

	public es.upm.fi.sos.GetMyTreasuresFoundResponse getMyTreasuresFound(es.upm.fi.sos.GetMyTreasuresFound getMyTreasuresFound) {
		GetMyTreasuresFoundResponse respuesta = new GetMyTreasuresFoundResponse();

		TreasureList encontrados = new TreasureList();
		
		if(usuario.getName()!=null){		//Compruebo que el usuario esta logueado
			String user = usuario.getName();
			int aux = 0;
			Treasure[] tesoros1 = encontradosPorUsuario.get(user).toArray(new Treasure[0]);
			int tamanio = tesoros1.length;
			String[] nombres = new String[tamanio];
			double[] lats = new double[tamanio];
			double[] alts = new double[tamanio];
			for(int i = tamanio ; i>0 ; i--){//for para llenarme los arrays requeridos de tesoros encontrados de ultimo tesoro encontrado a primero
				nombres[aux] = tesoros1[i].getName();
				lats[aux] = tesoros1[i].getLatitude();
				lats[aux] = tesoros1[i].getAltitude();
				aux++;
			}
			System.out.println("Tesoros encontrados por " + user + ":\n");
			for(int i = 0 ; i< nombres.length ; i++){	//For para imprimir todos los tesoros encontrados
				System.out.println(i + "-" + nombres[i]);
			}
			encontrados.setNames(nombres);
			encontrados.setAlts(alts);
			encontrados.setLats(lats);
			encontrados.setResult(true);
		}else{
			System.out.println("ERROR, usuario no logueado");
			encontrados.setResult(false);
		}

		respuesta.set_return(encontrados);
		return respuesta;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getMyFollowers
	 * @return getMyFollowersResponse
	 */

	public es.upm.fi.sos.GetMyFollowersResponse getMyFollowers(es.upm.fi.sos.GetMyFollowers getMyFollowers) {
		GetMyFollowersResponse respuesta = new GetMyFollowersResponse();

		FollowerList seguidores = new FollowerList();

		
		if(usuario.getName()!=null){		//Compruebo que el usuario esta logueado
			String user = usuario.getName();
			if(seguidoresDeUsuarios.get(user).size()>0){		//si su lista de amigos no esta vacia 
				System.out.println("ENTRA EN EL LISTA DE AMIGOS NO VACIA");
				String[] followers = new String[seguidoresDeUsuarios.get(user).size()];
				for(int i = 0 ; i<seguidoresDeUsuarios.get(user).size() ; i++){
					followers[i] = seguidoresDeUsuarios.get(user).get(i);
				}
				seguidores.setFollowers(followers);
				seguidores.setResult(true);
				respuesta.set_return(seguidores);
			}else{										//si la lista esta vacia devuelvo true pero lista null
				System.out.println("ENTRA EN EL LISTA DE AMIGOS VACIA");
				String[] n = new String[20];
				seguidores.setFollowers(n);
				seguidores.setResult(true);
				respuesta.set_return(seguidores);
			}
		}else{
			System.out.println("ERROR, usuario no logueado");
			seguidores.setResult(false);
			respuesta.set_return(seguidores);
		}

		return respuesta;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getMyTreasuresCreated
	 * @return getMyTreasuresCreatedResponse
	 */

	public es.upm.fi.sos.GetMyTreasuresCreatedResponse getMyTreasuresCreated(es.upm.fi.sos.GetMyTreasuresCreated getMyTreasuresCreated) {
		GetMyTreasuresCreatedResponse respuesta = new GetMyTreasuresCreatedResponse();

		TreasureList creados = new TreasureList();
		String user = usuario.getName();
		if(usuario.getName()!=null){		//Compruebo que el usuario esta logueado

			int aux = 0;
			Treasure[] tesoros1 = tesorosAUsuario.get(user).toArray(new Treasure[0]);
			int tamanio = tesoros1.length;
			String[] nombres = new String[tamanio];
			double[] lats = new double[tamanio];
			double[] alts = new double[tamanio];
			for(int i = tamanio ; i>0 ; i--){//for para llenarme el array de nombres de tesoros encontrados de primer tesoro encontrado a ultimo
				nombres[aux] = tesoros1[i].getName();
				lats[aux] = tesoros1[i].getLatitude();
				lats[aux] = tesoros1[i].getAltitude();
				aux++;
			}
			System.out.println("Tesoros encontrados por " + user + ":\n");
			for(int i = 0 ; i< nombres.length ; i++){	//For para imprimir todos los tesoros creados
				System.out.println(i + "-" + nombres[i]);
			}
			creados.setNames(nombres);
			creados.setAlts(alts);
			creados.setLats(lats);
			creados.setResult(true);
		}else{
			System.out.println("ERROR, usuario no logueado");
			creados.setResult(false);
		}

		respuesta.set_return(creados);
		return respuesta;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param removeUser
	 * @return removeUserResponse
	 */

	public es.upm.fi.sos.RemoveUserResponse removeUser(es.upm.fi.sos.RemoveUser removeUser) {
		RemoveUserResponse respuesta = new RemoveUserResponse();	
		Response aux1 = new Response();
		UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.Username nombre = new UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.Username();							//nombre para rellenar la respuesta
		String eliminar = removeUser.getArgs0().getUsername();		//Nombre del usuario a eliminar
		nombre.setName(eliminar);   								//completamos el username para la respuesta con el nombre del usuario a eliminar

		if(usuario.getName()!=null){				//Comprobamos si estamos logueados
			if(usuarios.contains(eliminar)&&!eliminar.equals(Admin)&&(usuario.getName().equals(Admin) || usuario.getName().equals(eliminar))){// no podemos eliminar el admin

				try{
					UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUser remove = new UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUser();
					UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUserE removeE = new UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUserE();
					remove.setName(eliminar);
					removeE.setRemoveUser(remove);

					aux1.setResponse(stub.removeUser(removeE).get_return().getResult());

					ArrayList<Treasure> aux = tesorosAUsuario.get(eliminar);

					if(aux1.getResponse()){
						respuesta.set_return(aux1);
						usuarios.remove(eliminar);
						System.out.println(seguidoresDeUsuarios.size());
						System.out.println(seguidoresDeUsuarios.toString());
						for(String g : seguidoresDeUsuarios.keySet()){		//For para eliminar al usuario de las listas de followers de otros usuarios
							System.out.println(g);
							seguidoresDeUsuarios.get(g).remove(eliminar);
						}
						for(String g : encontradosPorUsuario.keySet()){						//For para eliminar los tesoros creados por el usuario a eliminar de las listas de encontrados de otros usuarios
							for(int j = 0 ; j<encontradosPorUsuario.get(g).size() ; j++){
								if(encontradosPorUsuario.get(g).contains(aux.get(j))){
									encontradosPorUsuario.get(g).remove(aux.get(j));
								}
							}
						}
						for(int i = 0 ; i<aux.size() ; i++){						//for para eliminar los tesoros creados por el usuario de la lista de tesoros
							for(int j = 0 ; j<tesoros.size() ; j++){
								if(aux.get(i).equals(tesoros.get(j))){
									tesoros.remove(j);
								}
							}
						}
						if(tesorosAUsuario.containsKey(eliminar)){					//If para eliminar la entrada de este usuaurio de sus tesoros creados
							tesorosAUsuario.remove(eliminar);
						}
					}else{
						System.out.println("ERROR, en la llamada al removeUser del stub");
						respuesta.set_return(aux1);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}else{
				System.out.println("ERROR, no se puede eliminar al usuario");
				respuesta.set_return(aux1);
			}
		}else{
			System.out.println("ERROR, no se ha iniciado sesion");
			respuesta.set_return(aux1);
		}


		return respuesta;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param addFollower
	 * @return addFollowerResponse
	 */

	public es.upm.fi.sos.AddFollowerResponse addFollower(es.upm.fi.sos.AddFollower addFollower) {
		AddFollowerResponse respuesta = new AddFollowerResponse();
		Response aux = new Response();
		
		String aniadir = addFollower.getArgs0().getUsername();			//Usuario a añadir a la lista de seguidores
		if(usuario!=null&& usuarios.contains(usuario.getName())){												//comprobamos si estamos logueados
			String user = usuario.getName();								//Usuario que quiere añadir un seguidor
			try{ 
				System.out.println("IMPRIMIMOS USUARIOS DEL SISTEMA///////////////////////////////////////////////////////");
				System.out.println(usuarios);
				if(usuarios.contains(aniadir)){		//comprueba si el amigo esta dado de alta
					if(!seguidoresDeUsuarios.get(user).contains(aniadir)){	//comprueba si el usuario a añadir es ya follower
						ArrayList<String> aux3 = seguidoresDeUsuarios.get(user);
						aux3.add(aniadir);
						seguidoresDeUsuarios.put(user, aux3);
						aux.setResponse(true);
						respuesta.set_return(aux);
					}else{
						System.out.println("ERROR, el usuario ya es follower");
						aux.setResponse(false);
						respuesta.set_return(aux);
					}
				}else{
					System.out.println("ERROR, el usuario o amigo no existen");
					aux.setResponse(false);
					respuesta.set_return(aux);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			System.out.println("ERROR, no estamos logueados");
			aux.setResponse(false);
			respuesta.set_return(aux);
		}
		return respuesta;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param createTreasure
	 * @return createTreasureResponse
	 */

	public es.upm.fi.sos.CreateTreasureResponse createTreasure(es.upm.fi.sos.CreateTreasure createTreasure) {
		CreateTreasureResponse respuesta = new CreateTreasureResponse();
		Response aux = new Response();
		
		
		String tesoro = createTreasure.getArgs0().getName();
		double lat = createTreasure.getArgs0().getLatitude();
		double alt = createTreasure.getArgs0().getAltitude();

		if(usuario.getName()!=null&&usuarios.contains(usuario.getName())){ //Si el usuario está logueado
			String user = usuario.getName();
			try{ 

				Treasure nuevo = new Treasure();
				nuevo.setAltitude(alt);
				nuevo.setLatitude(lat);
				nuevo.setName(tesoro);
				if(!tesoros.contains(nuevo)){//si no existe ya ese tesoro, lo  añado
					System.out.println("crear tesoro\n");
					ArrayList<Treasure> lt = tesorosAUsuario.get(user);
					lt.add(nuevo);
					tesorosAUsuario.put(user, lt);
					tesoros.add(tesoro);
					aux.setResponse(true);
					respuesta.set_return(aux);
				}else{//en otro caso, ya existe, por lo que hay que actualizar el tesoro existente
					System.out.println("actualizar tesoro\n");
					int pos = 0;
					for(int i = 0 ; i<tesorosAUsuario.get(user).size() ; i++){			//busco donde se encuentra ese tesoro en la lista
						if(tesorosAUsuario.get(user).get(i).getName().equals(tesoro)){
							pos = i;
							break;
						}
					}
					//y modifico los datos pertinentes
					tesorosAUsuario.get(user).get(pos).setAltitude(alt);
					tesorosAUsuario.get(user).get(pos).setLatitude(lat);
					aux.setResponse(true);
					respuesta.set_return(aux);
				}

			}catch(Exception e){
				e.printStackTrace();
			}	
		}else{
			System.out.println("ERROR, usuario no logueado");
			aux.setResponse(false);
			respuesta.set_return(aux);
		}

		return respuesta;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param findTreasure
	 * @return findTreasureResponse
	 * encontradosPorUsuario
	 */

	public es.upm.fi.sos.FindTreasureResponse findTreasure(es.upm.fi.sos.FindTreasure findTreasure) {
		FindTreasureResponse respuesta = new FindTreasureResponse();
		Response aux = new Response();

		
		Treasure nuevo = findTreasure.getArgs0();
		String tesoro = nuevo.getName();
		if(usuario.getName()!= null&&usuarios.contains(usuario.getName())){//compruebo login
			String user = usuario.getName();
			try{
				if(tesoros.contains(tesoro)){//compruebo si el tesoro existe previamente
					encontradosPorUsuario.get(user).add(nuevo);
					aux.setResponse(true);
					respuesta.set_return(aux);
				}else{
					System.out.println("ERROR, tesoro inexistente");
					aux.setResponse(false);
					respuesta.set_return(aux);
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			System.out.println("ERROR, usuario no logueado");
			aux.setResponse(false);
			respuesta.set_return(aux);
		}
		return respuesta;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param addUser
	 * @return addUserResponse
	 */

	public es.upm.fi.sos.AddUserResponse addUser(es.upm.fi.sos.AddUser addUser) {

		AddUserResponse respuesta = new AddUserResponse();
		es.upm.fi.sos.model.xsd.AddUserResponse aux = new es.upm.fi.sos.model.xsd.AddUserResponse();
		String aniadir = addUser.getArgs0().getUsername();			//Nombre del usuario a añadir
		//Creo los elementos para realizar la llamada a la funcion del stub
		UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.UserBackEnd user1 = new UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.UserBackEnd();
		UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.AddUser add = new UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.AddUser();
		user1.setName(aniadir);
		add.setUser(user1);								//completo el parametro de llamada a la funcion del stub addUser

		if(usuario.getName()!=null&&usuario.getName().equals(Admin)&&!usuarios.contains(aniadir)){						//Comprobamos si somos el admin y si el usuario esta ya contenido o no
			try{
				UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.AddUserResponse aux1 = stub.addUser(add);
				System.out.println(aux1.get_return().getResult() + "/////////////////////////////" );
				if(aux1.get_return().getResult()){
					usuarios.add(aniadir);												//guardo solo el usuario
					usuarioContrasena.put(aniadir, aux1.get_return().getPassword());	//Guardo el usuario con la contraseña generada por el stub
					//Inicializamos al usuario en los mapas correspondientes
					seguidoresDeUsuarios.put(aniadir, new ArrayList<String>());
					tesorosAUsuario.put(aniadir, new ArrayList<Treasure>());
					encontradosPorUsuario.put(aniadir, new ArrayList<Treasure>());
					//creamos respuesta
					aux.setResponse(true);
					aux.setPwd(aux1.get_return().getPassword());
					respuesta.set_return(aux);

				}else{
					System.out.println("ERROR, no se ha podido añadir el usuario");
					aux.setResponse(false);
					respuesta.set_return(aux);
				}
			}catch(Exception e){
				e.printStackTrace();
			}

		}else{
			System.out.println("ERROR, solo el admin puede añadir usuarios o el usuario ya existe");
			aux.setResponse(false);
			respuesta.set_return(aux);
		}

		return respuesta;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getMyFollowerTreasuresCreated
	 * @return getMyFollowerTreasuresCreatedResponse
	 */

	public es.upm.fi.sos.GetMyFollowerTreasuresCreatedResponse getMyFollowerTreasuresCreated(es.upm.fi.sos.GetMyFollowerTreasuresCreated getMyFollowerTreasuresCreated) {
		GetMyFollowerTreasuresCreatedResponse respuesta = new GetMyFollowerTreasuresCreatedResponse();

		String user = usuario.getName();												//usuario que invoca la operacion
		String follower = getMyFollowerTreasuresCreated.getArgs0().getUsername();		//amigo del usuario
		TreasureList creados = new TreasureList();

		try{
			if(usuarios.contains(user) && sesionesIni.containsKey(user)){//si ha iniciado sesion
				if(seguidoresDeUsuarios.get(user).contains(follower)){ //si son seguidores

					int aux = 0;
					Treasure[] tesoros1 = tesorosAUsuario.get(follower).toArray(new Treasure[0]);
					int tamanio = tesoros1.length;
					String[] nombres = new String[tamanio];
					double[] lats = new double[tamanio];
					double[] alts = new double[tamanio];
					if(tamanio != 0){
						for(int i = tamanio ; i>0 ; i--){//for para llenarme el array de nombres de tesoros encontrados de primer tesoro encontrado a ultimo
							nombres[aux] = tesoros1[i].getName();
							lats[aux] = tesoros1[i].getLatitude();
							lats[aux] = tesoros1[i].getAltitude();
							aux++;
						}
						System.out.println("Tesoros encontrados por " + user + ":\n");
						for(int i = 0 ; i< nombres.length ; i++){	//For para imprimir todos los tesoros creados
							System.out.println(i + "-" + nombres[i]);
						}
						creados.setNames(nombres);
						creados.setAlts(alts);
						creados.setLats(lats);
						creados.setResult(true);
						respuesta.set_return(creados);
					}else{											//si no ha creado tesoros, devolvemos las listas vacías y true
						creados.setResult(true);
						String[] n = {};
						double[] a = {};
						double[] l = {};
						creados.setNames(n);
						creados.setAlts(a);
						creados.setLats(l);
						respuesta.set_return(creados);
					}
				}else{
					System.out.println("ERROR, el usuairo seleccionado no es seguidor");
					creados.setResult(false);
					respuesta.set_return(creados);
				}
			}else{
				System.out.println("ERROR, el usuairo no está logueado");
				creados.setResult(false);
				respuesta.set_return(creados);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return respuesta;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param changePassword
	 * @return changePasswordResponse
	 */

	public es.upm.fi.sos.ChangePasswordResponse changePassword(es.upm.fi.sos.ChangePassword changePassword) {
		ChangePasswordResponse respuesta = new ChangePasswordResponse();
		Response aux = new Response();
		String vieja = changePassword.getArgs0().getOldpwd();
		String nueva = changePassword.getArgs0().getNewpwd();
		System.out.println("PRUEBA DE CHANGE PWD " + usuario.getName()+ " " + vieja + " " + usuario.getName()!=null);
		if(usuario.getName()!=null&&usuarios.contains(usuario.getName())){ //si estamos logueados y si existe el usuario
			String user = usuario.getName();
			if(user.equals(Admin)&& vieja.equals(AdminPwd)){		//si somos el admin cambiamos sin usar el UPMAuthen
				AdminPwd = nueva;
				usuario.setPwd(nueva);
				usuarioContrasena.put(Admin, nueva);
				aux.setResponse(true);
			}else{
				if(usuarioContrasena.get(user).equals(vieja)){		//Compruebo si el usuario ha introducido su contraseña antigua correctamente
					try {
						UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePasswordBackEnd BEpwd = new UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePasswordBackEnd();
						UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePassword newPwd = new UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePassword();
						BEpwd.setOldpwd(vieja);
						BEpwd.setNewpwd(nueva);
						BEpwd.setName(user);
						newPwd.setChangePassword(BEpwd);
						boolean exito = stub.changePassword(newPwd).get_return().getResult();
						if(exito){
							usuario.setPwd(nueva);
							usuarioContrasena.put(user, nueva);
							aux.setResponse(true);
							
						}else{
							aux.setResponse(false);
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else{
					System.out.println("ERROR, la contraseña vieja no coincide");
					aux.setResponse(false);
				}
			}
		}else{
			System.out.println("ERROR, no se ha iniciado sesion");
			aux.setResponse(false);
		}
		
		respuesta.set_return(aux);
		return respuesta;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param login
	 * @return loginResponse
	 */

	public es.upm.fi.sos.LoginResponse login(es.upm.fi.sos.Login login) {
		LoginResponse respuesta = new LoginResponse();
		User user = login.getArgs0();				//usuario a hacer login
		String nombre = user.getName();				//nombre del usuario
		String pwd = user.getPwd();					//Contraseña del usuario
		System.out.println(nombre);
		if(usuario.getName()!=null)System.out.println(usuario.getName());
		
		Response aux1 = new Response();
		UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.Login log1 = new UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.Login();
		UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.LoginBackEnd log = new UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.LoginBackEnd ();
		log.setName(nombre);
		log.setPassword(pwd);
		log1.setLogin(log);
		if(!nombre.equals(Admin) ){		//el login del admin lo gestiona el stub y tenemos que ser el mismo usuario
			if(usuario.getName()!=null && nombre.equals(usuario.getName())){				//si ya tengo un login con una sesion iniciada, siempre devuelvo true
				aux1.setResponse(true);
				respuesta.set_return(aux1);
			}else if(usuario.getName()!=null&&!nombre.equals(usuario.getName())){																//si es el primer login que hago, creo el usuario
				System.out.println("El usuario que intenta hacer login es distinto del autenticado");
				aux1.setResponse(false);
				respuesta.set_return(aux1);
			}else{

				try {
					UpmAuthenticationAutorization.UPMAuthenticationAuthorizationWSSkeletonStub.LoginResponse l= stub.login(log1);
					if(l.get_return().getResult()){
						usuario = user;
						aux1.setResponse(true);
						respuesta.set_return(aux1);
					}else{
						System.out.println("ERROR, nombre de usuario o contraseña incorrectos");
						aux1.setResponse(false);
						respuesta.set_return(aux1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		}else{
			System.out.println("admin login");
			if(nombre.equals(Admin) && pwd.equals(AdminPwd)){
				usuario = user;
				usuarios.add(Admin);
				aux1.setResponse(true);
				respuesta.set_return(aux1);
				
			}
		}

		return respuesta;
	}

}
