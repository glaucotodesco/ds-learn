package com.devsuperior.dslearn.resources;

import com.devsuperior.dslearn.dtos.NotificationDTO;
import com.devsuperior.dslearn.services.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notifications")
public class NotificationResouce {

	@Autowired
	private NotificationService service;

    @GetMapping
    public ResponseEntity<Page<NotificationDTO>> findAllPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "unreadOnly", defaultValue = "false") Boolean unreadOnly
        ) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<NotificationDTO> dtoPage = service.notificationsForCurrentUser(unreadOnly,pageRequest);
        return ResponseEntity.ok().body(dtoPage);
    }

}
