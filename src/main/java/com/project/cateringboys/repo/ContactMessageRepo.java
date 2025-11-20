package com.project.cateringboys.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cateringboys.model.ContactMessage;

@Repository
public interface ContactMessageRepo extends JpaRepository<ContactMessage, Long> {

}
