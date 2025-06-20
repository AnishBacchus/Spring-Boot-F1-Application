package formula1.example.FormulaOneStatTracker.repository;
import formula1.example.FormulaOneStatTracker.request.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, String> {
    List<Driver> findByName(String name);
    List<Driver> findByTeam(String team);
    List<Driver> findAllByOrderByDriverPointsDesc();

}
