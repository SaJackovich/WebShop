package util;

public class Routing {

    public String getPathFromURL(String path) {
        return path.substring(path.lastIndexOf('/') + 1);
    }

}