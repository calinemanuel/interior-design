package controller;

import model.Client;
import model.Favorites;
import model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.FavoritesService;
import service.ItemService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    FavoritesService favoritesService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    ItemService itemService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

    @GetMapping("/logged")
    public String logged(){
        return "logged";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/myProfile")
    public String myProfile(Model model, HttpServletRequest request)
    {
        model.addAttribute("client", userService.findByEmail(request.getRemoteUser()));
        model.addAttribute("clienti", userService.findAll());

        List<Favorites> favorites = favoritesService.findAll(userService.findByEmail(request.getRemoteUser()).getIdclient());

        model.addAttribute("favorites", favorites);

        return "myProfile";
    }

    @PostMapping("/favorite/{id}/{idclient}")
    public String addFavorite(@PathVariable Long id, @PathVariable Long idclient){
        Favorites favorites = new Favorites();
        favorites.setIdclient(idclient);

        Item item = itemService.findById(id).get();
        favorites.setTitle(item.getContent());
        favorites.setContent(item.getContent());
        favorites.setImg(item.getImg());

        favoritesService.add(favorites);


        return "redirect:/myProfile";

    }

    @GetMapping("/edit")
    public String edit(Model model, HttpServletRequest request){
        Client user = userService.findByEmail(request.getRemoteUser());
        model.addAttribute("client", user);
        return "edit";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable String id, Model model){
        Optional<Client> clientOptional = userService.findById(Long.valueOf(id));
        clientOptional.ifPresent(client -> model.addAttribute("client", client));
        //model.addAttribute("client", userService.findById(id));
        return "editUser";
    }

    @PostMapping("/edit/{id}")//HttpServletRequest request
    public String editClient(@ModelAttribute Client user){
        boolean deleted = userService.removeClient(user.getEmail());
        if (deleted){
            userService.save(user);
            user.setParola(passwordEncoder.encode(user.getParola()));
        } else {
            System.err.println("Error updating/deleting");
        }
        return "redirect:/";
    }
}