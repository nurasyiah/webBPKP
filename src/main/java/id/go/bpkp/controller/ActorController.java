package id.go.bpkp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.go.bpkp.dao.ActorDAO;
import id.go.bpkp.dao.FilmDAO;
import id.go.bpkp.entity.Actor;

@Controller
@RequestMapping("/actor")
public class ActorController {
	
	@Autowired
	private ActorDAO actorDAO;
	@Autowired
	private FilmDAO filmDAO;
	
	@GetMapping("/index")
	public String index(Model model) {
		String [] bahasa = {
			"Melayu", "Tagalog", "Thailand"	
		};
		model.addAttribute("bahasa", bahasa);
		model.addAttribute("negara", "Indonesia");
		model.addAttribute("actors", actorDAO.allActor());
		return "actor/index";
	}
	
	@GetMapping("/detail/{id}")
	public String detail (@PathVariable("id") int actorId, Model model){
		model.addAttribute("actor", actorDAO.getActor(actorId));
		model.addAttribute("films", actorDAO.getFilms(actorId));
		return "actor/detail";
	}
	
	@GetMapping("/film/{id}")
	public String film (@PathVariable("id") int filmId, Model model){
		model.addAttribute("film",filmDAO.getFilm(filmId));
		model.addAttribute("Actors", actorDAO.getActorsInFilm(filmId));
		return "actor/film";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("actor", new Actor());
		return "actor/add";
	}
	
	@PostMapping("/add")
	public String add(Actor actor) {
		actorDAO.addActor(actor);
		return "redirect:/actor/index";
	}
	
	/*tambahan edit*/
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int actorId, Model model) {
		model.addAttribute("actor", actorDAO.getActor(actorId));
		return "actor/edit";
	}
	
	@PostMapping("/edit")
	public String edit(Actor actor) {
		actorDAO.editActor(actor);
		return "redirect:/actor/index";
	}
	
	
	
	

}
