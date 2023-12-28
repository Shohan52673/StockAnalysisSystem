package mvc.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupUser {
	
	
	@Size(min = 2, max = 10, message = "{user.fullname.size}")
	private String fullname;
	
	
	@NotEmpty(message = "{user.email.notempty}")
	@Email(message = "{user.email.InvalidEmailFormat}")
	private String email;
	
	@Size(min = 8, max = 16, message = "{user.username.notempty}")
	private String username;
	
	@NotEmpty(message = "{user.password.notempty}")
	private String password;
}
