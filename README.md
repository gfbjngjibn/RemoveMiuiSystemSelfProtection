# RemoveMiuiSystemSelfProtection
allow uninstall some miui preinstall apps
可以卸载部分预装app而且不会卡米

```java
checkApps.add("com.lbe.security.miui");//权限管理服务
checkApps.add("com.android.updater");//系统更新
checkApps.add("com.miui.securitycenter");//手机管家
checkApps.add("com.xiaomi.finddevice");//查找手机
checkApps.add("com.miui.home");//系统桌面
checkApps.add("com.miui.guardprovider");//MIUI安全组件
checkApps.add("com.miui.gallery");//相册
if (!Build.IS_INTERNATIONAL_BUILD && !Build.IS_CM_CUSTOMIZATION && !Build.IS_CM_CUSTOMIZATION_TEST) {
    checkApps.add("com.miui.player");//音乐
    checkApps.add("com.android.browser");//浏览器
    checkApps.add("com.xiaomi.market");//应用商店
}
```
