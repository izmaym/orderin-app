package com.yustian.student.orderin;

public class Configuration {
    // Offline
    public static final String URL = "http://192.168.43.94/orderin/";

    // Online
    // public static final String URL = "http://orderin2.000webhostapp.com/";

    // API Menu
    public static final String URL_GET_ALL = URL + "tampilsemuamenu.php";
    public static final String URL_GET = URL + "tampilmenu.php?id=";
    public static final String URL_ADD = URL + "tambahmenu.php";
    public static final String URL_UPDATE = URL + "ubahmenu.php";
    public static final String URL_DELETE = URL + "hapusmenu.php?id=";

    // API Pesanan
    public static final String URL_GET_ALL_ORD_USER = URL + "tampilsemuapesananuser.php";
    public static final String URL_GET_ALL_ORD = URL + "tampilsemuapesanan.php";
    public static final String URL_GET_ORD = URL + "tampilpesanan.php?id=";
    public static final String URL_ORDER = URL + "tambahpesanan.php";
    public static final String URL_DELETE_ORD = URL + "hapuspesanan.php?id=";

    // API User
    public static final String URL_GET_ALL_USER = URL + "tampilsemuauser.php";
    public static final String URL_DELETE_USER = URL + "hapususer.php";

    // API Meja
    public static final String URL_GET_ALL_TABLE = URL + "tampilsemuameja.php";
    public static final String URL_ADD_TABLE = URL + "tambahmeja.php";

    // API Penjualan
    public static final String URL_ADD_SALES = URL + "tambahpenjualan.php";

    // KEY Menu
    public static final String KEY_ID = "id";
    public static final String KEY_KATEGORI = "id_kategori";
    public static final String KEY_NAME = "name";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_STOK = "stok";

    // KEY Meja
    public static final String KEY_ID_TABLE = "id_meja";
    public static final String KEY_NUMBER_TABLE = "no_meja";

    // KEY Sales
    public static final String KEY_ID_TRANSACTION = "no_transaksi";
    public static final String KEY_ID_USER = "id_user";

    // TAG
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_ID = "id";
    public static final String TAG_KATEGORI = "id_kategori";
    public static final String TAG_STOK = "stok";

    // TAG Pesanan
    public static final String TAG_NAME = "name";
    public static final String TAG_NUMBER = "number";

    // TAG User
    public static final String TAG_USERNAME = "username";

    // TAG Table
    public static final String TAG_ID_TABLE = "id_meja";
    public static final String TAG_NUMBER_TABLE = "no_meja";

    // TAG Sales
    public static final String TAG_ID_TRANSACTION = "no_transaksi";

    // CON
    public static final String CON_ID = "CON_id";
}
