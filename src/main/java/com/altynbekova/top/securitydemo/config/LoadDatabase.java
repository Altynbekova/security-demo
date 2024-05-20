package com.altynbekova.top.securitydemo.config;

import com.altynbekova.top.securitydemo.entity.Person;
import com.altynbekova.top.securitydemo.entity.Role;
import com.altynbekova.top.securitydemo.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class LoadDatabase {
    private static final Logger LOG = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase(PersonService personService){
        return args -> {
            Person person = new Person("User1",
                    34, "City1 Street1 Building1");
            person.setPassword(passwordEncoder.encode("1234"));
            person.setRole(Role.ADMIN);
            Person person2 = new Person("User3", 22, "C3 Str3 Bld3");
            person2.setPassword(passwordEncoder.encode("1234"));
            person2.setRole(Role.USER);

            LOG.info("Preloading "+ personService.save(new Person("User2",
                    87, "City2 Street2 Building2", "{noop}1234", Role.USER)));
            LOG.info("Preloading "+ personService.save(person));
            LOG.info("Preloading "+ personService.save(person2));
        };
    }
}

