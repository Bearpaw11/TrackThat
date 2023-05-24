<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>About</title>
  <link rel="icon" href="${pageContext.request.contextPath}/resources/images/TrackThat.png" type="image/x-icon"> 
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/TrackThat.png" type="image/x-icon"> 
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
     		 <li class="steps">1. Use our easy sign up to create a user account</li>
       		 	<img class="pageImg" src="${pageContext.request.contextPath}/resources/images/signup.png" alt="Signup Page" >
      		<li class="steps">2. Log in with your new account</li>
        		<img class="pageImg" src="${pageContext.request.contextPath}/resources/images/signin.png" alt="Signup Page" >
      		<li class="steps">3. You are redirected to your main user page</li>
        		<img class="pageImg" src="${pageContext.request.contextPath}/resources/images/home1.png" alt="Signup Page" >
      		<li class="steps">4. Click "Add record to collection" and get redirected to "Add Record to collection page"
      			<br> Enter information about a record in your collection and click "Add to Collection"</li>
      			<img class="pageImg" src="${pageContext.request.contextPath}/resources/images/addToCollection.png" alt="Add to collection">
      		<li class="steps">5. You are redirected to your home page and you will see your new record added to your collection
      			<br> 
      			Your records will automatically be sorted by artist</li> 
      			<img class="pageImg" src="${pageContext.request.contextPath}/resources/images/home.png" alt="Home Page">
      		<li class="steps">6. Use the "Add Record to Wish List" button to create a wish list of records you would like to acquire</li>  
     	   <li class="steps">7. Use the Update and delete buttons as needed</li>
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