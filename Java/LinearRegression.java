import java.util.Arrays;

/**
 * LinearRegression 類繼承自 DescriptiveStatistics 類，提供線性回歸分析的功能。
 * 它能計算線性回歸模型的斜率和截距，並使用模型進行預測。
 */
public class LinearRegression extends DescriptiveStatistics {

    private double[] xData; // 自變量
    private double[] yData; // 因變量

    /**
     * 使用自變量和因變量的數據，以及數據集的名稱來構造 LinearRegression 物件。
     *
     * @param xData 自變量的數據陣列。
     * @param yData 因變量的數據陣列。
     * @param name 數據集的名稱。
     */
    public LinearRegression(double[] xData, double[] yData, String name) {
        super(yData, name); // 使用因變量初始化 DescriptiveStatistics
        this.xData = xData;
        this.yData = yData;
    }

    /**
     * 計算回歸線的斜率（beta）。
     * 
     * @return 斜率值。計算方法是將 xData 和 yData 的協方差除以 xData 的變異數。
     */
    public double calculateSlope() {
        double meanX = new DescriptiveStatistics(xData, "x").mean();
        double meanY = mean();
        double numerator = 0.0;
        double denominator = 0.0;

        for (int i = 0; i < xData.length; i++) {
            numerator += (xData[i] - meanX) * (yData[i] - meanY);
            denominator += Math.pow(xData[i] - meanX, 2);
        }

        return numerator / denominator;
    }

    /**
     * 計算回歸線的截距（alpha）。
     * 
     * @return 截距值。計算方法是 y 數據的平均值減去斜率乘以 x 數據的平均值。
     */
    public double calculateIntercept() {
        double meanX = new DescriptiveStatistics(xData, "x").mean();
        double meanY = mean();
        return meanY - calculateSlope() * meanX;
    }

    /**
     * 使用線性回歸模型預測給定 x 值的 y 值。
     *
     * @param x 自變量 x 的值。
     * @return 預測的 y 值。計算方法是截距加上斜率乘以 x。
     */
    public double predict(double x) {
        return calculateIntercept() + calculateSlope() * x;
    }

    /**
     * 提供線性回歸模型的摘要。
     *
     * @return 描述線性回歸模型的字符串，包括斜率、截距和回歸方程。
     */
    public String summary() {
        return "線性回歸模型 - " + getName() + "\n斜率 (beta0): " + calculateSlope() + "\n截距 (beta1): " + calculateIntercept() + "\n本數據的迴歸模型是: ŷ = " + calculateSlope() + "x + " + calculateIntercept();
    }
    
    /**
     * 提供線性回歸的基本概念和模型解釋。
     * 
     * @return 線性回歸的基本概念和模型解釋的字符串。
     */
    public String explain() {
        return "Statitical (True) Model is: y = f(x) + ε \n" +
               "其中： y = 應變數； x = 自變數 \n" +
               "Statitical (True) Model ----->  Fitted model , eg: ŷ = b1x + b0\n" +
               "利用 data 來尋找Ｙ和Ｘ的關係（不一定是因果關係）\n";
    }
    
    /**
     * 提供簡單線性回歸的詳細說明和公式。
     * 
     * @return 簡單線性回歸的詳細說明和公式的字符串。
     */
    public String description() {
        return "以下是簡單線性回歸的概念\n" +
               "=> involves one independent variable and one dependent variable.\n" +
               "Suppose: y = B0 + B1x + ε , then E(y) = B0 + B1x , ε~NID(0 , σ^2)\n" +
               "Sampling and Fitted : ŷ = b0 + b1x\n" +
               "Estimated: ŷ = b0 + b1x -----> E(y) = B0 + B1x\n" +
               "b0 -----> B0  ;  b1 -----> B1\n" +
               "(residual) e = y - ŷ  -----> ε = y - E(y)\n" +
               "by min Σ(y - ŷ)^2 = min Σ(y - b0 + b1x)^2\n" +
               "由 ∂SSE / ∂b0 = 0  and  ∂SSE / ∂b1 = 0\n" +
               "得 b1 = Σ(x - x̄)^2 (y - ȳ)^2 / Σ(x - x̄)^2\n" +
               "b0 = ȳ - b1x̄";
    }
}
