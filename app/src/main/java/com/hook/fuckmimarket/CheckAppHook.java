package com.hook.fuckmimarket;

import dalvik.system.PathClassLoader;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class CheckAppHook implements IXposedHookZygoteInit {

    private final String SecurityManagerServiceName = "com.miui.server.SecurityManagerService$1";

    private PathClassLoader servicesClassLoader = null;

    private Class<?> SecurityManagerServiceClazz = null;

    private java.util.Set<XC_MethodHook.Unhook> pathClassLoaderHook = null;

    @Override
    public void initZygote(StartupParam startupParam) {
        XposedBridge.log("hook all PathClassLoader Constructors");
        pathClassLoaderHook =
                XposedBridge.hookAllConstructors(PathClassLoader.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) {
                        String path = param.args[0].toString();
                        if (path.contains("/system/framework/services.jar")) {
                            XposedBridge.log("find services.jar ClassLoader");
                            try {
                                servicesClassLoader = (PathClassLoader) param.thisObject;
                                SecurityManagerServiceClazz = XposedHelpers.findClass(
                                        SecurityManagerServiceName,
                                        servicesClassLoader);
                                XposedBridge.log("findClass SecurityManagerService$1");
                                XposedHelpers.findAndHookMethod(SecurityManagerServiceClazz,
                                        "run",new  XC_MethodReplacement(){
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam unused){
                                                XposedBridge.log("hooked checkSystemSelfProtection invoke");
                                                return null;
                                            }
                                        });
                                XposedBridge.log("hook method 'SecurityManagerService$1.run()'");
                            } catch (Exception e) {
                                XposedBridge.log("Exception!!!!!!!!!!");
                                e.printStackTrace();
                            }finally {
                                for (Unhook hook : pathClassLoaderHook) {
                                    hook.unhook();
                                }
                                XposedBridge.log("unhook all PathClassLoader Constructors");
                            }
                        }
                    }
                });
    }


}
