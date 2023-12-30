#!/bin/bash 
#datetime=`date +"%Y/%m/%d %H:%M:%S"`
while true
do
echo "<html>" >>monitor.html
echo "<head>" >>monitor.html
echo "<meta charset="utf-8">" >>monitor.html
echo "<meta http-equiv="refresh" content="300">" >>monitor.html
echo "<title>获取服务器运行状态信息</title>" >>monitor.html
echo "</head>" >>monitor.html
echo "<body>" >>monitor.html
echo "<p>时间戳：`date +"%Y/%m/%d %H:%M:%S"` </p>" >>monitor.html
echo "<p>主机名称：`hostname` </p>" >>monitor.html
echo "<p>内核信息：`uname -a` </p>" >>monitor.html
echo "<p>操作系统版本：`cat /etc/issue` </p>" >>monitor.html
echo "<p>IP地址：`hostname -I`</p>" >>monitor.html
echo "<p>服务器运行时间：`uptime -p` </p>" >>monitor.html
echo "<p>磁盘使用情况：当前目录大小：`du -sh` </p>" >>monitor.html
echo "<p>内存使用情况：`free -h` </p>" >>monitor.html
echo "<p>CPU负载情况：`uptime | cut -d ' ' -f 9-14` </p>" >>monitor.html
echo "<p>登陆用户：`who | cut -d' ' -f1 | sort | uniq` </p>" >>monitor.html
echo "<p>进程数量：`ps -ef | wc -l` </p>" >>monitor.html
echo "</body>" >>monitor.html
echo "</html>" >>monitor.html
sleep 300
done
