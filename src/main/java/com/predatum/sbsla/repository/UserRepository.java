package com.predatum.sbsla.repository;

import com.predatum.sbsla.entity.Application;
import com.predatum.sbsla.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Marcos Peña
 */
public interface UserRepository extends JpaRepository<User, Long>
{
    User findOneByEmail(String email);

    User findOneByConfirmationToken(String confirmationToken);

    List<User> findByApplications(Application application);
}
