package com.hjm.biz.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.hjm.biz.board.BoardService;
import com.hjm.biz.board.BoardVO;

@Controller
@SessionAttributes("data")	// "data"라는 이름의 데이터가 Model 객체에 세팅이 된다면, 그것을 session에 기억시키겠다.
public class BoardController {
	
	// BoardServiceImpl 주입
	@Autowired
	private BoardService BoardService; 	// 비즈니스 컴포넌트, DAO 직접이용 x
	// Command 객체 내의 BoardDAO DAO 전부 삭제
	// 이후 DAO로 되어있는 것을 BoardService로 교체
	
	@ModelAttribute("scMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> scMap = new HashMap<String, String>();
		// main.jsp 내 option 태그를 가져옴 
		// put(view에서 어떻게 보여야하는지, 실제로 어떤건지)
		scMap.put("제목", "TITLE");
		scMap.put("작성자", "WRITER");
		return scMap;
	}
	
	@RequestMapping("/main.do")
	   public String main(@RequestParam(value="searchCondition",defaultValue="TITLE",required=false)String searchCondition,@RequestParam(value="searchContent",defaultValue="",required=false)String searchContent,BoardVO bVO,Model model) {
	      System.out.println("검색조건: "+searchCondition);
	      System.out.println("검색어: "+searchContent);
	      if(searchContent != null) {
	    	  System.out.println("로그맨");
	    	  if(searchCondition.equals("TITLE")) {
	    		  bVO.setTitle(searchContent);
	    		  System.out.println("로그1");
	    	  } else if(searchCondition.equals("WRITER")) {
	    		  bVO.setWriter(searchContent);
	    		  System.out.println("로그2");
	    	  }
	    	 
	      }
	      List<BoardVO> datas=BoardService.selectAllBoard(bVO);
	      System.out.println("로극" + bVO);
	      model.addAttribute("datas", datas);
	      System.out.println("로그극" + datas);
	      return "main.jsp";
	   }
	

	@RequestMapping(value = "/board.do", method = RequestMethod.GET)
	public String handleRequest(BoardVO bVO, Model model) {
		System.out.println("board.do 하나 선택한 로그 : " + bVO);
		bVO=BoardService.selectOneBoard(bVO);
		model.addAttribute("data", bVO);
		System.out.println("알랄라" + bVO.getFileName());
		return "board.jsp";
	}
	@RequestMapping(value = "/updateB.do")
	public String updateB(@ModelAttribute("data") BoardVO bVO, Model model) throws IllegalStateException, IOException {
		MultipartFile uploadFile = bVO.getUploadFile();
		if(!uploadFile.isEmpty()) {	// 업로드한 파일 존재여부 확인
			String fileName = uploadFile.getOriginalFilename();	// 업로드한 파일명
			uploadFile.transferTo(new File("C:\\HWANG0607\\workspace\\test0913\\src\\main\\webapp\\images\\" + fileName));
			// 업로드한 파일을 지정한 경로에 저장
			bVO.setFileName(fileName);
			System.out.println("ddd" + fileName);
		}
		
		BoardService.updateBoard(bVO);
		System.out.println("update.do 로그" + bVO);
		return "main.do";
	}
	@RequestMapping(value = "/insertB.do")
	public String insertB(BoardVO bVO) throws IllegalStateException, IOException {
		
		MultipartFile uploadFile = bVO.getUploadFile();
		if(!uploadFile.isEmpty()) {	// 업로드한 파일 존재여부 확인
			String fileName = uploadFile.getOriginalFilename();	// 업로드한 파일명
			uploadFile.transferTo(new File("C:\\HWANG0607\\workspace\\test0913\\src\\main\\webapp\\images\\" + fileName));
			// 업로드한 파일을 지정한 경로에 저장
			bVO.setFileName(fileName);
			System.out.println("ddd" + fileName);
		}
		
		BoardService.insertBoard(bVO);
		System.out.println("insertB 로그 " + bVO);
		return "redirect:main.do";
	}
	@RequestMapping(value = "/deleteB.do")
	public String deleteB(@ModelAttribute("data") BoardVO bVO, Model model) {
		BoardService.deleteBoard(bVO);
		System.out.println("deleteB 로그 " + bVO);
		return "main.do";
	}

	/*
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		BoardVO bVO=new BoardVO();
		bVO.setBid(Integer.parseInt(request.getParameter("bid")));
		
		BoardDAO bDAO=new BoardDAO();
		bVO=bDAO.selectOneBoard(bVO);
		
		HttpSession session=request.getSession();
		session.setAttribute("data", bVO);
	
		return "board";
	}
	 */
	
}
