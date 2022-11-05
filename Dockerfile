from openjdk
COPY ./src/main/java/*.java /
RUN javac /Calculator.java /Main.java
ENTRYPOINT ["java", "Main"]