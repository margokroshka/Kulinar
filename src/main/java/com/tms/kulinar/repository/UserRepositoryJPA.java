package com.tms.kulinar.repository;

import com.tms.kulinar.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepositoryJPA extends JpaRepository<User, Integer> {
    @Query(value = "SELECT u FROM User u WHERE u.id=:id")
    User findUsersById(int id);

   User findUsersByLogin(String login);

    @Query(nativeQuery = true, value = "SELECT role FROM roles WHERE user_id=:id")
    String getRole(int id);
    @Modifying
    @Query( value = "INSERT INTO roles(id,user_id,role) VALUES (DEFAULT,:userId, 'USER')", nativeQuery = true)
    void addRole(Integer userId);

}
