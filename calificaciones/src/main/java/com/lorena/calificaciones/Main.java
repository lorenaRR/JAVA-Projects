package com.lorena.calificaciones;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	private JButton btnDarAlta;
	private JButton btnNewButton;
	private JButton btnCalificar;
	private JButton btnDarDeAlta;
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 367, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnDarAlta = new JButton("Dar de alta alumnos");
		btnDarAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog nuevoAlumno = new CrearAlumno();
				nuevoAlumno.setVisible(true);
			}
		});
		btnDarAlta.setBounds(65, 51, 233, 23);
		frame.getContentPane().add(btnDarAlta);
		
		btnNewButton = new JButton("Crear nueva asignatura");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog nuevaAsignatura = new CrearAsignatura();
				nuevaAsignatura.setVisible(true);
			}
		});
		btnNewButton.setBounds(65, 97, 233, 23);
		frame.getContentPane().add(btnNewButton);
		
		btnCalificar = new JButton("Calificar");
		btnCalificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog calificar = new Calificar();
				calificar.setVisible(true);
			}
		});
		btnCalificar.setBounds(65, 199, 233, 23);
		frame.getContentPane().add(btnCalificar);
		
		btnDarDeAlta = new JButton("Matricular alumnos");
		btnDarDeAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog matricularAlumno = new MatricularAlumno();
				matricularAlumno.setVisible(true);
			}
		});
		btnDarDeAlta.setBounds(65, 150, 233, 23);
		frame.getContentPane().add(btnDarDeAlta);
		
		btnBuscar = new JButton("Buscar Notas");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog boletinNotas = new BoletinNotas();
				boletinNotas.setVisible(true);
			}
		});
		btnBuscar.setBounds(65, 244, 233, 23);
		frame.getContentPane().add(btnBuscar);
	}
}
