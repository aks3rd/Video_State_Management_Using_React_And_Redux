var ontime=0;
var vid1;
var state={};
var store=Redux.createStore(reducer,state);
function reducer(state,action)
{
if(action.type=='initiale')
{

    fetch("service/Video/load",
    {
      method: "POST",
      mode: "cors",
      cache: "no-cache",
      credentials:"same-origin",
      headers:{
       "Content-Type":"application/json"
      },
      redirect:"follow",
      referrerPolicy:"no-referrer"
    }).then(response =>response.json()).then(function(data){
         if(data.success)
         {
           //console.log(data," and ",data.response.videoList.listOfVideos);
           let list=data.response.videoList.listOfVideos;

           list.forEach(function(video,index){
             //console.log(video);
             if(video.src==store.getState().video.currentSrc)
             {
               store.getState().video.currentTime=video.currentTime;
               if(video.playMode)
               {
                 store.getState().playEvent="Play";
                 store.getState().playingEvent="Playing";
                 store.getState().pauseEvent="Pause";
                 store.getState().video.play();
               }
               else
               {
                 store.getState().playEvent="Pause";
                 store.getState().playingEvent="Pause";
                 store.getState().pauseEvent="Pause";
                 store.getState().video.pause();
               }
             }
           })

           setInterval(function(){
             var mode=false;
             if(store.getState().playEvent=="Play")
             {
               mode=true;
             }
             var data={src:store.getState().video.currentSrc,playMode:mode,currentTime:parseInt(store.getState().video.currentTime),visitedURL:window.location.href};
             //console.log(data);
             fetch("service/Video/update",
             {
               method: "POST",
               mode: "cors",
               cache: "no-cache",
               credentials:"same-origin",
               headers:{
                 "Content-Type":"application/json"
               },
               redirect:"follow",
               referrerPolicy:"no-referrer",
               body: JSON.stringify(data)
             }).then(response =>response.json()).then(function(data){
                  //console.log(data);
                }).catch(function(error){
                     console.log("Error:",error);
                   });
           },1000);



         }
       }).catch(function(error){
       console.error("Error:", error);
    });
    
    //return Object.assign({},state,store.getState());
    return Object.assign({},state,{video:vid1,playEvent:"Initaile",pauseEvent:"Initaile",currentTime:0,playingEvent:"Initaile",endedEvent:"Initaile"});
}
if(action.type=='onPlayEvent')
{
return Object.assign({},state,{playEvent:"Play",pauseEvent:"Play"});
}
if(action.type=='onPauseEvent')
{
return Object.assign({},state,{playEvent:"Pause",pauseEvent:"Pause",playingEvent:"Pause"});
}
if(action.type=='onPlayingEvent')
{
return Object.assign({},state,{playingEvent:"Playing"});
}
if(action.type=='onTimeUpdate')
{
return Object.assign({},state,{currentTime:state.video.currentTime});
}
if(action.type=='onEndedEvent')
{
return Object.assign({},state,{endedEvent:"Ended"});
}
return state;
}
function updateOnPlayEvent()
{
document.getElementById("videoPlayEvent").innerHTML=store.getState().playEvent;
}
function updateOnPauseEvent()
{
document.getElementById("videoPlayEvent").innerHTML=store.getState().pauseEvent;
document.getElementById("videoPlayingEvent").innerHTML=store.getState().playingEvent;
}
function updateOnPlayingEvent()
{
document.getElementById("videoPlayingEvent").innerHTML=store.getState().playingEvent;
}
function updateOnTimeUpdateEvent()
{
var videoVisitingPer=((store.getState().video.currentTime/store.getState().video.duration)*100).toFixed(2);
document.getElementById("videoTimeUpdateEvent").innerHTML=sToTime(store.getState().video.currentTime)+"/"+sToTime(store.getState().video.duration)+"   ( "+videoVisitingPer+" %)";


}
function sToTime(t) {
  if(Number.isNaN(t))return "00:00:00";
  return padZero(parseInt((t / (60 * 60)) % 24)) + ":" +
         padZero(parseInt((t / (60)) % 60)) + ":" + 
         padZero(parseInt((t) % 60));
}
function padZero(v) {
  return (v < 10) ? "0" + v : v;
}



function updateOnEndedEvent()
{
if(store.getState().endedEvent=="Ended")
{
alert("onEndedEvent Raise");
store.getState().endedEvent="Initiale";
}
}
function updateSRC()
{
document.getElementById("videoSRC").innerHTML=vid1.currentSrc;

}
function onPlayEvent()
{
store.dispatch({type:"onPlayEvent"});
}
function onPauseEvent()
{
store.dispatch({type:"onPauseEvent"});
}
function onPlayingEvent()
{
store.dispatch({type:"onPlayingEvent"});
}
function onTimeUpdateEvent()
{
store.dispatch({type:"onTimeUpdateEvent"});
}
function onEndedEvent()
{
store.dispatch({type:"onEndedEvent"});
}

window.addEventListener('load',function(){
vid1=document.getElementById("video1");
store.subscribe(updateOnPlayEvent);
store.subscribe(updateOnPauseEvent);
store.subscribe(updateOnPlayingEvent);
store.subscribe(updateOnTimeUpdateEvent);
store.subscribe(updateOnEndedEvent);
store.subscribe(updateSRC);
vid1.onplay=onPlayEvent;
vid1.onpause=onPauseEvent;
vid1.onplaying=onPlayingEvent;
vid1.ontimeupdate=onTimeUpdateEvent;
vid1.onended=onEndedEvent;
state.video=vid1;
store.dispatch({type:'initiale'});
/*
  
*/

});

