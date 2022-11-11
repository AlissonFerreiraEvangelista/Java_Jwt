package br.com.jwt.jwt.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jwt.jwt.entity.UserEntity;
import br.com.jwt.jwt.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder(){	 
		return new BCryptPasswordEncoder();
	}

    public UserEntity findUserEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Transactional
    public UserEntity saveUser(UserEntity userEntity){
        userEntity.setPassword(passwordEncoder().encode(userEntity.getPassword()));
        return userRepository.save(userEntity);

    }

    public List<UserEntity> findAllByUser() {
        return userRepository.findAll();
    }
    @Transactional
    public UserEntity saveAndFlushUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder().encode(userEntity.getPassword()));
        return userRepository.saveAndFlush(userEntity);
    }

    
}
