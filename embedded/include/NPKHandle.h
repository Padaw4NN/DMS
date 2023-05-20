#include <Arduino.h>
#include <SoftwareSerial.h>

#define RE 12
#define DE 14
#define RX 26
#define TX 25

const byte N_setting[] = { 0x01,0x03, 0x00, 0x1e, 0x00, 0x01, 0xe4, 0x0c };
const byte P_setting[] = { 0x01,0x03, 0x00, 0x1f, 0x00, 0x01, 0xb5, 0xcc };
const byte K_setting[] = { 0x01,0x03, 0x00, 0x20, 0x00, 0x01, 0x85, 0xc0 };

SoftwareSerial mod(RX, TX);


byte N;
std::vector<std::string> N_vec;
byte P;
std::vector<std::string> P_vec;
byte K;
std::vector<std::string> K_vec;


void readNitrogen()
{
    digitalWrite(DE, HIGH);
    digitalWrite(RE, HIGH);

    delay(300);

    if ( mod.write(N_setting, sizeof(N_setting)) == 8 ) {
        digitalWrite(DE, LOW);
        digitalWrite(RE, LOW);
        Serial.println("Reading Nitrogen...");
        for (std::size_t i{}; i < 7; i++) {
            N = mod.read();
            if (i == 5) {
                Serial.print("N:" );
                Serial.println(N);
                N_vec.push_back(std::to_string(N));
            }
            delay(100);
        }
    }
}

void readPhosporus()
{
    digitalWrite(DE, HIGH);
    digitalWrite(RE, HIGH);

    delay(300);

    if ( mod.write(P_setting, sizeof(P_setting)) == 8 ) {
        digitalWrite(DE, LOW);
        digitalWrite(RE, LOW);
        Serial.println("Reading Phosphorus...");
        for (std::size_t i{}; i < 7; i++) {
            P = mod.read();
            if (i == 5) {
                Serial.print("P:" );
                Serial.println(P);
                P_vec.push_back(std::to_string(P));
            }
            delay(100);
        }
    }
}

void readPotassium()
{
    digitalWrite(DE, HIGH);
    digitalWrite(RE, HIGH);

    delay(300);

    if ( mod.write(K_setting, sizeof(K_setting)) == 8 ) {
        digitalWrite(DE, LOW);
        digitalWrite(RE, LOW);
        Serial.println("Reading Potassium...");
        for (std::size_t i{}; i < 7; i++) {
            K = mod.read();
            if (i == 5) {
                Serial.print("K:" );
                Serial.println(K);
                K_vec.push_back(std::to_string(K));
            }
            delay(100);
        }
    }
}
