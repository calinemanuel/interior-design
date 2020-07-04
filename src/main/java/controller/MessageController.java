package controller;

import model.DesignerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.MessageService;

@Controller
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping("/new")
    public String addMessage(@ModelAttribute DesignerMessage designerMessage){
        DesignerMessage saved = messageService.addMessage(designerMessage);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String message(Model model){
        model.addAttribute("messages", messageService.retriveMessages());
        return "message";
    }
}
