package com.all_union.es.esalarm.service.wxcms;

import java.util.List;

import com.all_union.es.esalarm.pojo.wxcms.MsgBase;


public interface MsgBaseService {

	public MsgBase getById(String id);

	public List<MsgBase> listForPage(MsgBase searchEntity);

	public void add(MsgBase entity);

	public void update(MsgBase entity);

	public void delete(MsgBase entity);



}