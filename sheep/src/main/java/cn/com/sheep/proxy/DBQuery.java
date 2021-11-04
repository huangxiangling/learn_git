package cn.com.sheep.proxy;

public class DBQuery implements IDBQuery{

    @Override
    public String request() {
        return "request string";
    }

    @Override
    public String req() {
        return "req string";
    }
}
