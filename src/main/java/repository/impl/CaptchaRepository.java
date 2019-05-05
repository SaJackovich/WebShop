package repository.impl;

import entity.Captcha;
import repository.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

public class CaptchaRepository implements Repository<Captcha> {

    private Map<Integer, Captcha> captcha;

    public CaptchaRepository() {
        this.captcha = new LinkedHashMap<>();
    }

    @Override
    public Captcha add(Captcha captcha){
        return this.captcha.put(captcha.getId(), captcha);
    }

    @Override
    public boolean contains(int captchaId){
        return captcha.containsKey(captchaId);
    }

    @Override
    public Captcha get(int captchaId){
        return captcha.get(captchaId);
    }

    @Override
    public boolean remove(int captchaId){
        captcha.remove(captchaId);
        return false;
    }

    @Override
    public Map<Integer, Captcha> getAll() {
        return captcha;
    }

}
