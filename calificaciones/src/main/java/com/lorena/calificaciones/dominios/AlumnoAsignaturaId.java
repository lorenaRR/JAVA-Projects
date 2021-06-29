package com.lorena.calificaciones.dominios;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class AlumnoAsignaturaId implements Serializable {
	private static final long serialVersionUID = 1L;

	private String dni;
	private String asigId;

	public AlumnoAsignaturaId() {

	}

	public AlumnoAsignaturaId(String dni, String asigId) {
		super();
		this.dni = dni;
		this.asigId = asigId;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getAsigId() {
		return asigId;
	}

	public void setAsigId(String asigId) {
		this.asigId = asigId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((asigId == null) ? 0 : asigId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlumnoAsignaturaId other = (AlumnoAsignaturaId) obj;
		return Objects.equals(getDni(), other.getDni())
				&& Objects.equals(getAsigId(), other.getAsigId());
	}
}
