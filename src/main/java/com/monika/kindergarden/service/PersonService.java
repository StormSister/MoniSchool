package com.monika.kindergarden.service;

import com.monika.kindergarden.constans.MoniSchoolConstans;
import com.monika.kindergarden.model.Person;
import com.monika.kindergarden.model.Roles;
import com.monika.kindergarden.repository.PersonRepository;
import com.monika.kindergarden.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final RolesRepository rolesRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createNewPerson(Person person) {
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(MoniSchoolConstans.STUDENT_ROLE);
        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0) {
            isSaved = true;
        }

        return isSaved;
    }

}
