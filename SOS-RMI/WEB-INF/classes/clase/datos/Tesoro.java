package clase.datos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@XmlRootElement(name = "tesoro")
public class Tesoro {
	private int id;
	private int creador;
	private LocalDate fecha;
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private int tamanio;
	private String dificultad;
	private String terreno;
	private String pista;
	private double latitud;
	private double longitud;
	
	public Tesoro() {
	}

	@XmlAttribute(required=false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreador() {
		return creador;
	}

	public void setCreador(int creador) {
		this.creador = creador;
	}
	
	public LocalDate getFecha() {
		return LocalDate.now();
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = LocalDate.now();
	}
	
	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}
	
	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	
	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	
	public String getPista() {
		return pista;
	}

	public void setPista(String pista) {
		this.pista = pista;
	}
	
	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	
	public Tesoro(int creador, LocalDate fecha, int tamanio, String dificultad, String terreno, String pista, double latitud, double longitud) {
        super();
        this.id = id;
        this.creador = creador;
        this.fecha = LocalDate.now();
        this.tamanio = tamanio;
        this.dificultad = dificultad;
        this.terreno = terreno;
        this.pista = pista;
        this.latitud = latitud;
        this.longitud = longitud;
    }
	
}
