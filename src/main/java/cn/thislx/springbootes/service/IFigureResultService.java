package cn.thislx.springbootes.service;

import cn.thislx.springbootes.entity.dto.FigureResult;
import org.elasticsearch.action.search.SearchResponse;

public interface IFigureResultService {
    public FigureResult toFigureResult(SearchResponse searchResponse);
}
