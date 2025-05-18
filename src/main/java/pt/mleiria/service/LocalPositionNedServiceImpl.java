package pt.mleiria.service;

import org.springframework.stereotype.Service;
import pt.mleiria.dt.Action;
import pt.mleiria.dt.MessageResponse;
import pt.mleiria.dt.ResponseResult;
import pt.mleiria.entity.LocalPositionNedEntity;
import pt.mleiria.repository.LocalPositionNedRepository;

@Service
public class LocalPositionNedServiceImpl implements LocalPositionNedService {

    private final LocalPositionNedRepository localPositionNedRepository;


    public LocalPositionNedServiceImpl(LocalPositionNedRepository localPositionNedRepository) {
        this.localPositionNedRepository = localPositionNedRepository;
    }

    @Override
    public MessageResponse process(LocalPositionNedEntity localPositionNedEntity) {
        // Save the LocalPositionNedEntity object to the database
        final double x1 = -35.3632808;
        final double y1 = 149.1650462;
        final LocalPositionNedEntity savedLocalPositionNedEntity = localPositionNedRepository.save(localPositionNedEntity);
        if (nearlyEqual.test(savedLocalPositionNedEntity.getX(), x1)
                && nearlyEqual.test(savedLocalPositionNedEntity.getY(), y1)) {
            return new MessageResponse("The coordinates are correct. Point 1 checked!", ResponseResult.OK);
        }


        return new MessageResponse(savedLocalPositionNedEntity.toString(), ResponseResult.OK);
    }
}
