package com.decoxy.common.webservice;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.decoxy.common.CodeListKeyBean;
import com.decoxy.common.util.StringUtil;

public abstract class WebServiceMap {

	private static List<WebserviceItem> wsList = new ArrayList<WebserviceItem>();
	private static List<WebserviceItem> updateList = new ArrayList<WebserviceItem>();
	private static List<WebserviceItem> deleteList = new ArrayList<WebserviceItem>();
	private static List<String> allUrlList = new ArrayList<String>();

	static {

		String path = WebServiceMap.class.getResource("/").getPath();
		SAXReader read = new SAXReader();
		Document xmlDoc;
		try {
			// path = path.substring(1, path.length());
			String allPath = path + "subsite-ws.xml";

			xmlDoc = read.read(new File(allPath));

			if (xmlDoc != null) {

				Element root = xmlDoc.getRootElement();

				wsList.clear();
				updateList.clear();
				deleteList.clear();

				for (Iterator it = root.elementIterator("webservice"); it.hasNext();) {

					Element s = (Element) it.next();

					WebserviceItem tempBean = new WebserviceItem();
					tempBean.setType(StringUtil.getString(s.attributeValue("type")));
					tempBean.setOperation(StringUtil.getString(s.elementText("operation")));
					tempBean.setUrl(StringUtil.getString(s.elementText("url")));
					tempBean.setAddress(StringUtil.getString(s.elementText("address")));
					tempBean.setMethod(StringUtil.getString(s.elementText("method")));
					tempBean.setParamType(StringUtil.getString(s.elementText("paramType")));

					if ("update".equals(tempBean.getOperation())) {
						updateList.add(tempBean);
						String str = tempBean.getUrl();
						str = str.replace("/userinfo", "");
						str = str.replace("/services/xmlrpc", "");
						allUrlList.add(str);
					} else {
						deleteList.add(tempBean);
					}
					wsList.add(tempBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<WebserviceItem> getList() {
		return wsList;
	}

	public static List<WebserviceItem> getList(String flag) {
		if ("update".equals(flag)) {
			return updateList;
		} else {
			return deleteList;
		}
	}

	/**
	 * @return the urlList
	 */
	public static List<String> getAllUrlList() {
		return allUrlList;
	}
	
	public static void main(String[] args) {
		List<WebserviceItem> tempList = WebServiceMap.getList();
		System.out.println("tempList===" + tempList.size());
		System.out.println("tempList===" + updateList.size());
		System.out.println("tempList===" + deleteList.size());
		System.out.println("tempList===" + allUrlList.size());
	}
}