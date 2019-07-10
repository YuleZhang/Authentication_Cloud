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
	
	 
     // ���õ���Excel�ı���     
    public String getSheetName() {
        return "���Ե�������";
    }
    // ���õ���Excel������
    public String getSheetTitleName() {
        return "���,�˺�,����,�ֻ���,����,�û���Դ,״̬,���һ�ε�¼ip,ע������,���һ�ε�¼ʱ��";
    }
    
     // ���� sheet �ĵ�һ��,������     
    private void createSheetTitle(HSSFSheet sheet, String strTitle) {
        HSSFRow row = sheet.createRow(0); // �����ñ��(sheet)�ĵ�һ��
        sheet.setDefaultColumnWidth(4);
        HSSFCell cell = null;
        String[] strArray = strTitle.split(",");
        for (int i = 0; i < strArray.length; i++) {
            cell = row.createCell(i); // �������еĵ�һ��
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(strArray[i]));
        }
    }
    @SuppressWarnings("resource")
    public InputStream getExcelStream(List<User> userlist) throws IOException {
        // ����һ�� Excel �ļ�
        HSSFWorkbook wb = new HSSFWorkbook();
        // ����һ����� Sheet
        HSSFSheet sheet = wb.createSheet(this.getSheetName());
        // ���� sheet �ĵ�һ��,������
        // �кŴ�0��ʼ����
        this.createSheetTitle(sheet, this.getSheetTitleName());
        // ���� sheet ����������
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
        // ���(sheet) �ĵڶ���, ��һ���Ǳ���, Excel���к�, �к� ���� 0 ��ʼ��
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
