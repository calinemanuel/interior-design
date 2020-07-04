package service;

import model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.NewsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public News addNews(News news){
        if (news != null){
            return newsRepository.save(news);
        } else {
            throw new IllegalArgumentException("cannot save news");
        }
    }

    public Optional<News> getNews(Long id){
        return newsRepository.findById(id);
    }
    public List<News> getAllNews(){
        return newsRepository.findAll();
    }
}
