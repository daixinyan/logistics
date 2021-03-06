package org.module.client.presentation.orderui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import org.module.client.businesslogic.orderbl.TranCenterLoadingController;
import org.module.client.businesslogicservice.orderBLservice.TranCenterLoadingBLService;
import org.module.client.presentation.ResultFrame;
import org.module.client.vo.TranCenterLoadingListVO;

public class ListTableForTranCenterLoading extends ListTableForAll {

	
	private static final long serialVersionUID = -7237937068690576654L;

	private TranCenterLoadingBLService controller;
	protected ArrayList<TranCenterLoadingListVO> listCell;
	@Override
	protected void initData() {
		this.controller = new TranCenterLoadingController();
		this.listCell = this.controller.getAll();
		this.typeArray = new String[]{
					"装车日期","中转中心","汽运编号","出发地","目的地",
					"车号","监装员","司机","物流单号","价格","状态"
			};
	}

	@Override
	protected void refresh() {
		this.listCell = this.controller.getAll();
		this.table.setList(listCell);
		this.table.setName(typeArray);
		this.table.fireTableDataChanged();;
	}

	@Override
	protected void modify() {
		int[] indexes = this.table.getCheckedIndexes();
		if(indexes.length!=1){
			return;
		}
		final NewTranCenterLoadingrListInputFrame frame = new NewTranCenterLoadingrListInputFrame(
				this.controller.getCityArray(),
				this.listCell.get(indexes[0]));
		frame.setVisible(true);
		frame.getComfirm().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(frame.isDataUsable()){
					if(controller.update(frame.getVO())){
						frame.dispose();
						new ResultFrame(true);
					}else{
						new ResultFrame(false);
					}
					table.fireTableDataChanged();
					
				}
			}
		});
	}

	@Override
	protected void add() {
		final NewTranCenterLoadingrListInputFrame frame = new NewTranCenterLoadingrListInputFrame(
				this.controller.getCityArray());
		frame.setVisible(true);
		frame.getComfirm().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(controller.creat(frame.getVO())){
					frame.dispose();
					new ResultFrame(true);
				}else{
					new ResultFrame(false);
				}
				table.fireTableDataChanged();
			}
		});
	}

}
