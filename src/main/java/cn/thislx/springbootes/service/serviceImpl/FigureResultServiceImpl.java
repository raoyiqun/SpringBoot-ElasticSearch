package cn.thislx.springbootes.service.serviceImpl;

import cn.thislx.springbootes.entity.dto.FigureDTO;
import cn.thislx.springbootes.entity.dto.FigureResult;
import cn.thislx.springbootes.service.IFigureResultService;
import cn.thislx.springbootes.util.base.Convert;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FigureResultServiceImpl implements IFigureResultService{
    @Override
    public FigureResult toFigureResult(SearchResponse searchResponse) {
        SearchHits hits = searchResponse.getHits();
        FigureResult figureResult = new FigureResult();
        List<FigureDTO> listFigure = new ArrayList<FigureDTO>();
        for (SearchHit hit : hits){
            FigureDTO figureDTO = new FigureDTO();
            Map<String, Object> source = hit.getSource();
            String title = Convert.toString(source, "title");
            String birthDate = Convert.toString(source, "birthDate");
            String birthPlace = Convert.toString(source, "birthPlace");
            figureDTO.setId(Integer.valueOf(source.get("id").toString()));
            figureDTO.setTitle(title);
            figureDTO.setBirthDate(birthDate);
            figureDTO.setBirthPlace(birthPlace);
            listFigure.add(figureDTO);
        }
        figureResult.setListFigure(listFigure);
        return figureResult;
    }
}
