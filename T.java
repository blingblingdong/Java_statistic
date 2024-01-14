import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T {

    public static void main(String[] args) {
        String path = "df.csv"; // 替换为您的文件路径

        Map<String, List<Double>> dataMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // 读取首行（列名）

            if (line != null) {
                String[] columns = line.split(",");
                for (String col : columns) {
                    dataMap.put(col, new ArrayList<>());
                }

                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    for (int i = 0; i < values.length; i++) {
                        dataMap.get(columns[i]).add(Double.parseDouble(values[i]));
                    }
                }
            }

            // 对每列数据应用 DescriptiveStatistics 并打印结果
            for (String col : dataMap.keySet()) {
                DescriptiveStatistics stats = new DescriptiveStatistics(
                    dataMap.get(col).stream().mapToDouble(i -> i).toArray());
                System.out.println("Column: " + col);
                System.out.println(stats.information());
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
