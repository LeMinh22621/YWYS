package minh.lehong.yourwindowyoursoul.service.impl;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.MotivationalQuote;
import minh.lehong.yourwindowyoursoul.repository.MotivationalQuoteRepository;
import minh.lehong.yourwindowyoursoul.service.MotivationalQuoteService;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.connection.jedis.JedisUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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
        return motivationalQuoteRepository.getFirstMotivationalQuote()
                .orElseThrow(() -> new DBException("GET first MotivationalQuote Error!"));
    }
    private List<MotivationalQuote> getAllMotivationalQuote(){
        List<MotivationalQuote> motivationalQuotes =  motivationalQuoteRepository.findAll();
        return motivationalQuotes;
    }
    @Override
    public MotivationalQuote getRandomMotivationalQuote() {
        List<MotivationalQuote> motivationalQuotes = getAllMotivationalQuote();
        Random random = new Random();
        int size = motivationalQuotes.size();
        int randomIndex = random.nextInt(size);
        return motivationalQuotes.get(randomIndex);
    }

    @Override
    public List<MotivationalQuote> getAllMotivationalQuotes() {
        return motivationalQuoteRepository.findAll();
    }

    @Override
    public MotivationalQuote save(MotivationalQuote motivationalQuote) {
        return motivationalQuoteRepository.save(motivationalQuote);
    }

    @Override
    public void deleteMotivationalQuoteById(UUID uuid) {
        motivationalQuoteRepository.deleteById(uuid);
    }
}