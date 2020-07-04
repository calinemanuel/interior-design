package controller;


import model.Item;
import model.News;
import model.Project;
import service.ItemService;
import service.ProjectService;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Controller
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemService itemService;



    @Autowired
    UserService userService;

    @GetMapping("/all")
    public String getItems(Model model, HttpServletRequest request){
        model.addAttribute("items", itemService.retriveItems());
        model.addAttribute("client", userService.findByEmail(request.getRemoteUser()));
        return "items";
    }

    @GetMapping("/{id}")
    public String getItemsById(@PathVariable String id, Model model){
        Optional<Item> itemOptional = itemService.getItems(Long.valueOf(id));
        itemOptional.ifPresent(items -> model.addAttribute("items", items));
        return "items";
    }

    @PostMapping("/additems")
    public String addItems(@ModelAttribute @NotEmpty Item item){
        Item saved = itemService.addItem(item);
        return "redirect:/items/all";
    }
}
