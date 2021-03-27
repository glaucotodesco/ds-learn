package com.devsuperior.dslearn.services;

import java.time.Instant;

import javax.annotation.PostConstruct;

import com.devsuperior.dslearn.dtos.NotificationDTO;
import com.devsuperior.dslearn.entities.Deliver;
import com.devsuperior.dslearn.entities.Notification;
import com.devsuperior.dslearn.entities.User;
import com.devsuperior.dslearn.observers.DeliverRevisionObserver;
import com.devsuperior.dslearn.repositories.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService implements DeliverRevisionObserver{

    @Autowired
    private NotificationRepository repo;

    @Autowired
    private AuthService authService;

    @Autowired
    private DeliverService deliverService;

    @PostConstruct
    public void initalize(){
        deliverService.subscribeDeliverRevisionObserver(this);
    }

    @Transactional(readOnly = true)
    public Page<NotificationDTO> notificationsForCurrentUser(boolean unreadOnly, Pageable pageable) {
        User user = authService.getAuthenticatedUser().get();
        Page<Notification> page = repo.find(user, unreadOnly, pageable);
        return page.map(n -> new NotificationDTO(n));
    }



    @Transactional
    public void saveDeliverNotificatation(Deliver deliver){
        
        Long sectionId   = deliver.getLesson().getSection().getId();
        Long resourceId  = deliver.getLesson().getSection().getResource().getId();
        Long offerId     = deliver.getLesson().getSection().getResource().getOffer().getId();

        String route = "/offers/" + offerId + "/resources/" + resourceId + "/sections" + sectionId;
        String text  = deliver.getFeedback();
        Instant moment = Instant.now();

        User user = deliver.getEnrollment().getStudent();

        Notification notification = new Notification(null, text, moment, false, route, user);

        repo.save(notification);

    }

    @Override
    public void onSaveRevision(Deliver deliver) {
        saveDeliverNotificatation(deliver);
    }

}
