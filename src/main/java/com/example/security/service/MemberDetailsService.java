package com.example.security.service;

import org.springframework.security.core.userdetails.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.entity.Member;
import com.example.security.repository.MemberRepository;

@Service
public class MemberDetailsService implements UserDetailsService{
    @Autowired MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username)
        .orElseThrow(()->new UsernameNotFoundException("사용자 없음 : " + username));

        return new User(
            member.getUsername(),
            member.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

}
