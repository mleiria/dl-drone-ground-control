package pt.mleiria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.mleiria.entity.MissionNameEntity;

import java.util.List;

public interface MissionNameRepository extends JpaRepository<MissionNameEntity, Long> {

    List<MissionNameEntity> findByMissionName(String missionName);

    List<MissionNameEntity> findMissionNameByIsActive(Boolean isActive);
}
