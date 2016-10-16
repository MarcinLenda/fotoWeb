package com.fotoweb.app.controller;

import com.fotoweb.app.entity.MailEntity;
import com.fotoweb.app.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Promar on 12.09.2016.
 */
@RestController
public class MailController {

    private final MailService mailService;
    private final String setTo = "777marcinlenda777@gmail.com";

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }


    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public SimpleMailMessage send(@RequestBody MailEntity mailEntity) {
        mailEntity.setContent(mailEntity.getContent(), mailEntity.getFrom());

        return mailService.mailSend(setTo, mailEntity.getFrom(), mailEntity.getSubject(),
                mailEntity.getContent());
    }
}
