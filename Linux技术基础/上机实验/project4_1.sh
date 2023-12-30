#!/bin/bash

hostname -I

iwantip=$(ifconfig | grep '\<inet\>'| grep -v '127.0.0.1' | awk '{ print $2}' | awk 'NR==1' | cut -c 1-11) 


for ip in $iwantip.{1..255} ;
do
ping $ip -c 2 &> /dev/null ;

if [ $? -eq 0 ];
then
echo $ip is alive
else
echo $ip is down
fi
done
