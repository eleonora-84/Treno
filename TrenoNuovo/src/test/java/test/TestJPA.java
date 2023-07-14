package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import builder.TrenoBuilder;
import builder.TN.TNBuilder;
import dao.*;
import daoImpl.*;
import treno.Treno;
import dto.*;

public class TestJPA {

	public static void main(String[] args) {
		// testPeso();
		// eliminaTreno();
		 creaTreno();
		//utenti();
	}
	
	public static void eliminaTreno() {
		TrenoDao trenoDao = TrenoDaoImpl.getInstance();
		
		trenoDao.deleteTreno(21);
		
	}

	public static void utenti() {
		System.out.println("Test utenti");
		
		
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager entitymanager = emFactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		UtenteDao utenteDao = UtenteDaoImpl.getInstance();
		
//		System.out.println(utenteDao.findByID(1));
		System.out.println("Trova utente by user " + utenteDao.findByUsername("user"));
		System.out.println("Trova utente by psw " + utenteDao.findByPassword("psw1"));
		
		
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emFactory.close();
	}
	
	public static void testPeso() {

		System.out.println("Test peso");	
		TrenoBuilder builderTN = new TNBuilder();
		
		Treno trenoTN = builderTN.costruisci("HCCCCC");
		
		System.out.println(trenoTN);
	}

	public static void creaTreno() {
		System.out.println("Test treno");
		
		String siglaTrenord = "HCCCCC";
		
		TrenoBuilder builderTN = new TNBuilder();
		
		Treno trenoTN = builderTN.costruisci(siglaTrenord);
		Treno trenoTN2 = builderTN.costruisci("HPPPRPPP");
		Treno trenoTN3 = builderTN.costruisci("HRPPP");
		Treno trenoTN4 = builderTN.costruisci("HCCCC");
		
		TrenoDao trenoDao = TrenoDaoImpl.getInstance();

		
		
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager entitymanager = emFactory.createEntityManager();
		entitymanager.getTransaction().begin();
				
		UtenteDao utenteDao = UtenteDaoImpl.getInstance();
		
		if (utenteDao.findByUsername("utenteTest1") != null) {
			utenteDao.deleteUtenteByID(utenteDao.findByUsername("utenteTest1").getId());
		}
		
		utenteDao.add("utenteTest1", "psw1");
		
//		trenoDao.add(trenoTN, utenteDao.findByUsername("utenteTest1"));
//		trenoDao.add(trenoTN2, utenteDao.findByUsername("utenteTest1"));
//		trenoDao.add(trenoTN3, utenteDao.findByUsername("utenteTest1"));
//		trenoDao.add(trenoTN4, utenteDao.findByUsername("utenteTest1"));
//		System.out.println(trenoTN);
		System.out.println(trenoTN2);
		System.out.println(trenoTN3);
		System.out.println(trenoTN4);

//
//		entitymanager.persist(trenoDao.add(trenoTN, utente));
//		entitymanager.persist(trenoDao.add(trenoTN2, utente));
//		entitymanager.persist(trenoDao.add(trenoTN3, utente2));
//		entitymanager.persist(trenoDao.add(trenoTN4, utente2));
		

		
		TrenoDTO trenoID2 = entitymanager.find(TrenoDTO.class, 9);
		
		System.out.println(trenoID2);
		
	}

}