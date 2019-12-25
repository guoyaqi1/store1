package com.itheima.service.impl;

import java.util.List;



import com.itheima.dao.ProductDao;
import com.itheima.dao.impl.ProductDaoImpl;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;

public class ProductServiceImpl implements ProductService {

	@Override
	//��ѯ������Ʒ
	public List<Product> findHot() throws Exception {
		/*ProductDao pd= new ProductDaoImpl();
		return pd.findHot();*/
		ProductDao pd= (ProductDao) BeanFactory.getBean("ProductDao");
		return pd.findNew();
		
	}

	@Override
	//��ѯ��������Ʒ
	public List<Product> findNew() throws Exception {
		/*ProductDao pd= new ProductDaoImpl();
		return pd.findNew();*/
		ProductDao pd= (ProductDao) BeanFactory.getBean("ProductDao");
		return pd.findHot();
	}

	@Override
	//������Ʒ����
	public Product getById(String pid) throws Exception {
		/*ProductDao pd=new ProductDaoImpl();
		
		return pd.getById(pid);*/
        ProductDao pd=(ProductDao) BeanFactory.getBean("ProductDao");
		
		return pd.getById(pid);
	}

	@Override
	//��ҳչʾ��Ʒ
	public PageBean<Product> findByPage(int pageNumber, int pageSize, String cid) throws Exception {
		//ProductDao pDao= new ProductDaoImpl();
		ProductDao pDao= (ProductDao) BeanFactory.getBean("ProductDao");
		//1.����pagebean
		PageBean<Product> pb = new PageBean<>(pageNumber, pageSize);
		
		//2.���õ�ǰҳ����
		List<Product> data = pDao.findByPage(pb,cid);
		pb.setData(data);
		
		//3.�����ܼ�¼��
		int totalRecord = pDao.getTotalRecord(cid);
		pb.setTotalRecord(totalRecord);
		
		return pb;
	}

}
