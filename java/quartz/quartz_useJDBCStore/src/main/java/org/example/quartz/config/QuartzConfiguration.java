package org.example.quartz.config;

import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.servlet.ServletContextEvent;
import java.util.Properties;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: QuartzConfiguration
 * Author:   lingerkptor
 * Date:     2021/10/31 下午 02:38
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/10/31 下午 02:38</td><td>1</td><td>file created</td></tr>
 * </table>
 */

@Configuration
public class QuartzConfiguration {

    @Bean
    public JobFactory getJobFactory(@Autowired AutowireCapableBeanFactory beanFactory) {
        return new SpringBeanJobFactory() {
            @Override
            protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
                Object jobInstance = super.createJobInstance(bundle);
                beanFactory.autowireBean(jobInstance);
                return jobInstance;
            }
        };
    }

    @Bean
    public QuartzInitializerListener executorListener(@Autowired @Qualifier("schedulerFactoryBean") SchedulerFactoryBean schedulerFactoryBean) {
        QuartzInitializerListener listener = new QuartzInitializerListener() {
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                super.contextInitialized(sce);
                System.out.println("quartz start..bean: ");
            }
            @Override
            public void contextDestroyed(ServletContextEvent sce) {
                super.contextDestroyed(sce);
                System.out.println("quartz close.. ");
            }
        };
        return listener;
    }

    @Bean(name = "quartzProp")
    @ConfigurationProperties(prefix = "spring.quartz.properties")
    public Properties personInfoListFactory() {
        return new Properties();
    }

    @Bean("schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory,
                                                     @Qualifier("quartzProp") Properties quartzProperties) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory);
        schedulerFactoryBean.setQuartzProperties(quartzProperties);
        return schedulerFactoryBean;
    }
}
