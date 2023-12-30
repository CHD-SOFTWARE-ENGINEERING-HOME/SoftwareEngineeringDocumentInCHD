#!/bin/bash
disk_size=`df -m / | awk '/\//{print $4}'`
mem_size=`free -m | awk '/Mem/{print $4}'`
if [ $disk_size -le 102400 ];then
  mail -s Warning root <<EOF
  Insufficient resources,磁盘资源不足
EOF
fi


if [[ "$mem_size" < 204800 ]] ;then
  mail -s Warning root <<EOF
  Insufficient resources,磁盘资源不足
EOF
fi
