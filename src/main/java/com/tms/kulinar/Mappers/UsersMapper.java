package com.tms.kulinar.Mappers;

import com.tms.kulinar.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        User users=new User();
        users.setId(resultSet.getInt("id"));
        users.setFirst_name(resultSet.getString("first name"));
        users.setLast_name(resultSet.getString("last name"));
        users.setPhone(resultSet.getInt("phone"));
        users.setEmail(resultSet.getString("email"));
        users.setLogin(resultSet.getString("login"));
        users.setPassword(resultSet.getString("password"));
        System.out.println("WORK WORK WORK");
        return users;
    }
}
