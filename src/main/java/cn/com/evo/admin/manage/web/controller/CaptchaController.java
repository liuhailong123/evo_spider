package cn.com.evo.admin.manage.web.controller;import com.alibaba.fastjson.JSONObject;import com.frameworks.core.web.controller.BaseController;import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;import com.github.bingoohuang.patchca.font.RandomFontFactory;import com.github.bingoohuang.patchca.service.Captcha;import com.github.bingoohuang.patchca.word.RandomWordFactory;import net.sf.ehcache.Cache;import net.sf.ehcache.CacheException;import net.sf.ehcache.CacheManager;import net.sf.ehcache.Element;import net.sf.ehcache.config.Configuration;import net.sf.ehcache.config.ConfigurationFactory;import org.apache.commons.lang3.StringUtils;import org.springframework.core.io.Resource;import org.springframework.core.io.support.PathMatchingResourcePatternResolver;import org.springframework.core.io.support.ResourcePatternResolver;import org.springframework.http.MediaType;import org.springframework.stereotype.Controller;import org.springframework.util.Base64Utils;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.ResponseBody;import javax.imageio.ImageIO;import javax.servlet.http.HttpServletRequest;import java.io.*;/** * @Description: * @author: lu.xin * @create: 2019-01-18 10:11 AM **/@Controller@RequestMapping("/captcha")public class CaptchaController extends BaseController {    private static int WIDTH = 110;    private static int HEIGHT = 50;    private static int MAX_LENGTH = 4;    private static int MIN_LENGTH = 4;    private static String CACHE_NAME = "_captcha_";    private static String CACHE_FILE = "ehcache/ehcache-captcha.xml";    private static ConfigurableCaptchaService cs;    private static Cache cache;    @RequestMapping(value = "", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})    @ResponseBody    public JSONObject getCaptcha(HttpServletRequest request) throws IOException {        init();        int width = 110;        int height = 60;        int size = 35;        String sessionId = request.getSession().getId();        OutputStream os = new FileOutputStream(new File("captcha.png"));        cs.setWidth(width);        cs.setHeight(height);        RandomFontFactory fontFactory = new RandomFontFactory();        fontFactory.setMaxSize(size);        fontFactory.setMinSize(size);        cs.setFontFactory(fontFactory);        Captcha captcha = cs.getCaptcha();        String patchca = captcha.getWord();        // 放入缓存        Element element = new Element(sessionId, patchca);        cache.put(element);        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();        ImageIO.write(captcha.getImage(), "png", byteArrayOutputStream);        String imageString = "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray()).toString();        JSONObject jsonObject = new JSONObject();        jsonObject.put("captcha", imageString);        return jsonObject;    }    public static void init() {        cs = new ConfigurableCaptchaService();        int width = WIDTH;        int height = HEIGHT;        cs.setWidth(width);        cs.setHeight(height);        int maxLength = MAX_LENGTH;        int minLength = MIN_LENGTH;        RandomWordFactory wf = new RandomWordFactory();        wf.setMaxLength(maxLength);        wf.setMinLength(minLength);        cs.setWordFactory(wf);        ResourcePatternResolver rpr = new PathMatchingResourcePatternResolver();        Resource resource = rpr.getResource("classpath:" + CACHE_FILE);        try {            CacheManager cacheManager = CacheManager.create(resource.getInputStream());            cache = cacheManager.getCache(CACHE_NAME);            if (cache == null) {                Configuration configuration = ConfigurationFactory.parseConfiguration(resource.getInputStream());                cache = new Cache(configuration.getCacheConfigurations().get(CACHE_NAME));                if (cache == null) {                    cache = cacheManager.getCache(Cache.DEFAULT_CACHE_NAME);                } else {                    cacheManager.addCache(cache);                }            }        } catch (CacheException e) {            e.printStackTrace();        } catch (IOException e) {            e.printStackTrace();        }    }    /**     * 检查验证码是否正确     *     * @param sessionId     * @param code     * @return     */    public static boolean validate(String sessionId, String code) {        Element element = cache.get(sessionId);        if (element != null) {            String sourceCode = (String) element.getObjectValue();            cache.remove(sessionId);            boolean flag = StringUtils.equalsIgnoreCase(code, sourceCode);            return flag;        }        return false;    }    public static void destroy() {        cs = null;        CacheManager.getInstance().removeCache(CACHE_NAME);    }}