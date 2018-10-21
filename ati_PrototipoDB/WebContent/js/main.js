"use strict";

function showReservated(){
	
	
	
		document.getElementById("tabla0").style.display="block";
		document.getElementById("tabla1").style.display="none";
		document.getElementById("tabla2").style.display="none";
		document.getElementById("tabla3").style.display="none";
  
    
    
	
}

function showReservated2(){
	
	
	
	document.getElementById("tabla1").style.display="block";
	document.getElementById("tabla0").style.display="none";
	document.getElementById("tabla2").style.display="none";
	document.getElementById("tabla3").style.display="none";




}

function showReservated3(){
	
	
	
	document.getElementById("tabla2").style.display="block";
	document.getElementById("tabla0").style.display="none";
	document.getElementById("tabla1").style.display="none";
	document.getElementById("tabla3").style.display="none";




}

function showReservated4(){
	
	
	
	document.getElementById("tabla3").style.display="block";
	document.getElementById("tabla0").style.display="none";
	document.getElementById("tabla2").style.display="none";
	document.getElementById("tabla1").style.display="none";




}

function compare()
{
    let startDt = document.getElementById("startDate").value;
    let endDt = document.getElementById("endDate").value;
    
    if(startDt > endDt)
    {
        alert("La hora inicial debe indicar un momento antes que la final");
        document.getElementById("endDate").value="";
    }
}