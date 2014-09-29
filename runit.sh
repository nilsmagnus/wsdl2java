#!/bin/sh
cd plugin && ../gradlew clean build uA && cd .. && cd consumer && rm -rf generated* && ../gradlew clean build --stacktrace && ../gradlew xsd2java