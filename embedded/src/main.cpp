#include <WiFi.h>
#include <Arduino.h>
#include <FirebaseESP32.h>
#include <SoftwareSerial.h>

#include "../include/NPKHandle.h"
#include "../include/DHTHandle.h"
#include "../include/WifiConnection.h"
#include "../include/FirebaseConnection.h"


#define TEN_MINUTES ((1000 * 60) * 10)

String wifi_ssid = "*****";
String wifi_pswd = "*****";
String fb_key = "********";
String fb_url = "********";


void setup()
{
  Serial.begin(4800);
  mod.begin(4800);

  wifiInit(wifi_ssid, wifi_pswd);
  firebaseInit(fb_key, fb_url);

  pinMode(RE, OUTPUT);
  pinMode(DE, OUTPUT);
}

void loop()
{
  Serial.println("Response vector:");

  for (std::size_t i{}; i < 10; ++i) {
    readNitrogen();
    readPhosporus();
    readPotassium();
  }

  Serial.println("Send data to fireabase...");
  delay(2000);
  sendDataByJson(N_vec, P_vec, K_vec);

  Serial.println("\n");
  delay(TEN_MINUTES);
}
