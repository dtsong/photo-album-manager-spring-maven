package com.teradata.jsonreader;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = APIObjectMapper.class)
@WebAppConfiguration
public class APIObjectMapperTest {

    @Test
    public void testReadAlbumJsonWithObjectMapper() throws Exception {
        APIObjectMapper obj = new APIObjectMapper();
        obj.readAlbumJsonWithObjectMapper();
    }

    @Test
    public void testReadPhotoJsonWithObjectMapper() throws Exception {
        APIObjectMapper obj = new APIObjectMapper();
        obj.readPhotoJsonWithObjectMapper();
    }
}
