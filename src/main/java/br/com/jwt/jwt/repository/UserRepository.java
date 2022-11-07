package br.com.jwt.jwt.repository;

import org.springframework.stereotype.Repository;

import br.com.jwt.jwt.entity.UserEntity;


import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query(value="select u from UserEntity u where u.email = ?1")
    UserEntity findUserByEmail(String email);
    
    @Modifying
    @Query(nativeQuery = true, value="update UserEntity set token = ?1 where email = ?2")
    void atualizaTokenUser(String token, String email);
}
