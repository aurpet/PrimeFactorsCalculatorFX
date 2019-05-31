package sample;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class DataStorage {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSS");

    // return data from GUI input fields
    public static HashMap InputDataMap(String startNumber, String endNumber, String increaseNumber) {
        HashMap<Integer, InputValueObject> map = new HashMap();
        InputValueObject inputValue = new InputValueObject(Integer.parseInt(startNumber),
                Integer.parseInt(endNumber), Integer.parseInt(increaseNumber));
        map.put(1, inputValue);
        return map;
    }

    public void saveFile() throws IOException {
        int fileNumber = 0;
        File file = new File("results" + fileNumber + ".txt");
        while (file.exists()) {
            file = new File("results" + (fileNumber++) + ".txt");
        }
        StringBuilder sb = new StringBuilder();

        if (GUI.data.size() > 0 && GUI.data != null){
            for (Map.Entry<Integer, InputValueObject> entry : GUI.data.entrySet()){

                sb.append(" Pirmas " + entry.getValue().startNumber);
                sb.append(" Antras " + entry.getValue().endNumber);
                sb.append(" Trecias " + entry.getValue().endNumber);
            }
        }

        Writer writer = new BufferedWriter(new FileWriter(file));
        writer.write(timeStamp () + " Suvesti skaiciai: " + sb.toString());
        writer.close();
    }

    private static String timeStamp (){
        String time;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        time = dateFormat.format(timestamp);
        return time;
    }

}
