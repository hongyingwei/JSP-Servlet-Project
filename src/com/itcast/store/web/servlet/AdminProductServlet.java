package com.itcast.store.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.itcast.store.domain.Category;
import com.itcast.store.domain.PageModel;
import com.itcast.store.domain.Product;
import com.itcast.store.service.CategoryService;
import com.itcast.store.service.ProductService;
import com.itcast.store.service.serivceImp.CategoryServiceImp;
import com.itcast.store.service.serivceImp.ProductServiceImp;
import com.itcast.store.utils.MyBeanUtils;
import com.itcast.store.utils.UUIDUtils;
import com.itcast.store.utils.UploadUtils;
import com.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminProductServlet
 */
public class AdminProductServlet extends BaseServlet {
	//按分页查看正在销售商品
	public String findAllUpProductsWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int curNum = Integer.parseInt(request.getParameter("currentPageNum"));
		ProductService productService = new ProductServiceImp();
		PageModel pm =productService.findAllUpProductsWithPage(curNum);
		request.setAttribute("page", pm);
		return "/admin/product/list.jsp";
	}
	
	//跳转到增加商品页面
	public String addProductUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CategoryService service = new CategoryServiceImp();
		List<Category> list = service.getAllCats();
		int pflag = Integer.parseInt(request.getParameter("pflag"));
		
		request.setAttribute("allCats", list);
		request.setAttribute("pflag", pflag);
		return "/admin/product/add.jsp";
	}
	//增加商品到数据库
	public String addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//存储表单中数据
		Map<String,String> map=new HashMap<String,String>();
		//携带表单中的数据向servcie,dao
		Product product=new Product();
		int pflag = 1;
		try {
			//利用req.getInputStream();获取到请求体中全部数据,进行拆分和封装
			DiskFileItemFactory fac=new DiskFileItemFactory();
			ServletFileUpload upload=new ServletFileUpload(fac);
			List<FileItem> list = upload.parseRequest(request);
			//遍历集合
		
			for (FileItem item : list) {
				if(item.isFormField()){
					//如果当前的FileItem对象是普通项
					//将普通项上name属性的值作为键,将获取到的内容作为值,放入MAP中
					// {username<==>tom,password<==>1234}
					if(item.getFieldName().equals("pflag")) {
						pflag = Integer.parseInt(item.getString("utf-8"));
						continue;
					}
					map.put(item.getFieldName(), item.getString("utf-8"));
				}else{
					//如果当前的FileItem对象是上传项
					
					//获取到原始的文件名称
					String oldFileName=item.getName();
					System.out.println(oldFileName);
					char m = oldFileName.charAt(0);
					if(m=='c' || m=='d')
						m='1';
					//获取到要保存文件的名称,改变文件名
					String newFileName=UploadUtils.getUUIDName(oldFileName);
					//通过FileItem获取到输入流对象,通过输入流可以获取到图片二进制数据
					InputStream is=item.getInputStream();
					//获取到当前项目下products/3下的真实路径
					//D:\tomcat\tomcat71_sz07\webapps\store\products\1
					String realPath=getServletContext().getRealPath("/products/" + m);
					//String dir=UploadUtils.getDir(oldFileName); // /f/e/d/c/4/9/8/4
					//String path=realPath+"\\" + newFileName; //D:\tomcat\tomcat71_sz07\webapps\store_v5\products\3/f/e/d/c/4/9/8/4
					//内存中声明一个目录
					
					File newDir=new File(realPath);
					if(!newDir.exists()){
						newDir.mkdirs();
					}
					
					//在服务端创建一个空文件(后缀必须和上传到服务端的文件名后缀一致)
					File finalFile=new File(newDir,newFileName);
					if(!finalFile.exists()){
						finalFile.createNewFile();
					}
					//建立和空文件对应的输出流
					OutputStream os=new FileOutputStream(finalFile);
					//将输入流中的数据刷到输出流中
					IOUtils.copy(is, os);
					//释放资源
					IOUtils.closeQuietly(is);
					IOUtils.closeQuietly(os);
					//向map中存入一个键值对的数据 userhead<===> /image/11.bmp
					// {username<==>tom,password<==>1234,userhead<===>image/11.bmp}
					map.put("pimage", "products/"+m+"/"+newFileName);
				}
			}

			
			//利用BeanUtils将MAP中的数据填充到Product对象上
			BeanUtils.populate(product, map);
			product.setPid(UUIDUtils.getId());
			product.setPdate(new Date());
			product.setPflag(pflag);
			
			//调用servcie_dao将user上携带的数据存入数据仓库,重定向到查询全部商品信息路径
			ProductService ProductService=new ProductServiceImp();
			ProductService.saveProduct(product);
			if(pflag==1) {
				response.sendRedirect("/store/AdminProductServlet?method=findAllUpProductsWithPage&currentPageNum=1");
			}else if(pflag==0) {
				response.sendRedirect("/store/AdminProductServlet?method=findPushDownProduct&currentPageNum=1");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//跳转到修改商品界面
	public String editProductUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid = request.getParameter("pid");
		String currentPageNum = request.getParameter("currentPageNum");
		ProductService service = new ProductServiceImp();
		Product product = service.getProductById(pid);
		CategoryService service1 = new CategoryServiceImp();
		List<Category> list = service1.getAllCats();
		request.setAttribute("allCats", list);
		request.setAttribute("product", product);
		request.setAttribute("currentPageNum", currentPageNum);
		return "/admin/product/edit.jsp";
	}
	//修改商品
	public String editProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String[]> map = request.getParameterMap();
		String currentPageNum = request.getParameter("currentPageNum");
		/*
		 * for(String list: map.keySet()){ for(String x: map.get(list)) {
		 * System.out.print(x); } System.out.println(); }
		 */
		Product product = new Product();
		//设置日期
		MyBeanUtils.populate(product, map); //把map转换bean对象
		ProductService service = new ProductServiceImp();
		service.editProduct(product);
		response.sendRedirect("/store/AdminProductServlet?method=findPushDownProduct&currentPageNum="+currentPageNum);
		return null;
	}
	
	//让商品下架操作
	public String deleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid = request.getParameter("pid");
		int curNum = Integer.parseInt(request.getParameter("currentPageNum"));
		ProductService service = new ProductServiceImp();
		service.DownProduct("1", pid); //1为下架
		response.sendRedirect("/store/AdminProductServlet?method=findAllUpProductsWithPage&currentPageNum="+curNum);
		return null;
	}
	
	//让下架商品展示
	public String findPushDownProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int curNum = Integer.parseInt(request.getParameter("currentPageNum"));
		ProductService productService = new ProductServiceImp();
		PageModel pm =productService.findPushDownProduct(curNum);
		request.setAttribute("page", pm);
		return "/admin/product/pushDown_list.jsp";
	}
	//让商品上架
	public String upProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid = request.getParameter("pid");
		int curNum = Integer.parseInt(request.getParameter("currentPageNum"));
		ProductService service = new ProductServiceImp();
		service.DownProduct("0", pid); //0为上架
		response.sendRedirect("/store/AdminProductServlet?method=findPushDownProduct&currentPageNum="+curNum);
		return null;
	}
	//findProductsByCategory
	//通过分类cid查找所有分类的商品
	public String findProductsByCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		int currentPageNum = Integer.parseInt(request.getParameter("currentPageNum"));
		ProductService service = new ProductServiceImp();
		PageModel list = service.findProductsByCategory(cid, currentPageNum);
		request.setAttribute("page", list);
		return "/admin/product/list.jsp";
	}
}
