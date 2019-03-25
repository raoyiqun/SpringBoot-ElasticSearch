package cn.thislx.springbootes.entity.dto;

import java.io.Serializable;

/**人物
 * @author binzhao
 * @date 2017年5月17日
 */
public class FigureDTO implements Serializable {
	private static final long serialVersionUID = 1346295698487803L;
	private Integer id;
	private String title;
	private String coverPic;
	private String gender;
	private String prof;
	private String birthDate;
	private String birthPlace;
	private String status;
	private String highlightTitle;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCoverPic() {
		return coverPic;
	}
	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProf() {
		return prof;
	}
	public void setProf(String prof) {
		this.prof = prof;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHighlightTitle() {
		return highlightTitle;
	}
	public void setHighlightTitle(String highlightTitle) {
		this.highlightTitle = highlightTitle;
	}
}
