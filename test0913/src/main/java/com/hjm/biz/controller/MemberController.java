 package com.hjm.biz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hjm.biz.board.BoardService;
import com.hjm.biz.member.MemberService;
import com.hjm.biz.member.MemberVO;
import com.hjm.biz.member.impl.MemberDAO;

@Controller
@SessionAttributes("userId")
public class MemberController {
	
	@Autowired
	private MemberService MemberService;
	
	// index.jsp 실행시 login.jsp로 가게끔
	// 로그인 화면 보여줘
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String index() {
		return "login.jsp";
	}
	// post 방식으로 login.do를 받아온다.
	// 로그인 해줘
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	// 데이터를 1개이상 전달할때 output이 ModelAndView
	// 인자 값으로는 각 member, modelandview, session 활용
	public String selectOneMember(MemberVO mVO, HttpSession session) {
		// selectOneMember를 mVO로 새로 정의
		mVO = MemberService.selectOneMember(mVO);
		// 입력한 mid, mpw를 userId로 저장한다.
		session.setAttribute("userId", mVO);
		// 만약 mVO가 null이라면
		// 즉 로그인이 되지 않았다면
		if(mVO == null) {
			// login.jsp로 다시 돌아가게끔 한다.
			return "redirect:login.jsp";
		} else {
			// 로그인이 되었다면
			// 로그인이 된 아이디를 새로 member라는 객체에 다시금 저장한다.
			session.setAttribute("member", mVO);
			// 이후 main.do로 이동
			return "main.do";
		}
	}
	@RequestMapping("/logout.do")
	// 로그아웃시에는 이전과 동일하게 session내 값을 invalidate해준다.
	public String logout(HttpSession session) {
		
		session.invalidate();
		// login.jsp로 다시 돌아간다.
		// 이때 알 수 있는 것 
		// VR의 default가 forward 방식이구나
		return "redirect:login.do";
		
	}
		// signin.do를 읽으면 이 Controller를 실행한다.
		@RequestMapping(value="signin.do",method=RequestMethod.POST)
		public String signIn(MemberVO mVO) {
			// insertMember 메소드 실행
			MemberService.insertMember(mVO);
			// login.jsp로 완료 후 이동
			return "login.jsp";
		}
		// update.do를 읽으면 이 Controller를 수행
		@RequestMapping(value="/updateM.do",method=RequestMethod.POST)
		public String updateM(@ModelAttribute("userId")MemberVO mVO) {
			// update메소드 실행
			MemberService.updateMember(mVO);
			// update 종료시 main으로
			return "login.do";
			
		}
		// mypage.do 라는 do를 받으면 위 컨트롤러를 수행한다.
		@RequestMapping(value="mypage.do",method=RequestMethod.GET)
		public String mypage(MemberVO mVO, HttpSession session) {
			// session에 있는 userId를 mVO에 다시 저장
			// 즉 로그인 당시 mid, mpw를 불러온다.
			mVO = (MemberVO)session.getAttribute("userId");
			// 바뀐 name을 보여주기 위해 & mid
			mVO=MemberService.selectOneMember(mVO);
			// 바꾼 mpw, mname을 jsp에 보여주기위해 member에 바뀐 값을 다시 저장
			return "mypage.jsp";
		}
		@RequestMapping(value = "deleteM.do")
		public String deleteM(@ModelAttribute("userId") MemberVO mVO, Model model) {
			MemberService.deleteMember(mVO);
			System.out.println("delete안됨" + mVO);
			return "login.do";
		}
	
}
