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

	long countOfSlideshows() throws ScriptException{
		String[] strs = new String[]{
				"tell application \"Keynote\"",
				"count of slideshow",
				"end tell"
		};
		return (Long)engine.eval(joinString(strs,"\r\n"));
	}

	List<String> getSlideshowNames() throws ScriptException{
		String[] strs = new String[]{
				"tell application \"Keynote\"",
				"set slideshowList to {}",
				"repeat with n from 1 to count of slideshow",
				"set end of slideshowList to name of slideshow n",
				"end repeat",
				"slideshowList",
				"end tell"
		};
		return (List<String>)engine.eval(joinString(strs,"\r\n"));
	}

	boolean startSlideshow() throws ScriptException{
		String[] strs = new String[]{
				"try",
				"tell application \"Keynote\"",
				"start",
				"end tell",
				"true",
				"on error",
				"false",
				"end try"
		};
		//true  : 1 in the AppleScript Engine for Java 1.0
		//false : 0 in the AppleScript Engine for Java 1.0
		return ((Long)engine.eval(joinString(strs,"\r\n")))==1;
	}

	boolean stopSlideshow() throws ScriptException{
		String[] strs = new String[]{
				"try",
				"tell application \"Keynote\"",
				"stop slideshow",
				"end tell",
				"true",
				"on error",
				"false",
				"end try"
		};
		//true  : 1 in the AppleScript Engine for Java 1.0
		//false : 0 in the AppleScript Engine for Java 1.0
		return ((Long)engine.eval(joinString(strs,"\r\n")))==1;
	}

	boolean resumeSlideshow() throws ScriptException{
		String[] strs = new String[]{
				"try",
				"tell application \"Keynote\"",
				"resume slideshow",
				"end tell",
				"true",
				"on error",
				"false",
				"end try"
		};
		//true  : 1 in the AppleScript Engine for Java 1.0
		//false : 0 in the AppleScript Engine for Java 1.0
		return ((Long)engine.eval(joinString(strs,"\r\n")))==1;
	}

	boolean pauseSlideshow() throws ScriptException{
		String[] strs = new String[]{
				"try",
				"tell application \"Keynote\"",
				"pause slideshow",
				"end tell",
				"true",
				"on error",
				"false",
				"end try"
		};
		//true  : 1 in the AppleScript Engine for Java 1.0
		//false : 0 in the AppleScript Engine for Java 1.0
		return ((Long)engine.eval(joinString(strs,"\r\n")))==1;
	}

	boolean showSlideSwitcher() throws ScriptException{
		String[] strs = new String[]{
				"try",
				"tell application \"Keynote\"",
				"show slide switcher",
				"end tell",
				"true",
				"on error",
				"false",
				"end try"
		};
		//true  : 1 in the AppleScript Engine for Java 1.0
		//false : 0 in the AppleScript Engine for Java 1.0
		return ((Long)engine.eval(joinString(strs,"\r\n")))==1;
	}

	boolean acceptSlideSwitcher() throws ScriptException{
		String[] strs = new String[]{
				"try",
				"tell application \"Keynote\"",
				"accept slide switcher",
				"end tell",
				"true",
				"on error",
				"false",
				"end try"
		};
		//true  : 1 in the AppleScript Engine for Java 1.0
		//false : 0 in the AppleScript Engine for Java 1.0
		return ((Long)engine.eval(joinString(strs,"\r\n")))==1;
	}

	boolean cancelSlideSwitcher() throws ScriptException{
		String[] strs = new String[]{
				"try",
				"tell application \"Keynote\"",
				"cancel slide switcher",
				"end tell",
				"true",
				"on error",
				"false",
				"end try"
		};
		//true  : 1 in the AppleScript Engine for Java 1.0
		//false : 0 in the AppleScript Engine for Java 1.0
		return ((Long)engine.eval(joinString(strs,"\r\n")))==1;
	}

	boolean moveSlideSwitcherForward() throws ScriptException{
		String[] strs = new String[]{
				"try",
				"tell application \"Keynote\"",
				"move slide switcher forward",
				"end tell",
				"true",
				"on error",
				"false",
				"end try"
		};
		//true  : 1 in the AppleScript Engine for Java 1.0
		//false : 0 in the AppleScript Engine for Java 1.0
		return ((Long)engine.eval(joinString(strs,"\r\n")))==1;
	}

	boolean moveSlideSwitcherBackward() throws ScriptException{
		String[] strs = new String[]{
				"try",
				"tell application \"Keynote\"",
				"move slide switcher backward",
				"end tell",
				"true",
				"on error",
				"false",
				"end try"
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
