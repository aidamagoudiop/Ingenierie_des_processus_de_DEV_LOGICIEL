package esp.dgi.sb_car_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class RestCarController {
    
    private List<Car> cars = new ArrayList<>();

    public RestCarController() {
        System.out.println("🚀 RestCarController chargé !");

        cars.add(new Car("Toyota", "Corolla"));
        cars.add(new Car("Peugeot", "208"));
        cars.add(new Car("Renault", "Clio"));
    }

    @GetMapping
    public List<Car> getAll() {
        return cars;
    }

    @GetMapping("/{id}")
    public Optional<Car> getById(@PathVariable String id) {
        return cars.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @PostMapping
    public Car create(@RequestBody Car car) {
        Car newCar = new Car(car.getBrand(), car.getModel());
        cars.add(newCar);
        return newCar;
    }

    @PutMapping("/{id}")
    public Car update(@PathVariable String id, @RequestBody Car car) {
        Optional<Car> existing = cars.stream().filter(c -> c.getId().equals(id)).findFirst();
        if (existing.isPresent()) {
            existing.get().setBrand(car.getBrand());
            existing.get().setModel(car.getModel());
            return existing.get();
        } else {
            Car newCar = new Car(id, car.getBrand(), car.getModel());
            cars.add(newCar);
            return newCar;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        cars.removeIf(c -> c.getId().equals(id));
    }
}
