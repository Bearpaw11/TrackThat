// Function to check if the email is valid
checkEmail = () => {
	//first we set the email to a variable
	let email = document.getElementById('email');
	//set our regex to filter
	let filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	//test the value of email
	if (filter.test(email.value)) {
		//if we pass the email check then we can call the check pass function
		checkPass = () => {
			//first check to see if password and confirm are the smae
			if (document.getElementById('password').value == document.getElementById('confirm').value) {
				//check to see if passord is more that 5 characters 
				if (document.getElementById('password').value.length >= 5) {
					//if both are true then disabled is set to false on the Signup botton
					document.getElementById('submit').disabled = false;
				} else {
					//if password is not at least 5 characters then alert and keep disabled true
					document.getElementById('submit').disabled = true;
					alert("passwords must be at least 5 charachters long")
				}
			} else {
				//if password and confirm are not equal alert and keep disabled true
				document.getElementById('submit').disabled = true;
				alert("passwords do not match")
			}
		}
	} else {
		alert("Please enter a vaild email")
	}
}

