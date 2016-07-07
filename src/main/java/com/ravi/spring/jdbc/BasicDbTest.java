/**
 * 
 */
package com.ravi.spring.jdbc;

/**
 * 
 */

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ravi.bank.domain.service.jpa.entities.TransactionType;
import com.ravi.bank.domain.service.jpa.entities.TransactionTypeImpl;


/**
 * A sanity check using no Spring / JPA / Hibernate
 * This way we know if something is actually wrong with the 
 * Frameworks configuration / query or not.
 * 
 * @author Ravi Aghera
 *
 */
public class BasicDbTest {


	//private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_DRIVER =  "oracle.jdbc.pool.OracleDataSource";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_USER = "RAVI_BANK";
	private static final String DB_PASSWORD = "RAVI_BANK";

	public List<TransactionType> runQuery() throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		List<TransactionType> transTypeList = new ArrayList<TransactionType>();

		String sql = "SELECT TRANSACTION_TYPE_ID, TRANSACTION_TYPE_NAME FROM TRANSACTION_TYPE";

		//String sql = "INSERT INTO ravi_bank.transaction_type(transaction_type_id, transaction_type_name) values(?,?)";

		try {

			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(sql);
			//			preparedStatement.setInt(1, 3);
			//			preparedStatement.setString(2, "dummy");

			System.out.println("executing query:  " + sql);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				TransactionType transType = new TransactionTypeImpl();
				System.out.println("results received " + rs);

				Long id = rs.getLong("TRANSACTION_TYPE_ID");
				String name = rs.getString("TRANSACTION_TYPE_NAME");
				transType.setTransactionTypeId(id);
				transType.setTransactionTypeName(name);

				System.out.println("id : " + id);
				System.out.println("name : " + name);
				
				transTypeList.add(transType);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

		return transTypeList;
	}

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(
					DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

}
