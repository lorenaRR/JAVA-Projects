package com.lorena.calificaciones;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.persistence.RollbackException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lorena.calificaciones.dao.AlumnoDao;
import com.lorena.calificaciones.dominios.Alumno;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CrearAlumno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellidos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearAlumno dialog = new CrearAlumno();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearAlumno() {
		setBounds(100, 100, 380, 218);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblDni_1 = new JLabel("DNI");
		lblDni_1.setBounds(55, 40, 48, 14);
		contentPanel.add(lblDni_1);

		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(113, 37, 185, 20);
		contentPanel.add(txtDNI);

		JLabel lblNombre_1 = new JLabel("Nombre");
		lblNombre_1.setBounds(55, 71, 48, 14);
		contentPanel.add(lblNombre_1);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(113, 68, 185, 20);
		contentPanel.add(txtNombre);

		JLabel lblApellidos_1 = new JLabel("Apellidos");
		lblApellidos_1.setBounds(55, 106, 60, 14);
		contentPanel.add(lblApellidos_1);

		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(113, 103, 185, 20);
		contentPanel.add(txtApellidos);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Guardar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {//Añadir Alumno
						AlumnoDao alumnoDao = new AlumnoDao();
						String dni = txtDNI.getText();
						String nombre = txtNombre.getText();
						String apellidos = txtApellidos.getText();
						if (!dni.equals("") || !nombre.equals("") || !apellidos.equals("")) {
							try {
								Alumno alumno = new Alumno(dni, nombre, apellidos);
								alumnoDao.save(alumno);
							} catch (RollbackException rbe) {
								JOptionPane.showMessageDialog(getParent(), rbe);
							}
						}
						txtDNI.setText("");
						txtNombre.setText("");
						txtApellidos.setText("");
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
}
