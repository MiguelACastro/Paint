import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InterfazPaint extends JFrame{
	
	public InterfazPaint() {
		this.setTitle("MiniPaint");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(paint());
		this.pack();
		this.setLocationRelativeTo(null);
		
		this.setMinimumSize(getMinimumSize());
		this.setPreferredSize(getPreferredSize());;
	}

	private JPanel paint() {
		JPanel panelInterfaz = new JPanel(new GridBagLayout());
		GridBagConstraints reglas = new GridBagConstraints();
		panelInterfaz.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		panelInterfaz.setBackground(Color.BLACK);
		
		PaintPanel panelCanvas = new PaintPanel();
		panelCanvas.setPreferredSize(new Dimension(800, 600));
		panelCanvas.setMinimumSize(new Dimension(800, 600));
		reglas.gridx = 1;
		reglas.gridy = 1;
		reglas.gridwidth = 1;
		reglas.gridheight = 1;
		reglas.weightx = 1.0;
		reglas.weighty = 1.0;
		reglas.fill = GridBagConstraints.BOTH;
		reglas.insets = new Insets(0, 15, 0, 0);
		panelInterfaz.add(panelCanvas, reglas);
		reglas.insets = new Insets(0, 0, 0, 0);
		
		JPanel panelHerramientas = new JPanel();
		panelHerramientas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelHerramientas.setLayout(new BoxLayout(panelHerramientas, BoxLayout.Y_AXIS));
		reglas.gridx = 0;
		reglas.gridy = 1;
		reglas.gridwidth = 1;
		reglas.gridheight = 1;
		reglas.weightx = 0.2;
		reglas.fill = GridBagConstraints.VERTICAL;
		panelInterfaz.add(panelHerramientas, reglas);
		
		JPanel panelColores = new JPanel(new GridLayout(2, 3));
		panelColores.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelHerramientas.add(panelColores);
		
		Color[][] colores = {{Color.WHITE, Color.BLACK, Color.GRAY}, {Color.BLUE, Color.RED, Color.GREEN}};
		
		reglas.weightx = 1.0;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j++) {				
				JButton botonColor = new JButton();
				botonColor.setBackground(colores[i][j]);
				botonColor.setPreferredSize(new Dimension(50,50));
				botonColor.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						panelCanvas.setColor(botonColor.getBackground());
					}
				});
				reglas.gridy = i;
				reglas.gridx = j;
				reglas.gridwidth = 1;
				reglas.gridheight = 1;
				reglas.insets = new Insets(0, 0, 15, 15);
				reglas.fill = GridBagConstraints.NONE;
				reglas.anchor = GridBagConstraints.NORTHWEST;
				panelColores.add(botonColor);
			}
		}
		panelHerramientas.add(Box.createVerticalGlue());
		
		JLabel etiquetaGrosor = new JLabel("Grosor");
		etiquetaGrosor.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelHerramientas.add(etiquetaGrosor);
		panelHerramientas.add(Box.createVerticalStrut(10));
		
		JSlider sliderGrosor = new JSlider(1, 15, 2);	
		sliderGrosor.setMajorTickSpacing(2);
		sliderGrosor.setPaintTicks(true);
		sliderGrosor.setLabelTable(sliderGrosor.createStandardLabels(2));
		sliderGrosor.setPaintLabels(true);
		sliderGrosor.setAlignmentX(Component.LEFT_ALIGNMENT);
		sliderGrosor.addChangeListener(new ChangeListener() {	
			@Override
			public void stateChanged(ChangeEvent e) {
				panelCanvas.setGrosor(sliderGrosor.getValue());
			}
		});
		panelHerramientas.add(sliderGrosor);
		panelHerramientas.add(Box.createVerticalGlue());
		
		JPanel panelPincel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
		panelPincel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelHerramientas.add(panelPincel);
		
		JButton botonPincel = new JButton("Pincel");
		botonPincel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCanvas.setHerramienta(PaintPanel.HERRAMIENTA_PINCEL);
			}
		});
		panelPincel.add(botonPincel);
		
		JButton botonBorrador = new JButton("Borrador");
		botonBorrador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCanvas.setHerramienta(PaintPanel.HERRAMIENTA_BORRADOR);
			}
		});
		panelPincel.add(botonBorrador);
		
		JPanel panelFiguras = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		panelFiguras.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelHerramientas.add(panelFiguras);
		
		JButton botonRectangulo= new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				g.drawRect(10, 10, 20, 20);
			}
		};
		botonRectangulo.setPreferredSize(new Dimension(40,40));
		botonRectangulo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCanvas.setHerramienta(PaintPanel.HERRAMIENTA_CUADRADO);
			}
		});
		panelFiguras.add(botonRectangulo);
		
		JButton botonCirculo = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				g.drawOval(10, 10, 20, 20);
			}
		};
		botonCirculo.setPreferredSize(new Dimension(40,40));
		botonCirculo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCanvas.setHerramienta(PaintPanel.HERRAMIENTA_CIRCULO);
			}
		});
		panelFiguras.add(botonCirculo);
		
		JButton botonTriangulo = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				int[] xPoints = {10, 20, 30};
				int[] yPoints = {30, 10, 30};
				g.drawPolygon(xPoints, yPoints, 3);
			}
		};
		botonTriangulo.setPreferredSize(new Dimension(40,40));
		panelFiguras.add(botonTriangulo);
		
		
		JButton botonLinea = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				g.drawLine(10, 30, 30, 10);
			}
		};
		botonLinea.setPreferredSize(new Dimension(40,40));
		botonLinea.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCanvas.setHerramienta(PaintPanel.HERRAMIENTA_LINEA);
			}
		});
		panelFiguras.add(botonLinea);
		
		JPanel panelOpcionesFiguras = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
		panelOpcionesFiguras.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelHerramientas.add(panelOpcionesFiguras);
		
		JCheckBox checkboxRelleno = new JCheckBox("Relleno");
		checkboxRelleno.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				panelCanvas.setRelleno(checkboxRelleno.isSelected());
			}
		});
		panelOpcionesFiguras.add(checkboxRelleno);
		
		SpinnerModel modelo = new SpinnerNumberModel(80, 10, 500, 10);
		JSpinner spinnerSize = new JSpinner(modelo);
		spinnerSize.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					spinnerSize.commitEdit();
				} catch(ParseException ex) {	
					spinnerSize.setValue(80);
				}
				int size =(Integer) spinnerSize.getValue();
				panelCanvas.setFiguraSize(size);
			}
		});
		panelOpcionesFiguras.add(spinnerSize);
		
		JPanel panelLimpiar = new JPanel();
		panelLimpiar.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelHerramientas.add(panelLimpiar);
		JButton botonLimpiar = new JButton("Limpiar");
		botonLimpiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCanvas.borrarTodo();
			}
		});
		panelLimpiar.add(botonLimpiar);
		
		return panelInterfaz;
	}
}
