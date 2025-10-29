package ch17.assib.fortnite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

/**
 * @author Assib Pajman
 *
 */

@Controller
public class ChapterController {

    @GetMapping("chapters")
    private static String showChapterOverview(Model datamodel) {
        datamodel.addAttribute("requesttime", LocalDateTime.now());

        return "chapterOverview";
    }
}
