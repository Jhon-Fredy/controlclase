package com.gestionclase.controlclase.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//1. debilmente acoplado
//2. principio responsabilidad simple
//3. escalable, legible.
//4. Testeable.
//representa el modelo del dominio del negocio. tablas de la bd.
//mapeo de la bd a la clase DRM. mapeador objeto-documento.
//NoSQL.
@Document(collection = "students")
public class Student {
    // encapsulacion
    @Id
    private String id;
    private String name;
    private String apellido;
    private boolean attend;
    // constructor de una clase

    public Student() {

    }  

    // inyeccion
    public Student(String name, String apellido, boolean attend) {

        this.name=name;
        this.apellido=apellido;
        this.attend=attend;

    }
  //metodo publico que expone un atributo privado a otro componete.

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public boolean isAttend() {
        return attend;
    }

    public void setAttend(boolean attend) {
        this.attend = attend;
    }  
    @Override
    public String toString(){
        return "Student [id=" + id + ", name=" + name + ", desc=" + apellido + ", attend=" + attend + "]";
    } 

    
}
/*Student mystudnet = new Student();  mystudent*/
