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
	
	
	
//	使用者-User:
//	1. 查詢所有使用者(多筆)
	@Override
	public List<User> findAllUsers() {
		String sql = "select userId, fullname, email, userName, password from stockanalysissystem.user";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
	}
//	2. 新增使用者
	@Override
	public int addUser(User user) {
		String sql ="insert into StockAnalysisSystem.user(fullname, email, username, password) values(?,?,?,?)";
		return jdbcTemplate.update(sql, user.getFullname(), user.getEmail(),user.getUsername(),user.getPassword());
		
	}
//	3. 修改密碼
	@Override
	public Boolean updateUserPassword(Integer userId, String newPassword) {
		String sql ="update StockAnalysisSystem.user set password = ? where userId = ?";
		int rowcount = jdbcTemplate.update(sql, newPassword, userId);
		return rowcount > 0;
	}
//	4. 根據使用者名稱查找使用者(登入用-單筆)
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
//	5. 根據使用者email查找使用者(註冊用-單筆)
	@Override
	public Optional<User> findUserByEmail(String email) {
		String sql ="select userId, fullname, email, username, password from stockanalysissystem.user where email = ?";
		try {
			User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
			return Optional.ofNullable(user);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	
	
}
