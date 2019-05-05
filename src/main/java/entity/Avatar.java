package entity;

import util.IdGenerator;

import java.util.Arrays;
import java.util.Objects;

public class Avatar {

    private int id;
    private String fileName;
    private String fullFileName;
    private String extension;
    private byte[] image;

    public Avatar() {
        id = IdGenerator.getAvatarId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullFileName() {
        return fullFileName;
    }

    public void setFullFileName(String fullFileName) {
        this.fullFileName = fullFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false;}
        Avatar avatar = (Avatar) o;
        return Objects.equals(fileName, avatar.fileName) &&
                Objects.equals(fullFileName, avatar.fullFileName) &&
                Objects.equals(extension, avatar.extension) &&
                Arrays.equals(image, avatar.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(fileName, fullFileName, extension);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fullFileName='" + fullFileName + '\'' +
                ", extension='" + extension + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
