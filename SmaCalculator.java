import java.io.*;
import java.util.*;

public class SmaCalculator {
    private static final int SMA_WINDOW = 10; 

    public static void main(String[] args) {
        String inputFile = "RELIANCE_DATA.csv"; 
        String outputFile = "RELIANCE_SMA.csv"; 

        
        List<String[]> data = readCSV(inputFile);
        if(data.isEmpty()){
            System.out.println("No valid data found in CSV.");
            return;
        }
        
        List<Double> smaOpen = calculateSMA(data, 1, SMA_WINDOW);
        List<Double> smaHigh = calculateSMA(data, 2, SMA_WINDOW);
        List<Double> smaLow = calculateSMA(data, 3, SMA_WINDOW);
        List<Double> smaClose = calculateSMA(data, 4, SMA_WINDOW);
        List<Double> smaAdjClose = calculateSMA(data, 5, SMA_WINDOW);
        List<Double> smaVolume = calculateSMA(data, 6, SMA_WINDOW); 

        writeCSV(outputFile, data, smaOpen, smaHigh, smaLow, smaClose, smaAdjClose, smaVolume);
        System.out.println("SMA calculation completed. Output saved to: " + outputFile);
    }

    private static List<String[]> readCSV(String inputFile){
        List<String[]> data = new ArrayList<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(inputFile))){
            String line;
    
            while((line = br.readLine()) != null){
                String[] values = line.split(",");

                if(values.length < 7 ||    
                    values[1].trim().isEmpty() ||  
                    values[2].trim().isEmpty() ||  
                    values[3].trim().isEmpty() ||  
                    values[4].trim().isEmpty() ||  
                    values[5].trim().isEmpty() ||    
                    values[6].trim().isEmpty()){ 
                    continue;
                }
                data.add(values);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return data;
    }
    
    private static List<Double> calculateSMA(List<String[]> data, int columnIndex, int period){
        List<Double> smaValues = new ArrayList<>(Collections.nCopies(data.size(), Double.NaN));
        Deque<Double> window = new LinkedList<>();
        double sum = 0;
        
        for(int i=1; i<data.size(); i++){
            try{
                double value = Double.parseDouble(data.get(i)[columnIndex]);
                window.addLast(value);
                sum += value;
            }catch(NumberFormatException e){
                window.addLast(Double.NaN); 
            }
            if(window.size() > period){
                sum -= window.removeFirst();
            }
            if(window.size() == period){
                smaValues.set(i, sum / period);
            }
        }
        return smaValues;
    }

    private static void writeCSV(String outputFile, List<String[]> data, 
                                 List<Double> smaOpen, List<Double> smaHigh, 
                                 List<Double> smaLow, List<Double> smaClose, 
                                 List<Double> smaAdjClose, List<Double> smaVolume){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))){
            
            bw.write(String.join(",", data.get(0)) + ",SMA(Open) 10D,SMA(High) 10D,SMA(Low) 10D,SMA(Close) 10D,SMA(Adj Close) 10D,SMA(Volume)\n");
        

            for (int i = 1; i < data.size(); i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(String.join(",", data.get(i))); 
            
                
                sb.append(",").append(getSMAValue(smaOpen, i, false));
                sb.append(",").append(getSMAValue(smaHigh, i, false));
                sb.append(",").append(getSMAValue(smaLow, i, false));
                sb.append(",").append(getSMAValue(smaClose, i, false));
                sb.append(",").append(getSMAValue(smaAdjClose, i, false));
                sb.append(",").append(getSMAValue(smaVolume, i, true)); 
            
                sb.append("\n"); 
                bw.write(sb.toString());
            }
            

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private static String getSMAValue(List<Double> smaList, int index, boolean isVolume) {
        if (Double.isNaN(smaList.get(index))) {
            return "";
        }
        return isVolume ? String.format("%.0f", smaList.get(index)) : String.format("%.3f", smaList.get(index));
    }
    

} 