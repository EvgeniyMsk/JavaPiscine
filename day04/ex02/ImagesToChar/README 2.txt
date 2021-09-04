mkdir target
mkdir lib
curl https://repo1.maven.org/maven2/com/beust/jcommander/1.78/jcommander-1.78.jar --output lib/jcommander.jar
curl https://repo1.maven.org/maven2/com/diogonunes/JColor/5.0.0/JColor-5.0.0.jar --output lib/jcolor.jar
jar xf lib/jcommander.jar
jar xf lib/jcolor.jar
mv com target/
rm -rf META-INF/
cp -r src/resources target
find src/java -type f -name "*.java" > project_files_list.txt
javac -cp target -sourcepath src/java -d target @project_files_list.txt
jar cfmv target/images-to-chars-printer.jar src/manifest.txt -C target .
java -jar target/images-to-chars-printer.jar --white=RED --black=WHITE
#rm -rf target
#rm project_files_list.txt
#rm lib