package repositories.tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import models.News;
import models.Tag;
import repositories.MySQLAbstractRepository;
import repositories.news.NewsRepository;

public class MySQLTagRepository extends MySQLAbstractRepository implements TagRepository {
	
	@Inject
	private NewsRepository newsRepository;
	
	@Override
	public Tag findById(int tagId) {
        Tag tag = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tag AS t WHERE t.id = ?");
            preparedStatement.setInt(1, tagId);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
                tag = new Tag(tagId, resultSet.getString("keyword"));

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tag;
	}
	
	@Override
	public Tag findByKeyword(String keyword) {
        Tag tag = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tag AS t WHERE t.keyword LIKE ?");
            preparedStatement.setString(1, keyword);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
                tag = new Tag(resultSet.getInt(1), keyword);

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tag;
	}

	@Override
	public List<Tag> getTagsByNews(int newsId) {
        List<Tag> tags = new ArrayList<>();

        Connection connection = null;
        ResultSet resultSet = null;

        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news_tag WHERE newsId = ?");
            preparedStatement.setInt(1, newsId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            	tags.add(findById(resultSet.getInt("tagId")));

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);

        }

        return tags;
	}

	@Override
	public List<News> getNewsByTag(int tagId) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        ResultSet resultSet = null;

        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news_tag WHERE tagId = ?");
            preparedStatement.setInt(1, tagId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            	news.add(newsRepository.findById(resultSet.getInt("newsId")));

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);

        }

        return news;
	}

	@Override
	public Tag createTag(int newsId, String keyword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Tag tag = null;

        try {
            connection = this.newConnection();

            
            tag = findByKeyword(keyword);
            
            if (tag == null) {
	            String[] generatedColumns={"tagId"};
	            preparedStatement = connection.prepareStatement("INSERT INTO tag (keyword) VALUES (?)", generatedColumns);
	            preparedStatement.setString(1, keyword);
	
	            preparedStatement.executeUpdate();
	            resultSet = preparedStatement.getGeneratedKeys();
	            
	            if (resultSet.next())
	            	tag = new Tag(resultSet.getInt(1), keyword);
            }
            
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            
            preparedStatement = connection.prepareStatement("INSERT INTO news_tag (newsId, tagId) VALUES (?, ?)");
            preparedStatement.setInt(1, newsId);
            preparedStatement.setInt(2, tag.getTagId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tag;
	}
}
