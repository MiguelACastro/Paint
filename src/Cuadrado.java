import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Cuadrado extends Pintable{

	private int x, y, width, height;
	
	public Cuadrado(int x, int y, int width, int height, Color color, BasicStroke grosor, boolean esRellena) {
		super(color, grosor, esRellena);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.esRellena = esRellena;
	}
	
	public Cuadrado(int x, int y, int width, int height, Color color, BasicStroke grosor) {
		this(x, y, width, height, color, grosor, false);
	}
	
	@Override
	public void pintar(Graphics2D g2d) {
		super.pintar(g2d);
		if(esRellena) {
			g2d.fillRect(x-width/2, y-height/2, width, height);
		} else {			
			g2d.drawRect(x-width/2, y-height/2, width, height);
		}
	}
	
}
