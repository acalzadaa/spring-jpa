package com.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.model.Explore;
import com.core.repository.ExploreRepository;

@Service
public class ExploreService {

	@Autowired
	ExploreRepository repo;

	public List<Explore> getAllExplore() {
		List<Explore> exploreList = new ArrayList<>();
		repo.findAll().forEach(row -> exploreList.add(row));
		return exploreList;
	}

	public void addExplores(List<Explore> explores) {
		explores.stream().forEach(list -> repo.save(list));
	}

	public void addExplore(Explore explore) {
		repo.save(explore);
	}

	public void deleteExplore(Explore explore) {
		repo.delete(explore);
	}

	public void deleteExploreById(Long id) {
		repo.deleteById(id);
	}

	public Optional<Explore> getExploreById(long id) {
		return repo.findById(id);
	}
}
