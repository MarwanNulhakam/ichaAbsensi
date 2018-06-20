/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import database.Toolbox;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Heni Edrianti
 */
public class ExcelExporter {
    public final int EXPORTER_FIRST_ROW = 9;
    public final String TEMPLATE__URL = "\\file\\ReportTemplate.xlsx";
    
    //flag
    private boolean workbookIsPrepared = false;
    private String fileURL;
    
    public ExcelExporter(){
        workbook = new XSSFWorkbook();
    }
    public ExcelExporter(Object[]identity){
        this();
        insertUserIdentity(identity);
    }
    public ExcelExporter(Object[]identity,Object[][]data){
        this(identity);
        writeToFile(data);
    }
    public ExcelExporter(String file,Object[]identity,Object[][]data){
        this();
        createTemplateOn(file);
        insertUserIdentity(identity);
        writeToFile(data);
    }
    
    private void createTemplateOn(String file){
        try {
            java.io.File f = new java.io.File(file);
            FileUtils.copyFileToDirectory(new java.io.File(TEMPLATE__URL), f);
            fileURL = f.getName();
        } catch (IOException ex) {
            database.Toolbox.alert("Failed to create export file");
        }
    }
    
    public void prepareWorkbook(Object[][]content){
        workbookIsPrepared = true;
        sheet = workbook.getSheetAt(0);
        row = sheet.getRow(0);
        cell = row.getCell(0);
    }
    private void insertUserIdentity(Object[]identity){
        writeOn(identity[0],5,1);
        writeOn(identity[1],6,1);
        writeOn(identity[2],5,5);
    }
    public void writeOn(Object data,int row, int cell){
        this.row = sheet.getRow(row);
        this.cell = this.row.getCell(cell);
        this.cell.setCellValue((String) data);
    }
    private void write(Object[][]content){
        int idx = EXPORTER_FIRST_ROW;
        for (Object[] contentRow : content) {
            row = sheet.createRow(idx++);
            int colNum = 1;
            for (Object field : contentRow) {
                cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }
    }
    private void writeToFile(Object[][]content){
        if(workbook == null){
            Toolbox.alert("workbook is empty, initialize it first");
            return;
        }
        if(!workbookIsPrepared)
            prepareWorkbook(content);
        write(content);
        try {
            FileOutputStream outputStream = new FileOutputStream(fileURL);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Row row;
    private Cell cell;
    
}
