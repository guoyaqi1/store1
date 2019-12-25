package com.itheima.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author Administrator
 *
 */
public class Cart {
	private Map<String, CartItem> itemMap=new HashMap<String, CartItem>();
	private Double total=0.0;
	
	/**
	 * ��ȡ���еĹ�����
	 * @return
	 */
	public  Collection<CartItem> getCartItems(){
		return itemMap.values();
	}
	public Map<String, CartItem> getItemMap() {
		return itemMap;
	}
	public void setItemMap(Map<String, CartItem> itemMap) {
		this.itemMap = itemMap;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	/**
	 * ���빺�ﳵ
	 * @param item
	 */
	public void add2cart(CartItem item){
		//��ȡ��Ʒ��pid
		String pid = item.getProduct().getPid();
		
		//�жϹ��ﳵ���Ƿ���
		if(itemMap.containsKey(pid)){
			//���޸�����=ԭ������+�¼ӵ�����
			//ԭ�еĹ�����
			CartItem oItem = itemMap.get(pid);
			
			oItem.setCount(oItem.getCount()+item.getCount());
		}else{
			//��
			itemMap.put(pid, item);
		}
		
		//�޸��ܽ��
		total += item.getSubtotal();
	}
	
	/**
	 * �ӹ��ﳵ�Ƴ�
	 * @param pid
	 */
	public void removeFromCart(String pid){
		//1.�ӹ��ﳵ(map)�Ƴ�������
		CartItem item = itemMap.remove(pid);
		
		//2.�޸��ܽ��
		total -= item.getSubtotal();
	}
	
	/**
	 * ��չ��ﳵ
	 */
	public void clearCart(){
		//1.���map����
		itemMap.clear();
		
		//2.�޸��ܽ��Ϊ0
		total=0.0;
	}
}

