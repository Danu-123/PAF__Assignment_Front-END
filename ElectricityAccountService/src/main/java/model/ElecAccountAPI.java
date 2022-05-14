package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ElecAccountAPI
 */
@WebServlet("/ElecAccountAPI")
public class ElecAccountAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ElecAccount elec = new ElecAccount();
       
     //convert request parameters to a map
       private static Map getParasMap(HttpServletRequest request) {
   		Map<String, String> map = new HashMap<String, String>();

   		try {
   			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
   			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
   			scanner.close();

   			String[] params = queryString.split("&");

   			for (String param : params) {
   				String[] p = param.split("=");
   				map.put(p[0], p[1]);
   			}
   		} catch (Exception e) {

   		}

   		return map;
   	}
       
    public ElecAccountAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sending values to insert function
		
		String output = elec.addAccount(	request.getParameter("Account_number"),
				request.getParameter("Account_name"),
				request.getParameter("Premises_id"),
				request.getParameter("Cus_id"));
				
//sending the output to client
response.getWriter().write(output);
		
		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//parameter map
		Map paras = getParasMap(request);
		
		//getting values from the map and sending to update function
				String output = elec.updateAccount(	paras.get("hidAccIDSave").toString(),
													paras.get("Account_number").toString(),
													paras.get("Account_name").toString(),
													paras.get("Premises_id").toString(),
													paras.get("Cus_id").toString());
				
				//sending the output to client
				response.getWriter().write(output);
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//parameter map
		Map paras = getParasMap(request);
		
		
		//getting values from the map and sending to delete function
				String output = elec.deleteAccount(	paras.get("AccID").toString());
				
				//sending the output to client
				response.getWriter().write(output);
		
		
	}

}
