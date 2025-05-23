package esp.dgi.sb_coffee_1;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffees")
public class RestCoffeeController {

    private List<Coffee> coffees = new ArrayList<>();

    public RestCoffeeController() {
        coffees.add(new Coffee("Café Touba"));
        coffees.add(new Coffee("Café Nespresso"));
        coffees.add(new Coffee("Café Capuccino"));
    }

    // 1) GET all
    @GetMapping
    public List<Coffee> getAllCoffees() {
        return coffees;
    }

    // 2) GET one by ID
    @GetMapping("/{id}")
    public Optional<Coffee> getCoffeeById(@PathVariable String id) {
        return coffees.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    // 3) POST (create)
    @PostMapping
    public Coffee addCoffee(@RequestBody Coffee coffee) {
        Coffee newCoffee = new Coffee(coffee.getName());
        coffees.add(newCoffee);
        return newCoffee;
    }

    // 4) PUT (update)
    @PutMapping("/{id}")
    public Coffee updateCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
        Optional<Coffee> existing = coffees.stream().filter(c -> c.getId().equals(id)).findFirst();
        if (existing.isPresent()) {
            existing.get().setName(coffee.getName());
            return existing.get();
        } else {
            Coffee newCoffee = new Coffee(id, coffee.getName());
            coffees.add(newCoffee);
            return newCoffee;
        }
    }

    // 5) DELETE
    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable String id) {
        coffees.removeIf(c -> c.getId().equals(id));
    }
}
