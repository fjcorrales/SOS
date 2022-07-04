package clase.datos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;

public class Aplicacion {
	private int idUsuario;
	private String nombre;
	private String correo;
	private int edad;
	private String localidad;
	private int nTesorosE;
	private int nAmigos;
	private int nTesorosA;
	private ArrayList<Integer> TesorosEnc;
	
	public Aplicacion() {
        this.TesorosEnc = new ArrayList<Integer>();
    }
	
	@XmlAttribute(required=false)
	public int getId() {
		return idUsuario;
	}

	public void setId(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	public int getNTesorosE() {
		return nTesorosE;
	}

	public void setNTesorosE(int nTesorosE) {
		this.nTesorosE = nTesorosE;
	}

	public int getNTesorosA() {
		return nTesorosA;
	}

	public void setNTesorosA(int nTesorosA) {
		this.nTesorosA = nTesorosA;
	}
	
	public int getNAmigos() {
		return nAmigos;
	}

	public void setNAmigos(int nAmigos) {
		this.nAmigos = nAmigos;
	}
	
	public ArrayList<Integer> getUltimos() {
		return TesorosEnc;
	}

	public void setUltimos(ArrayList<Integer> TesorosEnc) {
		this.TesorosEnc = TesorosEnc;
	}
	
	
	public Aplicacion(String nombre, String correo, int edad, String localidad, int nTesorosE, ArrayList<Integer> TesorosEnc, int nAmigos, int nTesorosA) {
        super();
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
        this.localidad = localidad;
        this.nTesorosE = nTesorosE;
        this.nAmigos = nAmigos;
        this.nTesorosA = nTesorosA;
        this.TesorosEnc = new ArrayList<>();
    }
}
