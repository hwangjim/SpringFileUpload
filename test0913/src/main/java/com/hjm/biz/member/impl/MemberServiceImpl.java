package com.hjm.biz.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjm.biz.member.MemberService;
import com.hjm.biz.member.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired // MemberDAO Ÿ���� ��ü�� �޸𸮿� �־������ DI(������ ����) ������!
	private MemberDAO2 memberDAO; // �ٽɷ����� ������ ��ü
	
	@Override
	public void insertMember(MemberVO vo) {
		memberDAO.insertMember(vo);
	}

	@Override
	public void deleteMember(MemberVO vo) {
		memberDAO.deleteMember(vo);
	}

	@Override
	public void updateMember(MemberVO vo) {
		memberDAO.updateMember(vo);
	}

	@Override
	public MemberVO selectOneMember(MemberVO vo) {
		if(vo.getMid().equals("timo")) {
			throw new IllegalArgumentException("[����ÿ���]");
		}
		return memberDAO.selectOneMember(vo);
	}

	@Override
	public List<MemberVO> selectAllMember(MemberVO vo) {
		return memberDAO.selectAllMember(vo);
	}

}
