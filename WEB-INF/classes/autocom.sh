#!/usr/bin/env bash

for i in 'ls *.java'
    do
    javac -cp ..\..\..\..\lib\servlet-api.jar $i
  done