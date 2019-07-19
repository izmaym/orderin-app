package com.yustian.student.orderin;

public class Konfigurasi {
    // API URL
    public static final String URL = "http://192.168.43.94/orderin/";

    // Create
    public static final String URL_ADD = URL + "tambah.php";
    public static final String URL_ORDER = URL + "tambahpesanan.php";

    // Read
    public static final String URL_GET_CON = URL + "tampilmenu.php?id=";
    public static final String URL_GET_ALL = URL + "tampilsemuamenu.php";
    public static final String URL_GET_ORD = URL + "tampilpesanan.php?id=";
    public static final String URL_GET_ALL_ORD = URL + "tampilsemuapesanan.php";

    // Update
    public static final String URL_UPDATE_CON = URL + "ubah.php";

    // Delete
    public static final String URL_DELETE_CON = URL + "hapusmenu.php?id=";
    public static final String URL_DELETE_ORD = URL + "hapuspesanan.php?id=";

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
