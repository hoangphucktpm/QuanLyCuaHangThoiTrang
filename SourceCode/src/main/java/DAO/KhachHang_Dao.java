package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectDatabase;
import Entity.KhachHang;

import Entity.LoaiKhachHang;
import Entity.LoaiSanPham;

public class KhachHang_Dao {
	
	public KhachHang_Dao() {
		ConnectDatabase.getInstance().connect();
	}

	/**
	 * add Khachhang
	 * 
	 * @param kh
	 * @return true/false
	 */
	@SuppressWarnings("static-access")
	
	public boolean them(String ten,String sdt, String CCCD,Date ngaySinh, String diaChi,int gioiTinh, String loaiKH,String email, float dtluy, String ma) {
		Connection con = ConnectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			if (isPhoneNumberExists(con, sdt)) {
		           
	            return false;
	        }
			stmt = con.prepareStatement("insert into KhachHang (TenKH, Sdt, CCCD, NgaySinh, DiaChi, GioiTinh, LoaiKH, Email, diemTichLuy, MaKH) \n"
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
			java.sql.Date birth = new java.sql.Date(ngaySinh.getTime());
			stmt.setString(10, ma);
			stmt.setString(1, ten);
			stmt.setString(2, sdt);
			stmt.setString(3, CCCD);
			stmt.setDate(4, birth);
			stmt.setString(5, diaChi);
			stmt.setInt(6, gioiTinh);
			stmt.setString(7, loaiKH);
			stmt.setString(8, email);
			stmt.setFloat(9, dtluy);
			stmt.executeUpdate();
		} catch (Exception e3) {
			// TODO: handle exception
		}
		return true;
	}
	private boolean isPhoneNumberExists(Connection con, String sdt) throws SQLException {
	    PreparedStatement checkStmt = null;
	    ResultSet rs = null;

	   
	        // Query to check if the phone number already exists
	        String checkQuery = "select count(*) from KhachHang where Sdt = ?";
	        checkStmt = con.prepareStatement(checkQuery);
	        checkStmt.setString(1, sdt);
	        rs = checkStmt.executeQuery();

	        if (rs.next() && rs.getInt(1) > 0) {
	            // Phone number already exists
	            return true;
	        }
	   

	    return false;
	}
	public boolean sua(String ten,String sdt, String CCCD,Date ngaySinh, String diaChi,int gioiTinh, String loaiKH,String email,float diemTichLuy, String ma)
	{
		Connection con = ConnectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n= 0;
		try {
			String sql = "update KhachHang set TenKH = ?,Sdt = ?,CCCD = ?,NgaySinh = ?,DiaChi = ?,GioiTinh = ?,LoaiKH = ?,Email = ?,diemTichLuy = ? where MaKH = ?";
			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ten);
			stmt.setString(2, sdt);
			stmt.setString(3, CCCD);
			stmt.setDate(4, ngaySinh);
			stmt.setString(5, diaChi);
			stmt.setInt(6, gioiTinh);
			stmt.setString(7, loaiKH);
			stmt.setString(8, email);
			stmt.setFloat(9, diemTichLuy);
			stmt.setString(10, ma);
			n = stmt.executeUpdate();
		} catch (Exception e3) {
			// TODO: handle exception
		}
		return n > 0;
	}

	/**
	 * get all KhachHang
	 * 
	 * @return List<KhachHang>
	 * @throws Exception
	 */

	@SuppressWarnings("static-access")
	public List<KhachHang> getAllKhachHang(){
		List<KhachHang> result = new ArrayList<>();
		
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select * from KhachHang";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String ten = rs.getString("TenKH");
				String sdt = rs.getString("Sdt");
				String cCCD = rs.getString("CCCD");
				Date ngaySinh = rs.getDate("NgaySinh");
				String diaChi = rs.getString("DiaChi");
				int gioiTinh = rs.getInt("GioiTinh");
				String loaiKH = rs.getString("LoaiKH");
				String email = rs.getString("Email");
				Float dTichLuy = rs.getFloat("diemTichLuy");
				KhachHang Kh = new KhachHang(maKH, ten, sdt, cCCD, ngaySinh, diaChi, gioiTinh,new LoaiKhachHang(loaiKH) , email,dTichLuy);
				result.add(Kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
//lay rqa diem tich luy
	public float getDiem(String maKH){
		float result = 0;
		
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select diemTichLuy from KhachHang where MaKH = " + maKH;
			PreparedStatement preStm = con.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();

			while (rs.next()) {
				result = rs.getFloat(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String getMaLoaiKH(String maKH){
		String result = "";
		
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select LoaiKH from KhachHang where MaKH =" + maKH;
			PreparedStatement preStm = con.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();

			while (rs.next()) {
				String Loaikh = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String getTenLoaiKH(String maKH){
		String result = "";
		
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select TenLoaiKH from KhachHang k join loaiKhachHang lkh on k.LoaiKH = lkh.MaLoaiKH\n"
					+ "  where MaKH like '%" + maKH + "%'";
			PreparedStatement preStm = con.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();

			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}	
	public void updateLoaiKH() {
	    try {
	        Connection con = ConnectDatabase.getInstance().getConnection();
	        String sql = "update KhachHang set LoaiKH = '002' where diemTichLuy >= 50000";
	        PreparedStatement preStm = con.prepareStatement(sql);
	        preStm.executeUpdate();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}	
	
	public KhachHang getMa(String MaKHang) {
		KhachHang list = new KhachHang();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select * from [dbo].[KhachHang] where MaKH like N'%" + MaKHang + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String ten = rs.getString("TenKH");
				String sdt = rs.getString("Sdt");
				String cCCD = rs.getString("CCCD");
				Date ngaySinh = rs.getDate("NgaySinh");
				String diaChi = rs.getString("DiaChi");
				int gioiTinh = rs.getInt("GioiTinh");
				String loaiKH = rs.getString("LoaiKH");
				LoaiKhachHang LKH = new LoaiKhachHang();
				LKH.setTenLoai(loaiKH);
				String email = rs.getString("Email");
				Float dTichLuy = rs.getFloat("diemTichLuy");
				KhachHang kh = new KhachHang(maKH, ten, sdt, cCCD, ngaySinh, diaChi, gioiTinh,new LoaiKhachHang(loaiKH) , email,dTichLuy);
				list = kh;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public KhachHang getTen(String TenKHang) {
		KhachHang list = new KhachHang();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select * from [dbo].[KhachHang] where TenKH like N'%" + TenKHang + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String ten = rs.getString("TenKH");
				String sdt = rs.getString("Sdt");
				String cCCD = rs.getString("CCCD");
				Date ngaySinh = rs.getDate("NgaySinh");
				String diaChi = rs.getString("DiaChi");
				int gioiTinh = rs.getInt("GioiTinh");
				String loaiKH = rs.getString("LoaiKH");
				LoaiKhachHang LKH = new LoaiKhachHang();
				LKH.setTenLoai(loaiKH);
				String email = rs.getString("Email");
				Float dTichLuy = rs.getFloat("diemTichLuy");
				KhachHang kh = new KhachHang(maKH, ten, sdt, cCCD, ngaySinh, diaChi, gioiTinh,new LoaiKhachHang(loaiKH) , email,dTichLuy);
				list = kh;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public KhachHang getLoaiKH(String LKHang) {
		KhachHang list = new KhachHang();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select * from [dbo].[KhachHang] kh join loaiKhachHang lkh on kh.LoaiKH = lkh.MaLoaiKH\n"
					+ "where LoaiKH ='%" + LKHang + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String ten = rs.getString("TenKH");
				String sdt = rs.getString("Sdt");
				String cCCD = rs.getString("CCCD");
				Date ngaySinh = rs.getDate("NgaySinh");
				String diaChi = rs.getString("DiaChi");
				int gioiTinh = rs.getInt("GioiTinh");
				String loaiKH = rs.getString("LoaiKH");
				LoaiKhachHang LKH = new LoaiKhachHang();
				LKH.setMaLoai(loaiKH);
				String email = rs.getString("Email");
				Float dTichLuy = rs.getFloat("diemTichLuy");
				KhachHang kh = new KhachHang(maKH, ten, sdt, cCCD, ngaySinh, diaChi, gioiTinh,new LoaiKhachHang(loaiKH) , email,dTichLuy);
				list = kh;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<KhachHang> getGT(int GT) {
		
		List<KhachHang> list= new ArrayList<>();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select * from [dbo].[KhachHang] where GioiTinh = " + GT;
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String ten = rs.getString("TenKH");
				String sdt = rs.getString("Sdt");
				String cCCD = rs.getString("CCCD");
				Date ngaySinh = rs.getDate("NgaySinh");
				String diaChi = rs.getString("DiaChi");
				int gioiTinh = rs.getInt("GioiTinh");
				String loaiKH = rs.getString("LoaiKH");
				LoaiKhachHang LKH = new LoaiKhachHang();
				LKH.setTenLoai(loaiKH);
				String email = rs.getString("Email");
				Float dTichLuy = rs.getFloat("diemTichLuy");
				KhachHang kh = new KhachHang(maKH, ten, sdt, cCCD, ngaySinh, diaChi, gioiTinh,new LoaiKhachHang(loaiKH) , email,dTichLuy);
				list.add(kh);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
public List<KhachHang> getlistLKH(String lkh) {
		
		List<KhachHang> list= new ArrayList<>();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select MaKH,TenKH,[Sdt],[CCCD],[NgaySinh],[DiaChi],[GioiTinh],TenLoaiKH,[Email],[diemTichLuy]\n"
					+ "from [dbo].[KhachHang] kh join loaiKhachHang lkh on kh.LoaiKH = lkh.MaLoaiKH where TenLoaiKH like N'%" + lkh + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String ten = rs.getString("TenKH");
				String sdt = rs.getString("Sdt");
				String cCCD = rs.getString("CCCD");
				Date ngaySinh = rs.getDate("NgaySinh");
				String diaChi = rs.getString("DiaChi");
				int gioiTinh = rs.getInt("GioiTinh");
				String loaiKH = rs.getString("TenLoaiKH");
				LoaiKhachHang LKH = new LoaiKhachHang();
				LKH.setTenLoai(loaiKH);
				String email = rs.getString("Email");
				Float dTichLuy = rs.getFloat("diemTichLuy");
				KhachHang kh = new KhachHang(maKH, ten, sdt, cCCD, ngaySinh, diaChi, gioiTinh,new LoaiKhachHang(loaiKH) , email,dTichLuy);
				list.add(kh);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//lấy số lượng ctkm
		public int soLuongKH(){
			int sum = 0;
			try {
				Connection con = ConnectDatabase.getInstance().getConnection();
				String sql="select count(*) from [dbo].KhachHang";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					sum = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sum;
		}
//		Lấy danh sách loại khách hàng 
		public List<LoaiKhachHang> getLoaiKH (){
			List<LoaiKhachHang> list = new ArrayList<LoaiKhachHang>();
			try {
				Connection con = ConnectDatabase.getInstance().getConnection();
				String sql="select *\r\n" + 
						"from [dbo].[loaiKhachHang]";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while(rs.next()) {
					String maLoai= rs.getString(1);
					String tenLoai = rs.getString(2);
					LoaiKhachHang l = new LoaiKhachHang(maLoai, tenLoai);
					list.add(l);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return list;
		}
		public List<LoaiKhachHang> getALLLoaiKH(){
			List<LoaiKhachHang> result = new ArrayList<>();
			
			try {
				Connection con = ConnectDatabase.getInstance().getConnection();
				String sql="select * from loaiKhachHang";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					String maLoai = rs.getString(1);
					String tenLoai = rs.getString(2);
					LoaiKhachHang lkh = new LoaiKhachHang(maLoai, tenLoai);
					result.add(lkh);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

	
}
