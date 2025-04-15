# CAMEL-K TEST
We provided two test cases:
1. Java based camel-k route
2. yaml dsl based camel-k route

## Local Environment
Install camel with jbang
Using PowerShell run the following commands
````
iex "& { $(iwr https://ps.jbang.dev) } trust add https://github.com/apache/"
````
````
iex "& { $(iwr https://ps.jbang.dev) } app install --fresh --force camel@apache/camel"
````

## Testing Scenarios
Use terminal and go to the camel-k folder.  
Or you can directly Right click inside the folder and select 'Open Terminal'.
1. Java solution   
cd to the folder
````
cd java
````
run the route 
````
camel run HttpCallRoute.java
````

2. yaml dsl solution
cd to the folder
   cd to the folder
````
cd java
````
run the route
````
camel run http-call-transform.yaml JsonTransformer.java
````