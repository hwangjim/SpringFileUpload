package com.hjm.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hjm.biz.board.BoardVO;

// 주입대상의 변경
@Repository("boardDAO")
public class BoardDAO2 {

	// template 사용 = 의존관계로 의존성 주입해주면 된다.
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	final String sql_selectOne="SELECT * FROM BOARD WHERE BID=?";
	final String sql_selectAll="SELECT * FROM BOARD ORDER BY BID DESC";
	final String sql_insert="INSERT INTO BOARD(BID,TITLE,WRITER,CONTENT,ABOUTFILE) VALUES((SELECT NVL(MAX(BID),0)+1 FROM BOARD),?,?,?,?)";
	final String sql_update="UPDATE BOARD SET TITLE=?,CONTENT=?, ABOUTFILE=? WHERE BID=?";
	final String sql_delete="DELETE BOARD WHERE BID=?";
	final String sql_findtitle = "SELECT * FROM BOARD WHERE TITLE LIKE '%'||?||'%' ORDER BY BID DESC";
	final String sql_findwriter = "SELECT * FROM BOARD WHERE WRITER LIKE '%'||?||'%' ORDER BY BID DESC";
	
	// insert, update, delete 류들은 jdbcTemplate.update로 표현한다.
	// .update(활용할 sql문, get해줘야할 값들 즉, sql문 내 ?에 해당하는 값들)
	void insertBoard(BoardVO vo) {
		jdbcTemplate.update(sql_insert,vo.getTitle(),vo.getWriter(),vo.getContent(), vo.getFileName());
	}
	void updateBoard(BoardVO vo) {
		jdbcTemplate.update(sql_update,vo.getTitle(),vo.getContent(),vo.getFileName(),vo.getBid());
	}
	void deleteBoard(BoardVO vo) {
		jdbcTemplate.update(sql_delete,vo.getBid());
	}
	// selectOne류는 queryForObject를 사용한다.
	// queryForObject(활용할 sql문, Object[], 매핑정보)
	BoardVO selectOneBoard(BoardVO vo) {
		Object[] args= {vo.getBid()};
		return jdbcTemplate.queryForObject(sql_selectOne,args,new BoardRowMapper());
	}
	// selectAll류는 query를 사용한다.
	// query(활용할 sql문, Object[], 매핑정보)
	// 이 때 sql문에서 요구하는 ?가 없기 때문에 Object[]는 제외한다.
	List<BoardVO> selectAllBoard(BoardVO vo) {
		if(vo.getTitle()!= null) {
			Object[] args = {vo.getTitle()};
			return jdbcTemplate.query(sql_findtitle,args, new BoardRowMapper());
		} else if(vo.getWriter() != null) {
			Object[] args = {vo.getWriter()};
			return jdbcTemplate.query(sql_findwriter,args, new BoardRowMapper());
		} else {
			jdbcTemplate.query(sql_selectAll, new BoardRowMapper());
		}
		return jdbcTemplate.query(sql_selectAll,new BoardRowMapper());
	}
}
// 매핑정보가 저장될 클래스 필요
// 매핑정보는 RowMapper 클래스를 통해 작성 가능
class BoardRowMapper implements RowMapper<BoardVO>{
	
	// 강제된 메소드를 활용
	// selectOne이든 All이든 알아서 구분해서 해준다.
	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO data=new BoardVO();
		data.setBid(rs.getInt("BID"));
		data.setContent(rs.getString("CONTENT"));
		data.setTitle(rs.getString("TITLE"));
		data.setWriter(rs.getString("WRITER"));
		data.setCnt(rs.getInt("CNT"));
		data.setRegdate(rs.getString("REGDATE"));
		data.setFileName(rs.getString("ABOUTFILE"));
		return data;
	}
	
}
