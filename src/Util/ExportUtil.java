package Util;

import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



import entity.User;

public class ExportUtil {
	
	 
     // 设置导出Excel的表名     
    public String getSheetName() {
        return "测试导出数据";
    }
    // 设置导出Excel的列名
    public String getSheetTitleName() {
        return "序号,账号,密码,手机号,邮箱,用户来源,状态,最后一次登录ip,注册日期,最后一次登录时间";
    }
    
     // 创建 sheet 的第一行,标题行     
    private void createSheetTitle(HSSFSheet sheet, String strTitle) {
        HSSFRow row = sheet.createRow(0); // 创建该表格(sheet)的第一行
        sheet.setDefaultColumnWidth(4);
        HSSFCell cell = null;
        String[] strArray = strTitle.split(",");
        for (int i = 0; i < strArray.length; i++) {
            cell = row.createCell(i); // 创建该行的第一列
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(strArray[i]));
        }
    }
    @SuppressWarnings("resource")
    public InputStream getExcelStream(List<User> userlist) throws IOException {
        // 创建一个 Excel 文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建一个表格 Sheet
        HSSFSheet sheet = wb.createSheet(this.getSheetName());
        // 创建 sheet 的第一行,标题行
        // 行号从0开始计算
        this.createSheetTitle(sheet, this.getSheetTitleName());
        // 设置 sheet 的主体内容
        this.createSheetBody(userlist, sheet);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        wb.write(output);
        byte[] ba = output.toByteArray();
        InputStream is = new ByteArrayInputStream(ba);
        return is;
    }

    private void createSheetBody(List<User> personList, HSSFSheet sheet) {
        if (personList == null || personList.size() < 1) {
            return;
        }
        // 表格(sheet) 的第二行, 第一行是标题, Excel中行号, 列号 是由 0 开始的
        int rowNum = 1;
        HSSFCell cell = null;
        HSSFRow row = null;
        for (Iterator it = (Iterator) personList.iterator(); it.hasNext(); rowNum++) {
            User us = (User) it.next();
            if (us == null)
                break;
            row = sheet.createRow(rowNum);
            int i = 0;
            cell = row.createCell(i++);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(rowNum + ""));
            cell = row.createCell(i++); // name
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(us.getUserName()));
            cell = row.createCell(i++); // password
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(us.getPassword()));
            cell = row.createCell(i++); // phone
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(us.getPhoneNum()));
            cell = row.createCell(i++); // email
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(us.getEmail()));
            cell = row.createCell(i++); // email
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(us.getSystemSource()));
            cell = row.createCell(i++); // status
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(us.getStatus()));
            cell = row.createCell(i++); // 
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(us.getFinalip()));
            cell = row.createCell(i++); //
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(us.getFinalTime()));
            cell = row.createCell(i++); // 
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(us.getRegisterDate()));
        }
    }
	
	
    


}
