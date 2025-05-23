package dam.uasz.sbcar2.domain;

//    ajoutes apres 
import jakarta.persistence.*;


@Entity
public class Car {
    //    ajoutes apres 
    // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")


    @ManyToMany(mappedBy = "cars")
    // private Set<Owner> owners = new HashSet<>();

    private Owner owner;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String brand, model, color, registerNumber ;
    private int years, price;
       
    // Constructeurs
    public Car() {}
    
    public Car(String brand, String model, String color, String registerNumber, 
               int years, int price) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.years = years;
        this.price = price;
    }
    
    // Getters et setters

    public Owner getOwner() {
    return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public int getyears() {
        return years;
    }

    public void setyears(int years) {
        this.years = years;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    // Méthode toString() pour l'affichage
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", registerNumber='" + registerNumber + '\'' +
                ", years=" + years +
                ", price=" + price +
                '}';
    }
}