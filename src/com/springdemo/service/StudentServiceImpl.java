package com.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springdemo.dao.StudentDao;
import com.springdemo.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao;
	
	@Autowired
	public StudentServiceImpl(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Transactional
	@Override
	public List<Student> getStudents() {
		
		return studentDao.getStudents();
		
	}

	@Transactional
	@Override
	public void saveStudent(Student student) {
		studentDao.saveStudent(student);
		
	}

	@Transactional
	@Override
	public Student getStudent(int studentId) {
		Student student = studentDao.getStudent(studentId);
		return student;
	}

	@Transactional
	@Override
	public void deleteStudent(int studentId) {
		studentDao.deleteStudent(studentId);
		
	}

	@Transactional
	@Override
	public List<Student> searchStudents(String theSearchName) {
		
		return studentDao.searchStudents(theSearchName);
	}

}
