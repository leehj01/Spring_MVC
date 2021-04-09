package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV1 implements ControllerV1 {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();

        request.setAttribute("members",members);

        String viewPath = "/WEB-INF/views/members.jsp"; // 항상 이코드가 중복됨.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); // 담아서 보내면, 이제 뷰로직이 실행이 됨.  - 항상 이코드가 반복됨
        dispatcher.forward(request, response);
    }
}
