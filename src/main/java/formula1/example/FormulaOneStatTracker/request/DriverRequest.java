package formula1.example.FormulaOneStatTracker.request;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Entity
public class DriverRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

        String name;
        String team;

        public String getName() {
            return name;
        }

        public String getTeam() {
            return team;
        }

        public void setName(String name) {

            this.name = name;
        }

        public void setTeam(String team) {
            this.team = team;
        }

    @Override
    public String toString() {
        return name + ", " + team;
    }
}

