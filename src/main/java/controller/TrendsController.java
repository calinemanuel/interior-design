package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrendsController {
    @GetMapping("/trends2017")
    public String trend2017(){
        return "trend2017";
    }

    @GetMapping("/trends2018")
    public String trend2018(){
        return "trend2018";
    }

    @GetMapping("/trends2019")
    public String trend2019(){
        return "trend2019";
    }

    @GetMapping("/trends2020")
    public String trend2020(){
        return "trend2020";
    }
}
