package edu.clemson.openflow.sos.agent.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgentClient {
    private static final Logger log = LoggerFactory.getLogger(AgentClient.class);

    private static final int AGENT_DATA_PORT = 9878;
    private String agentServerIP;
    private Channel channel;

    public AgentClient (String agentServerIP) {
        this.agentServerIP = agentServerIP;
    }

    public Channel getChannel() {
        return channel;
    }

    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap().group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new AgentClientChannelInitializer());
            Channel channel = bootstrap.connect(agentServerIP, AGENT_DATA_PORT).sync().channel();
            this.channel = channel;
            log.info("Connected to Agent-Server {} on Port {}", agentServerIP, AGENT_DATA_PORT);


        } catch (Exception e) {
            log.error("Error connecting to Agent-Server {} on Port{}", agentServerIP, AGENT_DATA_PORT);
            e.printStackTrace();
        } finally {
            //group.shutdownGracefully();
        }
    }
}
