package com.lxc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.lxc.base.LinkDataBase;
import com.lxc.entity.Garage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FindServlet
 * 
 * 查询操作 查询所有信息
 */
@WebServlet("/FindServlet")
public class FindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FindServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * 在这里得到所有的数据库garage js信息
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String carId = request.getParameter("carId");
		int pn = Integer.parseInt(request.getParameter("pn"));
		int rn = Integer.parseInt(request.getParameter("rn"));
		LinkDataBase ldb = new LinkDataBase();
		List<Garage> list = ldb.getListByCarId(carId,pn,rn);	//所有的数据
		int total = ldb.count();	//所有数据
		
		Map map = new HashMap();
		map.put("isOk", true);
		map.put("messages", list);
		map.put("total",total);
		String json = new Gson().toJson(map);
		response.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
