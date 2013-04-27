package com.tycomputer.common;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Image;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.io.WireFeedOutput;
import com.tycomputer.common.util.DateUtil;
import com.tycomputer.common.util.SpringUtil;
import com.tycomputer.yyc.entity.YycNews;

/**
 * 日期 : 2011-10-26 下午8:35:09<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : <br>
 */
public class RSS extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		String type = req.getParameter("t");
		HibernateTemplate dao = (HibernateTemplate) SpringUtil.getBean("dao");
		Channel channel = new Channel("rss_2.0");
		channel.setEncoding("UTF-8");
		channel.setLanguage("zh_CN");
		channel.setPubDate(DateUtil.getSysDate());

		List<Item> items = new ArrayList<Item>();

		if (type != null && dao != null) {
			if (type.equals("d")) {
				channel.setDescription("北京东创伟业礼品有限公司,是专业的北京礼品提供商,从事生产设计销售商务礼品,办公礼品,促销礼品,广告礼品,电子礼品,水晶工艺礼品,时尚礼品,装饰礼品,纪念礼品的礼品公司。电话：13436565358,010-82621648");
				channel.setLink("http://www.dclipin.com/");
				channel.setTitle("北京东创伟业商贸公司");
				List l = dao
						.find("select t.uid,t.pname,t.description,t.bigpic,t.datacata.cataName,t.datatype.typeName from Present t where t.addData>str_to_date('05.28.2011', '%m.%d.%Y') order by t.addData desc");
				for (int i = 0; i < l.size(); i++) {
					Object[] obj = (Object[]) l.get(i);
					String img = obj[3] == null ? "" : "<img src='http://www.dclipin.com/images/p/" + obj[3].toString() + "' alt='" + obj[4].toString() + " "
							+ obj[5].toString() + "  " + obj[1].toString() + "' />";
					String desc = img + (obj[2] == null ? "" : obj[2].toString());
					Item item = new Item();
					item.setAuthor("ZhangLiuDong");
					item.setLink("http://www.dclipin.com/presentDetail.do?i=" + obj[0].toString());
					item.setTitle(obj[4].toString() + " " + obj[5].toString() + " " + obj[1].toString());
					Description description = new Description();
					description.setType("text/html");
					description.setValue(desc);
					item.setDescription(description);
					items.add(item);
				}

			} else if (type.equals("h")) {
				channel.setDescription("北京旭日东升货架有限公司;北京货架厂,仓储货架厂,角钢货架,库房货架,精品货架,阁楼平台,货架批发,货架厂家,货架公司,木制展柜,展架,柜台,北京货架厂家 ");
				channel.setLink("http://www.xrdshuojia.com/");
				channel.setTitle("北京旭日东升货架有限公司");
				List l = dao
						.find("select t.uid,t.pname,t.description,t.bigpic,t.datacata.cataName,t.datatype.typeName from Hjpresent t where t.addData>str_to_date('05.28.2011', '%m.%d.%Y') order by t.addData desc");
				for (int i = 0; i < l.size(); i++) {
					Object[] obj = (Object[]) l.get(i);
					String img = obj[3] == null ? "" : "<img src='http://www.xrdshuojia.com/images/h/" + obj[3].toString() + "' alt='" + obj[4].toString()
							+ " " + obj[5].toString() + "  " + obj[1].toString() + "' />";
					String desc = img + (obj[2] == null ? "" : obj[2].toString());
					Item item = new Item();
					item.setAuthor("XuDongSheng");
					item.setLink("http://www.xrdshuojia.com/hjDetail.do?i=" + obj[0].toString());
					item.setTitle(obj[4].toString() + " " + obj[5].toString() + " " + obj[1].toString());
					Description description = new Description();
					description.setType("text/html");
					description.setValue(desc);
					item.setDescription(description);
					items.add(item);
				}
			} else {
				channel.setDescription("北京育英才教育是集0-6岁的婴幼儿师资培养,幼儿园,电子商务,互联网服务为一体的综合性婴幼儿教育服务品牌. 幼师培训,幼师学校,幼师,幼儿师资培养,北京幼师,育英才幼师,育英才教育,幼教培训,幼儿教师培训,幼师就业,幼儿教师,幼儿园,幼师学院,幼师英语培训,幼儿师资培训");
				channel.setLink("http://www.yuyingcai.com.cn/");
				channel.setTitle("北京育英才教育科技有很责任公司");

				Image image = new Image();
				image.setTitle("yuyingcai logo,育英才");
				image.setLink("http://www.yuyingcai.com.cn/");
				image.setUrl("http://www.yuyingcai.com.cn/imgs/logo.jpg");
				image.setHeight(80);
				image.setWidth(267);
				channel.setImage(image);

				List l = dao.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("from YycNews t order by t.addData desc");
						query.setFirstResult(0);
						query.setMaxResults(30);
						List list = query.list();
						return list;
					}
				});
				for (int i = 0; i < l.size(); i++) {
					YycNews yycNews = (YycNews) l.get(i);
					Item item = new Item();
					item.setPubDate(DateUtil.parseDateTime(DateUtil.format(yycNews.getAddData())));
					item.setAuthor("YuYingCai");
					item.setLink("http://www.yuyingcai.com.cn/yycNewsDetail.do?i=" + yycNews.getUuid());
					item.setTitle(yycNews.getNewsTitle());
					Description description = new Description();
					description.setType("text/html");
					description.setValue(yycNews.getDescription());
					item.setDescription(description);
					items.add(item);
				}
			}
		}
		channel.setItems(items);
		try {
			WireFeedOutput out = new WireFeedOutput();
			resp.getWriter().println(out.outputString(channel));
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.flushBuffer();
		return;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
