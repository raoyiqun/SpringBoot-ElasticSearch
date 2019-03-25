package cn.thislx.springbootes;

import cn.thislx.springbootes.entity.req.SearchReq;
import cn.thislx.springbootes.service.serviceImpl.SearchServiceImpl;
import cn.thislx.springbootes.utils.ElasticsearchUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LX
 * @Description:
 * @Date: Created in 11:23 2018/11/6
 * @Modified by:
 */
@RestController
@RequestMapping("/es")
public class EsController {

    @Autowired
    private SearchServiceImpl searchService;

    /**
     * http://127.0.0.1:8080/es/createIndex
     * 创建索引
     *
     * @param request
     * @param response
     * @return
     */
    /**
     * 查询分页
     *
     * @param startPage 第几条记录开始
     *                  从0开始
     *                  第1页 ：http://127.0.0.1:8080/es/queryPage?startPage=0&pageSize=2
     *                  第2页 ：http://127.0.0.1:8080/es/queryPage?startPage=2&pageSize=2
     * @param pageSize  每页大小
     * @return
     */
    @RequestMapping("/queryPage")
    public String queryPage(SearchReq req) {
        String filterFigures = searchService.filterFigure(req);
        if (StringUtils.isNotBlank(filterFigures)){
            return filterFigures;
        }
        return null;
    }
}