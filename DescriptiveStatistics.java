import java.util.Arrays;

/**
 * The DescriptiveStatistics class provides methods for calculating 
 * descriptive statistics for a dataset. These include mean, median, 
 * standard deviation, sample size, population variance, and population 
 * standard deviation.
 */
public class DescriptiveStatistics {

    private double[] data;

    /**
     * Constructs a new DescriptiveStatistics instance with the specified data.
     *
     * @param data the array of double values to be analyzed
     */
    public DescriptiveStatistics(double[] data) {
        this.data = data;
    }

    /**
     * Gets the current dataset.
     *
     * @return the current dataset
     */
    public double[] getData() {
        return data;
    }

    /**
     * Sets the dataset to the specified array of doubles.
     *
     * @param data the new dataset
     */
    public void setData(double[] data) {
        this.data = data;
    }

    /**
     * Calculates the mean of the dataset.
     *
     * @return the mean value
     */
    public double mean() {
        double sum = 0.0;
        for (double num : this.data) {
            sum += num;
        }
        return sum / this.data.length;
    }

    /**
     * Calculates the median of the dataset.
     *
     * @return the median value
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
     * Calculates the standard deviation of the dataset.
     *
     * @return the standard deviation
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
     * Calculates the size of the dataset.
     *
     * @return the size of the dataset
     */
    public int sampleSize() {
        return this.data.length;
    }

    /**
     * Calculates the population variance of the dataset.
     *
     * @return the population variance
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
     * Calculates the population standard deviation of the dataset.
     *
     * @return the population standard deviation
     */
    public double populationStandardDeviation() {
        return Math.sqrt(populationVariance());
    }

    /**
     * Provides a summary of descriptive statistics for the dataset.
     *
     * @return a string summarizing the statistics of the dataset
     */
    public String information() {
        return "Mean: " + mean() + "\nMedian: " + median() + "\nStandard Deviation: " + standardDeviation() + "\nSample Size: " + sampleSize() + "\nPopulation Variance: " + populationVariance() + "\nPopulation Standard Deviation: " + populationStandardDeviation();
    }
}

