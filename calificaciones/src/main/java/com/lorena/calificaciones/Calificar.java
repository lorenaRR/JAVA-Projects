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
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Calificar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDNI;
	private JTextField txtNota;

	private Alumno alumno = new Alumno();
	private AlumnoDao alumnoDao = new AlumnoDao();
	private AlumnoAsignaturaDao alumnoAsignaturaDao = new AlumnoAsignaturaDao();
	private AlumnoAsignatura alumnoAsignatura = new AlumnoAsignatura();
	private Asignatura asignatura = new Asignatura();
	private AsignaturaDao asignaturaDao = new AsignaturaDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Calificar dialog = new Calificar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Calificar() {
		setBounds(100, 100, 455, 490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(75, 30, 48, 14);
		contentPanel.add(lblDni);

		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(141, 27, 96, 20);
		contentPanel.add(txtDNI);

		JLabel lblNom = new JLabel("Nombre");
		lblNom.setBounds(75, 67, 48, 14);
		contentPanel.add(lblNom);

		JLabel lblNombre = new JLabel("");
		lblNombre.setBorder(BorderFactory.createLineBorder(Color.black));
		lblNombre.setBounds(141, 67, 217, 20);
		contentPanel.add(lblNombre);

		JLabel lblApe = new JLabel("Apellidos");
		lblApe.setBounds(75, 107, 48, 14);
		contentPanel.add(lblApe);

		JLabel lblApellidos = new JLabel("");
		lblApellidos.setBorder(BorderFactory.createLineBorder(Color.black));
		lblApellidos.setBounds(141, 107, 217, 20);
		contentPanel.add(lblApellidos);

		JList lstAsignaturas = new JList();
		lstAsignaturas.setBounds(141, 167, 217, 157);
		contentPanel.add(lstAsignaturas);
		DefaultListModel modeloLista = new DefaultListModel<>(); // Para poder añadir elementos a la lista
		lstAsignaturas.setModel(modeloLista);

		{
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() { // Buscar alumno por DNI
				public void actionPerformed(ActionEvent e) {
					try {
						alumno = alumnoDao.getAlumno(txtDNI.getText());
						lblNombre.setText(alumno.getNombre());
						lblApellidos.setText(alumno.getApellidos());

						List<AlumnoAsignatura> notas = alumnoAsignaturaDao.getNotas(alumno.getDni());

						for (AlumnoAsignatura nota : notas) {
							modeloLista.addElement(nota.getAsignatura().getNomAsignatura());
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(getParent(), e2);
					}

				}
			});
			btnBuscar.setBounds(269, 26, 89, 23);
			contentPanel.add(btnBuscar);
		}

		txtNota = new JTextField();
		txtNota.setColumns(10);
		txtNota.setBounds(141, 373, 48, 20);
		contentPanel.add(txtNota);

		JLabel lblNota = new JLabel("Nota:");
		lblNota.setBounds(75, 376, 48, 14);
		contentPanel.add(lblNota);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("Calificar");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					asignatura = asignaturaDao.getAsignatura((String) lstAsignaturas.getSelectedValue());
					alumnoAsignatura = alumnoAsignaturaDao.getAlumnoAsignatura(alumno.getDni(), asignatura.getId());
					alumnoAsignatura.setNota(Float.parseFloat(txtNota.getText()));
					alumnoAsignaturaDao.update(alumnoAsignatura);
					txtDNI.setText("");
					lblNombre.setText("");
					lblApellidos.setText("");
					modeloLista.clear();
					txtNota.setText("");

				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
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
