package com.predatum.sbsla.repository;

import com.predatum.sbsla.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Marcos Peña
 */
public interface ApplicationRepository extends JpaRepository<Application, Long>
{
}
