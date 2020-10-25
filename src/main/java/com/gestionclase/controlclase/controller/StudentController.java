package com.gestionclase.controlclase.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gestionclase.controlclase.model.Student;
import com.gestionclase.controlclase.repository.StudentRepository;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//exponer los puntos finales los datos, consultar,CRUD
//post , delete, put, get, request---, servidor-response
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StudentController {

    //implementar los metods donde exposgo
    //listado de estudiantes.
    @Autowired
    StudentRepository studentrepository;
    /*public StudentController(StudentRepositroy studentrepository){ this.studentrepository=studentrepository;}*/

    //visible + lo que retorna + nombre (parametros){}
    @PostMapping("/students")
    public ResponseEntity<Student> createTutorial(@RequestBody Student student){
        try{
            Student _student = studentrepository.save(new Student(student.getName(), student.getApellido(), false));
            return new ResponseEntity<>(_student, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);        

        }

    }
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllTutorials(@RequestParam(required = false) String name){
        try{
            List<Student> students = new ArrayList<Student>();//inicializo vacio

            if (name == null)
            studentrepository.findAll().forEach(students::add);
            else            
            studentrepository.findByNameContaining(name).forEach(students::add);

            if(students.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(students, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }    
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") String id){
        
        Optional<Student> students = studentrepository.findById(id);

        if(students.isPresent()){
            return new ResponseEntity<>(students.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<HttpStatus> deletStudentById(@PathVariable("id") String id){
        
        try{
            studentrepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        
}
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> update(@PathVariable("id") String id,@RequestBody Student student){

        Optional<Student> students = studentrepository.findById(id);
        
        if(students.isPresent()){
            Student _student = students.get();
            _student.setName(student.getName());
            _student.setApellido(student.getApellido());
            _student.setAttend(false);
            studentrepository.save(_student);
            return new ResponseEntity<>(_student,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }       
}
}
//if(name==null)
  //       studentrepository.findAll().forEach(students::add);//llenar elementos
    //    else
      //   studentrepository.findByNameStudent(name).forEach(students::add);
//
  //       return new ResponseEntity<>(students, HttpStatus.OK);
         ///SI PASO UN ERROR O UNA EXCEPCION.
         ///return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);.
         /*codigo de put
         return studentrepository.findById(id)
        .map(todoStudent -> {
            todoStudent.setName(student.getName());
            todoStudent.setApellido(student.getApelldio());
            todoStudent.setAttend(student.isAttend());
             Student updateStudentById = studentrepository.save(todoStudent);
             return ResponseEntity.ok().body(updateStudentById);       
        }).orElse(ResponseEntity.notFound().build());*/