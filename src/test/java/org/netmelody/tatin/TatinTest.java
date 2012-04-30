package org.netmelody.tatin;

import java.io.IOException;

import org.junit.Test;
import org.netmelody.tatin.TrivialHttpClient.TrivialResponse;
import org.netmelody.tatin.server.TatinServer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class TatinTest {

    private final TatinServer tatin = startTatin();
    private final String address = "http://localhost:" + tatin.port();

    @Test public void
    serves_an_empty_string_for_an_unpopulated_url() throws Exception {
        final TrivialResponse response = TrivialHttpClient.getFrom(address + "/some/place/or/other");
        assertThat(response.code, is(200));
        assertThat(response.content, is(""));
    }

    @Test public void
    serves_content_previously_put() throws Exception {
        final TrivialResponse putResponse = TrivialHttpClient.putTo(address + "/this/and/that", "orange");
        assertThat(putResponse.code, is(200));
        assertThat(putResponse.content, is(""));
        
        final TrivialResponse getResponse = TrivialHttpClient.getFrom(address + "/this/and/that");
        assertThat(getResponse.code, is(200));
        assertThat(getResponse.content, is("orange"));
    }

    private static TatinServer startTatin() {
        try {
            return new TatinServer(0);
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}