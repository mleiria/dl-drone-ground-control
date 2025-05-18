package pt.mleiria.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.mleiria.entity.CommandAckEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public class CommandParser {
    private static final Logger log = LoggerFactory.getLogger(CommandParser.class);

    private CommandParser() {}

    public static CommandAckEntity parseCommandAck(final String input) {
        log.info("Parsing command ack: {}", input);
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input string is null or empty");
        }

        // Extract the content inside the curly braces
        final int start = input.indexOf('{');
        final int end = input.lastIndexOf('}');
        if (start == -1 || end == -1 || end <= start) {
            throw new IllegalArgumentException("Invalid input format: Missing or misplaced braces");
        }
        final String body = input.substring(start + 1, end).trim();

        // Map the string keys to corresponding setter methods using a BiConsumer
        Map<String, BiConsumer<CommandAckEntity, Integer>> setterMap = new HashMap<>();
        setterMap.put("command", CommandAckEntity::setCommand);
        setterMap.put("result", CommandAckEntity::setResult);
        setterMap.put("progress", CommandAckEntity::setProgress);
        setterMap.put("result_param2", CommandAckEntity::setResultParam2);
        setterMap.put("target_system", CommandAckEntity::setTargetSystem);
        setterMap.put("target_component", CommandAckEntity::setTargetComponent);

        final CommandAckEntity ack = new CommandAckEntity();

        // Process each token in a functional style using streams
        Arrays.stream(body.split(","))
                .map(String::trim)
                .map(token -> token.split(":"))
                .filter(parts -> parts.length == 2)
                .forEach(parts -> {
                    final String key = parts[0].trim();
                    final String valueStr = parts[1].trim();
                    try {
                        int value = Integer.parseInt(valueStr);
                        Optional.ofNullable(setterMap.get(key))
                                .ifPresent(setter -> setter.accept(ack, value));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid number format for key: " + key +
                                " with value: " + valueStr, e);
                    }
                });

        return ack;
    }

}
