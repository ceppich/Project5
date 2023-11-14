/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.project5_algos;
import java.util.Scanner;


/**
 *
 * @author 13016
 */
public class Project5_Algos {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        int numIntersections = inputScanner.nextInt();
        int numRoads = inputScanner.nextInt();
        int numCities = inputScanner.nextInt();
        
        double [][] map = new double[numIntersections][numIntersections];
        
        for (int i = 0; i < numRoads; i++){
            int intersection1 = inputScanner.nextInt();
            int intersection2 = inputScanner.nextInt();
            map[intersection1][intersection2] = inputScanner.nextDouble();
            map[intersection2][intersection1] = map[intersection1][intersection2];
        }
        
        String [] cityList = new String[numIntersections];
        
        for (int i = 0; i < numCities; i++){
            cityList[inputScanner.nextInt()] = inputScanner.next();
        }
        
        int numSigns = inputScanner.nextInt();
        
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
        
        for (int i = 0; i < numSigns; i++){
            
        }
            
        

    }
    
}
