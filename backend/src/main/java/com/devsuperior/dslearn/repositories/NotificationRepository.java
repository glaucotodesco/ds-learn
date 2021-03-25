package com.devsuperior.dslearn.repositories;

import com.devsuperior.dslearn.entities.Notification;
import com.devsuperior.dslearn.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository  extends JpaRepository<Notification, Long>{

    @Query("SELECT n FROM Notification n WHERE "+
            " (n.user =  :user)  AND "            +
            " (:unreadOnly = false OR n.read = false)  "                +
            " ORDER BY  n.moment DESC"
    )
    public Page<Notification> find(User user, boolean unreadOnly, Pageable pageable);

}
