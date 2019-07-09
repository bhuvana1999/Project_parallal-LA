package cap.dao;

import java.util.HashMap;

import cap.bean.BankBean;

public class BankDao implements BankDaoInF {

	BankBean bbo;

	HashMap<Long, BankBean> hm = new HashMap<Long, BankBean>();

	public void addCustomer(BankBean bbo)
	{		                                       
		this.bbo = bbo;						     
		hm.put(bbo.getAccNo(), bbo);			 
	}

	public HashMap<Long, BankBean> hm()
	{		                                       
		return hm;
	}
}

