package id.go.bpkp.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.go.bpkp.entity.Film;
import id.go.bpkp.entity.FilmActor;

@Service
public class FilmDAO {
	
	//Film film=new Film();
	@Autowired
	private EntityManagerFactory emf;
	
	public List<Film> allFilm (){
		return (List<Film>) emf.createEntityManager().createQuery("from Film").getResultList();
	}
	
	public Film getFilm(int id){
		
		return  (Film) emf.createEntityManager().createQuery("FROM Film WHERE filmId =" + id).getSingleResult();
	}


}
