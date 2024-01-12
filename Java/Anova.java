import java.util.Arrays;

/**
 * Anova 類別提供了執行單因素方差分析（ANOVA）的方法。
 * 這個類別繼承自 DescriptiveStatistics，用於分析多組數據集之間的均值是否存在顯著差異。
 */
public class Anova extends DescriptiveStatistics {
    private double[][] groups;

    /**
     * 使用多組數據構造 Anova 對象。
     *
     * @param groups 二維數組，每個子數組代表一組數據。
     * @param name 數據集的名稱。
     */
    public Anova(double[][] groups, String name) {
        super(flatten(groups), name);
        this.groups = groups;
    }

    /**
     * 將二維數組展平為一維數組。
     *
     * @param arrays 要展平的二維數組。
     * @return 展平後的一維數組。
     */
    public static double[] flatten(double[][] arrays) {
        return Arrays.stream(arrays).flatMapToDouble(Arrays::stream).toArray();
    }

    /**
     * 計算總體均值。
     *
     * @return 總體均值。
     */
    public double overallMean() {
        return mean();
    }

    /**
     * 計算總體平方和（SST）。
     *
     * @return 總體平方和。
     */
    public double totalSumOfSquares() {
        double overallMean = overallMean();
        return Arrays.stream(this.getData()).map(x -> Math.pow(x - overallMean, 2)).sum();
    }

    /**
     * 計算組間平方和（SSB）。
     *
     * @return 組間平方和。
     */
    public double betweenGroupSumOfSquares() {
        double overallMean = overallMean();
        return Arrays.stream(groups).mapToDouble(group -> 
            group.length * Math.pow(new DescriptiveStatistics(group, "").mean() - overallMean, 2)
        ).sum();
    }

    /**
     * 計算組內平方和（SSW）。
     *
     * @return 組內平方和。
     */
    public double withinGroupSumOfSquares() {
        return totalSumOfSquares() - betweenGroupSumOfSquares();
    }

    /**
     * 提供 ANOVA 分析的摘要。
     *
     * @return 描述 ANOVA 分析結果的字符串。
     */
    public String summary() {
        return "ANOVA 分析 - " + getName() + "\n總體平方和 (SST): " + totalSumOfSquares() +
               "\n組間平方和 (SSB): " + betweenGroupSumOfSquares() +
               "\n組內平方和 (SSW): " + withinGroupSumOfSquares();
    }
    
    /**
     * 計算 ANOVA 的 F 值。
     * F 值是用於測量組間變異與組內變異的比率。
     *
     * @return F 統計量的值。
     */
    public double calculateFValue() {
        double ssb = betweenGroupSumOfSquares(); // 已計算的組間平方和
        double ssw = withinGroupSumOfSquares(); // 已計算的組內平方和
        int dfBetween = groups.length - 1; // 組間自由度
        int dfWithin = Arrays.stream(groups).mapToInt(arr -> arr.length).sum() - groups.length; // 組內自由度

        double msb = ssb / dfBetween; // 組間均方
        double msw = ssw / dfWithin; // 組內均方

        return msb / msw; // 計算 F 值
    }
    
    /**
     * 提供 ANOVA 分析的基本概念和使用方法的解釋。
     *
     * @return ANOVA 分析的基本概念和方法的字符串描述。
     */
    public String explain() {
        return "ANOVA (分析變異) 用於比較三個或更多組的平均數是否有顯著差異。" +
               "它將總變異分解為組間變異和組內變異，並通過 F 統計量來評估組間變異是否顯著大於組內變異。";
    }

    /**
     * 提供 ANOVA 的詳細描述和公式。
     *
     * @return ANOVA 的詳細描述和公式的字符串描述。
     */
    public String description() {
        return "ANOVA 通過計算 F 值來測試組間差異的顯著性。" +
               "F 值是組間均方 (MSB) 和組內均方 (MSW) 的比率，" +
               "其中 MSB = 組間平方和(SSB) / 組間自由度(dfBetween)，" +
               "MSW = 組內平方和(SSW) / 組內自由度(dfWithin)。" +
               "高 F 值通常表明組間變異顯著大於組內變異，從而指示組間存在顯著差異。";
    }
}
