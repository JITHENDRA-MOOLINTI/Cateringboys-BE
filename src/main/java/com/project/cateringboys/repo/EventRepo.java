package com.project.cateringboys.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cateringboys.model.Event;
import com.project.cateringboys.model.User;

@Repository
public interface EventRepo extends JpaRepository<Event, Long>{
	
    List<Event> findByCreatedBy(User userId);
    List<Event> findByIdNotIn(List<Long> ids);
}
