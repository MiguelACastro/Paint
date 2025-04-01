import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

abstract class Pintable {
	
	protected Color color;
	protected BasicStroke grosor;
	
	public Pintable(Color color, BasicStroke grosor) {
		this.color = color;
		this.grosor = grosor;
	}
	
	public Color getColor() {
		return color;
	}
	
	public BasicStroke getGrosor() {
		return grosor;
	}
	
	public void pintar(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.setStroke(grosor);
	};
}
