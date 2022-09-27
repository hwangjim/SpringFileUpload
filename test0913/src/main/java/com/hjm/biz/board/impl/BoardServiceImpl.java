package com.hjm.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjm.biz.board.BoardService;
import com.hjm.biz.board.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	// BoardDAO는 메모리에 존재하지 않기 때문에 BoardDAO2로 output을 작성
	private BoardDAO2 boardDAO;
	
	@Override
	public void insertBoard(BoardVO vo) {
		boardDAO.insertBoard(vo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}

	@Override
	public BoardVO selectOneBoard(BoardVO vo) {
		return boardDAO.selectOneBoard(vo);
	}

	@Override
	public List<BoardVO> selectAllBoard(BoardVO vo) {
		return boardDAO.selectAllBoard(vo);
	}

}
