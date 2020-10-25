package com.gestionclase.controlclase.repository;

import java.util.List;

import com.gestionclase.controlclase.model.Student;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
    //no necesito codificar para hacer CRUD porque mongorepository ya los tiene implemetados
    List<Student> findByAttend(boolean asistio);
    List<Student> findByNameContaining(String name);
	
    
}
