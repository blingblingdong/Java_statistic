package final_project_;

import java.util.ArrayList;
import java.util.Scanner;

public class tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println("您好！歡迎使用統計教學計算系統，請問您今天想要使用什麼功能？\n假設檢定h、迴歸分析l、變異數分析a、關閉系統q");
			String system = sc.next();

			// 進入假設檢定之功能

			if (system.equals("h")) {

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

			} else if (system.equals("l")) {

			} else if (system.equals("a")) {

			} else if (system.equals("q")) {
				break;

			}

		}
		sc.close();
	}

}
