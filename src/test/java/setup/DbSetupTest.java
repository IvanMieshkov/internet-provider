package setup;

import com.mieshkov.corplan.model.dao.impl.ConnectionPoolHolder;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Ivan Mieshkov
 */
public class DbSetupTest {

    public static void setUpDataBase() {
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM corplan_db_test.user_tariff;");
            stmt.execute("DELETE FROM corplan_db_test.users;");
            stmt.execute("DELETE FROM corplan_db_test.tariff;");
            stmt.execute("ALTER TABLE corplan_db_test.users AUTO_INCREMENT =1;");
            stmt.execute("ALTER TABLE corplan_db_test.user_tariff AUTO_INCREMENT = 1;");
            stmt.execute("ALTER TABLE corplan_db_test.tariff AUTO_INCREMENT =1;");
            stmt.execute("INSERT INTO " +
                    "           corplan_db_test.users (login_number, full_name_en, full_name_ukr, password, email, phone_number, balance, user_role, user_active) " +
                    "           VALUES ('000001', 'admin', 'адмін', '$2a$10$Gcs0UPzgE4YUaf97SixIDuU2DVx/75vD4IE5nqD/2X.le59MnINnK', 'admin@gmail.com', '+380445557788', 0, 'admin', '1'), " +
                    "                   ('000002', 'user', 'користувач', '$2a$10$WlPgTDIdMBpfQCgEsPiqiumkZjzI0mlN.BMauxN5SArxzUh/jHZNm', 'user@ukr.net', '+380564895263', '0', 'client', '1');");
            stmt.execute("INSERT INTO" +
                    "          corplan_db_test.tariff (tariff_name_ukr, tariff_name_en, tariff_price, tariff_service) " +
                    "          VALUES ('Базовий', 'Basic', 100, 'internet')," +
                    "                 ('Місцевий', 'Local', 40, 'tv'), " +
                    "                 ('Безлімітний', 'Unlimited', 140, 'telephony')");
            stmt.execute("INSERT INTO" +
                    "           corplan_db_test.user_tariff (user_id, tariff_id) VALUES (2, 2), (2, 3) ");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void tearDownDataBase() {
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM corplan_db_test.users;");
            stmt.execute("DELETE FROM corplan_db_test.tariff;");
            stmt.execute("DELETE FROM corplan_db_test.user_tariff;");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
