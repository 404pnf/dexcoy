package test;

import com.decoxy.base.AbstractService;

/**
 * 
 * @author Administrator
 * 
 */
public class TestDbConn extends AbstractService {

	public Integer testDbConn() {

		TestDbConn c = new TestDbConn();
		int i = 0;
		try {
			i = c.conTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}