package service;

import model.Favorites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FavoritesRepository;

import java.util.List;

@Service
public class FavoritesService {
    @Autowired
    FavoritesRepository favoritesRepository;

    public List<Favorites> findAll(Long id){
        return favoritesRepository.findAll(id);
    }


    public void add(Favorites favorites){
        favoritesRepository.save(favorites);
    }
}