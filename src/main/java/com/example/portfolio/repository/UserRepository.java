package com.example.portfolio.repository;


import com.example.portfolio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
@EnableJpaRepositories(basePackages = "com.example.portfolio.repository")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

