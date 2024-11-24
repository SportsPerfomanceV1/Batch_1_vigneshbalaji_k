package com.example.demo.Repo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Enities.Event;

public interface EventRepo  extends JpaRepository<Event, Integer>{

	public Optional<Event> findByEventTitle(String eventTitle);
	
	public boolean existsByEventTitle(String eventTitle);
	
	@Query(value = "SELECT * FROM Event AS e ORDER BY e.event_id DESC LIMIT 1", nativeQuery = true)
	public Optional<Event> findLastRow();

	    @Query(value = "SELECT * FROM event WHERE event_id IN (:eventIds)", nativeQuery = true)
		public List<Event> getRegevent(@Param("eventIds") Set<Integer> eventIds);
}
