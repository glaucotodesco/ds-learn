package com.devsuperior.dslearn.services;

import com.devsuperior.dslearn.dtos.DeliverRevisionDTO;
import com.devsuperior.dslearn.entities.Deliver;
import com.devsuperior.dslearn.repositories.DeliverRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * DeliverService
 */

@Service
public class DeliverService {

    @Autowired
    private DeliverRepository repo;

    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    @Transactional
    public void saveRevision(Long id, DeliverRevisionDTO dto){

        Deliver deliver = repo.getOne(id);

        deliver.setStatus(dto.getStatus());
        deliver.setFeedback(dto.getFeedback());
        deliver.setCorrectCount(dto.getCorrectCount());

        repo.save(deliver);
    }
    
}