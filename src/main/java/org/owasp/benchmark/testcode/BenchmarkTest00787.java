/**
* OWASP Benchmark Project v1.2beta
*
* This file is part of the Open Web Application Security Project (OWASP)
* Benchmark Project. For details, please see
* <a href="https://www.owasp.org/index.php/Benchmark">https://www.owasp.org/index.php/Benchmark</a>.
*
* The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
* of the GNU General Public License as published by the Free Software Foundation, version 2.
*
* The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
* even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* @author Nick Sanidas <a href="https://www.aspectsecurity.com">Aspect Security</a>
* @created 2015
*/

package org.owasp.benchmark.testcode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BenchmarkTest00787")
public class BenchmarkTest00787 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	
		String[] values = request.getParameterValues("vector");
		String param;
		if (values != null && values.length > 0)
		  param = values[0];
		else param = "";
		
		
		String bar = "safe!";
		java.util.HashMap<String,Object> map40716 = new java.util.HashMap<String,Object>();
		map40716.put("keyA-40716", "a_Value"); // put some stuff in the collection
		map40716.put("keyB-40716", param); // put it in a collection
		map40716.put("keyC", "another_Value"); // put some stuff in the collection
		bar = (String)map40716.get("keyB-40716"); // get it back out
		bar = (String)map40716.get("keyA-40716"); // get safe value back out
		
		
		String sql = "INSERT INTO users (username, password) VALUES ('foo','"+ bar + "')";
				
		try {
			java.sql.Statement statement = org.owasp.benchmark.helpers.DatabaseHelper.getSqlStatement();
			int count = statement.executeUpdate( sql, java.sql.Statement.RETURN_GENERATED_KEYS );
            org.owasp.benchmark.helpers.DatabaseHelper.outputUpdateComplete(sql, response);
		} catch (java.sql.SQLException e) {
			if (org.owasp.benchmark.helpers.DatabaseHelper.hideSQLErrors) {
        		response.getWriter().println("Error processing request.");
        		return;
        	}
			else throw new ServletException(e);
		}
	}
}