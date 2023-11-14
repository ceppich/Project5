package com.mycompany.project5_algos;
import java.util.Scanner;


/**
 *
 * @author 13016
 */
public class Project5_Algos {

    private static final String spaces = "                    ";
    
    public static void main(String[] args) {
        //gather initial defining input
        Scanner inputScanner = new Scanner(System.in);
        int numIntersections = inputScanner.nextInt();
        int numRoads = inputScanner.nextInt();
        int numCities = inputScanner.nextInt();
        
        //create map
        double [][] map = new double[numIntersections][numIntersections];
        
        //gather all roads
        for (int i = 0; i < numRoads; i++){
            int intersection1 = inputScanner.nextInt();
            int intersection2 = inputScanner.nextInt();
            map[intersection1][intersection2] = inputScanner.nextDouble();
            map[intersection2][intersection1] = map[intersection1][intersection2];
        }
        
        //create city list
        String [] cityList = new String[numIntersections];
        
        //input scanner list
        for (int i = 0; i < numCities; i++){
            cityList[inputScanner.nextInt()] = inputScanner.next();
        }
        
        //gather number of signs
        int numSigns = inputScanner.nextInt();
        
        //create previous and best arrays
        double [][] best = new double[numIntersections][numIntersections];
        int [][] prev = new int[numIntersections][numIntersections];
                
        //copy map for best array
        for (int u = 0; u < numIntersections; u++){
            for (int v = 0; v < numIntersections; v++){
                if (map[u][v] > 0.0){
                    best[u][v] = map[u][v];
                    prev[u][v] = u;
                }
                else if (u == v) {
                    best[u][v] = 0.0;
                    prev[u][v] = u;
                }
                else {
                    best[u][v] = Double.MAX_VALUE;
                }
            }
        }
        
        //All-Pairs Shortest Paths with Floyd's Algo :)
        for (int k = 0; k < numIntersections; k++){
            for (int u = 0; u < numIntersections; u++){
                for (int v = 0; v < numIntersections; v++){
                    if ((best[u][k] + best[k][v] ) < best[u][v]){
                        best[u][v] = best[u][k] + best[k][v];
                        prev[u][v] = prev[k][v];
                    }
                }
            }
        }
            
        //use best and prev to make road signs
        for (int i = 0; i < numSigns; i++){
            int u = inputScanner.nextInt();
            int v = inputScanner.nextInt();
            double dist = inputScanner.nextDouble();
            
            int numCitiesOnSign = 0;
            String[] output = new String[numIntersections];
            int[] distList = new int[numIntersections];
            
            //determine which cities's shortest past come from current road
            for (int j = 0; j < numIntersections; j++) {
                if (prev[j][u] == v) {
                    if (cityList[j] != null) {
                        int totalDist = (int)(best[u][j] + 0.5 - dist);
                        output[numCitiesOnSign] = cityList[j] + spaces.substring(cityList[j].length()) + totalDist;
                        distList[numCitiesOnSign++] = totalDist;
                        
                    }
                }
            }
            
            //sort results for output of signs
            for (int j = numCitiesOnSign - 1; j >= 0; j--) {
                for (int k = 0; k < j; k++) {
                    if (distList[k] > distList[k + 1]) {
                        int temp = distList[k];
                        distList[k] = distList[k + 1];
                        distList[k + 1] = temp;
                        String tempString = output[k];
                        output[k] = output[k + 1];
                        output[k + 1] = tempString;
                    }
                }
            }
            
            //print output
            for (int j = 0; j < numCitiesOnSign; j++) {
                System.out.println(output[j]);
            }
            System.out.println("");
        }
            
        

    }
    
}
