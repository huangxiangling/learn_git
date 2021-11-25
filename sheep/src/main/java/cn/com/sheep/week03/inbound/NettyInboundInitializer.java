package cn.com.sheep.week03.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ServerChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

public class NettyInboundInitializer extends ChannelInitializer<ServerChannel> {

    private List<String> urls;

    public NettyInboundInitializer(List<String> urls) {
        this.urls = urls;
    }

    @Override
    protected void initChannel(ServerChannel serverChannel) throws Exception {
        ChannelPipeline pipeline = serverChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        pipeline.addLast(new NettyInboundHandler(urls));
    }
}
