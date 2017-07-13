# big-data-analytics-smartenergy

This repository contains CEP, DAS, GridLab-D(GLD) and glm of two smart grid projects carried out.
1. Price Prediction <br/>
    GLD : GridLAB-D-master<br/>
    CEP : wso2cep-4.1.0<br/>
    glm : GridLAB-D-master/defaultsimulation
2. Non-cooperative Game Theory <br/>
    GLD : game-trunk<br/>
    DAS : wso2das-3.1.0<br/>
    glm : game


To run the experiments first resolve all dependencies of CEP , DAS and GLD. Then, do the following in order. 
1. Run CEP / DAS instance.
2. Once the sever starts, login with credentials, both user-name and password “admin”. <br/>
   Change the path of pediction models in the execution plan. (prediction models can be found in /models)<br/>
   Go to Tools → Event Stimulator and play the given .csv file/s.
   
3. Change the file path of following lines in grid.glm of specified glm folder.<br/>
    #include "/<path-to-glm-folder>/light_schedule.glm";<br/>
    #include "/<-path-to-glm-folder>/water_and_setpoint_schedule.glm";
4. Build the GLD instance using the following commands in order in the GLD folder.
    <br/> autoreconf -isf
    <br/> ./configure
    <br/> sudo make install
    <br/> Note : You might need to replace line 350 with the following.
    <br/> $(LDFLAGS) -lpthread -o $@
5. Start the GLD server with the following command. <br/>
   pathToBin/gridlabd.bin pathToGLM/grid.glm -- server


Files edited in this experiment are ,

1. market/controller.cpp
<br/> This receives the data (i.e. nash load and total load) sent from DAS/CEP and does the required changes in the setpoints.
2. tape/multi_recorder.c
<br/> Edited inorder to send http requests to DAS/CEP. The initial implentation was sending a curl request, which was later modified to send requests through a socket.
