package com.xc.cmsclient.service;

import cn.hutool.core.io.IoUtil;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xc.cmsclient.dao.CmsPageRepository;
import com.xc.cmsclient.dao.CmsSiteRepository;
import com.xc.model.cms.CmsPage;
import com.xc.model.cms.CmsSite;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Optional;

/**
 * @author : 吴后荣
 * @date : 2019/12/4 23:06
 * @description :
 */
@Log4j2
@Service
public class PageService {

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Autowired
    CmsSiteRepository cmsSiteRepository;

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    public void publishPage(String pageId) {
        //获取页面信息
        Optional<CmsPage> cmsPageOptional =  cmsPageRepository.findById(pageId);
        if (!cmsPageOptional.isPresent()) {
            log.error("cmsPage is null，pageId: {}", pageId);
            return;
        }
        CmsPage cmsPage = cmsPageOptional.get();
        //获取文件
        InputStream inputStream = this.getFile(cmsPage.getHtmlFileId());
        if (inputStream == null) {
            log.error("htmlFile inputStream is null, htmlFileId :{}", cmsPage.getHtmlFileId());
            return;
        }

        Optional<CmsSite> optionalCmsSite = cmsSiteRepository.findById(cmsPage.getSiteId());
        if (!optionalCmsSite.isPresent()) {
            log.error("cmsSite is null，siteId: {}", cmsPage.getSiteId());
            return;
        }
        CmsSite cmsSite = optionalCmsSite.get();
        //拼接页面物理路径
        String targetPath = cmsSite.getSitePhysicalPath() + cmsPage.getPageWebPath() + cmsPage.getPageName();
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(targetPath));
            //拷贝流，保存文件
            IoUtil.copy(inputStream, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public InputStream getFile(String fileId) {
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, downloadStream);
        try {
            return gridFsResource.getInputStream();
        } catch (IOException e) {
            return null;
        }
    }



}
