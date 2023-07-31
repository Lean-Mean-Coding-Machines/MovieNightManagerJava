package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.controller.security.Authorize;
import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.mapper.NominationsMapper;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.model.nomination.NominationRequest;
import com.carterprojects.movienightmanager.service.NominationService;
import com.carterprojects.movienightmanager.service.NotificationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationServiceImpl;

    @Authorize
    @PostMapping("email")
    public MnmApiResponse sendEmailNotification() throws MessagingException {
        notificationServiceImpl.sendEmailNotification();
        return MnmApiResponse.success(null);
    }
}