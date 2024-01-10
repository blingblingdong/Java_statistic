import java.util.Arrays;

/**
 * DataPreprocessing 類提供了數據預處理和清洗的基本方法。
 * 這個類繼承自 DescriptiveStatistics，可以用於對數據集進行基本的統計分析和預處理。
 */
public class DataPreprocessing extends DescriptiveStatistics {

    /**
     * 使用指定的數據構造一個新的 DataPreprocessing 實例。
     *
     * @param data 要分析和處理的雙精度值數組。
     * @param name 數據集的名稱。
     */
    public DataPreprocessing(double[] data, String name) {
        super(data, name);
    }

    /**
     * 移除數據集中的無效值。
     * 這個方法將從數據集中移除所有 NaN 值或其他特定的無效值。
     */
    public void removeInvalidValues() {
        double[] newData = Arrays.stream(getData())
                                 .filter(x -> !Double.isNaN(x)) // 移除 NaN 值
                                 .toArray();
        setData(newData);
    }


    /**
     * 對數據進行標準化。
     * 這個方法將數據轉換為標準正態分佈（均值為 0，標準差為 1）。
     */
    public void standardizeData() {
        double mean = mean();
        double stdDev = standardDeviation();
        double[] newData = Arrays.stream(getData())
                                 .map(x -> (x - mean) / stdDev)
                                 .toArray();
        setData(newData);
    }

    /**
     * 對數據進行範圍縮放。
     * 這個方法將數據縮放到指定的最小值和最大值之間。
     *
     * @param min 縮放範圍的最小值。
     * @param max 縮放範圍的最大值。
     */
    public void scaleData(double min, double max) {
        double dataMin = Arrays.stream(getData()).min().orElse(Double.NaN);
        double dataMax = Arrays.stream(getData()).max().orElse(Double.NaN);
        double[] newData = Arrays.stream(getData())
                                 .map(x -> (x - dataMin) / (dataMax - dataMin) * (max - min) + min)
                                 .toArray();
        setData(newData);
    }

    /**
     * 移除數據集中的重複值。
     */
    public void removeDuplicates() {
        double[] newData = Arrays.stream(getData()).distinct().toArray();
        setData(newData);
    }

    /**
     * 根據指定的條件過濾數據。
     * 這個方法僅保留大於指定閾值的數據點。
     *
     * @param threshold 過濾閾值。
     */
    public void filterData(double threshold) {
        double[] newData = Arrays.stream(getData())
                                 .filter(x -> x > threshold)
                                 .toArray();
        setData(newData);
    }
}
