
setlocal enabledelayedexpansion
docker-compose -f basicrobot24.yaml down
docker-compose -f basicrobot24.yaml up
for /f "tokens=1 delims= " %%a in ('docker ps -a ^| findstr "virtualrobotdisi23:1.0"') do (
    set cid=%%a
)
docker cp sceneConfig.js %cid%:/home/node/WEnv/WebGLScene/sceneConfig.js
docker commit %cid%

