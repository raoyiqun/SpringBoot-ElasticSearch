package cn.thislx.springbootes.entity.dto;

import java.io.Serializable;
import java.util.List;

public class FigureResult implements Serializable {
	private static final long serialVersionUID = -1867026082803577460L;
	private List<FigureDTO> listFigure;					//视频
    
	public List<FigureDTO> getListFigure() {
		return listFigure;
	}
	public void setListFigure(List<FigureDTO> listFigure) {
		this.listFigure = listFigure;
	}
}
