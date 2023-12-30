#!/bin/bash

echo -n "Please enter your want number: "
read n
first=0
second=0
third=1
for ((i=1;i<=n;i++))
do
echo -n $third" "
let first=$second
let second=$third
let third=$second+$first
done
echo
