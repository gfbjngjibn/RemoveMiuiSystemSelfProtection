# RemoveMiuiSystemSelfProtection
allow uninstall some miui preinstall apps
可以卸载部分预装app而且不会卡米

这个模块不会自动帮你卸载app，需要自行手动卸载

提示：系统对预装app都留有后门，包括删除apk（提示相关目录无法挂载读写），冻结(提示失败或重启后自动解冻)，禁用服务（提示失败）等功能都无法对某些预装app生效

推荐使用magisk模块来删除对应app

[Debloater (Terminal Emulator)](https://github.com/Magisk-Modules-Repo/terminal_debloater)

实际操作前请准备救砖措施

有些app和系统深度耦合，不建议卸载(仅仅是不建议，如果有能力处理卸载后遇到的各种问题，依然可以卸载)

```java
checkApps.add("com.lbe.security.miui");//权限管理服务(不建议卸载)
checkApps.add("com.android.updater");//系统更新
checkApps.add("com.miui.securitycenter");//手机管家(不建议卸载)
checkApps.add("com.xiaomi.finddevice");//查找手机
checkApps.add("com.miui.home");//系统桌面(不建议卸载)
checkApps.add("com.miui.guardprovider");//MIUI安全组件(不建议卸载)
checkApps.add("com.miui.gallery");//相册
if (!Build.IS_INTERNATIONAL_BUILD && !Build.IS_CM_CUSTOMIZATION && !Build.IS_CM_CUSTOMIZATION_TEST) {
    //从代码语义上可以看出这三个app是ChinaMainland特供的，作者已经测试，卸载之后系统可以正常工作，建议卸载
    checkApps.add("com.miui.player");//音乐
    checkApps.add("com.android.browser");//浏览器
    checkApps.add("com.xiaomi.market");//应用商店
}
```
