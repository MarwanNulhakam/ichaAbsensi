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
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Heni Edrianti
 */
public class ExcelExporter {
    public final int EXPORTER_FIRST_ROW = 9;
    public final String TEMPLATE__URL = "C:\\Users\\Heni Edrianti\\Documents\\icha\\ReportTemplate.xlsx";
    
    //flag
    private boolean workbookIsPrepared = false;
    private String fileURL;
    
    public ExcelExporter(){
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("rekap absensi");
        sheet.setColumnWidth(0, (int)(47*36.5));
        sheet.setColumnWidth(1, (int)(94*36.5));
        sheet.setColumnWidth(2, (int)(88*36.5));
        sheet.setColumnWidth(3, (int)(88*36.5));
        sheet.setColumnWidth(4, (int)(112*36.5));
        sheet.setColumnWidth(5, (int)(180*36.5));
    }
    
    public ExcelExporter(Object[]identity,Object[][]data){
        this();
        writeToFile(identity,data);
    }
    public ExcelExporter(String file,Object[]identity,Object[][]data){
        this();
        createTemplateOn(file);
        writeToFile(identity,data);
    }
    
    private void createTemplateOn(String file){
        try {
            System.out.println("creting template on "+file);
            System.out.println("initialize file");
            java.io.File f = new java.io.File(file);
            System.out.println("create new file");
            f.createNewFile();
            fileURL = file;
            System.out.println(fileURL);
        } catch (IOException ex) {
            database.Toolbox.alert("Failed to create export file");
            System.out.println(ex.getMessage());
        }
    }
    
    public void prepareWorkbook(Object []identity,Object[][]content){
        workbookIsPrepared = true;
        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue("PEMERINTAH PROVINSI NUSA TENGGARA BARAT");
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("DINAS PENDIDIKAN DAN KEBUDAYAAN");
        sheet.addMergedRegion(new CellRangeAddress(1,1,0,5));
        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("SMK PERHOTELAN 45 MATARAM");
        sheet.addMergedRegion(new CellRangeAddress(2,2,0,5));
        row = sheet.createRow(3);
        cell = row.createCell(0);
        cell.setCellValue("Jl. Imam Bonjol Cakranegara Utara - Mataram, Telp. 085937012236, E-mail: smkperhotelan45mataram@gmail.com");
        sheet.addMergedRegion(new CellRangeAddress(3,3,0,5));
        
        row = sheet.createRow(5);
        cell = row.createCell(0);
        cell.setCellValue("NIY:");
        cell = row.createCell(1);
        cell.setCellValue((String)identity[0]);
        cell = row.createCell(4);
        cell.setCellValue("Bulan:");
        cell = row.createCell(5);
        cell.setCellValue((String)identity[2]);
        row = sheet.createRow(6);
        cell = row.createCell(0);
        cell.setCellValue("Nama:");
        cell = row.createCell(1);
        cell.setCellValue((String)identity[1]);
        
        row = sheet.createRow(8);
        cell = row.createCell(0);
        cell.setCellValue("No.");
        cell = row.createCell(1);
        cell.setCellValue("Tanggal");
        cell = row.createCell(2);
        cell.setCellValue("Jam Datang");
        cell = row.createCell(3);
        cell.setCellValue("Jam Pulang");
        cell = row.createCell(4);
        cell.setCellValue("Status Kehadiran");
        cell = row.createCell(5);
        cell.setCellValue("Keterangan");
        
        
    }
//    private void insertUserIdentity(Object[]identity){
//        writeOn(identity[0],5,1);
//        writeOn(identity[1],6,1);
//        writeOn(identity[2],5,5);
//    }
//    public void writeOn(Object data,int row, int cell){
//        if(this.row.getRowNum() != row)
//            this.row = sheet.createRow(row);
//        this.cell = this.row.createCell(cell);
//        this.cell.setCellValue((String) data);
//    }
    private void write(Object[][]content){
        int idx = EXPORTER_FIRST_ROW;
        int no = 1;
        for (Object[] contentRow : content) {
            row = sheet.createRow(idx++);
            cell = row.createCell(0);
            cell.setCellValue(no);
            int colNum = 1;
            for (Object field : contentRow) {
                cell = row.createCell(colNum++);
//                if (field instanceof String) {
                    cell.setCellValue(field==null? "":(String) field);
//                } else if (field instanceof Integer) {
//                    cell.setCellValue((Integer) field);
//                }
            }
            no++;
        }
    }
    private void writeToFile(Object[]identity,Object[][]content){
        if(workbook == null){
            Toolbox.alert("workbook is empty, initialize it first");
            return;
        }
        if(!workbookIsPrepared)
            prepareWorkbook(identity,content);
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
        Toolbox.info("Data berhasil diexport ke "+fileURL);
    }
    
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Row row;
    private Cell cell;
    
}
