import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Trazo extends Pintable{
	private ArrayList<Point> puntos = new ArrayList<Point>();
		
	public Trazo(Color color, BasicStroke grosor) {
		super(color, grosor, false);
	}

	public ArrayList<Point> getPuntos() {
		return puntos;
	}

	public void addPoint(Point punto) {
		puntos.add(punto);
	}

	@Override
	public void pintar(Graphics2D g2d) {
		super.pintar(g2d);
		if(puntos.size()>1) {
			for (int i = 1; i < puntos.size(); i++) {
				Point p1 = puntos.get(i-1);
				Point p2 = puntos.get(i);
				g2d.drawLine(p1.x,p1.y,p2.x,p2.y);
			}
		}
	}
	
}
