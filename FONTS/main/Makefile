CLASS_INPUT =  ./domain/classes/*.java \
               ./domain/classes/enumerations/*.java \
               ./domain/controller/*.java \
               ./domain/classes/algorismes/*.java

CLASS_OUTPUT = ../EXE/out/

JUNIT_JARS = ../lib/junit-4.12.jar:../../lib/hamcrest-core-1.3.jar

JUNIT_TESTS = ./test/*.java

JAR_OUTPUT =	../EXE/

all:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(JUNIT_TESTS)

TestRecord: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestRecord

TestPartida: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.UnitTestPartida