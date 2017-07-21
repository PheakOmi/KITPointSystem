package com.DaoClasses;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.EntityClasses.Batch_Master;
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

	public boolean createOrUpdate(KIT_Point kitPoint)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		String queryString = "from KIT_Point";
	    Query query = session.createQuery(queryString);
	    KIT_Point Point = (KIT_Point) query.uniqueResult();
		try {
		if (Point==null)
		{
			return addPointValue(kitPoint);
		}
		else {
			 return  updateKITPoint(kitPoint,Point);
			
		}
		}
		catch (RuntimeException e) {
		    e.printStackTrace();
		    return false;
		}
		finally{

		}
		
	}
	public boolean updateKITPoint( KIT_Point kitPoint,KIT_Point DatabasePoint)
	{
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Timestamp updated_at= new Timestamp(System.currentTimeMillis());
        //int id=kitPoint.getId();
        int kit=kitPoint.getValue();
        //DatabasePoint.setId(id);
        DatabasePoint.setValue(kit);
        DatabasePoint.setUpdated_at(updated_at);
        try {
            trns = session.beginTransaction();
           
            session.update(DatabasePoint);
            session.getTransaction().commit();
        
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
                return false;
            }
            e.printStackTrace();
        } 
        finally {
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
		List<KIT_Point> kitpoint= new ArrayList < KIT_Point > ();
		   Transaction trns = null;
		   Session session = HibernateUtil.getSessionFactory().openSession();
		   try {
		    trns = session.beginTransaction();
		    kitpoint = session.createQuery("from KIT_Point").list();
		    
		   } catch (RuntimeException e) {
		    e.printStackTrace();
		    return kitpoint;
		   } finally {
		    session.flush();
		    session.close();
		   }
		   return kitpoint;
		  }
		


	public boolean deletePoint(KIT_Point kitPointValue) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
