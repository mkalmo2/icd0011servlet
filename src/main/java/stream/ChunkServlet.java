package stream;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet("/chunks")
public class ChunkServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        sleep();

        response.getWriter().println("Chunk 1");
        response.flushBuffer();
        sleep();

        response.getWriter().println("Chunk 2");
        response.flushBuffer();
        sleep();

        response.getWriter().println("Chunk 3");
    }

    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
