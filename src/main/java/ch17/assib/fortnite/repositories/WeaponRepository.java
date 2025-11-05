package ch17.assib.fortnite.repositories;

import ch17.assib.fortnite.model.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Assib Pajman
 *
 */
public interface WeaponRepository extends JpaRepository<Weapon, Long> {
}
