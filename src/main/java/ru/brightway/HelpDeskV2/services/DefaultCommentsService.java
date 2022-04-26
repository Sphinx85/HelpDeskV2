package ru.brightway.HelpDeskV2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.Entites.Comments;
import ru.brightway.HelpDeskV2.repository.CommentsRepository;
import ru.brightway.HelpDeskV2.services.interfaces.CommentsService;

@Service
public class DefaultCommentsService implements CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public void save(Comments comment) {
        commentsRepository.save(comment);
    }
}
