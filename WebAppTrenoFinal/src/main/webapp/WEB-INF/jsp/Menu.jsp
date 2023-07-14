
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
<%@page import="java.util.Arrays"%>
<%@page import="dto.TrenoDTO"%>
<%@page import="dao.TrenoDao"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
<%@ include file = "Style.css"%>
</style>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body onload = myFunction1()>
<!--  navbar -->
<%request.getSession().getAttribute("username");
request.getSession().getAttribute("listaTreni");
%>
<div class="topnav" id="myTopnav">
  <a href="Menu" class="active"><i class="fa-solid fa-house" style="color: #ffffff;"></i></a>

  <template>
	  <a href="treni">Lista treni</a>
	  <a href="CreazioneTreno">Crea Treno</a>
  </template>
  <a href="about" id="about">About</a>
 <!-- <a id="user" href="Home"><i class="fa-solid fa-user" style="color: #ffffff;"></i> ${username} </a>-->
   <a href="javascript:void(0);" class="icon" onclick="myFunction()">
    <i class="fa fa-bars"></i>
  </a>
  

<div class="dropdown">
  <a class="dropbtn" href="#"> <i class="fa-solid fa-user" style="color: #ffffff;"></i> ${username}</a> 
  
  <div class="dropdown-content">
 	<a href="Home">Cambio Utente</a>
	<a href="logout">Logout</a>
  </div>
</div>
	

</div>  

<div class="homepage">
	<img src="./img/crea.png" width="600px" />	
</div>  
<div class="homepage">
	<h2>Scegli come modellare il tuo treno!</h2>
	<p>Decidi tu il modello, la tipologia e il numero dei vagoni!</p>
	<p>Sarà Frecciarossa o Trenord? Cargo o Passeggeri? Ha la carrozza ristorante? <a id="link_treno" href="Home">Fai il login</a> e <a id="link_treno" href="CreazioneTreno">Personalizza il tuo treno!</a></p> 
</div>

<a hidden = "true">
<input id="flag" name="prova" value="${flag}"></input>
</a>
<p class="homepage_title"><strong>Sei indeciso? Ecco gli ultimi 5 treni creati dai nostri utenti!</strong></p>
<div class="lista_treni">
<div>
		<%
		int j = 0;
		List<TrenoDTO> listaTreni = (List<TrenoDTO>) request.getSession().getAttribute("listaTreni");
		System.out.println(listaTreni);
		Collections.reverse(listaTreni);
	    String locomotiva = "";
	    String passeggeri = "";
	    String ristorante = "";
	    String cargo = "";
	    for (TrenoDTO t : listaTreni) {
			if (t.getTipo().equals("TN")) {
				locomotiva = "./img/locomotiva.png";
				passeggeri = "./img/passeggeri.png";
				ristorante = "./img/ristorante.png";
				cargo = "./img/cargo.png";	
			} else {
				locomotiva = "./img/locomotivaFR.png";
				passeggeri = "./img/passeggeriFR.png";
				ristorante = "./img/ristoranteFR.png";
				cargo = "./img/cargoFR.png";		
			}
			if (j == 5)
				break;
				for (int i = 0; i < t.getSigla().length(); i++) {
					switch (t.getSigla().toUpperCase().charAt(i)) {
					case 'H':
						%><p id='lt'> <%=t.getSigla()%> </p>
						<br>
						<img class='main-treno' src=<%=locomotiva%> width='150'>
						<%;
						break;
					case 'P':
						%><img class='main-treno' src=<%=passeggeri%> width='150'>
						<% break;
					case 'R':
						%><img class='main-treno' src=<%=ristorante%> width='150'>
						<% break;
					case 'C':
						%><img class='main-treno' src=<%=cargo%> width='150'>
						<% break;
					}


				}

				%><br><%
				j++;
			
		}%>
</div>
</div>
<script>
function myFunction() {
  var x = document.getElementById("myTopnav");
  if (x.className === "topnav") {
    x.className += " responsive";
  } else {
    x.className = "topnav";
  }
}
function myFunction1() {
	var flag = document.getElementById("flag").value;
	if (flag == 1) {
	var temp = document.getElementsByTagName("template")[0];
	  var clon = temp.content.cloneNode(true);
	  var about = document.getElementById("about");
	  clon.appendChild(about);
	  document.getElementById("myTopnav").appendChild(clon); 
	  
	}
	}
</script>

</body>
</html>


