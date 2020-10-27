package UI.utils;

/**
 * Descripcion: modelo utilizado par crear objetos del tipo FileModel que tienen la informacion principal
 * de un archivo de excel
 * */

public class FileModel {
    private String fileName;
    private String sheetName;

    public FileModel(){

    }

    public FileModel(String fileName, String sheetName) {
        this.fileName = fileName;
        this.sheetName = sheetName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
}
