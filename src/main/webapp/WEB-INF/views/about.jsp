<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>About</title>
  <link rel="icon" href="${pageContext.request.contextPath}/resources/images/TrackThat2.png" type="image/x-icon"> 
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/TrackThat2.png" type="image/x-icon"> 
  <link rel="preconnect" href="https://fonts.gstatic.com" />
  <link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/about.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>

<body>
  <!-- Bootstrap navbar -->
  <nav class="navbar navbar-expand-sm navClass">
    <div class="container-fluid">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon "></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/signup">Signup</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/signin">Sign In</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <header>
    <h1 class="aboutTitle fntSize">About "Track That Track"</h1>
    <h2 class="aboutHeader fntSize">Does your record collection look like this?</h2>
    <img class="messy" src="${pageContext.request.contextPath}/resources/images/messyRecords.jpeg" alt="Messy Records" width="400" height="300">
    <h2 class="aboutHeader fntSize">Use Track That Track to make your collection virtually look like this!</h2>
    <img class="organized" src="${pageContext.request.contextPath}/resources/images/organized.jpeg" alt="Orginazied Records" width="400" height="300">
    <h3 class="howHeader fntSize">Here's how it works</h3>
    	<!-- List of how the site works and images -->
    	<ul>
     		 <li class="steps"> Use our easy sign up to create a user account</li>
       		 	<img class="pageImg" src="${pageContext.request.contextPath}/resources/images/Signup.png" alt="Signup Page" >
      		<li class="steps">Sign in with your new account</li>
        		<img class="pageImg" src="${pageContext.request.contextPath}/resources/images/Signin.png" alt="Signup Page" >
      		<li class="steps">You are redirected to your main user page</li>
        		<img class="pageImg" src="${pageContext.request.contextPath}/resources/images/UserHome.png" alt="Signup Page" >
          <li class="steps">Use the search bar to search for an album or an artist</li>
        		<img class="pageImg" src="${pageContext.request.contextPath}/resources/images/Search.png" alt="Signup Page" > 
          <li class="steps">Once You find the record you want to add, use the "Add to Collection" or "Add to Wish List" buttons</li>
            <img class="pageImg" src="${pageContext.request.contextPath}/resources/images/SearchAdd.png" alt="Signup Page" >
          <li class="steps">You will then see the record added to "Current Collection" or "My Wish List"</li>
            <img class="pageImg" src="${pageContext.request.contextPath}/resources/images/AddToCollection.jpeg" alt="Signup Page" >
      		<li class="steps">If your search doesn't have the record you want, no problem! Use the "Add record to collection" or "Add record to wish list" button to add manually.</li>
            <img class="pageImg" src="${pageContext.request.contextPath}/resources/images/ManualAdd.png" alt="Signup Page" >
      		<li class="steps">Enter the Artist, Album Title and URL of the Album Cover and click Save</li>
      			<img class="pageImg" src="${pageContext.request.contextPath}/resources/images/ManualAdd2.png" alt="Add to collection">
      		<li class="steps">You are redirected to your home page and you will see your new record added to your collection.
      			<br> 
      			Your records will automatically be sorted alphabetically by artist</li> 
      			<img class="pageImg" src="${pageContext.request.contextPath}/resources/images/ManualAdd3.png" alt="Home Page">
      		<li class="steps">Use the "Add record to wish list" button to create a wish list of records you would like to acquire</li>  
     	   <li class="steps">Use the Update and Delete buttons as needed</li>
      		<br>
      		<br>
      		<br>
      		<br>
     		<br>
     
    </ul>
  </header>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>

</html>