import java.io.PrintWriter;
import java.io.IOException;

try (PrintWriter writer = new PrintWriter("example.csv")) {
    StringBuilder sb = new StringBuilder();
    // 添加标题行
    sb.append("id");
    sb.append(",");
    sb.append("name");
    sb.append(",");
    sb.append("value");
    sb.append("\n");

    // 添加一些数据行
    sb.append("1");
    sb.append(",");
    sb.append("Alice");
    sb.append(",");
    sb.append("100");
    sb.append("\n");

    sb.append("2");
    sb.append(",");
    sb.append("Bob");
    sb.append(",");
    sb.append("200");
    sb.append("\n");

    writer.write(sb.toString());
    System.out.println("done!");
} catch (IOException e) {
    System.out.println(e.getMessage());
}
