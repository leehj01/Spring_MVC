package hello.servlet.basic.request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * http-요청 데이터 - get 쿼리 파라미터
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 * 2. 동일한 파라미터 전송가능
 * http://localhost:8080/request-param?username=hello&username=kim&age=20
 */

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[전체 파라미터 조회 ] - start ");

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
        System.out.println(paramName + "=" + request.getParameter(paramName));
        }


        // 최근 방식
        //request.getParameterNames().asIterator().forEachRemaining(paramName -> System.out.println(paramName +
        //"=" + request.getParameter(paramName)));

        System.out.println("[전체 파라미터 조회 ] - end" );
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        //2. 동일한 파라미터 전송가능 - 내부 우선순위에서 먼저 잡히는 경우가 나옴
        System.out.println("[이름이 같은 복수 파라미터 조회]");
        System.out.println("request.getParameterValues(username)");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames){
            System.out.println("name = " + name);
        }

        response.getWriter().write("ok");  // 웹브라우저가 허전하니깐 쓰는것..
    }
}
