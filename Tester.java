import java.util.Arrays;
import java.util.Scanner;


public class Tester {
    public static void main(String[] args) {
      
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the number of data points: ");
        
        int n = sc.nextInt();
        
        double[] data = new double[n];
        
        System.out.println("Enter the data points: ");
        
        for (int i = 0; i < n; i++) {
            data[i] = sc.nextDouble();
        }
        
        DescriptiveStatistics stats = new DescriptiveStatistics(data);
        
        System.out.println(stats.information());
      
    }
}