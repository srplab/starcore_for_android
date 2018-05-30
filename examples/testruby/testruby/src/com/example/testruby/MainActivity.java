package com.example.testruby;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.content.res.AssetManager;

import java.io.ByteArrayOutputStream;   
import java.io.FileNotFoundException;
import java.io.IOException;   
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.zip.ZipInputStream;

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
        	copyFile(this,"testrb.rb",null);
        	copyFile(this,"cmath.rb",null);
        	copyFile(this,"test_calljava.rb",null);
        }
        catch(Exception e){
        	System.out.println(e);        	
        }          
        
		try{
			System.load(this.getApplicationInfo().nativeLibraryDir+"/libruby.so");
		}
		catch(UnsatisfiedLinkError ex)
		{
			System.out.println(ex.toString());
		}    

        /*----init starcore----*/
        StarCoreFactoryPath.StarCoreCoreLibraryPath = this.getApplicationInfo().nativeLibraryDir;
        StarCoreFactoryPath.StarCoreShareLibraryPath = this.getApplicationInfo().nativeLibraryDir;
        StarCoreFactoryPath.StarCoreOperationPath = "/data/data/"+getPackageName()+"/files";
        
		StarCoreFactory starcore= StarCoreFactory.GetFactory();
		StarServiceClass Service=starcore._InitSimple("test","123",0,0);
		SrvGroup = (StarSrvGroupClass)Service._Get("_ServiceGroup");
		Service._CheckPassword(false);
		
		SrvGroup._InitRaw("ruby",Service);
		StarObjectClass ruby = Service._ImportRawContext("ruby","",false,"");
		System.out.println(ruby._Get("LOAD_PATH"));
		System.out.println(ruby._Get("File"));
/*		
		StarParaPkgClass para = (StarParaPkgClass)ruby._Get("LOAD_PATH");
		
		for (Object obj : para )
            System.out.println(obj);
*/            
		StarObjectClass LOAD_PATH = (StarObjectClass)ruby._R("LOAD_PATH");
		System.out.println(LOAD_PATH);
		LOAD_PATH._Call("unshift", "/data/data/"+getPackageName()+"/files");
		
		StarObjectClass para = (StarObjectClass)ruby._Get("LOAD_PATH");
		
		for (Object obj : para )
            System.out.println(obj);
		
		ruby._Call("require","cmath");
		System.out.println(ruby._Get("CMath"));
		
		//--load ruby module ---*/
		SrvGroup._LoadRawModule("ruby","","/data/data/"+getPackageName()+"/files/testrb.rb",false);
		//--attach object to global ruby space ---*/
		StarObjectClass object = Service._ImportRawContext("ruby","",false,"");  
		//--call ruby function tt, the return contains two integer, which will be packed into parapkg ---*/
		StarObjectClass RetObj = (StarObjectClass)object._Call("tt","hello ","world");
		System.out.println("ret from ruby : "+RetObj._Get(0)+"  "+RetObj._Get(1));
		//--get global int value g1--------*/
		System.out.println("ruby value g1 :  "+object._Get("g1"));
		//--get global class Multiply
        StarObjectClass Multiply = Service._ImportRawContext("ruby","Multiply",true,"");
        StarObjectClass multiply = Multiply._New("","",33,44);
        //--call instance method multiply
        System.out.println("instance multiply = "+multiply._Call("multiply",11,22));	
        
        String CorePath = "/data/data/"+getPackageName()+"/files";
        ruby._Set("$JavaClass", CallBackClass.class);
        Service._DoFile("ruby", CorePath + "/test_calljava.rb", "");  //should not use null
		
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
