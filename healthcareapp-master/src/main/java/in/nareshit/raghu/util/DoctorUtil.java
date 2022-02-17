package in.nareshit.raghu.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class DoctorUtil {

	public void generatePie(String path, List<Object[]> list) {
		//1. DataSet using above data (List<Object[]>)
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(Object[] ob:list) {
			dataset.setValue(
					ob[0].toString(),  //String
					Double.valueOf( ob[1].toString()) //count
					);
		}

		//2. Create JFreeCharts object using DataSet
		JFreeChart chart = ChartFactory.createPieChart("DOCTORS CHART PIE", dataset);
		//3. Convert JFreeCharts into image
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/docA.jpg"), chart, 400, 400);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void generateBar(String path, List<Object[]> list) {
		//1. DataSet using above data (List<Object[]>)
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(Object[] ob:list) {
			dataset.setValue(
					Double.valueOf( ob[1].toString()), //count
					ob[0].toString(),  //String
					""
					);
		}
		//2. Create JFreeCharts object using DataSet
		JFreeChart chart = ChartFactory.createBarChart("DOCTORS CHART BARS", "SPECS", "COUNT", dataset);
		//3. Convert JFreeCharts into image
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/docB.jpg"), chart, 400, 400);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
