package ch17.assib.fortnite.controller;

import ch17.assib.fortnite.model.Chapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Assib Pajman
 *
 */

@Controller
public class ChapterController {

    @GetMapping("/chapters")
    private static String showChapterOverview(Model datamodel) {
        ArrayList<Chapter> chapters = new ArrayList<>();

        chapters.add(new Chapter(1));
        chapters.add(new Chapter(2));

        datamodel.addAttribute("chapters", chapters);

        return "chapterOverview";
    }
}
