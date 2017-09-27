#!/usr/bin/env bash
#author Khayam Anjam kanjam@g.clemson.edu
#This script will start the mininet topology and the sos agents
#pass the agent names as arguments

#screen -S mininet -t 0 -A -d -m
#screen -S mySessionName -p 0 -X stuff $'echo myWinName0\necho cmd1\necho cmd2\n'


#screen -S mininet sudo python mininet-scripts/docker-mininet-topo.py -A -d -m
#echo Started mininet topology

#screen -S $1 sudo docker exec -it mn.$1 java -jar  /sos-agent/target/sosagent.jar -A -d -m
#screen -S $2 sudo docker exec -it mn.$2 java -jar  /sos-agent/target/sosagent.jar -A -d -m


#for var in "$@"
#do
#screen -S $var sudo docker exec -it mn.$var java -jar  /sos-agent/target/sosagent.jar
#echo Started $var
#done