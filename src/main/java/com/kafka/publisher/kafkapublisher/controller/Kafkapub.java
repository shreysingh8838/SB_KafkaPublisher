package com.kafka.publisher.kafkapublisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class Kafkapub {

    @Autowired
    private KafkaTemplate<String, Object> template; // STEP1
    // Created our custom Kafka Template in config package/config class
    // main object created, we will use its send() method to send the data into kafka topic

    static final String Topic = "test-topic";
    @GetMapping("/publish/{message}/{name}")
    public String publishMessage(@PathVariable("message") String message, @PathVariable("name") String name){
        template.send(Topic, "Hi, I am " + name + " and i wanted to say " + message + ".");
        return "Data Published";
    }

    @GetMapping("/publishjson")
    public String publishJson(){
        User user = new User(124, "User124", new String[]{ "Banglore","Delhi", "Pune" });
        template.send(Topic, user);
        return "JSON data Published";
    }

    @GetMapping("/publish/hello")
    public String publishHi(){
        return "Hi, its working";
    }
}
