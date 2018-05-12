
function highlight(element){  
    element.setAttribute("style","background-color:green");
}

function unhighlight(element){
    element.setAttribute("style","background-color:transparent");
}


function shrinkBar(){
    
    document.getElementById("top").setAttribute("style","margin-left:22%;");
  
}

function shrinkback(){
    
    document.getElementById("top").setAttribute("style","margin-left:0%;");
}


function write(){
    console.log("hey");
}


function enable(){
    
    
    //document.getElementById("agregarbtn").setAttribute("disabled","false");
    
    var btn = document.getElementsByClassName("agregarbtn")[0];
    
    
    
}

var div;
var botones;

function processar(boton){
    
     
     
     div = document.getElementsByClassName("commentContainer");
     botones = document.getElementsByClassName("agregarbtn");
     
 /*    document.getElementById("myBtn").addEventListener("click", function(){
    document.getElementById("demo").innerHTML = "Hello World";
});*/

    console.log(botones[2]);
    
    
 }
    
    /*

    for(var b in botones){
        b.addEventListener("click",function(){
           console.log(b);
        });
    }
     
     */
     
     
     
     //console.log(div[5]);
     
     
   /* 
    var newDiv = document.createElement("div");
    newDiv.setAttribute("class","commentStyle");
    
    newDiv.appendChild(document.createTextNode("HOLA MUNDO JAVASCRIPT"));
    div.appendChild(newDiv);*/
    
    






