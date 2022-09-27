package com.hjm.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.hjm.biz.member.MemberVO;
import com.hjm.biz.member.impl.MemberDAO;

/*
package com.kim.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	String handleRequest(HttpServletRequest request, HttpServletResponse response);
}
*/
public class LoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberVO mVO=new MemberVO();
		mVO.setMid(request.getParameter("mid"));
		mVO.setMpw(request.getParameter("mpw"));
		
		MemberDAO mDAO=new MemberDAO();
		mVO=mDAO.selectOneMember(mVO);
		
		ModelAndView mav=new ModelAndView();
		if(mVO==null) {
			mav.setViewName("redirect:login.jsp");
		}
		else {
			HttpSession session=request.getSession();
			session.setAttribute("member", mVO);
			
			mav.setViewName("redirect:main.do");
		}
		return mav;
	}

	/*
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String mid=request.getParameter("mid");
		String mpw=request.getParameter("mpw");
		MemberVO mVO=new MemberVO();
		mVO.setMid(mid);
		mVO.setMpw(mpw);
		
		MemberDAO mDAO=new MemberDAO();
		mVO=mDAO.selectOneMember(mVO);
		
		if(mVO==null) {
			return "login"; // VR�� .jsp�� �߰��ϱ⶧���� �����ؼ� ��ȯ
		}
		else {
			HttpSession session=request.getSession();
			session.setAttribute("member", mVO);
			
			return "main.do";
		}
	}
	*/

}
