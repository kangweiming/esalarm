/**
 * 
 */
package com.all_union.es.esalarm.service.impl.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.all_union.es.esalarm.dao.flow.FlowGoodsDoMapper;
import com.all_union.es.esalarm.pojo.flow.FlowGoodsDo;
import com.all_union.es.esalarm.service.flow.FlowGoodsService;
import com.kwm.common.lang.StringUtil;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月5日 上午11:23:31 
 * @version V1.0 
 * 
*/
@Service("flowGoodsService")
public class FlowGoodsServiceImpl implements FlowGoodsService {
	
	private static Logger logger = LogManager.getLogger(FlowGoodsServiceImpl.class);
	
	@Resource
	private FlowGoodsDoMapper flowGoodsDao;

	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.flow.FlowService#listGoodsByTPNameAndProvinceName(java.util.Map)
	 */
	@Override
	public List<FlowGoodsDo> listGoodsByTPNameAndProvinceName(String tpName, String provinceShortName) {
		logger.debug("excute...");
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		if(StringUtil.isBlank(tpName) || StringUtil.isBlank(provinceShortName)){
			logger.debug("invalid parameter ... ");
			return new ArrayList<FlowGoodsDo>();
		}
		
		map.put("tpName", tpName);
		map.put("provinceShortName", provinceShortName);
		
		return this.flowGoodsDao.listGoodsByTPNameAndProvinceName(map);

	}

	@Override
	public FlowGoodsDo selectByPrimaryKey(Long id) {
		logger.debug("excute...");
		return this.flowGoodsDao.selectByPrimaryKey(id);
	}

}
