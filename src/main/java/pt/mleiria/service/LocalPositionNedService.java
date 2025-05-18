package pt.mleiria.service;

import pt.mleiria.dt.MessageResponse;
import pt.mleiria.entity.LocalPositionNedEntity;

public interface LocalPositionNedService extends PositionService<LocalPositionNedEntity, MessageResponse> {

    MessageResponse process(LocalPositionNedEntity localPositionNedEntity);
}
