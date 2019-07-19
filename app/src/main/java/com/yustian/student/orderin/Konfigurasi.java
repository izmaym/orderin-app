package com.yustian.student.orderin;

public class Konfigurasi {
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
    public static final String URL_GET_ALL_ORD = URL + "tampilsemuapesanan.php";
    public static final String URL_GET_ORD = URL + "tampilpesanan.php?id=";
    public static final String URL_ORDER = URL + "tambahpesanan.php";
    public static final String URL_DELETE_ORD = URL + "hapuspesanan.php?id=";

    // KEY Menu
    public static final String KEY_ID = "id";
    public static final String KEY_KATEGORI = "id_kategori";
    public static final String KEY_NAME = "name";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_STOK = "stok";

    public static final String KEY_CON_TABLE = "id_meja";

    // TAG
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_NUMBER = "number";

    // CON
    public static final String CON_ID = "CON_id";
}
