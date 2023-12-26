package mvc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mvc.entity.User;

@Repository
public class UserMySQL implements UserDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	

	@Override
	public List<User> findAllUsers() {
		String sql = "select userId, fullname, email, userName, password from stockanalysissystem.user";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
	}

	@Override
	public void addUser(User user) {
		String sql ="insert into StockAnalysisSystem.user(fullname, email, username, password) values(?,?,?,?)";
		jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
		
	}

	@Override
	public Boolean updateUserPassword(Integer userId, String newPassword) {
		String sql ="update StockAnalysisSystem.user set password = ? where userId = ?";
		int rowcount = jdbcTemplate.update(sql, newPassword, userId);
		return rowcount > 0;
	}

	@Override
	public Optional<User> findUserByUsername(String username) {
		String sql = "select userId, fullname, email, username, password from stockanalysissystem.user where userName = ?";
		try {
			User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
			return Optional.ofNullable(user);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	
	
}
