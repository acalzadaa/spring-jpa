package com.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.model.Explore;
import com.core.service.ExploreService;

@RestController
@RequestMapping("/explore")
public class ExploreController {

	@Autowired
	ExploreService exploreRepo;

	@GetMapping("/")
	private List<Explore> getAllExplore() {
		return exploreRepo.getAllExplore();
	}

	@GetMapping("/{id}")
	private Explore getExplore(@PathVariable("id") long id) {
		return exploreRepo.getExploreById(id).orElse(null);
	}

	@DeleteMapping("/{id}")
	private void deleteExplore(@PathVariable("id") long id) {
		exploreRepo.deleteExploreById(id);
	}

	@PutMapping("/")
	private void addExplore(@RequestBody Explore explore) {
		exploreRepo.addExplore(explore);
	}

}
