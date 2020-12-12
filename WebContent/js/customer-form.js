$(document).ready(function(){
		
		$("#customerForm").validate({
			rules: {
				email: {
					required: true,
					email: true
				},
				firstname: "required",
				lastname: "required",
				password: "required",
				confirmPassword: {
					required:true,
					equalTo: "#password"
				},
				phone: "required",
				address1: "required",
				address2: "required",
				city: "required",
				state: "required",
				zipCode: "required",
				country: "required",
			},
			
			messages: {
				email: {
					required: "Please enter e-mail address",
					email: "Please enter a valid e-mail address"
				},
				firstname: "Please enter first name",
				lastname: "Please enter last name",
				password: "Please enter password",
				confirmPassword: {
					required: "Please confirm password",
					equalTo: "Passwords don't match"
				},
				phone: "Please enter phone number",
				address1: "Please enter address line 1",
				address2: "Please enter address line 2",
				city: "Please enter city",
				state: "Please enter state",
				zipCode: "Please enter zip code",
				country: "Please enter country"
			}
		});
		
		$("#buttonCancel").click(function() {
			history.go(-1);
	    });
		
	});