package ch17.assib.fortnite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

/**
 * @author Assib Pajman
 *
 */

@Entity
public class Chapter {

    @Id @GeneratedValue
    Long chapterId;

    Integer chapterNumber;

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
}
