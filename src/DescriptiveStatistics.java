import java.util.Arrays;

/**
 * DescriptiveStatistics 類別提供了計算數據集描述性統計的方法。
 * 這些統計包括平均值、中位數、標準偏差、樣本大小、母體方差和母體標準偏差。
 */
public class DescriptiveStatistics {

    private double[] data;
    private String name;

    /**
     * 使用指定的數據構造一個新的 DescriptiveStatistics 實例。
     *
     * @param data 要分析的雙精度值數組
     * @param name 數據的名稱
     */
    public DescriptiveStatistics(double[] data, String name) {
        this.data = data;
        this.name = name;
    }

    /**
     * 獲取當前數據集。
     *
     * @return 當前數據集
     */
    public double[] getData() {
        return data;
    }

    /**
     * 獲取數據集的名稱。
     *
     * @return 數據集的名稱
     */
    public String getName() {
        return name;
    }

    /**
     * 將數據集設置為指定的雙精度數組。
     *
     * @param data 新的數據集
     */
    public void setData(double[] data) {
        this.data = data;
    }

    /**
     * 設置數據集的名稱。
     *
     * @param name 數據集的新名稱
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 計算數據集的平均值。
     *
     * @return 平均值
     */
    public double mean() {
        double sum = 0.0;
        for (double num : this.data) {
            sum += num;
        }
        return sum / this.data.length;
    }

    /**
     * 計算數據集的中位數。
     *
     * @return 中位數
     */
    public double median() {
        int size = this.data.length;
        double[] sortedData = Arrays.copyOf(this.data, size);
        Arrays.sort(sortedData);
        if (size % 2 == 0) {
            return (sortedData[size / 2 - 1] + sortedData[size / 2]) / 2.0;
        } else {
            return sortedData[size / 2];
        }
    }

    /**
     * 計算數據集的標準偏差。
     *
     * @return 標準偏差
     */
    public double standardDeviation() {
        double mean = mean();
        double sumOfSquares = 0.0;
        for (double num : this.data) {
            sumOfSquares += Math.pow(num - mean, 2);
        }
        return Math.sqrt(sumOfSquares / this.data.length);
    }

    /**
     * 計算數據集的樣本大小。
     *
     * @return 樣本大小
     */
    public int sampleSize() {
        return this.data.length;
    }

    /**
     * 計算數據集的母體方差。
     *
     * @return 母體方差
     */
    public double populationVariance() {
        double mean = mean();
        double sumOfSquares = 0.0;
        for (double num : this.data) {
            sumOfSquares += Math.pow(num - mean, 2);
        }
        return sumOfSquares / this.data.length;
    }

    /**
     * 計算數據集的母體標準偏差。
     *
     * @return 母體標準偏差
     */
    public double populationStandardDeviation() {
        return Math.sqrt(populationVariance());
    }

    /**
     * 提供數據集的描述性統計摘要。
     *
     * @return 描述數據集統計信息的字符串
     */
    public String information() {
        return "數據名稱: " + this.name + "\n平均值: " + mean() + "\n中位數: " + median() + "\n標準偏差: " + standardDeviation() + "\n樣本大小: " + sampleSize() + "\n母體方差: " + populationVariance() + "\n母體標準偏差: " + populationStandardDeviation();
    }
}
