package com.example.testpython_34;

import com.srplab.www.starcore.StarObjectClass;
import com.srplab.www.starcore.StarParaPkgClass;

public class CallBackClass {
	StarObjectClass PythonClass;
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
    public void SetPythonObject(Object rb)
    {
        PythonClass = (StarObjectClass)rb; 
        String aa = "";
        StarParaPkgClass data1 = MainActivity.Host.SrvGroup._NewParaPkg("b",789,"c",456,"a",123)._AsDict(true);
        Object d1 = PythonClass._Call("dumps", data1, MainActivity.Host.SrvGroup._NewParaPkg("sort_keys", true)._AsDict(true));
        System.out.println("" + d1);
        Object d2 = PythonClass._Call("dumps", data1,null); 
        System.out.println("" + d2);
        Object d3 = PythonClass._Call("dumps", data1, MainActivity.Host.SrvGroup._NewParaPkg("sort_keys", true, "indent",4)._AsDict(true));
        System.out.println("" + d3);
    }
}
