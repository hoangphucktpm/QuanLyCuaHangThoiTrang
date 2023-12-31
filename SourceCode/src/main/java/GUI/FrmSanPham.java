package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import DAO.KhuyenMai_Dao;
import DAO.SanPham_Dao;
import Entity.ChatLieu;
import Entity.KhachHang;
import Entity.KhuyenMai;
import Entity.LoaiKhachHang;
import Entity.LoaiSanPham;
import Entity.NhaCungCap;
import Entity.sanPham;
import Entity.sanPham.MauSac;
import Entity.sanPham.Size;

import javax.swing.border.EtchedBorder;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.Container;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Label;

public class FrmSanPham extends JFrame implements ActionListener, MouseListener, DocumentListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtGiaNhap;
	private JComboBox cboColor;
	private JTextField txtSoLuong;
	private JComboBox cboSize;
	private JTextField txtDonGia;
	private JTextField txtGiaDau;
	private JTextField txtGiaSau;
	private JLabel lblTieuDeTrang;
	private JPanel pnlTieuDe;
	private JPanel pnlthongTin;
	private JLabel lblMaSP;
	private JLabel lblLoaiSP;
	private JLabel lblTenSP;
	private JDateChooser txtChonNgaynhap;
	private Component lblColor;
	private JLabel lblSoLuong;
	private JLabel lblNgayNhap;
	private JLabel lblSize;
	private JLabel lblKhuyenMai;
	private JPanel pnlChucNang;
	private JLabel label;
	private ButtonGroup buttonGroupVAT;
	private JRadioButton rdKhongVAT;
	private JRadioButton rdCoVAT;
	private JButton btnSua;
	private JButton btnThem;
	private JButton btnLammoi;
	private JLabel lblNhaCungCap;
	private JLabel lblDonGia;
	private Container lblHinhanh;
	private JRadioButton rdHetHang;
	private JRadioButton rdConHang;
	private ButtonGroup buttonGroupTT;
	private Container lblTinhTrang;
	private JLabel bltinhtrang;
	private JLabel lblChatLieu;
	private JLabel lblDonViTinh;
	private Container lblVAT;
	private JComboBox cboDVT;
	private JComboBox cboChatLieu;
	private JComboBox cboNhaCungCap;
	private JComboBox cboLoaiSP;
	private JButton btnThemLoaiSP;
	private JButton btnChatLieu;
	private JButton btnThemDonVi;
	private JPanel panel_DSSP;
	private JButton btnTimkiem;
	private JButton btnLammoithanh;
	private JLabel lblGiaDau;
	private JLabel lblGiaSau;
	private JComboBox cboTimKiem;
	private DefaultTableModel tablemodel;
	private JTable tblDSSP;
	private boolean chkThem = false;
	private boolean chkSua = false;
	private List<String> listTimNCC = new ArrayList<>();
	private SanPham_Dao dao = new SanPham_Dao();
	private KhuyenMai_Dao daoKM = new KhuyenMai_Dao();

	List<LoaiSanPham> listSP = dao.getLoaiSP();
	List<ChatLieu> listCL = dao.getChatLieu();
	List<NhaCungCap> listNCC = dao.getTenNCC();
	List<KhuyenMai> listKM = dao.getKMTheoTen();

	private JRadioButton rdTimChatLieu;
	private JRadioButton rdTimMau;
	private JRadioButton rdTimSize;
	private JRadioButton rdTimGia;
	private JLabel lblNhapThongTin;
	private JRadioButton rdTimMa;
	private JRadioButton rdTen;
	private JRadioButton rdTimLoai;
	private JRadioButton rdTimNCC;
	private ButtonGroup buttonGroupTK;
	private JButton btbAnh;
	private String filename = null;
	private byte[] imageThuoc;
	private String folderName;
	private JButton btnLuu;
	private boolean lock = false;

	DecimalFormat tien = new DecimalFormat("#.##");
	private JTextField txtKhuyenMai;
	private JLabel lblLoi;
	private JLabel lblLoi_Ten;
	private JLabel lblLoi_GiaNhap;
	private JLabel lblLoi_SoLuong;
	private JLabel lblLoi_Ngay;
	private JLabel lblGiaNhap;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSanPham frame = new FrmSanPham();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmSanPham() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1347, 843);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(new Color(0, 255, 255));
		pnlTieuDe.setBounds(0, 0, 1343, 43);
		contentPane.add(pnlTieuDe);
		pnlTieuDe.setLayout(null);
		lblTieuDeTrang = new JLabel("QUẢN LÝ SẢN PHẨM");
		lblTieuDeTrang.setBounds(512, 12, 305, 25);
		pnlTieuDe.add(lblTieuDeTrang);
		lblTieuDeTrang.setBackground(new Color(0, 255, 255));
		lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

		pnlthongTin = new JPanel();
		pnlthongTin.setBackground(new Color(255, 255, 255));
		pnlthongTin.setLayout(null);
		pnlthongTin.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Thi\u1EBFt l\u1EADp th\u00F4ng tin s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnlthongTin.setBounds(20, 48, 1285, 326);
		contentPane.add(pnlthongTin);

		label = new JLabel(" ");
		label.setBackground(new Color(0, 0, 255));
		label.setBounds(773, 146, 171, 129);
		pnlthongTin.add(label);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		label.setOpaque(true);
		label.setBackground(Color.WHITE);

		lblMaSP = new JLabel("Mã sản phẩm");
		lblMaSP.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblMaSP.setBounds(10, 28, 114, 12);
		pnlthongTin.add(lblMaSP);

		txtMaSP = new JTextField(deFaultID());
		txtMaSP.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtMaSP.setEditable(false);
		txtMaSP.setColumns(10);
		txtMaSP.setBounds(128, 28, 204, 20);
		pnlthongTin.add(txtMaSP);

		lblLoaiSP = new JLabel("Loại sản phẩm");
		lblLoaiSP.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoaiSP.setBounds(342, 28, 71, 12);
		pnlthongTin.add(lblLoaiSP);

		lblTenSP = new JLabel("Tên sản phẩm");
		lblTenSP.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTenSP.setBounds(10, 79, 96, 15);
		pnlthongTin.add(lblTenSP);

		txtTenSP = new JTextField();
		txtTenSP.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(128, 77, 204, 20);
		pnlthongTin.add(txtTenSP);

		lblGiaNhap = new JLabel("Giá nhập");
		lblGiaNhap.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblGiaNhap.setBounds(10, 124, 96, 13);
		pnlthongTin.add(lblGiaNhap);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtGiaNhap.setColumns(10);
		txtGiaNhap.setBounds(128, 124, 204, 20);
		pnlthongTin.add(txtGiaNhap);

		lblColor = new JLabel("Màu sắc");
		lblColor.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblColor.setBounds(10, 262, 45, 13);
		pnlthongTin.add(lblColor);

		cboColor = new JComboBox();
		cboColor.setBounds(128, 259, 204, 20);
		pnlthongTin.add(cboColor);

		lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblSoLuong.setBounds(10, 165, 45, 13);
		pnlthongTin.add(lblSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(128, 165, 204, 20);
		pnlthongTin.add(txtSoLuong);

		lblNgayNhap = new JLabel("Ngày nhập");
		lblNgayNhap.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNgayNhap.setBounds(342, 170, 114, 13);
		pnlthongTin.add(lblNgayNhap);

		txtChonNgaynhap = new JDateChooser();
		txtChonNgaynhap.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtChonNgaynhap.setBounds(450, 165, 202, 21);
		txtChonNgaynhap.setForeground(new Color(0, 0, 0));
		txtChonNgaynhap.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtChonNgaynhap.setLocale(new Locale("vi", "VN"));
		txtChonNgaynhap.setDateFormatString("dd/MM/yyyy");
		txtChonNgaynhap.setDate(new Date(System.currentTimeMillis()));

		pnlthongTin.add(txtChonNgaynhap);

		lblSize = new JLabel("Size");
		lblSize.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblSize.setBounds(10, 212, 108, 13);
		pnlthongTin.add(lblSize);

		cboSize = new JComboBox();
		cboSize.setBounds(128, 211, 204, 20);
		pnlthongTin.add(cboSize);

		lblKhuyenMai = new JLabel("Khuyến mãi");
		lblKhuyenMai.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblKhuyenMai.setBounds(342, 212, 108, 13);
		pnlthongTin.add(lblKhuyenMai);
//		cbokhuyenmai.setEditable(false);

		pnlChucNang = new JPanel();
		pnlChucNang.setBackground(new Color(255, 255, 255));
		pnlChucNang.setLayout(null);
		pnlChucNang.setBorder(
				new TitledBorder(null, "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChucNang.setBounds(1011, 75, 241, 189);
		pnlthongTin.add(pnlChucNang);

		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThem.setBackground(Color.CYAN);
		btnThem.setBounds(49, 24, 160, 30);
		btnThem.setIcon(new ImageIcon("Anh\\them.png"));
		pnlChucNang.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSua.setBackground(Color.CYAN);
		btnSua.setBounds(49, 64, 160, 30);
		btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
		pnlChucNang.add(btnSua);

		btnLammoi = new JButton("Làm mới");
		btnLammoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLammoi.setBackground(Color.CYAN);
		btnLammoi.setBounds(49, 104, 160, 30);
		btnLammoi.setIcon(new ImageIcon("Anh\\lammoi.png"));
		pnlChucNang.add(btnLammoi);

		btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLuu.setBackground(new Color(0, 255, 255));
		btnLuu.setBounds(49, 144, 160, 30);
		btnLuu.setIcon(new ImageIcon("Anh\\luu.png"));
		pnlChucNang.add(btnLuu);

		lblNhaCungCap = new JLabel("Nhà cung cấp");
		lblNhaCungCap.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNhaCungCap.setBounds(342, 78, 71, 13);
		pnlthongTin.add(lblNhaCungCap);

		lblDonGia = new JLabel(" Đơn giá");
		lblDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblDonGia.setBounds(342, 128, 71, 13);
		pnlthongTin.add(lblDonGia);

		lblHinhanh = new JLabel("Hình ảnh");
		lblHinhanh.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblHinhanh.setBounds(698, 146, 61, 13);
		pnlthongTin.add(lblHinhanh);

		rdHetHang = new JRadioButton("Hết");
		rdHetHang.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdHetHang.setBackground(new Color(255, 255, 255));
		rdHetHang.setBounds(589, 259, 103, 20);
		pnlthongTin.add(rdHetHang);

		rdConHang = new JRadioButton("Còn");
		rdConHang.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdConHang.setBackground(new Color(255, 255, 255));
		rdConHang.setBounds(450, 259, 132, 20);
		pnlthongTin.add(rdConHang);

		buttonGroupTT = new ButtonGroup();

		// Thêm các nút radio vào ButtonGroup
		buttonGroupTT.add(rdHetHang);
		buttonGroupTT.add(rdConHang);

		lblTinhTrang = new JLabel("Tình trạng");
		lblTinhTrang.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTinhTrang.setBounds(342, 259, 71, 16);
		pnlthongTin.add(lblTinhTrang);

		lblChatLieu = new JLabel("Chất liệu");
		lblChatLieu.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblChatLieu.setBounds(698, 29, 108, 13);
		pnlthongTin.add(lblChatLieu);

		lblDonViTinh = new JLabel("Đơn vị tính");
		lblDonViTinh.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblDonViTinh.setBounds(698, 78, 108, 13);
		pnlthongTin.add(lblDonViTinh);

		lblVAT = new JLabel("VAT");
		lblVAT.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblVAT.setBounds(698, 124, 35, 13);
		pnlthongTin.add(lblVAT);

		cboDVT = new JComboBox();
		cboDVT.setBounds(771, 76, 171, 20);
		pnlthongTin.add(cboDVT);

		cboChatLieu = new JComboBox();
		cboChatLieu.setBounds(771, 24, 171, 20);
		pnlthongTin.add(cboChatLieu);

		cboLoaiSP = new JComboBox();
		cboLoaiSP.setBounds(450, 24, 171, 20);
		pnlthongTin.add(cboLoaiSP);

		cboNhaCungCap = new JComboBox();
		cboNhaCungCap.setBounds(450, 71, 204, 20);
		pnlthongTin.add(cboNhaCungCap);

		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(450, 124, 204, 20);
		txtDonGia.setEditable(false);
		pnlthongTin.add(txtDonGia);

		btnThemLoaiSP = new JButton("");
		btnThemLoaiSP.setIcon(new ImageIcon("Anh\\them.png"));
		btnThemLoaiSP.setBounds(624, 20, 30, 30);
		pnlthongTin.add(btnThemLoaiSP);

		btnChatLieu = new JButton("");
		btnChatLieu.setIcon(new ImageIcon("Anh\\them.png"));
		btnChatLieu.setBounds(945, 20, 30, 30);
		pnlthongTin.add(btnChatLieu);

		btnThemDonVi = new JButton("");
		btnThemDonVi.setIcon(new ImageIcon("Anh\\them.png"));
		btnThemDonVi.setBounds(945, 71, 30, 30);
		pnlthongTin.add(btnThemDonVi);

		rdCoVAT = new JRadioButton("Có");
		rdCoVAT.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdCoVAT.setBackground(new Color(255, 255, 255));
		rdCoVAT.setBounds(773, 120, 61, 20);
		pnlthongTin.add(rdCoVAT);

		rdKhongVAT = new JRadioButton("Không");
		rdKhongVAT.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdKhongVAT.setBackground(new Color(255, 255, 255));
		rdKhongVAT.setBounds(852, 120, 71, 20);
		pnlthongTin.add(rdKhongVAT);

		buttonGroupVAT = new ButtonGroup();

		// Thêm các nút radio vào ButtonGroup
		buttonGroupVAT.add(rdCoVAT);
		buttonGroupVAT.add(rdKhongVAT);

		btbAnh = new JButton("Chọn ảnh");
		btbAnh.setBounds(790, 285, 132, 31);
		btbAnh.setBackground(new Color(0, 255, 255));
		btbAnh.setIcon(new ImageIcon("Anh\\chonanh.png"));
		pnlthongTin.add(btbAnh);
		btbAnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		panel_DSSP = new JPanel();
		panel_DSSP.setBackground(new Color(255, 255, 255));
		panel_DSSP.setLayout(null);
		panel_DSSP.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_DSSP.setBounds(20, 384, 1285, 395);
		contentPane.add(panel_DSSP);

		btnTimkiem = new JButton("Tìm kiếm");
		btnTimkiem.setBackground(Color.CYAN);
		btnTimkiem.setIcon(new ImageIcon("Anh\\timkiem.png"));
		btnTimkiem.setBounds(1018, 46, 114, 30);
		panel_DSSP.add(btnTimkiem);

		btnLammoithanh = new JButton("Làm mới");
		btnLammoithanh.setIcon(new ImageIcon("Anh\\lammoi.png"));
		btnLammoithanh.setBackground(Color.CYAN);
		btnLammoithanh.setBounds(1142, 46, 114, 30);
		panel_DSSP.add(btnLammoithanh);

		txtGiaDau = new JTextField();
		txtGiaDau.setColumns(10);
		txtGiaDau.setBounds(793, 23, 190, 20);
		panel_DSSP.add(txtGiaDau);

		lblGiaDau = new JLabel("Giá từ");
		lblGiaDau.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblGiaDau.setBounds(693, 29, 96, 13);
		panel_DSSP.add(lblGiaDau);

		txtGiaSau = new JTextField();
		txtGiaSau.setColumns(10);
		txtGiaSau.setBounds(793, 51, 190, 20);
		panel_DSSP.add(txtGiaSau);

		lblGiaSau = new JLabel("Đến");
		lblGiaSau.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblGiaSau.setBounds(693, 54, 96, 13);
		panel_DSSP.add(lblGiaSau);

		cboTimKiem = new JComboBox();
		cboTimKiem.setBounds(160, 23, 483, 20);
		panel_DSSP.add(cboTimKiem);

		JScrollPane scrDSSP;
		String[] tb1 = new String[] { "STT", "Mã SP", "Tên SP", "Loại SP", "Giá nhập", "Số lượng", "Ngày nhập",
				"Nhà cung cấp", "Chất liệu", "Size", "Màu sắc", " Đơn vị tính", "Khuyến mãi", "VAT", "Tình trạng",
				"Giá bán" };
		tablemodel = new DefaultTableModel(tb1, 0);
		tblDSSP = new JTable(tablemodel);

		tblDSSP.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		tblDSSP.setBackground(new Color(224, 255, 255));
		tblDSSP.setForeground(new Color(0, 0, 0));
		getContentPane().add(scrDSSP = new JScrollPane(tblDSSP, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
		scrDSSP.setBounds(10, 104, 1265, 281);
		panel_DSSP.add(scrDSSP);
		scrDSSP.setPreferredSize(new Dimension(0, 250));

		lblNhapThongTin = new JLabel("Nhập thông tin tìm kiếm:");
		lblNhapThongTin.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNhapThongTin.setBounds(10, 24, 140, 15);
		panel_DSSP.add(lblNhapThongTin);

		JLabel lblTimTheo = new JLabel("Tìm theo:");
		lblTimTheo.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblTimTheo.setBounds(10, 52, 99, 15);
		panel_DSSP.add(lblTimTheo);

		rdTimMa = new JRadioButton("Mã sản phẩm");
		rdTimMa.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdTimMa.setBackground(Color.WHITE);
		rdTimMa.setBounds(86, 46, 116, 21);
		panel_DSSP.add(rdTimMa);

		rdTen = new JRadioButton("Tên sản phẩm");
		rdTen.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdTen.setBackground(Color.WHITE);
		rdTen.setBounds(198, 46, 134, 21);
		panel_DSSP.add(rdTen);

		rdTimLoai = new JRadioButton("Loại sản phẩm");
		rdTimLoai.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdTimLoai.setBackground(Color.WHITE);
		rdTimLoai.setBounds(328, 46, 141, 21);
		panel_DSSP.add(rdTimLoai);

		rdTimNCC = new JRadioButton("Nhà cung cấp");
		rdTimNCC.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdTimNCC.setBackground(Color.WHITE);
		rdTimNCC.setBounds(506, 46, 137, 21);
		panel_DSSP.add(rdTimNCC);

		rdTimChatLieu = new JRadioButton("Chất liệu");
		rdTimChatLieu.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdTimChatLieu.setBackground(Color.WHITE);
		rdTimChatLieu.setBounds(86, 70, 116, 21);
		panel_DSSP.add(rdTimChatLieu);

		rdTimMau = new JRadioButton("Màu sắc");
		rdTimMau.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdTimMau.setBackground(Color.WHITE);
		rdTimMau.setBounds(198, 70, 134, 21);
		panel_DSSP.add(rdTimMau);

		rdTimSize = new JRadioButton("Size");
		rdTimSize.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdTimSize.setBackground(Color.WHITE);
		rdTimSize.setBounds(328, 70, 143, 21);
		panel_DSSP.add(rdTimSize);

		rdTimGia = new JRadioButton("Theo giá");
		rdTimGia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdTimGia.setBackground(Color.WHITE);
		rdTimGia.setBounds(506, 70, 139, 21);
		panel_DSSP.add(rdTimGia);
		txtGiaDau.setEditable(false);
		txtGiaSau.setEditable(false);

		buttonGroupTK = new ButtonGroup();
		buttonGroupTK.add(rdTimMa);
		buttonGroupTK.add(rdTimLoai);
		buttonGroupTK.add(rdTen);
		buttonGroupTK.add(rdTimChatLieu);
		buttonGroupTK.add(rdTimNCC);
		buttonGroupTK.add(rdTimGia);
		buttonGroupTK.add(rdTimMau);
		buttonGroupTK.add(rdTimSize);

		docDuLieu();
		updateComboBox();

		khoaTXT(lock);

		btnThem.addActionListener(this);
		btnLammoi.addActionListener(this);
		btnThemLoaiSP.addActionListener(this);
		btnSua.addActionListener(this);
		btnTimkiem.addActionListener(this);
		btnLammoithanh.addActionListener(this);
		btbAnh.addActionListener(this);
		btnLuu.addActionListener(this);
		btnLuu.setEnabled(false);

		txtKhuyenMai = new JTextField();
		txtKhuyenMai.setEditable(false);
		txtKhuyenMai.setColumns(10);
		txtKhuyenMai.setBounds(450, 211, 204, 20);
		pnlthongTin.add(txtKhuyenMai);

		lblLoi = new JLabel("");
		lblLoi.setForeground(Color.RED);
		lblLoi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLoi.setBounds(128, 296, 532, 20);
		pnlthongTin.add(lblLoi);

		lblLoi_Ten = new JLabel("");
		lblLoi_Ten.setForeground(Color.RED);
		lblLoi_Ten.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLoi_Ten.setBounds(128, 101, 204, 20);
		pnlthongTin.add(lblLoi_Ten);

		lblLoi_GiaNhap = new JLabel("");
		lblLoi_GiaNhap.setForeground(Color.RED);
		lblLoi_GiaNhap.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLoi_GiaNhap.setBounds(128, 145, 204, 20);
		pnlthongTin.add(lblLoi_GiaNhap);

		lblLoi_SoLuong = new JLabel("");
		lblLoi_SoLuong.setForeground(Color.RED);
		lblLoi_SoLuong.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLoi_SoLuong.setBounds(128, 188, 204, 20);
		pnlthongTin.add(lblLoi_SoLuong);

		lblLoi_Ngay = new JLabel("");
		lblLoi_Ngay.setForeground(Color.RED);
		lblLoi_Ngay.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLoi_Ngay.setBounds(448, 188, 204, 20);
		pnlthongTin.add(lblLoi_Ngay);

//		Có thuế
		rdCoVAT.addActionListener(this);
//		Không có thuế
		rdKhongVAT.addActionListener(this);
		btnChatLieu.addActionListener(this);
		btnThemDonVi.addActionListener(this);
		tblDSSP.addMouseListener(this);
		txtGiaNhap.addKeyListener(this);
		txtSoLuong.addKeyListener(this);
		txtGiaDau.addKeyListener(this);
		txtGiaSau.addKeyListener(this);
//		upcombobox tim
		rdTimMa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateCBBoxTim();
			}
		});
		rdTen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateCBBoxTim();
			}
		});
		rdTimLoai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateCBBoxTim();
			}
		});
		rdTimNCC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateCBBoxTim();
			}
		});
		rdTimChatLieu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateCBBoxTim();
			}
		});
		rdTimMau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateCBBoxTim();
			}
		});
		rdTimSize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateCBBoxTim();
			}
		});
		rdTimGia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtGiaDau.setEditable(true);
				txtGiaSau.setEditable(true);
			}
		});
//		Bắt dự kiện tính ra giá bán
		txtGiaNhap.getDocument().addDocumentListener(new DocumentListener() {
			private double giaBan = 0;

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateTargetTextField();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateTargetTextField();
			}

			private void updateTargetTextField() {
				Double giaNhap = Double.parseDouble(txtGiaNhap.getText());
				giaBan = tinhGiaBan(giaNhap);
				txtDonGia.setText(String.valueOf(giaBan));
			}
		});

	}

	/**
	 * Dùng để mở giao diện thêm loại sản phẩm
	 */
	public void themLoaiSP() {
		cboLoaiSP.setEditable(true);
		String moi = JOptionPane.showInputDialog(this, "Thêm loại sản phẩm mới");

		if (moi != null && !moi.equalsIgnoreCase("")) {
			int n = dao.soLuongLSP() + 1;
			String soLuongMaLoaiSP = String.format("%03d", n);
			String deFault = soLuongMaLoaiSP;
			boolean check = dao.themLoaiSP(deFault, moi);

			if (check == true) {
				lblLoi.setText("Thêm loại sản phẩm thành công");

			} else
				lblLoi.setText("Thêm loại sản phẩm không thành công");
		} else
			lblLoi.setText("Thêm loại sản phẩm không thành công");
		cboLoaiSP.removeAllItems();
		listSP = dao.getLoaiSP();
		cboLoaiSP.setSelectedItem(moi);
		for (LoaiSanPham l : listSP) {
			cboLoaiSP.addItem(l.getTenLoai());
		}

		cboLoaiSP.setEditable(false);
	}

	/**
	 * Dùng để mở giao diện thêm chất liệu mới
	 */
	public void themCLSP() {
		cboChatLieu.setEditable(true);
		String moi = JOptionPane.showInputDialog(this, "Thêm chất liệu mới");
		String moTa = JOptionPane.showInputDialog(this, "Mô tả");
		if (moi != null && !moi.equalsIgnoreCase("")) {
			int n = dao.soLuongChatLieu() + 1;
			String soLuongChatLieu = String.format("%03d", n);
			String deFault = soLuongChatLieu;
			boolean check = dao.themChatLieu(deFault, moi, moTa);

			if (check == true) {
				lblLoi.setText("Thêm chất liệu thành công");

			} else
				lblLoi.setText("Thêm chất liệu không thành công");
		} else
			lblLoi.setText("Thêm chất liệu không thành công");
		cboChatLieu.removeAllItems();
		cboChatLieu.setSelectedItem(moi + "(" + moTa + ")");
		listCL = dao.getChatLieu();
		for (ChatLieu c : listCL) {
			cboChatLieu.addItem(c.getTenChatLieu() + "(" + c.getMoTa() + ")");
		}
		cboChatLieu.setEditable(false);

	}

	/**
	 * Dùng để mở giao diện thêm đơn vị tính
	 */
	public void themDVTSP() {
		cboDVT.setEditable(true);
		String moi = JOptionPane.showInputDialog(this, "Thêm đơn vị tính mới");
		cboDVT.setSelectedItem(moi);
		cboDVT.setEditable(false);
	}

	public void docDuLieu() {
		int d = 1;
		List<sanPham> list = dao.getAllSP();

		String sta = "";

		rdKhongVAT.setSelected(true);

		for (sanPham x : list) {
			float VAT = x.getVAT();
			String km = dao.getKMTheoTen(x.getKhuyenMai().getMaKhuyenMai());
			int pt = dao.getKMTheoPhanTram(x.getMaSP());
			if (x.isTinhTrang() == true) {
				sta = "Còn hàng";
			} else
				sta = "Hết hàng";
			if (dao.vat(x.getMaSP()) == 1) {

				VAT = (float) (tinhGiaBan(x.getGiaNhap()) * 0.05);
			} else
				VAT = 0;
			double giaBan = tinhGiaBan(x.getGiaNhap()) + VAT;
			double giaBanKM = giaBan - (float)(giaBan *  (float)((float) pt / 100));
			if (x.getKhuyenMai().getMaKhuyenMai() == null) {
//				Không có khuyến mãi

				String maCL = x.getChatLieu().getMaChatLieu();

				tablemodel.addRow(new Object[] { d++, x.getMaSP(), x.getTenSP(), x.getMaLoai().getMaLoai(),
						tien.format(x.getGiaNhap()), x.getSoLuong(), x.getNgayNhap(),
						x.getNhaCungCap().getMaNhaCungCap(),
						dao.getTenChatLieu(maCL) + "(" + dao.getMoTaChatLieu(maCL) + ")", x.getSize(), x.getMauSac(),
						x.getDonVitinh(), 0, VAT, sta, tien.format(giaBan) });
			} else {
//				có khuyến mãi

				String maCL = x.getChatLieu().getMaChatLieu();

				tablemodel.addRow(new Object[] { d++, x.getMaSP(), x.getTenSP(), x.getMaLoai().getMaLoai(),
						tien.format(x.getGiaNhap()), x.getSoLuong(), x.getNgayNhap(),
						x.getNhaCungCap().getMaNhaCungCap(),
						dao.getTenChatLieu(maCL) + "(" + dao.getMoTaChatLieu(maCL) + ")", x.getSize(), x.getMauSac(),
						x.getDonVitinh(), km, VAT, sta, tien.format(giaBanKM) });
			}

		}
		tblDSSP.setModel(tablemodel);
	}

	/**
	 * Dùng để xóa dữ liệu bảng
	 */
	public void xoaAllDataTable() {
		DefaultTableModel dm = (DefaultTableModel) tblDSSP.getModel();
		dm.getDataVector().removeAllElements();

	}

	public void luuThayDoi() throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String maSP = txtMaSP.getText();
		String tenSP = txtTenSP.getText();
		String ncc = (String) cboNhaCungCap.getSelectedItem();
		String km = txtKhuyenMai.getText();

		Double giaNhap = Double.parseDouble(txtGiaNhap.getText());
		int soLuong = Integer.parseInt(txtSoLuong.getText());
		String ngayNhap1 = dateFormat.format(txtChonNgaynhap.getDate());
		java.util.Date ngayNhap2 = dateFormat.parse(ngayNhap1);
		Date ngayNhapsql = new Date(ngayNhap2.getTime());
//		String hinhAnh = (String) cbohinhanh.getSelectedItem();
		sanPham.MauSac colr = (MauSac) cboColor.getSelectedItem();
		sanPham.Size size = (Size) cboSize.getSelectedItem();
		String chatLieu = (String) cboChatLieu.getSelectedItem();
		int tinhTrang = 1;
		String img = folderName;
		String dvt = (String) cboDVT.getSelectedItem();
		String loaiSP = (String) cboLoaiSP.getSelectedItem();
		String mauSac = colr.nCo;
		String kichThuoc = size.nSiz;
		String maNCC = "";
		String maChatLieu = "";
		String maLoai = "";
		String maKM = "";

		String tenCL = "";
		String moTa = "";
		float VAT = 0;
		int thue = 0;
		if (rdCoVAT.isSelected()) {
			thue = 1;

			VAT = (float) (tinhGiaBan(giaNhap) * 0.05);
		} else
			VAT = 0;

		int phanTram = 0;
		if (km.equalsIgnoreCase("None")) {
			maKM = "none";
			phanTram = 0;

		} else {
			for (KhuyenMai kmai : listKM) {
				if (kmai.getMaKhuyenMai().equalsIgnoreCase(km)) {
					maKM = kmai.getMaKhuyenMai();
					phanTram = dao.getKMTheoPhanTram(maSP);
				}
			}
		}

		double giaBan = (tinhGiaBan(giaNhap) * (1 - (float) ((float) phanTram / 100))) + VAT;
		for (NhaCungCap n : listNCC) {
			if (n.tenNhaCungCap.equalsIgnoreCase(ncc))
				maNCC = n.maNhaCungCap;
		}

		for (LoaiSanPham l : listSP) {
			if (l.getTenLoai().equalsIgnoreCase(loaiSP))
				maLoai = l.getMaLoai();
		}
		int openParenthesisIndex = chatLieu.indexOf('(');
		int closeParenthesisIndex = chatLieu.indexOf(')');

		// Kiểm tra xem cả hai dấu ngoặc có tồn tại trong chuỗi hay không
		if (openParenthesisIndex != -1 && closeParenthesisIndex != -1) {
			// Tách chuỗi thành hai phần
			tenCL = chatLieu.substring(0, openParenthesisIndex); // "Ngoài dấu ngoặc"
			moTa = chatLieu.substring(openParenthesisIndex + 1, closeParenthesisIndex); // "Trong dấu ngoặc"
		}
		for (ChatLieu c : listCL) {
			if (c.getTenChatLieu().equalsIgnoreCase(tenCL))
				maChatLieu = dao.getMaChatLieu(tenCL, moTa);
		}

		if (rdHetHang.isSelected()) {
			tinhTrang = 0;
		}

		if (rdKhongVAT.isSelected()) {
			VAT = 0;
		}
		sanPham sp = valiData();
		if (sp == null)
			return;
		else {

			if (chkThem == true) {
				try {
					giaBan = (tinhGiaBan(giaNhap) * (1 - (float) ((float) phanTram / 100)));
					boolean spMoi = dao.them(maSP, tenSP, maNCC, maKM, giaNhap, soLuong, ngayNhapsql, img, mauSac,
							kichThuoc, maChatLieu, tinhTrang, dvt, maLoai, thue, giaBan);

					if (spMoi) {

						btnThem.setText("Thêm");
						reSet();
						xoaAllDataTable();
						docDuLieu();
						JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
						btnThem.setIcon(new ImageIcon("Anh\\them.png"));
						chkThem = false;
						chkSua = false;
						lock = false;
						btnLuu.setEnabled(false);
						btnSua.setEnabled(true);

					} else
						JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại");

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (chkSua == true) {
				if (img != null) {
					giaBan = (tinhGiaBan(giaNhap) * (1 - (float) ((float) phanTram / 100))) + VAT;
					int soLuongBanDau = soLuong;
					if (soLuongBanDau != Integer.parseInt(txtSoLuong.getText()))
						ngayNhapsql = new java.sql.Date(System.currentTimeMillis());
					
						boolean spMoi = dao.sua(tenSP, maNCC, maKM, giaNhap, soLuong, ngayNhapsql, folderName, mauSac,
								kichThuoc, maChatLieu, tinhTrang, dvt, maLoai, thue, giaBan, maSP);
						if (spMoi) {
							btnSua.setText("Sửa");
							xoaAllDataTable();
							docDuLieu();
							reSet();
							JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công");
							btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
							chkThem = false;
							chkSua = false;
							lock = false;
							btnLuu.setEnabled(false);
							btnThem.setEnabled(true);
						} else
							JOptionPane.showMessageDialog(this, "Cập nhật thất bại.");
					}
					else {
					giaBan = (tinhGiaBan(giaNhap) * (1 - (float) ((float) phanTram / 100))) + VAT;

					boolean spMoi = dao.suaKhongAnh(tenSP, maNCC, maKM, giaNhap, soLuong, ngayNhapsql, mauSac,
							kichThuoc, maChatLieu, tinhTrang, dvt, maLoai, thue, giaBan, maSP);
					if (spMoi) {
						btnSua.setText("Sửa");
						xoaAllDataTable();
						docDuLieu();
						reSet();
						JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công");
						btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
						chkThem = false;
						chkSua = false;
						lock = false;
						btnLuu.setEnabled(false);
						btnThem.setEnabled(true);
					} else
						JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công");
				}
				}
			}
		}
	private void ShowErrorField(String string, JTextField txt) {
		lblLoi.setText(string);
		txt.requestFocus();

	}

	public sanPham valiData() {
		sanPham sp;
		String maSP = txtMaSP.getText().trim();
		if (txtTenSP.getText().isEmpty() || txtTenSP.getText().trim() == "") {
			lblLoi_Ten.setText("Tên sản phẩm không được rỗng");
			txtTenSP.requestFocus();
			return null;
		} else {
			lblLoi_Ten.setText("");
		}
		String tenSP = txtTenSP.getText().trim();
		if (txtGiaNhap.getText().isEmpty() || txtGiaNhap.getText().trim() == "") {
			lblLoi_GiaNhap.setText("Giá nhập không được rỗng");
			txtGiaNhap.requestFocus();
			return null;
		} else if (txtGiaNhap.getText().trim().matches("([1-9][0-9]*)") == false) {
			lblLoi_GiaNhap.setText("Vui lòng nhập giá nhập lớn hơn 0 !");
			txtGiaNhap.requestFocus();
			return null;
		} else {
			lblLoi_GiaNhap.setText("");
		}
		Double giaNhap = Double.parseDouble(txtGiaNhap.getText().trim());

		if (txtSoLuong.getText().isEmpty() || txtSoLuong.getText().trim() == "") {
			lblLoi_SoLuong.setText("Số lượng không được rỗng");
			txtSoLuong.requestFocus();
			return null;
		} else if (txtSoLuong.getText().trim().matches("([1-9][0-9]*)") == false) {
			lblLoi_SoLuong.setText("Vui lòng nhập Số lượng lớn hơn 0 !");
			txtSoLuong.requestFocus();
			return null;
		} else {
			lblLoi_SoLuong.setText("");
		}
		String soLuong = txtSoLuong.getText().trim();

		java.util.Date ngayNhap = txtChonNgaynhap.getDate();
		if (ngayNhap == null) {
			lblLoi_Ngay.setText("Ngày nhập không được rỗng");
			return null;
		} else {
			lblLoi_Ngay.setText("");
		}
		try {
			sp = new sanPham(maSP, tenSP, giaNhap, Integer.parseInt(soLuong), ngayNhap);
			return sp;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}

	}

	public void khoaTXT(boolean x) {
		txtTenSP.setEditable(x);
		txtGiaNhap.setEditable(x);
		txtSoLuong.setEditable(x);
		btnLuu.setEnabled(x);
		btbAnh.setEnabled(x);
		
	}

	public void them() {
		lock = true;
		khoaTXT(lock);
		chkThem = true;
		btnThem.setText("Hủy");
		btnSua.setEnabled(false);
		txtTenSP.requestFocus();
		btnThem.setIcon(new ImageIcon("Anh\\huy.png"));
	}

//	Sửa thông tin san pham
	public void sua() {
		lock = true;
		khoaTXT(lock);
		chkSua = true;
		btnSua.setText("Hủy");
		btnThem.setEnabled(false);
		btnSua.setIcon(new ImageIcon("Anh\\huy.png"));

	}

//	cập nhật các comboBox
	public void updateComboBox() {

		cboLoaiSP.removeAllItems();
		cboChatLieu.removeAllItems();
		cboNhaCungCap.removeAllItems();
		cboColor.removeAllItems();
		cboSize.removeAllItems();
		List<sanPham> sp = dao.getAllSP();

		for (LoaiSanPham l : listSP) {
			cboLoaiSP.addItem(l.getTenLoai());
		}

		for (ChatLieu c : listCL) {
			cboChatLieu.addItem(c.getTenChatLieu() + "(" + c.getMoTa() + ")");
		}

		for (NhaCungCap ncc : listNCC) {
			cboNhaCungCap.addItem((ncc.getTenNhaCungCap()));
		}

		for (sanPham.MauSac mau : sanPham.MauSac.values()) {
			cboColor.addItem(mau);
		}
		for (sanPham.Size size : sanPham.Size.values()) {
			cboSize.addItem(size);
		}
		cboDVT.removeAllItems();
		// List<sanPham> sp = dao.getAllSP();
		HashSet<String> dvt = new HashSet<>();

		for (sanPham s : sp) {
			String d = s.getDonVitinh();
			dvt.add(d);
		}

		for (String donViTinh : dvt) {
			cboDVT.addItem(donViTinh);
		}
	}

	public void updateCBBoxTim() {
		cboTimKiem.removeAllItems();
//get ma san pham			
		List<sanPham> listsp = dao.getAllSP();
		cboTimKiem.setEditable(true);
		for (sanPham n : listsp) {
			if (rdTimMa.isSelected() && n.getMaSP() != null) {
				cboTimKiem.addItem(n.getMaSP());
			}
//get ten san pham		
			else if (rdTen.isSelected() && n.getTenSP() != null) {
				cboTimKiem.addItem(n.getTenSP());
			}
//get loai san pham		
			else if (rdTimLoai.isSelected()) {

				HashSet<String> tenlsp = new HashSet<>();

				for (sanPham x : listsp) {
					String o = dao.getTenLoaiSP(x.getMaSP());

					tenlsp.add(o);
				}
				cboTimKiem.removeAllItems();
				for (String loaiSP : tenlsp) {
					cboTimKiem.addItem(loaiSP);
				}
			}
			//
			else if (rdTimNCC.isSelected()) {

				HashSet<String> tenncc = new HashSet<>();

				for (sanPham x : listsp) {
					String o = dao.getTenNhaCC(x.getMaSP());

					tenncc.add(o);
				}
				cboTimKiem.removeAllItems();
				for (String ncc : tenncc) {
					cboTimKiem.addItem(ncc);
				}
			}
			//
			else if (rdTimChatLieu.isSelected()) {

				HashSet<String> clieu = new HashSet<>();

				for (sanPham x : listsp) {
					String o = dao.getTenCL(x.getMaSP());

					clieu.add(o);
				}
				cboTimKiem.removeAllItems();
				for (String tenchatLieu : clieu) {
					cboTimKiem.addItem(tenchatLieu);
				}
			}
			//

		}
		if (rdTimSize.isSelected()) {
			for (sanPham.Size size : sanPham.Size.values()) {
				cboTimKiem.addItem(size);
			}
		} else if (rdTimMau.isSelected()) {
			for (sanPham.MauSac clo : sanPham.MauSac.values()) {
				cboTimKiem.addItem(clo);
			}
		}
	}

//	Tính giá bán của sản phẩm
	public double tinhGiaBan(double giaNhap) {
		double m = 0;
		m = giaNhap * 2.5;
		return m;
	}

//Tạo mã sản phẩm mặc định
	public String deFaultID() {
		int n = dao.soLuong() + 1;
		String soLuongMa = String.format("%03d", n);
		String deFault = "SP" + soLuongMa;
		return deFault;
	}

//	Bắt các sự kiện
	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			lblLoi.setText("");
			if (chkThem == false) {
				txtKhuyenMai.setText("0");
				them();
			}

			else if (chkThem == true) {
				txtKhuyenMai.setText("0");
				lock = false;
				khoaTXT(lock);
				btnThem.setText("Thêm");
				chkThem = false;
				btnSua.setEnabled(true);
				btnThem.setIcon(new ImageIcon("Anh\\them.png"));
			}

		} else if (o.equals(btnLammoi)) {
			reSet();
		} else if (o.equals(btnThemLoaiSP)) {
			themLoaiSP();

		} else if (o.equals(btnTimkiem)) {
			tim();

		} else if (o.equals(rdCoVAT)) {
			tinhGiaThue();
		} else if (o.equals(rdKhongVAT)) {
			giaKhongVAT();
		} else if (o.equals(btnChatLieu)) {
			themCLSP();

		} else if (o.equals(btnThemDonVi)) {
			themDVTSP();

		} else if (o.equals(btnLammoithanh)) {
			xoaTrangTimKiem();

		} else if (o.equals(btnSua)) {
			lblLoi.setText("");
			{
				if (chkSua == false) {
					sua();
				}

				else if (chkSua == true) {
					lock = false;
					khoaTXT(lock);
					btnSua.setText("Sửa");
					chkSua = false;
					btnThem.setEnabled(true);
					btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
					reSet();
				}
			}

		} else if (o.equals(btnLuu)) {
			try {
				luuThayDoi();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btbAnh)) {
			chonAnh();
		}
	}

//BẮt sự kiện click màn vào bảng
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		btnThem.setEnabled(false);
		int d = 1;

		int row = tblDSSP.getSelectedRow();
		txtMaSP.setText(tablemodel.getValueAt(row, 1).toString());
		txtTenSP.setText(tablemodel.getValueAt(row, 2).toString());
		cboLoaiSP.setSelectedItem(tablemodel.getValueAt(row, 3));
		txtGiaNhap.setText(tablemodel.getValueAt(row, 4).toString());
		txtSoLuong.setText(tablemodel.getValueAt(row, 5).toString());
//		Date ngayNhap = (Date) tablemodel.getValueAt(row, 6);
		cboNhaCungCap.setSelectedItem(tablemodel.getValueAt(row, 7));
		cboChatLieu.setSelectedItem(tablemodel.getValueAt(row, 8));
		cboSize.setSelectedItem(tablemodel.getValueAt(row, 9));
		cboColor.setSelectedItem(tablemodel.getValueAt(row, 10));
		cboDVT.setSelectedItem(tablemodel.getValueAt(row, 11));

//	    txtkhuyenmai.setText(tablemodel.getValueAt(row, 12).toString());
		txtKhuyenMai.setText(String.valueOf(dao.getKMTheoPhanTram(tablemodel.getValueAt(row, 1).toString())));

		// txtChonNgaynhap.setDate(ngayNhap);
		txtChonNgaynhap.setDate(dao.getAllSP().get(row).getNgayNhap());
		txtDonGia.setText(tablemodel.getValueAt(row, 15).toString());
		List<sanPham> list = dao.getAllSP();

		for (sanPham x : list) {
			if (x.getMaSP().equals(tablemodel.getValueAt(row, 1).toString())) {
				if (dao.vat(x.getMaSP()) == 1) {
					rdCoVAT.setSelected(true);
				} else {
					rdKhongVAT.setSelected(true);
				}
				if (x.isTinhTrang() == true) {
					rdConHang.setSelected(true);
				} else
					rdHetHang.setSelected(true);

			}
			ImageIcon imageIcon = new ImageIcon(
					new ImageIcon(dao.getMa(tablemodel.getValueAt(row, 1).toString()).getHinhAnh()).getImage()
							.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
			label.setIcon(imageIcon);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

//	Làm mới thanh thông tin và bảng
	public void reSet() {
		btnThem.setEnabled(true);
		txtMaSP.setText(deFaultID());
		txtTenSP.setText("");
		txtGiaNhap.setText(String.valueOf(0));
		txtSoLuong.setText(String.valueOf(0));
		txtDonGia.setText("");
		txtChonNgaynhap.setDate(new java.util.Date());
		txtDonGia.setText("");
		txtTenSP.requestFocus();
		xoaAllDataTable();
		docDuLieu();
	}

//	chon anh
	public String chonAnh() {
		try {
			String user = System.getProperty("user.dir");
			JFileChooser fileChooser = new JFileChooser(user + "\\AnhSanPham");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("png file", "png");
			FileNameExtensionFilter filterJPG = new FileNameExtensionFilter("jpg file", "jpg");

			fileChooser.addChoosableFileFilter(filter);
			fileChooser.addChoosableFileFilter(filterJPG);
			int result = fileChooser.showOpenDialog(null);

			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String filePath = selectedFile.getAbsolutePath();

				// Load the selected image into the label
				ImageIcon imageIcon = new ImageIcon(new ImageIcon(filePath).getImage()
						.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
				label.setIcon(imageIcon);
				File file = new File(filePath);

				String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
				folderName = file.getParentFile().getName() + "\\\\" + fileName;

				return folderName; // Return the file path
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return folderName;
	}

//	tính giá có VAT
	public void tinhGiaThue() {
		double giaBan;
		double thue;
		double giaNhap = Double.parseDouble(txtGiaNhap.getText());
		thue = tinhGiaBan(giaNhap) * 0.05;
		giaBan = tinhGiaBan(giaNhap) + thue;
		txtDonGia.setText(String.valueOf(giaBan));
	}

//	tinh giá không có VAT
	public void giaKhongVAT() {
		double giaBan = 0;
		Double giaNhap = Double.parseDouble(txtGiaNhap.getText());
		giaBan = tinhGiaBan(giaNhap);
		txtDonGia.setText(String.valueOf(giaBan));
		// txtdongia.setText(String.valueOf(giaBan));
	}

// tim
	public void tim() {
		xoaAllDataTable();
		String tim = "";

		List<sanPham> list = dao.getAllSP();
		float VAT = 0;
		String sta = "";
		// int km = 0;
		rdKhongVAT.setSelected(true);

		for (sanPham sp : list) {
			int d = 1;
			if (rdTimGia.isSelected()) {
				double gia1 = Double.parseDouble(txtGiaDau.getText());
				double gia2 = Double.parseDouble(txtGiaSau.getText());
				List<sanPham> listsp = dao.timTheoGia(gia1, gia2);
				xoaAllDataTable();

				for (sanPham x : listsp) {
					String km = x.getKhuyenMai().getMaKhuyenMai();
					if (x.isTinhTrang() == true) {
						sta = "Còn hàng";
					} else
						sta = "Hết hàng";
					if (dao.vat(x.getMaSP()) == 1) {

						VAT = (float) (tinhGiaBan(x.getGiaNhap()) * 0.05);
					} else
						VAT = 0;
					double giaBan = tinhGiaBan(x.getGiaNhap()) + VAT;
					giaBan = x.getGiaBan();
					tablemodel.addRow(new Object[] { d++, x.getMaSP(), x.getTenSP(), x.getMaLoai().getMaLoai(),
							x.getGiaNhap(), x.getSoLuong(), x.getNgayNhap(), x.getNhaCungCap().getMaNhaCungCap(),
							x.getChatLieu().getMaChatLieu(), x.getSize(), x.getMauSac(), x.getDonVitinh(), km, VAT, sta,
							x.getGiaBan() });

				}

			} else {
				try {
					tim = cboTimKiem.getSelectedItem().toString();

				} catch (Exception e) {
					// TODO: handle exception
				}
				if (tim.equalsIgnoreCase("")) {
					lblLoi.setText("Rỗng!");
					return;
				}
				if (rdTimMa.isSelected()) {
					sanPham x = dao.getMa(tim);
					xoaAllDataTable();
					String km = x.getKhuyenMai().getMaKhuyenMai();
					if (x.isTinhTrang() == true) {
						sta = "Còn hàng";
					} else
						sta = "Hết hàng";
					if (dao.vat(x.getMaSP()) == 1) {

						VAT = (float) (tinhGiaBan(x.getGiaNhap()) * 0.05);
					} else
						VAT = 0;
					double giaBan = tinhGiaBan(x.getGiaNhap()) + VAT;
					giaBan = x.getGiaBan();
					tablemodel.addRow(new Object[] { d++, x.getMaSP(), x.getTenSP(), x.getMaLoai().getMaLoai(),
							x.getGiaNhap(), x.getSoLuong(), x.getNgayNhap(), x.getNhaCungCap().getMaNhaCungCap(),
							x.getChatLieu().getMaChatLieu(), x.getSize(), x.getMauSac(), x.getDonVitinh(), km, VAT, sta,
							x.getGiaBan() });
				}
				if (rdTen.isSelected()) {

					sanPham x = dao.getTenSP(tim);
					String km = x.getKhuyenMai().getMaKhuyenMai();
					xoaAllDataTable();
					if (x.isTinhTrang() == true) {
						sta = "Còn hàng";
					} else
						sta = "Hết hàng";
					if (dao.vat(x.getMaSP()) == 1) {

						VAT = (float) (tinhGiaBan(x.getGiaNhap()) * 0.05);
					} else
						VAT = 0;
					double giaBan = tinhGiaBan(x.getGiaNhap()) + VAT;
					giaBan = x.getGiaBan();
					tablemodel.addRow(new Object[] { d++, x.getMaSP(), x.getTenSP(), x.getMaLoai().getMaLoai(),
							x.getGiaNhap(), x.getSoLuong(), x.getNgayNhap(), x.getNhaCungCap().getMaNhaCungCap(),
							x.getChatLieu().getMaChatLieu(), x.getSize(), x.getMauSac(), x.getDonVitinh(), km, VAT, sta,
							x.getGiaBan() });
				}
				if (rdTimLoai.isSelected()) {

					List<sanPham> listsp = dao.getlistTenLoaiSP(tim);
					xoaAllDataTable();

					for (sanPham x : listsp) {
						String km = x.getKhuyenMai().getMaKhuyenMai();
						if (x.isTinhTrang() == true) {
							sta = "Còn hàng";
						} else
							sta = "Hết hàng";
						if (dao.vat(x.getMaSP()) == 1) {

							VAT = (float) (tinhGiaBan(x.getGiaNhap()) * 0.05);
						} else
							VAT = 0;
						double giaBan = tinhGiaBan(x.getGiaNhap()) + VAT;
						giaBan = x.getGiaBan();
						tablemodel.addRow(new Object[] { d++, x.getMaSP(), x.getTenSP(), x.getMaLoai().getMaLoai(),
								x.getGiaNhap(), x.getSoLuong(), x.getNgayNhap(), x.getNhaCungCap().getMaNhaCungCap(),
								x.getChatLieu().getMaChatLieu(), x.getSize(), x.getMauSac(), x.getDonVitinh(), km, VAT,
								sta, x.getGiaBan() });
					}
				}
				if (rdTimNCC.isSelected()) {

					List<sanPham> listsp = dao.getlistTenNCC(tim);
					xoaAllDataTable();

					for (sanPham x : listsp) {
						String km = x.getKhuyenMai().getMaKhuyenMai();
						if (x.isTinhTrang() == true) {
							sta = "Còn hàng";
						} else
							sta = "Hết hàng";
						if (dao.vat(x.getMaSP()) == 1) {

							VAT = (float) (tinhGiaBan(x.getGiaNhap()) * 0.05);
						} else
							VAT = 0;
						double giaBan = tinhGiaBan(x.getGiaNhap()) + VAT;
						giaBan = x.getGiaBan();
						tablemodel.addRow(new Object[] { d++, x.getMaSP(), x.getTenSP(), x.getMaLoai().getMaLoai(),
								x.getGiaNhap(), x.getSoLuong(), x.getNgayNhap(), x.getNhaCungCap().getMaNhaCungCap(),
								x.getChatLieu().getMaChatLieu(), x.getSize(), x.getMauSac(), x.getDonVitinh(), km, VAT,
								sta, x.getGiaBan() });
					}
				}
				if (rdTimChatLieu.isSelected()) {

					List<sanPham> listsp = dao.getlistTenCL(tim);
					xoaAllDataTable();

					for (sanPham x : listsp) {
						String km = x.getKhuyenMai().getMaKhuyenMai();
						if (x.isTinhTrang() == true) {
							sta = "Còn hàng";
						} else
							sta = "Hết hàng";
						if (dao.vat(x.getMaSP()) == 1) {

							VAT = (float) (tinhGiaBan(x.getGiaNhap()) * 0.05);
						} else
							VAT = 0;
						double giaBan = tinhGiaBan(x.getGiaNhap()) + VAT;
						giaBan = x.getGiaBan();
						tablemodel.addRow(new Object[] { d++, x.getMaSP(), x.getTenSP(), x.getMaLoai().getMaLoai(),
								x.getGiaNhap(), x.getSoLuong(), x.getNgayNhap(), x.getNhaCungCap().getMaNhaCungCap(),
								x.getChatLieu().getMaChatLieu(), x.getSize(), x.getMauSac(), x.getDonVitinh(), km, VAT,
								sta, x.getGiaBan() });
					}
				}
				if (rdTimMau.isSelected()) {

					List<sanPham> listsp = dao.getlistTenMauSac(tim);
					xoaAllDataTable();

					for (sanPham x : listsp) {
						String km = x.getKhuyenMai().getMaKhuyenMai();
						if (x.isTinhTrang() == true) {
							sta = "Còn hàng";
						} else
							sta = "Hết hàng";
						if (dao.vat(x.getMaSP()) == 1) {

							VAT = (float) (tinhGiaBan(x.getGiaNhap()) * 0.05);
						} else
							VAT = 0;
						double giaBan = tinhGiaBan(x.getGiaNhap()) + VAT;
						giaBan = x.getGiaBan();
						tablemodel.addRow(new Object[] { d++, x.getMaSP(), x.getTenSP(), x.getMaLoai().getMaLoai(),
								x.getGiaNhap(), x.getSoLuong(), x.getNgayNhap(), x.getNhaCungCap().getMaNhaCungCap(),
								x.getChatLieu().getMaChatLieu(), x.getSize(), x.getMauSac(), x.getDonVitinh(), km, VAT,
								sta, x.getGiaBan() });

					}
				}
				if (rdTimSize.isSelected()) {

					List<sanPham> listsp = dao.getlistSize(tim);
					xoaAllDataTable();

					for (sanPham x : listsp) {
						String km = x.getKhuyenMai().getMaKhuyenMai();
						if (x.isTinhTrang() == true) {
							sta = "Còn hàng";
						} else
							sta = "Hết hàng";
						if (dao.vat(x.getMaSP()) == 1) {

							VAT = (float) (tinhGiaBan(x.getGiaNhap()) * 0.05);
						} else
							VAT = 0;
						double giaBan = tinhGiaBan(x.getGiaNhap()) + VAT;
						giaBan = x.getGiaBan();
						tablemodel.addRow(new Object[] { d++, x.getMaSP(), x.getTenSP(), x.getMaLoai().getMaLoai(),
								x.getGiaNhap(), x.getSoLuong(), x.getNgayNhap(), x.getNhaCungCap().getMaNhaCungCap(),
								x.getChatLieu().getMaChatLieu(), x.getSize(), x.getMauSac(), x.getDonVitinh(), km, VAT,
								sta, x.getGiaBan() });

					}
				}

			}
		}
	}

	public void xoaTrangTimKiem() {
		buttonGroupTK.clearSelection();
		cboTimKiem.removeAllItems();
		xoaAllDataTable();
		docDuLieu();
		txtGiaDau.setText("");
		txtGiaSau.setText("");
		txtGiaDau.setEditable(false);
		txtGiaSau.setEditable(false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(txtGiaNhap) || o.equals(txtSoLuong) || o.equals(txtGiaDau) || o.equals(txtGiaSau)) {
			if (!((e.getKeyChar() >= 48 && e.getKeyChar() <= 57) || e.getKeyChar() == 8)) {
				e.consume();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}
}
	