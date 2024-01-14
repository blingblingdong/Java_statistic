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
        
        System.out.println("請選擇數據來源 ? 1:CSV 2:手動輸入 3.使用範例");
        int choice1 = sc.nextInt();
        
        switch(choice1){
            case 1:
              System.out.println("輸入csv檔: ");
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
            case 3:
              System.out.println("為您提供以下示範");
              double[] data1 = {1, 2, 3, 4, 5};
              double[] data2 = {2, 5 , 6, 7, 8};
              double[] data3 = {3, 4, 5, 6, 7};
              statsList.add(new DescriptiveStatistics(data1, "data1"));
              statsList.add(new DescriptiveStatistics(data2, "data2"));
              statsList.add(new DescriptiveStatistics(data3, "data3"));
              System.out.println("data1: " + Arrays.toString(data1));
              System.out.println("data2: " + Arrays.toString(data2));
              System.out.println("data3: " + Arrays.toString(data3));
              System.out.println("");
              System.out.println("--------------------------");
              System.out.println("");
              break;
        }
        
        System.out.println("基礎數據分析");
        System.out.println(statsList.get(0).description());
        for (DescriptiveStatistics stats : statsList) {
            System.out.println(stats.summary());
            System.out.println();
        }
        
        System.out.println(statsList.get(0).explain());
        System.out.println("--------------------------");
        System.out.println("");
        
        System.out.println("選擇要進行的分析：");
        System.out.println("1. HypothesisTest");
        System.out.println("2. ANOVA");
        System.out.println("3. Regression");
        int choice = sc.nextInt();
        System.out.println("");
        
        switch(choice){
            case 1:
              HypothesisTest hy_instruction = new HypothesisTest(null, "name");
      				System.out.println("歡迎使用「假設檢定」功能，請問有需要為您進行假設檢定之概念講解嗎？(輸入y/n)");
      
      				if (sc.next().equals("y")) {
      					hy_instruction.instruction();
      				}
      				
      				for (int i = 0; i < statsList.size(); i++) {
      					System.out.println((i + 1) + ". " + statsList.get(i).getName());
      				}
      				System.out.println("請選擇要做假設檢定的資料(輸入對應數字)：");
      				int index_hy = sc.nextInt() - 1;
      				System.out.println("您選擇的資料為：" + statsList.get(index_hy).getName());
      				System.out.println("----------------------------------------");
      				
      				HypothesisTest hy = new HypothesisTest(statsList.get(index_hy).getData(), statsList.get(index_hy).getName());
      				System.out.println("第一個步驟為「確立虛無假設」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");
      
      				if (sc.next().equals("y")) {
      					hy_instruction.instruction1();
      				}
      				
      				System.out.println("輸入假設之均值：");
      				double mean = sc.nextDouble();
      				System.out.println("輸入假設之方向「左尾、右尾、雙尾」：");
      				String direction = sc.next();
      				hy.setNullHypo(mean, direction);
      				System.out.println("-----------------------------");
      				
      				System.out.println("第二個步驟為「確立檢定統計量」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");
      				if (sc.next().equals("y")) {
							hy_instruction.instruction2();
						  }
      				//如果數據>30，則使用z檢定
      				if(hy.getData().length > 30) {
      				  System.out.println("數據大於30筆，適用的是Z分析");
      					System.out.println("是否得知或遇輸入母體體標準差？(輸入y/n)");
      					String answer_hy = sc.next();
      					
      					double std;
                if(answer_hy.equals("y")){
                    System.out.println("請輸入母體標準差：");
                    std = sc.nextDouble();
                }else{
                    System.out.println("將以樣本標準差替代");
                    std = hy.standardDeviation();
                }
              
      					System.out.println("-----------------------------");
      					double pValue = hy.p_Population(std);
      					System.out.println("P 值: " + pValue);
      					hy.analysis(pValue);
      				}else{
      				  System.out.println("數據小於30筆，適用的是T分析");
      				  System.out.println("-----------------------------");
      				  double tValue = hy.t_Sample();
                System.out.println("T 值: " + tValue);
                hy.tAnalysis(tValue);
      				}
      				hy_instruction.instruction3();
      				System.out.println("");
              break;
            case 2:
              //ANOVA-1 解釋
              System.out.println("要聽一下Anova的概念嗎？(Y/N)");
              String answera1 = sc.next();
              while(answera1.equalsIgnoreCase("Y")){
                System.out.println("概念講解");
                double[][] dataForAnova = {{1, 2, 3, 4, 5}, {2, 5 , 6, 7, 8}};
                Anova anova = new Anova(dataForAnova, "ANOVA Test");
                System.out.println(anova.description());
                System.out.println("要在聽一次嗎？(Y/N)");
                String answera2 = sc.next();
                if (answera2.equalsIgnoreCase("N")) {
                    System.out.println("好的，那我們進入實戰環節！");
                    System.out.println("");
                    break;
                }
              }
              
              //ANOVA-2 實戰
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
                  System.out.println(anova.summary());
                  System.out.println("F 值: " + anova.calculateFValue());
                  System.out.println(anova.explain());
              } else {
                  System.out.println("至少需要選擇兩組數據進行ANOVA分析");
              }
              break;
            case 3:
              //提供線性回歸的解釋
              System.out.println("要聽一下線性回歸的概念嗎？(Y/N)");
              String answer = sc.next();
              while(answer.equalsIgnoreCase("Y")){
                System.out.println("概念講解");
                double[] xData = {1, 2, 3, 4, 5};
                double[] yData = {2, 5 , 6, 7, 8};
                LinearRegression reg = new LinearRegression(xData, yData, "線性回歸示範");
                System.out.println(reg.description());
                System.out.println("要在聽一次嗎？(Y/N)");
                String answerl1 = sc.next();
                if (answerl1.equalsIgnoreCase("N")) {
                    System.out.println("好的，那我們進入實戰環節！");
                    System.out.println("");
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
              
              System.out.println("");
              System.out.println("迴歸分析結果：");
              System.out.println(reg.summary());
              System.out.println(reg.explain());
              System.out.println("--------------------------");
              System.out.println("");
              
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
