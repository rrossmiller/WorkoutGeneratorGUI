#! /usr/bin/env zsh

cd src
rm *.class
javac *.java
java GUI
rm *.class
open ~/Desktop/SpinWorkout.txt
