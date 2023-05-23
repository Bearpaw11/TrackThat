<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Signup</title>
  <link rel="icon" href="../Images/favicon.ico" type="image/x-icon"> 
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" type="image/x-icon">
  <link rel="preconnect" href="https://fonts.gstatic.com" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
  <link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/signup.css">
</head>

<body>
  <!-- Bootstrap Navbar -->
  <nav class="navbar navbar-expand-sm navClass">
    <div class="container-fluid">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon "></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/about">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#">Signup</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/signin">Sign In</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="pageBackground">
  	 <!-- error message if user exists -->
  	<div class="error">${UserError}</div>
    <img class="pageImg"src="${pageContext.request.contextPath}/resources/images/TrackThat.png" alt="logg" width="300" height="300">
    <div class="formCard">
      <h1 class="signUpHeader">Signup</h1>
      <!-- Form to signup a new user -->
      <form:form action="saveUser" modelAttribute="user" method="Post" id="signup">
      <form:hidden path="id"/>
        <div class="mb-3">
          <label for="email" class="form-label">Email address</label>
          <form:input path="email" id="email" type="text" class="form-control" onchange="checkEmail()" />
        </div>
        <div class="mb-3">
          <label for="userName" class="form-label">User Name</label>
          <form:input path="userName"  type="text" class="form-control" id="UserName"/>
        </div>
        <div class="mb-3">
          <label for="pasword" class="form-label" >Password</label>
          <h6 class="PWTest">Must be at least 5 characters long</h6>
          <input type="password" class="form-control" id="password" placeholder=""/>
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Confirm Password </label>
          <form:input path="password" type="password" class="form-control" id="confirm"  onchange="checkPass()"/>
        </div>
        <input type="submit" value="Signup" class="btn btn-danger" id="submit" disabled/>
      </form:form>
    </div>
  </div>
 

   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
    crossorigin="anonymous"></script>
   <!--  Link to custom JS Script for email and password validation -->
  <script src="${pageContext.request.contextPath}/resources/scripts/signup.js"></script>
</body>

</html>