
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
    
 var num = 1;   

function colapsar(){
  var div = document.getElementsByClassName("chatdiv")[0];
   
   if((num%2) === 0){
       div.setAttribute("style","top:583px");
   }else{
       div.setAttribute("style","top:55px");
   }
   num++;  
}



function css(value){ 
  
     
   if(value.style.color === 'blue'){
       value.style.color = 'gray';
       value.style.border = 'none';
   }else{
       value.style.color = 'blue';
       value.style.border = '1px solid blue';
   }
   
   
}

function colorear(value){
    
    
    if(value.style.color === 'blue')
    {
      value.setAttribute("style","color:#008B8B;font-size:14px;font-family:monospace");
       value.innerHTML= "Like";
    }else{
        value.setAttribute("style","color:blue;font-size:16px;font-family:Arial");
         value.innerHTML= "Liked";
    }
    
  
 
}

function refrescar(){
    document.getElementsByClassName("btnRefresh")[0].click();
}

function refresh(){
    
    document.getElementById("frm:j_idt51").click();
    
}


function showEdit(button){
 
  var divs = document.getElementsByName("editDialog");
  
  console.log(divs);
  
}