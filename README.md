# Common Language Extension (CLE) 

Portability is an issue we must consider when developing applications. The most portable language is ansi c. However, due to the lack of functional libraries, applications are not easy to write and need to take a long time to market. An alternative is to use a scripting language, such as python, ruby, or lua. With the help of CLE middleware, these scripting languages can run on most platforms such as windows, windows 10, ios, android, linux.

CLE is a middleware to support mixed programming of multiple languages. Any language of c/c++, lua, python, c#, java can access most classes, functions, variables, or modules of other languages directly, which makes existing code or libraries to be reused easily. Developers can write code using the favorite language and then use them in other applications written with different languages. CLE is simple, which is a single core share library with libraries corresponding to each script language.

We recommend that developers write primary logic in a scripting language, and GUI or device-specific parts in the platform-dependent language. Not only ensure application portability, but also take advantage of the platform SDK. Using scripting language may result in a little large about the size of installation packages and a slightly decrease in performance, but these should not be a problem as hardware performance improves and storage increases
 

### It's major features are listed below:

- Supporting multiple platforms. CLE supports windows xp, windows 7, windows 8, linux x86, android, ios, windows 10
- Supporting multiple script languages. CLE supports c/c++, lua, python, java, c#,ruby
- Integrated two-way bridge between scripts. Any language of the c/c++, lua, python, java, c# can access most classes, functions, methods, or modules of another language directly.
- Providing unified interface to multiple script language.


## starcore_for_android v2.5.2

- c/c++, lua, python2.7.14, 3.4.5, 3.5.4, 3.6.3, java, ruby225, 235,242
- lua 5.3.4
- support delphi
- 32/64bit version
- Free trial


## History of CLE

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