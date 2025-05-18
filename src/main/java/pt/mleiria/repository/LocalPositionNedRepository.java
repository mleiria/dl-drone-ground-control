package pt.mleiria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.mleiria.entity.LocalPositionNedEntity;

public interface LocalPositionNedRepository extends JpaRepository<LocalPositionNedEntity, Long> {

}
