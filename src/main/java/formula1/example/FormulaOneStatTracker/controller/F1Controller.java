package formula1.example.FormulaOneStatTracker.controller;

import formula1.example.FormulaOneStatTracker.DTO.DriverDTO;
import formula1.example.FormulaOneStatTracker.Service.DriverService;
import formula1.example.FormulaOneStatTracker.request.Driver;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;



@CrossOrigin(origins = "*")
@RestController
public class F1Controller {

    @Autowired
    private DriverService driverService;

    //-----------------------------------------------------------------------------------------------------------
    // Method to add drivers with JSON(Postman).

    @PostMapping("/add")
    public ResponseEntity<String> addDriver(@RequestBody @Valid Driver driver) {
        return driverService.add(driver); //Status 201
    }
    //-----------------------------------------------------------------------------------------------------------
    //Method to get all the drivers in the list.

    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getDrivers() {
        return driverService.getAll(); //Status 200
    }

    //-----------------------------------------------------------------------------------------------------------
    //Method to get a driver based on their name. Uses PathVariable for name in URL.

    @GetMapping("/drivers/name/{name}")
    public ResponseEntity<List<Driver>> getDriversByName(@PathVariable String name) {
        return driverService.getByName(name); //Status 200
    }

    //-----------------------------------------------------------------------------------------------------------
    //Method to get the drivers by their team. Uses PathVariable for team in URL.

    @GetMapping("/drivers/team/{team}")
    public ResponseEntity<List<Driver>> getDriversByTeam(@PathVariable String team) {
        return driverService.getByTeam(team); //Status 200
    }

    //-----------------------------------------------------------------------------------------------------------
    //Method to get the drivers standings.

    @GetMapping("/drivers/standings")
    public ResponseEntity<List<DriverDTO>> getStandings() {
        return driverService.getDriverStandings();  //Status 200
    }

    //-----------------------------------------------------------------------------------------------------------
    //Method to replace a driver with another. Uses PathVariables to get the old and new driver.

    @PutMapping("/replace/{current}")
    public ResponseEntity<String> updateDriver(@PathVariable String current, @RequestBody Driver newDriver) {

        return driverService.update(current, newDriver); //Status 200
    }

    //-----------------------------------------------------------------------------------------------------------
    //Method to delete a driver.

    @DeleteMapping("/remove/{name}")
    public ResponseEntity<String> removeDriver(@PathVariable String name) {
        return driverService.remove(name);//Status 204

    }

}
