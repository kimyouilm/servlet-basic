package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
HTTP 요청 메시지를 개발자가 직접 파싱해서 사용해도 되지만, 매우 불편할 것이다. 서블릿은 개발자가
HTTP 요청 메시지를 편리하게 사용할 수 있도록 개발자 대신에 HTTP 요청 메시지를 파싱한다. 그리고 그
결과를 HttpServletRequest 객체에 담아서 제공한다

로컬에서 테스트하면 IPv6 정보가 나오는데, IPv4 정보를 보고 싶으면 다음 옵션을 VM options에
넣어주면 된다.
> -Djava.net.preferIPv4Stack=true
 */
@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    printStartLine(request);
    printHeaders(request);
    printHeaderUtils(request);
    printEtc(request);
  }

  private void printStartLine(HttpServletRequest request) {
    System.out.println("--- REQUEST-LINE - start ---");
    System.out.println("request.getMethod() = " + request.getMethod()); //GET
    System.out.println("request.getProtocal() = " + request.getProtocol()); // HTTP/1.1
    System.out.println("request.getScheme() = " + request.getScheme()); //http
    // http://localhost:8080/request-header
    System.out.println("request.getRequestURL() = " + request.getRequestURL());
    // /request-test
    System.out.println("request.getRequestURI() = " + request.getRequestURI());
    //username=hi
    System.out.println("request.getQueryString() = " + request.getQueryString());
    System.out.println("request.isSecure() = " + request.isSecure()); //https 사용 유무
    System.out.println("--- REQUEST-LINE - end ---");
    System.out.println();
  }

  //Header 모든 정보
  private void printHeaders(HttpServletRequest request) {
    System.out.println("--- Headers - start ---");
/*
 Enumeration<String> headerNames = request.getHeaderNames();
 while (headerNames.hasMoreElements()) {
 String headerName = headerNames.nextElement();
 System.out.println(headerName + ": " + request.getHeader(headerName));
 }
*/
    request.getHeaderNames().asIterator()
        .forEachRemaining(headerName -> System.out.println(headerName +
            ": " + request.getHeader(headerName)));
    // 헤더 하나만 조회하고싶을때 원하는 헤더 이름 넣으면 값을 꺼낼수있따
//    request.getHeader("host");
    System.out.println("--- Headers - end ---");
    System.out.println();
  }

  //Header 편리한 조회
  private void printHeaderUtils(HttpServletRequest request) {
    System.out.println("--- Header 편의 조회 start ---");
    System.out.println("[Host 편의 조회]");
    System.out.println("request.getServerName() = " +
        request.getServerName()); //Host 헤더
    System.out.println("request.getServerPort() = " +
        request.getServerPort()); //Host 헤더
    System.out.println();
    System.out.println("[Accept-Language 편의 조회]");
    request.getLocales().asIterator()
        .forEachRemaining(locale -> System.out.println("locale = " +
            locale));
    System.out.println("request.getLocale() = " + request.getLocale());
    System.out.println();
    System.out.println("[cookie 편의 조회]");
    if (request.getCookies() != null) {
      for (Cookie cookie : request.getCookies()) {
        System.out.println(cookie.getName() + ": " + cookie.getValue());
      }
    }
    System.out.println();
    System.out.println("[Content 편의 조회]");
    System.out.println("request.getContentType() = " +
        request.getContentType());
    System.out.println("request.getContentLength() = " +
        request.getContentLength());
    System.out.println("request.getCharacterEncoding() = " +
        request.getCharacterEncoding());
    System.out.println("--- Header 편의 조회 end ---");
    System.out.println();
  }

  //기타 정보
  private void printEtc(HttpServletRequest request) {
    System.out.println("--- 기타 조회 start ---");
    System.out.println("[Remote 정보]");
    System.out.println("request.getRemoteHost() = " +
        request.getRemoteHost()); //
    System.out.println("request.getRemoteAddr() = " +
        request.getRemoteAddr()); //
    System.out.println("request.getRemotePort() = " +
        request.getRemotePort()); //
    System.out.println();
    System.out.println("[Local 정보]");
    System.out.println("request.getLocalName() = " +
        request.getLocalName()); //
    System.out.println("request.getLocalAddr() = " +
        request.getLocalAddr()); //
    System.out.println("request.getLocalPort() = " +
        request.getLocalPort()); //
    System.out.println("--- 기타 조회 end ---");
    System.out.println();
  }
}
