package com.java8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

public class Base64Tester {
	
	@Test
	public void test() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName( "JavaScript" );
		         
		System.out.println( engine.getClass().getName() );
		try {
			System.out.println( "Result:" + engine.eval( "function f() { return 1; }; f() + 3;" ) );
		} catch (ScriptException e) {
			e.printStackTrace();
		}

		
	}
	
	
}

