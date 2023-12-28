package mvc.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUser {

	@NotEmpty(message = "{user.username.notempty}")
	private String username;
	
	@NotEmpty(message = "{user.password.notempty}")
	private String password;
	
	@NotEmpty(message = "{user.code.notempty}")
	private String code;
}
