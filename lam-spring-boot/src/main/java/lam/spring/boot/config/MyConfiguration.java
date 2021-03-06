package lam.spring.boot.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import lam.spring.boot.model.MyThing;
import lam.spring.boot.model.MyThing1;

/**
* <p>
* TODO
* </p>
* @author linanmiao
* @date 2018年6月16日
* @versio 1.0
*/
@Configuration
public class MyConfiguration extends WebMvcConfigurerAdapter{
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		System.out.println(MyConfiguration.class.getName() + "-configureMessageConverters()");
		//json:
		MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.parseMediaType("application/json;charset=UTF-8"));
		supportedMediaTypes.add(MediaType.parseMediaType("text/html;charset=UTF-8"));
		//or use like below:
		//supportedMediaTypes.addAll(MediaType.parseMediaTypes("application/json;charset=UTF-8,text/html;charset=UTF-8"));
		mappingJacksonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
		converters.add(mappingJacksonHttpMessageConverter);
		
		//string:
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
		List<MediaType> supportedMediaTypes1 = new ArrayList<MediaType>();
		supportedMediaTypes1.add(MediaType.parseMediaType("application/json;charset=UTF-8"));
		supportedMediaTypes1.add(MediaType.parseMediaType("text/html;charset=UTF-8"));
		stringHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes1);
		converters.add(stringHttpMessageConverter);
		
		super.configureMessageConverters(converters);
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		System.out.println(MyConfiguration.class.getName() + "-configureViewResolvers()");
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(2);
		
		registry.viewResolver(viewResolver);
		
		super.configureViewResolvers(registry);
	}
	
	/**@Bean
	public HttpMessageConverters customConverters() {
		System.out.println(MyConfiguration.class.getName() + "-customConverters()");
		//HttpMessageConverter<MyThing> additional = new HttpMessageConverter<MyThing>() {
		HttpMessageConverter<MyThing> additional = new AbstractHttpMessageConverter<MyThing>() {

			@Override
			protected boolean supports(Class<?> clazz) {
				return clazz == MyThing.class;
			}

			@Override
			protected MyThing readInternal(Class<? extends MyThing> clazz, HttpInputMessage inputMessage)
					throws IOException, HttpMessageNotReadableException {
				//inputMessage.getBody().read(b)
				return null;
			}

			@Override
			protected void writeInternal(MyThing t, HttpOutputMessage outputMessage)
					throws IOException, HttpMessageNotWritableException {
				//outputMessage.getBody().write(b);
			}

			};
			HttpMessageConverter<MyThing1> additional1 = new AbstractHttpMessageConverter<MyThing1>() {

				@Override
				protected boolean supports(Class<?> clazz) {
					return clazz == MyThing1.class;
				}

				@Override
				protected MyThing1 readInternal(Class<? extends MyThing1> clazz, HttpInputMessage inputMessage)
						throws IOException, HttpMessageNotReadableException {
					//inputMessage.getBody().read(b)
					return null;
				}

				@Override
				protected void writeInternal(MyThing1 t, HttpOutputMessage outputMessage)
						throws IOException, HttpMessageNotWritableException {
					//outputMessage.getBody().write(b);
				}

				};
		return new HttpMessageConverters(additional, additional1); //可以添加一个或多个HttpMessageConverter
	}*/

}
