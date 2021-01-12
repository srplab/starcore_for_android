# Common Language Extension (CLE) 

Portability is an issue we must consider when developing applications. The most portable language is ansi c. However, due to the lack of functional libraries, applications are not easy to write and need to take a long time to market. An alternative is to use a scripting language, such as python, ruby, or lua. With the help of CLE middleware, these scripting languages can run on most platforms such as windows, windows 10, ios, android, linux.

CLE is a middleware to support mixed programming of multiple languages. Any language of c/c++, lua, python, c#, java can access most classes, functions, variables, or modules of other languages directly, which makes existing code or libraries to be reused easily. Developers can write code using the favorite language and then use them in other applications written with different languages. CLE is simple, which is a single core share library with libraries corresponding to each script language.

We recommend that developers write primary logic in a scripting language, and GUI or device-specific parts in the platform-dependent language. Not only ensure application portability, but also take advantage of the platform SDK. Using scripting language may result in a little large about the size of installation packages and a slightly decrease in performance, but these should not be a problem as hardware performance improves and storage increases
 

### It's major features are listed below:

- Supporting multiple platforms. CLE supports windows xp, windows 7, windows 8, linux x86, android, ios, windows 10
- Supporting multiple script languages. CLE supports c/c++, lua, python, java, c#,ruby
- Integrated two-way bridge between scripts. Any language of the c/c++, lua, python, java, c# can access most classes, functions, methods, or modules of another language directly.
- Providing unified interface to multiple script language.


## starcore_for_android v3.7.5

- c/c++, lua, python2.7/3.4/3.5/3.6/3.7/3.8/3.9,java, ruby22/23/24/25
- lua 5.3.5
- support delphi
- 32/64bit version
- Free


## History of CLE
### 3.7.5
- Adds functions GetCoreOperationPath/SetCoreLibraryPath/GetCoreLibraryPath/SetShareLibraryPath/GetShareLibraryPath for SRPControlInterface
- Fix bug of ImportRawContext for java interface on android
- Adds functions QueryFirstStringKeyEx/QueryNextStringKeyEx/QueryFirstStringKeyAEx/QueryNextStringKeyAEx for BasicSRPInterface and SRPInterface, which returns the index key
- released at 2021/01/12 

### 3.7.4
- support python 3.9
- support android api version higher than 29
- released at 2020/11/15 


### 3.7.3
- Fix the installation problem for python3 on macos,version above 10.15
- Fix the bug of VSEVENT_SYSTEMEVENT_ONDESTROYCHILD event cannot generate before the object is released. Note: to generate this event, you need to set the mask SetChildEventMask(CNodeSetBase, GetLayer(Object), VSSYSEVENT_PROCESS_DESTROY)
- Add the function ChangeParentEx to insert the object in front of the queue for SRPInterface.
- Adds functions RegFreeCallBack/UnRegFreeCallBack for SRPInerface, which is called before the object is released. When it is called, the property of the object does not change. After the function returns, the object will be released
- Optimizes InsertObject and GetObject functions for ParaPkg interface
- LuaIsFunctionDefined and DefLuaFunction is changed, which will not call the callback function registered by RegLuaGetValueFunc
- released at 2020/07/15 

### v3.7.0
- Solve the bug that LuaIsFunctionDeined is not affected by InvalidRegLuaFunction
- Solve the bug that python multithreading simultaneously calling SRP Dispatch may deadlock
- Add the function DispatchCall/MainDispatchCall to assist the call of object function between multiple threads
- released at 2019/10/19

### v3.6.0
- Solve the bug of parapkg function GetValueStr
- Add the function GetObjectEx3 to get all the objects with the same name. The service object of the script adds the function _GetObjectEx3
- Add GetStarSpace to get the namespace object, the script's Service object adds the function _GetStarSpace
- released at 2019/10/05

### v3.5.0
- Add the object index count change callback function for SRPControlInterface, RegRefCountChangeCallBack and UnRegRefCountChangeCallBack
- fix memory bug for object with long name
- fix memory bug for Del function of ParaPkg
- add vs_tm_gettickcount_us for vs_shell and _TickCountUs for script interface
- fix bug about define python callback using decorator
- released at 2019/07/27

### v3.4.0
- fixed bug when initialize cle on macos, sometimes waiting too long.
- support java 9 or above
- released at 2019/06/02

### v3.3.0
- fix bug of VS_QUERYRECORD memory corrupt for 64bit version
- upgrade the "starmodule" tool 
- released at 2019/05/25

### v3.2.0
- fix bug of VS_QUERYRECORD memory corrupt for 64bit version
- fix bug of load native service share library for macos
- released at 2019/03/30

### v3.1.0
- 1. add function for ClassOfSRPInterface: "GetAttributeIndex", "XmlToObjectEx", "InstNumber", "InstNumberEx", "AllObject"
- 2. add function for ClassOfSRPControlInterface : "ReleaseScriptGIL", "CaptureScriptGIL"
- 3. add function for vs_shell: "vs_tm_gettickcount64"
- 4. add function for script interface: "_InstNumber"
- 5. add function for ClassOfSRPParaPackageInterface : "GetDesc", "GetValueStr", "Equals"; script function "_V", "_Equals"
- 6. add object's event "VSEVENT_SYSTEMEVENT_ONBEFOREDESTORY"
- 7. add script function "AttachRawObjectEx(RawObject,IsCLECall)", if RawObject is true, the first arg of raw function must be (Object cleobj /(StarObjectClass cleobj, ...)
- 8. add core object "StarObjectSpace", which is used to manage object's space
- 9. for lua,python,ruby, support using string key to set or get value from parapkg
- 10. support python decorator, _RegMsgCallBack_P,_RegDispatchRequest_P,_RegServiceClearCallBack_P,_MsgLoop_P,_RegDispatchCallBack_P,_RegSysEventProc_P,_RegScriptProc_P. for example,  @realm._RegScriptProc_P('OnBeforeExecute')   def realm_OnBeforeExecute(CleObj):  pass   
- 11. support directly get or set two/three dimensional set of c# from other script lang
- 12. function "ChangeParent" can accept NULL as parent object.
- 13. set default value of CheckPassword to false
- 14. fix bug for byte alignment of atomic attribute
- 15. fix bug for lua function "SrvGroup._NewParaPkg(xx)"
- 16. fix bug for ClassOfSRPParaPackageInterface's function "InsertEmpty"
- 17. fix bug for can not get first object from parent's object queue for script lang.
- 18. fix bug for json to support 64bit integer
- released at 2019/03/20

### v3.0.0
- 1. Support for overriding of object functions, including encapsulated script raw objects.If object is an instance of a raw class, or if it is a raw object itself, function overriding is not supported. If overriding is needed, a new cle instance of it must be created and overriding function on the new instance.
- 2. Optimize script object multi-thread scheduling, added SRPDispatch related callback function for script interface module.
- 3. Add _RegServiceClearCallBack_P function for script interface, which is called before the service is cleared. After the function returns, the cle platform will delete the CLE object.
- 4. For java scripts, support for accessing static properties and static functions of classes through instances
- 5. Add platform callback MSG_ONINTERFACE_ACTIVATE
- 6. Add a static library: starcore.lib(windows)/libstarcore.a, which can be linked to the lua core library and support lua editing debuggers such as ZeroBrane Studio. Also includes the compiled shared library starcore53.dll/luastarcore53.so/luastarcore53.dylib without the lua core.
- 7. Add RestfulCall and JSonCall, input the string in JSon format, and output the string in JSon format.
- 8. Dynamic library export function adds VSCore_GetCFunctionTable, in order to facilitate access to the functions provided by the CLE platform through the C interface.
- 9. Lua kernel upgrade to 5.3.5
- 10. Support python 3.7
- 11. Bug fix.
- 12. Licence Change, Divided into community version and professional version, the community version is completely free, there is no longer the maximum number of objects to create, the professional version provides some advanced features, such as function overriding, object creation event capture, etc., see the programming guide for details. 
- released at 2018/11/01 

### v2.6.0
- 1. Print warning for ruby if object's function with special name does not exist, but it has function "method_missing"
- 2. Fix exception for remote call if object has no parent class
- 3. The return value such as tuple/list will be try to converted to parapkg for raw object, when is called from remote size
- 4. Support changing port number for client dynamically
- 5. Fix memory layer byte alignment bug in communication layer
- 6. Add definition "SRPLOADPROCESS_EXIT", when call RunFromUrl / RunFromBuf load the app, if there is an error during the loading process, starcore will automatically unloas and cleanup, then the functions return SRPLOADPROCESS_EXIT
- 7. Solve Function "SRPI_ScriptSyncCallInt64Var2" definition wrong in vsopenapi_c.h
- 8. Add support for mingw, separate library libstarlib.a
- 9. Add interface function "StarCoreScript_Init2", which takes c function table as parameter.
- 10.Fix python exits exception bug.
- 11.Supports shared libraries not unloaded, including services, or script interfaces, because some scripts do not support unload.
- 12.Solve symbol table undefined error for linux. 
- released at 2018/05/30 

### v2.5.2
- Add RunInMainThread function for ClassOfSRPControlInterface
- Add RegDispatchRequest/UnRegDispatchRequest function for ClassOfSRPControlInterface, and _RegDispatchRequest_P for script.
- Print trace back info for python after exception
- Support insert script objects to parapkg
- Fix bug of python interface, occurred when compile string or call function "eval".
- Add telnet string preprocessing before execution.
- Force ruby code runs in main thread.
- Script function "eval" and "execute" supports "%@variablename" embedded in input string.When executed, global variable named "variablename" is generated, which value is from input parameter. For example, eval("print(%@a)","hello") => 1. set a to "hello". 2. call print(a)
- lua is updated to 5.3.4
- Add function "GetNameEx" for SRPInterface returns more info about script object.
- Add function "TSRPVariant","TSRPParaPkg","TSRPBinBuf","TSRPSXml","TSRPComm" for delphi to help to interoperate with script object.
- For c++ builder, app can use Variant to encapsulate cle object to help call script object's function or access script object's properties.

### v2.5.1
- fix bug "_InjectClass" exception for java
- add vs2015 solution support for starmodule
- add _GetObjectNum function for BasicSRPInterface(C/C++) or SrvGroupClass(Script)
- add _ActiveScriptInterface,_PreCompile function for SrvGroupClass(Script)
- lua is updated to 5.3.3
- C/C++ function "ScriptCall" returns ParaPkg for multiple return values.
- add _ToBuf/_FromBuf function for BinBufClass(script)
- add FromRaw/IsFromRaw for BinBufInterface(c/c++) to help convert binary data for scripts.
- add InsertStrEx/GetStrEx for ParaPkgInterface(c/c++), support \0 in string
- add InsertBinEx/GetBinEx for ParaPkgInterface(c/c++) to help convert binary data for scripts.
- If arguments type of native function is double or float, it should be changed to VS_DOUBLE_F or VS_FLOAT_F. TO_VS_FLOAT_F/FROM_VS_FLOAT_F; TO_VS_DOUBLE_F/FROM_VS_DOUBLE_F
- Licence Change, Max number of object can be created by free version is limited to 48. function "GetObjectNum" is added to get current object number. For details, please refer to programmer's guide.