package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Student;
import com.cognixia.jump.repository.StudentRepository;
import com.cognixia.jump.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	StudentRepository repo;
	
//	@Autowired
//	StudentService service;
	
	@GetMapping("/student")
	public List<Student> getStudents() {
		
		return repo.findAll();
		//return service.getStudents();
	}
	
	@GetMapping("/student/major/{major}") 
	public List<Student> getByDepartment(@PathVariable String major){
		return repo.studentsInMajor(major);
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<?> getStudent(@PathVariable int id) {
		
		// Optionals -> no guarentee that what you're looking for is there
		//			 -> you SHOULD be checking for a null/empty
		Optional<Student> found = repo.findById(id);
		
		//Optional<Student> found = service.getStudentById(id);
		
		if(found.isEmpty()) {
			return ResponseEntity.status(404)
								 .body("Student with id = "+ id +" not found");
		}
		else {
			return ResponseEntity.status(200).body( found.get() );
		}
	}
	
	@PostMapping("/student")
	public ResponseEntity<?> createStudent(@RequestBody Student student) {
		
		// set id to null so we can autoincrement with our table
		student.setId(null);
		
		// save() -> is used for both updates and insertions of data
		//		  -> if the primary key of the object given already exists in the table --> update
		//		  -> if primary key NOT present --> insert
		Student created = repo.save(student);
		
		return ResponseEntity.status(201).body(created);
	}
	
	
	@PutMapping("/student")
	public ResponseEntity<?> updateStudent(@RequestBody Student student) {
		
		boolean exists = repo.existsById(student.getId());
		
		// can do update if id exists
		if(exists) {
		
			Student updated = repo.save(student);
			
			return ResponseEntity.status(200).body(updated);
			
		}
		else { // id doesn't exist, can't do update
			
			return ResponseEntity.status(404).body("Can't perform update, student doesn't exist");
		}
		
	}
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable int id) {
		
		boolean exists = repo.existsById(id);
		
		if(exists) {
			
			repo.deleteById(id);
			
			return ResponseEntity.status(200).body("Student was deleted");
			
		}
		else { 
			
			return ResponseEntity.status(404).body("Can't delete, student doesn't exist");
		}
		
	}
	
	
	@PatchMapping("/student/major")
	public ResponseEntity<?> updateMajor(@PathParam(value = "id") int id, @PathParam(value = "major") String major) {
		
		Optional<Student> found = repo.findById(id);
		
		if(found.isEmpty()) {
			
			return ResponseEntity.status(404).body("Student with that id not found");
		}
		else {
			
			Student toUpdate = found.get();
			
			toUpdate.setMajor(major);
			
			repo.save(toUpdate);
			
			return ResponseEntity.status(200).body("Major for student was changed");
			
		}
		
	}
	
}
