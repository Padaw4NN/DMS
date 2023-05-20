#ifndef WIFICONNECTION_H
#define WIFICONNECTION_H


#include <WiFi.h>
#include <Arduino.h>

void wifiInit(String ssid, String password)
{
    delay(2000);
    WiFi.begin(ssid.c_str(), password.c_str());

    Serial.print("\n\n[*] Connecting to ");
    Serial.print(ssid);

    delay(1000);

    while (WiFi.status() != WL_CONNECTED)
    {
        Serial.print('.');
        delay(500);
    }

    Serial.print("\n[*] Connected (");
    Serial.print(WiFi.localIP());
    Serial.print(")\n\n");

    delay(300);
}


#endif