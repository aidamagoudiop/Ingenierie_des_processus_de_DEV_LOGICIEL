package dam.uasz.sbcar2;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dam.uasz.sbcar2.domain.Car;
import dam.uasz.sbcar2.domain.CarRepository;
import dam.uasz.sbcar2.domain.Owner;
import dam.uasz.sbcar2.domain.OwnerRepository;

@SpringBootApplication
public class SbCar2Application implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(SbCar2Application.class);

    @Autowired
    private CarRepository carRepository;
	@Autowired
    private OwnerRepository ownerRepository;


    public static void main(String[] args) {
        SpringApplication.run(SbCar2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        carRepository.deleteAll();
        ownerRepository.deleteAll();

    // Création des propriétaires
    Owner owner1 = new Owner("John", "Johnson");
    Owner owner2 = new Owner("Mary", "Robinson");
    
    // Création des voitures
    Car car1 = new Car("Ford", "Mustang", "Red", "ADF-1121", 2021, 59000);
    Car car2 = new Car("Nissan", "Leaf", "White", "SSJ-3002", 2019, 29000);
    
    // Établissement des relations ManyToMany 
    owner1.getCars().add(car1);  // John possède la Mustang
    owner1.getCars().add(car2);  // John possède aussi la Leaf
    owner2.getCars().add(car2);  // Mary possède la Leaf

    owner1.addCar(car1); // Utilisez la méthode addCar() qui gère la bidirectionnalité
    owner2.addCar(car2);
    
    // Sauvegarde en cascade (grâce à CascadeType.PERSIST)
    ownerRepository.save(owner1);
    ownerRepository.save(owner2); 
    
    logger.info("### Liste des propriétaires avec leurs voitures ###");
        for (Owner owner : ownerRepository.findAll()) {
            logger.info(owner.getFirstname() + " " + owner.getLastname() + " possède :");
            for (Car car : owner.getCars()) {
                logger.info("- " + car.getBrand() + " " + car.getModel());
            }
        }
        
    logger.info("\n### Liste des voitures avec leur propriétaire ###");
        for (Car car : carRepository.findAll()) {
            String ownerName = car.getOwner() != null ? 
                car.getOwner().getFirstname() + " " + car.getOwner().getLastname() : "Aucun";
            logger.info(car.getBrand() + " " + car.getModel() + " - Propriétaire: " + ownerName);
        }
}
    // public void run(String... args) throws Exception {

	// 	Owner owner1 = new Owner("John", "Johnson");
    //     Owner owner2 = new Owner("Mary", "Robinson");
    //     orepository.save(owner1);
    //     orepository.save(owner2);
  
	// 	Car car1 = new Car("Ford", "Mustang", "Red", "ADF-1121", 2021, 59000);
	// 	car1.setOwner(owner1);
    //     repository.save(car1);

    //     Car car2 = new Car("Nissan", "Leaf", "White", "SSJ-3002", 2019, 29000);
	// 	car2.setOwner(owner2);
    //     repository.save(car2);

    //     // Car car3 = new Car("Toyota", "Prius", "Silver", "KKO-0212", 2020, 39000);
    //     // car3.setOwner(owner3);
    //     // repository.save(car3);

    //     repository.saveAll(Arrays.asList(car1, car2));
        
    //     for (Car car : repository.findAll()) {
    //         logger.info(car.getBrand() + " " + car.getModel() + ", Owner: " + 
    //                   car.getOwner().getFirstname() + " " + car.getOwner().getLastname());
    //     }
    // }
}