#!/usr/bin/python

"""
Simple test topology for SoS. One agent on each side along with one client and server
@author Khayam Anjam
@email kanjam@g.clemson.edu
"""

from mininet.net import Mininet
from mininet.node import Controller, OVSSwitch, RemoteController
from mininet.cli import CLI
from mininet.log import setLogLevel, info

def multiControllerNet():
    "Create a network from semi-scratch with multiple controllers."

    net = Mininet( controller=Controller, switch=OVSSwitch )

    info( "*** Creating (reference) controllers\n" )
    c = RemoteController( 'c', ip='192.168.56.1', port=6653 )

    info( "*** Creating switches\n" )
    s1 = net.addSwitch( 's1' )
    s2 = net.addSwitch( 's2' )

    info( "*** Creating hosts\n" )
    agent1 = net.addHost('agent1', ip='10.0.0.11')
    agent2 = net.addHost('agent2', ip='10.0.0.21')

    client1 = net.addHost('client1', ip='10.0.0.111')
    server1 = net.addHost('server1', ip='10.0.0.211')

    info( "*** Creating links\n" )
    net.addLink( s1, client1)
    net.addLink(s1, agent1)

    net.addLink( s1, s2 )

    net.addLink(s2,server1)
    net.addLink(s2,agent2)

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