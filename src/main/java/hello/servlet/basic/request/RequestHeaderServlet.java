package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "reuestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override // public 이아니라, protected로 만들어야 한다.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.service(request, response);  // 이걸하면 -가 난다 왜인가? - HttpServlet에서 파라미터가 가져온다  상위 클래스 메서드 호출 .
        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);
        String method = request.getMethod(); // http 메서드 정보 알수있음 - 실무에서 잘 안씀
    }
    private void printStartLine(HttpServletRequest request){
            System.out.println("--- REQUEST-LINE - start 라인 출력---");
            System.out.println("request.getMethod() = " + request.getMethod()); //GET
            System.out.println("request.getProtocal() = " + request.getProtocol()); //  HTTP / 1.1
            System.out.println("request.getScheme() = " + request.getScheme()); //http
            // http://localhost:8080/request-header
            System.out.println("request.getRequestURL() = " + request.getRequestURL());
            // /request-test
            System.out.println("request.getRequestURI() = " + request.getRequestURI());
            //username=hi
            System.out.println("request.getQueryString() = " +
                    request.getQueryString());
            System.out.println("request.isSecure() = " + request.isSecure()); //https 사용 유무
            System.out.println("--- REQUEST-LINE - end ---");
            System.out.println();

        }
    //Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

         // header 정보를 가져오는 방식이 2가지가 있는데 이것은 옛날 방식임 - 8버전
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {   // 요소가 있으면
              String headerName = headerNames.nextElement(); // 요소를 꺼내고
            System.out.println(headerName + ": " + request.getHeader(headerName)); // 출력
        }

        // 요즘 스타일 -- 자바 11버전
//        request.getHeaderNames().asIterator().forEachRemaining(headerName -> System.out.println(
//               headerName + ":" + headerName ));
//        request.getHeader("원하는값");// 원하는값만 뽑을 수 있음
        System.out.println("--- Headers - end ---");
        System.out.println();
    }

        //Header 편리한 조회
        private void printHeaderUtils (HttpServletRequest request){
            System.out.println("--- Header 편의 조회 start ---");
            System.out.println("[Host 편의 조회]");
            System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더
            System.out.println("request.getServerPort() = " + request.getServerPort()); //Host 헤더
            System.out.println();

            System.out.println("[Accept-Language 편의 조회]");
            // getLocales 를 하면, locales 정보를 다 줄 수 있음.
//            request.getLocales().asIterator()
//                    .forEachRemaining(locale -> System.out.println("locale = " + locale));
            System.out.println("request.getLocale() = " + request.getLocale()); // 위에 중에서 가장 위에 있는것이 뽑힘 - 보통 이걸 사용
            System.out.println();

            System.out.println("[cookie 편의 조회]");
            if (request.getCookies() != null) { // 쿠키의 정보를 담아올 수 있음.
                for (Cookie cookie : request.getCookies()) {
                    System.out.println(cookie.getName() + ": " + cookie.getValue());
                }
            }

            System.out.println();
            System.out.println("[Content 편의 조회]");
            System.out.println("request.getContentType() = " +  request.getContentType());
            System.out.println("request.getContentLength() = " + request.getContentLength());
            System.out.println("request.getCharacterEncoding() = " +
                    request.getCharacterEncoding());
            System.out.println("--- Header 편의 조회 end ---");
            System.out.println();
    }

    private void printEtc(HttpServletRequest request) { System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " +   request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " +  request.getRemotePort()); //
        System.out.println();
        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " +  request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " +
                request.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " +
                request.getLocalPort()); //
        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }
}
