package org.module.client.businesslogic.orderbl;

import java.rmi.RemoteException;
import java.util.Date;

import org.module.client.businesslogic.logisticsbl.LogisticsState;
import org.module.client.businesslogicservice.management.TicketAndorderVerify;
import org.module.client.javaRMI.RmiClient;
import org.module.client.presentation.DateTransferHelper;
import org.module.client.vo.LogisticsVO;
import org.module.client.vo.TransportListVO;
import org.module.common.dataservice.MyList;
import org.module.common.dataservice.orderdataservice.TransportListService;
import org.module.common.po.State;
import org.module.common.po.TransportListPO;

public class TransportVerify  implements TicketAndorderVerify{

	private TransportListService transport = new RmiClient().get(TransportListService.class);
	private LogisticsState logistics = new LogisticsState();
	private MyList<TransportListVO> list;
	
	public MyList<TransportListVO> getAll(){
		list = new MyList<TransportListVO>();
		try {
			MyList<TransportListPO> pos = this.transport.getByState(State.SUBMITTED);
			for (TransportListPO transportListPO : pos) {
				list.add(new TransportListVO(transportListPO));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean pass(int[] index){
		
		boolean re = true;
		try{
			for(int i = index.length-1; i>=0; i--){
				TransportListVO transportListVO = this.list.get(index[i]);
				
				if(this.updateLogistics(transportListVO)){
					this.list.remove(index[i]);
					transportListVO.setState(State.PASS);
				}
				
				re = re &&this.transport.update(transportListVO.toPO());
			}
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return re;
	}
	
	
	private boolean updateLogistics(TransportListVO transportListVO){
		String[] ordersArray = transportListVO.getShippingId();
		boolean re = true;
		for (String order : ordersArray) {
			LogisticsVO logisticsVO = this.logistics.find(order);
			if(logisticsVO!=null){
				logisticsVO.setLocation("正在中转");
				logisticsVO.addLocationAndTime("正在中转,准备发往"+transportListVO.getArrival(), DateTransferHelper.getString(new Date()));
			    this.logistics.update(logisticsVO);
			}else{
				re = false;
			}
			
		}
		return re;
		
	}
	
	
	public boolean unpass(int[] index){
		boolean re = true;
		try{
			for(int i = index.length-1; i>=0; i--){
				TransportListVO vo = this.list.get(index[i]);
				vo.setState(State.UNPASS);
				re = re &&this.transport.update(vo.toPO());
			}
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return re;
	}
}
