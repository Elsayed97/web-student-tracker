package com.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public StudentDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Student> getStudents() {
		
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// create a query .. sort by last name
		Query<Student> theQuery = session.createQuery("from Student order by lastName",Student.class);
		
		// execute query and get result set
		List<Student> students = theQuery.getResultList();
		
		//return the results
		return students;
		
	}

	@Override
	public void saveStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(student);
		
	}

	@Override
	public Student getStudent(int studentId) {
		Session session = sessionFactory.getCurrentSession();
		Student student = session.get(Student.class,studentId);
		return student;
	}

	@Override
	public void deleteStudent(int studentId) {
		Session session = sessionFactory.getCurrentSession();
		Query theQuery = session.createQuery("delete from Student where id=:studentId");
		theQuery.setParameter("studentId",studentId);
		theQuery.executeUpdate();
		
	}

	@Override
	public List<Student> searchStudents(String theSearchName) {
		//get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery = currentSession.createQuery("from Student where lower(firstName) like :theName or lower(lastName) like :theName", Student.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery = currentSession.createQuery("from Student", Student.class);            
        }
        
        // execute query and get result list
        List<Student> students = theQuery.getResultList();
                
        // return the results        
        return students;
	}

}
