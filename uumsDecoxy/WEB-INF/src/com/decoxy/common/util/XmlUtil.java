package com.decoxy.common.util;

import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.decoxy.common.CommonConst;
import com.decoxy.user.formbean.UserFormBean;

public class XmlUtil {

	/**
	 * 生成XML文件
	 * 
	 * @param bean
	 * @return
	 * @throws IOException
	 */
	public static Document buildXMLDoc(UserFormBean bean) throws IOException {

		Document xmlDoc = DocumentHelper.createDocument();
		// 创建根节点 UUMS
		Element root = xmlDoc.addElement("UUMS");
		// 创建子节点 ReturnInfo
		Element returnInfo = root.addElement("ReturnInfo");
		// 设置Result
		Element result = returnInfo.addElement("Result");
		result.setText(bean.getResult());
		// 设置message
		int size = bean.getMsgList().size();
		if (size > 0) {
			Element msgList = returnInfo.addElement("MsgList");
			for (int i = 0; i < size; i++) {
				String value = bean.getMsgList().get(i);
				String[] str = value.split(CommonConst.SPLIT_KEY);
				// 设置ErrID
				Element errID = msgList.addElement("ErrID");
				errID.setText(str[0]);
				// 设置Message
				Element message = msgList.addElement("Message");
				message.setText(StringUtil.setValue(str[1]));
			}
		}
		// 创建子节点 BaseInfo
		Element baseInfo = root.addElement("BaseInfo");
		// 设置UserName
		Element username = baseInfo.addElement("UserName");
		username.setText(StringUtil
				.setValue(bean.getReturnBean().getUsername()));
		// 设置password
		Element password = baseInfo.addElement("PassWord");
		password.setText(StringUtil
				.setValue(bean.getReturnBean().getPass_expressly()));
		// 设置Init
		Element init = baseInfo.addElement("Init");
		init.setText(bean.getReturnBean().getInit());
		// 设置Mail
		Element mail = baseInfo.addElement("Mail");
		mail.setText(bean.getReturnBean().getMail());
		// 设置Status
		Element status = baseInfo.addElement("Status");
		status.setText(bean.getReturnBean().getStatus());
		// 设置DeleteLevel
		Element deleteLevel = baseInfo.addElement("DeleteLevel");
		deleteLevel.setText(bean.getReturnBean().getDeleteLevel());
		// 设置ServiceTicket
		Element serviceTicket = baseInfo.addElement("ServiceTicket");
		serviceTicket.setText(bean.getBaseInfo_ServiceTicket());

		return xmlDoc;
	}

}