package com.example.project.journalists.repository.rowMapper;

import com.example.project.journalists.model.entity.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.project.journalists.repository.rowMapper.ColumnName.*;

@Component
public class CategoryRowMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Category.builder()
                .name(rs.getString(CATEGORY_NAME))
                .id(rs.getLong(CATEGORY_ID))
                .build();
    }
}
