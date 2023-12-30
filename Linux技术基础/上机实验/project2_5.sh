#!/bin/bash

read -p 'please write time: ' -s o
for ((i=$o;i>=0;i--))
do
if [ "$i" = "0" ]
then
echo "time is over! "
exit
fi
((Minute=$i/60))
((Second=$i%60))
echo "$Minute :$Second"
sleep 1
clear
done
