package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.MotivationalQuote;
import minh.lehong.yourwindowyoursoul.repository.MotivationalQuoteRepository;
import minh.lehong.yourwindowyoursoul.service.MotivationalQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MotivationalQuoteServiceImpl implements MotivationalQuoteService {

    @Autowired
    MotivationalQuoteRepository motivationalQuoteRepository;

    @Override
    public MotivationalQuote findById(UUID uuid) {
        return motivationalQuoteRepository.findById(uuid)
                .orElseThrow(() -> new DBException("No MotivationalQuoteID found!"));
    }

    @Override
    public MotivationalQuote getFirstMotivationalQuote() {
        return motivationalQuoteRepository.findAll().get(0);
//                .getFirstMotivationalQuote()
//                .orElseThrow(() -> new DBException("Get First MotivationalQuote error!"));
    }
}
