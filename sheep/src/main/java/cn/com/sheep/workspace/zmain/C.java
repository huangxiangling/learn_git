package cn.com.sheep.workspace.zmain;

import lombok.Data;

@Data
public class C extends B {

    private int avg;
    private String str;

    public void add(){
        this.avg = 120;
    }
}
