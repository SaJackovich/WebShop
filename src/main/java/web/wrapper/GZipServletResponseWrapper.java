package web.wrapper;

import org.apache.log4j.Logger;
import util.GZipServletOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class GZipServletResponseWrapper extends HttpServletResponseWrapper {

    private static final String STREAM_WASN_T_FLUSHED = "GZIPOutputStream wasn't flushed";
    private static final String PRINT_WRITER_OBTAINED= "PrintWriter obtained already - cannot get OutputStream";
    private static final String OUTPUT_STREAM_OBTAINED = "OutputStream obtained already - cannot get PrintWriter";

    private GZipServletOutputStream gzipOutputStream;
    private PrintWriter printWriter;

    private final Logger log = Logger.getLogger(this.getClass());

    public GZipServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public void close() throws IOException {
        if (printWriter != null) {
            printWriter.close();
        }
        if (gzipOutputStream != null) {
            gzipOutputStream.close();
        }
    }

    @Override
    public void flushBuffer(){
        if (this.printWriter != null) {
            this.printWriter.flush();
        }
        try {
            flushOutputStream();
            super.flushBuffer();
        } catch (IOException e) {
            log.error(STREAM_WASN_T_FLUSHED, e);
        }
    }

    private void flushOutputStream() throws IOException {
        if (this.gzipOutputStream != null) {
            this.gzipOutputStream.flush();
        }
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (this.printWriter != null) {
            throw new IllegalStateException(PRINT_WRITER_OBTAINED);
        }
        if (this.gzipOutputStream == null) {
            this.gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
        }
        return this.gzipOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (this.printWriter == null && this.gzipOutputStream != null) {
            throw new IllegalStateException(OUTPUT_STREAM_OBTAINED);
        }
        if (this.printWriter == null) {
            this.gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
            this.printWriter = new PrintWriter(
                    new OutputStreamWriter(this.gzipOutputStream, getResponse().getCharacterEncoding()));
        }
        return this.printWriter;
    }

    @Override
    public void setContentLength(int len) {
    }
}
