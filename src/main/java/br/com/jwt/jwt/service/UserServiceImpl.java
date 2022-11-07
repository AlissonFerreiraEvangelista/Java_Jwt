package br.com.jwt.jwt.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.jwt.jwt.entity.UserEntity;
import br.com.jwt.jwt.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado" + email);
        }
        return new User(user.getEmail(), 
                        user.getPassword(),
                        user.isEnabled(), 
                        user.isAccountNonExpired(), 
                        user.isCredentialsNonExpired(),
                        true,
                        user.getAuthorities());
    }
    
}
