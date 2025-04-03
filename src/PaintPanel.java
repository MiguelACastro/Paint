import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class PaintPanel extends JPanel {
	public static final int HERRAMIENTA_PINCEL = 0;
	public static final int HERRAMIENTA_CUADRADO = 1;
	public static final int HERRAMIENTA_CIRCULO = 2;
	public static final int HERRAMIENTA_TRIANGULO = 3;
	public static final int HERRAMIENTA_LINEA = 4;
	public static final int HERRAMIENTA_BORRADOR = 5;
	
	
	private ArrayList<Pintable> figuras = new ArrayList<Pintable>();
	
	
	private Color color = Color.BLACK;
	private BasicStroke grosor = new BasicStroke(3);
	private int herramienta = HERRAMIENTA_PINCEL;
	private int figuraSize = 80;
	private boolean relleno = false;
	
	private int numClicks = 0;
	private int x1, y1, x2, y2;
	
	public PaintPanel() {
		this.setBackground(Color.WHITE);
		
		MouseAdapter mouse = new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				if(herramienta == HERRAMIENTA_PINCEL) {						
					Trazo trazoActual = (Trazo) figuras.getLast();
					trazoActual.addPoint(new Point(e.getX(), e.getY()));
				}
				if(herramienta == HERRAMIENTA_BORRADOR) {
					figuras.add(new Cuadrado(e.getX(), e.getY(), 5*(int)grosor.getLineWidth(),
								5*(int)grosor.getLineWidth(), getBackground(), grosor, true));
				}
				repaint();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				if(herramienta == HERRAMIENTA_PINCEL) {	
					figuras.add(new Trazo(color, grosor));
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(herramienta == HERRAMIENTA_CUADRADO) {
					figuras.add(new Cuadrado(e.getX(), e.getY(), figuraSize, figuraSize, color, grosor, relleno));
				}
				else if(herramienta == HERRAMIENTA_CIRCULO) {
					figuras.add(new Circulo(e.getX(), e.getY(), figuraSize, color, grosor, relleno));
				}
				else if(herramienta == HERRAMIENTA_LINEA) {
					numClicks++;
					if(numClicks == 1) {
						x1 = e.getX();
						y1 = e.getY();
					}
					else if(numClicks == 2) {
						numClicks = 0;
						x2 = e.getX();
						y2 = e.getY();
						figuras.add(new Linea(x1, y1, x2, y2, color, grosor));
					}
					
				}
				repaint();
			}
		};
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
	}
	
	public void setHerramienta(int herramienta) {
		this.herramienta  = herramienta;
		numClicks=0;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setGrosor(int grosor) {
		this.grosor = new BasicStroke(grosor);
	}
	
	public void setFiguraSize(int figuraSize) {
		this.figuraSize = figuraSize; 
	}
	
	public void setRelleno(boolean relleno) {
		this.relleno = relleno;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for (Iterator iterator = figuras.iterator(); iterator.hasNext();) {
			Pintable figura =  (Pintable) iterator.next();
			figura.pintar(g2d);
			}
	}
	
	public void borrarTodo() {
		figuras.clear();
		repaint();
	}
}
