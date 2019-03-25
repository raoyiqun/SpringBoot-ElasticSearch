package cn.thislx.springbootes.service.serviceImpl;

import cn.thislx.springbootes.entity.dto.FigureResult;
import cn.thislx.springbootes.entity.dto.PageListDTO;
import cn.thislx.springbootes.entity.req.SearchReq;
import cn.thislx.springbootes.service.ISearchService;
import cn.thislx.springbootes.utils.ElasticsearchUtil;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements ISearchService {

    /**
     * 测试索引
     */
    private String indexName = "ott-cms-qa";

    /**
     * 类型
     */
    private String esType = "figure";

    @Autowired
    private FigureResultServiceImpl figureResultService;

    @Override
    public String filterFigure(SearchReq req) {
        PageListDTO pageListDTO = new PageListDTO();
        Integer page = req.getStartPage();
        Integer size = req.getPageSize();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("height").from("0").to("0"));
        SearchRequestBuilder sq = ElasticsearchUtil.client.prepareSearch(indexName);
        SearchRequestBuilder searchRequestBuilder = sq.setQuery(boolQuery);
        SearchResponse searchResponse = searchRequestBuilder
                .setFrom((page - 1) * size)// 分页起始位置（跳过开始的n个）
                .setSize(size)// 本次返回的文档数量
                .execute()
                .actionGet();
        FigureResult figureResult = figureResultService.toFigureResult(searchResponse);
        pageListDTO.setData(figureResult);
        return JSONObject.toJSONString(pageListDTO);
    }
}