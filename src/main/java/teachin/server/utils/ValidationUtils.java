package teachin.server.utils;

import teachin.server.entity.*;
import teachin.server.exception.ValidationException;

import java.util.StringJoiner;

public class ValidationUtils {
    public static final String nameRegex = "^[A-Z|А-Я][a-z|а-я]{2,32}$";
    public static final String ogrnRegex = "^\\d{15}$";
    public static final String innRegex = "^(\\d{10})|(\\d{12})$";

    public static void validate(Teacher teacher) throws ValidationException {
        StringJoiner errorMessage = new StringJoiner("\n");
        String name = teacher.getName();
        String surname = teacher.getSurname();
        String patronymic = teacher.getPatronymic();
        Passport passport = teacher.getPassport();
        Vacancy vacancy = teacher.getVacancy();
        ScientificDegree degree = teacher.getScientificDegree();
        Integer hours = teacher.getHours();
        String social = teacher.getSocialWork();

        if (name == null || name.isBlank() || !name.matches(nameRegex)) {
            errorMessage.add("Имя сотрудника должно иметь 2 - 32 символов и начинаться с заглавной буквы");
        }

        if (surname == null || surname.isBlank() || !surname.matches(nameRegex)) {
            errorMessage.add("Фамилия сотрудника должно иметь 2 - 32 символов и начинаться с заглавной буквы");
        }

        if (patronymic == null || patronymic.isBlank() || !patronymic.matches(nameRegex)) {
            errorMessage.add("Отчество сотрудника должно иметь 2 - 32 символов и начинаться с заглавной буквы");
        }

        if (passport == null) {
            errorMessage.add("Должен указываться паспорт");
        }

        if (vacancy == null) {
            errorMessage.add("Должна указываться должность");
        }

        if (degree == null) {
            errorMessage.add("Должна указываться ученая степень");
        }

        if (hours == null) {
            errorMessage.add("Должны указываться часы вычета уроков");
        }

        if (social != null && !social.isBlank() && social.length() < 2) {
            errorMessage.add("Длина строки общественной работы должна превосходить 2 символа");
        }

        if (errorMessage.length() > 0)
            throw new ValidationException(errorMessage.toString());
    }

    public static void validate(Passport passport) throws ValidationException {
        StringJoiner errorMessage = new StringJoiner("\n");

        if (passport.getSeries() == null || passport.getSeries().isBlank()
                || passport.getSeries().length() != 4) {
            errorMessage.add("Серия паспорта должна состоять только из 4 цифровых символов");
        }

        if (passport.getNumber() == null || passport.getNumber().isBlank()
                || passport.getNumber().length() != 6) {
            errorMessage.add("Номер паспорта должен состоять только из 6 цифровых символов");
        }

        if (passport.getIssuingAuthority() == null || passport.getIssuingAuthority().isBlank()
                || passport.getIssuingAuthority().length() < 2) {
            errorMessage.add("Длина строки выдачи паспорта должна превышать 2 и не состоять только из пробельных символов");
        }

        if (errorMessage.length() > 0)
            throw new ValidationException(errorMessage.toString());
    }

    public static void validate(Moonlighter moonlighter) throws ValidationException {
        StringJoiner errorMessage = new StringJoiner("\n");

        if (moonlighter.getOGRN() == null || !moonlighter.getOGRN().matches(ogrnRegex)) {
            errorMessage.add("ОГРН совместительства должен содержать только 15 цифровых символов");
        }

        if (moonlighter.getINN() == null || !moonlighter.getINN().matches(innRegex)) {
            errorMessage.add("ИНН совместительства может состоять из 10 или 12 символов");
        }

        if (errorMessage.length() > 0)
            throw new ValidationException(errorMessage.toString());
    }

    public static void validate(Vacancy vacancy) throws ValidationException {
        StringJoiner errorMessage = new StringJoiner("\n");

        if (vacancy.getName() == null || vacancy.getName().isBlank()) {
            errorMessage.add("Название вакансии не должно быть пустым или содержать только пробелы");
        }

        if (vacancy.getDisciplines().size() == 0) {
            errorMessage.add("У вакансии должна быть хотя бы одна дисциплина");
        }

        if (errorMessage.length() > 0)
            throw new ValidationException(errorMessage.toString());
    }

    public static void validate(Discipline discipline) throws ValidationException {
        if (discipline.getName() == null || discipline.getName().isBlank()
                || discipline.getName().length() < 2 || discipline.getName().length() > 255)
            throw new ValidationException("Название дисциплины должно быть в диапазоне от 2 - 255 символов и не содержать только пробельных символов");
    }

    public static void validate(HRAccount account) throws ValidationException {
        StringJoiner errorMessage = new StringJoiner("\n");

        if (account.getUsername() == null || account.getUsername().isBlank()
                || account.getUsername().length() < 2 || account.getUsername().length() > 32) {
            errorMessage.add("Логин не может быть пуст или состоять из пробельных символов, длина должна быть не меньше 2 и не длиннее 32");
        }

        if (account.getPassword() == null || account.getPassword().isBlank()
                || account.getPassword().length() < 4 || account.getPassword().length() > 255) {
            errorMessage.add("Пароль не может быть пуст или состоять из пробельных символов, длина должна быть не меньше 4 и не длиннее 255");
        }

        if (account.getRole() == null) {
            errorMessage.add("Роль не должна быть пустой");
        }

        if (errorMessage.length() > 0)
            throw new ValidationException(errorMessage.toString());
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
        } else
            throw new ClassNotFoundException("Валидатора обработчика для данного класса не существует %s".formatted(entity.getClass().getSimpleName()));
    }
}
