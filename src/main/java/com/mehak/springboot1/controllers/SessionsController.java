package com.mehak.springboot1.controllers;

import com.mehak.springboot1.models.Session;
import com.mehak.springboot1.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Session> list()
    {
        return sessionRepository.findAll();

    }
    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id)
    {
        return sessionRepository.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@RequestBody final Session session)
    {
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value="{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id)
    {
        sessionRepository.deleteById(id);
    }
    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Session update(@PathVariable Long id,@RequestBody Session session)
    {
        Session existingSession=sessionRepository.getById(id);
        BeanUtils.copyProperties(session,existingSession,"session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }



}
