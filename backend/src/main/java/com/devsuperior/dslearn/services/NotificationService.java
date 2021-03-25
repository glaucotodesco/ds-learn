package com.devsuperior.dslearn.services;

import com.devsuperior.dslearn.dtos.NotificationDTO;
import com.devsuperior.dslearn.entities.Notification;
import com.devsuperior.dslearn.entities.User;
import com.devsuperior.dslearn.repositories.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repo;

    @Autowired
    private AuthService authService;

    public Page<NotificationDTO> notificationsForCurrentUser(boolean unreadOnly, Pageable pageable) {
        User user = authService.getAuthenticatedUser().get();
        Page<Notification> page = repo.find(user, unreadOnly, pageable);
        return page.map(n -> new NotificationDTO(n));
    }

}
