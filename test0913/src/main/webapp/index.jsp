<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


	<!-- 데이터를 보내야하는 것이 1개 이상이라면 C를 방문해야하기 때문에 .jsp이동을 하면 안된다. -->
<%
	response.sendRedirect("login.do");
%>