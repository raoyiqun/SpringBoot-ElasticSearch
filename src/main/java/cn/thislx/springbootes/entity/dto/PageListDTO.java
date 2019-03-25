package cn.thislx.springbootes.entity.dto;

import java.io.Serializable;

/**分页返回类，所有的分页查询都用此类
 * @author binzhao
 * @date 2017年5月16日
 */
public class PageListDTO implements Serializable {
	private Integer currentPage;
	private Integer pageSize;
	private Long totalPages;
	private Long totalDatas;
	private Serializable data;
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}
	public Long getTotalDatas() {
		return totalDatas;
	}
	public void setTotalDatas(Long totalDatas) {
		this.totalDatas = totalDatas;
	}
	public Serializable getData() {
		return data;
	}
	public void setData(Serializable data) {
		this.data = data;
	}
}
