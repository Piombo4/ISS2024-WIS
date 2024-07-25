/*
wsminimal.js
*/

    var socket;
    var divRP;
    var divAsh;
    var divIncinerator;
    var divPosition;
    var divJob
    window.onload = ()=>{
        divRP = document.querySelector("#rp");
        divAsh = document.querySelector("#ash");
        divIncinerator = document.querySelector("#incinerator");
        divPosition = document.querySelector("#position");
        divJob = document.querySelector("#job");
    }

    function sendMessage(message) {
        var jsonMsg = JSON.stringify( {'name': message});
        socket.send(jsonMsg);
        console.log("Sent Message: " + jsonMsg);
    }

    function connect(){
        var host     =  document.location.host;
        var pathname =  "/"                   //document.location.pathname;
        var addr     = "ws://" +host  + pathname + "accessgui"  ;
        //alert("connect addr=" + addr   );

        // Assicura che sia aperta un unica connessione
        if(socket !== undefined && socket.readyState !== WebSocket.CLOSED){
             alert("WARNING: Connessione WebSocket già stabilita");
        }
        socket = new WebSocket(addr);

        socket.onopen = function (event) {
            console.log("Connected to " + addr);
        };

        socket.onmessage = function (event) {
            //alert(`Got Message: ${event.data}`);
            msg = event.data;
            //alert(`Got Message: ${msg}`);
            console.log("ws-status:" + msg);
            updateValues(msg);
        };
    }//connect
    function updateValues(msg){
        //guidata(1,25,true,3,1,burnin,moving_to_home)
        msg = msg.replace("guidata(","")
        msg = msg.replace(")","")
        const values = msg.split(",");
        divRP.innerHTML =  values[0]
        divAsh.innerHTML =  values[1];
        if(values[2] == "true"){
            divIncinerator.innerHTML = "🔥🔥🔥🔥🔥🔥🔥";
        }else{
             divIncinerator.innerHTML = "💀💀💀💀💀💀💀";
        }
        divPosition.innerHTML = values[3] + ", " + values[4] + " - " + values[5];
        divJob.innerHTML = "JOB: " + values[6].replaceAll("_"," ");

    }

