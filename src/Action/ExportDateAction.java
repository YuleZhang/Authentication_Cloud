package Action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.ExportUtil;

import service.UserService;
import service.UserServiceiImpl;
import entity.User;

public class ExportDateAction extends HttpServlet {
         //起始页       
        //@RequestMapping("/index")
        public String login() {
            return "index";
        }
        
         // 导出Excel
         @Override
		protected void service(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			// 设置导出的编码格式，此处统一为UTF-8
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            // 设置导出文件的名称
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String("用户登录信息.xls".getBytes(), "iso-8859-1"));
            UserService us = new UserServiceiImpl();
            
    		List<User> personList = us.getAllUser();
          
          
            // 实际应用中这个地方会判断获取的数据，如果没有对应的数据则不导出，如果超过2000条，则只导出2000条
            if (personList.size() == 0) {
                PrintWriter print = response.getWriter();
                print.write("没有需要导出的数据！");
               
            }
            ServletOutputStream out = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                ExportUtil dataExportUtil = new ExportUtil();
                bis = new BufferedInputStream(dataExportUtil.getExcelStream(personList));
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
                bos.flush();
            } catch (final IOException e) {
            	
                System.out.println("数据导出列表导出异常！");
            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            }
		}
       
    }
/*	public void getExcel(HttpServletRequest request,HttpServletResponse response,Integer key){
		response.setContentType("application/vnd.ms-excel");
		//List<User> listUser = prdUserSer.getAllPrdVersion(key);
		UserService uSer = new UserServiceiImpl();
		List<User> listUser = uSer.getAllUser();
		 if (listUser.size() == 0) {
			    request.setAttribute("getFileMsg", "没有符合条件的信息！");
		 }
		String n="";
	    n = listUser.get(0).getUserName() + "的版本列表";
	 // 从session中删除saveExcelMsg属性
	    request.getSession().removeAttribute("saveExcelMsg");
	    // 定义一个输出流
	    ServletOutputStream sos = null;
	 
	    // 创建一个工作簿
	    HSSFWorkbook wb = new HSSFWorkbook();
	    // 创建一个工作表
	    HSSFSheet sheet = null;
	    if (key != "") {
	      sheet = wb.createSheet(verList.get(0).getPrdName() + "的版本信息");
	    } else {
	      sheet = wb.createSheet("产品版本信息");
	    }

			      

		
	}
*/
	
	

