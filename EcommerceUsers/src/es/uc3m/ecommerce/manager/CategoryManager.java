package es.uc3m.ecommerce.manager;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import es.uc3m.ecommerce.model.Category;
import es.uc3m.ecommerce.model.Product;

public class CategoryManager {

	private EntityManager em;
	private UserTransaction ut;
	
	public CategoryManager() {
		try {
			Context ctx = new InitialContext();
			/*
			 * The java:comp/env/jpa/pc has been defined at Servlet Class.
			 * The Servlet has done the injection using the following lines and then it has published at "jpa/pc".
			 * @PersistenceContext(unitName = "AgendaPersistentUnitWithJTA",name = "jpa/pc")
			 * private EntityManager em;
			 */
			em = (EntityManager) ctx.lookup("java:comp/env/jpa/pc");
			/*
			 * // You can get the UserTransaction using injection via @Resource (Only in injectable class such as Servlet or EJB).
			 * // You can use this anotation until The DynamicWebProject 3.1
			 * // If you want to use it in 4.0, you need to follow this instructions:
			 * // http://balusc.omnifaces.org/2013/10/how-to-install-cdi-in-tomcat.html
			 * @Resource
			 * private UserTransaction ut;  
			 */
			ut = (UserTransaction) ctx.lookup("java:comp/UserTransaction");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Category findById(int id) {
		Category resultado;

		resultado  = em.find(Category.class, id);

		return resultado;
	}
	
	
	public Category findByName(String name) {
		Query q= em.createNamedQuery("Category.findByName");
		
		q.setParameter("categoryName", name);
		
		Category c;
		
		try {
			c =  (Category) q.getSingleResult();		
		} catch (NoResultException n){
			c = null;
		}
		
		return c;

	}
}