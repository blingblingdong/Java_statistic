import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.*;

public class WriteData {

    public static void write(ArrayList<DescriptiveStatistics> statsList){
      while (true) {
            Scanner scfordata = new Scanner(System.in);
            
            System.out.println("數據欄位名: ");
            String name = scfordata.next();
          
            System.out.println("請問有幾個數據: ");
            int n = scfordata.nextInt();
            double[] data = new double[n];

            System.out.println("輸入數據: ");
            for (int i = 0; i < n; i++) {
                data[i] = scfordata.nextDouble();
            }

            DescriptiveStatistics stats = new DescriptiveStatistics(data, name);
            statsList.add(stats);

            System.out.println("Do you want to enter another set of data? (Y/N)");
            String answer = scfordata.next();

            if (answer.equalsIgnoreCase("N")) {
                scfordata.close();
                break;
            }
        }
      
    }
    
    public static void writetoCSV(String fileName, ArrayList<DescriptiveStatistics> statsList) throws IOException {
          FileWriter csvWriter = new FileWriter(fileName + ".csv");
          ArrayList<String> headers = new ArrayList<>();
          
          for (int i = 0; i < statsList.size(); i++) {
              headers.add(statsList.get(i).getName());
              csvWriter.append(headers.get(i));
              csvWriter.append(",");
          }
          
          csvWriter.append("\n");
          
          for (int i = 0; i < headers.size(); i++) {
              for (int j = 0; j < headers.size(); j++) {
                  // 將 double 轉換為 String
                  csvWriter.append(String.valueOf(statsList.get(j).getData()[i]));
                  csvWriter.append(",");
              }
              csvWriter.append("\n");
          }
          
          csvWriter.flush();
          csvWriter.close();
      }

}