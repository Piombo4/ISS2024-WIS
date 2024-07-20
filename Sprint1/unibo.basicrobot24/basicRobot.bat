
setlocal enabledelayedexpansion
docker-compose -f basicrobot24.yaml down
START /B docker-compose -f basicrobot24.yaml up
timeout /t 5
for /f "tokens=1 delims= " %%a in ('docker ps -a ^| findstr "virtualrobotdisi23:1.0"') do (
    set cid=%%a
)

docker cp sceneConfig.js %cid%:/home/node/WEnv/WebGLScene/sceneConfig.js
docker commit %cid%

for /f "tokens=1 delims= " %%a in ('docker ps -a ^| findstr "basicrobot24"') do (
    set cid=%%a
)

docker cp basicrobotConfig.json  %cid%:/basicrobot24-1.0/bin/basicrobotConfig.json
docker commit %cid%
