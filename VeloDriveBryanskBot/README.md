Бот для группы "Велодрайв Брянск".
Бот предназначен для сборки новостей из группы вконтакте и отправке их в группу телеграм.

Настройки бота хранятся в переменных окружения. Для его корректной работы необходимо настроить следующие переменные:

id группы, из которой собираются сообщения. Можно указывать несколько групп с разделителем "|".
VK_CONSTANT_OWNER_ID=""

id зарегистрированного приложения ВК.
VK_CONSTANT_USER_ID=""

Токен доступа приложения ВК.
VK_CONSTANT_ACCESS_TOKEN=""

Задержка между отправками запросов в АПИ.
VK_CONSTANT_DELAY=""

Токен телеграм-бота.
TELEGRAM_CONSTANT_BOT_TOKEN=""

Имя телеграм-бота.
TELEGRAM_CONSTANT_BOT_NAME=""

id чата, из которой транслируются сообщения по хэш-тегу. 0 - не отправлять в чат.
TELEGRAM_CONSTANT_CHAT_ID=""

Адрес прокси для доступа к telegram-api.
TELEGRAM_CONSTANT_PROXY_ADDRESS=""

Порт прокси для доступа к telegram-api.
TELEGRAM_CONSTANT_PROXY_PORT=""

Хэш-тег для фильтрации сообщений в группе.
TELEGRAM_CONSTANT_HASH_TAG=""

id канала, в который транслируются сообщения. 0 - не отправлять в канал.
TELEGRAM_CONSTANT_CHANNEL_ID=""

Интервал между отправлениями сообщений в телеграм.
TELEGRAM_CONSTANT_TELEGRAM_SEND_DELAY

Таймаут подключения к api телеграма.
TELEGRAM_CONSTANT_TELEGRAM_CONNECTION_TIMEOUT

id владельца бота.
TELEGRAM_CONSTANT_OWNER_ID

Интервал между переподключениями (для прокси).
TELEGRAM_CONSTANT_RECONNECT_TIMER

Время ожидания между переподключениями (для прокси).
TELEGRAM_CONSTANT_RECONNECT_DELIMITER