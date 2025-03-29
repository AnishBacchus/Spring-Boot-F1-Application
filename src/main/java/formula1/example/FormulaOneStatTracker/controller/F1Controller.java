package formula1.example.FormulaOneStatTracker.controller;

import formula1.example.FormulaOneStatTracker.repository.DriverRepository;
import formula1.example.FormulaOneStatTracker.request.DriverRequest;
import formula1.example.FormulaOneStatTracker.request.UpdateDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Driver;
import java.util.List;


@RestController
public class F1Controller {


    @Autowired
    private DriverRepository driverRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addDriver(@RequestBody DriverRequest driver) {
        driverRepository.save(driver);
        return ResponseEntity.status(HttpStatus.CREATED).body(driver + " added successfully.");
    }
    //-----------------------------------------------------------------------------------------------------------

    @GetMapping("/drivers")
    public List<DriverRequest> getDrivers() {
        return driverRepository.findAll();
    }

    //-----------------------------------------------------------------------------------------------------------

    @GetMapping("/drivers/name/{name}")
    public List<DriverRequest> getDriversByName(@PathVariable String name) {

        return driverRepository.findByName(name);
    }

    //-----------------------------------------------------------------------------------------------------------

    @GetMapping("/drivers/team/{team}")
    public List<DriverRequest> getDriversByTeam(@PathVariable String team) {

        return driverRepository.findByTeam(team);
    }

    //-----------------------------------------------------------------------------------------------------------

    @PutMapping("/replace/{oldName}/{newName}")
    public String updateDriver(@PathVariable String oldName, @PathVariable String newName) {
        List<DriverRequest> drivers = driverRepository.findByName(oldName);

        if (drivers.isEmpty()) {
            return oldName + " is currently not a driver";
        }
        drivers.get(0).setName(newName);
        driverRepository.save(drivers.get(0));
        return newName + " has replaced " + oldName + " for the season.";
    }

    //-----------------------------------------------------------------------------------------------------------

    @DeleteMapping("/remove/{name}")
    public String removeDriver(@PathVariable String name) {
        List<DriverRequest> drivers = driverRepository.findByName(name);

        if (drivers.isEmpty()) {
            return "No drivers to remove.";
        }
        driverRepository.delete(drivers.get(0));
        return name + " was removed successfully";
    }

    //-----------------------------------------------------------------------------------------------------------


}
