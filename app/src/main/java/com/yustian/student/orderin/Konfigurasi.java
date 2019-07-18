package com.yustian.student.orderin;

public class Konfigurasi {
    // API URL
    public static final String URL_ADD = "http://192.168.43.94/orderin/tambah.php";
    public static final String URL_ORDER = "http://192.168.43.94/orderin/tambahpesanan.php";
    public static final String URL_GET_ALL = "http://192.168.43.94/orderin/tampilsemua.php";
    public static final String URL_GET_CON = "http://192.168.43.94/orderin/tampilmenu.php?id=";
    public static final String URL_GET_ORD = "http://192.168.43.94/orderin/tampilpesanan.php";
    public static final String URL_UPDATE_CON = "http://192.168.43.94/orderin/ubah.php";
    public static final String URL_DELETE_CON = "http://192.168.43.94/orderin/hapus.php?id=";

    // KEY
    public static final String KEY_CON_ID = "id";
    public static final String KEY_CON_NAME = "name";
    public static final String KEY_CON_NUMBER = "number";
    public static final String KEY_CON_TABLE = "id_meja";

    // TAG
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_NUMBER = "number";

    // CON
    public static final String CON_ID = "CON_id";
}
