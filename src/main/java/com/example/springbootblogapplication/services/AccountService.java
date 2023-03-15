package com.example.springbootblogapplication.services;

import com.example.springbootblogapplication.models.Account;
import com.example.springbootblogapplication.models.Authority;
import com.example.springbootblogapplication.repositories.AccountRepository;
import com.example.springbootblogapplication.repositories.AuthorityRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    public Account save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        Set<Authority> authorities1 = new HashSet<>();
        authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
        account.setAuthorities(authorities1);

        return accountRepository.saveAndFlush(account);


    }

    public Optional <Account> findByEmail(String email){
        return accountRepository.findOneByEmail(email);
    }


}
