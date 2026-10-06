# Полностью готовый GitHub Actions проект

1. Создайте пустой GitHub repository.
2. Загрузите всё содержимое этой папки в repository.
3. Откройте **Actions → Build Android APK → Run workflow**.
4. После сборки скачайте artifact `LETI-Student-3.0-debug`.

Gradle Wrapper находится в `gradlew`, `gradlew.bat` и `gradle/wrapper/gradle-wrapper.properties`. Скрипты автоматически получают Gradle 8.9 из официального Gradle distribution при первой сборке.

Для release: **Actions → Build Release APK → Run workflow**.
