<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
		<%@ include file = "Style.css"%>
</style>
<%request.getSession().getAttribute("listaTreni");
 request.getSession().getAttribute("username");
 %>
</head>
<body onload="start()" >

<div class="container-login">
<div class="main">  	
		<input type="checkbox" id="chk" aria-hidden="true">
			<div class="signup" id="reg">
				<form method="POST" action="./register">
					<label for="chk" aria-hidden="true">Sign up</label>
					<input type="text" name="username" placeholder="Username" required="">
					
					<input type="password" name="password" placeholder="Password" required="">
					<button>Sign up</button>
				</form>
				 <template class="noRegistrazione" >
				 <label id="erroreLogin">${erroreRegister}</label>
				  </template>
				 <template class="Registrazione">
				 <label id="erroreLogin">${Registrato}</label>
				 </template>
			</div>
			<div class="login" id="login">
				<form method="POST" action="./login">
					<label for="chk" aria-hidden="true">Login</label>
					<input type="text" name="username" placeholder="username" required="">
					<input type="password" name="password" placeholder="Password" required="">
					<button>Login</button>
				
				</form>
				<template class="noLogin" >
				 <label id="erroreLogin">${erroreLogin}</label>
				  </template>
			</div>
	</div>
</div>

<input type="hidden" id="flag" value="${flag}" hidden="true">
<input type="hidden" id="flag1" value="${flagLogin}" hidden="true">

<script type="text/javascript">
function showContent() {
	 var temp = document.getElementsByClassName("noRegistrazione")[0];
	  var temp1 = document.getElementsByClassName("Registrazione")[0];
	  var clon = temp.content.cloneNode(true);
	  var clon1 = temp1.content.cloneNode(true);
	  var flag= document.getElementById("flag").value;
	  if (flag==2){
		  document.getElementById("reg").append(clon);
	} if(flag==1){document.getElementById("reg").appendChild(clon1);}
}
function showContent1() {
	var temp = document.getElementsByClassName("noLogin")[0];
	var clon = temp.content.cloneNode(true);
	var flag= document.getElementById("flag1").value;
	if (flag==1){
		document.getElementById("reg").appendChild(clon);
	}
}
function start(){
	showContent();
	showContent1();
}
</script>
</body>
</html>