package ru.ava.interview.dataCatalog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/***********************************************************
 *  <pre>
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
 *      TODO: Может что-то вспомню
 *      DONE:
 *          2.1) Добавим основные проверки. На нулы, и прочие варианты, обернём всё в трай-кэтч
 *          2.2) Кэшируем
 *              * Строки Проходить тоже дорого, но и хэш нормальный посчитать не дешевле, по этому кешировать их не будем, а если все строки хранить, то будет больно памяти
 *
 *      Возможноые дальнейшие улучшения: использовать LRU-cache,...
 *</pre>
 * *********************************************************/
public class MatcherImpl implements Matcher {

    private ConcurrentMap<String, Pattern> cache;

    MatcherImpl(){
        cache = new ConcurrentHashMap<>( );
    }

    MatcherImpl(Map<String, Pattern> savedCashe){                                               //Вдруг есть сохранённый кэш, можем его использовать
        cache = new ConcurrentHashMap<>( savedCashe );
    }

    public boolean matches(String regex, String text) {
        try {

            if( regex == null || text == null ){                                                // Если регулярка и текст - нулы, то просто ошибка будет, логично что не матчится нул под нул
                return false;
            }

            if( regex.isEmpty()  &&  text.isEmpty() ){                                          // НО! Пустая строка матчится под пустую регулярку
                return true;
            }

            if( regex.isEmpty()  ||  text.isEmpty() ){                                          // И если мы дошли до сюда, то пустая строка не матчится под не пустую регулярку и наоборот
                return false;
            }

            Pattern pattern;

            if( !cache.containsKey( regex ) ){                                                    // доколи регулярка не закеширована, нада её создать и закешировать
                pattern = Pattern.compile( regex );
                cache.putIfAbsent( regex, pattern );                                              // на случай если будут потоки, вставим паттерн атомарно
            } else {
                pattern = cache.get( regex );                                                     // Ну а если есть в кеше, то и
            }

            return  pattern.matcher( text ).matches();

        } catch ( PatternSyntaxException e ){
            System.out.println("Не верная регулярка, как нибудь обработать следует");
            return false;
        } catch (Exception e){
            return false;
        }
    }
}
