package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Engine extends JFrame implements ActionListener {
	private JPanel contentPanel, displayPanel, buttonPanel;
	private JTextField display;
	private JButton n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, divide, multiply, subtract, add, equal, reset;

	private enum ButtonType {
		REGULAR, OPERATOR
	}

	private int num1, num2, result;
	private char operation;

	/**
	 * Constructora
	 */
	public Engine() {
		setSettings();
		addActionEvent();
	}

	/**
	 * Metodo que añade un actionListener a todos los componentes menos al frame.
	 */
	private void addActionEvent() {
		JButton[] buttons = { n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, divide, multiply, subtract, add, equal, reset };
		for (JButton button : buttons) {
			button.addActionListener(this);
		}
	}

	/**
	 * Metodo donde se crea toda la ventana
	 */
	private void setSettings() {
	    this.setTitle("Mi Calculadora");
	    this.setSize(350, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);

	    contentPanel = new JPanel(new BorderLayout());
	    displayPanel = new JPanel(new BorderLayout());
	    displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); 
	    buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));

	    display = new JTextField();
	    display.setEditable(false);
	    display.setFont(new Font("Verdana", Font.BOLD, 18));
	    display.setHorizontalAlignment(JTextField.LEFT);
	    display.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
	    display.setBackground(new Color(230, 230, 230));

	    JPanel displayContainer = new JPanel(new BorderLayout());
	    displayContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
	    displayContainer.add(display, BorderLayout.CENTER);
	    
	    displayPanel.add(displayContainer, BorderLayout.CENTER);

	    n0 = new JButton("0");
	    n1 = new JButton("1");
	    n2 = new JButton("2");
	    n3 = new JButton("3");
	    n4 = new JButton("4");
	    n5 = new JButton("5");
	    n6 = new JButton("6");
	    n7 = new JButton("7");
	    n8 = new JButton("8");
	    n9 = new JButton("9");
	    divide = new JButton("÷");
	    multiply = new JButton("×");
	    subtract = new JButton("−");
	    add = new JButton("+");
	    equal = new JButton("=");
	    reset = new JButton("AC");

	    JButton[] buttons = { n7, n8, n9, divide, n4, n5, n6, multiply, n1, n2, n3, subtract, reset, n0, equal, add };
	    for (JButton button : buttons) {
	        if (button == divide || button == multiply || button == subtract || button == add || button == equal) {
	            setFeaturesButton(button, ButtonType.OPERATOR);
	        } else {
	            setFeaturesButton(button, ButtonType.REGULAR);
	        }

	        buttonPanel.add(button);
	    }

	    contentPanel.add(displayPanel, BorderLayout.NORTH);
	    contentPanel.add(buttonPanel, BorderLayout.CENTER);
	    this.add(contentPanel);
	    this.setVisible(true);
	}


	/**
	 * Metodo que dependiendo del operador realiza una operacion o otra
	 */
	public void operation() {
		switch (operation) {
		case '+':
			result = num1 + num2;
			break;
		case '-':
			result = num1 - num2;
			break;
		case '×':
			result = num1 * num2;
			break;
		case '÷':
			result = num1 / num2;
			break;
		}
	}

	/**
	 * Metodo que aplica colores a los botones
	 * 
	 * @param _button
	 * @param _type
	 */
	public void setFeaturesButton(JButton _button, ButtonType _type) {
		switch (_type) {
		case REGULAR:
			_button.setFont(new Font("Arial", Font.PLAIN, 14));
			_button.setFocusPainted(false);
			_button.setBackground(Color.LIGHT_GRAY);
			break;
		case OPERATOR:
			_button.setFont(new Font("Arial", Font.BOLD, 16));
			_button.setFocusPainted(false);
			_button.setBackground(Color.ORANGE);
			_button.setForeground(Color.WHITE);
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		
	}
}