package com.core.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.core.repository.ExploreRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class ExploreTest {

	@Autowired
	ExploreRepository repo;

	@Test
	public void testCreation() {
		log.info("Starting testing creation");

		/* creating a record */
		Explore e1 = new Explore((long) 1, "John", true, LocalTime.of(10, 00, 00), LocalDate.of(2020, 10, 30),
				LocalDateTime.parse("2020/12/30 12:15:22", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
		repo.save(e1);
		Assert.assertEquals(1, repo.count());

		/* checking if all was created ok */
		Assert.assertEquals(1, repo.findById(1L).get().getIds().intValue());
		Assert.assertEquals("John", repo.findById(1L).get().getNames());
		Assert.assertEquals(true, repo.findById(1L).get().getFlags());
		Assert.assertEquals(LocalTime.of(10, 00, 00), repo.findById(1L).get().getTimes());
		Assert.assertEquals(LocalDate.of(2020, 10, 30), repo.findById(1L).get().getDates());
		Assert.assertEquals(
				LocalDateTime.parse("2020/12/30 12:15:22", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")),
				repo.findById(1L).get().getTimestamps());

		repo.deleteById(1L);
		log.info("Finishing testing creation");

	}

	@Test
	public void testUpdate() {

		log.info("Starting testing replace");
		Explore e2 = new Explore(2L, "John", true, LocalTime.of(10, 00), LocalDate.of(2020, 10, 30),
				LocalDateTime.parse("2020/12/30 12:15:22", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
		repo.save(e2);

		Assert.assertEquals(1L, repo.count());

		Explore e3 = repo.findById(2L).get();
		e3.setNames("TheOtherJohn");
		repo.save(e3);
		Assert.assertEquals("TheOtherJohn", repo.findById(2L).get().getNames());

		log.info("Finishing testing replace");

	}

	@Test
	public void testFindDateRange() {
		Explore e1 = new Explore((long) 1, "John", true, LocalTime.of(10, 00, 00), LocalDate.of(2020, 8, 20),
				LocalDateTime.parse("2020/12/20 10:15:22", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
		Explore e2 = new Explore(2L, "John", true, LocalTime.of(11, 00), LocalDate.of(2020, 9, 20),
				LocalDateTime.parse("2020/12/20 11:15:22", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
		Explore e3 = new Explore(2L, "John", true, LocalTime.of(12, 00), LocalDate.of(2020, 10, 20),
				LocalDateTime.parse("2020/12/20 12:15:22", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

		repo.save(e1);
		repo.save(e2);
		repo.save(e3);
		Assert.assertEquals(3L, repo.count());

		Assert.assertEquals(2, repo.readAllByDatesBetween(LocalDate.of(2020, 9, 1), LocalDate.of(2020, 10, 21)).size());
		Assert.assertEquals(1, repo.readAllByDatesBetween(LocalDate.of(2020, 9, 1), LocalDate.of(2020, 10, 19)).size());
		Assert.assertEquals(3, repo.readAllByDatesBetween(LocalDate.of(2020, 8, 1), LocalDate.of(2020, 11, 19)).size());

	}

}
