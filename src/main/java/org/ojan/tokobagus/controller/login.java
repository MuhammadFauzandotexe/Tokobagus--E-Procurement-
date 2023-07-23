package org.ojan.tokobagus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class login {
    @GetMapping("test")
    public String test(@RequestBody Object object){
        return object.toString();
    }

}
