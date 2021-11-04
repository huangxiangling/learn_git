package cn.com.sheep.control;

import cn.com.sheep.service.SheepTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SheepTestController {

    @Autowired
    private SheepTestService sheepTestService;

    @GetMapping("/getList")
    public List<SheepTestService> getList(){

        List<SheepTestService> list = sheepTestService.selectList();
        return list;
    }

    public static void main(String[] args) {

        String s = "中中、、中中、、中国、国国、、";

        s.replaceAll("(、+)","");
        s.replaceAll("(.)\\1+","$1");
        System.out.println(s);
    }
}
