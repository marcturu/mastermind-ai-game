CLASS_INPUT =  ./main/domain/classes/*.java \
               ./main/domain/classes/enumerations/*.java \
               ./main/domain/classes/types/*.java \
               ./main/domain/controller/*.java
               #./main/domain/classes/algorismes/*.java


CLASS_RECORD = ./main/domain/classes/Record.java

CLASS_RANKING = ./main/domain/classes/Ranking.java \
				./main/domain/classes/types/Pair.java


CLASS_PARTIDA = ./main/domain/classes/Partida.java \
				./main/domain/classes/enumerations/*.java \
				./main/domain/classes/Ronda.java \
				./main/domain/classes/Sequencia.java \
				./main/domain/classes/User.java \
				./main/domain/classes/enumerations/type_seq.java \
				./main/domain/classes/types/Pair.java

CLASS_SEQUENCIA = ./main/domain/classes/Sequencia.java \
				  ./main/domain/classes/enumerations/colors.java \
				  ./main/domain/classes/enumerations/type_seq.java

CLASS_RONDA = ./main/domain/classes/Sequencia.java \
              	./main/domain/classes/enumerations/colors.java \
              	./main/domain/classes/enumerations/type_seq.java \
				./main/domain/classes/Ronda.java

CLASS_USER = ./main/domain/classes/Partida.java \
			./main/domain/classes/User.java \
			./main/domain/classes/enumerations/Type_user.java \
			./main/domain/classes/enumerations/dificultats.java \
			./main/domain/classes/enumerations/Type_user.java \
			./main/domain/classes/User_maquina.java \
			./main/domain/classes/User_persona.java \
			./main/domain/classes/enumerations/colors.java \
            ./main/domain/classes/Ronda.java \
            ./main/domain/classes/Sequencia.java \
            ./main/domain/classes/types/Pair.java \
            ./main/domain/classes/enumerations/type_seq.java \
			./main/domain/classes/algorismes/Five_guess_algorithm.java


CLASS_USER_MAQUINA = ./main/domain/classes/User_maquina.java \
					./main/domain/classes/User.java \
					./main/domain/classes/Partida.java \
            		./main/domain/classes/Ronda.java \
					./main/domain/classes/Sequencia.java \
					./main/domain/classes/types/Pair.java \
					./main/domain/classes/enumerations/dificultats.java \
					./main/domain/classes/enumerations/colors.java \
					./main/domain/classes/enumerations/type_seq.java \
					./main/domain/classes/enumerations/Type_user.java \
					./main/domain/classes/algorismes/Maquina.java \
					./main/domain/classes/algorismes/Five_guess_algorithm.java

CLASS_USER_PERSONA = ./main/domain/classes/User_persona.java \
					./main/domain/classes/User.java \
					./main/domain/classes/Partida.java \
            		./main/domain/classes/Ronda.java \
					./main/domain/classes/Sequencia.java \
					./main/domain/classes/types/Pair.java \
					./main/domain/classes/enumerations/dificultats.java \
					./main/domain/classes/enumerations/colors.java \
					./main/domain/classes/enumerations/type_seq.java \
					./main/domain/classes/enumerations/Type_user.java 


CLASS_OUTPUT = ../EXE/out/

JUNIT_JARS = ../lib/junit-4.12.jar:../lib/hamcrest-core-1.3.jar

JUNIT_TESTS_RECORD = ./test/TestRecord.java
JUNIT_TESTS_PARTIDA = ./test/TestPartida.java
JUNIT_TESTS_RANKING = ./test/TestRanking.java
JUNIT_TESTS_RONDA = ./test/TestRonda.java
JUNIT_TESTS_SEQUENCIA = ./test/TestSequencia.java
JUNIT_TESTS_USER = ./test/TestUser.java
JUNIT_TESTS_USER_MAQUINA = ./test/TestUser_maquina.java
JUNIT_TESTS_USER_PERSONA = ./test/TestUser_persona.java

JAR_OUTPUT =	../EXE/

all:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(JUNIT_TESTS)

driver:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./drivers/Driver.java
	jar cmvf drivers/Driver.mf $(JAR_OUTPUT)Driver.jar -C $(CLASS_OUTPUT) .
	java -jar $(JAR_OUTPUT)Driver.jar

executa_driver:
		java -jar $(JAR_OUTPUT)Driver.jar

allTest: TestRecord TestRanking TestRonda TestSequencia TestUser TestUser_maquina TestUser_persona

TestRecord:
	javac -d $(CLASS_OUTPUT) $(CLASS_RECORD)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_RECORD) $(JUNIT_TESTS_RECORD)
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestRecord

TestRanking:
	javac -d $(CLASS_OUTPUT) $(CLASS_RANKING)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_RANKING) $(JUNIT_TESTS_RANKING)
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestRanking

TestPartida:
	javac -d $(CLASS_OUTPUT) $(CLASS_PARTIDA)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_PARTIDA) $(JUNIT_TESTS_PARTIDA)
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestPartida

TestRonda:
	javac -d $(CLASS_OUTPUT) $(CLASS_RONDA)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_RONDA) $(JUNIT_TESTS_RONDA)
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestRonda

TestSequencia:
	javac -d $(CLASS_OUTPUT) $(CLASS_SEQUENCIA)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_SEQUENCIA) $(JUNIT_TESTS_SEQUENCIA)
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestSequencia

TestUser:
	javac -d $(CLASS_OUTPUT) $(CLASS_USER)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_USER) $(JUNIT_TESTS_USER)
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestUser

TestUser_maquina:
	javac -d $(CLASS_OUTPUT) $(CLASS_USER_MAQUINA)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_USER_MAQUINA) $(JUNIT_TESTS_USER_MAQUINA)
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestUser_maquina

TestUser_persona:
	javac -d $(CLASS_OUTPUT) $(CLASS_USER_PERSONA)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_USER_PERSONA) $(JUNIT_TESTS_USER_PERSONA)
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestUser_persona

clean:
	rm -rf ../EXE/out/* ../EXE/*
