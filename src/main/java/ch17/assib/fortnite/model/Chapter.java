package ch17.assib.fortnite.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

/**
 * @author Assib Pajman
 *
 */

@Entity
public class Chapter {

    @Id @GeneratedValue
    Long chapterId;

    @Column(unique = true)
    Integer chapterNumber;

    @ManyToMany
    private Set<Weapon> weapons;

    @OneToMany(mappedBy = "chapter")
    private List<Location> locations;

    public int getNumberOfLocations() {
        return locations.size();
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public Set<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(Set<Weapon> weapons) {
        this.weapons = weapons;
    }
}
