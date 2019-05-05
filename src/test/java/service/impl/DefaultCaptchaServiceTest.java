package service.impl;

import entity.Captcha;
import exception.SessionTimeOutException;
import handler.captcha.CaptchaHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import repository.Repository;
import repository.impl.CaptchaRepository;

import javax.naming.directory.NoSuchAttributeException;
import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCaptchaServiceTest {

    private static final int VALID_CAPTCHAS = 4;
    private static final String CAPTCHA_NUMBERS = "666";
    private Repository<Captcha> repository = new CaptchaRepository();

    @Mock
    private HttpServletRequest request;

    @Mock
    private Captcha captcha;

    @Mock
    private CaptchaHandler captchaHandler;

    @InjectMocks
    private DefaultCaptchaService captchaService;

    @Before
    public void setUp(){
        captchaService = new DefaultCaptchaService(repository);
    }


    @Test
    public void createCaptcha() throws NoSuchAttributeException {
        captchaService.create();
    }

    @Test
    public void removeOldCaptcha(){
        initRepository();
        captchaService.removeOldCaptcha();
        assertEquals(VALID_CAPTCHAS, repository.getAll().size());
    }

    private void initRepository() {
        for (int i = 0; i < 10; i++){
            repository.add(new Captcha.CaptchaBuilder().setNumbers("" + i)
                    .setExpirationTime(System.currentTimeMillis() - (100000 * i))
                    .build());
        }
    }

    @Test
    public void checkCaptchaOnValidAndReturnTrue_whenCaptchaIsValid() throws SessionTimeOutException {
        Mockito.when(captchaHandler.getCaptcha(request)).thenReturn(captcha);
        Mockito.when(captcha.getNumbers()).thenReturn(CAPTCHA_NUMBERS);
        Mockito.when(request.getParameter(anyString())).thenReturn(CAPTCHA_NUMBERS);
        assertTrue(captchaService.checkCaptchaOnValid(request, captchaHandler));
    }

    @Test
    public void checkCaptchaOnValidAndReurnFalse_whenCaptchaIsNotValid() throws SessionTimeOutException {
        Mockito.when(captchaHandler.getCaptcha(request)).thenThrow(SessionTimeOutException.class);
        assertFalse(captchaService.checkCaptchaOnValid(request, captchaHandler));
    }

}
