package com.example.blog.Components;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class MainModel {
    private final JdbcTemplate jdbcTemplate;

    public MainModel() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/blog");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    //INSERT///////////////////////////////////////////////////////////////
    public void insertPost(String title, String main_text, byte[] image) {
        System.out.println("########Adding new Post with image##########");
        String sql = "INSERT INTO post (title, main_text, image, have_image) VALUES (?, ?, ?, ?)";
        if (image != null && image.length > 0) {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setString(1, title);
                    ps.setString(2, main_text);
                    ps.setBytes(3, image);
                    ps.setBoolean(4, true);
                    return ps;
                }
            });
        } else {
            jdbcTemplate.update(sql, title, main_text);
        }
    }

    public void insertPost(String title, String main_text){

        String sql = "INSERT INTO post(title, main_text, have_image) VALUES ('" + title + "', '" + main_text + "', false);";
        int users = jdbcTemplate.update(sql);
        System.out.println(users);
    }

    public void insertUsr(String login, String pass, String email){

        String sql = "INSERT INTO usr(login, pass, email) VALUES ('" + login + "', '" + pass + "', '" + email + "');";
        int users = jdbcTemplate.update(sql);
        System.out.println(users);
    }

    public void insertComment(int post_id, int usr_id, String text){

        String sql = "INSERT INTO comment(post_id, usr_id, text) VALUES ('" + post_id + "', '" + usr_id + "', '" + text + "');";
        int users = jdbcTemplate.update(sql);
        System.out.println(users);
    }


    //DELETE///////////////////////////////////////////////////////////////
    public void deletePost(int id){
        String sql = "DELETE FROM post WHERE id = " + id;
        int users = jdbcTemplate.update(sql);
        System.out.println(users);
    }

    public void deleteComment(int id){
        String sql = "DELETE FROM comment WHERE id = " + id;
        int users = jdbcTemplate.update(sql);
        System.out.println(users);
    }

    public void deleteUsr(int id){
        String sql = "DELETE FROM usr WHERE id = " + id;
        int users = jdbcTemplate.update(sql);
        System.out.println(users);
    }

    //GETTERS///////////////////////////////////////////////////////////////
    public List<Map<String, Object>> getTable(String table){
        String sql = "SELECT * FROM " + table;
        List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
        return users;
    }


    //SELECT usr.login, comment.text, comment.create_date, comment.id FROM comment INNER JOIN usr WHERE comment.usr_id = usr.id AND comment.post_id = 1;
    public List<Map<String, Object>> getCommentsByPost(int idOfPost){
        String sql = "SELECT usr.login, comment.text, comment.create_date, comment.id FROM comment INNER JOIN usr WHERE comment.usr_id = usr.id AND comment.post_id = " + idOfPost;
        return jdbcTemplate.queryForList(sql);
    }
    public List<Map<String, Object>> getPost(int idOfPost){
        String sql = "SELECT * FROM post WHERE id = " + idOfPost;
        return jdbcTemplate.queryForList(sql);
    }

    //FIND///////////////////////////////////////////////////////////////
    public Map<String, Object> findUsr(String username){
        String sql = "SELECT * FROM usr WHERE login = '" + username + "';";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
        return users.get(0);
    }

    //DEBUG///////////////////////////////////////////////////////////////
    public void debugTable(String table){
        String sql = "SELECT * FROM " + table;
        List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
        System.out.println(users);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }


}
