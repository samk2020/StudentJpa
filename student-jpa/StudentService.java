package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Student;
import com.cognixia.jump.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository repo;
	
	public List<Student> getStudents() {
		return repo.findAll();
	}
	
	public Optional<Student> getStudentById(int id) {
		
		return repo.findById(id);
	}

}
