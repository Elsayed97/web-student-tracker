package com.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.entity.Student;
import com.springdemo.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	private StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		List<Student> students = studentService.getStudents();
		theModel.addAttribute("students",students);
		return "list-students";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Student student = new Student();
		theModel.addAttribute(student);
		return "student-form";
		
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute Student student) {
		studentService.saveStudent(student);
		return "redirect:/students/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId")int theId,Model theModel) {
		
		Student student = studentService.getStudent(theId);
		theModel.addAttribute("student",student);
		return "student-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteStudent(@RequestParam("studentId")int studentId) {
		
		studentService.deleteStudent(studentId);
		return "redirect:/students/list";
	}
	
	@GetMapping("/search")
    public String searchStudents(@RequestParam("theSearchName") String theSearchName,
                                    Model theModel) {

        // search customers from the service
        List<Student> theStudents = studentService.searchStudents(theSearchName);
                
        // add the customers to the model
        theModel.addAttribute("students", theStudents);

        return "list-students";        
    }
	
	

}
