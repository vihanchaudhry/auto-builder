Vihan Chaudhry
CIS 35B
Winter 2016
Lab 5
03/16/16

IDE: IntelliJ IDEA
- Project is provided

DESIGN: 
- client and server use object streams (i/o) for passing Automobiles and Properties to each other
- client is run in DefaultSocketClient, server extends this class
- one project, two main methods (Driver and DefaultSocketClient)
- SERVER runs in DRIVER, CLIENT runs in DEFAULTSOCKETCLIENT
- connection is established between client and server
- client is asked to choose between uploading Properties to a new Automobile and configuring an existing model
- option A: .properties file is sent to server, and the server adds valid files to the LinkedHashMap as a new Automobile
- option B: server sends back names of existing models, then client sends back one of the model names, then server sends back an instance of that Automobile to client for configuration
- included props file is called toyotaprius.properties