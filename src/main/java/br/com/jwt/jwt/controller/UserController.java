package br.com.jwt.jwt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jwt.jwt.dto.UserDto;
import br.com.jwt.jwt.entity.UserEntity;
import br.com.jwt.jwt.repository.UserRepository;
import br.com.jwt.jwt.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class UserController {
    
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserDto userDto) {
        var userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        UserEntity user = userRepository.findUserByEmail(userEntity.getEmail());
        if(user == null) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(userEntity));
        }
        return ResponseEntity.status(HttpStatus.OK).body("Emails already exist");
    }

    @GetMapping("/findall")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<UserEntity>> findAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllByUser());
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserEntity userEntity){
		if(userEntity.getUserId() == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não informado");	
		}
		 return ResponseEntity.status(HttpStatus.OK).body(userService.saveAndFlushUser(userEntity));
	}


    
    

}
