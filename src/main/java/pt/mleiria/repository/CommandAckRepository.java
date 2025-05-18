package pt.mleiria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.mleiria.entity.CommandAckEntity;

public interface CommandAckRepository extends JpaRepository<CommandAckEntity, Long> {

}
