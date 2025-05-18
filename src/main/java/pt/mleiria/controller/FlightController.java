package pt.mleiria.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.mleiria.core.JacksonUtils;
import pt.mleiria.dt.MessageResponse;
import pt.mleiria.dt.ResponseResult;
import pt.mleiria.dt.json.ActionVo;
import pt.mleiria.dto.Pair;
import pt.mleiria.service.CommandService;
import pt.mleiria.service.MessageSender;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import static pt.mleiria.dt.ResponseResult.NOK;

/**
 * Controller for handling flight-related operations coming from the webpage.
 */
@RestController
@RequestMapping("/api")
public class FlightController {
    private static final Logger log = LoggerFactory.getLogger(FlightController.class);

    private final CommandService commandService;

    public FlightController(final CommandService commandService) {
        this.commandService = commandService;
    }

    public record ChartData(long timestamp, int value) {}

    private final Random random = new Random();

    @GetMapping(value = "/chart-data", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<ChartData> chartData() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> new ChartData(System.currentTimeMillis(), random.nextInt(100)));
    }

    /**
     * Receive commands from the webpage
     * @param command
     * @return
     */
    @PostMapping("/cmd")
    @ResponseBody  // Return data directly (e.g., JSON)
    public MessageResponse command(@RequestBody String command) {
        try {
            log.info("Command: {}", command);
            final ActionVo actionVo = JacksonUtils.decode(command, ActionVo.class);
            commandService.sendCommand(actionVo);
        }catch (IOException e){
            log.error("Error decoding command: {}", e.getMessage());
            return new MessageResponse(e.getMessage(), NOK);
        }
        // TODO - Implement the message response
        final MessageResponse messageResponse = new MessageResponse("Success", ResponseResult.OK);
        return messageResponse;
    }
}
