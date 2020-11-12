# UERANSIM
UERANSIM is the state-of-the-art 5G UE and RAN (gNodeB) simulator. The project can be used for testing 5G Core Network and studying 5G System.

## General Info  
**3GPP Release:** R15  
**Access Type:** 5G-SA (Standalone Access)  
**Connection Type:** 3GPP Access  

## Documentation  
**Wiki Page:** [UERANSIM Wiki](https://github.com/aligungr/UERANSIM/wiki)  

##  Requirements
1. Ubuntu 16.04 or later 
2. OpenJDK 11 or later
3. g++/gcc version 6.4.0 or later

## Dependencies
```
sudo apt update
sudo apt upgrade
sudo apt install make
sudo apt install g++
sudo apt install openjdk-11-jdk
sudo apt install maven
sudo apt install libsctp-dev lksctp-tools
```

## Building
1. Make sure that `JAVA_HOME` variable is set.
2. Run the following command:
    ```
    ./build.sh
    ```


## Running
1. Select a configuration profile by modifying `config/profile.yaml`.
2. You may need to further modify the profile configurations for IP addresses and port numbers etc. (see `config/open5gs`, `config/free5gc`, etc.)
3. Execute `./run.sh` to start the application.
4. Use `tail -f` to monitor logs realtime located at `logs/*.log`.
5. Use terminal to trigger test events such as `initial-registration`

## FAQ

**Q1. Why am I getting java.net.SocketException: Protocol not supported exception?**  

This error usually happens if you are using some Linux VM container in Windows. Windows does not support SCTP protokol, therefore you *may* require a physical Linux machine depending on your VM choice.

Also it is possible that your specific Linux distribution does not support SCTP. However Ubuntu 16.04 and later should support it. 

**Q2. Why am I getting java.net.ConnectException: Connection refused exception?**  

This error means SCTP connection could not established between RAN and AMF. Therefore make sure these 3 following conditions:

1. AMF is running and listening NGAP port (38412).
2. AMF's NGAP IP address and port number exactly matches with gnb.yaml config file.
3. AMF is reachable by RAN over the network. (Check firewall etc.)
  

**Q3. I can't build native libraries.**

Make sure that you set the `JAVA_HOME` environment variable, and have correct version of gcc/g++. You can check the current version with `gcc -v`. In order to upgrade gcc/g++ run the following command:
```
sudo apt update
sudo apt upgrade
sudo apt install g++
``` 

**Q4. How to increase the number of UE and RANs?**

In order to increase the number of UEs, you can change the `number-of-UE` value in `config/testing.yaml`. Multiple RAN feature is in progress now.

## Supporting UERANSIM

UERANSIM is the first and only open source project that implements 5G UE and RAN. Commercial alternatives of this software cost hundreds of thousands of dollars. You can support this free and open source software by:
- Starring our GitHub repository,
- Donating on [Patreon](https://www.patreon.com/ueransim),
- Creating pull requests, submitting bugs, suggesting new features or documentation updates.

## License

UERANSIM source code and all related files including documentation and wiki pages are licensed under [GPL-3.0](https://www.gnu.org/licenses/gpl-3.0.en.html), see [LICENSE](https://github.com/aligungr/UERANSIM/blob/master/LICENSE) for more details.

