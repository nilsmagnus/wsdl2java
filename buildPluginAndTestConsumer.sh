#!/bin/bash

cd plugin && gradle clean build uA && cd ../consumer && gradle clean build --stacktrace