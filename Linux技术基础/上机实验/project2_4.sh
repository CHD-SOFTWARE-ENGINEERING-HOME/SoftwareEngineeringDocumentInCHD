#!/bin/bash
read -p "请输入十个整数：" list
arr=($list)
echo "原数组的顺序为：${arr[@]}"
length=${#arr[@]}
for ((i=1;i<$length;i++))
do
  for ((j=0;j<$length-$i;j++))
  do
  first=${arr[$j]}
  k=$[$j+1]
  second=${arr[$k]}
  if [ $first -gt $second ]
  then
    temp=${arr[$j]}
    arr[$j]=$second
    arr[$k]=$temp
  fi
  done
done
 echo "排序后新的数组顺序为：${arr[@]}"

