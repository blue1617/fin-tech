package io.fourfinanceit.homework;

import com.sun.javafx.collections.MappingChange;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apreda on 30.06.2016.
 */
class VideoStreamingServlet extends HttpServlet {
    /**
     * A cache.
     */
    public Map<String, String> cache = new HashMap<>();
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String file = request.getParameter("file");
        if(file == "") {
            return;
        }
        InputStream is = new FileInputStream("/home/videos/large/" + file);
        int r;
        String contents = "";
        byte[] buffer = new byte[100];
        while ((r = is.read(buffer)) > 0) {
            contents += new String(buffer);
        }
        cache.put(file, contents);
        response.getWriter().println(contents);
    }
}
