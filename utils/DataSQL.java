import javax.xml.crypto.Data;
import java.util.ArrayList;

public class DataSQL {

    public static void main(String[] args) throws Exception {
        String FILE_PATH = "/home/sup/Documents/Project-managment-system/src/main/resources/data.sql";
        String PROJECT_HEADER = "INSERT INTO PROJECT\n" +
                "(ID, PROJECT_NAME, PROJECT_DESCRIPTION, PROJECT_STATUS)\n" +
                "VALUES";
        ArrayList<String> projectList = new ArrayList<>();
        projectList.add(PROJECT_HEADER);

        String TASK_HEADER = "INSERT INTO TASK (ID, PROJECT_ID, TASK_NAME, TASK_DESCRIPTION, TASK_PRIORITY, TASK_CREATE_TIME, TASK_MODIFY_TIME, TASK_STATE) \n" +
                "VALUES ";
        ArrayList<String> taskList = new ArrayList<>();
        taskList.add(TASK_HEADER);

        String sql = null;
        String task = null;
        int idCounter = 0;

        int PROJECTS = 200;
        int TASKS = 100;

        for (int projectID = 1; projectID <= PROJECTS; ++projectID) {


            sql = "(" + projectID + "," + "'PROJEKTAS_" + JavaRandom.numb() + "'," + JavaRandom.mainProject_Descript()
                    + JavaRandom.mainProject_State() + "),";
            if(PROJECTS == projectID) {
                sql = "(" + projectID + "," + "'PROJEKTAS_" + JavaRandom.numb() + "'," + JavaRandom.mainProject_Descript()
                        + JavaRandom.mainProject_State() + ");";
            }
            projectList.add(sql);
            int randNumOfTasks = JavaRandom.randomNum(0, 200);
            for (int j = 1; j <= randNumOfTasks; ++j) {
                idCounter++;
                task = "(" + idCounter + ", " + projectID + ", " + JavaRandom.mainTask_Name() + JavaRandom.mainTask_Description()
                        + JavaRandom.mainTask_Priority() + " current_timestamp()," + " current_timestamp(),"
                        + JavaRandom.mainTasc_State() + "),";
                taskList.add(task);
            }

        }
        endLastTaskWithSemiColon(taskList);
        projectList.addAll(taskList);
        FileHandler.writeFile(FILE_PATH, projectList);
        System.out.println("Completed..");

    }

    private static void endLastTaskWithSemiColon(ArrayList<String> taskList) {
        String fieldName = taskList.get(taskList.size() - 1);
        if (fieldName.endsWith(",")) {
            fieldName = fieldName.substring(0, fieldName.length() - 1) + ";";
        }
        taskList.remove(taskList.size() - 1);
        taskList.add(fieldName);
    }

}
