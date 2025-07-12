# Java 21（Eclipse Temurinディストリビューション）を使用
FROM maven:3.9.6-eclipse-temurin-21

# 作業ディレクトリを /app に設定
WORKDIR /app

# プロジェクト全体をコピー（pom.xml, src/, mvnwなど）
COPY . /app

# Maven Wrapperを使ってビルド（テストはスキップ）
RUN mvn clean package -DskipTests

# Spring Boot アプリケーションが使うポート
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
EXPOSE 8080 5005

# アプリの起動コマンド
CMD ["java", "-jar", "target/demoapp-0.0.1-SNAPSHOT.jar"]
