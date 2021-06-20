package com.hibernate.jdbc.demo;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.model.Student;

public class HibernateQueryObject {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();

			List<Student> students = session.createQuery("from Student").getResultList();
			System.out.println("");
			printStudents(students);
			
			students = session.createQuery("from Student where firstName like '%A*%'").getResultList();
			printStudents(students);

			System.out.println("List of Students with name Arijit or Tamoghna");
			students = session.createQuery("from Student s where s.firstName='Arijit' OR s.firstName='Tamoghna'").getResultList();
			printStudents(students);

			System.out.println("List of Students with email gmail.com");
			students = session.createQuery("from Student s where s.email like '%gmail.com'").getResultList();
			printStudents(students);
			
			session.getTransaction().commit();
			
		}catch(Exception e) {
			System.out.println("Error = "+ e.getMessage());
		}finally {
			session.close();//No point of doing this, Session is already dead after "commit". if you use sessionFactory.getCurrentSession(), you'll obtain a "current session" which is bound to the lifecycle of the transaction and will be automatically flushed and closed when the transaction ends (commit or rollback).if you decide to use sessionFactory.openSession(), you'll have to manage the session yourself and to flush and close it "manually".
			factory.close();
		}

	}

	private static void printStudents(List<Student> students) {
		for(Student student : students) {
			System.out.println("Student - "+ student);
		}
	}
}
