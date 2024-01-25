package ru.ava.interview.dataCatalog;

import java.util.regex.Pattern;
/***********************************************************
 *  Проблемы:
 *    1) Предыдущие:
 *      1. Не понял, зачем он был статиком. убрал сразу.
 *
 *    2) Основные:
 *      1. Нет проверок. Никаких. Вообще.
 *      2. Паттерны строить из регулярок - довольно дорого.
 *
 *    Не значительные:
 *      Бессмысленное использование обёртки Булиан.
 *
 *      TODO:
 *          2.1) Добавим основные проверки. На нулы, и прочий бред, обернём всё в трай-кэтч
 *          2.2) Кэшируем
 *              * Строки Проходить тоже дорого, но и хэш нормальный построит не дешевле, по этому кешировать их не будем
 *      * Строки Проходить тоже дорого, но и хэш нормальный построит не дешевле, по этому кешировать их не буде
 *
 * *********************************************************/
public class MatcherImpl implements Matcher {

    public boolean matches(String regex, String text) {
        Boolean result = Pattern.compile( regex ).matcher( text ).matches();
        return result;
    }
}
