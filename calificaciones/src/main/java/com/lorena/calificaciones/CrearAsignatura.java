package com.lorena.calificaciones;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.persistence.RollbackException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lorena.calificaciones.dao.AsignaturaDao;
import com.lorena.calificaciones.dominios.Asignatura;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CrearAsignatura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearAsignatura dialog = new CrearAsignatura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearAsignatura() {
		setBounds(100, 100, 399, 205);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCdigo = new JLabel("Código");
			lblCdigo.setBounds(51, 46, 48, 14);
			contentPanel.add(lblCdigo);
		}
		{
			txtCodigo = new JTextField();
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(115, 43, 96, 20);
			contentPanel.add(txtCodigo);
		}
		{
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(51, 83, 48, 14);
			contentPanel.add(lblNombre);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(115, 80, 160, 20);
			contentPanel.add(txtNombre);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Guardar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {//Añadir asignatura
						AsignaturaDao asignaturaDao = new AsignaturaDao();
						String codigo = txtCodigo.getText().toUpperCase();
						String nombre = txtNombre.getText();
						if (!codigo.equals("") || !nombre.equals("")) {
							try {
								Asignatura asignatura = new Asignatura(codigo, nombre);
								asignaturaDao.save(asignatura);
							} catch (RollbackException rbe) {
								JOptionPane.showMessageDialog(getParent(), rbe);
							}
						}

						txtCodigo.setText("");
						txtNombre.setText("");

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
