package perros;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

public class DAOPerro {
	
	private static DAOPerro daoPerro;
	
	private DAOPerro(){
	}

	public static DAOPerro getInstance() {
		if(daoPerro==null)
			daoPerro=new DAOPerro();
		return daoPerro;
	}

	public Perro findById(int id) {
		
		EntityManager entityManager=JPAHelper.createEntityManager();
		Perro perro=entityManager.find(Perro.class, id);
		entityManager.close();
		return perro;
	
	}

	public Perro create(Perro perro) {
		EntityManager entityManager=JPAHelper.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(perro);
		entityManager.getTransaction().commit();
		entityManager.close();
		return perro;
	}

}
