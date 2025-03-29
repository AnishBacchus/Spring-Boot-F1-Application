package formula1.example.FormulaOneStatTracker.repository;

import formula1.example.FormulaOneStatTracker.request.DriverRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<DriverRequest, String> {
    List<DriverRequest> findByName(String name);
    List<DriverRequest> findByTeam(String team);

}
