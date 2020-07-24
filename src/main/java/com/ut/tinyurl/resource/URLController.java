package com.ut.tinyurl.resource;

import com.ut.tinyurl.model.URL;
import com.ut.tinyurl.repository.URLRepository;
import com.ut.tinyurl.utils.GetShortURLFromRedis;
import com.ut.tinyurl.utils.RedirectURL;
import com.ut.tinyurl.utils.ValidateLongURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class URLController {

    @Autowired
    private URLRepository urlRepository;

    @Autowired
    ValidateLongURL validateLongURL;

    @Autowired
    GetShortURLFromRedis getShortURLFromRedis;

    @Autowired
    RedirectURL rurl;



    /*
   apis:
   /putLongURL : returns available short url in redis, removes it from redis and marks an entry in URL table
   www.ut.com/shortURL : redirects to long url
    */
    @PostMapping("/{longurl}")
    public String putURL(String longurl){
        //validate long url
//        longurl="https://google.com/";
        System.out.println(longurl);
        if(validateLongURL.validateURL(longurl)) {
            //check if url already exists in the db (add one cache in future)
            //get short url from redis and delete it
            String shorturl = getShortURLFromRedis.getShortURLFromRedis();
            System.out.println(shorturl);
            //put long/short url into url db
            URL url = new URL(shorturl,longurl);
            urlRepository.save(url);
            //return short url
            return "short url...."+"http://ut.com/"+shorturl;
        }
        return "url is not valid.";
    }


    @GetMapping("/{id}")
    public RedirectView redirectURL(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){
        System.out.println("redirect url called");
        String urltoredirect = rurl.redirectShortURLLink(id);
        System.out.println("short url: "+id);
        System.out.println("long url: "+urltoredirect);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(urltoredirect);
        return redirectView;
    }

}
