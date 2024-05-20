package com.altynbekova.top.securitydemo.controller;

import com.altynbekova.top.securitydemo.entity.Person;
import com.altynbekova.top.securitydemo.service.PersonService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@Controller
public class PersonController implements UserDetailsService {
    private PersonService personService;

    public PersonController(PersonService service) {
        this.personService = service;
    }

    @GetMapping("/users")
    public String all(Model model) {
        List<Person> users = personService.findAll();
        model.addAttribute("users", users);

        return "user/users";
    }

    @GetMapping("/users/{id}")
    public String find(@PathVariable long id,
                       Model model) {
        Person person = personService.findById(id);
        model.addAttribute("person", person);
        return "user/user";
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = personService.findByName(username)
                .map(p -> new User(
                        p.getName(),
                        p.getPassword(),
                        Collections.singleton(p.getRole())
                )).orElseThrow(() ->
                        new UsernameNotFoundException("Cannot find user: "
                                + username));
        return userDetails;
    }
}
