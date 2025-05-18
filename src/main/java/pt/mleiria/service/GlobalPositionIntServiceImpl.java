package pt.mleiria.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pt.mleiria.core.JacksonUtils;
import pt.mleiria.dt.Action;
import pt.mleiria.dt.MessageResponse;
import pt.mleiria.dt.ResponseResult;
import pt.mleiria.dto.Triplet;
import pt.mleiria.entity.GlobalPositionIntEntity;
import pt.mleiria.entity.WayPointEntity;
import pt.mleiria.repository.GlobalPositionIntRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class GlobalPositionIntServiceImpl implements GlobalPositionIntService {

    private static final Logger log = LoggerFactory.getLogger(GlobalPositionIntServiceImpl.class);

    private static final ConcurrentLinkedQueue<WayPointEntity> wpFlightPlan = new ConcurrentLinkedQueue<>();
    private static Map<Long, Integer> checkPointController = new ConcurrentHashMap<>();

    private final GlobalPositionIntRepository globalPositionIntRepository;
    private final CommandService commandService;

    public GlobalPositionIntServiceImpl(final GlobalPositionIntRepository globalPositionIntRepository,
                                        final CommandService commandService) {
        this.globalPositionIntRepository = globalPositionIntRepository;
        this.commandService = commandService;
    }

    @Override
    public MessageResponse process(GlobalPositionIntEntity globalPositionIntEntity) {
        final GlobalPositionIntEntity savedGlobalPositionIntEntity = globalPositionIntRepository.save(globalPositionIntEntity);
        WayPointEntity wayPointEntity = null;
        if (wpFlightPlan.isEmpty() && checkPointController.isEmpty()) {
            log.info("Loading flight plan... Mission_1");
            commandService.loadMission("Mission_1")
                    .forEach(wp -> {
                        wpFlightPlan.add(wp);
                        checkPointController.put(wp.getId(), 0);
                    });
            log.info("WP size: {}", wpFlightPlan.size());
            wayPointEntity = wpFlightPlan.poll();
        }
        try {




            final Triplet<Double, Double, Double> gps = new Triplet<>(
                    wayPointEntity.getLatitude(), wayPointEntity.getLongitude(), wayPointEntity.getAltitude());

            if (nearlyEqualConvertDivide.test(savedGlobalPositionIntEntity.getLat(), gps._1())
                    && nearlyEqualConvertDivide.test(savedGlobalPositionIntEntity.getLon(), gps._2())) {

                log.info("Current WP: {}", wayPointEntity.toString());
                log.info("CheckPoint Controller data: {}", checkPointController.toString());
                if (checkPointController.get(wayPointEntity.getId()) == 0) {
                    savedGlobalPositionIntEntity.setLat(convertMult.apply(gps._1()));
                    savedGlobalPositionIntEntity.setLon(convertMult.apply(gps._2()));
                    savedGlobalPositionIntEntity.setRelative_alt(gps._3().intValue());
                    log.info("New GlobalPositionIntEntity Checkpoint: {}", savedGlobalPositionIntEntity);
                    checkPointController.put(wayPointEntity.getId(), 1);
                    return new MessageResponse(JacksonUtils.encode(savedGlobalPositionIntEntity), ResponseResult.OK);
                }
            }
            return new MessageResponse(JacksonUtils.encode(savedGlobalPositionIntEntity), ResponseResult.OK);
        } catch (JsonProcessingException e) {
            log.error("Error processing JSON: {}", e.getMessage());
            return new MessageResponse(e.getMessage(), ResponseResult.OK);
        }
    }
    @Override
    public void clearFlightPlan() {
        wpFlightPlan.clear();
        checkPointController.clear();
    }
}