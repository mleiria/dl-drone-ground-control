package pt.mleiria.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pt.mleiria.commands.DroneCommand;
import pt.mleiria.dto.Pair;
import pt.mleiria.entity.CommandAckEntity;
import pt.mleiria.entity.CommandEntity;
import reactor.core.publisher.Mono;

@Component
public class CommandClient {

    private static final Logger log = LoggerFactory.getLogger(CommandClient.class);

    private final WebClient client;

    @Value("${drone.url}")
    private String droneUrl;

    @Value("${drone.url.generic}")
    private String droneUrlGeneric;

    @Value("${drone.url.mavlink}")
    private String droneUrlMavlink;

    // Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
    // We can use it to create a dedicated `WebClient` for our component.
    public CommandClient(WebClient.Builder builder) {
        this.client = builder.baseUrl(droneUrl).build();
    }

    public Mono<CommandAckEntity> sendGenericCommand(final CommandEntity commandEntity) {
        final String url = droneUrlMavlink + commandEntity.getName() + "/" + commandEntity.getOptions();
        log.info("URL: {}", url);
        return this.client.get().uri(url).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CommandAckEntity.class);
                //.map(Greeting::getMessage);
    }

    public <U> Mono<Integer>  sendGenericCommand(final String missionName, final Pair<DroneCommand, U> commandEntity) {
        final String url = droneUrlMavlink + missionName + "/" + commandEntity._1().getCommand() + "/" + commandEntity._2();
        log.info("URL: {}", url);
        return this.client.get().uri(url).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity()
                .map(response -> response.getStatusCode().value());
    }
}
