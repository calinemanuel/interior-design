package service;

import controller.dto.UserRegistrationDto;
import model.Client;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    Client findByEmail(String email);

    Client save(UserRegistrationDto registration);

    Optional<Client> findById(Long id);

    Client save (Client client);

    List<Client> findAll();

    boolean removeClient(String id);






}