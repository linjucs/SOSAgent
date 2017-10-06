#!/usr/bin/env bash
#author Khayam Anjam kanjam@g.clemson.edu
#This script will start the agents and tshark on agents

if [ "$(id -u)" != "0" ]; then
   echo "This script must be run as root" 1>&2
   exit 1
fi

tmux set-option remain-on-exit on

tmux new-session -d -s sos -n agent1 'sudo docker exec -it mn.agent1 java -jar  /sos-agent/target/sosagent.jar'
tmux split-window -d -t sos:agent1  'sudo docker exec -it mn.agent1 tshark -i agent1-eth1'


#tmux new-window -d -a -t sos:agent1 -n agent2 'sudo docker exec -it mn.agent2 java -jar  /sos-agent/target/sosagent.jar'
#tmux split-window -d -t sos:agent2  'sudo docker exec -it mn.agent2 tshark -i agent2-eth1'

tmux new-window -d -a -t sos:agent1 -n server1 'sudo docker exec -it mn.server1 python -m SimpleHTTPServer 5201'
tmux split-window -d -t sos:server1  'sudo docker exec -it mn.server1 tshark -i server1-eth1'


tmux new-window -d -a -t sos:agent1 -n client1 'sudo docker exec -it mn.client1 bash'
tmux split-window -d -t sos:client1 'sudo docker exec -it mn.client1 tshark -i client1-eth1'


tmux attach -t sos
