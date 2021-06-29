package com.lorena.calificaciones.dominios;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table (name="asignatura")
public class Asignatura {
	@Id
	private String asigId;
	@NaturalId
	private String nomAsignatura;
	
	@OneToMany(
	        mappedBy = "asignatura",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private Set<AlumnoAsignatura>alumnos;
	
	public Asignatura() {
		alumnos= new HashSet<>();
	}

	public Asignatura(String asigId, String nomAsignatura) {
		this();
		this.asigId = asigId;
		this.nomAsignatura = nomAsignatura;
	}

	public String getId() {
		return asigId;
	}

	public void setId(String id) {
		this.asigId = asigId;
	}

	public String getNomAsignatura() {
		return nomAsignatura;
	}

	public void setNomAsignatura(String nomAsignatura) {
		this.nomAsignatura = nomAsignatura;
	}
	
	public Set<AlumnoAsignatura> getAlumnos() {
		return alumnos;
	}
	
	/*public void addAlumno(Alumno alumno) {
		alumnos.add(alumno);
		if(!alumno.getAsignaturas().contains(this)) {
			alumno.addAsignatura(this);
		}
		
	}*/
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Asignatura asignatura = (Asignatura) o;
	        return Objects.equals(nomAsignatura, asignatura.nomAsignatura);
	    }
	 
	    @Override
	    public int hashCode() {
	        return Objects.hash(nomAsignatura);
	    }
	

}
