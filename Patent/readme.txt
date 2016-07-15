The dataset for this problem is provided in patent file 
This program finds the associated subpatents associated with each patent id
For output, please refer to out.txt file


Instructions for running this program 
First create a jar file and the add the external jars given in lib folder
The input file patent should be in hdfs and can be copied to hdfs using following commad:
hdfs dfs -copyFromLocal '[Local location where you have saved file patent]'
Run the jar giving following commands

cd /[Location where your jar is stored]
hadoop jar [your created jar file] [input data in file stored in hdfs] [output file where output is to be stored]