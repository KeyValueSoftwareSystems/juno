<img src="https://img.shields.io/badge/Licence-Apache 2.0-blue"></img>

# What
Juno is a burp extension to attack JWT tokens quickly and easily.

# Table of contents:
- <a href=https://github.com/KeyValueSoftwareSystems/juno#Why>Why</a>
- <a href=https://github.com/KeyValueSoftwareSystems/juno#how>How</a>
- <a href=https://github.com/KeyValueSoftwareSystems/juno#building>Building</a>
  - <a href=https://github.com/KeyValueSoftwareSystems/juno#requirements>Requirements</a>
  - <a href=https://github.com/KeyValueSoftwareSystems/juno#steps>Steps</a>
  - <a href=https://github.com/KeyValueSoftwareSystems/juno#burp-installation>Burp installation</a>
- <a href=https://github.com/KeyValueSoftwareSystems/juno#quick-start>Quick start</a>
- <a href=https://github.com/KeyValueSoftwareSystems/juno#additional-options>Additional options</a>
- <a href=https://github.com/KeyValueSoftwareSystems/juno#licence>Licence</a>
- <a href=https://github.com/KeyValueSoftwareSystems/juno#references>References</a>

# Why
JWTs have a signature which can be verified server-side, rendering forging a JWT impractical.
However, a JWT has the signature verification algorithm specified in its own header section.
A vulnerable server tends to accept `none` as a valid option and hence bypass signature verification.

# How
To check whether APIs on <i>vulnerable.com</i> are vulnerable to the above attack, one would have to:

0. Log in to vulnerable.com
1. Extract the JWT
2. Split the JWT
3. Decode the header section of the JWT (_base64 decode_)
4. Replace the `alg` parameter value with `none`
5. Encode the header section back (_base64 encode_)
6. Rejoin the JWT
7. Send a request with the forged token
8. See if it was successful
9. Repeat steps 5 through 7 with `None`, `NONE`, `nONE` and so on.

**Juno** performs everything above (_except step 0_) for you.

# Building

## Requirements
- JDK 17+

## Steps
```bash
git clone https://github.com/KeyValueSoftwareSystems/juno.git
cd juno
./gradlew build
```
The target jar file can be found in `./lib/build/libs`

# Installation
Follow these steps to install Juno from a JAR file onto Burp suite:

<pre>
1. Go to <b>Extensions</b> > <b>Installed</b> and click Add.
2. Under <b>Extension Details</b>, click <b>Select file</b> and locate the ```jar``` file.
3. Click Next.</li>
4. Wait for the extension to install. Notice that the extension is now listed in the <b>Installed</b> tab.
</pre>

# Quick start
1. Pick a request with a JWT in it.

![image](https://user-images.githubusercontent.com/60728930/208624128-35f03906-f88d-40fd-991b-1aa9b0f8839c.png)

2. Send it to <b>Juno</b>.
3. Click on <b>Auto ??</b>

![image](https://user-images.githubusercontent.com/60728930/208624811-c2c5780a-e483-48f8-9f74-9255feaff153.png)

4. Click on <b>Start attack</b> to launch the attack & hope for the best!

![image](https://user-images.githubusercontent.com/60728930/208585856-f6268c86-36e8-43ce-9b21-2c62ac2aac24.png)

## Additional options
- If needed, <b>Add ??</b> and <b>Clear??</b> can be used to manually select the JWT token and clear the selection respectively.

![image](https://user-images.githubusercontent.com/60728930/208625665-1255aaa0-8c3e-4dd2-9c26-b3d91e773908.png)

- If time is of the essence, increase the number of threads for faster execution.

![image](https://user-images.githubusercontent.com/60728930/208625489-f70a0952-6bff-4aff-9282-fa443ad47294.png)

# Licence
This project is licensed under the terms of the **Apache Licence 2.0**, as mentioned in the `COPYING` file in the root directory.

# References
- JSON Web Tokens (JWTs): <a href="https://jwt.io/introduction/">jwt.io</a>
- Installing extensions: <a href="https://portswigger.net/burp/documentation/desktop/extensions/installing-extensions">Portswigger</a>
