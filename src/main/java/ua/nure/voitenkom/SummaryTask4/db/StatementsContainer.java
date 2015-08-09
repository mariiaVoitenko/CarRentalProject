package ua.nure.voitenkom.SummaryTask4.db;

public class StatementsContainer {

    //brands
    public static final String SQL_SELECT_ALL_BRANDS = "SELECT * FROM brands";
    public static final String SQL_SELECT_BRAND_BY_ID = "SELECT * FROM brands WHERE id = ?";
    public static final String SQL_SELECT_BRAND_BY_NAME = "SELECT * FROM brands WHERE name = ?";
    public static final String SQL_INSERT_BRAND = "INSERT INTO brands (name) VALUES (?)";
    public static final String SQL_UPDATE_BRAND_BY_ID = "UPDATE brands SET name = ? WHERE id = ?";
    public static final String SQL_DELETE_BRAND_BY_ID = "DELETE FROM brands WHERE id = ?";

    //cars
    public static final String SQL_SELECT_ALL_CARS = "SELECT * FROM cars";
    public static final String SQL_SELECT_CAR_BY_ID = "SELECT * FROM cars WHERE id = ?";
    public static final String SQL_SELECT_CAR_BRAND_BY_ID = "SELECT brands.name FROM brands, cars WHERE cars.id = ? AND " +
            "cars.brands_id = brands.id";
    public static final String SQL_SELECT_CAR_CLASS_BY_ID = "SELECT classes.name FROM classes, cars WHERE cars.id = ? AND " +
            "cars.classes_id = classes.id";
    public static final String SQL_INSERT_CAR = "INSERT INTO cars (model, price, doors_count, has_conditioner, " +
            "big_luggage_count, small_luggage_count, sits_count, classes_id, colors_id, statuses_id, brands_id, photo, " +
            "available_count) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String SQL_UPDATE_CAR_AVAILABLE_COUNT_BY_ID = "UPDATE cars SET available_count = ? WHERE id = ?";
    public static final String SQL_UPDATE_CAR_STATUS_BY_ID = "UPDATE cars SET statuses_id = ? WHERE id = ?";
    public static final String SQL_UPDATE_CAR_PRICE_BY_ID = "UPDATE cars SET price = ? WHERE id = ?";
    public static final String SQL_DELETE_CAR_BY_ID = "DELETE FROM cars WHERE id = ?";
    public static final String SQL_SELECT_ALL_CAR_INFORMATION =  "SELECT cars.id, cars.model, price, doors_count, has_conditioner, " +
            "big_luggage_count, small_luggage_count, sits_count, classes.name AS class_name, brands.name AS brand_name, \n" +
            "colors.name AS color_name, statuses.name AS status_name FROM cars, classes, colors, statuses,\n" +
            "brands WHERE cars.classes_id = classes.id AND cars.brands_id = brands.id AND colors.id = cars.colors_id " +
            "AND statuses.id = cars.statuses_id AND cars.id = ?";

    //checks
    public static final String SQL_SELECT_ALL_CHECKS = "SELECT * FROM checks";
    public static final String SQL_SELECT_CHECK_BY_ID = "SELECT * FROM checks WHERE id = ?";
    public static final String SQL_SELECT_ALL_UNPAYED_CHECKS = "SELECT * FROM checks WHERE is_payed = false";
    public static final String SQL_INSERT_CHECK = "INSERT INTO checks (sum, is_payed) VALUES (?, ?)";
    public static final String SQL_UPDATE_CHECK_BY_ID = "UPDATE checks SET sum = ?, is_payed = ? WHERE id = ?";
    public static final String SQL_UPDATE_CHECK_SUM_BY_ID = "UPDATE checks SET sum = ? WHERE id = ?";
    public static final String SQL_UPDATE_CHECK_PAY_BY_ID = "UPDATE checks SET is_payed = true WHERE id = ?";
    public static final String SQL_DELETE_CHECK_BY_ID = "DELETE FROM checks WHERE id = ?";

    //colors
    public static final String SQL_SELECT_ALL_COLORS = "SELECT * FROM colors";
    public static final String SQL_SELECT_COLOR_BY_ID = "SELECT * FROM colors WHERE id = ?";
    public static final String SQL_SELECT_COLOR_BY_NAME = "SELECT * FROM colors WHERE name = ?";
    public static final String SQL_INSERT_COLOR = "INSERT INTO colors (name) VALUES (?)";
    public static final String SQL_UPDATE_COLOR_BY_ID = "UPDATE colors SET name = ? WHERE id = ?";
    public static final String SQL_DELETE_COLOR_BY_ID = "DELETE FROM colors WHERE id = ?";

    //damage
    public static final String SQL_SELECT_ALL_DAMAGES = "SELECT * FROM damages";
    public static final String SQL_SELECT_DAMAGE_BY_ID = "SELECT * FROM damages WHERE id = ?";
    public static final String SQL_SELECT_DAMAGE_BY_NAME = "SELECT * FROM damages WHERE name = ?";
    public static final String SQL_INSERT_DAMAGE = "INSERT INTO damages (name, sum) VALUES (?, ?)";
    public static final String SQL_UPDATE_DAMAGE_BY_ID = "UPDATE damages SET name = ?, sum = ? WHERE id = ?";
    public static final String SQL_UPDATE_DAMAGE_SUM_BY_ID = "UPDATE damages SET sum = ? WHERE id = ?";
    public static final String SQL_DELETE_DAMAGE_BY_ID = "DELETE FROM damages WHERE id = ?";

    //damagecheks
    public static final String SQL_SELECT_DAMAGECHECK_BY_ID = "SELECT * FROM damages_checks WHERE id = ?";
    public static final String SQL_SELECT_ALL_DAMAGECHECKS = "SELECT * FROM damages_checks";
    public static final String SQL_SELECT_DAMAGE_ID_BY_DAMAGECHECK_ID = "SELECT damages_id FROM damages_checks WHERE id = ?";
    public static final String SQL_SELECT_CHECK_ID_BY_DAMAGECHECK_ID = "SELECT checks_id FROM damages_checks WHERE id = ?";
    public static final String SQL_SELECT_ALL_DAMAGECHECK_INFORMATION = "SELECT damages.name, damages.sum, checks.id " +
            "AS check, damages.id AS damage, damages_checks.id FROM damages_checks, damages, checks";
    public static final String SQL_INSERT_DAMAGECHECK = "INSERT INTO damages_checks (damages_id, checks_id) VALUES (?, ?)";
    public static final String SQL_UPDATE_DAMAGECHECK_BY_ID = "UPDATE damages_checks SET damages_id = ?, checks_id = ? " +
            "WHERE id = ?";
    public static final String SQL_DELETE_DAMAGECHECK_BY_ID = "DELETE FROM damages_checks WHERE id = ?";

    //declines
    public static final String SQL_SELECT_ALL_DECLINES = "SELECT * FROM declines";
    public static final String SQL_SELECT_DECLINE_BY_ID = "SELECT * FROM declines WHERE id = ?";
    public static final String SQL_SELECT_DECLINE_BY_NAME = "SELECT * FROM declines WHERE name = ?";
    public static final String SQL_INSERT_DECLINE = "INSERT INTO declines (name) VALUES (?)";
    public static final String SQL_UPDATE_DECLINE_BY_ID = "UPDATE declines SET name = ? WHERE id = ?";
    public static final String SQL_DELETE_DECLINE_BY_ID = "DELETE FROM declines WHERE id = ?";

    //majority class
    public static final String SQL_SELECT_ALL_CLASSES = "SELECT * FROM classes";
    public static final String SQL_SELECT_CLASS_BY_ID = "SELECT * FROM classes WHERE id = ?";
    public static final String SQL_SELECT_CLASS_BY_NAME = "SELECT * FROM classes WHERE name = ?";
    public static final String SQL_INSERT_CLASS = "INSERT INTO classes (name) VALUES (?)";
    public static final String SQL_UPDATE_CLASS_BY_ID = "UPDATE classes SET name = ? WHERE id = ?";
    public static final String SQL_DELETE_CLASS_BY_ID = "DELETE FROM classes WHERE id = ?";

    //rent
    public static final String SQL_SELECT_ALL_RENTS = "SELECT * FROM rents";
    public static final String SQL_SELECT_ALL_RENTS_FOR_USER = "SELECT * FROM rents WHERE users_id = ?";
    public static final String SQL_SELECT_RENT_BY_ID = "SELECT * FROM rents WHERE id = ?";
    public static final String SQL_INSERT_RENT = "INSERT INTO rents (is_driven, days, cars_id, users_id, declines_id, " +
            "checks_id, start_date, end_date) VALUES (?,?,?,?,?,?,?,?)";
    public static final String SQL_UPDATE_RENT_BY_ID = "UPDATE rents SET is_driven = ?, days = ?, cars_id = ?, " +
            "users_id = ?, declines_id = ?, checks_id = ?, start_date = ?, end_date = ? WHERE id = ?";
    public static final String SQL_DELETE_RENT_BY_ID = "DELETE FROM rents WHERE id = ?";

    // roles
    public static final String SQL_SELECT_ALL_ROLES = "SELECT * FROM roles";
    public static final String SQL_SELECT_ROLE_BY_ID = "SELECT * FROM roles WHERE id = ?";
    public static final String SQL_SELECT_ROLE_BY_NAME = "SELECT * FROM roles WHERE name = ?";
    public static final String SQL_INSERT_ROLE = "INSERT INTO roles (name) VALUES (?)";
    public static final String SQL_UPDATE_ROLE_BY_ID = "UPDATE roles SET name = ? WHERE id = ?";
    public static final String SQL_DELETE_ROLE_BY_ID = "DELETE FROM roles WHERE id = ?";

    //status
    public static final String SQL_SELECT_ALL_STATUSES = "SELECT * FROM statuses";
    public static final String SQL_SELECT_STATUS_BY_ID = "SELECT * FROM statuses WHERE id = ?";
    public static final String SQL_SELECT_STATUS_BY_NAME = "SELECT * FROM statuses WHERE name = ?";
    public static final String SQL_INSERT_STATUS = "INSERT INTO statuses (name) VALUES (?)";
    public static final String SQL_UPDATE_STATUS_BY_ID = "UPDATE statuses SET name = ? WHERE id = ?";
    public static final String SQL_DELETE_STATUS_BY_ID = "DELETE FROM statuses WHERE id = ?";

    //user
    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
    public static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static final String SQL_INSERT_USER = "INSERT INTO users (full_name, registration_token, passport_number," +
            " roles_id, password, login, registration_time) VALUES (?,?,?,?,?,?,?)";
    public static final String SQL_INSERT_USER_WITH_PHOTO = "INSERT INTO users (full_name, registration_token, passport_number," +
            " roles_id, photo, password, login, registration_time) VALUES (?,?,?,?,?,?,?,?)";
    public static final String SQL_UPDATE_USER_BLOCKED = "UPDATE users SET is_blocked = true WHERE id = ?";
    public static final String SQL_UPDATE_USER_BLOCKED_NOT = "UPDATE users SET is_blocked = false WHERE id = ?";
    public static final String SQL_UPDATE_USER_IS_REGISTERED = "UPDATE users SET is_registered = true WHERE id = ?";
    public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    public static final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
}
