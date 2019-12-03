package com.onoriel.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onoriel.userservice.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
