package com.webapp.foodgate.repository;


import com.webapp.foodgate.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission>{

    @Query(" SELECT CASE WHEN COUNT(p)>0 THEN true ELSE false END " +
            " FROM Permission p " +
            " WHERE p.pCode = :pCode ")
    boolean existsByPCode(@Param("pCode") String pCode);
    boolean existsByName(String name);

    @Query(" SELECT p " +
            "FROM Permission p " +
    "WHERE p.pCode = :pCode")
    Permission findByPCode(@Param("pCode") String pCode);
}
