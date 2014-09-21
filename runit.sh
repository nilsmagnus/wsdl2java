#!/bin/sh
cd plugin && gradle clean build uA && cd .. && cd consumer && rm -rf generated* && gradle clean && gradle build --stacktrace