package com.lorena.calificaciones.dominios;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="alumno")
public class Alumno {
	@Id
	@Column(unique = true)
	private String dni;
	private String nombre;
	private String apellidos;
	
	@OneToMany(
	        mappedBy = "alumno",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private Set<AlumnoAsignatura>asignaturas;

	public Alumno() {
    asignaturas= new HashSet<>();
	}

	public Alumno(String dni, String nombre, String apellidos) {
		this();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public Set<AlumnoAsignatura> getAsignaturas() {
		return asignaturas;
	}
	
	/*public void addAsignatura(AlumnoAsignatura asignatura) {
		asignaturas.add(asignatura);
		if(!asignatura.getAlumnos().contains(this)) {
			asignatura.addAlumno(this);
		}
	}*/
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        Alumno alumno = (Alumno) o;
        return Objects.equals(nombre, alumno.nombre);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

}
