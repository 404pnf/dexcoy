package test;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import redstone.xmlrpc.XmlRpcClient;

import com.decoxy.common.CommonConst;
import com.decoxy.common.util.StringUtil;
import com.decoxy.common.webservice.WebServiceMap;
import com.decoxy.common.webservice.WebserviceItem;
import com.decoxy.user.dao.OperationLogDao;
import com.decoxy.user.dao.OperationLogDaoIF;
import com.decoxy.user.dao.daoBean.OperationLogDaoBean;
import com.decoxy.user.dao.daoBean.UserDaoBean;

public class Test {

	public static void main(String[] args) {
			
		Service service = new Service();

		String url = "http://192.168.1.133/uumsDecoxy/services/UserAuthWS?wsdl";
		String address = "";
		String method = "loginUser";
		
		Call call;
		String returnValue = "";
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setUseSOAPAction(true);
			call.setOperationName(new QName(address, method));

			Object[] obj = new Object[1];
			obj[0] = "<UUMS><BaseInfo><UserName>%27</UserName><PassWord>111111</PassWord></BaseInfo></UUMS>";
			returnValue = (String) call.invoke(obj);
			System.out.println("returnValue===" + returnValue);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
