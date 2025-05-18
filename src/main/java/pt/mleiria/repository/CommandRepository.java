package pt.mleiria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.mleiria.entity.CommandEntity;

import java.util.List;

public interface CommandRepository extends JpaRepository<CommandEntity, Long> {

    CommandEntity findByName(String name);

    List<CommandEntity> findAllByName(String name);
}
