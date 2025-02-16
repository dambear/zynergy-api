package com.danbear.zynergy.administrator;


import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
  boolean existsByEmail(String email);
}
