#ifndef FIREBASECONNECTION_H
#define FIREBASECONNECTION_H


#include <Arduino.h>
#include <FirebaseESP32.h>
#include "addons/TokenHelper.h"

FirebaseData      fb_data;
FirebaseAuth      fb_auth;
FirebaseJson      fb_json;
FirebaseConfig    fb_config;

String fb_uid = "";


void firebaseInit(String api_key, String database_url)
{
    fb_config.api_key = api_key.c_str();
    fb_config.database_url = database_url.c_str();
    
    fb_auth.user.email = "********";
    fb_auth.user.password = "*****";

    Firebase.reconnectWiFi(true);
    fb_data.setResponseSize(4096);

    fb_config.token_status_callback = tokenStatusCallback;
    Firebase.begin(&fb_config, &fb_auth);

    Serial.println("Getting User UID");
    while ((fb_auth.token.uid) == "") {
        Serial.print('.');
        delay(1000);
    }

    fb_uid = fb_auth.token.uid.c_str();
    Serial.print("User: ");
    Serial.println(fb_uid);

    Firebase.setReadTimeout(fb_data, (1000 * 60));
    Firebase.setwriteSizeLimit(fb_data, "tiny");  
}

void sendData(String values)
{
    fb_json.set("/data", values.c_str());
    Firebase.updateNode(fb_data, "/sensor", fb_json);
}

void sendDataChildren(String value)
{
    fb_json.set("/data", value.c_str());
    Firebase.updateNodeSilent(fb_data, "/sensor", fb_json);
}

void sendDataByJson(std::vector<std::string>& N, std::vector<std::string>& P, std::vector<std::string>& K)
{
    FirebaseJson jsonN;
    FirebaseJson jsonP;
    FirebaseJson jsonK;

    jsonN.toString(Serial, true);
    jsonP.toString(Serial, true);
    jsonK.toString(Serial, true);


    for (std::size_t i{}; i < N.size(); ++i) {
        std::string temperature = "N/[010";
        temperature = temperature + std::to_string(i) + "]";
        jsonN.set(temperature.c_str(), N.at(i).c_str());
        Serial.println(temperature.c_str());
    }

    for (std::size_t i{}; i < P.size(); ++i) {
        std::string humidity = "P/[010"; 
        humidity = humidity + std::to_string(i) + "]";
        jsonP.set(humidity.c_str(), P.at(i).c_str());
        Serial.println(humidity.c_str());
    }

    for (std::size_t i{}; i < K.size(); ++i) {
        std::string humidity = "K/[010"; 
        humidity = humidity + std::to_string(i) + "]";
        jsonK.set(humidity.c_str(), K.at(i).c_str());
        Serial.println(humidity.c_str());
    }
    
    Firebase.set(fb_data, "/N", jsonN);
    Firebase.set(fb_data, "/P", jsonP);
    Firebase.set(fb_data, "/K", jsonK);
}

void sendDataByJson(std::vector<std::string>& temps)
{
    FirebaseJson json;
    json.toString(Serial, true);

    for (std::size_t i{}; i < temps.size(); ++i) {
        std::string temp = "temp/[10";
        temp = temp + std::to_string(i) + "]";
        json.set(temp.c_str(), temps.at(i).c_str());
        Serial.println(temp.c_str());
    }

    Firebase.set(fb_data, "/temp", json);
}

#endif
