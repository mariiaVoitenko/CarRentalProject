package ua.nure.voitenkom.SummaryTask4.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.db.connection.ConnectionFactory;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.brand.BrandRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.brand.IBrandRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.car.CarRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.car.ICarRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.check.CheckRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.check.ICheckRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.color.ColorRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.color.IColorRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.damage.DamageRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.damage.IDamageRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.damagecheck.DamageCheckRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.damagecheck.IDamageCheckRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.decline.DeclineRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.decline.IDeclineRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.majorityclass.IMajorityClassRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.majorityclass.MajorityClassRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.rent.IRentRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.rent.RentRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.role.IRoleRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.role.RoleRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.status.IStatusRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.status.StatusRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.user.IUserRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.user.UserRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.brand.BrandService;
import ua.nure.voitenkom.SummaryTask4.service.brand.IBrandService;
import ua.nure.voitenkom.SummaryTask4.service.car.CarService;
import ua.nure.voitenkom.SummaryTask4.service.car.ICarService;
import ua.nure.voitenkom.SummaryTask4.service.check.CheckService;
import ua.nure.voitenkom.SummaryTask4.service.check.ICheckService;
import ua.nure.voitenkom.SummaryTask4.service.color.ColorService;
import ua.nure.voitenkom.SummaryTask4.service.color.IColorService;
import ua.nure.voitenkom.SummaryTask4.service.damage.DamageService;
import ua.nure.voitenkom.SummaryTask4.service.damage.IDamageService;
import ua.nure.voitenkom.SummaryTask4.service.damagecheck.DamageCheckService;
import ua.nure.voitenkom.SummaryTask4.service.damagecheck.IDamageCheckService;
import ua.nure.voitenkom.SummaryTask4.service.decline.DeclineService;
import ua.nure.voitenkom.SummaryTask4.service.decline.IDeclineService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.IMajorityClassService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.MajorityClassService;
import ua.nure.voitenkom.SummaryTask4.service.photo.IPhotoService;
import ua.nure.voitenkom.SummaryTask4.service.photo.PhotoService;
import ua.nure.voitenkom.SummaryTask4.service.rent.IRentService;
import ua.nure.voitenkom.SummaryTask4.service.rent.RentService;
import ua.nure.voitenkom.SummaryTask4.service.role.IRoleService;
import ua.nure.voitenkom.SummaryTask4.service.role.RoleService;
import ua.nure.voitenkom.SummaryTask4.service.status.IStatusService;
import ua.nure.voitenkom.SummaryTask4.service.status.StatusService;
import ua.nure.voitenkom.SummaryTask4.service.user.IUserService;
import ua.nure.voitenkom.SummaryTask4.service.user.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BeanContextInitializer implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(BeanContextInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent context) {
        logger.debug("Initializing beans");

        ConnectionHolder connectionHolder = new ConnectionHolder();
        ITransactionManager transactionManager = new TransactionManager(new ConnectionFactory(), connectionHolder);

        IBrandRepository brandRepository = new BrandRepository(connectionHolder);
        IBrandService brandService = new BrandService(transactionManager, brandRepository);
        context.getServletContext().setAttribute(ServiceConstant.BRAND_SERVICE_CONTEXT, brandService);

        ICarRepository carRepository = new CarRepository(connectionHolder);
        ICarService carService = new CarService(transactionManager, carRepository);
        context.getServletContext().setAttribute(ServiceConstant.CAR_SERVICE_CONTEXT, carService);

        ICheckRepository checkRepository = new CheckRepository(connectionHolder);
        ICheckService checkService = new CheckService(transactionManager, checkRepository);
        context.getServletContext().setAttribute(ServiceConstant.CHECK_SERVICE_CONTEXT, checkService);

        IColorRepository colorRepository = new ColorRepository(connectionHolder);
        IColorService colorService = new ColorService(transactionManager, colorRepository);
        context.getServletContext().setAttribute(ServiceConstant.COLOR_SERVICE_CONTEXT, colorService);

        IDamageRepository damageRepository = new DamageRepository(connectionHolder);
        IDamageService damageService = new DamageService(transactionManager, damageRepository);
        context.getServletContext().setAttribute(ServiceConstant.DAMAGE_SERVICE_CONTEXT, damageService);

        IDamageCheckRepository damageCheckRepository = new DamageCheckRepository(connectionHolder);
        IDamageCheckService damageCheckService = new DamageCheckService(transactionManager, damageCheckRepository);
        context.getServletContext().setAttribute(ServiceConstant.DAMAGE_CHECK_SERVICE_CONTEXT, damageCheckService);

        IDeclineRepository declineRepository = new DeclineRepository(connectionHolder);
        IDeclineService declineService = new DeclineService(transactionManager, declineRepository);
        context.getServletContext().setAttribute(ServiceConstant.DECLINE_SERVICE_CONTEXT, declineService);

        IMajorityClassRepository majorityClassRepository = new MajorityClassRepository(connectionHolder);
        IMajorityClassService majorityClassService = new MajorityClassService(transactionManager, majorityClassRepository);
        context.getServletContext().setAttribute(ServiceConstant.CLASS_SERVICE_CONTEXT, majorityClassService);

        IRentRepository rentRepository = new RentRepository(connectionHolder);
        IRentService rentService = new RentService(transactionManager, rentRepository);
        context.getServletContext().setAttribute(ServiceConstant.RENT_SERVICE_CONTEXT, rentService);

        IRoleRepository roleRepository = new RoleRepository(connectionHolder);
        IRoleService roleService = new RoleService(transactionManager, roleRepository);
        context.getServletContext().setAttribute(ServiceConstant.ROLE_SERVICE_CONTEXT, roleService);

        IStatusRepository statusRepository = new StatusRepository(connectionHolder);
        IStatusService statusService = new StatusService(transactionManager, statusRepository);
        context.getServletContext().setAttribute(ServiceConstant.STATUS_SERVICE_CONTEXT, statusService);

        IUserRepository userRepository = new UserRepository(connectionHolder);
        IUserService userService = new UserService(transactionManager, userRepository);
        context.getServletContext().setAttribute(ServiceConstant.USER_SERVICE_CONTEXT, userService);

        String picturesFolder = context.getServletContext().getInitParameter(Attributes.PICTURES_FOLDER);
        IPhotoService photoService = new PhotoService(picturesFolder);
        context.getServletContext().setAttribute(ServiceConstant.PHOTO_SERVICE_CONTEXT, photoService);

        logger.debug("Put beans to context");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}