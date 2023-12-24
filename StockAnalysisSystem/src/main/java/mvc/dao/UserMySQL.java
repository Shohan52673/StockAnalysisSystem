package mvc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mvc.bean.User;

@Repository
public class UserMySQL implements UserDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<User> findAllUsers() {
		String sql = "select userId, nickname, userName, password, email from stockanalysissystem.user";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
	}

	@Override
	public void addUser(User user) {
		String sql ="insert into user(fullname, username, password, email) values(?,?,?,?)";
		jdbcTemplate.update(sql,user.getFullname(), user.getUsername(), user.getPassword(), user.getEmail());
		
	}

	@Override
	public Boolean updateUserPassword(Integer userId, String newPassword) {
		String sql ="update user set password = ? where userId = ?";
		int rowcount = jdbcTemplate.update(sql, newPassword, userId);
		return rowcount > 0;
	}

	@Override
	public Optional<User> findUserByUsername(String username) {
		String sql = "select userId, userName, password, email from stockanalysissystem.user where userName = ?";
		try {
			User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
			return Optional.ofNullable(user);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	
}
