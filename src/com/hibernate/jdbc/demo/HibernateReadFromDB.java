package com.hibernate.jdbc.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.model.Student;

public class HibernateReadFromDB {

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
			
			System.out.println("Student 1 "+student1.getId());
			System.out.println("Student 2 "+student2.getId());
			System.out.println("Student 3 "+student3.getId());
			//session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Student 1"+session.get(Student.class,student1.getId()));
			
			session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("Error = "+ e.getMessage());
		}finally {
			factory.close();
		}

	}

}
