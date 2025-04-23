package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Demo;

@Repository
public class DemoDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    // Return Map (Using JdbcTemplate query )
    public List<Map<String, Object>> select1() {
        String sql = "select seq, user from demo";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        return list;
    }

    // Return Demo Model (Using [RowMapper] (Interface))
    // 해당 인터페이스를 통해 queryForList()와 같은 기능을 하도록 구현
    public List<Demo> select2() {
        String sql = "select seq, user from demo";
        List<Demo> list = jdbcTemplate.query(sql, new RowMapper<Demo>() {
            @Override
            public Demo mapRow(ResultSet rs, int index) throws SQLException {
                Long seq = rs.getLong("seq");
                String user = rs.getString("user");
                Demo demo = new Demo();
                demo.setSeq(seq);
                demo.setUser(user);
                return demo;
            }
        });
        return list;
    }

    public int insert(Demo demo) {
        String sql = "insert into demo (seq, user) values (? ,?)";

        int result = jdbcTemplate.update(sql, demo.getSeq(), demo.getUser());

        return result;
    }

    public int delete(Demo demo) {
        String sql = "delete from demo where seq=?";

        int result = jdbcTemplate.update(sql, demo.getSeq());

        return result;
    }
}
