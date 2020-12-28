package com.maliware.let.srecruit.configuration.security;

import com.maliware.let.srecruit.model.User;
import com.maliware.let.srecruit.repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepo userRepo;

    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepo.findByUsername(s)
                .map(Mapper::new)
                .orElseThrow(() -> new UsernameNotFoundException(s));
    }

    public static class Mapper extends User implements UserDetails{
        public Mapper(User user){
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return super.getUserAuthorities().stream()
                    .map(userAuthority -> new SimpleGrantedAuthority(userAuthority.getAuthority()))
                    .collect(Collectors.toList());
        }

        //        Override
//        public Collection<? extends GrantedAuthority> getAuthorities(){
//            return super.getAuthorities().stream()
//                    .map(userAuthority -> new SimpleGrantedAuthority(userAuthority.getAuthority()))
//                    .collect(Collectors.toList());
//        }
        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return super.getEnabled();
        }
    }
}
