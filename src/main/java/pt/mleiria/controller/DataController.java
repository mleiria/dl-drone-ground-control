package pt.mleiria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.mleiria.dt.DestinationQueue;
import pt.mleiria.dt.MessageResponse;
import pt.mleiria.entity.GlobalPositionIntEntity;
import pt.mleiria.entity.LocalPositionNedEntity;
import pt.mleiria.service.GlobalPositionIntService;
import pt.mleiria.service.LocalPositionNedService;
import pt.mleiria.service.MessageSender;
import reactor.core.publisher.Mono;

/**
 * Receives data from mavlink
 */
@RestController
@RequestMapping("/api")
public class DataController {

    private final LocalPositionNedService localPositionNedService;
    private final GlobalPositionIntService globalPositionIntService;
    private final MessageSender messageSender;

    public DataController(LocalPositionNedService localPositionNedService,
                          GlobalPositionIntService globalPositionIntService,
                          MessageSender messageSender) {
        this.localPositionNedService = localPositionNedService;
        this.globalPositionIntService = globalPositionIntService;
        this.messageSender = messageSender;
    }

    private static final Logger log = LoggerFactory.getLogger(DataController.class);

    @PostMapping("/commandack")
    public void submitCommandAck(@RequestBody String request) {
        messageSender.sendMessage(request, DestinationQueue.COMMAND_ACK);

    }

    /**
     * Position:
     * <p>
     * North (X): The position of the vehicle along the northward axis.
     * This is the distance from a reference point (often the takeoff point) in the northward direction.
     * East (Y): The position of the vehicle along the eastward axis.
     * This is the distance from the reference point in the eastward direction.
     * Down (Z): The position of the vehicle along the downward axis.
     * This is the distance below the reference point, typically measured as altitude below the starting altitude.
     * <p>
     * Velocity:
     * <p>
     * Velocity North (Vx): The velocity of the vehicle along the northward axis.
     * Velocity East (Vy): The velocity of the vehicle along the eastward axis.
     * Velocity Down (Vz): The velocity of the vehicle along the downward axis.
     *
     * @param request
     * @return
     */
    @PostMapping("/local-position-ned")
    public Mono<ResponseEntity<MessageResponse>> submitLocalPositionNed(@RequestBody LocalPositionNedEntity request) {
        // In a real scenario, you might process or save the request data here
        final MessageResponse response = localPositionNedService.process(request);

        // Create a response that echoes the message from the request (plus additional info)
        // final MessageResponse response = new MessageResponse("Received: " + request.toString(), System.currentTimeMillis());
        log.info("Request: {},", request.toString());
        log.info("ResponseResult: {},", response.toString());

        // Return the response wrapped in a Mono with HTTP status CREATED
        return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

    /**
     * Latitude: The latitude of the vehicle in degrees, expressed as an integer (usually in microdegrees, so you
     * may need to divide by 1e7 to convert to standard degrees).
     * <p>
     * Longitude: The longitude of the vehicle in degrees, also expressed as an integer (in microdegrees).
     * <p>
     * Altitude: The altitude above mean sea level in millimeters.
     * <p>
     * Relative Altitude: The altitude relative to the ground or takeoff point, also in millimeters.
     * <p>
     * Velocity: The ground speed in the north, east, and down directions.
     * <p>
     * Heading: The vehicle's heading in centidegrees.
     *
     * @param request
     * @return
     */
    @PostMapping("/global-position-int")
    public Mono<ResponseEntity<MessageResponse>> submitGlobalPositionInt(@RequestBody GlobalPositionIntEntity request) {
        final MessageResponse response = globalPositionIntService.process(request);

        return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(response));
    }


}
