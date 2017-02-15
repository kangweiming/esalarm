/**
 * 
 */
package com.all_union.es.esalarm.dao;

import java.io.Serializable;

import org.springframework.util.StringUtils;

/** 
 * @Description: 所有查询的基类，分页算法封装
 * 分页须设置: TotalItem（总条数）,缺省为0, 应该在dao中被设置 PageSize（每页条数），应在web层被设置 QueryBase 缺省为20，子类可以通过覆盖 getDefaultPageSize()
 * 修改 CurrentPage（当前页）,缺省为1，首页， 应在web层被设置 分页后，可以得到：TotalPage（总页数） FristItem(当前页开始记录位置，从1开始记数) 
 * PageLastItem(当前页最后记录位置) 页面上，每页显示条数名字应为： lines ，当前页名字应为： page
 * 
 * oracle数据库使用rownum，mysql使用limit分页
 * 针对oracle，pageFristItem从1开始
 * 针对mysql，pageFristItem从0开始
 * @author kwm
 * @date 2017年2月8日 上午10:18:09 
 * @version V1.0 
 * 
*/
public class Query implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5584556636352946563L;

	private static final Integer defaultPageSize = new Integer(20);

	private static final Integer defaultFriatPage = new Integer(1);

	private static final Integer defaultTotleItem = new Integer(0);


	/**
	 * 总记录数
	 */
	private Integer totalItem;

	/**
	 * 每页记录数
	 */
	private Integer pageSize;

	/**
	 * 当前页
	 */
	private Integer currentPage;

	// for paging
	private int startRow;

	private int endRow;

	/**
	 * @return Returns the defaultPageSize.
	 */
	protected final Integer getDefaultPageSize() {
		return defaultPageSize;
	}

	public boolean isFirstPage() {
		return this.getCurrentPage().intValue() == 1;
	}

	public int getPreviousPage() {
		int back = this.getCurrentPage().intValue() - 1;

		if (back <= 0) {
			back = 1;
		}

		return back;
	}

	public boolean isLastPage() {
		return this.getTotalPage() == this.getCurrentPage().intValue();
	}

	public int getNextPage() {
		int back = this.getCurrentPage().intValue() + 1;

		if (back > this.getTotalPage()) {
			back = this.getTotalPage();
		}

		return back;
	}

	/**
	 * @return Returns the currentPage.
	 */
	public Integer getCurrentPage() {
		if (currentPage == null) {
			return defaultFriatPage;
		}

		return currentPage;
	}

	/**
	 * @param currentPage The currentPage to set.
	 */
	public void setCurrentPage(Integer cPage) {
		if ((cPage == null) || (cPage.intValue() <= 0)) {
			this.currentPage = defaultFriatPage;
		} else {
			this.currentPage = cPage;
		}
	}

	public void setCurrentPageString(String s) {
		if (StringUtils.isEmpty(s)) {
			return;
		}
		try {
			setCurrentPage(Integer.parseInt(s));
		} catch (NumberFormatException ignore) {
			this.setCurrentPage(defaultFriatPage);
		}
	}

	/**
	 * @return Returns the pageSize.
	 */
	public Integer getPageSize() {
		if (pageSize == null) {
			return getDefaultPageSize();
		}

		return pageSize;
	}

	public boolean hasSetPageSize() {
		return pageSize != null;
	}

	/**
	 * @param pageSize The pageSize to set.
	 */
	public void setPageSize(Integer pSize) {
		if (pSize == null) {
			throw new IllegalArgumentException("PageSize can't be null.");
		}

		if (pSize.intValue() <= 0) {
			throw new IllegalArgumentException("PageSize must great than zero.");
		}

		this.pageSize = pSize;
	}

	public void setPageSizeString(String pageSizeString) {
		if (StringUtils.isEmpty(pageSizeString)) {			
			return;
		}

		try {
			Integer integer = new Integer(pageSizeString);
			this.setPageSize(integer);
		} catch (NumberFormatException ignore) {
		}
	}

	/**
	 * @return Returns the totalItem.
	 */
	public Integer getTotalItem() {
		if (totalItem == null) {
			// throw new IllegalStateException("Please set the TotalItem
			// frist.");
			return defaultTotleItem;
		}

		return totalItem;
	}

	/**
	 * @param totalItem The totalItem to set.
	 */
	public void setTotalItem(Integer tItem) {
		if (tItem == null) {
			// throw new IllegalArgumentException("TotalItem can't be null.");
			tItem = new Integer(0);
		}
		this.totalItem = tItem;
		int current = this.getCurrentPage().intValue();
		int lastPage = this.getTotalPage();

		if (current > lastPage) {
			this.setCurrentPage(new Integer(lastPage));
		}
	}

	public int getTotalPage() {
		int pgSize = this.getPageSize().intValue();
		int total = this.getTotalItem().intValue();
		int result = total / pgSize;

		if ((total % pgSize) != 0) {
			result++;
		}

		return result;
	}

	/**
	 * pageFristItem从1开始
	 * @return
	 */
	public int getPageFristItem() {
		int cPage = this.getCurrentPage().intValue();

		if (cPage == 1) {
			return 1; 
		}

		cPage--;

		int pgSize = this.getPageSize().intValue();

		return (pgSize * cPage) + 1;
	}
	
	/**
	 * pageFristItem从0开始
	 * @return
	 */
	public int getPageFristItemZero(){
		return this.getPageFristItem() - 1;
	}

	
	public int getPageLastItem() {
		int cPage = this.getCurrentPage().intValue();
		int pgSize = this.getPageSize().intValue();
		int assumeLast = pgSize * cPage;
		int totalItem = getTotalItem().intValue();

		if (assumeLast > totalItem) {
			return totalItem;
		} else {
			return assumeLast;
		}
	}

	/**
	 * @return Returns the endRow.
	 */
	public int getEndRow() {
		return endRow;
	}

	/**
	 * @param endRow The endRow to set.
	 */
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	/**
	 * @return Returns the startRow.
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * @param startRow The startRow to set.
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	protected String getSQLBlurValue(String value) {
		if (value == null) {
			return null;
		}

		return value + '%';
	}

	public boolean hasSearchCondition() {
		return true;
	}
}
