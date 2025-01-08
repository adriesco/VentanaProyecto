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
	private JButton n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, dividir, multiplicar, restar, suma, igual, reset;

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
		JButton[] buttons = { n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, dividir, multiplicar, restar, suma, igual,reset };
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
		dividir = new JButton("÷");
		multiplicar = new JButton("×");
		restar = new JButton("-");
		suma = new JButton("+");
		igual = new JButton("=");
		reset = new JButton("AC");

		JButton[] buttons = { n7, n8, n9, dividir, n4, n5, n6, multiplicar, n1, n2, n3, restar, reset, n0, igual,suma };
		for (JButton button : buttons) {
			if (button == dividir || button == multiplicar || button == restar || button == suma || button == igual) {
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

	/**
	 * Metodo que limpia el display
	 */
	public void resetDisplay() {
		display.setText("");
	}
	
	/**
	 * Metodo que hace visible y funcional las operaciones en el display
	 * 
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String inputText = e.getActionCommand();

		if (source == reset) {
			resetDisplay();
		} else if (source == igual) {
			try {
				String[] partes = display.getText().split(" ");
				num1 = Integer.parseInt(partes[0]);
				operation = partes[1].charAt(0);
				num2 = Integer.parseInt(partes[2]);

				if (operation == '÷' && num2 == 0) {
					display.setText("No se puede dividir entre 0");
				} else {
					operation();
					display.setText(String.valueOf(result));
				}
			} catch (Exception ex) {
				display.setText("Error");
			}
		} else if (source == suma || source == dividir || source == multiplicar) {
	        display.setText(display.getText() + " " + inputText + " ");
	    } else if (source == restar) {
	        String currentText = display.getText();
	        if (currentText.isEmpty() || currentText.endsWith(" ")) {
	            display.setText(currentText + "-");
	        } else {
	            display.setText(currentText + " " + inputText + " ");
	        }
	    } else {
	        display.setText(display.getText() + inputText);
	    }
	}

	}
