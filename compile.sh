# Compile all java file
project='/home/rakharrs/Workspace/avion-mi'
tomcat='/home/rakharrs/Downloads/apache-tomcat-10.0.27'
lib=$project/web/WEB-INF/lib
web_name='avion-mi'

shopt -s globstar

javac -cp $lib/servlet-api.jar:$lib/postgresql-42.5.0.jar -d $project/out $project/src/**/*.java

# Erase temp
rm -R $project/temp

# Reset classes of WEB-INF
rm -R $project/web/WEB-INF/classes/*
cp -R $project/out/* $project/web/WEB-INF/classes

# Create temp project
mkdir $project/temp

# Copy WEB-INF and views to temp
cp -R $project/web/* $project/temp
cp -R $project/views/* $project/temp

# Copy project into server
cd $project/temp
rm $tomcat/webapps/$web_name.war                         
jar -cf $tomcat/webapps/$web_name.war .

# Restart tomcat
sh $tomcat/bin/shutdown.sh
sh $tomcat/bin/startup.sh