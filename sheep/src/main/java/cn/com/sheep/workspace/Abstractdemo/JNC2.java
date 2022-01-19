package cn.com.sheep.workspace.Abstractdemo;

public class JNC2 extends Wine2 {

    public JNC2() {
        setName("JNC");
    }


    /**
     * 重写父类方法，实现多态
     *
     * @return
     */
    @Override
    public String drink() {
        return "喝的是：" + getName();
    }
}
