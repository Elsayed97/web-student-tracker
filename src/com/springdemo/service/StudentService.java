package com.springdemo.service;

import java.util.List;

import com.springdemo.entity.Student;

public interface StudentService {

	public List<Student> getStudents();
	
	public void saveStudent(Student student);
	
	public Student getStudent(int studentId);

	public void deleteStudent(int studentId);

	public List<Student> searchStudents(String theSearchName);
}
