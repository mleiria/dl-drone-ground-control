package pt.mleiria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.mleiria.entity.WayPointEntity;

import java.util.List;

public interface WayPointRepository extends JpaRepository<WayPointEntity, Long> {

    List<WayPointEntity> findAllByName(String name);
}
