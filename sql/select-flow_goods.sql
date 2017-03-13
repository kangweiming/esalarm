/**
	优化第三版
*/
SELECT
	g1.*
FROM
	flow_goods g1
INNER JOIN (
	SELECT
		MIN(GOODS_PRICE) AS price,
		g.GOODS_NAME,
		g.GOODS_PROVINCE_ID
	FROM
		flow_goods g,
		resource_area r,
		flow_telecom_operator tp
	WHERE
		1 = 1
	AND (
		g.GOODS_AMOUNT = - 1
		OR g.GOODS_AMOUNT > 0
	)
	AND g.GOODS_STATE = '1'
	AND g.GOODS_TP_ID = tp.id
	AND TP_NAME = '移动'
	AND r.parentid = 0
	AND r.shortname = '辽宁'
	AND (
		g.GOODS_PROVINCE_ID = r.id
		OR g.GOODS_PROVINCE_ID = 0
	)
	GROUP BY
		g.GOODS_NAME,
		g.GOODS_PROVINCE_ID
) m ON m.price = g1.GOODS_PRICE
AND m.GOODS_NAME = g1.GOODS_NAME
AND m.GOODS_PROVINCE_ID = g1.GOODS_PROVINCE_ID
GROUP BY
	g1.GOODS_NAME,
	g1.GOODS_PROVINCE_ID
ORDER BY
	(g1.GOODS_NAME + 0)