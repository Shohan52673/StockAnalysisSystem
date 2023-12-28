package mvc.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResetPassword {
	
	@NotEmpty(message = "{user.email.notempty}")
	@Email(message = "{user.email.InvalidEmailFormat}")
	private String email;
	
	@NotEmpty(message = "{user.username.notempty}")
	private String username;
	
	private String password;
}
