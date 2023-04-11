CLASS_INPUT =  ./main/domain/classes/*.java \
               ./main/domain/classes/enumerations/*.java \
               ./main/domain/controller/*.java \
               #./main/domain/classes/algorismes/*.java

CLASS_ENUM =  ./main/domain/classes/enumerations/*.java

CLASS_RECORD = ./main/domain/classes/Record.java
CLASS_OUTPUT = ../EXE/out/

JUNIT_JARS = ../lib/junit-4.12.jar:../lib/hamcrest-core-1.3.jar

JUNIT_TESTS = ./test/*.java

JAR_OUTPUT =	../EXE/

all:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(JUNIT_TESTS)

driver:
	javac -d $(CLASS_OUTPUT) $(CLASS_ENUM) ./drivers/Driver.java
	jar cmvf drivers/Driver.mf $(JAR_OUTPUT)Driver.jar -C $(CLASS_OUTPUT) .
	java -jar $(JAR_OUTPUT)Driver.jar

executa_driver:
		java -jar $(JAR_OUTPUT)Driver.jar

allTest: TestRecord TestRanking TestRonda TestSequencia TestUser TestUser_persona TestUser_persona

TestRecord:
	javac -d $(CLASS_OUTPUT) $(CLASS_RECORD)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(JUNIT_TESTS)
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestRecord

TestRanking: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestRanking

TestRonda: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestRonda

TestSequencia: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestSequencia

TestUser: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestUser

TestUser_maquina: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestUser_maquina

TestUser_persona: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestUser_persona

clean:
	rm -rf ../EXE/out/* ../EXE/*

