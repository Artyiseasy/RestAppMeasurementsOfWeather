package ru.tarasov.springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tarasov.springcourse.FirstRestApp.models.Sensor;

import java.util.Optional;


@Repository
public interface SensorRepository extends JpaRepository<Sensor, String> {
    // ищем сенсор по имени в БД
        Optional<Sensor> findByName (String name);
}
