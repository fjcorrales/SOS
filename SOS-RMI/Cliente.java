package clase.cliente;

import java.net.URI;
import java.time.LocalDate;
import java.util.Date;

import javax.print.attribute.standard.MediaTray;
import javax.smartcardio.ResponseAPDU;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import clase.datos.*;

public class Cliente{
    static WebTarget target;
        
    //MUCHO OJO A LO QUE LE HACEMOS EL PRINT
    public static void main(String []args){

        ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		target = client.target(getBaseURI());

        //LLAMADAS REFERENTES A LAS FUNCIONES DE USUARIOS
        System.out.println("Ejecutando funciones de usuarios\n");
        createUser();
        getUser();
        modifyUser();
        delUser();
        getUsers();
        getUsers1();            //Una prueba con filtros
        found();
        getFound();
        newFriend();
        getFriends();
        unfriend();
        System.out.println("Fin de ejecucion de funciones de usuarios\n");

        //LLAMADAS REFERENTES A FUNCIONES DE TESOROS
        System.out.println("Ejecutando funciones de tesoros\n");
        createTreasure();
        delTreasure();
        getTreasure();
        modifyTreasure();
        getTreasures();
        System.out.println("Fin de ejecucion de funciones de tesoros\n");

    }

    private static URI getBaseURI(){
        return UriBuilder.fromUri("http://localhost:8080/GeoETSIINF/api").build();
    }
    //Funciones donde voy a llamar a cada una de las funcionalidades del servicio REST

    /////////////////////USUARIOS/////////////////////
    
    //1- llamo al POST de crear usuario
    public static void createUser(){
        Usuario pepe = new Usuario();                          //Creo el usuario que voy a meter y a continuacion completo sus datos
        pepe.setNombre("pepe");
        pepe.setCorreo("pepemola@gmail.com");
        pepe.setEdad(25);
        pepe.setLocalidad("Guadalajara");
        Response r = target.path("usuarios").request().post(Entity.json(pepe));
        System.out.println(r.getStatus());
       // pepeID = r.readEntity(User.class).getId(); NO SE SI ES NECESARIO 
    }

    //2- llamo al GET de un usuario
    public static void getUser(){
        Response r = target.path("usuarios").path("2").request().accept(MediaType.APPLICATION_JSON).get();
        System.out.println(r.readEntity(Usuario.class));
    }

    //3- llamo al PUT de usuarios
    //NO SE SI ESTO JUJA
    public static void modifyUser(){
        Usuario pepe = new Usuario(); 
        pepe.setCorreo("pepemolamucho@gmail.com");
        pepe.setEdad(26);
        pepe.setLocalidad("Madrid");
        Response r = target.path("usuarios").path("2")
                            .queryParam("Usuario", pepe)
				            .request()
				            .put(Entity.entity(pepe, MediaType.APPLICATION_JSON));
        System.out.println(r);
    }

    //4- llamo al DELETE de usuario
    public static void delUser(){
        Response r = target.path("usuarios").path("2")
                           .request().accept(MediaType.APPLICATION_JSON)
                           .delete();
        System.out.println(r);
    }

    //5- llamo al GET de todos los usuarios
    public static void getUsers(){
        Response r = target.path("usuarios").request().accept(MediaType.APPLICATION_JSON).get();
        System.out.println(r.readEntity(Usuario.class));
    }
    //5.1- llamo al GET de todos los usuarios con un patron, en este caso, nombres con p
    public static void getUsers1(){
        Response r = target.path("usuarios").queryParam("p").request().accept(MediaType.APPLICATION_JSON).get();
        System.out.println(r.readEntity(Usuario.class));
    }

    //6- llamo al POST de tesoro encontrado por un usuario
    public static void found(){
        Tesoro t = new Tesoro();
        t.setDificultad("facil");
        t.setCreador(2);
        t.setFecha(LocalDate.now());
        t.setLatitud(3.82568);
        t.setLongitud(-9.46591);
        t.setPista("al amanecer del quinto dia mirad al este");
        t.setTamanio(50);
        t.setTerreno("agua");
        Response r = target.path("usuarios").path("2").path("encontrado")
                            .request().post(Entity.json(t));
        System.out.println(r.getStatus());
    }

    //7- llamo al GET de tesoros encontrados por un usuario
    public static void getFound(){
        Response r = target.path("usuarios").path("2").path("encontrado")
                            .request().accept(MediaType.APPLICATION_JSON).get();
        System.out.println(r.readEntity(Usuario.class));
    }

    //8- llamo al POST de un amigo por parte de un usuario
    public static void newFriend(){
        Usuario jose = new Usuario();                          //Creo el usuario que va a ser amigo y a continuacion completo sus datos
        jose.setNombre("jose");
        jose.setCorreo("joseguay@gmail.com");
        jose.setEdad(30);
        jose.setLocalidad("Extremadura");
        Response r = target.path("usuarios").path("2").path("amigos")
                            .request().post(Entity.json(jose));
        System.out.println(r.getStatus());
    }

    //9- llamo al GET de la lista de amigos de un usuario 
    public static void getFriends(){
        Response r = target.path("usuarios").path("2").path("amigos")
                            .request().accept(MediaType.APPLICATION_JSON).get();
        System.out.println(r.readEntity(Usuario.class));    
    }  

    //10- llamo al DELETE de un amigo de un usuario
    public static void unfriend(){
        Response r = target.path("usuarios").path("2").path("amigos").path("3")
                           .request().accept(MediaType.APPLICATION_JSON)
                           .delete();
        System.out.println(r);
    }

    /////////////////////TESOROS/////////////////////
    //1- llamo al POST de un tesoro creado por un usuario
    //he usado el mismo tesoro que en otra funcion, puede que haya que cambiar los valores
    public static void createTreasure(){
        Tesoro t = new Tesoro();
        t.setDificultad("facil");
        t.setCreador(2);
        t.setFecha(LocalDate.now());
        t.setLatitud(3.82568);
        t.setLongitud(-9.46591);
        t.setPista("al amanecer del quinto dia mirad al este");
        t.setTamanio(50);
        t.setTerreno("agua");
        Response r = target.path("tesoros").request().post(Entity.json(t));
        System.out.println(r.getStatus());
    }

    //2- llamo al DELETE de un tesoro
    public static void delTreasure(){
        Response r = target.path("tesoros")
                           .request().accept(MediaType.APPLICATION_JSON)
                           .delete();
        System.out.println(r);
    }

    //3- llamo al GET de un tesoro s
    public static void getTreasure(){
        Response r = (target.path("tesoros").request())
                            .accept(MediaType.APPLICATION_JSON).get();
        System.out.println(r.readEntity(Tesoro.class));
    }

    //4- llamo al PUT de un tesoro
    public static void modifyTreasure(){
        Tesoro t = new Tesoro();
        t.setDificultad("medio");
        t.setCreador(2);
        t.setFecha(LocalDate.now());
        t.setLatitud(2.82568);
        t.setLongitud(-8.46591);
        t.setPista("al amanecer del quinto dia mirad al oeste");
        t.setTamanio(25);
        t.setTerreno("tierra");
        Response r = target.path("tesoros").path("2")
                            .queryParam("nuevoTesoro", t)
				            .request()
				            .put(Entity.entity(t, MediaType.APPLICATION_JSON));
        System.out.println(r);
    }

    //5- llamo al GET del listado de todos los tesoros publicados por un usuario
    public static void getTreasures(){
        Response r = target.path("tesoros").path("2").request() 
                            .accept(MediaType.APPLICATION_JSON).get();
        System.out.println(r.readEntity(Tesoro.class));
    }
}
