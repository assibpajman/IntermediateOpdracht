package ch17.assib.fortnite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Assib Pajman
 *
 */
@Entity
public class Weapon {

    @Id @GeneratedValue
    private Long weaponId;

    private String name;

    @ManyToMany(mappedBy = "weapons")
    private Set<Chapter> chapters = new HashSet<>();

    public Long getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(Long weaponId) {
        this.weaponId = weaponId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Set<Chapter> chapters) {
        this.chapters = chapters;
    }
}
