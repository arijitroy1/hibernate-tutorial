package com.hibernate.jdbc.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.model.Student;

public class HibernatePrimaryKeyDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			Student student1 = new Student("Arijit", "Roy", "greatestarijit@gmail.com");
			Student student2 = new Student("Tamoghna", "Roy", "tmghn.roy@gmail.com");
			Student student3 = new Student("Tapan", "Roy", "tapan.roy123@gmail.com");
			session.save(student1);
			session.save(student2);
			session.save(student3);
			session.getTransaction().commit();
			System.out.println("Data are saved !!!");
		}catch(Exception e) {
			System.out.println("Error = "+ e.getMessage());
		}finally {
			session.close();//No point of doing this, Session is already dead after "commit". if you use sessionFactory.getCurrentSession(), you'll obtain a "current session" which is bound to the lifecycle of the transaction and will be automatically flushed and closed when the transaction ends (commit or rollback).if you decide to use sessionFactory.openSession(), you'll have to manage the session yourself and to flush and close it "manually".
			factory.close();
		}

	}

}
