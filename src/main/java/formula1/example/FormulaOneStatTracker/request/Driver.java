package formula1.example.FormulaOneStatTracker.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Entity
@Getter
@Setter
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @JsonIgnore
        private long id;

        @NotBlank(message = "Driver must have a name.")
        String name;

        @NotBlank(message = "Driver must be assigned to a team.")
        String team;

        @Min(value = 1, message = "Driver's number must be 1 or larger.")
        int driverNumber;

        int driverPoints;



    @Override
    public String toString() {
        return name + ", " + team + ", " + driverNumber;
    }

}

