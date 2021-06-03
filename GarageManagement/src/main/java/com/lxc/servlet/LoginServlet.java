package com.lxc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.lxc.base.UserBase;
import com.lxc.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
@MultipartConfig
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public LoginServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pwd = request.getParameter("password");
		Map map = new HashMap();
		UserBase dao = new UserBase();
		User u = dao.getUserById(id);
		String jsonStr = null;
		if (u != null &&u.getPassword().equals(pwd)) {
			u.setPassword(null);
			request.getSession().setAttribute("loginUser", u);
			map.put("user", u);
			jsonStr = new Gson().toJson(map);
		}else {
			Map error = new HashMap();
			error.put("user","error");
			jsonStr = new Gson().toJson(error);
		}
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.close();
	}

}
