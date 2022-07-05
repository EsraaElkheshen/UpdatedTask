package utlities;

import io.ExcelParser;

import java.io.IOException;
import java.util.HashMap;

public class TestData {
    // Initialize el excel

    private HashMap<String, String> dataCollector = new HashMap<>();

    public TestData(String excelFilePath, String sheetName) {
        try {
            ExcelParser.setExcelFile(excelFilePath, sheetName);
            readData();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // read data from excel sheet

    private void readData() {
        for (int i = 0; i <= ExcelParser.getRowCountInSheet(); i++) {
            // read key w el value mn excel sheet
            dataCollector.put(ExcelParser.getCellData(i, 0), ExcelParser.getCellData(i, 1));
        }

    }

    public HashMap<String, String> getDataCollector() {
        return dataCollector;
    }

    public void setDataCollector(HashMap<String, String> dataCollector) {
        this.dataCollector = dataCollector;
    }


    public void selectDataSet(String sheetName) {
        ExcelParser.selectSheet(sheetName);
        readData();
    }

}


