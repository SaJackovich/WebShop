package service.impl;

import container.ControllerConstant;
import creator.AvatarCreator;
import db.dao.AbstractDAO;
import db.dao.transaction.TransactionManager;
import entity.Avatar;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import service.AvatarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Optional;

public class DefaultAvatarService extends AvatarService{

    private static final String AVATAR_NOT_CREATE = "Avatar wasn't created";
    private static final String OLD_AVATAR_WAS_DELETED = "Old avatar was deleted";
    private static final String THIS_AVATAR_IS_FIRST_FOR_USER = "This avatar is first for user";
    private static final String AVATAR_FOLDER = "\\images\\avatar\\";
    private static final String FILENAME_FOR_SAVE_AVATAR = "src\\main\\webapp";

    private final Logger log = Logger.getLogger(this.getClass());

    public DefaultAvatarService(TransactionManager manager, AbstractDAO<Avatar> dao) {
        super(manager, dao);
    }

    @Override
    public Optional<Avatar> get(int id) {
        return transactionManager.doInTransaction(connection ->
                    avatarDAO.getById(connection, id));
    }

    @Override
    public Optional<Avatar> create(HttpServletRequest request) {
        AvatarCreator creator = new AvatarCreator();
        try {
            Avatar avatar = creator.createAvatarByRequest(request);
            if (Objects.nonNull(avatar)) {
                String avatarFullName = getFullAvatarName(request, avatar);
                avatar.setFullFileName(avatarFullName);
                String fullFileName = FILENAME_FOR_SAVE_AVATAR + avatarFullName;
                saveAvatar(fullFileName, avatar.getImage());
                return Optional.of(avatar);
            }
        } catch (IOException | ServletException e) {
            log.error(AVATAR_NOT_CREATE, e);
        }
        return Optional.empty();
    }

    @NotNull
    private String getFullAvatarName(@NotNull HttpServletRequest request, Avatar avatar) {
        return AVATAR_FOLDER + request.getParameter(ControllerConstant.USER_LOGIN) + "_" + avatar.getFileName();
    }

    private void saveAvatar(String fullFileName, byte[] image) throws IOException {
        InputStream imageStream = new ByteArrayInputStream(image);
        File avatarFileDestination = new File(fullFileName);
        deleteOldAvatar(avatarFileDestination);
        Files.copy(imageStream, avatarFileDestination.toPath());
    }

    private void deleteOldAvatar(File avatarFileDestination) {
        try {
            Files.delete(avatarFileDestination.toPath());
            log.info(OLD_AVATAR_WAS_DELETED);
        } catch (IOException e) {
            log.info(THIS_AVATAR_IS_FIRST_FOR_USER);
        }
    }

}
