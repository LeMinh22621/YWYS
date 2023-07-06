package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.model.entity.MotivationalQuote;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MotivationalQuoteService {
    MotivationalQuote findById(UUID uuid);

    MotivationalQuote getFirstMotivationalQuote();

    MotivationalQuote getRandomMotivationalQuote();

    List<MotivationalQuote> getAllMotivationalQuotes();

    MotivationalQuote save(MotivationalQuote motivationalQuote);

    void deleteMotivationalQuoteById(UUID fromString);
}
