package ir.dotin.core.servlet;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.FlatColorBackgroundProducer;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.servlet.CaptchaServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

import static nl.captcha.Captcha.NAME;


@WebServlet(urlPatterns = "/captcha.png")
public class CmsCaptchaServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Captcha captcha = new Captcha.Builder(250, 50)
                    .addText()
                    .addBackground(new GradiatedBackgroundProducer(Color.white, Color.ORANGE))
                    .addNoise()
                    .build();

            CaptchaServletUtil.writeImage(resp, captcha.getImage());
            req.getSession().setAttribute(NAME, captcha);
        } catch (IllegalStateException e) {
//            do nothing!
        }
    }

}
