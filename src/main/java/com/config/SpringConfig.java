package com.config;

import com.lesson2.part1.Route;
import com.lesson2.part1.Service;
import com.lesson2.part1.Step;
import com.lesson2.part2.Entity.Item;
import com.lesson2.part2.ItemController.ItemController;
import com.lesson2.part2.ItemDAO.ItemDAO;
import com.lesson2.part2.ItemService.ItemService;
import com.lesson3.model.File;
import com.lesson3.model.Storage;
import com.lesson3.repository.FileDAO;
import com.lesson3.repository.StorageDAO;
import com.lesson3.repository.StorageDAOImpl;
import com.lesson3.service.FileService;
import com.lesson3.service.FileServiceImpl;
import com.lesson3.service.StorageService;
import com.lesson3.service.StorageServiceImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com")
public class SpringConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSources());
        sessionFactory.setPackagesToScan(new String[]{"com.lesson3.model"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        //em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@gromcode-lessons.clg2f8ljlc7q.us-east-2.rds.amazonaws.com:1521:ORCL");
        dataSource.setUsername("main");
        dataSource.setPassword("TsulukiyA_123");
        return dataSource;
    }

//

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;

    }

//    @Bean
//    public Properties additionalProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
//        return properties;
//    }


    @Bean
    public DataSource dataSources() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@gromcode-lessons.clg2f8ljlc7q.us-east-2.rds.amazonaws.com:1521:ORCL");
        dataSource.setUsername("main");
        dataSource.setPassword("TsulukiyA_123");
        return dataSource;
    }

    @Bean
    public Properties hibernateProperties(){
        return new Properties() {
            {
                //setProperty("hibernate.hbm2ddl.auto", "update");
                //setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
                setProperty("hibernate.show_sql", "true");
            }

        };
    }

    @Bean(name = "route")
    public Route route() {
        return new Route();
    }

    @Bean(name = "step")
    public Step step() {
        return new Step();
    }

    @Bean(name = "service")
    public Service service() {
        return new Service();
    }

    @Bean(name = "item")
    public Item item() {
        return new Item();
    }

    @Bean(name = "itemDao")
    public ItemDAO itemDAO() {
        return new ItemDAO();
    }

    @Bean(name = "itemService")
    public ItemService itemService() {
        return new ItemService();
    }

    @Bean(name = "itemController")
    public ItemController itemController() {
        return new ItemController();
    }

    @Bean(name = "file")
    public File file() {
        return new File();
    }

    @Bean(name = "storage")
    public Storage storage() {
        return new Storage();
    }

    @Bean(name = "fileDao")
    public FileDAO fileDAO(StorageDAO storageDAO) {
        return new FileDAOImpl(storageDAO);
    }

    @Bean(name = "storageDao")
    public StorageDAO storageDAO() {
        return new StorageDAOImpl();
    }

    @Bean(name = "fileService")
    public FileService fileService(FileDAO fileDAO) {
        return new FileServiceImpl(fileDAO);
    }

    @Bean(name = "storageService")
    public StorageService storageService() {
        return new StorageServiceImpl();
    }


}

