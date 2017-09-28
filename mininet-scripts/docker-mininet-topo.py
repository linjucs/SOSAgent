#!/usr/bin/python

"""
Simple test topology for SoS. One agent on each side along with one client and server
@author Khayam Anjam    kanjam@g.clemson.edu
"""

from mininet.net import Containernet
from mininet.node import Controller, OVSSwitch, RemoteController
from mininet.cli import CLI
from mininet.log import setLogLevel, info
import os

def multiControllerNet():
    "Create a network from semi-scratch with multiple controllers."

    net = Containernet( controller=Controller, switch=OVSSwitch )

    info( "*** Creating (reference) controllers\n" )
    c = RemoteController( 'c', ip='172.17.0.1', port=6653 )

    info( "*** Creating switches\n" )
    s1 = net.addSwitch( 's1' )
    s2 = net.addSwitch( 's2' )

    info( "*** Creating hosts\n" )
    #agent1 = net.addHost('agent1', ip='10.0.0.11')
    #agent2 = net.addHost('agent2', ip='10.0.0.21')
    home_path = os.path.expanduser('~')
    agent1 = net.addDocker('agent1', ip='10.0.0.11',
                           dimage="khayamgondal/sosagent:v4",
                           volumes=[""+home_path+"/SOSAgent:/sos-agent"])
    agent2 = net.addDocker('agent2', ip='10.0.0.21',
                           dimage="khayamgondal/sosagent:v4",
                           volumes=[""+home_path+"/SOSAgent:/sos-agent"])
    client1 = net.addDocker('client1', ip='10.0.0.111', dimage="khayamgondal/sosagent:v4")
    server1 = net.addDocker('server1', ip='10.0.0.211', dimage="khayamgondal/sosagent:v4")

    #controller = net.addDocker('controller', ip='10.0.0.69', dimage="khayamgondal/sosagent:v4", volumes=["/home/ubuntu/sos-for-floodlight:/sos-for-floodlight"])
    #client1 = net.addHost('client1', ip='10.0.0.111')
    #server1 = net.addHost('server1', ip='10.0.0.211')

    info( "*** Creating links\n" )
    net.addLink( s1, client1, port1=1, port2=1)
    net.addLink(s1, agent1, port1=2, port2=1)

    net.addLink( s1, s2 , port1=3, port2=3)

    net.addLink(s2, server1, port1=1, port2=1)
    net.addLink(s2, agent2,  port1=2, port2=1)

    #net.addLink(controller, s1, port1=0, port2=5)
    #net.addLink(controller, s2, port1=1, port2=5)

    info( "*** Starting network\n" )
    net.build()
    c.start()
    s1.start( [ c ] )
    s2.start( [ c ] )

    #info( "*** Testing network\n" )
    #net.pingAll()

    #info( "*** Running CLI\n" )
    CLI( net )

    #info( "*** Stopping network\n" )
    net.stop()

if __name__ == '__main__':
    setLogLevel( 'info' )  # for CLI output
    multiControllerNet()