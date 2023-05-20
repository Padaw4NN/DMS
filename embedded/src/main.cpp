#include <WiFi.h>
#include <Arduino.h>
#include <FirebaseESP32.h>

#include "../lib/WifiConnection.h"
#include "../lib/FirebaseConnection.h"

String wifi_ssid = "<ssid>";
String wifi_pswd = "<password>";
String fb_key = "<api key>";
String fb_url = "<firebase database url>";


void setup()
{
  Serial.begin(9600);
  wifiInit(wifi_ssid, wifi_pswd);
  firebaseInit(fb_key, fb_url);
}

void loop()
{
//generic data example
  sendData("0.232");
  sendData("0.221");
  sendData("0.223");
  sendData("0.212");
  sendData("0.211");
  sendData("0.202");
}
