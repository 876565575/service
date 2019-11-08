package com.xc.cms.web;

import com.xc.cms.model.entity.CmsSite;
import com.xc.cms.model.vo.PageQueryResult;
import com.xc.cms.service.CmsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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


    @GetMapping("/list")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(cmsSiteService.findAll());
    }

}
