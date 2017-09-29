#!/usr/bin/env bash
#author Khayam Anjam kanjam@g.clemson.edu
#This script will start the mininet topology and the sos agents
#pass the agent names as arguments



tmux new-session -d -s sos -n agent1 'sudo docker exec -it mn.agent1 java -jar  /sos-agent/target/sosagent.jar'

tmux split-window -d -t sos:agent1 'sudo docker exec -it mn.agent1 tshark -i agent1-eth1'

tmux new-window -d -a -t sos:agent1 -n agent2 'sudo docker exec -it mn.agent2 java -jar  /sos-agent/target/sosagent.jar'

tmux new-window -d -a -t sos:agent1 -n server1 'sudo docker exec -it mn.server1 iperf3 -s'

tmux new-window -d -a -t sos:agent1 -n client1 'sudo docker exec -it mn.client1 iperf3 -c 10.0.0.211'

tmux attach -t sos
