#!/bin/bash

echo "please write number n:"
read n
s=0
for ((i=1;i<=$n;i++))
do
s=$[s+i]
done
echo "1+2+...+n="
echo $s
