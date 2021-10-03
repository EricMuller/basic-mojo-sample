# basic-mojo-sample
basic Mojo sample


## install mojo
mvn clean install

## test mojo
mvn com.emu.maven.plugins:basic-mojo-sample:1.0-SNAPSHOT:dependencies-mojo

```
 <build>
        <plugins>
            <plugin>
                <groupId>com.emu.maven.plugins</groupId>
                <artifactId>basic-mojo-sample</artifactId>
                <version>1.0-SNAPSHOT</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>dependencies-mojo</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <scope>test</scope>
                </configuration>
            </plugin>
        </plugins>
 </build>
```
