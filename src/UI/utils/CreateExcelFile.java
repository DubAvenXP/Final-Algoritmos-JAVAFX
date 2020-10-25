package UI.utils;


import database.models.Cliente;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CreateExcelFile {


    public static String reportPath;
    public static void exportClientsToExcel(List<Cliente> clientes) {

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("_dMMMyyyy_HH-mm"));
        final String GLOBALUSER = System.getProperty("user.name");
        String fileName = "clients" + "Report" + now + ".xlsx";
        String hoja = "clients";

        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);

        //Headers
        String[] header = new String[]{
                "id",
                "nit",
                "nombre",
                "apellido",
                "direccion",
                "telefono"
        };

        //Bold
        CellStyle style = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBold(true);
        style.setFont(font);
        int index = 0;
        //generar los datos para el doc
        for (int i = 0; i <= clientes.size(); i++) {
            XSSFRow row = hoja1.createRow(i); //Se crea la fila

            for (int j = 0; j < header.length; j++) {
                if (i == 0) { //para la cabecera
                    XSSFCell cell = row.createCell(j); //Se crean las celdas para la cabecera
                    cell.setCellValue(header[j]); //Se añade el contenido
                }
            }
            if (i != 0 && index < clientes.size()) {
                Cliente cliente = clientes.get(index);
                XSSFCell id = row.createCell(0); //Se crean las celdas para la cabecera
                id.setCellValue(cliente.getIdCliente());
                XSSFCell nit = row.createCell(1); //Se crean las celdas para la cabecera
                nit.setCellValue(cliente.getNit());
                XSSFCell nombre = row.createCell(2); //Se crean las celdas para la cabecera
                nombre.setCellValue(cliente.getNombre());
                XSSFCell apellido = row.createCell(3); //Se crean las celdas para la cabecera
                apellido.setCellValue(cliente.getApellido());
                XSSFCell direccion = row.createCell(4); //Se crean las celdas para la cabecera
                direccion.setCellValue(cliente.getDireccion());
                XSSFCell telefono = row.createCell(5); //Se crean las celdas para la cabecera
                telefono.setCellValue(cliente.getTelefono());
                index++;
            }

        }


        //Crear el archivo
        //Darle un nombre al archivo
        reportPath = "C:\\Users\\" + GLOBALUSER + "\\Downloads\\" + fileName;

        try (OutputStream fileOut = new FileOutputStream(reportPath)) {
            libro.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
