package com.DaoClasses;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.EntityClasses.KIT_Point;
import com.HibernateUtil.HibernateUtil;


@Repository
public class kitPointDaoImpl implements kitPointDao {
	

	public boolean addPointValue(KIT_Point kitPointValue) {
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        Timestamp created_at= new Timestamp(System.currentTimeMillis()); 
        kitPointValue.setKit_point("1 Kit point");
	    kitPointValue.setCreated_at(created_at);
        try {
            trns = session.beginTransaction();
            session.save(kitPointValue);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.flush();
            session.close();
        }
		return true;
	}

	public KIT_Point addPointValue1(KIT_Point model1) {
        Transaction trns = null;
	    int kit_point_value=model1.getValue();
        Timestamp created_at= new Timestamp(System.currentTimeMillis()); 
	   
	    KIT_Point kitPointValue= new KIT_Point();
	    kitPointValue.setValue(kit_point_value);
	    kitPointValue.setKit_point("Kit point");
	    kitPointValue.setCreated_at(created_at);
		
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(kitPointValue);
            session.getTransaction().commit();
        } 
        catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return model1;
        } finally {
            session.flush();
            session.close();
        }
		return model1;
    }


	public List<KIT_Point> getAllPointValue() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean deletePoint(KIT_Point kitPointValue) {
		// TODO Auto-generated method stub
		return false;
	}

    

}
