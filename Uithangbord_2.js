/**
 *   Ajax request uitvoeren naar Servlet uithangbord_2P en als parameters de 4 velden meegeven  
 */

function clickHandler1() { 

  var veld1 = document.getElementById('naamBedrijf').value;
  var veld2 = document.getElementById('contactPersoon').value;
  var veld3 = document.getElementById('email').value;
  var veld4 = document.getElementById('aanvraag').value;
  
  var xhr = new XMLHttpRequest();
  
  xhr.addEventListener("readystatechange", stateChange, false);
  
  $.post("uithangbord_2",{"naamBedrijf":veld1,
	                      "contactPersoon":veld2,
	                      "email":veld3,
	                      "aanvraag":veld4,},function(data){},"xml");
    
  function stateChange() {
    if ( xhr.readyState == 4 && xhr.status == 200 ) {
      document.getElementById("result").innerHTML = xhr.responseText;
    } 
  } 
}	

/**
 *   Ajax request uitvoeren naar Servlet uithangbord_2G om de (vorige) data op te halen  
 */

function clickHandler2() { 
  
  $.ajax({   
    url:'uithangbord_2',   
    type:'get',   
    dataType: 'json',   
    success: function(data) {
	  console.log(data);
	  var obj = new Object(data);
	  var v1 = obj.naamBedrijf;
	  var v2 = obj.contactPersoon;
	  var v3 = obj.email;
	  var v4 = obj.aanvraag;
	  document.getElementById("naamBedrijf").value = v1;
	  document.getElementById("contactPersoon").value = v2;
	  document.getElementById("email").value = v3;
	  document.getElementById("aanvraag").value = v4;
    }   
  });   
}	
