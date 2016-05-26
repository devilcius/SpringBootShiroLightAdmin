package com.predatum.sbsla.repository;

import com.predatum.sbsla.entity.Application;
import com.predatum.sbsla.entity.Feature;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Marcos Peña
 */
public interface FeatureRepository extends JpaRepository<Feature, Long>
{
    List<Feature> findByApplication(Application application);
}
