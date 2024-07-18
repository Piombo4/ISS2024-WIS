#!/bin/bash

if [[ ! $1 == "DOWN" ]]; then
	(sleep 5 && CONTAINERID=$(sudo docker ps | grep "virtualrobotdisi23" | cut -f1 -d' ') && echo $CONTAINERID && sudo docker cp sceneConfig.js $CONTAINERID:/home/node/WEnv/WebGLScene/sceneConfig.js && sudo docker commit $CONTAINERID &)
	(sleep 5 && CONTAINERID=$(sudo docker ps | grep "basicrobot24" | cut -f1 -d' ') && sudo docker cp basicrobotConfig.json $CONTAINERID:/basicrobot24-1.0/bin/basicrobotConfig.json &)
	sudo docker-compose -f basicrobot24.yaml up
else
	sudo docker-compose -f basicrobot24.yaml down
fi
