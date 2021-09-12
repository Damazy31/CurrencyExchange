# CurrencyExchange

Aplikacja pozwalająca na założenie konta i wymianę walut PLN<->USD według 
kursów z API NBP.

##Uruchomienie aplikacji

mvnw clean spring-boot:run

##Aplikacja wystawia API, które ma następujące funkcje:

###-Utworzenie nowego konta:

POST localhost:8080/accounts

Parametry ciała wiadomości:

{name} - imię właściciela konta

{surname} - nazwisko właściciela konta

{pln} - początkowy stan konta PLN

Parametry odpowiedzi:

{id} - id konta, nowoutworzonoa wartość potrzebna do kolejnych zapytań

{name} - imię właściciela konta

{surname} - nazwisko właściciela konta

{pln} - stan konta PLN

{usd} - stan konta USD, po utworzeniu konta równy 0

###-Pobranie informacji o koncie:

GET localhost:8080/accounts/{id}

Parametry zapytania:

{id} - id konta 

Parametry odpowiedzi:

{id} - id konta

{name} - imię właściciela konta

{surname} - nazwisko właściciela konta

{pln} - stan konta PLN

{usd} - stan konta USD


###-Wymiana PLN na USD:

PUT localhost:8080/accounts/{id}/buy

Parametry zapytania:

{id} - id konta

W ciele wiadomości należy podać żądaną wartość w USD

Jeśli wymiana się udała to parametry odpowiedzi takie jak przy pobraniu 
informacji o koncie. 

###-Wymiana USD na PLN

PUT localhost:8080/accounts/{id}/sell

Parametry zapytania:

{id} - id konta

W ciele wiadomości należy podać żądaną wartość w USD

Jeśli wymiana się udała to parametry odpowiedzi takie jak przy pobraniu
informacji o koncie. 

###-Usunięcie konta

DELETE localhost:8080/accounts/{id}/

Parametry zapytania:

{id} - id konta

Zapytanie nic nie zwraca