/*
  Created by IntelliJ IDEA.
  User: Long
  Date: 2018/10/24
  Time: 10:14
  To change this template use File | Settings | File Templates.
*/
package org.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
