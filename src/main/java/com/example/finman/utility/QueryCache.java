package com.example.finman.utility;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple cache that loads classpath resources as strings.
 * @source https://github.com/ArchitecturalKnowledgeAnalysis/EmailIndexer/blob/main/src/main/java/nl/andrewl/email_indexer/data/QueryCache.java
 * Code obtained after permission from author @andrewlalis
 */
public class QueryCache {
    private static final Map<String, String> CACHE = new ConcurrentHashMap<>();

    public static String load(String resourceName) {
        String sql = CACHE.get(resourceName);
        if (sql == null) {
            InputStream is = QueryCache.class.getResourceAsStream(resourceName);
            if (is == null) throw new RuntimeException("Could not load " + resourceName);
            try {
                sql = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            CACHE.put(resourceName, sql);
        }
        return sql;
    }
}