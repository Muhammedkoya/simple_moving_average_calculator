# SmaCalculator 📈  
A **Java program** that reads stock market data from a CSV file, calculates the **Simple Moving Average (SMA)** for key price metrics, and writes the results to a new CSV file.

---

##  Features  
✅ Reads stock market data from a CSV file  
✅ Filters out invalid or incomplete rows  
✅ Computes **SMA (10-day moving average)** for:  
   - Open Price  
   - High Price  
   - Low Price  
   - Close Price  
   - Adjusted Close Price  
   - Volume (rounded to the nearest whole number)  
✅ Writes the updated data with SMA values into a new CSV file  
✅ Efficient **sliding window** implementation for SMA calculation  

---

##  How It Works  
1. **Reads CSV file** (`RELIANCE_DATA.csv`)  
2. **Validates and cleans data** (removes rows with missing values)  
3. **Calculates SMA** (10-day moving average) for multiple stock metrics  
4. **Writes the results** to a new CSV file (`RELIANCE_SMA.csv`)  

---

## compile the java program
1. javac SmaCalculator.java

## run the java program
1. java SmaCalculator

## Expected Input File (RELIANCE_DATA.csv) format
1. The input file should have at least 7 columns with the following format: Date,Open,High,Low,Close,Adj Close,Volume

## Example Input Data
1. 2024-02-01,100.5,102.3,99.8,101.2,101.0,150000
2. 2024-02-02,101.2,103.0,100.1,102.5,102.3,180000
3. ...


## Expected Output File (RELIANCE_SMA.csv) format
1. Date,Open,High,Low,Close,Adj Close,Volume,SMA(Open) 10D,SMA(High) 10D,SMA(Low) 10D,SMA(Close) 10D,SMA(Adj Close) 10D,SMA(Volume) 10D
2. 2024-02-01,100.5,102.3,99.8,101.2,101.0,150000,,,,
3. 2024-02-02,101.2,103.0,100.1,102.5,102.3,180000,,,,
4. ...
5. 2024-02-11,104.3,106.5,103.7,105.8,105.5,200145,102.457,104.789,100.563,103.450,103.876,189235


