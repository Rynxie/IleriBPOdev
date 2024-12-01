package com.tuna.serialcontrol;

import com.fazecast.jSerialComm.SerialPort;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        SerialPort serialPort = SerialPort.getCommPort("/dev/ttyACM1");
        serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);

        if (!serialPort.openPort()) {
            System.out.println("Seri port açılamadı!");
            return;
        }

        System.out.println("Arduino'ya bağlandı. Durum okunuyor...");
        Scanner scanner = new Scanner(serialPort.getInputStream());

        try {
            while (true) {
                if (scanner.hasNextLine()) {
                    String data = scanner.nextLine().trim(); // Satır sonu karakterlerini temizle
                    if (data.equals("1")) {
                        System.out.println("Butona basılıyor");
                    } else if (data.equals("0")) {
                        System.out.println("Butona basılmıyor");
                    } else {
                        System.out.println("Beklenmeyen veri alındı: " + data);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Hata: " + e.getMessage());
        } finally {
            serialPort.closePort();
            System.out.println("Seri port kapatıldı.");
        }
    }
}

