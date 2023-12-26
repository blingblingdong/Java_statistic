import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
      
        //有可能會使用到兩種以上的資料集
        //所以用ArrayList來存放
        
        Scanner sc = new Scanner(System.in);
        ArrayList<DescriptiveStatistics> statsList = new ArrayList<>();

        while (true) {
            
            System.out.println("Enter the name of the data set: ");
            String name = sc.next();
            
          
            System.out.println("Enter the number of the data point: ");
            int n = sc.nextInt();
            double[] data = new double[n];

            System.out.println("Enter the data points: ");
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

        
        for (DescriptiveStatistics stats : statsList) {
            System.out.println(stats.information());
            System.out.println();
        }
        
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
              System.out.println("請選擇要做假設檢定的資料：");
              for (int i = 0; i < statsList.size(); i++) {
                  System.out.println((i + 1) + ". " + statsList.get(i).getName());
              }
              int index = sc.nextInt() - 1
              Hypothesis ht = new Hypothesis(statsList.get(index));
              //...
              
              break;
            case 2:
              System.out.println("請選擇要做ANOVA的資料：");
              for (int i = 0; i < statsList.size(); i++) {
                  System.out.println((i + 1) + ". " + statsList.get(i).getName());
              }
              int index = sc.nextInt() - 1
              ANOVA anova = new ANOVA(statsList.get(index));
              //...
            case 3:
              System.out.println("請選擇要做迴歸分析的資料：");
              for (int i = 0; i < statsList.size(); i++) {
                  System.out.println((i + 1) + ". " + statsList.get(i).getName());
              }
              int index = sc.nextInt() - 1
              Regression reg = new Regression(statsList.get(index));
              //...
            }
        

        sc.close();
    }
}
