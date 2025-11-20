package com.project.cateringboys.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cateringboys.model.ContactMessage;
import com.project.cateringboys.repo.ContactMessageRepo;
import com.project.cateringboys.service.ContactMessageService;

@Service
public class ContactMessageServiceImpl implements ContactMessageService {
	
	@Autowired
	private ContactMessageRepo   contactMessageRepo;

	@Override
	public ContactMessage saveMessage(ContactMessage message) {
		
		return contactMessageRepo.save(message);
	}

}
