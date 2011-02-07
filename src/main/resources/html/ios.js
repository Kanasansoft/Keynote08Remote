var parameters;
var webSocket;
function getParameters(){
	var data={};
	var params=location.search.replace(/^\?/,"").split("&");
	for(var i=0;i<params.length;i++){
		var keyvalue=params[i].split("=");
		if(keyvalue.length!=2){continue;}
		var key=decodeURIComponent(keyvalue[0]);
		var value=decodeURIComponent(keyvalue[1]);
		if(!data.hasOwnProperty(key)){
			data[key]=[];
		}
		data[key].push(value);
	}
	return data;
}
function layout(){
	var elems=Array.prototype.slice.call(document.getElementsByTagName("div"));
	elems.forEach(function(elem){
		if(elem.id.match(/^button_/)){
			var img=document.createElement("img");
			img.src="svg/"+elem.id+".svg"+"?"+(new Date()).getTime();
			elem.appendChild(img);
		}
	});
}
function sendMessage(data){
	webSocket.send(data.join(","));
}
function displayNotes(notesString){
	var notes=document.getElementById("notes");
	if(notes.textContent!=notesString){
		notes.textContent=notesString;
	}
}
function displayGroup(id){
	["group_start_or_resume","group_slideshow","group_slide_switcher"].forEach(function(elem_id){
		document.getElementById(elem_id).setAttribute("data-status",elem_id==id?"display":"none");
	});
}
function updateOrientation(){
	["notes","group_start_or_resume","group_slideshow","group_slide_switcher"].forEach(function(elem_id){
		document.getElementById(elem_id).setAttribute("data-orientation",window.orientation.toString());
	});
}
function onOpenWebSocket(){
	var elems=Array.prototype.slice.call(document.getElementsByTagName("div"));
	elems.forEach(function(elem){
		var match=elem.id.match(/^button_(.*)$/);
		if(!match){return;}
		var type=match[1].replace(/_/g,"");
		elem.addEventListener("touchend",function(){
			sendMessage([type]);
		},false);
	});
	sendMessage(["status"]);
}
function onCloseWebSocket(){
}
function onMessageWebSocket(message){
	var data=message.data;
	var position=data.indexOf("_");
	var messageType;
	var messageData;
	if(position==-1){
		messageType=data;
		messageData="";
	}else{
		messageType=data.slice(0,position);
		messageData=data.slice(position+1);
	}
	switch(messageType){
	case "status":
		onMessageWebSocketStatus(messageData);
		break;
	}
}
function onMessageWebSocketStatus(data){
	var position=data.indexOf("_");
	var status;
	var argString;
	if(position==-1){
		status=data;
		argString="";
	}else{
		status=data.slice(0,position);
		argString=data.slice(position+1);
	}
	switch(status){
	case "stop":displayGroup("group_start_or_resume");break;
	case "playing":displayGroup("group_slideshow");break;
	case "slideswitcher":displayGroup("group_slide_switcher");break;
	}
	displayNotes(argString);
}
function onUnloadWindow(){
	webSocket.close();
}
function cancelHandler(eve){
	eve.preventDefault();
}
function initial(eve){
	["touchstart","touchmove","touchend","gesturestart","gesturechange","gestureend"].forEach(
			function(eventName){
				document.addEventListener(eventName,cancelHandler,false);
			}
	);
	layout();
	updateOrientation();
	parameters=getParameters();
	window.addEventListener("orientationchange",function(){
		updateOrientation();
		window.scrollTo(0,0);
		setTimeout(window.scrollTo,3000,0,0);
	},false);
	var protocol=(location.protocol=="https:")?"wss":"ws";
	var host=location.host;
	webSocket=new WebSocket(protocol+"://"+host+"/ws/");
	webSocket.addEventListener("open",onOpenWebSocket,false);
	webSocket.addEventListener("close",onCloseWebSocket,false);
	webSocket.addEventListener("message",onMessageWebSocket,false);
	window.addEventListener("unload",onUnloadWindow,false);
	window.scrollTo(0,0);
	setTimeout(window.scrollTo,3000,0,0);
}
window.addEventListener("load",initial,false);
