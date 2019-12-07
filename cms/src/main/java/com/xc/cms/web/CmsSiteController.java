package com.xc.cms.web;

import com.xc.common.model.entity.CmsSite;
import com.xc.cms.model.vo.QueryResult;
import com.xc.cms.service.CmsSiteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <Description> <br>
 *
 * @author Wu<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/09/24 0:25 <br>
 * @see com.xc.cms.web <br>
 */
@RestController
@RequestMapping("/cms/site")
public class CmsSiteController {
    private final CmsSiteService cmsSiteService;

    public CmsSiteController(CmsSiteService cmsSiteService) {
        this.cmsSiteService = cmsSiteService;
    }


    @GetMapping("/all")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(cmsSiteService.findAll());
    }

    @GetMapping("/list")
    public ResponseEntity list(Integer pageNum, Integer pageSize){
        QueryResult queryResult = cmsSiteService.list(pageNum, pageSize);
        return ResponseEntity.ok(queryResult);
    }

}
