package ru.sds.calculator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public MainServlet() {
       
    }
   
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	
    	
    	String param1 = request.getParameter("p1");
    	String param2 = request.getParameter("p2");
    	String action = request.getParameter("action");
    	double p1=0;
    	double p2=0;
    	String operation = "";
    	
    	
    	if(param1!=null) {
    	 p1= Double.valueOf(param1);
    	}
    	
    	if(param2!=null) {
       	 p2= Double.valueOf(param2);
       	}
    	if(action!=null) {
          	 operation = action;
          	}
    	
    	
    	
    	response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<body>");
		out.print("<h1>Programm calculator</h1>");
		List<String> arStr = null;
		
		try {
			//СЧИТЫВАНИЕ ПАРАМЕТРОВ
			out.print("<h1>Переданный параметр p1= " +param1+"</h1>");
			out.print("<h1>Переданный параметр p2= " +param2+"</h1>");
			out.print("<h1>Переданный параметр action= " +action+"</h1>");
			
			//ПОЛУЧЕНИЕ ОТ ТОМКАТА ИЛИ СОЗДАНИЕ ИМ СЕССИИ
			HttpSession session = request.getSession(true);
			
			
		
			if(!session.isNew()) {
				arStr = (List<String>)session.getAttribute("operations");
			}
			
			if (arStr == null) {
				arStr = new ArrayList<String>();
			}
			
			out.print("<h3>Всего операций!!! = " +arStr.size()+"</h3>");
			
			if(operation.equals("ymn") || (arStr.size()>0)) {
				if(operation.equals("ymn")) {
					double p4=p1*p2;
					arStr.add(p1+" * "+p2+"= "+p4);
					session.setAttribute("operations", arStr);
				}
				
				
				out.print("<h1>ID вашей сессии = " +session.getId()+"</h1>");
				out.print("<h3>Всего операций = " +arStr.size()+"</h3>");
				out.print(arStr);
				//out.print((List<String>)session.getAttribute("operations"));
				for( String opera: arStr) {
					out.print("<h3>" + opera+"/h3");
				}
	
			}
			if(operation.equals("del")) {
				double p5=p1/p2;
				out.print("<h1>p1 / p2= " +p5+"</h1>");
			}
			if(operation.equals("min")) {
				double p6=p1-p2;
				out.print("<h1>p1 - p2= " +p6+"</h1>");
			}
			if(operation.equals("sum")) {
				double p7=p1+p2;
				out.print("<h1>p1 + p2= " +p7+"</h1>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print(e.getMessage());
			out.print("что то не так, сорянчик");
		}
		finally {
			out.print("<h3>Всего операций!!! = " +arStr.size()+"</h3>");
			out.print("<h1><a href=\"index.html\">Назад</a></h1>");
			out.print("</body>");
			out.print("</html>");
			out.close();
		}
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
