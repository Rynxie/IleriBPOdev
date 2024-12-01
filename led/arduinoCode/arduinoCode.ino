void setup() {
    pinMode(13, OUTPUT); // Yerleşik LED pini çıkış olarak ayarlandı
    Serial.begin(9600); // Seri haberleşme hızı
}

void loop() {
    if (Serial.available()) { // Seri portta veri var mı kontrol et
        char command = Serial.read(); // Gelen veriyi oku
        if (command == '1') { // '1' komutu LED'i yakar
            digitalWrite(13, HIGH);
        } else if (command == '0') { // '0' komutu LED'i söndürür
            digitalWrite(13, LOW);
        }
    }
}

