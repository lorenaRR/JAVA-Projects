package com.lorena.calificaciones;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lorena.calificaciones.dao.AlumnoAsignaturaDao;
import com.lorena.calificaciones.dao.AlumnoDao;
import com.lorena.calificaciones.dao.AsignaturaDao;
import com.lorena.calificaciones.dominios.Alumno;
import com.lorena.calificaciones.dominios.AlumnoAsignatura;
import com.lorena.calificaciones.dominios.Asignatura;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.persistence.NoResultException;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class BoletinNotas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDNI;

	private Alumno alumno = new Alumno();
	private AlumnoDao alumnoDao = new AlumnoDao();
	private Asignatura asignatura = new Asignatura();
	private AsignaturaDao asignaturaDao = new AsignaturaDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BoletinNotas dialog = new BoletinNotas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BoletinNotas() {
		setBounds(100, 100, 454, 459);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(70, 31, 48, 14);
		contentPanel.add(lblDni);

		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(136, 28, 96, 20);
		contentPanel.add(txtDNI);

		JLabel lblNom = new JLabel("Nombre");
		lblNom.setBounds(70, 68, 48, 14);
		contentPanel.add(lblNom);

		JLabel lblNombre = new JLabel("");
		lblNombre.setBorder(BorderFactory.createLineBorder(Color.black));
		lblNombre.setBounds(136, 68, 217, 20);
		contentPanel.add(lblNombre);

		JLabel lblApe = new JLabel("Apellidos");
		lblApe.setBounds(70, 108, 48, 14);
		contentPanel.add(lblApe);

		JLabel lblApellidos = new JLabel("");
		lblApellidos.setBorder(BorderFactory.createLineBorder(Color.black));
		lblApellidos.setBounds(136, 108, 217, 20);
		contentPanel.add(lblApellidos);

		JList lstNotas = new JList();
		lstNotas.setBounds(136, 168, 217, 157);
		contentPanel.add(lstNotas);
		DefaultListModel modeloLista = new DefaultListModel<>(); // Para poder añadir elementos a la lista
		lstNotas.setModel(modeloLista);

		{
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modeloLista.clear();
					alumno = alumnoDao.getAlumno(txtDNI.getText());
					lblNombre.setText(alumno.getNombre());
					lblApellidos.setText(alumno.getApellidos());
					try {
						AlumnoAsignaturaDao alumnoAsignaturaDao = new AlumnoAsignaturaDao();
						List<AlumnoAsignatura> notas = alumnoAsignaturaDao.getNotas(alumno.getDni());
						for (AlumnoAsignatura nota : notas) {
							modeloLista.addElement(nota.getAsignatura().getNomAsignatura() + " - " + nota.getNota());
						}

					} catch (NoResultException e2) {
						JOptionPane.showMessageDialog(getParent(), "No hat resultados.");
					}

				}
			});
			btnBuscar.setBounds(264, 27, 89, 23);
			contentPanel.add(btnBuscar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
