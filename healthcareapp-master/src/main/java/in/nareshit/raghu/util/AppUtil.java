package in.nareshit.raghu.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppUtil {

	public static Map<Long, String> convertListToMap(List<Object[]> list) {
		//JAVA 8 STREAM API (List<Object[]> ---> Map<Integer,String>
		return list.stream()
				.collect(
						Collectors.toMap(
								ob->Long.valueOf(ob[0].toString()), 
								ob->ob[1].toString())
						);
	}

}
