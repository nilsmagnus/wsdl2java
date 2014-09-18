#!/bin/sh
cd plugin && gradle clean build uA
cd ..
cd consumer && gradle clean build