package BasePackage;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	
	
	public void writeToExcel(Map<Integer,String> data)
	{
		XSSFWorkbook workbook = new XSSFWorkbook(); 
		  
        XSSFSheet sheet = workbook.createSheet("sheet1"); 
    
        Set<Integer> keyset = data.keySet(); 
        int rownum = 0; 
        for (int key : keyset) { 
            Row row = sheet.createRow(rownum++); 
            int cellnum = 0; 
                Cell cell = row.createCell(cellnum++); 
                    cell.setCellValue((String)data.get(key)); 
        }
        try { 
            FileOutputStream out = new FileOutputStream(new File("output file path +filename.xlsx")); 
            workbook.write(out); 
            out.close(); 
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        
	}
}