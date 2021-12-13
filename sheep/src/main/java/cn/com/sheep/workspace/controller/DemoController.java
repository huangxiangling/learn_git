package cn.com.sheep.workspace.controller;

import cn.com.sheep.workspace.service.HelloFunction;
import cn.com.sheep.workspace.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/hello/{str}")
    public String hello(@PathVariable String str){
        return helloService.hello(str);
    }


    public String helloFunction(){




        return null;
    }
}
