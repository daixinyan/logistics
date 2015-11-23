package org.module.server.data.managementdata;

import java.io.File;
import java.util.ArrayList;

import org.module.common.dataservice.managementdataservice.PriceAndCityDataService;
import org.module.common.po.PriceAndCityPO;
import org.module.server.data.FileHelper;

public class PriceAndCityDataImpl implements PriceAndCityDataService {

	String path="file"+File.separator+"PriceAndCity.txt";
	File file=new File(path);
	FileHelper help=new FileHelper(file);
	
	public PriceAndCityDataImpl() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<PriceAndCityPO> getAll() {
		// TODO Auto-generated method stub
		ArrayList<String> re = this.help.read();
		ArrayList<PriceAndCityPO> ar =  new ArrayList<PriceAndCityPO>();	
		for (String string : re) {
			ar.add(new PriceAndCityPO(string));
		}
		return ar;
	}

	public ArrayList<String> getCity() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addCity(String city) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addPriceAndCity(PriceAndCityPO p) {
		// TODO Auto-generated method stub
		return this.help.add(p);
	}

	public boolean update(PriceAndCityPO p) {
		// TODO Auto-generated method stub
		ArrayList<PriceAndCityPO> re = this.getAll();
		for (int i = 0; i < re.size(); i++) {
			if(re.get(i).getcityA().equals(p.getcityA())){
				re.remove(i);
				re.add(p);
				ArrayList<Object> c =  new ArrayList<Object>();
				c.add(re);
				return help.rewrite(c);
			}
		}		
		return false;
	}

}