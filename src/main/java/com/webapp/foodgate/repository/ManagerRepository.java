package com.webapp.foodgate.repository;

import com.webapp.foodgate.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ManagerRepository extends JpaRepository<Manager, Long> {
//, JpaSpecificationExecutor<Manager>
}
