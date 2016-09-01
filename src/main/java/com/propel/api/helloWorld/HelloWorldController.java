package com.propel.api.helloWorld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nick Schramm on 8/19/16.
 */
@RestController
public class HelloWorldController {

    @RequestMapping(path="/hello",method = RequestMethod.GET)
    public String get(){
        return "HelloWorld";
    }
}
