package com.springdemo.dao;

import java.util.List;

import com.springdemo.entity.Student;

public interface StudentDao {
	
	public List<Student> getStudents();
	
	public void saveStudent(Student student);
	
	public Student getStudent(int studentId);

	public void deleteStudent(int studentId);

	public List<Student> searchStudents(String theSearchName);

}
