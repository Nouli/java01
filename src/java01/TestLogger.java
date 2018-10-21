package java01;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.fasterxml.classmate.AnnotationConfiguration;

import HibernateUtils.HibernateUtil;
import java01.entity.Utilisateur;

public class TestLogger {
	public TestLogger() {
		
	}
	public void addUser(Utilisateur p){
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
	}
	}
	   