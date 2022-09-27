//package com.hjm.biz.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import com.hjm.biz.board.BoardService;
//import com.hjm.biz.board.BoardVO;
//import com.hjm.biz.board.impl.BoardDAO;
//
//@Controller
//@SessionAttributes("userId")
//public class MainController  {
//	
//	
//	@ModelAttribute("scMap")
//	public Map<String, String> searchConditionMap() {
//		Map<String, String> scMap = new HashMap<String, String>();
//		// main.jsp 내 option 태그를 가져옴 
//		// put(view에서 어떻게 보여야하는지, 실제로 어떤건지)
//		scMap.put("제목", "TITLE");
//		scMap.put("작성자", "WRITER");
//		return scMap;
//	}
//	
//	@RequestMapping("/main.do")
//	   public String main(@RequestParam(value="searchCondition",defaultValue="TITLE",required=false)String searchCondition,@RequestParam(value="searchContent",defaultValue="",required=false)String searchContent,BoardVO bVO,BoardDAO bDAO,Model model) {
//	      System.out.println("검색조건: "+searchCondition);
//	      System.out.println("검색어: "+searchContent);
//	      if(searchContent != null) {
//	    	  System.out.println("로그맨");
//	    	  if(searchCondition.equals("TITLE")) {
//	    		  bVO.setTitle(searchContent);
//	    		  System.out.println("로그1");
//	    	  } else if(searchCondition.equals("WRITER")) {
//	    		  bVO.setWriter(searchContent);
//	    		  System.out.println("로그2");
//	    	  }
//	    	 
//	      }
//	      List<BoardVO> datas=bDAO.selectAllBoard(bVO);
//	      
//	      model.addAttribute("datas", datas);
//	      return "main.jsp";
//	   }
//	
////	// main.do를 읽으면 위 Controller를 수행한다.
////	@RequestMapping(value = "/main.do")
////	public String handleRequest(@RequestParam(value = "searchCondition", defaultValue = "TITLE", required = false) String searchCondition, @RequestParam() String searchContent, MemberVO mVO, MemberDAO mDAO, BoardVO bVO, BoardDAO bDAO, Model model, HttpSession session) {
////		System.out.println("검색 조건" + searchCondition);
////		System.out.println("검색어" + searchContent);
////		// Board에 저장되어있는 것들을 selectAll해서 보여준다.
////		List<BoardVO> datas=bDAO.selectAllBoard(bVO);
////		// 로그인 당시 아이디를 보여주고
////		mVO = (MemberVO)session.getAttribute("userId");
////		mVO = mDAO.selectOneMember(mVO);
////		// mypage에서 이름을 변경했을 경우 member에 저장해 보여주게끔
////		model.addAttribute("member", mVO);
////		// 역시 새 글을 작성했을때 기존 datas에 담겨있기 때문에 새로 계속 보여준다.
////		model.addAttribute("datas", datas);
////		return "main.jsp";
////	}
////	
//
//	/*
//	@Override
//	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
//		BoardVO bVO=new BoardVO();
//		
//		BoardDAO bDAO=new BoardDAO();
//		List<BoardVO> datas=bDAO.selectAllBoard(bVO);
//		
//		HttpSession session=request.getSession();
//		session.setAttribute("datas", datas);
//		
//		return "main";
//	}
//	*/
//	
//}
