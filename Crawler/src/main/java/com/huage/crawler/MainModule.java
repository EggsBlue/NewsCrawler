package com.huage.crawler;

import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@SetupBy(value = MainSetup.class)
@IocBy(type = ComboIocProvider.class, args = { "*js", "ioc/", "*anno", "com.huage.crawler",
		"*tx","*quartz" })
@Modules(scanPackage = true, packages = { "com.huage.crawler"})
@ChainBy(args = "mvc/chain.js")
@Encoding(input = org.nutz.lang.Encoding.UTF8, output = org.nutz.lang.Encoding.UTF8)
//@Ok("json")
public class MainModule {

}
