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
              while (true) {
            
                    System.out.println("數據欄位名: ");
                    String name = sc.next();
                  
                    System.out.println("請問有幾個數據: ");
                    int n = sc.nextInt();
                    double[] data = new double[n];
        
                    System.out.println("輸入數據: ");
                    for (int i = 0; i < n; i++) {
                        data[i] = sc.nextDouble();
                    }
        
                    DescriptiveStatistics stats = new DescriptiveStatistics(data, name);
                    statsList.add(stats);
        
                    System.out.println("Do you want to enter another set of data? (Y/N)");
                    String answer = sc.next();
        
                    if (answer.equalsIgnoreCase("N")) {
                        break;
                    }
                }
                      break;
        }
        
        for (DescriptiveStatistics stats : statsList) {
            System.out.println(stats.information());
            System.out.println();
        }
        
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
              //提供線性回歸的解釋
              System.out.println("要聽一下線性回歸的概念嗎？(Y/N)");
              String answer = sc.next();
              while(answer.equalsIgnoreCase("Y")){
                System.out.println("概念講解");
                double[] xData = {1, 2, 3, 4, 5};
                double[] yData = {2, 5 , 6, 7, 8};
                LinearRegression reg = new LinearRegression(xData, yData, "線性回歸示範");
                System.out.println(reg.Explain());
                System.out.println("要在聽一次嗎？(Y/N)");
                String answerl1 = sc.next();
                if (answerl1.equalsIgnoreCase("N")) {
                    System.out.println("好的，那我們進入實戰環節！");
                    break;
                }
              }
              for (int i = 0; i < statsList.size(); i++) {
                  System.out.println((i + 1) + ". " + statsList.get(i).getName());
              }
              System.out.println("請選擇要做迴歸分析的因變數：");
              int index1 = sc.nextInt() - 1;
              System.out.println("請選擇要做迴歸分析的自變數：");
              int index2 = sc.nextInt() - 1;
              String regName = statsList.get(index1).getName() + " vs. " + statsList.get(index2).getName();
              LinearRegression reg = new LinearRegression(statsList.get(index1).getData(), statsList.get(index2).getData(), regName);
              System.out.println(reg.description());
              System.out.println(reg.Summary());
              System.out.println("請問要預測 y(應變數) 值嗎？(Y/N)");
              String answerl2 = sc.next();
              while(answerl2.equalsIgnoreCase("Y")){
                System.out.println("請輸入 y 值：");
                double x = sc.nextDouble();
                System.out.println("預測的 x 值為：" + reg.predict(x));
                System.out.println("要在預測一次嗎？(Y/N)");
                String answerl3 = sc.next();
                if (answerl3.equalsIgnoreCase("N")) {
                    System.out.println("好的，再見！");
                    break;
                }
              }
            }
        sc.close();
    }
}
