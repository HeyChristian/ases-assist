/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdvsys.ases.datalayer;




import com.fdvsys.ases.utils.DbProperties;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author Christian
 */
public class EntityManagerHelper {
    
    
         /**
         * @param args
         */
        public static void main(String[] args) {
                EntityManager em = EntityManagerHelper.getEntityManager();
                em.getTransaction().begin();
                em.getTransaction().commit();
                EntityManagerHelper.closeEntityManager();
        }

    
        public static final EntityManagerFactory tEntityManagerFactory=Persistence.createEntityManagerFactory("AssistPU",DbProperties.getProperties());
        public static final ThreadLocal<EntityManager> tThreadLocal = new ThreadLocal<EntityManager>();

        public static EntityManager getEntityManager(){
                EntityManager tEntityManager = (EntityManager) tThreadLocal.get();
            if (tEntityManager == null) {
                tEntityManager = tEntityManagerFactory.createEntityManager();
                tThreadLocal.set(tEntityManager);
            }
            return tEntityManager;
        }

        public static void closeEntityManager(){
                EntityManager tEntityManager = (EntityManager) tThreadLocal.get();
            if (tEntityManager != null)tEntityManager.close();
            tThreadLocal.set(null);
        }
    
    
    
}
