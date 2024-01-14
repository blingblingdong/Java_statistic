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
        System.out.println("1. ＨypothesisTest");
        System.out.println("2. ANOVA");
        System.out.println("3. Regression");
        int choice = sc.nextInt();
        System.out.println("");
        
        switch(choice){
            case 1:
              hypothesisTest test = new hypothesisTest(null, null, "name");
      				System.out.println("歡迎使用「假設檢定」功能，請問有需要為您進行假設檢定之概念講解嗎？(輸入y/n)");
      
      				if (sc.next().equals("y")) {
      					test.instruction();
      				}
      
      				System.out.println("第一個步驟為「確立虛無假設」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");
      
      				if (sc.next().equals("y")) {
      					test.instruction1();
      				}
      
      				System.out.println("\n題目是否有提供敘述統計量？(輸入y/n)");
      				String userInput = sc.next();
      
      				if (userInput.equals("y")) {
      
      					System.out.println("請依序輸入「虛無假設值」以及選擇「左尾、右尾、雙尾」");
      					test.setNullHypo(sc.nextDouble(), sc.next());
      
      					System.out.println("您想進行z檢定還是t檢定（a.z檢定 b.t檢定(僅提供29筆以下之數據使用)，輸入a/b）");
      
      					String testChoose = sc.next();
      					double t = 0;
      
      					if (testChoose.equals("a")) {
      
      						System.out.println("請依序輸入「樣本平均數」、「母體變異數」、「信心水準」、「母體數量」");
      						test.setXbar(sc.nextDouble());
      						test.setVar(sc.nextDouble());
      						test.setAlpha(Math.round(100 * (1 - sc.nextDouble())) / 100);
      						test.setNum(sc.nextInt());
      
      						System.out.println("第二個步驟為「計算檢定統計量」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");
      
      						if (sc.next().equals("y")) {
      							test.instruction2();
      						}
      						test.calculatePValue();
      
      						System.out.println("第三個步驟為「針對結果進行分析」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");
      
      						if (sc.next().equals("y")) {
      							test.instruction3();
      						}
      						test.analysis(t);
      
      					} else if (testChoose.equals("b")) {
      
      						System.out.println("請依序輸入「樣本平均數」、「樣本變異數」、「信心水準」、「母體數量」");
      						test.setXbar(sc.nextDouble());
      						test.setVar(sc.nextDouble());
      						test.setAlpha(Math.round(100 * (1 - sc.nextDouble())) / 100);
      						test.setNum(sc.nextInt());
      
      						System.out.println("第二個步驟為「計算檢定統計量」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");
      
      						if (sc.next().equals("y")) {
      							test.instruction2();
      						}
      						t = test.tCalculatePValue();
      						System.out.println("可得此情形下，檢定統計量為" + t + "，自由度為" + (test.getNum() - 1));
      
      						System.out.println("第三個步驟為「針對結果進行分析」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");
      
      						if (sc.next().equals("y")) {
      							test.instruction3();
      						}
      						test.tAnalysis(t);
      
      					}
      					//以下是要自己手動輸入資料的程式碼
      				} else if (userInput.equals("n")) {
      
      					ArrayList<Double> dataList = new ArrayList<>();
      
      					while (true) {
      						System.out.print("輸入資料（輸入 'q' 結束）：");
      						String userInputnum = sc.next();
      
      						if (userInputnum.equalsIgnoreCase("q")) {
      							break; // 用戶輸入 'q'，跳出迴圈
      						}
      
      						try {
      							// 嘗試將輸入轉換為 double
      							double number = Double.parseDouble(userInputnum);
      							dataList.add(number);
      						} catch (NumberFormatException e) {
      							System.out.println("請輸入有效的數字或 'q' 來結束。");
      						}
      					}
      
      					hypothesisTest test1 = new hypothesisTest(dataList, null, "name");
      					System.out.println("請依序輸入「虛無假設值」以及「左尾、右尾、雙尾」");
      					test1.setNullHypo(sc.nextDouble(), sc.next());
      
      					System.out.println("您想進行z檢定還是t檢定（a.z檢定 b.t檢定(僅提供29筆以下之數據使用)，輸入a/b");
      
      					String testChoose = sc.next();
      					double t = 0;
      
      					if (testChoose.equals("a")) {
      
      						System.out.println("請輸入信心水準");
      						test1.setAvg(test1.mean());
      						test1.setVar(test1.populationVariance());
      						test1.setAlpha(Math.round(100 * (1 - sc.nextDouble())) / 100);
      
      						System.out.println("第二個步驟為「計算檢定統計量」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");
      						if (sc.next().equals("y")) {
      							test1.instruction2();
      						}
      						test1.calculatePValue();
      
      						System.out.println("第三個步驟為「針對結果進行分析」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");
      						if (sc.next().equals("y")) {
      							test1.instruction3();
      						}
      						test1.analysis(t);
      
      					} else if (testChoose.equals("b")) {
      
      						System.out.println("請輸入信心水準");
      						test1.setAvg(test1.mean() * dataList.size() / (dataList.size() - 1));
      						test1.setVar(test1.populationVariance() * dataList.size() / (dataList.size() - 1));
      						test1.setNum(dataList.size());
      						test1.setAlpha(Math.round(100 * (1 - sc.nextDouble())) / 100);
      
      						System.out.println("第二個步驟為「計算檢定統計量」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");
      						if (sc.next().equals("y")) {
      							test1.instruction2();
      						}
      						double t1 = test1.tCalculatePValue();
      						System.out.println("可得此情形下，檢定統計量為" + t1 + "，自由度為" + test.getNum());
      
      						System.out.println("第三個步驟為「針對結果進行分析」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");
      						if (sc.next().equals("y")) {
      							test1.instruction3();
      						}
      						test1.tAnalysis(t1);
      
      					}
      
      				}
      
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
