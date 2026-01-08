package utilities_classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_File_Reader {

    private String path;

    public Excel_File_Reader(String path) {
        this.path = path;
    }
    
    //get all the row present in the excel
    public Object[][] getTestData() {

        List<Object[]> data = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells(); // header row

            // Skip header → start from row 1
            for (int i = 1; i < rowCount; i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                Object[] rowData = new Object[colCount];

                for (int j = 0; j < colCount; j++) {
                    rowData[j] = formatter.formatCellValue(row.getCell(j)).trim();
                }

                data.add(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[][] arr = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            arr[i] = data.get(i);
        }
        return arr;
    }
    
    // get the first row from excel even if excell has multilple row
    public Object[][] getSingleRowData() {

        DataFormatter df = new DataFormatter();

        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = wb.getSheetAt(0);

            // always read ONLY row 1 (after header)
            XSSFRow row = sheet.getRow(1);

            if (row == null) {
                throw new RuntimeException("No data found in Excel");
            }

            String col1 = df.formatCellValue(row.getCell(0)).trim();
            String col2 = df.formatCellValue(row.getCell(1)).trim();
            String col3 = df.formatCellValue(row.getCell(2)).trim();

            return new Object[][] {
                { col1, col2, col3 }
            };

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
