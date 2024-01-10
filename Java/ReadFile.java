import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.*;

public class ReadFile {

    public static void readDataToStatsList(String filePath, ArrayList<DescriptiveStatistics> statsList) {
        try {
            Scanner sc = new Scanner(new File(filePath));
            String[] headers = sc.nextLine().split(",");

            List<List<Double>> columns = new ArrayList<>();
            for (int i = 0; i < headers.length; i++) {
                columns.add(new ArrayList<>());
            }

            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",");
                for (int i = 0; i < data.length; i++) {
                    try {
                        double value = Double.parseDouble(data[i]);
                        columns.get(i).add(value);
                    } catch (NumberFormatException e) {
                        // 不是數字，忽略此列
                    }
                }
            }

            for (int i = 0; i < columns.size(); i++) {
                List<Double> column = columns.get(i);
                if (!column.isEmpty()) {
                    double[] dataArray = column.stream().mapToDouble(d -> d).toArray();
                    statsList.add(new DescriptiveStatistics(dataArray, headers[i]));
                }
            }

            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
