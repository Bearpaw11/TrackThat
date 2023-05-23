package com.TrackThat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.TrackThat.dao.UserRepository;
import com.TrackThat.entity.User;
import com.TrackThat.entity.UserRecord;
import com.TrackThat.entity.UserWishRecord;
import com.TrackThat.service.UserService;

@Controller
public class SiteController {
	
	// need to inject our user service
	@Autowired
	private UserService userService;
			
	@Autowired
	private PasswordEncoder encoder;
	
			
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	
	
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

//    @PostMapping("/signup")
//    public String signup(@ModelAttribute("user") User user) {
//        // Save the user to the database
//        userRepository.save(user);
//        return "redirect:/login";
//    }
    
	
	@PostMapping("saveUser")
	public String saveUser(@ModelAttribute("user") User theUser, Model theModel) {
		
		//set user to the userName entered
		User user = userService.verifyLogin(theUser.getUserName());
		
		//check to see is the user already exists. If null then the user does not exist in database
		if(user == null) {
			
		//encrypting the user password
		String encryptPassword = encoder.encode(theUser.getPassword());
		
		//setting the user password to encryptPassword
		theUser.setPassword(encryptPassword);
		
		//save the user using the service
		userService.signupUser(theUser);
		
		//redirect to the sign in page
		return "redirect:/signin";
		} else {
			theModel.addAttribute("UserError", "Error, User name already exists");
		}
		//add a new user to the model and return back to the signup page
		theModel.addAttribute("user", new User());
		return "signup";
	}


//	@PostMapping("saveUser")
//		public String saveUser(@ModelAttribute("user") User theUser, Model theModel) {
//	
//	//set user to the userName entered
//		User user = userService.verifyLogin(theUser.getUserName());
//	
//	//check to see is the user already exists. If null then the user does not exist in database
//		if(user == null) {
//		
//	//encrypting the user password
//		String encryptPassword = encoder.encode(theUser.getPassword());
//	
//	//setting the user password to encryptPassword
//		theUser.setPassword(encryptPassword);
//	
//	//save the user using the service
//		userService.saveUser(theUser);
//	
//	//redirect to the sign in page
//		return "redirect:/signin";
//		} else {
//		theModel.addAttribute("UserError", "Error, User name already exists");
//		}
//	//add a new user to the model and return back to the signup page
//		theModel.addAttribute("user", new User());
//		return "signup";
//	}

	@GetMapping("/signin")
	public String signin(Model theModel) {
	
	//create a loginHelper object to bind to the form data
		LoginHelper loginHelper = new LoginHelper();
	
	//add loginHelper to the model
		theModel.addAttribute("loginHelper", loginHelper);
	
	//Route to the signin page
		return "signin";
	}
//
//
//verifies user and directs to the user home page
@PostMapping("/loginUser")
	public String verifyLogin(@ModelAttribute("loginHelper") LoginHelper loginHelper, Model theModel, HttpSession session){
		
		User user = userService.verifyLogin(loginHelper.getUserName());
	
		//encoder is matching raw password to encrypted password
		if(user == null || !encoder.matches(loginHelper.getPassword(), user.getPassword())) {
			//error message if login fails
			theModel.addAttribute("loginError", "Error logging in. Please Try again.");
			//reload the sign in page
			return "signin";
		}
		//add loggedInUser to HttpSession as an attribute
		session.setAttribute("loggedInUser", user);
		return "redirect:/mainUser";
	}			
//
////removes loggedInuser from HttpSession and redirects to landing page
//@GetMapping("/logout")
//public String logout(HttpSession session) {
//	session.removeAttribute("loggedInUser");
//	return "redirect:/";
//}
//
//
//// This is the Main user page
//@GetMapping("/mainUser")
//public String mainUser(Model theModel, HttpSession session) {
//
////gets the user from the http session attribute and gets the User Id		
//User user =  (User) (session.getAttribute("loggedInUser"));
//int UserId =(user.getId());
//
////uses the UserId to get the userRecords using the userService
//List<UserRecord> theUserRecords = userService.getUserRecords(UserId);
//session.setAttribute("userRecords", theUserRecords);
//
////uses the UserId to get the userWishRecords using the userService
//List<UserWishRecord> theUserWishRecords = userService.getUserWishRecords(UserId);
//theModel.addAttribute("userWishRecords", theUserWishRecords);
//
////route to the mainUser page
//return "mainUser";
//}
//
//
////creates model attribute to bind the form data. Returns the add collection page
//@GetMapping("/addCollection")
//public String addCollection(Model theModel, HttpSession session) {
//	UserRecord theUserRecord = new UserRecord();
//	theModel.addAttribute("userRecord", theUserRecord);
//	
//	return "addCollection";
//}
//
////saves the user records. Redirects to mainUser
//@PostMapping("/saveUserRecord")
//public String saveUserRecord(@ModelAttribute("userRecord") UserRecord theUserRecord, @ModelAttribute("loggedInUser") User user, HttpSession session) {
//	
//	//gets the user Id from http session
//	User user1 =  (User) (session.getAttribute("loggedInUser"));
//	int UserId =(user1.getId());
//	
//	//save the record using the userService
//	userService.saveUserRecord(theUserRecord, UserId);
//	
//	//Redirects to the mainUser page
//	return "redirect:/mainUser";
//}
//
////get record info to populate form for update
//@GetMapping("/showFormForUpdate")
//public String showFormForUpdate(@RequestParam("recordId") int theId, Model theModel) {
//	//get the record from our service
//	UserRecord theUserRecord = userService.getUserRecord(theId);
//	
//	// set record as a model attribute to pre-populate the form
//	theModel.addAttribute("userRecord", theUserRecord);
//	
//	//Route to add form page
//	return "addCollection";
//}
//
//@GetMapping("/deleteRecord")
//public String deleteRecord(@RequestParam("userRecordId") int theId) {
//	//use the service to delete the record
//	userService.deleteUserRecord(theId);
//	//reload mainUser page
//	return "redirect:/mainUser";
//}
//
////creates model attribute to bind the form data. Returns the addWish
//@GetMapping("/addWish")
//public String addWish(Model theModel, HttpSession session) {
//	UserWishRecord theUserWishRecord = new UserWishRecord();
//	theModel.addAttribute("userWishRecord", theUserWishRecord);
//	return "addWish";
//}
//
//@PostMapping("/saveUserWishRecord")
//public String saveUserWishRecord(@ModelAttribute("userWishRecord") UserWishRecord theUserWishRecord, @ModelAttribute("loggedInUser") User user, HttpSession session) {
//	//gets the user Id from http session
//	User user1 =  (User) (session.getAttribute("loggedInUser"));
//	int UserId =(user1.getId());
//	
//	//save the record using service
//	userService.saveUserWishRecord(theUserWishRecord, UserId);
//	
//	//Redirects to the mainUser page
//	return "redirect:/mainUser";
//}
//
////get record info to populate form for update
//@GetMapping("/showFormForWishUpdate")
//public String showFormForWishUpdate(@RequestParam("recordId") int theId, Model theModel) {
//	//get the record from our service
//	UserWishRecord theUserWishRecord = userService.getUserWishRecord(theId);
//	
//	// set record as a model attribute to pre-populate the form
//	theModel.addAttribute("userWishRecord", theUserWishRecord);
//	
//	//Route to addWish page
//	return "addWish";
//}
//
//@GetMapping("/deleteWishRecord")
//public String deleteWishRecord(@RequestParam("userWishRecordId") int theId) {
//	//use the service to delete the record
//	userService.deleteUserWishRecord(theId);
//	
//	//Redirects to the mainUser page
//	return "redirect:/mainUser";
//}

}
