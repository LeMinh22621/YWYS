package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.model.entity.MotivationalQuote;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface MotivationalQuoteService {
    MotivationalQuote findById(UUID uuid);

    MotivationalQuote getFirstMotivationalQuote();
}
