package oop.backend.crawler.top;

import oop.backend.App;
import oop.backend.config.PathFile;
import oop.backend.properties.PropertyGetter;
import oop.backend.properties.eplatform.OpenSeaProperty;
import oop.backend.crawler.abstractcrawler.GetOpenSea;
import oop.backend.dtos.eplatform.OpenSeaDTO;
import oop.backend.utils.json.JsonUtil;
import oop.backend.utils.fix.PathFixUtil;
import oop.backend.utils.scroll.top.OpenSeaTopUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Component
@RestController
@RequestMapping("${api.v1}/top")
public class OpenSeaTopCrawler extends GetOpenSea {
    private final String PATH_OPEN_SEA = PathFixUtil.fix(App.class.getResource(PathFile.PATH_OPEN_SEA_TOP).getPath());
    private final PropertyGetter<OpenSeaDTO> openSeaAttr = new OpenSeaProperty();

    public OpenSeaTopCrawler() {
        selectionToRequest.put("Day", "?sortBy=one_day_volume");
        selectionToRequest.put("Week", "?sortBy=seven_day_volume");
        selectionToRequest.put("Month", "?sortBy=thirty_day_volume");
        selectionToRequest.put("AllTime", "?sortBy=total_volume");
    }

    @Override
    public List<OpenSeaDTO> getData(String selection) throws Exception {
        String request = selectionToRequest.get(selection);
        List<OpenSeaDTO> openSeas = new ArrayList<>();
        Document document = OpenSeaTopUtil.scrollAndGet(request);
        Elements elements = document.select("div.sc-e7b51c31-0");
        for (Element element : elements) {
            OpenSeaDTO openSea = openSeaAttr.attrGet(element);
            if(openSea != null && !openSeas.contains(openSea))openSeas.add(openSea);
        }
        return openSeas;
    }

    private final JsonUtil<OpenSeaDTO> jsonHandler = new JsonUtil<>(PATH_OPEN_SEA);

    @GetMapping("/opensea/{selection}")
    public ResponseEntity<?> getDataFromOpenSea(@PathVariable("selection") String selection) {
        try {
            return jsonHandler.handleJsonOperation(() -> getData(selection));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid selection");
        }
    }
}

