# Tax-Financial-Organizer
A financial organizer created to organize tax-related tasks, such as consolidating data from financial documents like 1099 forms and organizing them into a database.

## Getting Started

### Dependencies

+ JDBC
+ SQL Server and SSMS
+ Apache POI
+ iText Core

### Installation

1. Install the financeproject folder
2. Open your IDE and open financeproject as a Java Project
3. Install the Dependencies below

#### **JDBC Installation**  
1. Download JDBC Driver: [Microsoft JDBC Driver](https://go.microsoft.com/fwlink/?linkid=2310306)  
2. Unzip the file.  
3. In Eclipse:  
   - Right-click your project → **Properties** → **Java Build Path**.  
   - Go to **Libraries** → **Classpath** → **Add External JARs**.  
4. Navigate to the unzipped folder:  
   ```
   sqljdbc_enu > sqljdbc > enu > jars > mssql-jdbc.jre11.jar
   ```  
5. Select **`mssql-jdbc.jre11.jar`**, then click **Apply & Close**.  

#### **Apache POI (Excel File Handling)**  
- [Apache POI Installation Guide](https://www.youtube.com/watch?app=desktop&v=tJZWGSa2Dhg)  
- [Apache POI Binaries](https://archive.apache.org/dist/poi/release/bin/)

#### **Note:**
- DO NOT add the log4j-api.jar from the apache poi binaries as shown in the installation guide. Instead install log4j-api.jar from [here](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api) and log4j-core.jar from [here](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core). Make sure that the version number of the installed jars are the same otherwise the program will show the error:
   ```
   ERROR StatusLogger Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console...
   ```

#### **SQL Server & SSMS Installation**  
- [SQL Server Installation Tutorial](https://www.youtube.com/watch?v=dPs7BQ4Zx_Q)  
- [Download SQL Server](https://www.microsoft.com/en-us/sql-server/sql-server-downloads)  
- [Download SSMS (SQL Server Management Studio)](https://aka.ms/ssmsfullsetup)

#### **iText Core**
1. Head over to [iText Java](https://github.com/itext/itext-java/releases)
2. Download iText-Core-only-jars.zip and add the jars to the java project
3. Install [slf4j-api.jar](https://mvnrepository.com/artifact/org.slf4j/slf4j-api) and add the jar to the java project
4. Install [slf4j-simple.jar](https://mvnrepository.com/artifact/org.slf4j/slf4j-simple) and add the jar to the java project
5. Install [log4j-slf4j2-imp.jar](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j2-impl)
