@PropertySource("file:./first.properties")
@PropertySource("file:./second.properties")
public class MyAppConfig {
    @Bean
    public PropertiesFactoryBean properties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new FileSystemResource("./first.properties"));
        bean.setLocation(new FileSystemResource("./second.properties"));
        return bean;
    }
}
