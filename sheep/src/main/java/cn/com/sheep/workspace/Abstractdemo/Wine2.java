package cn.com.sheep.workspace.Abstractdemo;

import lombok.Data;

@Data
public class Wine2 {

    private String name;


    public String drink() {
        return "喝的是：" + getName();
    }


}


