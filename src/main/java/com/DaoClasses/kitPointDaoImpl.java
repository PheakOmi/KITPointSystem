package com.DaoClasses;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.EncryptionDecryption.Decryption;
import com.EncryptionDecryption.Encryption;
import com.EncryptionDecryption.SecretKeyClass;
import com.EntityClasses.Batch_Master;
import com.EntityClasses.KIT_Point;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.KIT_Point_Model;


@Repository
public class kitPointDaoImpl implements kitPointDao {
	

	Encryption encrypt= new Encryption();
	Decryption decrypt= new Decryption();
	public boolean addPointValue(KIT_Point kitPointValue)  {
		Transaction trns1 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
            String valueEncryp =encrypt.encryptText(kitPointValue.getValue(), secKey) ;
            Timestamp created_at= new Timestamp(System.currentTimeMillis()); 
            kitPointValue.setValue(valueEncryp);
            kitPointValue.setKit_point("1 Kit point");
    	    kitPointValue.setCreated_at(created_at);
            trns1 = session.beginTransaction();
            session.save(kitPointValue);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns1 != null) {
                trns1.rollback();
            }
            e.printStackTrace();
            return false;
        }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
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
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{

		}
		return false;
		
	}
	public boolean updateKITPoint( KIT_Point kitPoint,KIT_Point DatabasePoint) throws Exception
	{
		Transaction trns2 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Timestamp updated_at= new Timestamp(System.currentTimeMillis());
        
      
        DatabasePoint.setUpdated_at(updated_at);
        try {
        	SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
            String valueEncryp =encrypt.encryptText( kitPoint.getValue(), secKey) ;
            DatabasePoint.setValue(valueEncryp);
            trns2 = session.beginTransaction();
           
            session.update(DatabasePoint);
            session.getTransaction().commit();
        
        } catch (RuntimeException e) {
            if (trns2 != null) {
                trns2.rollback();
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
        Transaction trns3 = null;
	   // int kit_point_value=model1.getValue();
        Timestamp created_at= new Timestamp(System.currentTimeMillis()); 
	   
	    KIT_Point kitPointValue= new KIT_Point();
	   // kitPointValue.setValue(kit_point_value);
	    kitPointValue.setKit_point("Kit point");
	    kitPointValue.setCreated_at(created_at);
		
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns3 = session.beginTransaction();
            session.save(kitPointValue);
            session.getTransaction().commit();
        } 
        catch (RuntimeException e) {
            if (trns3 != null) {
                trns3.rollback();
            }
            e.printStackTrace();
            return model1;
        } finally {
            session.flush();
            session.close();
        }
		return model1;
    }


	public List<KIT_Point_Model> getAllPointValue() {
		List<KIT_Point_Model> kitpoint= new ArrayList < KIT_Point_Model > ();
		   Transaction trns4 = null;
		   Session session = HibernateUtil.getSessionFactory().openSession();
		   try {
		    trns4 = session.beginTransaction();
		    List<KIT_Point> kitpointDatabase = session.createQuery("from KIT_Point").list();
		    SecretKey secKey = SecretKeyClass.getSecretEncryptionKey();
		    String decryptedText;
		    for(int i=0;i<kitpointDatabase.size();i++)
		    { 
		    	kitpoint.add(new KIT_Point_Model());
		    	decryptedText = decrypt.decryptText(kitpointDatabase.get(i).getValue(), secKey);
		    	kitpoint.get(i).setValue(decryptedText);
		    	
		    }
		   } catch (RuntimeException e) {
		    e.printStackTrace();
		    return null;
		   } catch (Exception e) {
			e.printStackTrace();
			return null;
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
