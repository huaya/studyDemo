package com.maxlong.study.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2021/5/11.
 *
 * @author xxxx
 * @Email xxxx
 */
public class RestEsClient {

    public static void main(String[] args) throws IOException {
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("172.21.1.45", 9200, "http"),
                new HttpHost("172.21.1.46", 9200, "http"),
                new HttpHost("172.21.1.47", 9200, "http"));
        RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);

        IndicesClient indicesClient = client.indices();

        GetMappingsRequest request = new GetMappingsRequest();
        GetMappingsResponse response = indicesClient.getMapping(request, RequestOptions.DEFAULT);
        ImmutableOpenMap<String, ImmutableOpenMap<String, MappingMetaData>> allMappings = response.getMappings();
        ImmutableOpenMap<String, MappingMetaData> indexMappings = allMappings.get("goods-beta-2");

        MappingMetaData data = indexMappings.get("goods");

        Map<String, Object> mappings = new HashMap<>();
        mappings.put("goods", data.getSourceAsMap());
        System.out.println(mappings);
        client.close();
    }
}
