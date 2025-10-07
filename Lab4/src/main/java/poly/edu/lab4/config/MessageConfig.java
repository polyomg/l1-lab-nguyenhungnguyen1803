package poly.edu.lab4.config;

import java.time.Duration;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MessageConfig implements WebMvcConfigurer {

    // Cấu hình nguồn tài nguyên i18n
    @Bean("messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        // Đường dẫn đến file layout.properties, layout_en.properties, layout_vi.properties
        ms.setBasenames("classpath:i18n/layout");
        ms.setDefaultEncoding("UTF-8");

        // Ngăn Spring Boot tự thêm hậu tố locale như _en_US
        ms.setFallbackToSystemLocale(false);
        return ms;
    }

    // Cấu hình ngôn ngữ mặc định và lưu ngôn ngữ trong cookie
    @Bean("localeResolver")
    public LocaleResolver getLocaleResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookiePath("/");
        resolver.setCookieMaxAge(Duration.ofDays(30));
        resolver.setDefaultLocale(new Locale("vi")); // mặc định là tiếng Việt
        return resolver;
    }

    // Cho phép thay đổi ngôn ngữ bằng cách thêm ?lang=vi hoặc ?lang=en
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        registry.addInterceptor(interceptor);
    }
}
