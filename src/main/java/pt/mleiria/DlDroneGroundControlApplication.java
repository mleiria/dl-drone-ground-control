package pt.mleiria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pt.mleiria.entity.CommandEntity;
import pt.mleiria.entity.WayPointEntity;
import pt.mleiria.repository.CommandRepository;
import pt.mleiria.repository.WayPointRepository;

@SpringBootApplication(scanBasePackages = {"pt.mleiria", "pt.mleiria.repository", "pt.mleiria.entity"})
public class DlDroneGroundControlApplication {

	private static final Logger log = LoggerFactory.getLogger(DlDroneGroundControlApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DlDroneGroundControlApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(CommandRepository commandRepository,
									  WayPointRepository wayPointRepository) {
		return (args) -> {

			commandRepository.save(new CommandEntity("ping", "1", "See if the system is alive"));
			commandRepository.save(new CommandEntity("connect", "udpin:localhost:14551", "Connection string"));
			commandRepository.save(new CommandEntity("arm", "1", "0;1", "Arm/Disarm command"));
			commandRepository.save(new CommandEntity("takeoffAltitude", "10", "Altitude for the UAV to reach upon takeoff"));
			commandRepository.save(new CommandEntity("mode", "GUIDED", "Mode to change to"));
			commandRepository.save(new CommandEntity("flight_test", "0", "0;1;2",
					"Flight test commands: 0=start, 1=stop, 2=end"));

			// fetch all commands
			log.info("Commands found with findAll():");
			log.info("-------------------------------");
			for (CommandEntity commandEntity : commandRepository.findAll()) {
				log.info(commandEntity.toString());
			}
			// Save test flight plan
			final String testFlightPlan = "Mission_1";
			wayPointRepository.save(new WayPointEntity(testFlightPlan, -35.3632808, 149.1650462, 10.0));
			wayPointRepository.save(new WayPointEntity(testFlightPlan, -35.3621767, 149.1649019, 10.0));
			wayPointRepository.save(new WayPointEntity(testFlightPlan, -35.3621437, 149.1651761, 10.0));
			wayPointRepository.save(new WayPointEntity(testFlightPlan, -35.3629277, 149.1653031, 10.0));
		};


	}


}
