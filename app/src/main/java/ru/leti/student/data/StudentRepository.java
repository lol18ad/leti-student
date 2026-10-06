package ru.leti.student.data;
import java.util.*;

public class StudentRepository {
    // Единственная точка подключения реального API.
    // Публичной документации API lk.etu.ru не обнаружено, поэтому endpoint'ы намеренно не выдумываются.
    public List<Models.News> news() {
        return Arrays.asList(
            new Models.News("Новости личного кабинета","Сегодня","После авторизации здесь будут отображаться новости ЛЭТИ."),
            new Models.News("Учебный процесс","Обновление","Данные синхронизируются с личным кабинетом.")
        );
    }
    public List<Models.Lesson> schedule() {
        return Arrays.asList(
            new Models.Lesson("09:00","Математика","Преподаватель","Ауд. —"),
            new Models.Lesson("10:50","Программирование","Преподаватель","Ауд. —"),
            new Models.Lesson("13:30","Физика","Преподаватель","Ауд. —")
        );
    }
    public List<Models.Grade> grades() {
        return Arrays.asList(
            new Models.Grade("Математика","—","—"),
            new Models.Grade("Программирование","—","—"),
            new Models.Grade("Физика","—","—")
        );
    }
}
