package com.webapp.foodgate.repository;

import com.webapp.foodgate.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface GroupRepository extends JpaRepository<Group, Long>, JpaSpecificationExecutor<Group> {

    public Group findFirstByKind(int kind);

    boolean existsByKind(int kind);

    @Query(" SELECT CASE WHEN COUNT(g)>0 THEN true ELSE false END " +
            " FROM Group g JOIN g.permissions p " +
            " WHERE p.pCode = :pCode AND g.id = :id")
    Boolean existPermissionOfGroup(@Param("pCode") String pCode,
                                   @Param("id") Long id);


}
