import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Trazo {
	private ArrayList<Point> puntos = new ArrayList<Point>();
	
	private Color color;
	private BasicStroke grosor;
	
	public Trazo(Color color, BasicStroke grosor) {
		this.color = color;
		this.grosor = grosor;
	}

	public ArrayList<Point> getPuntos() {
		return puntos;
	}

	public void addPoint(Point punto) {
		puntos.add(punto);
	}
	
	public Color getColor() {
		return color;
	}

	public BasicStroke getGrosor() {
		return grosor;
	}
	
}
