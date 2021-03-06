package com.fxx.sorm.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fxx.sorm.core.DBManager;

/**
 * 链接池类
 * @author 风潇潇
 *
 */
public class DBConnPool {
	/**
	 * 链接池对象
	 */
	private static List<Connection> pool;//连接池对象
	/**
	 * 最大连接数
	 */
	private static final int POOL_MAX_SIZE = DBManager.getConf().getPoolMaxSize();
	/**
	 * 最小连接数
	 */
	private static final int POOL_MIN_SIZE = DBManager.getConf().getPoolMinSize();
	/**
	 * 初始化连接池,使连接池内的链接达到最小值
	 */
	public void initPool(){
		if(pool==null){
			pool = new ArrayList<Connection>();
		}
		while(pool.size()<DBConnPool.POOL_MIN_SIZE){
			pool.add(DBManager.creatConn());
		}
	}
	public DBConnPool(){
		initPool();
	}
	/**
	 * 从链接池中取出一个链接
	 * @return
	 */
	public synchronized Connection getConnection(){
		int last_index = pool.size()-1;
		if(last_index>=0){
			Connection conn = pool.get(last_index);
			pool.remove(last_index);
			return conn;
		}else{
			return DBManager.creatConn();
		}
	}
	/**
	 * 将连接放回池中
	 * @param conn
	 */
	public synchronized void close(Connection conn){
		if(pool.size()>=POOL_MAX_SIZE){
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			pool.add(conn);
		}
		
	}
	
	
}
