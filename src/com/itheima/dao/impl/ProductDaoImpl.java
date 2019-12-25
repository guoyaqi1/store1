package com.itheima.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.constant.Constant;
import com.itheima.dao.ProductDao;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.utils.DataSourceUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	//��ѯ������Ʒ
	public List<Product> findHot() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where is_hot = ? and pflag = ? order by pdate desc limit 9";
		return qr.query(sql, new BeanListHandler<>(Product.class), Constant.PRODUCT_IS_HOT,Constant.PRODUCT_IS_UP);
		}

	@Override
	//��ѯ������Ʒ
	public List<Product> findNew() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pflag = ? order by pdate desc limit 9";
		return qr.query(sql, new BeanListHandler<>(Product.class),Constant.PRODUCT_IS_UP);
	}

	@Override
	//��ѯ������Ʒ
	public Product getById(String pid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid = ? ";
		return qr.query(sql, new BeanHandler<>(Product.class), pid);
		
		
	}

	@Override
	//��ѯ��ǰҳ����
	public List<Product> findByPage(PageBean<Product> pb, String cid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid = ? and pflag = ? order by pdate desc limit ?,?";
		return qr.query(sql, new BeanListHandler<>(Product.class), cid,Constant.PRODUCT_IS_UP,pb.getStartIndex(),pb.getPageSize());
	}

	@Override
	//��ȡ�ܼ�¼��
	
	public int getTotalRecord(String cid) throws Exception {
		return ((Long)new QueryRunner(DataSourceUtils.getDataSource()).query("select count(*) from product where cid = ? and pflag = ?", new ScalarHandler(), cid,Constant.PRODUCT_IS_UP)).intValue();
		}

}
