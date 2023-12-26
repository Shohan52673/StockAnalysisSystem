package mvc.bean;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupUser {
	
	@NotEmpty(message = "{user.fullname.notempty}")
	private String fullname;
	
	@NotEmpty(message = "{user.email.notempty}")
	@NotEmpty(message = "{user.email.InvalidEmailFormat}")
	private String email;
	
	@NotEmpty(message = "{user.username.notempty}")
	private String username;
	
	@NotEmpty(message = "{user.password.notempty}")
	private String password;
}
