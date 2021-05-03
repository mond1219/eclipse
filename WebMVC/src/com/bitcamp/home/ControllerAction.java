package com.bitcamp.home;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/*.do")
public class ControllerAction extends HttpServlet {
   private static final long serialVersionUID = 1L;
    
   //맵핑주소와 실행할 Command객체를 보관할 맵
   HashMap<String, CommandService> map = new HashMap<String, CommandService>();
   
   
    public ControllerAction() {
        super();
    }

   public void init(ServletConfig config) throws ServletException {
      //doGet 실행전에 init 메소드 먼저실행
      
      //properties파일명을 web.xml에서 가져오기
      String propertiesFilename = config.getInitParameter("proConfig");
      
      Properties prop = new Properties(); // key: String, value : String 상태 = value를 객체타입으로 변환시켜야함
      try {
         FileInputStream fis = new FileInputStream(propertiesFilename);
         //urlMapping.properties파일의 내용을 읽어온다. properties객체(여기선 prop변수)로 대입
         prop.load(fis);
      }catch(Exception e) {
         System.out.println("properties 객체 생생 오류" + e.getMessage());
      }
      /////////////////////////////////////////////////////////////////////
      try {
         //properties의 키 목록 구하기
         Enumeration keyList = prop.propertyNames();
         
         while(keyList.hasMoreElements()) {
            //key에 대한 커맨드 클래스명을 가져온다
            String key = (String)keyList.nextElement();
            String commandName = prop.getProperty(key);// com.bitcamp.home.IndexCommand 변수로 넣어야 두개가나온다
            System.out.println(key + " = " + commandName);
            
            //문자열을 객체로 생성하여 Map추가
            Class classObject = Class.forName(commandName);
            //value 값을 구함
            CommandService service = (CommandService)classObject.getDeclaredConstructors()[0].newInstance();
            
            //맵에 key와 value를 담는다
            map.put(key, service);
         }
         
         
      }catch(Exception e) {
         System.out.println("프로퍼티의 내용을 맵객체로 변환에러" + e.getMessage());
      }
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //접속자의 url주소를 알아낸다.
      String uri = request.getRequestURI(); // /webMVC/index.do
      String ctx = request.getContextPath(); // //webMVC //클라이언트의 컨텍스트를 구함
      System.out.println("uri=" + uri);
      System.out.println("ctx=" + ctx);
      
      String urlMapping = uri.substring(ctx.length()); //      /index.do
      
      CommandService command = map.get(urlMapping);
      
      String viewFilename = command.processStart(request, response);
      
      //viewFile로 이동하기
      RequestDispatcher dispatcher = request.getRequestDispatcher(viewFilename);
      dispatcher.forward(request, response);
      
      System.out.println("doGet()");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      doGet(request, response);
   }

}