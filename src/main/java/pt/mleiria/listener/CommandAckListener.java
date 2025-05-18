package pt.mleiria.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import pt.mleiria.core.JacksonUtils;
import pt.mleiria.dt.RequestParams;
import pt.mleiria.entity.CommandAckEntity;
import pt.mleiria.parser.CommandParser;
import pt.mleiria.service.CommandService;

import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class CommandAckListener {

    private static final Logger log = LoggerFactory.getLogger(CommandAckListener.class);
    private final CommandService commandService;

    // A consumer to notify when a new value is available.
    private Consumer<CommandAckEntity> valueListener;

    public CommandAckListener(final CommandService commandService) {
        this.commandService = commandService;
    }

    public void setValueChangeListener(Consumer<CommandAckEntity> listener) {
        this.valueListener = listener;
    }

    @JmsListener(destination = "COMMAND_ACK", containerFactory = "myFactory")
    public void recMsgCommandAck(final String request) {
        try {
            final Map<String, String> req = JacksonUtils.decode(request, Map.class);
            log.info("Req: {},", req);
            final String missionName = req.get(RequestParams.MISSION_NAME.getValue());
            final String cmdResp = req.get(RequestParams.CMD_RESP.getValue());
            final CommandAckEntity commandAckEntity = CommandParser.parseCommandAck(cmdResp);

            valueListener.accept(commandAckEntity);
            commandService.updateMission(missionName, commandAckEntity);
        }catch (IOException e){
            log.error(e.getMessage(), e);
        }
    }


}
