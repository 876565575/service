package com.xc.cms.gridfs;

import cn.hutool.core.io.IoUtil;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import kotlin.text.Charsets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

/**
 * @author : 吴后荣
 * @date : 2019/10/31 01:49
 * @description :
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GridFSTest {

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    @Test
    public void Save() throws FileNotFoundException {
        File file = new File("D:\\javaProject\\xuecheng\\门户\\index_banner.html");
        FileInputStream inputStream = new FileInputStream(file);
        gridFsTemplate.store(inputStream, "测试文件");
    }

    @Test
    public void Save2() throws FileNotFoundException {
        File file = new File("D:\\javaProject\\xuecheng\\门户\\index_banner.html");
        String str = "xxxxx";
        InputStream inputStream = new ByteArrayInputStream(str.getBytes());
        gridFsTemplate.store(inputStream, "测试文件", str);
    }

    @Test
    public void Load() throws IOException {
        GridFSFile id = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is("5a795bbcdd573c04508f3a59")));
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(id.getObjectId());
        GridFsResource gridFsResource = new GridFsResource(id, downloadStream);
        InputStream inputStream = gridFsResource.getInputStream();
        String read = IoUtil.read(inputStream, Charsets.UTF_8);
        inputStream.close();
        System.out.println(read);
    }


}
