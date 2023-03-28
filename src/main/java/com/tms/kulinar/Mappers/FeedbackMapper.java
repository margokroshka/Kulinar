package com.tms.kulinar.Mappers;

import com.tms.kulinar.domain.Feedback;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackMapper implements RowMapper<Feedback> {

    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Feedback feedback=new Feedback();
        feedback.setId(rs.getInt("id"));
        feedback.setName(rs.getString("name"));
        feedback.setContent(rs.getString("content"));
        System.out.println("WORK WORK WORK");
        return feedback;
    }
}
