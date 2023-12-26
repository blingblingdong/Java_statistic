## 程式設計一期末報告

### 題目：統計

Tester.java 關於使用流程

DescriptiveStatistics.java統計class


### Description.Rmd

一些rjava範例

### src 

javadoc 網頁版
用javadoc DescriptiveStatistics.java生成

### 文檔

DescriptiveStatistics.java pdf與word版本的doc

# DescriptiveStatistics 類別說明

**類別名稱：** `DescriptiveStatistics`

**類別說明：** 
這個類別提供了計算數據集描述性統計的方法，包括平均值、中位數、標準偏差等。

## 建構函數

| 建構函數 | 描述 |
| --- | --- |
| `DescriptiveStatistics(double[] data, String name)` | 用指定的數據陣列和數據集名稱初始化 DescriptiveStatistics 實例。 |

## 方法

| 方法名 | 返回類型 | 描述 |
| --- | --- | --- |
| `getData()` | `double[]` | 返回當前數據集。 |
| `getName()` | `String` | 返回數據集的名稱。 |
| `setData(double[] data)` | `void` | 設置新的數據集。 |
| `setName(String name)` | `void` | 設置數據集的新名稱。 |
| `mean()` | `double` | 計算數據集的平均值。 |
| `median()` | `double` | 計算數據集的中位數。 |
| `standardDeviation()` | `double` | 計算數據集的標準偏差。 |
| `sampleSize()` | `int` | 返回數據集的樣本大小。 |
| `populationVariance()` | `double` | 計算數據集的總體方差。 |
| `populationStandardDeviation()` | `double` | 計算數據集的總體標準偏差。 |
| `information()` | `String` | 提供數據集的描述性統計摘要。 |
