package com.ut.tinyurl.utils;

import com.ut.tinyurl.model.URL;
import com.ut.tinyurl.repository.URLRepository;
import com.ut.tinyurl.resource.URLController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
public class RedirectURL {
    @Autowired
    URLRepository urlRepository;

    public String redirectShortURLLink(String shortURL){
      Optional<URL> url = urlRepository.findById(shortURL);
      URL url1;
      if(url.isPresent()){
          url1 = url.get();
          String longurl = url1.getLongURL();
          return longurl;
      }

          return "url does not exist...";
    }
}
