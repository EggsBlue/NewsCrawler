package com.huage.crawler;

import com.huage.crawler.service.TTCrawler;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

/**
 */
public class MainSetup implements Setup {

	private Log log = Logs.get();

	@Override
	public void init(NutConfig nc) {
		new TTCrawler().run();;
	}

	@Override
	public void destroy(NutConfig nutConfig) {

	}
}
