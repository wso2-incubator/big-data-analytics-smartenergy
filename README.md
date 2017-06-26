# big-data-analytics-smartenergy

This repository contains CEP, DAS and GridLab-D(GLD) of two smart grid projects carried out.
1. Price Prediction <br/>
    GLD : GridLAB-D-master<br/>
    CEP : wso2cep-4.1.0
2. Non-cooperative Game Theory <br/>
    GLD : trunk-old<br/>
    DAS : wso2das-3.1.0


To run the experiments first resolve all dependencies of CEP , DAS and GLD. Then, do the following in order. 
1. Run CEP / DAS instance.
2. Once the sever starts, login with credentials, both user-name and password “admin”. <br/>
   Go to Tools → Event Stimulator and play the given .csv file/s.
   <br/>Note : Correct the path to the prediction model in the execution plan.
3. Build the GLD instance using the following commands in order.
    <br/> autoreconf -isf
    <br/> ./configure
    <br/> sudo make install
    <br/> Note : You might need to replace line 350 with the following.
    <br/> $(LDFLAGS) -lpthread -o $@
4. Start the GLD server with the following command. <br/>
   pathToBin/gridlabd.bin pathToGLM/grid.glm — server
