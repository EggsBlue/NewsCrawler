var ioc = {
	conf : {
		type : "org.nutz.ioc.impl.PropertiesProxy",
		fields : {
			utf8  : false,
			paths : [ "config/" ]
		}
	},
    cronConf : {
        type : "org.nutz.ioc.impl.PropertiesProxy",
        fields : {
            utf8  : false,
            paths : [ "cron.properties" ]
        }
    }

}