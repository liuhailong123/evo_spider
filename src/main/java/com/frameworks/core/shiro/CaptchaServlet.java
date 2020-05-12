package com.frameworks.core.shiro;

import com.frameworks.utils.Exceptions;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.ConfigurationFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

//import org.patchca.service.ConfigurableCaptchaService;
//import org.patchca.utils.encoder.EncoderHelper;
//import org.patchca.word.RandomWordFactory;


/**
 * 验证码
 *
 * @author cao.yong
 */
public class CaptchaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(CaptchaServlet.class);

    private static int WIDTH = 110;
    private static int HEIGHT = 50;
    private static int MAX_LENGTH = 4;
    private static int MIN_LENGTH = 4;

    private static String CACHE_NAME = "_captcha_";
    private static String CACHE_FILE = "ehcache/ehcache-captcha.xml";

    private static Cache cache;
    private static ConfigurableCaptchaService cs;

    /* (non-Javadoc)

     * @see javax.servlet.GenericServlet#init()

     */
    @Override
    public void init() throws ServletException {
        cs = new ConfigurableCaptchaService();

        int width = NumberUtils.toInt(this.getInitParameter("width"), WIDTH);
        int height = NumberUtils.toInt(this.getInitParameter("height"), HEIGHT);
        cs.setWidth(width);
        cs.setHeight(height);

        int maxLength = NumberUtils.toInt(this.getInitParameter("maxLength"), MAX_LENGTH);
        int minLength = NumberUtils.toInt(this.getInitParameter("minLength"), MIN_LENGTH);
        RandomWordFactory wf = new RandomWordFactory();
        wf.setMaxLength(maxLength);
        wf.setMinLength(minLength);

        cs.setWordFactory(wf);

        ResourcePatternResolver rpr = new PathMatchingResourcePatternResolver();
        Resource resource = rpr.getResource("classpath:" + CACHE_FILE);

        try {
            CacheManager cacheManager = CacheManager.create(resource.getInputStream());

            cache = cacheManager.getCache(CACHE_NAME);
            if (cache == null) {
                Configuration configuration = ConfigurationFactory.parseConfiguration(resource.getInputStream());

                cache = new Cache(configuration.getCacheConfigurations().get(CACHE_NAME));
                if (cache == null) {
                    cache = cacheManager.getCache(Cache.DEFAULT_CACHE_NAME);
                } else {
                    cacheManager.addCache(cache);
                }
            }
        } catch (CacheException e) {
            LOGGER.error("创建缓存[" + CACHE_NAME + "]出错，请检查配置参数:" + Exceptions.getStackTraceAsString(e));
        } catch (IOException e) {
            LOGGER.error("缓存配置文件[" + CACHE_FILE + "]读取出错:" + Exceptions.getStackTraceAsString(e));
        }
    }

    /* (non-Javadoc)

     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)

     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int width = Integer.valueOf(request.getParameter("w"));
        int height = Integer.valueOf(request.getParameter("h"));
        int size = Integer.valueOf(request.getParameter("size"));

        String sessionId = request.getSession().getId();
        // 清除缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);

        // 显示类型
        response.setContentType("image/png");

        OutputStream os = response.getOutputStream();
        cs.setWidth(width);
        cs.setHeight(height);
        RandomFontFactory fontFactory = new RandomFontFactory();
        fontFactory.setMaxSize(size);
        fontFactory.setMinSize(size);
        cs.setFontFactory(fontFactory);

        String patchca = EncoderHelper.getChallangeAndWriteImage(cs, "png", os);

        // 放入缓存
        Element element = new Element(sessionId, patchca);
        cache.put(element);

        os.flush();
        os.close();
    }

    /**
     * 检查验证码是否正确
     *
     * @param sessionId
     * @param code
     * @return
     */
    public static boolean validate(String sessionId, String code) {
        Element element = cache.get(sessionId);

        if (element != null) {
            String sourceCode = (String) element.getObjectValue();

            cache.remove(sessionId);
            return StringUtils.equalsIgnoreCase(code, sourceCode);
        }

        return false;
    }

    /* (non-Javadoc)

     * @see javax.servlet.GenericServlet#destroy()

     */
    @Override
    public void destroy() {
        cs = null;
        CacheManager.getInstance().removeCache(CACHE_NAME);
    }

}
