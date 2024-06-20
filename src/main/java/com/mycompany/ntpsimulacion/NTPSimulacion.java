/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ntpsimulacion;

/**
 *
 * @author SEBASTIAN
 */
import java.time.Instant;
import java.util.Random;

public class NTPSimulacion {
    static class TimeServer {
        private long offset;
        
        public TimeServer() {
            // Simulamos una pequeña desviación del tiempo "real"
            this.offset = new Random().nextInt(1000);
        }
        
        public long getTime() {
            return System.currentTimeMillis() + offset;
        }
    }
    
    static class NTPClient {
        private long localTime;
        
        public NTPClient() {
            this.localTime = System.currentTimeMillis();
        }
        
        public void syncWithServer(TimeServer server) {
            long t1 = System.currentTimeMillis();
            long serverTime = server.getTime();
            long t4 = System.currentTimeMillis();
            
            // Tiempo de ida y vuelta
            long roundTripTime = t4 - t1;
            // Estimamos la latencia como la mitad del tiempo de ida y vuelta
            long latency = roundTripTime / 2;
            
            // Calculamos el offset entre nuestro tiempo local y el tiempo del servidor
            long offset = (serverTime + latency) - t4;
            
            // Ajustamos nuestro tiempo local
            localTime = System.currentTimeMillis() + offset;
            
            System.out.println("Tiempo local antes de la sincronizacion: " + Instant.ofEpochMilli(t1));
            System.out.println("Tiempo del servidor: " + Instant.ofEpochMilli(serverTime));
            System.out.println("Tiempo local despues de la sincronizacion: " + Instant.ofEpochMilli(localTime));
            System.out.println("Offset aplicado: " + offset + " ms");
            System.out.println("Latencia estimada: " + latency + " ms");
        }
    }
    
    public static void main(String[] args) {
        TimeServer server = new TimeServer();
        NTPClient client = new NTPClient();
        
        System.out.println("Simulacion de sincronizacion NTP:");
        client.syncWithServer(server);
    }
}