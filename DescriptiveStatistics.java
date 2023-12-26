import java.util.Arrays;

public class DescriptiveStatistics {

    private double[] data;

    
    public DescriptiveStatistics(double[] data) {
        this.data = data;
    }
    
    //getters and setters
    
    public double[] getData() {
        return data;
    }
    
    public void setData(double[] data) {
        this.data = data;
    }

    
    public double mean() {
        double sum = 0.0;
        for (double num : this.data) {
            sum += num;
        }
        return sum / this.data.length;
    }

    
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

    
    public double standardDeviation() {
        double mean = mean();
        double sumOfSquares = 0.0;
        for (double num : this.data) {
            sumOfSquares += Math.pow(num - mean, 2);
        }
        return Math.sqrt(sumOfSquares / this.data.length);
    }

    
    public int sampleSize() {
        return this.data.length;
    }

    
    public double populationVariance() {
        double mean = mean();
        double sumOfSquares = 0.0;
        for (double num : this.data) {
            sumOfSquares += Math.pow(num - mean, 2);
        }
        return sumOfSquares / this.data.length;
    }

    
    public double populationStandardDeviation() {
        return Math.sqrt(populationVariance());
    }

  
}
