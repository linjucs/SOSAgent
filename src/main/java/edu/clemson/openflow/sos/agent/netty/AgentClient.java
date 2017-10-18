package edu.clemson.openflow.sos.agent.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class AgentClient {
    private static final int AGENT_DATA_PORT = 9878;
    private String agentServerIP;

    public AgentClient(String agentServerIP) {
        this.agentServerIP = agentServerIP;
    }

    public void startSender() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap().group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientAdapterInitializer());

            Channel channel = bootstrap.connect(agentServerIP, AGENT_DATA_PORT).sync().channel();

            channel.write("Hi\n");
            channel.write("Hi\n");
            channel.write("Hi\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}
