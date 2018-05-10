
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