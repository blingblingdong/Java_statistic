import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;



public class Main {
        public static void main(String[] args) {
      
        //有可能會使用到兩種以上的資料集
        //所以用ArrayList來存放
        
        Scanner sc = new Scanner(System.in);
        ArrayList<DescriptiveStatistics> statsList = new ArrayList<>();
        
        System.out.println("請選擇數據來源 ? 1:CSV 2:手動輸入");
        int choice1 = sc.nextInt();
        
        switch(choice1){
            case 1:
              System.out.println("Enter the CSV file path: ");
              String filePath = sc.next();
              ReadFile.readDataToStatsList(filePath, statsList);
              break;
            case 2:
              WriteData.write(statsList);
              WriteData.writetoCSV("test", statsList);
              break;
        }
        
        for (DescriptiveStatistics stats : statsList) {
            System.out.println(stats.information());
            System.out.println();
        }
        
        //將數據寫入csv
        /*
        System.out.println("Do you want to write the data to a csv file? (Y/N)");
        String answer = sc.next();
        
        if (answer.equalsIgnoreCase("Y")) {
            System.out.println("Enter the name of the csv file: ");
            String fileName = sc.next();
            File.writeToCSV(statsList, fileName);
        }
        */
        
        // 進階hypothesisTesting、anova、regression
        // 先選擇要進行的分析
        // 再選擇要進行分析的資料
        
        
        System.out.println("選擇要進行的分析：");
        System.out.println("1. Hypothesis Testing");
        System.out.println("2. ANOVA");
        System.out.println("3. Regression");
        int choice = sc.nextInt();
        
        switch(choice){
            case 1:
              break;
            case 2:
              System.out.println("請選擇要做ANOVA的資料(輸入對應數字，輸入-1結束)：");
              for (int i = 0; i < statsList.size(); i++) {
                  System.out.println((i + 1) + ". " + statsList.get(i).getName());
              }
              
              ArrayList<Integer> indexList = new ArrayList<>();
              while(true) {
                  int index = sc.nextInt() - 1;
                  if (index == -2) {
                      break;
                  } else if (index >= 0 && index < statsList.size()) {
                      indexList.add(index);
                      System.out.println("已選擇 " + statsList.get(index).getName());
                  } else {
                      System.out.println("無效的索引，請重新輸入");
                  }
              }
              
              double[][] dataForAnova = new double[indexList.size()][];
              for (int i = 0; i < indexList.size(); i++) {
                  DescriptiveStatistics stats = statsList.get(indexList.get(i));
                  dataForAnova[i] = stats.getData();
              }
              
              if (dataForAnova.length > 1) {
                  Anova anova = new Anova(dataForAnova, "ANOVA Test");
                  System.out.println(anova.anovaSummary());
                  System.out.println("F 值: " + anova.calculateFValue());
              } else {
                  System.out.println("至少需要選擇兩組數據進行ANOVA分析");
              }
            case 3:
              for (int i = 0; i < statsList.size(); i++) {
                  System.out.println((i + 1) + ". " + statsList.get(i).getName());
              }
              System.out.println("請選擇要做迴歸分析的因變數：");
              int index1 = sc.nextInt() - 1;
              System.out.println("請選擇要做迴歸分析的自變數：");
              int index2 = sc.nextInt() - 1;
              String regName = statsList.get(index1).getName() + " vs. " + statsList.get(index2).getName();
              LinearRegression reg = new LinearRegression(statsList.get(index1).getData(), statsList.get(index2).getData(), regName);
              System.out.println(reg.modelSummary());
            }
        sc.close();
    }
}
