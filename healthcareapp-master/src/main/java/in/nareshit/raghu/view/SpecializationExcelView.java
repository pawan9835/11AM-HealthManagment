package in.nareshit.raghu.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import in.nareshit.raghu.entity.Specialization;

public class SpecializationExcelView extends AbstractXlsxView {
	
	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws Exception 
	{
		
		//specify file name
		response.addHeader("Content-Disposition", "attachment;filename=Specialization.xlsx");
		
		//1. Read data from Model Memory
		List<Specialization> list = (List<Specialization>) model.get("list");
		
		//2. Create Sheet
		Sheet sheet = workbook.createSheet("SPECIALIZATIONS");
		
		//3. create Rows and Cells
		setHeader(sheet);
		setBody(sheet,list);
		
	}

	private void setHeader(Sheet sheet) {
		int rowNum = 0;
		Row row =  sheet.createRow(rowNum);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("CODE");
		row.createCell(2).setCellValue("NAME");
		row.createCell(3).setCellValue("NOTE");
	}
	
	private void setBody(Sheet sheet, List<Specialization> list) {
		int rowNum = 1;
		for(Specialization spec:list) {
			Row row =  sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getSpecId());
			row.createCell(1).setCellValue(spec.getSpecCode());
			row.createCell(2).setCellValue(spec.getSpecName());
			row.createCell(3).setCellValue(spec.getSpecNote());
		}
		
	}

}
