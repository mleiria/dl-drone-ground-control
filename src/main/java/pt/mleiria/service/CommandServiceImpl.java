package pt.mleiria.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pt.mleiria.commands.CommandUtils;
import pt.mleiria.commands.DroneCommand;
import pt.mleiria.core.CommandClient;
import pt.mleiria.core.Validator;
import pt.mleiria.dt.MessageResponse;
import pt.mleiria.dt.json.ActionVo;
import pt.mleiria.dto.Pair;
import pt.mleiria.entity.CommandAckEntity;
import pt.mleiria.entity.CommandEntity;
import pt.mleiria.entity.MissionNameEntity;
import pt.mleiria.entity.WayPointEntity;
import pt.mleiria.repository.CommandAckRepository;
import pt.mleiria.repository.CommandRepository;
import pt.mleiria.repository.MissionNameRepository;
import pt.mleiria.repository.WayPointRepository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class CommandServiceImpl implements CommandService {

    private static final Logger log = LoggerFactory.getLogger(CommandServiceImpl.class);

    private final CommandClient commandClient;
    private final CommandRepository commandRepository;
    private final CommandAckRepository commandAckRepository;
    private final WayPointRepository wayPointRepository;
    private final MissionNameRepository missionNameRepository;

    public CommandServiceImpl(final CommandClient commandClient,
                              final CommandRepository commandRepository,
                              final WayPointRepository wayPointRepository,
                              final MissionNameRepository missionNameRepository,
                              final CommandAckRepository commandAckRepository) {
        this.commandClient = commandClient;
        this.commandRepository = commandRepository;
        this.wayPointRepository = wayPointRepository;
        this.missionNameRepository = missionNameRepository;
        this.commandAckRepository = commandAckRepository;
    }

    public String sendGenericCommand(final Pair<String, String> commandAndAction) {
        final Mono<CommandAckEntity> cmdResp = commandClient.sendGenericCommand(new CommandEntity());
        cmdResp.subscribe(
                value -> log.info("Received: {}", value), // On next (success)
                error -> log.error("Error: {}", String.valueOf(error)),     // On error
                () -> log.info("Completed")               // On complete
        );
        return "---";
    }

    @Override
    public List<WayPointEntity> loadMission(final String missionName) {
        return wayPointRepository.findAllByName(missionName);
    }

    @Override
    public void sendCommand(final ActionVo actionVo) {
        final DroneCommand action = DroneCommand.valueOf(actionVo.getAction());
        switch (action){
            case TAKEOFF -> sendTakeoff(actionVo.getParams().getAltitude());
            default -> log.error("Invalid action: {}", actionVo.getAction());
        }

    }

    @Override
    public void sendTakeoff(int altitude) {
        log.info("Command takeoff to send with altitude: {} meters", altitude);
        final String missionName = getActiveMissionName().orElse("Anonymous");
        commandClient.sendGenericCommand(missionName, new Pair<>(DroneCommand.TAKEOFF, altitude))
                .subscribe(value -> log.info("Received: {}", value), // On next (success)
                        error -> log.error("Error: {}", String.valueOf(error)),     // On error
                        () -> log.info("Completed")               // On complete
                );
    }

    @Override
    public Optional<String> getActiveMissionName() {
        final List<MissionNameEntity> missionNameEntityList = missionNameRepository.findMissionNameByIsActive(true);
        final MissionNameEntity missionNameEntity = CommandUtils.getFirstOrNull(missionNameEntityList);
        return Validator.validate(missionNameEntity).map(MissionNameEntity::getMissionName);
    }

    @Override
    public void saveMissionName(String missionName, boolean isActive) {
        missionNameRepository.save(new MissionNameEntity(missionName, isActive));
    }

    @Override
    public void updateMission(final String missionName, final CommandAckEntity commandAckEntity) {
        final List<MissionNameEntity> missionNameEntityList = missionNameRepository.findByMissionName(missionName);
        final MissionNameEntity missionNameEntity = CommandUtils.getFirstOrNull(missionNameEntityList);
        Validator.validate(missionNameEntity).ifPresent(value -> {
            commandAckEntity.setMissionNameId(missionNameEntity.getId());
            commandAckRepository.save(commandAckEntity);
        });
    }
}
