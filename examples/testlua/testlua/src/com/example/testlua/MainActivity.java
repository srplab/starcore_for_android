package com.example.testlua;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.res.AssetManager;

import java.io.ByteArrayOutputStream;   
import java.io.IOException;   
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

import com.srplab.www.starcore.*;

public class MainActivity extends Activity {
	public static MainActivity Host;
	public StarSrvGroupClass SrvGroup;
	
	private void copyFile(Activity c, String Name,String desPath) throws IOException {  
		File outfile = null;
		if( desPath != null )
			outfile = new File("/data/data/"+getPackageName()+"/files/"+desPath+Name);
		else
			outfile = new File("/data/data/"+getPackageName()+"/files/"+Name); 
	    //if (!outfile.exists()) {
	    	outfile.createNewFile();
        	FileOutputStream out = new FileOutputStream(outfile);        	
	        byte[] buffer = new byte[1024];  
	        InputStream in;  
	        int readLen = 0;  
	        if( desPath != null )
	        	in = c.getAssets().open(desPath+Name);
	        else
	        	in = c.getAssets().open(Name);
            while((readLen = in.read(buffer)) != -1){  
                out.write(buffer, 0, readLen);  
            }  
            out.flush();  
            in.close();  
	        out.close();  
	    //}  
	} 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Host = this;
        
        File destDir = new File("/data/data/"+getPackageName()+"/files");
        if(!destDir.exists())
        	destDir.mkdirs();
        try{    	
        	copyFile(this,"testlua.lua",null);
        	copyFile(this,"test_calljava.lua",null);
        }
        catch(Exception e){
        	System.out.println(e);        	
        }  
        /*----init starcore----*/
        StarCoreFactoryPath.StarCoreCoreLibraryPath = this.getApplicationInfo().nativeLibraryDir;
        StarCoreFactoryPath.StarCoreShareLibraryPath = this.getApplicationInfo().nativeLibraryDir;
        StarCoreFactoryPath.StarCoreOperationPath = "/data/data/"+getPackageName()+"/files";
        
		StarCoreFactory starcore= StarCoreFactory.GetFactory();
		StarServiceClass Service=starcore._InitSimple("test","123",0,0);
		SrvGroup = (StarSrvGroupClass)Service._Get("_ServiceGroup");
		Service._CheckPassword(false);
		
		/*----run lua code----*/		
		SrvGroup._InitRaw("lua",Service);
		StarObjectClass lua = Service._ImportRawContext("lua","",false,"");
		
		String CorePath = "/data/data/"+getPackageName()+"/files";
		//--load lua module ---*/
		SrvGroup._LoadRawModule("lua","",CorePath+"/testlua.lua",false);
		//--call lua function tt, the return contains two integer, which will be wrapped into StarObjectClass
		StarObjectClass retobj = (StarObjectClass)lua._Call("tt","hello ","world");
		System.out.println("ret from lua :  "+retobj._Get(1)+"   "+retobj._Get(2));
		//--get global int value g1--------*/
		System.out.println("lua value g1 :  "+lua._Get("g1"));
		//--get global table value c, which is a table with function, it will be mapped to cle object --------*/
		StarObjectClass c = lua._GetObject("c");
		//--get int value x from c--------*/
		System.out.println("c value x :  "+c._Get("x"));
		//--call c function yy, the return is a table, which will be mapped to cle object ---*/
		StarObjectClass yy = (StarObjectClass)c._Call("yy",c,"hello ","world","!");
		System.out.println("yy value [1] :  "+yy._Get(1));
		System.out.println("yy value [Type] :  "+yy._Get("Type"));		
		
		/*-------*/
		lua._Set("JavaClass", CallBackClass.class);
        Service._DoFile("lua", CorePath + "/test_calljava.lua", "");  //should not use null
      
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
