#!/bin/bash

echo "请输入给定目录："
read n
cd $n
if [ -f project1 ] 
then 
     echo "project1 exist"
else
     echo "project1 doesn't exist"
fi
if [ -d net ] 
then 
     echo "/net exist"
else
     echo "/net doesn't exist"
fi
 

