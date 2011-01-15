package com.kanasansoft.Keynote08Remote;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Keynote08Wrapper {

	ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
	ScriptEngine engine = scriptEngineManager.getEngineByName("AppleScript");

	Keynote08Wrapper() throws ScriptException {
		if(engine == null){
			throw new ScriptException("Not found script engine for Applescript.");
		}
		if(!checkExistKeynote()){
			throw new ScriptException("Not found Keynote.");
		}
	}

	boolean checkExistKeynote() throws ScriptException{
		String[] strs = new String[]{
				"try",
				"application id \"com.apple.iWork.Keynote\"",
				"true",
				"on error",
				"false",
				"end try"
		};
		//true  : 1 in the AppleScript Engine for Java 1.0
		//false : 0 in the AppleScript Engine for Java 1.0
		return ((Long)engine.eval(joinString(strs,"\r\n")))==1;
	}

	boolean isPlaying() throws ScriptException{
		String[] strs = new String[]{
				"tell application \"Keynote\"",
				"playing",
				"end tell"
		};
		//true  : 1 in the AppleScript Engine for Java 1.0
		//false : 0 in the AppleScript Engine for Java 1.0
		return ((Long)engine.eval(joinString(strs,"\r\n")))==1;
	}

	boolean isFrozen() throws ScriptException{
		String[] strs = new String[]{
				"tell application \"Keynote\"",
				"frozen",
				"end tell"
		};
		//true  : 1 in the AppleScript Engine for Java 1.0
		//false : 0 in the AppleScript Engine for Java 1.0
		return ((Long)engine.eval(joinString(strs,"\r\n")))==1;
	}

	boolean isVisibleSlideWwitcher() throws ScriptException{
		String[] strs = new String[]{
				"tell application \"Keynote\"",
				"slide switcher visible",
				"end tell"
		};
		//true  : 1 in the AppleScript Engine for Java 1.0
		//false : 0 in the AppleScript Engine for Java 1.0
		return ((Long)engine.eval(joinString(strs,"\r\n")))==1;
	}

	String joinString(List<String> stringList, String with){
		return joinString(stringList.toArray(new String[]{}),with);
	}

	String joinString(String[] stringArray, String with){
		boolean isFirst = true;
		StringBuffer sb = new StringBuffer();
		for(String string : stringArray){
			if(!isFirst){
				sb.append(with);
			}
			sb.append(string);
			isFirst = false;
		}
		return sb.toString();
	}

}
