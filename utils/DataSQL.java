package data;

import java.util.ArrayList;

public class DataSQL {

	public static void main(String[] args) {

		ArrayList<String> arrList = new ArrayList<>();
		ArrayList<String> taskList = new ArrayList<>();
		String sql = null;
		String task = null;

		for (int i = 1; i <= 1000; ++i) {

			sql = (i + "," + "'PROJEKTAS_" + JavaRandom.numb() + "'," + JavaRandom.mainProject_Descript()
					+ JavaRandom.mainProject_State());
			arrList.add(sql);

			for (int j = 1; j <= 200; ++j) {

				task = (j + ", " + i + ", " + JavaRandom.mainTask_Name() + JavaRandom.mainTask_Description()
						+ JavaRandom.mainTask_Priority() + " curent_timestamp()," + " curent_timestamp(),"
						+ JavaRandom.mainTasc_State());
				taskList.add(sql);
			}

		}
		arrList.stream().forEach(x -> System.out.println(x));
		taskList.stream().forEach(x -> System.out.println(x));

	}

}
