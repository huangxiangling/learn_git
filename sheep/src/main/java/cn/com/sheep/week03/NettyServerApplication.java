package cn.com.sheep.week03;

import cn.com.sheep.nio2.inbound.HttpInboundServer;
import cn.com.sheep.week03.inbound.NettyInboundHttpServer;

import java.util.Arrays;

/**
 * netty网关启动类
 */
public class NettyServerApplication {

    public static void main(String[] args) {

        String proxyPort = System.getProperty("proxyPort", "8888");
        // 这是多个后端url走随机路由的例子
        String proxyServers = System.getProperty("proxyServers", "http://localhost:8801,http://localhost:8802");
        int port = Integer.parseInt(proxyPort);
        NettyInboundHttpServer server = new NettyInboundHttpServer(port, Arrays.asList(proxyPort.split(",")));
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
