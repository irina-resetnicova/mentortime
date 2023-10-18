//"$2y$" - указывает, что используется версия алгоритма BCrypt.
//"10" - количество раундов хеширования, которые были применены при создании этого хеша.
//"PIvDEaxoGvSA5MP5ULcPFOH93xqtCivNp62fTuaDyOh7rq0uM6wWq" - хешированный пароль.
//BCrypt - это односторонний алгоритм хеширования, и вы не можете дешифровать хеш обратно в оригинальный пароль.
// Вместо этого вы можете сравнивать введенный пользователем пароль с хешем, созданным ранее,
// чтобы проверить, совпадают ли они.

package com.endava.atf.transition.testDataUI;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordBCrypt {
        public static void main(String[] args) {
            String enteredPassword = "123456";

            // Генерируем хеш пароля
            String hashedPassword = BCrypt.hashpw(enteredPassword, BCrypt.gensalt());

            System.out.println("password hash: " + hashedPassword);

            if (BCrypt.checkpw(enteredPassword, hashedPassword)) {
                System.out.println("The passwords match");
            } else {
                System.out.println("The passwords match do not match");
            }
        }
    }

