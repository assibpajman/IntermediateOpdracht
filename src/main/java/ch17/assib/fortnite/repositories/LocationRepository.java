package ch17.assib.fortnite.repositories;

import ch17.assib.fortnite.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Assib Pajman
 *
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
}
