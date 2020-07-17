package DAO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.ACCOUNT;
import BEAN.AccountActive;
import BEAN.Category;
import BEAN.Course;
import DB.DBConnection;

public class LoadProcessSystem {
	
	
				
		          //	>>>>>>>>>>>> USER <<<<<<<<<<<<<<<<<				
	
			// Xử lý chọn ra loại Account 
	public static String typeAccount(String AccountName) {
		String typeAccount = null;
		String query = "select TypeAccount FROM account " + "WHERE AccountName = ?";
		Connection conn = DBConnection.ConnectionToSql();

		try {
			PreparedStatement pptm = conn.prepareStatement(query);

			pptm.setString(1, AccountName);

			ResultSet rs = pptm.executeQuery();
			while (rs.next()) {
				typeAccount = rs.getString("TypeAccount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return typeAccount;
	}
			// Xử lý test tài khoản và mật khẩu Account hợp lệ hay ko
	public static boolean testLoginAccount(Connection conn, ACCOUNT acc, HttpServletRequest req) {
		PreparedStatement pptm = null;
		String query = "select AccountPass FROM account " + "WHERE AccountName = ?";
		boolean test = false;
		try {
			pptm = conn.prepareStatement(query);

			String AccountName = acc.getAccountName();
			String AccountPass = acc.getAccountPass();

			pptm.setString(1, AccountName);

			ResultSet rs = pptm.executeQuery();
			if (!rs.isBeforeFirst()) {
				req.setAttribute("statusAccountName", "Tài khoản không tồn tại");
			} else {
			}
			while (rs.next()) {
				String Pass = rs.getString("AccountPass");
				if (!Pass.equals(AccountPass)) {
					req.setAttribute("statusAccountPass", "Sai mật khẩu");
				} else {
					test = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return test;
	}
	
	       // lấy pass của tài khoản đó để xử lý cho phần đổi mật khẩu       
	public static String getPassword(String accountName) {
		String Pass="";
		Connection conn = DBConnection.ConnectionToSql();
		String query = "select AccountPass from account " + "WHERE AccountName = ?";
		try {
			PreparedStatement pptm =conn.prepareStatement(query);
			pptm = conn.prepareStatement(query);
			
			pptm.setString(1, accountName);
			
			ResultSet rs = pptm.executeQuery();
			while (rs.next()) {
				 Pass = rs.getString("AccountPass");
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return Pass;
	}

	
			// lấy ra tổng số dòng của mỗi bảng mà mình muốn theo nameTable
	public static int countRow(String nameTable) {
		int count = 0;
		Connection conn = DBConnection.ConnectionToSql();
		String query = "select count(*) from " + nameTable;
		try {
			PreparedStatement pptm = conn.prepareStatement(query);

			ResultSet rs = pptm.executeQuery();
			while(rs.next()) {
				String countStr = rs.getString(1);
				count = Integer.parseInt(countStr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
						
			// xử lý Đăng ký (Sign)
		public static void addAccount(Connection conn, ACCOUNT acc) {
			String query = "insert into account (UserName,AccountName,AccountPass,AccountEmail) values (?,?,?,?)";

			try {

				PreparedStatement pptm = conn.prepareStatement(query);

				String UserName = acc.getUserName();
				String AccountName = acc.getAccountName();
				String AccountPass = acc.getAccountPass();
				String AccountEmail = acc.getAccountEmail();

				pptm.setString(1, UserName);
				pptm.setString(2, AccountName);
				pptm.setString(3, AccountPass);
				pptm.setString(4, AccountEmail);

				pptm.executeUpdate();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

			
			
		
				// >>>>>>>>>>>>>>> THUỘC ADDMIN <<<<<<<<<<<<<<<
		// thêm vào thời gian login, thời gian logout của 1 account
	public static void insertTimeActive(AccountActive accActive) {
		String query = "insert into timeactive values(?,?,?,?)";
		Connection conn = DBConnection.ConnectionToSql();
		try {
			PreparedStatement pptm = conn.prepareStatement(query);
			pptm.setString(1, accActive.getAccountName());
			pptm.setString(2, accActive.getAccountPass());
			pptm.setTimestamp(3, accActive.getCreateTime());
			pptm.setTimestamp(4, accActive.getLastAccessTime());

			pptm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	    //  lấy tất cả account hiện có
	public static List<ACCOUNT> getAllAccount() {
		List<ACCOUNT> list = new ArrayList<ACCOUNT>();
		Connection conn = DBConnection.ConnectionToSql();
		String typeAccount = "normal";
		String query = "select * FROM account " + "WHERE TypeAccount = ?";
		
		try {
			PreparedStatement pptm = conn.prepareStatement(query);
			
			pptm.setString(1, typeAccount);
			
			ResultSet rs = pptm.executeQuery();
			while(rs.next()) {
				String UserName = rs.getString("UserName");
				String AccountName = rs.getString("AccountName");
				String AccountPass = rs.getString("AccountPass");
				String AccountEmail = rs.getString("AccountEmail");
				
				ACCOUNT acc = new ACCOUNT(UserName, AccountName, AccountPass, AccountEmail);
				
				list.add(acc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
		// lấy những acccount đã từng đăng nhập vào hệ thống
	public static List<AccountActive> getAccountHasLogged (){
		List<AccountActive> list = new ArrayList<AccountActive>();
		Connection conn = DBConnection.ConnectionToSql();
		String query = "SELECT * FROM timeactive where AccountName NOT IN ('addmin')";
		try {
			PreparedStatement pptm = conn.prepareStatement(query);
			ResultSet rs = pptm.executeQuery();
			while(rs.next()) {
				String AccountName = rs.getString("AccountName");
				String AccountPass = rs.getString("AccountPass");
				Timestamp createTime = rs.getTimestamp("TimeLogin");
				Timestamp lastAccessTime = rs.getTimestamp("TimeLogout");
				SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
				String createTimeStr = format.format(createTime);
				String lastAccessTimeStr = format.format(lastAccessTime);
				
				AccountActive accActive = new AccountActive(AccountName, AccountPass, createTimeStr, lastAccessTimeStr);
				
				list.add(accActive);
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return list;
}
		// lấy ra một danh sách vs đối tượng truyền vào (object) tùy vào người quyết định 
		// xữ lý Search theo NameAccount 
	public static List  find(Object obj, String nameSearch){
		String query ="";
		String special ="";
		List list = new ArrayList();
		nameSearch= "%"+nameSearch+"%";
		Connection conn = DBConnection.ConnectionToSql();
		if(obj instanceof AccountActive) {
			special="active";
			 query = "SELECT * FROM timeactive where AccountName NOT IN ('addmin') AND AccountName LIKE ?" ;
		}else if(obj instanceof ACCOUNT) {
			special="detail";
			 query = "select * FROM account WHERE TypeAccount ='normal' AND AccountName LIKE ?";
		}
		try {
			PreparedStatement pptm = conn.prepareStatement(query);
			 
			pptm.setString(1, nameSearch);
			
			ResultSet rs = pptm.executeQuery();
			while(rs.next()) {
			if(special.equals("active")){
				String AccountName = rs.getString("AccountName");
				String AccountPass = rs.getString("AccountPass");
				Timestamp createTime = rs.getTimestamp("TimeLogin");
				Timestamp lastAccessTime = rs.getTimestamp("TimeLogout");
				SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
				String createTimeStr = format.format(createTime);
				String lastAccessTimeStr = format.format(lastAccessTime);
				
				AccountActive accActive = new AccountActive(AccountName, AccountPass, createTimeStr, lastAccessTimeStr);
				
				list.add(accActive);
			}else {
				String UserName = rs.getString("UserName");
				String AccountName = rs.getString("AccountName");
				String AccountPass = rs.getString("AccountPass");
				String AccountEmail = rs.getString("AccountEmail");
				
				ACCOUNT acc = new ACCOUNT(UserName, AccountName, AccountPass, AccountEmail);
				
				list.add(acc);
			}
			}
		} catch(Exception e){

		}
		return list;
	}
	    // xữ lý Delete theo NameAccount 
	public static void  deleteAccount(String AccountName) {
		Connection conn = DBConnection.ConnectionToSql();
		String query ="DELETE FROM account WHERE AccountName = ?";
		try {
			PreparedStatement pptm = conn.prepareStatement(query);
			pptm.setString(1, AccountName);
			
			pptm.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String countCourse() {
		String number = null;
		Connection conn = DBConnection.ConnectionToSql();
		String query ="SELECT COUNT(*) FROM course";
		try {
			PreparedStatement pptm = conn.prepareStatement(query);
			
			
			ResultSet rs = pptm.executeQuery();
			while(rs.next()) {
				number = rs.getString(1);
			}
			
	}catch (SQLException e) {
		e.printStackTrace();
	}
		return number;
	}
	    //	xữ lý Search theo NameCourse 
	public static List searchCourse(String courseName) {
		List listNameCourse = new ArrayList();
		courseName= "%"+courseName+"%";
		Connection conn = DBConnection.ConnectionToSql();
		String query = "SELECT CourseName FROM course WHERE CourseName LIKE ? LIMIT 7";
		try {
			PreparedStatement pptm = conn.prepareStatement(query);
			 
			pptm.setString(1, courseName);
			
			ResultSet rs = pptm.executeQuery();
			while(rs.next()) {
				String CourseName = rs.getString("CourseName");
				
				listNameCourse.add(CourseName);
			}
	} catch(Exception e){

	}
		return listNameCourse;
	
}
			