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
import com.mysql.cj.protocol.x.OkBuilder;

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

public class MatricularAlumno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Alumno alumno = new Alumno();
	private AlumnoDao alumnoDao = new AlumnoDao();
	private Asignatura asignatura = new Asignatura();
	private AsignaturaDao asignaturaDao = new AsignaturaDao();

	private JTextField txtDNI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MatricularAlumno dialog = new MatricularAlumno();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MatricularAlumno() {
		setBounds(100, 100, 393, 407);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(25, 28, 48, 14);
		contentPanel.add(lblDni);

		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(91, 25, 96, 20);
		contentPanel.add(txtDNI);

		JLabel lblNom = new JLabel("Nombre");
		lblNom.setBounds(25, 65, 48, 14);
		contentPanel.add(lblNom);

		JLabel lblNombre = new JLabel("");
		lblNombre.setBorder(BorderFactory.createLineBorder(Color.black));
		lblNombre.setBounds(91, 65, 217, 20);
		contentPanel.add(lblNombre);

		JLabel lblApe = new JLabel("Apellidos");
		lblApe.setBounds(25, 105, 48, 14);
		contentPanel.add(lblApe);

		JLabel lblApellidos = new JLabel("");
		lblApellidos.setBorder(BorderFactory.createLineBorder(Color.black));
		lblApellidos.setBounds(91, 105, 217, 20);
		contentPanel.add(lblApellidos);

		JList lstAsignaturas = new JList();
		lstAsignaturas.setBounds(91, 165, 217, 157);
		contentPanel.add(lstAsignaturas);
		DefaultListModel modeloLista = new DefaultListModel<>(); // Inicio Añadir asignaturas a lstAsignaturas
		lstAsignaturas.setModel(modeloLista);
		AsignaturaDao asignaturaDao = new AsignaturaDao();
		List<Asignatura> asignaturas = asignaturaDao.getAll();

		for (int i = 0; i < asignaturas.size(); i++) {
			modeloLista.addElement(asignaturas.get(i).getNomAsignatura()); // Fin Añadir asignaturas a lstAsignaturas
		}

		JButton okButton = new JButton("Guardar");
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() { // Buscar alumno
			public void actionPerformed(ActionEvent e) {
				try {
					alumno = alumnoDao.getAlumno(txtDNI.getText());
					lblNombre.setText(alumno.getNombre());
					lblApellidos.setText(alumno.getApellidos());
					okButton.setEnabled(true);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(getParent(), e2);
				}

			}
		});
		btnBuscar.setBounds(219, 24, 89, 23);
		contentPanel.add(btnBuscar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{

				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { // Asociar alumnos con asignaturas
						try {
							alumno = alumnoDao.getAlumno(txtDNI.getText());
							asignatura = asignaturaDao.getAsignatura((String) lstAsignaturas.getSelectedValue());
							AlumnoAsignatura alumnoAsignatura = new AlumnoAsignatura(alumno, asignatura);
							AlumnoAsignaturaDao alumnoAsignaturaDao = new AlumnoAsignaturaDao();
							alumnoAsignaturaDao.update(alumnoAsignatura);
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(getParent(), e2);
						}

						txtDNI.setText("");
						lblNombre.setText("");
						lblApellidos.setText("");
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
