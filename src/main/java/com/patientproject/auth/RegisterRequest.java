package com.patientproject.auth;



import com.patientproject.enumeration.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	
	private String userName;
	
	private String email;
	
	private String password;
	
	private UserType userType;
}
