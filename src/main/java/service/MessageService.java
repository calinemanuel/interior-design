package service;

import model.DesignerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public DesignerMessage addMessage(DesignerMessage designerMessage){
        if(designerMessage != null){
            return messageRepository.save(designerMessage);
        }else {
            throw new IllegalArgumentException("not valid");
        }


    }

    public List<DesignerMessage> retriveMessages(){
        return  messageRepository.findAllByOrderByIdDesc();
    }
}
