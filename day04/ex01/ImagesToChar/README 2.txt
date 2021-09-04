Компиляция
javac -d target src/java/edu/school21/printer/app/Program.java

Архивирование
jar cvfm target/ImagesToChar.jar src/manifest.txt -C target .

Копируем ресурсы:
cp -a src/resources target

Запуск
java -jar target/ImagesToChar.jar --white=o --black=z 