package ch17.assib.fortnite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * @author Assib Pajman
 *
 */
@Entity
public class Location {
    private static final boolean DEFAULT_AVAILABLE = true;
    @Id @GeneratedValue
    private Long locationId;

    private Boolean available;

    @ManyToOne
    private Chapter chapter;

    public Location(Chapter chapter) {
        this.chapter = chapter;
        this.available = DEFAULT_AVAILABLE;
    }

    public Location() {

    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
