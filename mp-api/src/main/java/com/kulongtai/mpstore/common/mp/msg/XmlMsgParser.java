/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.kulongtai.mpstore.common.mp.msg;



import com.kulongtai.mpstore.common.mp.converter.TypeConverter;
import com.kulongtai.mpstore.common.mp.msg.bean.WxaMsg;
import com.kulongtai.mpstore.common.mp.util.XmlHelper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.text.ParseException;

/**
 * xml格式消息解析
 * @author l.cm
 *
 */
@Slf4j
public class XmlMsgParser extends MsgModelParser implements IMsgParser {

	@Override
	public WxaMsg parser(String msgStr) {
		XmlHelper xmlHelper = XmlHelper.of(msgStr);
		MsgModel msgModel = toMsgModel(xmlHelper);
		return parserMsg(msgModel);
	}

	private static MsgModel toMsgModel(XmlHelper xmlHelper) {
		MsgModel msgModel = new MsgModel();
		Field[] fields = MsgModel.class.getDeclaredFields();
		for (Field field : fields) {
			XPath xpathAnno = field.getAnnotation(XPath.class);
			if (xpathAnno == null) {
				continue;
			}
			
			String xpath    = xpathAnno.value();
			Class<?> type   = field.getType();
			String strValue = xmlHelper.getString(xpath);
			
			Object value = convert(type, strValue);
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			try {
				field.set(msgModel, value);
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage(), e);
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}
		return msgModel;
	}
	
	private static final Object convert(Class<?> type, String s) {
		try {
			return TypeConverter.me().convert(type, s);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
