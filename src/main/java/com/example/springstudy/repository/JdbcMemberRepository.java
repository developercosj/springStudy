package com.example.springstudy.repository;

import com.example.springstudy.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {


    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";
        Connection conn = null;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            // id 값 리턴
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // parameterIndex 는 ? 부분
            pstmt.setString(1, member.getName());
            // DB 에 쿼리 날림
            pstmt.executeUpdate();
            // Statement.RETURN_GENERATED_KEYS 과 매칭 key 의 index 반환해줌
            rs = pstmt.getGeneratedKeys();
            // 값이 있으면 값을 꺼내서 세팅
            if (rs.next()) {
                member.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            };
            return member;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, pstmt, rs);
        }


    }

    private Connection getConnection() {
        // DataSourceUtils를 통해 connection 획득해야 transaction 깨끗하게 유지가능
        return DataSourceUtils.getConnection(dataSource);
    }

    // try-with-resources 로도 작성 가능 (알아보기)
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
       // 역순으로 close
        try {
            if (rs != null) {
                rs.close();
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }


    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                Member member = new Member();;
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);

            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);

        } finally {
            close(conn, pstmt, rs);
        }

    }


    @Override
    public List<Member> findAll() {
        String sql = "select * from member";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>();
            while(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }

            return members;

        } catch (SQLException e) {
            throw new IllegalStateException(e);

        } finally {
            close(conn, pstmt, rs);
        }

    }

    @Override
    public Optional<Member> findByName(String name) {

        String sql = "select * from member where name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            return Optional.empty();
        } catch (SQLException e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }

    }


}
