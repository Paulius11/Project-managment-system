package data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class JavaRandom {

	private static String randomElement;

	public static String mainProject_State() {

		Random rand = new Random();

		List<String> givenList = Arrays.asList("'COMPLETED',", "'IN_PROGRESS',");

		int numberOfElements = 1;

		for (int i = 0; i < numberOfElements; i++) {
			int randomIndex = rand.nextInt(givenList.size());
			randomElement = givenList.get(randomIndex);
		}
		return randomElement;
	}

	public static String mainTask_Priority() {

		Random rand = new Random();
		List<String> givenList = Arrays.asList("'HIGHT',", "'NORMAL',", "'LOW',");

		int numberOfElements = 1;

		for (int i = 0; i < numberOfElements; i++) {
			int randomIndex = rand.nextInt(givenList.size());
			randomElement = givenList.get(randomIndex);
		}
		return randomElement;
	}

	public static int numb() {
		int min = 0;
		int max = 1000;
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		return randomNum;
	}

	public static String mainTasc_State() {

		Random rand = new Random();
		List<String> givenList = Arrays.asList("'IN_PROGRESS',", "'TO_DO',", "'DONE',");

		int numberOfElements = 1;

		for (int i = 0; i < numberOfElements; i++) {
			int randomIndex = rand.nextInt(givenList.size());
			randomElement = givenList.get(randomIndex);
		}
		return randomElement;
	}

	public static String mainTask_Name() {

		Random rand = new Random();
		List<String> givenList = Arrays.asList("'JAVA',", "'REACT',", "'BECKEND',", "'FRONTEND',", "'JAVASCRIPT',",
				"'PYTHON',", "'C++',", "'LOGO',");

		int numberOfElements = 1;

		for (int i = 0; i < numberOfElements; i++) {
			int randomIndex = rand.nextInt(givenList.size());
			randomElement = givenList.get(randomIndex);
		}
		return randomElement;
	}

	public static String mainTask_Description() {

		Random rand = new Random();
		List<String> givenList = Arrays.asList("'SUKURTI',", "'ATIDETI',", "'PERÞIURETI',", "'PASIRENGTI',",
				"'ÁVYKDYTI',", "'REDAGUOTI',", "'PERDUOTI',", "'SUGENERUOTI',");

		int numberOfElements = 1;

		for (int i = 0; i < numberOfElements; i++) {
			int randomIndex = rand.nextInt(givenList.size());
			randomElement = givenList.get(randomIndex);
		}
		return randomElement;
	}

	public static String mainProject_Descript() {

		Random rand = new Random();
		List<String> givenList = Arrays.asList("'DARBINIS_PROJECTAS',", "'NUOTOLINIS_PROJECTAS',",
				"'ATIDETAS_PROJECTAS',", "'PARENGTAS_PROJECTAS',", "'ÁVYKDYTAS_PROJECTAS',", "'REDAGUOTAS_PROJECTAS',",
				"'PERDUOTAS_PROJECTAS',", "'SUGENERUOTAS_PROJECTAS',");

		int numberOfElements = 1;

		for (int i = 0; i < numberOfElements; i++) {
			int randomIndex = rand.nextInt(givenList.size());
			randomElement = givenList.get(randomIndex);
		}
		return randomElement;
	}
}
