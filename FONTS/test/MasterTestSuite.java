package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



@RunWith(value = Suite.class)

@SuiteClasses(value = { 
    TestPartida.class, TestRanking.class, TestRecord.class, TestRonda.class, TestSequencia.class, TestUser_maquina.class, TestUser_persona.class, TestUser.class
})


public class MasterTestSuite {}
