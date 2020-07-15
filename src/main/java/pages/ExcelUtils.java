package pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ExcelUtils {

	public WebDriver driver;
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	Log4j log = new Log4j(driver);


	public ExcelUtils(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static Object[][] getTableArray(String filePath, String sheetName) {
		String[][] tabArr = null;
		String username, pwd;
		try {
			FileInputStream fis = new FileInputStream(filePath);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(fis);
			ExcelWSheet = ExcelWBook.getSheetAt(0);
			sheetName = ExcelWSheet.getSheetName();
			System.out.println("------------" + sheetName + "---------------");
			int rowNum = ExcelWSheet.getPhysicalNumberOfRows();
			System.out.println(rowNum);
			tabArr = new String[rowNum - 1][2];
			for (int i = 1; i < rowNum; i++) {
				System.out.println("----------" + i + "----------");
				if (ExcelWSheet.getRow(i).getCell(1) != null) {
					username = ExcelWSheet.getRow(i).getCell(1).getStringCellValue();
					if (ExcelWSheet.getRow(i).getCell(2) != null) {
						pwd = ExcelWSheet.getRow(i).getCell(2).getStringCellValue();
					} else {
						pwd = "";
					}
				} else {
					username = "";
					if (ExcelWSheet.getRow(i).getCell(2) != null) {
						pwd = ExcelWSheet.getRow(i).getCell(2).getStringCellValue();
					} else {
						pwd = "";
					}
				}
				System.out.println("Username :" + username + "------------" + "Password :" + "-----------" + pwd);
				tabArr[i - 1][0] = username;
				tabArr[i - 1][1] = pwd;

			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return tabArr;

	}

}
