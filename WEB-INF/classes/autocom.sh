#!/usr/bin/env bash

for i in '*.java'
    do
    javac -cp ../../../../lib/servlet-api.jar $i
  done
