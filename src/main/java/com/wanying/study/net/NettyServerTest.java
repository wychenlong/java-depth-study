package com.wanying.study.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <b>项目名称</b>： asset-app-server-product-server <br>
 * <b>包名称</b>： com.wangyin.asset.app.product <br>
 * <b>类名称</b>： NettyServerTest.java <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>：wychenlong <br>
 * <b>创建时间</b>：2016年10月19日 下午5:37:24 <br>
 * <b>修改人</b>： <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class NettyServerTest {

    private static final String IP = "127.0.0.1";
    private static final int PORT = 5656;
    private static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;

    private static final int BIZTHREADSIZE = 100;
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);

    public static void service() throws Exception {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel ch) throws Exception {
                // TODO Auto-generated method stub
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast(new LengthFieldPrepender(4));
                pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast(new TcpServerHandler());
            }

        });
        ChannelFuture f = bootstrap.bind(IP, PORT).sync();
        f.channel().closeFuture().sync();
        System.out.println("TCP服务器已启动");
    }

    protected static void shutdown() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("开始启动TCP服务器...");
        NettyServerTest.service();
//      NettyServerTest.shutdown();
    }
}
