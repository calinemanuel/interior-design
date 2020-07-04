package service;

import model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item addItem(Item item){
        if (item != null){
            return itemRepository.save(item);
        } else {
            throw new IllegalArgumentException("cannot save items");
        }
    }

    public Optional<Item> getItems(Long id){
        return itemRepository.findById(id);
    }

    public List<Item> retriveItems(){
        return  itemRepository.findAll();
    }
    public Optional<Item> findById(Long id){
        return itemRepository.findById(id);
    }
}
