package data;

public class DataSQL {

	public static void main(String[] args) {

		for (int i = 1; i <= 100; ++i) {

			System.out.println(i + "," + "'PROJEKTAS_" + JavaRandom.numb() + "'," + JavaRandom.mainProject_Descript()
					+ JavaRandom.mainProject_State());

			for (int j = 1; j <= 10; ++j) {

				System.out.println(j + ", " + i + ", " + JavaRandom.mainTask_Name() + JavaRandom.mainTask_Description()
						+ JavaRandom.mainTask_Priority() + " curent_timestamp()," + " curent_timestamp(),"
						+ JavaRandom.mainTasc_State());
			}
		}
	}
}
