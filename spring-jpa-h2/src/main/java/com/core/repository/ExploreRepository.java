package com.core.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.core.model.Explore;

public interface ExploreRepository extends CrudRepository<Explore, Long> {

	List<Explore> readAllByDatesBetween(LocalDate start, LocalDate end);

	List<Explore> readAllByTimesBetween(LocalTime start, LocalTime end);

	List<Explore> readAllByTimestampsBetween(LocalDateTime start, LocalDateTime end);

}
