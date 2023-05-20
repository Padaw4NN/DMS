#ifndef DHTHANDLE_H
#define FHTHANDLE_H

#include <DHT.h>
#define DHTTYPE 11

const int pinOutDHT = 4;

DHT dht(pinOutDHT, DHTTYPE);

std::vector<std::string> temps;
void readTemperatureFromDHT()
{
    float temp = dht.readTemperature();
    Serial.println(temp);
    temps.push_back(std::to_string(temp));
}


#endif