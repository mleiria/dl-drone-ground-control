package pt.mleiria.service;

import pt.mleiria.dt.MessageResponse;
import pt.mleiria.dt.json.ActionVo;
import pt.mleiria.entity.CommandAckEntity;
import pt.mleiria.entity.WayPointEntity;

import java.util.List;
import java.util.Optional;

public interface CommandService {

    List<WayPointEntity> loadMission(final String missionName);

    void sendTakeoff(int altitude);

    Optional<String> getActiveMissionName();

    void saveMissionName(final String missionName, final boolean isActive);

    void updateMission(final String missionName, CommandAckEntity commandAckEntity);

    void sendCommand(ActionVo action);
}
