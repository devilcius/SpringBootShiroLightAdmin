package com.predatum.sbsla.repository;

import com.predatum.sbsla.entity.UserRole;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Marcos Peña
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long>
{
    List<UserRole> findByEmail(String service);
}
