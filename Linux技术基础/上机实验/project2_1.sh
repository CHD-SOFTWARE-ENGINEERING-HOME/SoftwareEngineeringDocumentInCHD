#!/bin/bash

for ((i=1;i<10;i++))
do
for ((k=1;k<10-i;k++))
do
echo -n " "
done
for ((j=1;j<i;j++))
do
echo -n "* "
done
echo
done
