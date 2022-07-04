package clase.datos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tesoros")
public class Tesoros {
	private ArrayList<Link> tesoros;

	public Tesoros() {
		this.tesoros = new ArrayList<Link>();
	}

	@XmlElement(name="tesoro")
	public ArrayList<Link> getTesoros() {
		return tesoros;
	}

	public void setTesoros(ArrayList<Link> tesoros) {
		this.tesoros = tesoros;
	}
}
