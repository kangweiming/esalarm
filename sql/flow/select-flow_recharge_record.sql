SELECT
	o.*, g.ID as G_ID,g.GOODS_NAME as G_NAME,g.GOODS_PROVINCE_ID as G_PID
FROM
	flow_order o,
	flow_goods g,
	flow_order_goods og,
	flow_user u
WHERE
	1 = 1
AND u.ID = o.USER_ID
AND og.ORDER_NO = o.ORDER_NO
AND og.GOODS_ID = g.ID
AND o.STATE IN ('SUCCESS', 'NOTPAY')
AND u.OPEN_ID = '1234567890AABBCCDD1234567890'
