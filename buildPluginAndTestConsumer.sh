#!/bin/bash

cd plugin && ../gradlew clean build uA && cd ../consumer && ../gradlew clean build --stacktrace
