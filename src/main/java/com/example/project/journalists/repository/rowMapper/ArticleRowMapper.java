package com.example.project.journalists.repository.rowMapper;

import com.example.project.journalists.model.entity.Article;
import com.example.project.journalists.model.entity.Category;
import com.example.project.journalists.model.entity.Journalist;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.project.journalists.repository.rowMapper.ColumnName.*;

@Component
public class ArticleRowMapper implements ResultSetExtractor<List<Article>> {

    private final JournalistRowMapper journalistRowMapper;
    private final CategoryRowMapper categoryRowMapper;

    public ArticleRowMapper(JournalistRowMapper journalistRowMapper, CategoryRowMapper categoryRowMapper){
        this.journalistRowMapper = journalistRowMapper;
        this.categoryRowMapper = categoryRowMapper;
    }

    @Override
    public List<Article> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Article> articlesById = new HashMap<>();
        while (rs.next()) {
            Long articleId = rs.getLong(ARTICLE_ID);
            String articleTitle = rs.getString(ARTICLE_TITLE);
            String articleContent = rs.getString(ARTICLE_CONTENT);
            LocalDate articleDate = rs.getDate(ARTICLE_DATE).toLocalDate();
            Journalist articleJournalist = journalistRowMapper.mapRow(rs, 0);
            Category category = categoryRowMapper.mapRow(rs, 0);

            Article article = articlesById.get(articleId);

            if (article == null) {
                article = Article.builder()
                        .id(articleId)
                        .title(articleTitle)
                        .content(articleContent)
                        .date(articleDate)
                        .journalist(articleJournalist)
                        .categories(new ArrayList<>())
                        .build();
                articlesById.put(articleId, article);
            }
            if (category != null) article.getCategories().add(category);
        }

            return new ArrayList<>(articlesById.values());
    }
}
