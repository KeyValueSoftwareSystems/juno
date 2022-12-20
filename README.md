# Juno
Performs JWT ```alg:none``` attacks quickly and easily.

## Building

### Requirements
+ JDK 17+

### Steps
1. git clone
2. cd juno
3. ./gradlew build
4. The target jar file can be found in located in ```./lib/build/libs```


## Installation in Burp
1. Follow these steps to install an extension from a JAR file:
2. Go to <b>Extensions</b> > <b>Installed</b> and click Add.
3. Under <b>Extension Details</b>, click <b>Select file</b> and locate the downloaded ```jar``` file.</li>
4. Click Next.</li>
5. Wait for the extension to install. Notice that the extension is now listed in the <b>Installed</b> tab.</li>

## Quick start
1. Pick a request with a JWT in it.

![image](https://user-images.githubusercontent.com/60728930/208425523-cf5fe796-c88a-413c-996c-95410f956f39.png)

2. Send it to <b>Juno</b>.
3. Click on <b>Auto §</b>

![image](https://user-images.githubusercontent.com/60728930/208426308-87e47ac4-76d0-4797-8427-b562e92a3468.png)

4. Click on <b>Start attack</b> to launch the attack & hope for the best!

![image](https://user-images.githubusercontent.com/60728930/208585856-f6268c86-36e8-43ce-9b21-2c62ac2aac24.png)

## Additional options
+ If needed, <b>Add §</b> and <b>Clear§</b> can be used to manually select the JWT token and clear the selection respectively.

![image](https://user-images.githubusercontent.com/60728930/208585519-ad5218f0-7888-41a3-ad1c-918d1df9af94.png)

+ If time is of the essence, increase the number of threads for a faster execution.

![image](https://user-images.githubusercontent.com/60728930/208585359-c0c5b39a-659d-4c87-b9ff-3a2665bcf129.png)


## How it works
1. Obtain a certain JWT
2. Split the JWT</li>
3. Decode the header section of the JWT (base64 decode)</li>
4. Replace the ```alg```  parameter value with ```none``` , ```None``` , ```NONE``` and so on.
5. Encode the header section back (base64 encode)
6. Rejoin the JWT
7. Send a request with the forged token

## References
Installing extensions : <a href="https://portswigger.net/burp/documentation/desktop/extensions/installing-extensions">Portswigger</a>
