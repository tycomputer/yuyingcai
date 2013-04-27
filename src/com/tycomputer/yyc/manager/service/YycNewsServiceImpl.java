/*
 * @(#)TechartiManagerImpl.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tycomputer.common.util.DateUtil;
import com.tycomputer.common.web.CacheData;
import com.tycomputer.yyc.entity.YycNews;
import com.tycomputer.yyc.manager.action.YycNewsForm;

/**
 * 
 * 日期 : 2011-10-8 下午6:15:14<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : 育英才网站新闻 ServiceImpl<br>
 */
public class YycNewsServiceImpl implements IYycNewsService {

	private HibernateTemplate dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tycomputer.web.ITechartiManager#saveTecharti(com.tycomputer.web.
	 * TechartiForm)
	 */
	// @Override
	public String saveNews(YycNewsForm form) {
		String id = form.getUuid();
		boolean isnew = true;
		YycNews news = null;
		
		if ((id != null) && (!id.equals(""))) {
			 news = (YycNews) dao.load(YycNews.class, form.getUuid());
			 isnew = false;
		}
		if ( news == null) {
			news = new YycNews();
			isnew = true;
		}
		news.setAddData(DateUtil.getSysCalendar());
		news.setDescription(form.getDescription());
		news.setFlag(form.getFlag());
		news.setNewsTitle(form.getNewsTitle());
		
		if (isnew) {
			dao.save(news);
		} else {
			dao.update(news);
		}
		
		CacheData.YYC_NEWS = dao.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("select t.uuid,t.newsTitle,t.addData from YycNews t where t.flag='0' order by t.addData desc");
				query.setFirstResult(0);
				query.setMaxResults(100);
				List l = query.list();
				List<String[]> itemList = new ArrayList<String[]>();
				for (int i=0; i<l.size(); i++){
					Object[] obj = (Object[])l.get(i);
					String[] item = new String[3];
					item[0] = (String)obj[0];
					item[1] = (String)obj[1];
					item[2] = DateUtil.format((Calendar)obj[2], "yyyy-MM-dd");
					itemList.add(item);
				}				
				return itemList;		
			}
		});
		
		return news.getUuid();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.web.ITechartiManager#loadEntityAndSetFrom(com.tycomputer
	 * .web.TechartiForm)
	 */
	// @Override
	public void loadEntityAndSetFrom(YycNewsForm form) {
		if (form == null){
			form = new YycNewsForm();
			return;
		}
		YycNews news = (YycNews) dao.load(YycNews.class, form.getUuid());
		if (news != null) {
			form.setFlag(news.getFlag());
			form.setDescription(news.getDescription() == null ? "" : news.getDescription());
			form.setNewsTitle(news.getNewsTitle());
			form.setAddData(news.getAddData());
		}

	}

	public String getQuerySQL(YycNewsForm form) {
		if (form == null) {
			form = new YycNewsForm();
		}
		StringBuffer sb = new StringBuffer();

		sb.append("select t.uuid,t.newsTitle,t.flag,t.addData from YycNews t");
		if ((form.getSearchNewsTitle()!= null) && (!form.getSearchNewsTitle().trim().equals(""))) {
			sb.append(" where t.newsTitle like '").append(form.getSearchNewsTitle().trim()).append("' ");
		}
		
		sb.append(" order by t.addData desc");
		return sb.toString();
	}

	public void delNews(YycNewsForm form) {
		YycNews news = (YycNews) dao.load(YycNews.class, form.getUuid());
		if (news != null) {
			dao.delete(news);
		}

	}

	public void setDao(HibernateTemplate dao) {
		this.dao = dao;
	}

	
}

// /*
// * (non-Javadoc)
// *
// * @see com.tycomputer.web.IHjpresentManager#getAllPhoto()
// */
// //@Override
// public List getAllPhoto() {
//
// return dao.getList(Hjpresent.class);
// }

/*
 * (non-Javadoc)
 * 
 * @see com.tycomputer.web.IHjpresentManager#getDataDetailJson()
 */
// //@Override
// public List<String[]> getDataDetail() {
// List<String[]> result = new ArrayList<String[]>();
//
// for (int i = 0; i < CacheData.HJDATACATA.size(); i++) {
// Hjdatacata cata = CacheData.HJDATACATA.get(i);
// Iterator<Hjdatatype> iter = cata.getDatatypes().iterator();
// while (iter.hasNext()) {
// Hjdatatype type = (Hjdatatype) iter.next();
// String[] str = new String[3];
// str[0] = cata.getCataId();
// str[1] = type.getTypeId();
// str[2] = type.getTypeName();
// result.add(str);
// }
// }
// return result;
// }
//
// private String getMaxCode(Hjdatatype type) {
// int maxid = 0;
// @SuppressWarnings("rawtypes")
// List l = dao.find("select max(t.uid) from Hjpresent t where t.datatype=?",
// new Object[] { type });
// if ((l != null) && (l.size() > 0)) {
// String maxUid = (String) l.get(0);
// if (maxUid != null) {
// try {
// maxUid = maxUid.substring(6, 9);
// maxid = Integer.parseInt(maxUid);
// } catch (Exception e) {
// }
// }
// }
// maxid++;
// String maxStr = maxid < 10 ? "00" + maxid : (maxid < 100 ? "0" + maxid : "" +
// maxid);
//
// return type.getDatacata().getCataSort() + "-" + type.getTypeSort() + "-" +
// maxStr;
// }

// /**
// *
// * 功能说明 : 转换查询出的数据，转换，礼品目录、类别、标志
// *
// * @param list
// * @return
// */
// @SuppressWarnings("unchecked")
// public List transform(List list) {
// if ((list == null) || (list.size() == 0)) {
// return new ArrayList();
// }
// for (int i = 0; i < list.size(); i++) {
// Object[] obj = (Object[]) list.get(i);
// obj[0] = CacheData.getNameOfHjdatatype(obj[0].toString());
// obj[1] = CacheData.getNameOfDatadeta(obj[1].toString());
// obj[5] = ((Boolean) obj[5]) ? "启用" : "停产";
// }
// return list;
// }

/*
 * (non-Javadoc)
 * 
 * @see
 * com.tycomputer.web.IHjpresentManager#delOldImg(com.tycomputer.web.PresentForm
 * )
 */
// //@Override
// public void delOldImg(String imgId) {
// if ((imgId != null) && (!imgId.equals(""))) {
// Img lit = (Img) dao.load(Img.class, imgId);
// dao.delete(lit);
// }
//
// }

/*
 * (non-Javadoc)
 * 
 * @see
 * com.tycomputer.web.IHjpresentManager#getQuerySQL(com.tycomputer.web.PresentForm
 * )
 */
// @Override

/*
 * (non-Javadoc)
 * 
 * @see
 * com.tycomputer.web.IHjpresentManager#delHjpresent(com.tycomputer.web.PresentForm
 * )
 */
// @Override

// /**
// *
// * 功能说明 : 删除文件
// *
// * @param path
// * @param picName
// */
// private void delPic(String path, String picName) {
// File file = new File(path, picName);
// if (file.exists()) {
// file.delete();
// }
//
// }

// }
