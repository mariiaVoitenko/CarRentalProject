package ua.nure.voitenkom.SummaryTask4.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.Attributes;
import ua.nure.voitenkom.SummaryTask4.db.connection.ConnectionFactory;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.role.IRoleRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.role.RoleRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.status.StatusRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Maria on 05.08.2015.
 */
@WebListener
public class BeanContextInitializer implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(BeanContextInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent context) {
        logger.debug("Initializing Beans");
        ConnectionHolder connectionHolder = new ConnectionHolder();
        ITransactionManager txManager = new TransactionManager(new ConnectionFactory(), connectionHolder);

        IRoleRepository roleRepository = new RoleRepository(connectionHolder);
        RoleService roleService = new RoleJdbcService(txManager, roleRepository);
        UserService userService = new UserJdbcService(txManager, new UserJdbcRepository(connectionHolder), roleService);
        context.getServletContext().setAttribute(BeansName.USER_SERVICE_CONTEXT, userService);

        ProductRepository productRepository = new ProductJdbcRepository(connectionHolder);
        ProductService productService = new ProductJdbcService(productRepository, txManager);
        context.getServletContext().setAttribute(BeansName.PRODUCT_SERVICE_CONTEXT, productService);

        CategoryRepository categoryRepository = new CategoryJdbcRepository(connectionHolder);
        CategoryService categoryService = new CategoryJdbcService(txManager, categoryRepository);
        context.getServletContext().setAttribute(BeansName.CATEGORY_SERVICE_CONTEXT, categoryService);

        OrderRepository orderRepository = new OrderJdbcRepository(connectionHolder);
        OrderItemRepository orderItemRepository = new OrderItemJdbcRepository(connectionHolder);
        OrderItemService orderItemService = new OrderItemJdbcService(txManager, orderItemRepository);
        StatusRepository statusRepository = new StatusJdbcRepository(connectionHolder);
        OrderService orderService = new OrderJdbcService(txManager, orderRepository, statusRepository);
        ShopService shopService = new ShopJdbcService(txManager, orderService, orderItemService);
        context.getServletContext().setAttribute(BeansName.SHOP_SERVICE_CONTEXT, shopService);

        String securityFilePath = context.getServletContext().getInitParameter(Attributes.SECURITY_FILE_PATH_ATTRIBUTE);
        SecurityService securityService = new SecurityService(securityFilePath);
        context.getServletContext().setAttribute(BeansName.SECURITY_SERVICE_CONTEXT, securityService);
        logger.debug("Put beans to context");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}