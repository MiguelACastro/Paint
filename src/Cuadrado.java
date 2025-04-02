import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Cuadrado extends Pintable{

	private int x, y, width, height;
	private boolean esBorrador;
	
	public Cuadrado(int x, int y, int width, int height, Color color, BasicStroke grosor, boolean esBorrador) {
		super(color, grosor);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.esBorrador = esBorrador;
	}

	public Cuadrado(int x, int y, int width, int height, Color color, BasicStroke grosor) {
		this(x, y, width, height, color, grosor, false);
	}
	
	@Override
	public void pintar(Graphics2D g2d) {
		super.pintar(g2d);
		if(esBorrador) {
			g2d.clearRect(x-width/2, y-height/2, width, height);
		} else {			
			g2d.drawRect(x-width/2, y-height/2, width, height);
		}
	}
	
}
