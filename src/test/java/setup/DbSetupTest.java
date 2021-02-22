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
            stmt.execute("DELETE FROM corplan_db_test.user_has_tariff LIMIT 1000;");
            stmt.execute("DELETE FROM corplan_db_test.user LIMIT 1000;");
            stmt.execute("DELETE FROM corplan_db_test.tariff LIMIT 1000;");
            stmt.execute("ALTER TABLE corplan_db_test.user AUTO_INCREMENT =100001;");
            stmt.execute("ALTER TABLE corplan_db_test.user_has_tariff AUTO_INCREMENT = 1;");
            stmt.execute("ALTER TABLE corplan_db_test.tariff AUTO_INCREMENT =1;");
            stmt.execute("INSERT INTO " +
                    "           corplan_db_test.user (name_en, name_ukr, password, email, phone_number, balance, role, active) " +
                    "           VALUES ('admin', 'адмін', '$2a$10$Gcs0UPzgE4YUaf97SixIDuU2DVx/75vD4IE5nqD/2X.le59MnINnK', 'admin@gmail.com', '+380445557788', 0, 'admin', '1'), " +
                    "                   ('user', 'користувач', '$2a$10$WlPgTDIdMBpfQCgEsPiqiumkZjzI0mlN.BMauxN5SArxzUh/jHZNm', 'user@ukr.net', '+380564895263', '0', 'client', '1');");
            stmt.execute("INSERT INTO" +
                    "          corplan_db_test.tariff (name_ukr, name_en, price, service) " +
                    "          VALUES ('Базовий', 'Basic', 100, 'internet')," +
                    "                 ('Місцевий', 'Local', 40, 'tv'), " +
                    "                 ('Безлімітний', 'Unlimited', 140, 'telephony')");
            stmt.execute("INSERT INTO user_has_tariff (user_id, tariff_id) " +
                    "SELECT 2, 2 FROM DUAL WHERE NOT EXISTS " +
                    "(SELECT tariff.service, user_has_tariff.user_id, user_has_tariff.tariff_id FROM tariff "+
                    "INNER JOIN user_has_tariff ON tariff.id = user_has_tariff.tariff_id " +
                    "WHERE service = 'tv' AND user_has_tariff.user_id = 2 LIMIT 1)");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void tearDownDataBase() {
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM corplan_db_test.user;");
            stmt.execute("DELETE FROM corplan_db_test.tariff;");
            stmt.execute("DELETE FROM corplan_db_test.user_has_tariff;");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
