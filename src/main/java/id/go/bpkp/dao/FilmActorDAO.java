package id.go.bpkp.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import id.go.bpkp.entity.FilmActor;

@Service
public class FilmActorDAO {
	@Autowired
	private EntityManagerFactory emf;

	
	public List<FilmActor> allFilmActors (){
		return (List<FilmActor>) emf.createEntityManager().createQuery("from FilmActor").getResultList();
	}
	
	public int addFilmActor(short actorId, short filmId) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		int hasil = 0;
		try {
			trans.begin();
			FilmActor filmActor = new FilmActor(actorId, filmId);
			filmActor.setLastUpdate(new Date());
			em.persist(filmActor);
			trans.commit();
		} catch (Exception ex) {
			trans.rollback();
			hasil =-1;
			System.out.println(ex.getMessage());
		}
		return hasil;
	}
}
