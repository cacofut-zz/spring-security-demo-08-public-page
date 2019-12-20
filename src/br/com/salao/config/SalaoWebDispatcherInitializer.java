package br.com.salao.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SalaoWebDispatcherInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {		
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {		
		return new Class[]{ SalaoWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
