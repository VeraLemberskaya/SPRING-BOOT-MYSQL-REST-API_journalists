package com.example.project.journalists.repository.rowMapper;

import com.example.project.journalists.model.entity.Journalist;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.project.journalists.repository.rowMapper.ColumnName.*;

@Component
public class JournalistRowMapper implements RowMapper<Journalist> {
    @Override
    public Journalist mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Journalist.builder()
                .id(rs.getLong(JOURNALIST_ID))
                .name(rs.getString(JOURNALIST_NAME))
                .surname(rs.getString(JOURNALIST_SURNAME))
                .description(rs.getString(JOURNALIST_ABOUT))
                .photoURL(rs.getString(JOURNALIST_PHOTO_URL))
                .build();
    }
}
