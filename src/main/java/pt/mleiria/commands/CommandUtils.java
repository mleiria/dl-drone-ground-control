package pt.mleiria.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.mleiria.core.Validator;

import java.util.List;
import java.util.function.Function;

public class CommandUtils {

    private static final Logger log = LoggerFactory.getLogger(CommandUtils.class);

    public static Function<Boolean, List<String>> getArmDisarmParams =
            isArm -> isArm ? List.of("1", "0", "0", "0", "0", "0", "0") :
                    List.of("0", "0", "0", "0", "0", "0", "0");

    public static Function<String, Function<List<String>, String>> convToStr =
            delimiter -> params -> String.join(delimiter, params);



    public static <T> T getFirstOrNull(List<T> list) {
        if(Validator.isNotEmptyList(list)){
            if(list.size() > 1){
                log.warn("More than one element found. Returning the first one.");
            }
            return list.get(0);
        }else {
            log.warn("Element not found. Returning null.");
            return null;
        }
    }


}
