package com.tuna.serialcontrol;

import com.fazecast.jSerialComm.SerialPort;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        SerialPort serialPort = SerialPort.getCommPort("/dev/ttyACM0");
        serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);

        if (!serialPort.openPort()) {
            System.out.println("Seri port açılamadı!");
            return;
        }

        System.out.println("Seri port bağlantısı kuruldu: /dev/ttyACM0");
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.print("LED'i yakmak için '1', söndürmek için '0' girin ('q' ile çık): ");
                String command = scanner.nextLine();

                if (command.equals("q")) {
                    System.out.println("Çıkılıyor...");
                    break;
                } else if (command.equals("1") || command.equals("0")) {
                    serialPort.getOutputStream().write(command.getBytes());
                    serialPort.getOutputStream().flush();
                    System.out.println("Komut gönderildi: " + command);
                } else {
                    System.out.println("Geçersiz komut!");
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

