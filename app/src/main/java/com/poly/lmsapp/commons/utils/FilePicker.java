package com.poly.lmsapp.commons.utils;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.NonNull;
import droidninja.filepicker.FilePickerConst;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class FilePicker implements EasyPermissions.PermissionCallbacks {
    private static FilePicker filePicker;
    private static File file;
    private static String path;
    private static String name;
    private static Uri uploadFileUri;
    private static Activity activity;
    private static final int gallery = 12;
    private static final int fileRequestCode = 13;
    private static final String type = "image/*";
    private static final String allType = "*/*";

    public static FilePicker getInstance() {
        if (filePicker == null) filePicker = new FilePicker();
        return filePicker;
    }


    public static String convertBase64(File filePicked) {
        try {
            InputStream is = new FileInputStream(filePicked);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[(int) filePicked.length()];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            String extension = null;

            extension = path.split("\\.")[path.split("\\.").length - 1];
            name = path.split("/")[path.split("/").length - 1];
            String mine = "";
            assert extension != null;
            if (",.jpg,.jpeg,.png,.gif,.bmp"
                    .contains(extension)) {
                mine = "image";
            } else {
                mine = "application";
            }
            String sBase64 = "data:" + mine + "/" + extension + ";base64," + Base64.encodeToString(data,Base64.DEFAULT);
            Log.d("Base64", "convertBase64: " + sBase64);
            return sBase64;


            /*
             * image convert base64
            byte[] fileContent = Files.readAllBytes(filePicked.toPath());
            Log.d("Base64", "convertBase64: " + "data:image/" + extension + ";base64," + Base64.getEncoder().encodeToString(fileContent));
            Log.d("Base64", "convertBase64: " + extension);
            return "data:image/" + extension + ";base64," + Base64.getEncoder().encodeToString(fileContent);
            */
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public static String convertBase64FromUri(Activity activity,Uri filePickedUri) {
        try {
//           activity.getContentResolver().openInputStream(filePickedUri).re;
//
//            Log.d("Base64", "convertBase64: " + Base64.encodeToString(bytes,Base64.DEFAULT));
//            return Base64.encodeToString(bytes,Base64.DEFAULT);
//            InputStream is = new FileInputStream(filePicked);
            InputStream is = new FileInputStream(new File(""));
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
//            byte[] data = new byte[(int) filePicked.length()];
            byte[] data = new byte[500];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            String extension = null;

            extension = path.split("\\.")[path.split("\\.").length - 1];
            name = path.split("/")[path.split("/").length - 1];
            String mine = "";
            assert extension != null;
            if (",.jpg,.jpeg,.png,.gif,.bmp"
                    .contains(extension)) {
                mine = "image";
            } else {
                mine = "application";
            }
            String sBase64 = "data:" + mine + "/" + extension + ";base64," + Base64.encodeToString(data,Base64.DEFAULT);
            Log.d("Base64", "convertBase64: " + sBase64);
            return sBase64;


            /*
             * image convert base64
            byte[] fileContent = Files.readAllBytes(filePicked.toPath());
            Log.d("Base64", "convertBase64: " + "data:image/" + extension + ";base64," + Base64.getEncoder().encodeToString(fileContent));
            Log.d("Base64", "convertBase64: " + extension);
            return "data:image/" + extension + ";base64," + Base64.getEncoder().encodeToString(fileContent);
            */
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public static void showImagePicker() {
        if (EasyPermissions.hasPermissions(activity, FilePickerConst.PERMISSIONS_FILE_PICKER)) {

            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activity.startActivityForResult(Intent.createChooser(i, "select image"), gallery);
        } else {
            EasyPermissions.requestPermissions(activity, "Vui lòng cấp quyền để upload file",
                    gallery, FilePickerConst.PERMISSIONS_FILE_PICKER);
        }

    }

    public static void showFilePicker() {
        if (EasyPermissions.hasPermissions(activity, FilePickerConst.PERMISSIONS_FILE_PICKER)) {
            final Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//            final Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            activity.startActivityForResult(Intent.createChooser(intent, "select file"), gallery);
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(activity, "Vui lòng cấp quyền để upload file",
                    fileRequestCode, FilePickerConst.PERMISSIONS_FILE_PICKER);
        }

    }

    public static void callBackPicker(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK && data != null) {

            uploadFileUri = data.getData();                    file = new File(uploadFileUri.getPath());
            Log.e("AAAAAAa", "callBackPicker: "+ getFullPathFromContentUri(uploadFileUri));
            if(uploadFileUri != null){
                if(getFullPathFromContentUri(uploadFileUri) != null){
                    file = new File(getFullPathFromContentUri(uploadFileUri));
                    path = getFullPathFromContentUri(uploadFileUri);
                    Log.d("Callbackpicker: ", "callBackPicker: " + getFullPathFromContentUri(uploadFileUri));
                }
            }


        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(activity, perms)) {
            new AppSettingsDialog.Builder(activity).build().show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    public static void setActivity(Activity activity) {
        FilePicker.activity = activity;
    }

    public static File getFile() {
        return file;
    }

    public static String getFullPathFromContentUri(final Uri uri) {
        Log.d("FFFFFFFFFFFFf", "getFullPathFromContentUri: " + uri);
        Log.d("FFFFFFFFFFFFf", "getFullPathFromContentUri: " + uri.getAuthority());
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        String filePath = null;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(activity, uri)) {
            // ExternalStorageProvider
            if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }//non-primary e.g sd card
                else {

                    if (Build.VERSION.SDK_INT > 20) {
                        //getExternalMediaDirs() added in API 21
                        File extenal[] = activity.getExternalMediaDirs();
                        for (File f : extenal) {
                            filePath = f.getAbsolutePath();
                            if (filePath.contains(type)) {
                                int endIndex = filePath.indexOf("Android");
                                filePath = filePath.substring(0, endIndex) + split[1];
                            }
                        }
                    } else {
                        filePath = "/storage/" + type + "/" + split[1];
                    }
                    return filePath;
                }
            }
            // DownloadsProvider
            else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                String fileName = getFilePath(uri);
                if (fileName != null) {
                    return Environment.getExternalStorageDirectory().toString() + "/Download/" + fileName;
                }

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(contentUri, null, null);
            }
            // MediaProvider
            else if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                final String docId = DocumentsContract.getDocumentId(uri);
                Log.d("AAAAAAAAAaaa", "getFullPathFromContentUri: " + docId.toString());
                final String[] split = docId.split(":");

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                Cursor cursor = null;
                final String column = "_data";
                final String[] projection = {
                        column
                };

                try {

                    cursor =activity.getApplicationContext().getContentResolver().query(uri, projection, selection, selectionArgs, null);

                    if (cursor != null && cursor.moveToFirst()) {
//                        final int column_index = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
                        final int column_index = cursor.getColumnIndex("_data");
                        return cursor.getString(column_index);
                    }
                } finally {
                    if (cursor != null)
                        cursor.close();
                }
            } else return null;
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        } else return null;
        return null;
    }

    private static String getDataColumn(Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = activity.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private static String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public static String getFilePath(Uri uri) {

        final String[] projection = {
                MediaStore.MediaColumns.DISPLAY_NAME
        };
        try (Cursor cursor = activity.getContentResolver().query(uri, projection, null, null,
                null)) {
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);
                return cursor.getString(index);
            }
        }
        return null;
    }

    public static String getPath() {
        return path;
    }

    public static String getName() {
        return name;
    }

    public static void setFilePicker(FilePicker filePicker) {
        FilePicker.filePicker = filePicker;
    }

    public static void setFile(File file) {
        FilePicker.file = file;
    }

    public static void setPath(String path) {
        FilePicker.path = path;
    }

    public static void setName(String name) {
        FilePicker.name = name;
    }

    public static Uri getUploadFileUri() {
        return uploadFileUri;
    }

    public static void setUploadFileUri(Uri uploadFileUri) {
        FilePicker.uploadFileUri = uploadFileUri;
    }
}
