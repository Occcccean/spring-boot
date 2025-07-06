ip := "47.96.254.64"
url := "http://" + ip + "/myapp/"
package := "target/web-0.0.1-SNAPSHOT.jar"

run:
  mvn spring-boot:run

test uri:
  @curl {{uri}} 2>/dev/null

json uri query=".":
  @curl {{uri}} 2>/dev/null | jq -r '{{query}}'

docker:
  @sudo systemctl start docker
  @docker start opengauss

package:
  mvn package -DskipTests

scp:package
  scp {{package}} lingshin@gungnir.top:/opt/webapps/lingshin/web.jar

clean:
  mvn clean

sql:
  psql -U meteor password=meteor --host {{ip}}

update file="student.sql":
  psql -U meteor password=meteor --host {{ip}} -f {{file}}

open uri:
  zen  '{{url + uri}}'

log time="1min ago":
  ssh lingshin@gungnir.top 'journalctl -u spring-boot.service --since "{{time}}"' | nvim
