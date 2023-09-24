import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
  public static void main(String args[]){
    try {
      FileInputStream file = new FileInputStream(
          new File("error_code_24th.xlsx"));
      XSSFWorkbook workbook = new XSSFWorkbook(file);
      XSSFSheet sheet = workbook.getSheetAt(0);
      Iterator<Row> rowIterator = sheet.iterator();
      FileWriter myWriter = new FileWriter("filename.txt");
      while (rowIterator.hasNext()) {
        String error ="";

        Row row = rowIterator.next();

        // For each row, iterate through all the
        // columns
        Iterator<Cell> cellIterator
            = row.cellIterator();

        while (cellIterator.hasNext()) {
          Cell cell = cellIterator.next();
          if(cell.getCellType()== Cell.CELL_TYPE_STRING){
            System.out.println(cell.getStringCellValue());
            if(cell.getColumnIndex() == 0){
              error = cell.getStringCellValue() + "=";
            } else if(cell.getColumnIndex() == 1){
              error = error+ cell.getStringCellValue();
              myWriter.write(error +"\n");
              break;
            }
          }
        }

      }
      file.close();
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }


  }
}