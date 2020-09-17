# java串口调试助手
![SerialAssistant.png](https://i.loli.net/2020/09/17/aM5OjGrxze6sRNU.png)

# 使用说明
## 直接使用jar
要把 rxtxSerial64.dll 和 rxtxParallel64.dll
分别放到 jdk1.8.0_211\jre\bin 和 jdk1.8.0_211\jre\lib\ext 文件夹里 
这两个文件我放到了项目的dependences文件夹下
## 使用.exe
直接安装，自带jre

# 已解决的问题
- 无法选择波特率
- 当发送速度过快时，JavaFX Aplication Thread出现空指针异常
- 中文乱码问题

# 后续待开发功能
- 串口数据以文件形式保存
- 数据生成折线图
......

