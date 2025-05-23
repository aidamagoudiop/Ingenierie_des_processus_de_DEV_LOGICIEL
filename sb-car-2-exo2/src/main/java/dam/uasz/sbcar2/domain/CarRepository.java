package dam.uasz.sbcar2.domain;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
    // Méthodes CRUD de base fournies par CrudRepository
}