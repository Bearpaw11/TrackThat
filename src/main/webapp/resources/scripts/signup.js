
// Centralized validation function for enabling/disabling submit
function validateForm() {
	let email = document.getElementById('email').value;
	let userName = document.getElementById('UserName').value;
	let password = document.getElementById('password').value;
	let confirm = document.getElementById('confirm').value;
	let submitBtn = document.getElementById('submit');
	let emailFilter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

	let valid = true;

	if (email.length === 0 || !emailFilter.test(email)) valid = false;
	if (userName.length === 0) valid = false;
	if (password.length === 0 || password.length < 8) valid = false;
	if (confirm.length === 0 || password !== confirm) valid = false;

	submitBtn.disabled = !valid;
}

// Field-specific validation for showing messages only on blur
function validateEmailField() {
	const email = document.getElementById('email').value;
	const emailMsg = document.getElementById('emailMsg');
	const emailFilter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (email.length > 0 && !emailFilter.test(email)) {
		emailMsg.textContent = "Please enter a valid email";
	} else {
		emailMsg.textContent = "";
	}
	validateForm();
}

function validateUserNameField() {
	const userName = document.getElementById('UserName').value;
	const userNameMsg = document.getElementById('userNameMsg');
	if (userName.length === 0) {
		userNameMsg.textContent = "A user name must be entered";
	} else {
		userNameMsg.textContent = "";
	}
	validateForm();
}

function validatePasswordField() {
	const password = document.getElementById('password').value;
	const passwordMsg = document.getElementById('passwordMsg');
	if (password.length > 0 && password.length < 8) {
		passwordMsg.textContent = "Password must be at least 8 characters long";
	} else {
		passwordMsg.textContent = "";
	}
	validateConfirmField(); // <-- Add this line
	validateForm();
}

function validateConfirmField() {
	const password = document.getElementById('password').value;
	const confirm = document.getElementById('confirm').value;
	const pwMatchMsg = document.getElementById('pwMatchMsg');
	if (confirm.length > 0 && password !== confirm) {
		pwMatchMsg.textContent = "Passwords do not match";
	} else {
		pwMatchMsg.textContent = "";
	}
	validateForm();
}

// Attach validation to blur events only
window.onload = function () {
	document.getElementById('email').oninput = validateForm;
	document.getElementById('UserName').oninput = validateForm;
	document.getElementById('password').oninput = validateForm;
	document.getElementById('confirm').oninput = validateForm;

	document.getElementById('email').onblur = validateEmailField;
	document.getElementById('UserName').onblur = validateUserNameField;
	document.getElementById('password').onblur = validatePasswordField;
	document.getElementById('confirm').onblur = validateConfirmField;

	// Initial validation in case fields are pre-filled
	validateForm();
}