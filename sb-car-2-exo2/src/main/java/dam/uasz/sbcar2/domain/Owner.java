package dam.uasz.sbcar2.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ownerId;
    
    private String firstname;
    private String lastname;
    

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "car_owner",
        joinColumns = @JoinColumn(name = "owner_id"),
        inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private Set<Car> cars = new HashSet<>();


    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    // private List<Car> cars;
    // @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Car> cars = new ArrayList<>(); // Initialisation ici
    

    // Constructeurs, getters et setters
    public Owner() {
        this.cars = new ArrayList<>(); 
    }
    
    public Owner(String firstname, String lastname) {
        this();
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }


    public List<Car> getCars() {
        if (cars == null) {
            cars = new ArrayList<>();
        }
        return cars;
    }
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }


    public void addCar(Car car) {
        cars.add(car);
        car.setOwner(this);
    }
    
    public void removeCar(Car car) {
        cars.remove(car);
        car.setOwner(null);
    }

}