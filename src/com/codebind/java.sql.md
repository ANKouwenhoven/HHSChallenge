an java library to make a connection for multible connections.

# description of what is in the library
https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html#package.description


# what to do to make a sql request

1. get a connection from the driver (i think it is best if we keep the connection)
2. sending a sql statement
3. handle response

# get a connection from the driver

the connection class handels the tcp and ssl conection

[java docs for static method](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html#getConnection-java.lang.String-)

java. sql.DriverManager.getConnection(
String url
) -> [sql.Connection](https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html)

[java docs for static method](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html#getConnection-java.lang.String-java.util.Properties-)

java. sql.DriverManager.getConnection(
String url,
[Properties](https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html "class in java.util") info
uk
) -> [sql.Connection](https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html)

[java docs for static method](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html#getConnection-java.lang.String-java.lang.String-java.lang.String-)

java. sql.DriverManager.getConnection(
String url,
String user,
String password
)-> [sql.Connection](https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html)


# make a request

## PreparedStatement
```java
import java.sql.*;
class example {
    /**
     * use this if the parameters is set by the end user. 
     */
    public ResultSet sendRequest(Connection connection, String param1) {
        PreparedStatement preparedStatement =  connection.prepareStatement("SELECT * from table WHERE somting = ?;");
        preparedStatement.setString(param1);
        return preparedStatement.executeQuery();
    }
}
```

## statement
[sql.Connection](https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html).[createStatement](https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html#createStatement--)()
```java
import java.sql.*;
class example {
    public ResultSet sendRequest(Connection connection) {
        Statement statement = connection.createStatement();
        return statement.executeQuery("sql String");
    }
}
```


# how to use [java.sql.ResultSet](https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html)

## to loop through all rows and print and print its contents
```java
import java.sql.*;

static class example {
    public void loop(ResultSet resultSet) {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String name = metaData.getColumnName(i);
                String value = resultSet.getString(i);
                System.out.print("%s = %s", name, value);
            }
        }
    }
}
```