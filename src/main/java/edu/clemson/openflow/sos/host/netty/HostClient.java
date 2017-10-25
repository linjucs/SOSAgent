package edu.clemson.openflow.sos.host.netty;

import edu.clemson.openflow.sos.agent.netty.AgentClient;
import edu.clemson.openflow.sos.agent.netty.AgentClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HostClient {
    private static final Logger log = LoggerFactory.getLogger(AgentClient.class);

    private int hostServerPort;
    private String hostServerIP;
    private Channel remoteChannel;
    private Channel myChannel;


    public HostClient (String hostServerIP, int hostServerPort, Channel remoteChannel) {
        this.hostServerIP = hostServerIP;
        this.hostServerPort = hostServerPort;
        this.remoteChannel = remoteChannel;
    }

    public Channel getChannel() {
        return myChannel;
    }

    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap().group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new HostClientChannelInitializer(remoteChannel));
            Channel channel = bootstrap.connect(hostServerIP, hostServerPort).sync().channel();
            this.myChannel = channel;
            log.info("Connected to Host-Server {} on Port {}", hostServerIP, hostServerPort);


        } catch (Exception e) {
            log.error("Error connecting to Host-Server {} on Port{}", hostServerIP, hostServerPort);
            e.printStackTrace();
        } finally {
            //group.shutdownGracefully();
        }
    }
}

