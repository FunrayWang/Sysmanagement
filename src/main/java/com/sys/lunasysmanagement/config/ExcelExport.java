package com.sys.lunasysmanagement.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * excel表导出操作
 *
 * @author wangfangrui
 * @date 2019/8/23 16:53
 */
@Slf4j
@Service
public class ExcelExport {

    public <T> T export(String[] columnName, String sheetName, List<T> list, HttpServletResponse response) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);

        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 12);

        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setWrapText(true);

        HSSFRow row2 = sheet.createRow(0);
        for (int i = 0; i < columnName.length; i++) {
            HSSFCell cell0 = row2.createCell(i);
            cell0.setCellStyle(style);
            cell0.setCellValue(columnName[i]);
            sheet.autoSizeColumn((short) i);
        }

        int listCount = 0;
        for (T Obj : list) {
            HSSFRow rowx = sheet.createRow(listCount + 1);
            int i = 0;
            for (Field field : Obj.getClass().getDeclaredFields()) {
                try {
                    HSSFCell cell00 = rowx.createCell(i);
                    cell00.setCellStyle(style);
                    field.setAccessible(true);
                    if (field.get(Obj) != null) {
                        cell00.setCellValue(field.get(Obj).toString());
                    } else {
                        cell00.setCellValue("");
                    }
                    i++;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            listCount++;
        }
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        //暂定输出文件名为report.xls
        response.setHeader("Content-disposition", "attachment; filename=report.xls");
        response.setContentType("application/msexcel");

        wb.write(output);
        output.close();
        return null;
    }

}
