package com.example.testruby;

import com.srplab.www.starcore.StarObjectClass;
import com.srplab.www.starcore.StarParaPkgClass;

public class CallBackClass {
	StarObjectClass RBClass;
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
    public void SetRubyObject(Object rb)
    {
        RBClass = (StarObjectClass)rb; // Ruby File
        StarObjectClass f = RBClass._New("", "", "/data/data/"+MainActivity.Host.getPackageName()+"/files" + "/test.txt", "w+");
        f._Call("puts", "I am Jack");
        f._Call("close");
    }
}
