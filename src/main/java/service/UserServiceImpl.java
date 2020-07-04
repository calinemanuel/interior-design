package service;

import controller.dto.UserRegistrationDto;
import model.Client;
import model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Client findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Client save(UserRegistrationDto registration){
        Client client = new Client();
        client.setPrenume(registration.getFirstName());
        client.setNume(registration.getLastName());
        client.setEmail(registration.getEmail());
        client.setParola(passwordEncoder.encode(registration.getPassword()));
        client.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(client);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Client save(Client client) {
        return userRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean removeClient(String email) {
        Client clientToBeRemoved = findByEmail(email);
        if (clientToBeRemoved != null){
            userRepository.delete(clientToBeRemoved);
            return true;
        }
        return false;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = userRepository.findByEmail(email);
        if (client == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(client.getEmail(),
                client.getParola(),
                mapRolesToAuthorities(client.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}