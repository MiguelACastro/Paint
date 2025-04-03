import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

abstract class Pintable {
	
	protected Color color;
	protected BasicStroke grosor;
	protected boolean esRellena;
	
	public Pintable(Color color, BasicStroke grosor, boolean esRellena) {
		this.color = color;
		this.grosor = grosor;
		this.esRellena = esRellena;
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
