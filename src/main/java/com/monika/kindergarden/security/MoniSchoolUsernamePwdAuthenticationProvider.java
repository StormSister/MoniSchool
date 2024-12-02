package com.monika.kindergarden.security;

import com.monika.kindergarden.model.Person;
import com.monika.kindergarden.model.Roles;
import com.monika.kindergarden.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MoniSchoolUsernamePwdAuthenticationProvider implements AuthenticationProvider {


    private PersonRepository personRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public MoniSchoolUsernamePwdAuthenticationProvider(PersonRepository person, PasswordEncoder passwordEncoder){
        this.personRepository = person;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Person person = personRepository.readByEmail(email);

        if (person != null && person.getPersonId() > 0 &&
                passwordEncoder.matches(pwd, person.getPwd())) {
            return new UsernamePasswordAuthenticationToken(person.getName(), null, getGrantedAuthorities(person.getRoles()));
        } else {
            throw new BadCredentialsException("Invalid email or password");
        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + roles.getRoleName()));
        return authorities;
    }
}

