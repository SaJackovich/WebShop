package service.impl;

import container.CaptchaParameterContainer;
import container.ControllerConstant;
import creator.CaptchaCreator;
import entity.Captcha;
import exception.SessionTimeOutException;
import handler.captcha.CaptchaHandler;
import org.apache.log4j.Logger;
import repository.Repository;
import service.CaptchaService;

import javax.imageio.ImageIO;
import javax.naming.directory.NoSuchAttributeException;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DefaultCaptchaService implements CaptchaService{

    private Repository<Captcha> repository;
    private Logger log = Logger.getLogger(this.getClass());

    public DefaultCaptchaService(Repository<Captcha> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Captcha> get(int key) {
        return Optional.ofNullable(repository.get(key));
    }

    @Override
    public Captcha create() throws NoSuchAttributeException {
        CaptchaCreator creator = new CaptchaCreator().setHeight(40)
                .setWidth(220)
                .setSymbolCount(9);
        BufferedImage image = creator.createImage();
        createImageFile(image);
        return createCaptcha(creator.getCaptchaNumbers());
    }

    @Override
    public void removeOldCaptcha() {
        repository.getAll()
                .values()
                .stream()
                .filter(x -> !x.isValid())
                .forEach(x -> repository.remove(x.getId()));
    }

    @Override
    public boolean checkCaptchaOnValid(HttpServletRequest request, CaptchaHandler handler) {
        try {
            Captcha captcha = handler.getCaptcha(request);
            return captcha.getNumbers().equals(request.getParameter(ControllerConstant.CAPTCHA));
        } catch (SessionTimeOutException e) {
            log.error(ControllerConstant.CAPTCHA_INVALID_BY_TIME);
            return false;
        }
    }

    private void createImageFile(BufferedImage image){
        try {
            File file = new File(CaptchaParameterContainer.CAPTCHA_FILE_PATH);
            ImageIO.write(image, "jpeg", file);
        } catch (IOException e) {
            log.error(ControllerConstant.CAPTCHA_FILE_NOT_CREATED, e);
        }
    }

    private Captcha createCaptcha(String captchaNumbers) {
        return new Captcha.CaptchaBuilder().setExpirationTime(new Date().getTime())
                .setNumbers(captchaNumbers).build();
    }

}
