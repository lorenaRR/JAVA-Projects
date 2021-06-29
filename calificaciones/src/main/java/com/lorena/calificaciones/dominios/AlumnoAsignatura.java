package com.lorena.calificaciones.dominios;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "alumno_asignatura")
public class AlumnoAsignatura {
	@EmbeddedId
	private AlumnoAsignaturaId id = new AlumnoAsignaturaId();

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("dni")
	private Alumno alumno;

	@ManyToOne (fetch = FetchType.LAZY)
	@MapsId("asigId")
	private Asignatura asignatura;

	@Column(name="nota")
	private float nota;
	
	public AlumnoAsignatura() {
		
	}

	public AlumnoAsignatura(Alumno alumno, Asignatura asignatura) {
		super();
		this.alumno = alumno;
		this.asignatura = asignatura;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public Alumno getAlumno() {
		return alumno;
	}


	public Asignatura getAsignatura() {
		return asignatura;
	}

	@Override
	public boolean equals(Object o) {
		
		if (this == o) return true;
		 
        if (o == null || getClass() != o.getClass())
            return false;
 
        AlumnoAsignatura that = (AlumnoAsignatura) o;
        return Objects.equals(alumno, that.alumno) &&
               Objects.equals(asignatura, that.asignatura);
	}

	@Override
	public int hashCode() {
		return Objects.hash(alumno,asignatura);
	}
	
}
