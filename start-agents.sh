#!/usr/bin/env bash
#author Khayam Anjam kanjam@g.clemson.edu
#This script will start the mininet topology and the sos agents
#pass the agent names as arguments



#screen -AdmS sos-agent -L -t mininet sudo python mininet-scripts/docker-mininet-topo.py
#sleep 10 #Giving some time to mininet to bootup
echo Make sure that mininet is up and running otherwise agents wont start up.

screen -S sos-agent -L -t agent1 sudo docker exec -it mn.agent1 java -jar  /sos-agent/target/sosagent.jar
screen -S sos-agent -L -X screen -t agent2 sudo docker exec -it mn.agent2 java -jar  /sos-agent/target/sosagent.jar


#screen -S sos-agent -t 0 -A -d -m
#screen -S sos-agent -X screen -t 1

#screen -S sos-agent -p 0 -X stuff $"sudo python /home/ubuntu/sos-agent/mininet-scripts/docker-mininet-topo.py\n" -Adm
#screen -S sos-agent -p 1 -X stuff $'sudo docker exec -it mn.agent1 java -jar  /sos-agent/target/sosagent.jar' -Adm

#screen -R sos-agent -p 0
#screen -R sos-agent -p 1


#screen -S sos-agent
#screen -S sos-agent -t mininet  "sudo python /home/ubuntu/sos-agent/mininet-scripts/docker-mininet-topo.py"
#screen -S sos-agent -X screen -t $1 "sudo docker exec -it mn.$1 java -jar  /sos-agent/target/sosagent.jar"

#screen -S sos-agent -p mininet -X "sudo python mininet-scripts/docker-mininet-topo.py"
#screen -S sos-agent -p $1 -X "sudo docker exec -it mn.$1 java -jar  /sos-agent/target/sosagent.jar"






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