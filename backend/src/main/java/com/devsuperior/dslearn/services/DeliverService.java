package com.devsuperior.dslearn.services;

import java.util.LinkedHashSet;
import java.util.Set;

import com.devsuperior.dslearn.dtos.DeliverRevisionDTO;
import com.devsuperior.dslearn.entities.Deliver;
import com.devsuperior.dslearn.observers.DeliverRevisionObserver;
import com.devsuperior.dslearn.repositories.DeliverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * DeliverService
 */

@Service
public class DeliverService {

    @Autowired
    private DeliverRepository repo;


    private Set<DeliverRevisionObserver> deliverRevisionObservers = new LinkedHashSet<>();


    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    @Transactional
    public void saveRevision(Long id, DeliverRevisionDTO dto){

        Deliver deliver = repo.getOne(id);

        deliver.setStatus(dto.getStatus());
        deliver.setFeedback(dto.getFeedback());
        deliver.setCorrectCount(dto.getCorrectCount());

        repo.save(deliver);
    }
    
    public void subscribeDeliverRevisionObserver(DeliverRevisionObserver observer){
        deliverRevisionObservers.add(observer);
    }




}