package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK);  // http 응답 코드를 넣을 수 있음 - 200이라고 적는것보다. 저렇게 적는게 좋음

        // [response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8"); // 이걸 지정해줘야 한글이 나옴
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header","hello");

        PrintWriter writer = response.getWriter();
        writer.println("ok-response");

        // [message body ]
        PrintWriter writer1 = response.getWriter();
        writer1.println("ok-response !");

    }

    // content 편의 메서드
    private void content(HttpServletResponse response){
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
//        response.setContentLength(2); // 생략하면 자동생성 - 문자의 길이를 지정
    }

    // 쿠키 편의 메서드
    private  void cookie(HttpServletResponse response){
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600"); 아래와같은 코드
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 600초
        response.addCookie(cookie);
    }

    // redirect 편의 메서드
    private void redirect(HttpServletResponse response) throws IOException{
//        response.setStatus(HttpServletResponse.SC_FOUND); // 302
//        response.setHeader("Location","/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html"); // 여기로 보내버림 - 위의 두개 코드와 동일
    }
}
