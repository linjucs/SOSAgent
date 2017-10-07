package edu.clemson.openflow.sos.socks.netty;

import edu.clemson.openflow.sos.rest.RequestParser;
import edu.clemson.openflow.sos.socks.ISocketServer;
import edu.clemson.openflow.sos.socks.SocketManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;


/**
 * @author Khayam Anjam kanjam@g.clemson.edu
 * this class will start a new thread for every incoming connection from clients
 */
public class NettyClientSocket implements ISocketServer {
    protected static boolean isClientHandlerRunning = false;
    private static final Logger log = LoggerFactory.getLogger(SocketManager.class);
    private RequestParser request;
    private static final int CLIENT_DATA_PORT = 9877;

    public NettyClientSocket (RequestParser request) {
        this.request = request;
    }

    public boolean startSocket(int port) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(
                                    new ClientSocketHandler(request));
                        }
                    });

            ChannelFuture f = b.bind().sync();
            log.info("Started client-side socket server at Port {}",CLIENT_DATA_PORT);
            return true;
            // Need to do socket closing handling. close all the remaining open sockets
            //System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
            //f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("Error starting socket client-side socket");
            e.printStackTrace();
            return false;
        } finally {
            //group.shutdownGracefully().sync();
        }
    }

    @Override
    public boolean start() {
        if (!isClientHandlerRunning) {
            isClientHandlerRunning = true;
            return startSocket(CLIENT_DATA_PORT);
        }
        return true;
    }
}
