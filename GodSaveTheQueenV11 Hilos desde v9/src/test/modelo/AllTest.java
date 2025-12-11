package test.modelo;

import org.junit.platform.suite.api.Suite;

import test.hilos.RicoHilosTest;

import org.junit.platform.suite.api.SelectClasses;

@Suite
@SelectClasses({
    RateroTest.class,
    RicoHilosTest.class,
    PoblacionTest.class
})
class MiSuiteTest {}