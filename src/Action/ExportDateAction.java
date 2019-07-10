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
         //��ʼҳ       
        //@RequestMapping("/index")
        public String login() {
            return "index";
        }
        
         // ����Excel
         @Override
		protected void service(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			// ���õ����ı����ʽ���˴�ͳһΪUTF-8
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            // ���õ����ļ�������
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String("�û���¼��Ϣ.xls".getBytes(), "iso-8859-1"));
            UserService us = new UserServiceiImpl();
            
    		List<User> personList = us.getAllUser();
          
          
            // ʵ��Ӧ��������ط����жϻ�ȡ�����ݣ����û�ж�Ӧ�������򲻵������������2000������ֻ����2000��
            if (personList.size() == 0) {
                PrintWriter print = response.getWriter();
                print.write("û����Ҫ���������ݣ�");
               
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
            	
                System.out.println("���ݵ����б����쳣��");
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
			    request.setAttribute("getFileMsg", "û�з�����������Ϣ��");
		 }
		String n="";
	    n = listUser.get(0).getUserName() + "�İ汾�б�";
	 // ��session��ɾ��saveExcelMsg����
	    request.getSession().removeAttribute("saveExcelMsg");
	    // ����һ�������
	    ServletOutputStream sos = null;
	 
	    // ����һ��������
	    HSSFWorkbook wb = new HSSFWorkbook();
	    // ����һ��������
	    HSSFSheet sheet = null;
	    if (key != "") {
	      sheet = wb.createSheet(verList.get(0).getPrdName() + "�İ汾��Ϣ");
	    } else {
	      sheet = wb.createSheet("��Ʒ�汾��Ϣ");
	    }

			      

		
	}
*/
	
	

