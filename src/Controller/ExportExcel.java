package Controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ExportExcel {

    public static void export(JTable table, String filePath) {
        // Create Workbook
        Workbook workbook;
        if (filePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (filePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        // Create sheet
        Sheet sheet = workbook.createSheet("Sheet1");

        // Write Header
        // craete CellStyle
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.WHITE.getIndex());

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);

        // Create row
        Row row = sheet.createRow(0);
        for (int i = 0; i < table.getColumnCount(); i++) {
            // Create cell
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(table.getColumnName(i));
        }

        // Write data
        DataFormat df = workbook.createDataFormat();
        short format = df.getFormat("#.##");
        CellStyle cellStyleFormatNumber = workbook.createCellStyle();
        cellStyleFormatNumber.setDataFormat(format);
        for (int i = 1; i <= table.getRowCount(); i++) {
            row = sheet.createRow(i);
            for (int j = 0; j < table.getColumnCount(); j++) {
                Cell cell = row.createCell(j);
                Object data = table.getValueAt(i - 1, j);
                if (data instanceof String) {
                    cell.setCellValue((String) data);
                } else if (data instanceof Double) {
                    cell.setCellValue((double) data);
                    cell.setCellStyle(cellStyleFormatNumber);
                } else if (data instanceof Integer) {
                    cell.setCellValue((int) data);
                }
            }
        }

        // Auto resize column width
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int i = 0; i < numberOfColumn; i++) {
            sheet.autoSizeColumn(i);
        }

        // Create output file
        try {
            OutputStream os = new FileOutputStream(filePath);
            workbook.write(os);
            os.close();
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
