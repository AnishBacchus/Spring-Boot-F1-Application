package formula1.example.FormulaOneStatTracker.Service;

import formula1.example.FormulaOneStatTracker.DTO.DriverDTO;
import formula1.example.FormulaOneStatTracker.repository.DriverRepository;
import formula1.example.FormulaOneStatTracker.request.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    //-----------------------------------------------------------------------------------------------------------
    // Method to add drivers with JSON(Postman).

    public ResponseEntity<String> add(Driver driver){
        driverRepository.save(driver);
        return ResponseEntity.status(HttpStatus.CREATED).body(driver + " added successfully.");

    }

    //-----------------------------------------------------------------------------------------------------------
    //Method to get all the drivers in the list.

    public ResponseEntity<List<Driver>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(driverRepository.findAll()); //Status 200
    }

    //-----------------------------------------------------------------------------------------------------------
    //Method to get a driver based on their name. Uses PathVariable for name in URL.
    public ResponseEntity<List<Driver>> getByName(String name) {
        return ResponseEntity.status(HttpStatus.OK).body(driverRepository.findByName(name)); //Status 200
    }

    //-----------------------------------------------------------------------------------------------------------
    //Method to get the drivers by their team. Uses PathVariable for team in URL.
    public ResponseEntity<List<Driver>> getByTeam(String team) {
        return ResponseEntity.status(HttpStatus.OK).body(driverRepository.findByTeam(team)); //Status 200
    }

    //-----------------------------------------------------------------------------------------------------------
    //Method to get the drivers standings.

    public ResponseEntity<List<DriverDTO>> getDriverStandings() {
        List<DriverDTO> driverStandings = driverRepository.findAllByOrderByDriverPointsDesc().stream()
                .map(driver -> new DriverDTO(driver.getName(), driver.getTeam(), driver.getDriverPoints()))
                .collect(Collectors.toList());

        //Status 200
        return ResponseEntity.ok(driverStandings);
    }
    //-----------------------------------------------------------------------------------------------------------
    //Method to replace a driver with another. Uses PathVariables to get the old and new driver.

    public ResponseEntity<String> update(String current, Driver newDriver) {
        List<Driver> drivers = driverRepository.findByName(current);

        if (drivers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(current + " not a current driver.");
        }

        Driver existingDriver = drivers.get(0);
        existingDriver.setName(newDriver.getName());
        existingDriver.setDriverNumber(newDriver.getDriverNumber());
        driverRepository.save(existingDriver);


        return ResponseEntity.status(HttpStatus.OK).body(newDriver.getName() + " has replaced " + current + " for the season."); //Status 200
    }

    //-----------------------------------------------------------------------------------------------------------
    //Method to delete a driver.

    public ResponseEntity<String> remove(String name) {
        List<Driver> drivers = driverRepository.findByName(name);

        if (drivers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Driver not found");
        }
        driverRepository.delete(drivers.get(0));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //Status 204
    }

}
