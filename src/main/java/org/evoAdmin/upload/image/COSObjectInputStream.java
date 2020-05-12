package org.evoAdmin.upload.image;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.methods.HttpRequestBase;


public class COSObjectInputStream extends InputStream {
    private InputStream in;
    private HttpRequestBase httpRequest;
    private boolean eof;

    public COSObjectInputStream(InputStream in, HttpRequestBase httpRequest) {
        super();
        this.in = in;
        this.httpRequest = httpRequest;
    }

    private void doAbort() {
        if (httpRequest != null) {
            httpRequest.abort();
        }
        closeStream(in);
    }

    @Override
    public int read() throws IOException {
        int value = in.read();
        if (value == -1) {
            eof = true;
        }
        return value;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int value = in.read(b, off, len);
        if (value == -1) {
            eof = true;
        }
        return value;
    }

    @Override
    public void reset() throws IOException {
        in.reset();
        eof = false;
    }

    private void closeStream(InputStream in) {
        try {
            in.close();
        } catch (IOException e) {
        }
    }


    @Override
    public void close() throws IOException {
        if (eof) {
            closeStream(in);
            httpRequest.releaseConnection();
        } else {
            doAbort();
        }
    }

    public void abort() {
        doAbort();
    }
}
