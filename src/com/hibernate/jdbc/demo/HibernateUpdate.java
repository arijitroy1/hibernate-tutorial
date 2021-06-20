package com.hibernate.jdbc.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.model.Student;

public class HibernateUpdate {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		try {
			Session session = factory.getCurrentSession();
			session.getTransaction().begin();

			Student student = session.get(Student.class, "1");
			
			student.setFirstName("Akbar");
						
			session.getTransaction().commit();

			///////////////////////////////////////////////
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("Update Student set lastName='Roy'").executeUpdate();
			session.getTransaction().commit();
			
			
		}catch(Exception e) {
			System.out.println("Error = "+ e.getMessage());
		}finally {
			//session.close();//No point of doing this, Session is already dead after "commit". if you use sessionFactory.getCurrentSession(), you'll obtain a "current session" which is bound to the lifecycle of the transaction and will be automatically flushed and closed when the transaction ends (commit or rollback).if you decide to use sessionFactory.openSession(), you'll have to manage the session yourself and to flush and close it "manually".
			factory.close();
		}

	}

	private static void printStudents(List<Student> students) {
		for(Student student : students) {
			System.out.println("Student - "+ student);
		}
	}
}
