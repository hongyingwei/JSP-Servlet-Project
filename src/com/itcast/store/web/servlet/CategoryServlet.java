package com.itcast.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.store.domain.Category;
import com.itcast.store.service.CategoryService;
import com.itcast.store.service.serivceImp.CategoryServiceImp;
import com.itcast.store.utils.JedisUtils;
import com.itcast.store.web.base.BaseServlet;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends BaseServlet {

	public void findAllCats(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			CategoryService CategoryService = new CategoryServiceImp(); 
			List<Category> list = CategoryService.getAllCats(); // 将全部分类转换成JSON格式的数据 
			 String json = JSONArray.fromObject(list).toString();
			 // 将全部分类信息响应到客户端 // 告诉浏览器本次响应的数据是json格式的字符串
			  response.setContentType("application/json;charset=utf-8");
			 response.getWriter().print(json); // 你把数据响应回jsp页面中
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
