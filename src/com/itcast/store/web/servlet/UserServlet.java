package com.itcast.store.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.time.DateUtils;

import com.itcast.store.domain.User;
import com.itcast.store.service.userService;
import com.itcast.store.service.serivceImp.userServiceImp;
import com.itcast.store.utils.MailUtils;
import com.itcast.store.utils.MailUtils2;
import com.itcast.store.utils.UUIDUtils;
import com.itcast.store.utils.YanZhengCode;
import com.itcast.store.web.base.BaseServlet;

import net.sf.json.JSONObject;

/**
 * 用户（user）:系统与客户端交互的所有servlet服务
 */
public class UserServlet extends BaseServlet {
	//用户注册界面
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/jsp/register.jsp";
	}
	//用户注册账号
	public String userRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收表单数据
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		
		try {
			//设置时间
			DateConverter dt = new DateConverter();
			dt.setPattern("yyyy-MM-dd");
			
			ConvertUtils.register(dt, java.util.Date.class);
			
			BeanUtils.populate(user, map); //把map转换bean对象
			//或者为MyBeanUtils.populate(user, map)
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//随机生成uid	
		user.setUid(UUIDUtils.getCode());
		user.setState(0);
		//生产验证码
		user.setCode(YanZhengCode.achieveCode());
		//调用业务层注册功能
		userService  UserService = new userServiceImp();
		try {
			//发送邮件
			MailUtils2.sendMail(user.getEmail(), user.getCode());
			//注册成功，向用户邮箱发送信息，转跳到提示页面
			UserService.userRegist(user);
			request.setAttribute("msg", "用户注册成功，请前往邮箱，查看QQ邮箱获取激活码");
			return "/jsp/active.jsp";
		} catch (Exception e) {
			//注册失败，转跳到提示页面
			request.setAttribute("msg", "用户注册失败，请重新注册");
			return "/jsp/info.jsp";
			// TODO: handle exception
		}
	}
	//激活用户的servlet服务
	public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String code = request.getParameter("code");
			userService service = new userServiceImp();
			boolean flag = service.userActive(code);
			if(flag) {
				response.getWriter().print(1);
			}else {
				response.getWriter().print(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//用户登录界面
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}
	//用户登录servlet服务
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		try {
			userService userService = new userServiceImp();
			User user = userService.userLogin(username, password);
			request.getSession().setAttribute("loginUser", user);
			request.getSession().setMaxInactiveInterval(10*60);//设置session10分钟失效
			response.sendRedirect("/store/index.jsp"); //重定向
			return null;
		} catch (Exception e) {
			String msg = e.getMessage(); //获取异常输出的信息
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}
	}
	//退出登录
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//清除session
		request.getSession().invalidate();
		response.sendRedirect("/store/index.jsp");
		return null;
	}
	//ajax发起请求，效验用户名是否可用
	
	public void checkUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			String username = request.getParameter("username");
			userService service = new userServiceImp();
			boolean flag = service.checkUserName(username);
			if(!flag) {
				response.getWriter().print(1);//不存在
			}else {
				response.getWriter().print(2);//存在
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//从session中取出loginUser
	public void checkPersonal(HttpServletRequest request, HttpServletResponse response) throws Exception{
		User user = (User)request.getSession().getAttribute("loginUser");
		if(user.getBirthday()!=null)
			user.setBirthday(new java.util.Date(user.getBirthday().getTime()));//数据库里面是java.sql.date,所以要转化成java.util.data
		String json = JSONObject.fromObject(user).toString();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	}
	//修改用户信息
	public String changeUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		User user = new User();
		try {
			user.setUid(request.getParameter("uid"));
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("name"));
			user.setEmail(request.getParameter("email"));
			user.setTelephone(request.getParameter("telephone"));
			user.setSex(request.getParameter("sex"));
			
			user.setState(Integer.parseInt(request.getParameter("state")));
			user.setCode(request.getParameter("code"));
			if(request.getParameter("birthday")!="") {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatter.parse(request.getParameter("birthday"));
				user.setBirthday(date);
			}else {
				User u = (User) request.getSession().getAttribute("loginUser");
				user.setBirthday(u.getBirthday());
			}
			//request.getSession().removeAttribute("loginUser");//remove销毁某一个属性，而invalidate销毁所有的属性
			request.getSession().setAttribute("loginUser", user);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		userService userService = new userServiceImp();
		userService.changeUser(user);
		//response.sendRedirect("/store/jsp/personal.jsp"); //重定向
		return "/jsp/personal.jsp";
	}
}
