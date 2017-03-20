package com.teradata.jsonreader;


import org.junit.Test;

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
