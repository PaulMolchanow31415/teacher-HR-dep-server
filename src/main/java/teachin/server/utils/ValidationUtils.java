package teachin.server.utils;

import teachin.server.entity.*;
import teachin.server.exception.ValidationException;

import java.util.StringJoiner;

public class ValidationUtils {
    public static final String seriesRegex = "^\\d{4}$";
    public static final String numberRegex = "^\\d{6}$";
    public static final String ogrnRegex = "^\\d{15}$";
    public static final String innRegex = "^(\\d{10})|(\\d{12})$";
    public static final String nameRegex = "^[A-Z|А-Я][a-z|а-я]{2,32}$";

    public static void validate(Teacher teacher) throws ValidationException {
        var errors = new StringJoiner("\n");
        var name = teacher.getName();
        var surname = teacher.getSurname();
        var patronymic = teacher.getPatronymic();
        var passport = teacher.getPassport();
        var vacancy = teacher.getVacancy();
        var degree = teacher.getScientificDegree();
        var hours = teacher.getHours();
        var social = teacher.getSocialWork();

        if (name == null || !name.matches(nameRegex)) {
            errors.add("Имя сотрудника должно иметь 2 - 32 символов и начинаться с заглавной буквы");
        }

        if (surname == null || !surname.matches(nameRegex)) {
            errors.add("Фамилия сотрудника должно иметь 2 - 32 символов и начинаться с заглавной буквы");
        }

        if (patronymic == null || !patronymic.matches(nameRegex)) {
            errors.add("Отчество сотрудника должно иметь 2 - 32 символов и начинаться с заглавной буквы");
        }

        if (passport == null) {
            errors.add("Должен указываться паспорт");
        }

        if (vacancy == null) {
            errors.add("Должна указываться должность");
        }

        if (degree == null) {
            errors.add("Должна указываться ученая степень");
        }

        if (hours == null || hours < 1) {
            errors.add("Должны указываться часы вычета уроков, не меньше одного");
        }

        if (social != null && (social.isBlank() || social.length() < 2)) {
            errors.add("Длина строки общественной работы должна быть не меньше 2 символов или состоять из пробелов");
        }

        if (errors.length() > 0) throw new ValidationException(errors.toString());
    }

    public static void validate(Passport passport) throws ValidationException {
        var errors = new StringJoiner("\n");

        if (passport.getSeries() == null || !passport.getSeries().matches(seriesRegex)) {
            errors.add("Серия паспорта должна состоять только из 4 цифровых символов");
        }

        if (passport.getNumber() == null || !passport.getNumber().matches(numberRegex)) {
            errors.add("Номер паспорта должен состоять только из 6 цифровых символов");
        }

        if (passport.getDateOfIssue() == null) {
            errors.add("Дата выдачи паспорта не должна быть пустой");
        }

        if (passport.getIssuingAuthority() == null || passport.getIssuingAuthority().isBlank()
                || passport.getIssuingAuthority().length() < 2) {
            errors.add("Длина строки выдачи паспорта должна быть не меньше 2 и состоять только из непробельных символов");
        }

        if (errors.length() > 0) throw new ValidationException(errors.toString());
    }

    public static void validate(Moonlighter moonlighter) throws ValidationException {
        var errors = new StringJoiner("\n");

        if (moonlighter.getOGRN() == null || !moonlighter.getOGRN().matches(ogrnRegex)) {
            errors.add("ОГРН совместительства должен содержать только 15 цифровых символов");
        }

        if (moonlighter.getINN() == null || !moonlighter.getINN().matches(innRegex)) {
            errors.add("ИНН совместительства может состоять из 10 или 12 цифровых символов");
        }

        if (errors.length() > 0) throw new ValidationException(errors.toString());
    }

    public static void validate(Vacancy vacancy) throws ValidationException {
        var errors = new StringJoiner("\n");

        if (vacancy.getName() == null || vacancy.getName().isBlank()
                || vacancy.getName().length() < 3) {
            errors.add("Название вакансии должно содержать не менее 3 непробельных символов");
        }

        if (vacancy.getDisciplines() == null || vacancy.getDisciplines().size() == 0) {
            errors.add("У вакансии должна быть хотя бы одна дисциплина");
        }

        if (errors.length() > 0) throw new ValidationException(errors.toString());
    }

    public static void validate(Discipline discipline) throws ValidationException {
        if (discipline.getName() == null || discipline.getName().isBlank()
                || discipline.getName().length() < 2 || discipline.getName().length() > 255) {
            throw new ValidationException("Название дисциплины должно быть в диапазоне от 2 - 255 символов и не содержать только пробельных символов");
        }
    }

    public static void validate(HRAccount account) throws ValidationException {
        var errors = new StringJoiner("\n");

        if (account.getUsername() == null || account.getUsername().isBlank()
                || account.getUsername().length() < 2 || account.getUsername().length() > 32) {
            errors.add("Логин не может быть пуст или состоять из пробельных символов, длина должна быть не меньше 2 и не длиннее 32");
        }

        if (account.getPassword() == null || account.getPassword().isBlank()
                || account.getPassword().length() < 4 || account.getPassword().length() > 255) {
            errors.add("Пароль не может быть пуст или состоять из пробельных символов, длина должна быть не меньше 4 и не длиннее 255");
        }

        if (account.getRole() == null) {
            errors.add("Роль не должна быть пустой");
        }

        if (errors.length() > 0) throw new ValidationException(errors.toString());
    }

    public static void validate(Object entity) throws ValidationException, ClassNotFoundException {
        if (entity instanceof HRAccount) {
            validate((HRAccount) entity);
        } else if (entity instanceof Discipline) {
            validate((Discipline) entity);
        } else if (entity instanceof Passport) {
            validate((Passport) entity);
        } else if (entity instanceof Moonlighter) {
            validate((Moonlighter) entity);
        } else if (entity instanceof Vacancy) {
            validate((Vacancy) entity);
        } else if (entity instanceof Teacher) {
            validate((Teacher) entity);
        } else {
            throw new ClassNotFoundException("Валидатора обработчика для данного класса не существует %s".formatted(entity.getClass().getSimpleName()));
        }
    }
}