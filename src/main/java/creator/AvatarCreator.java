package creator;

import entity.Avatar;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@MultipartConfig
public class AvatarCreator {

    private Avatar avatar;

    private static final String PART_AVATAR = "avatar";
    private static final String CONTENT_DISPOSITION = "content-disposition";
    private static final String FILENAME = "filename";

    public Avatar createAvatarByRequest(HttpServletRequest request) throws IOException, ServletException {
        Part part = request.getPart(PART_AVATAR);
        if (part.getSize() != 0){
            try (InputStream fileContent = part.getInputStream()) {
                initAvatar(part, fileContent);
            }
        }
        return avatar;
    }

    private void initAvatar(Part part, InputStream fileContent) throws IOException {
        String inputAvatarFileName = getSubmittedFileName(part);
        String fileExtension = FilenameUtils.getExtension(inputAvatarFileName);
        byte[] bytes = IOUtils.toByteArray(fileContent);

        avatar = new Avatar();
        avatar.setFileName(inputAvatarFileName);
        avatar.setExtension(fileExtension);
        avatar.setImage(bytes);
    }


    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader(CONTENT_DISPOSITION).split(";")) {
            if (cd.trim().startsWith(FILENAME)) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1)
                        .substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return "";
    }

}
