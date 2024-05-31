package pw.karczewski.tasks.validation;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;

@Component
public class DictionaryValidator implements ConstraintValidator<DictionaryConstraint, String>, ApplicationContextAware {
    private static ApplicationContext applicationContext;
    private String table;

    @SneakyThrows
    @SuppressWarnings("null")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        DictionaryValidator.applicationContext = applicationContext;
    }

    @SneakyThrows
    @Override
    public void initialize(DictionaryConstraint constraintAnnotation) {
        table = constraintAnnotation.value();

        // Ensure the selected table exists in the database
        var query = String.format("create table if not exists %s (\"value\" text not null)", table);
        try (var connection = getConnection(); var statement = connection.createStatement()) {
            statement.execute(query);
        }
    }

    @SneakyThrows
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Ignore the check if the value is null
        if (value == null)
            return true;

        var query = String.format("select 1 from %s where \"value\" = ?", table);
        try (var connection = getConnection(); var statement = connection.prepareStatement(query)) {
            statement.setString(1, value);
            try (var result = statement.executeQuery()) {
                return result.first();
            }
        }
    }

    @SneakyThrows
    private Connection getConnection() {
        var dataSource = applicationContext.getBean(DataSource.class);
        return dataSource.getConnection();
    }
}
