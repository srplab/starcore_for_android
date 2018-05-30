package com.example.testlua;

import com.srplab.www.starcore.StarObjectClass;
import com.srplab.www.starcore.StarParaPkgClass;

public class CallBackClass {
	StarObjectClass LuaClass;
	public CallBackClass(String Info)
    {
		System.out.println(Info);
    }
    public void callback(float val)
    {
    	System.out.println("" + val);
    }
    public void callback(String val)
    {
    	System.out.println("" + val);
    }
    public void SetLuaObject(Object[] rb)
    {
        for(Object Item : rb){
        	System.out.println("" + Item);
        }     
    }
}
