package formula1.example.FormulaOneStatTracker.controller;

import formula1.example.FormulaOneStatTracker.repository.DriverRepository;
import formula1.example.FormulaOneStatTracker.request.DriverRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
public class F1Controller {

    @Autowired
    private DriverRepository driverRepository;

    // Method to add drivers with JSON(Postman).

    @PostMapping("/add")
    public ResponseEntity<String> addDriver(@RequestBody DriverRequest driver) {
        driverRepository.save(driver);
        return ResponseEntity.status(HttpStatus.CREATED).body(driver + " added successfully."); //Status 201
    }
    //-----------------------------------------------------------------------------------------------------------
    //Method to get all the drivers in the list.

    @GetMapping("/drivers")
    public ResponseEntity<List<DriverRequest>> getDrivers() {
        return ResponseEntity.status(HttpStatus.OK).body(driverRepository.findAll()); //Status 200
    }

    //-----------------------------------------------------------------------------------------------------------
    //Method to get a driver based on their name. Uses PathVariable for name in URL.

    @GetMapping("/drivers/name/{name}")
    public ResponseEntity<List<DriverRequest>> getDriversByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(driverRepository.findByName(name)); //Status 200
    }

    //-----------------------------------------------------------------------------------------------------------
    //Method to get the drivers by their team. Uses PathVariable for team in URL.

    @GetMapping("/drivers/team/{team}")
    public ResponseEntity<List<DriverRequest>> getDriversByTeam(@PathVariable String team) {
        return ResponseEntity.status(HttpStatus.OK).body(driverRepository.findByTeam(team)); //Status 200
    }

    //-----------------------------------------------------------------------------------------------------------
    //Method to replace a driver with another. Uses PathVariables to get the old and new driver.

    @PutMapping("/replace/{oldName}/{newName}")
    public ResponseEntity<String> updateDriver(@PathVariable String oldName, @PathVariable String newName) {
        List<DriverRequest> drivers = driverRepository.findByName(oldName);

        if (drivers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(oldName + " not a current driver.");
        }
        drivers.get(0).setName(newName);
        driverRepository.save(drivers.get(0));
        return ResponseEntity.status(HttpStatus.OK).body(newName + " has replaced " + oldName + " for the season."); //Status 200
    }

    //-----------------------------------------------------------------------------------------------------------
    //Method to delete a driver.

    @DeleteMapping("/remove/{name}")

    public ResponseEntity<String> removeDriver(@PathVariable String name) {
        List<DriverRequest> drivers = driverRepository.findByName(name);

        if (drivers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Driver not found");
        }
        driverRepository.delete(drivers.get(0));
        return new ResponseEntity(HttpStatus.NO_CONTENT); //Status 204
    }

    //-----------------------------------------------------------------------------------------------------------


}
