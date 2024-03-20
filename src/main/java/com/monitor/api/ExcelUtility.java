package com.monitor.api;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFLineChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ExcelUtility {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		//get current directory
		String currentDirectory = System.getProperty("user.dir");
		
		
		String filePath = System.getProperty("user.dir") + "\\chart.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Sheet1");

//		XSSFWorkbook wb = new XSSFWorkbook();
//		XSSFSheet sheet = wb.createSheet("C:\\STAF_Files\\chart");
		
		
		final int NUM_OF_ROWS = 3;
		final int NUM_OF_COLUMNS = 10;

		Row row;
		Cell cell;

		for (int rowIndex = 0; rowIndex < NUM_OF_ROWS; rowIndex++) {
		row = sheet.createRow((short) rowIndex);

			for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
				cell = row.createCell((short) colIndex);
				cell.setCellValue(colIndex * (rowIndex + 1.0)); // some random values
			}
		}
		
		XSSFDrawing drawing = sheet.createDrawingPatriarch();
		XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 15);
		
		XSSFChart chart = drawing.createChart(anchor);
		
		XDDFChartLegend legend = chart.getOrAddLegend();
		legend.setPosition(LegendPosition.TOP_RIGHT);
		
		
		// Use a category axis for the bottom axis.

		XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);

		bottomAxis.setTitle("x");

		XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);

		leftAxis.setTitle("f(x)");

		leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
		// Define Data Source x axis / y-axis values. (CellRangeAddress):
		XDDFDataSource<Double> xs = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 0, NUM_OF_COLUMNS - 1));
		// Define Data Source values to be plotted on those axis. (CellRangeAddress):
		XDDFNumericalDataSource<Double> ys1 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 0, NUM_OF_COLUMNS - 1));

		XDDFNumericalDataSource<Double> ys2 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, NUM_OF_COLUMNS - 1));
		// Create Chart – ChartType, ChartAxis, ValueAxis:
		XDDFLineChartData data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);
		// Add data series – axis values/category , values:
		XDDFLineChartData.Series series1 = (XDDFLineChartData.Series) data.addSeries(xs, ys1);
		series1.setTitle("2x", null);
		// Plot chart:
		chart.plot(data);
		
		
		
	      // Write the output to a file.
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        workbook.close();
        
        System.out.println("Chart.xlsx written successfully");
        
         // Save chart as image
  
		
		
		
		
		
		
		
		
	}
	
}