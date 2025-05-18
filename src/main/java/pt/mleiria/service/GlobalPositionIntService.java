package pt.mleiria.service;

import pt.mleiria.dt.MessageResponse;
import pt.mleiria.entity.GlobalPositionIntEntity;

public interface GlobalPositionIntService extends PositionService<GlobalPositionIntEntity, MessageResponse> {

    void clearFlightPlan();

    MessageResponse process(GlobalPositionIntEntity globalPositionIntEntity);
}
