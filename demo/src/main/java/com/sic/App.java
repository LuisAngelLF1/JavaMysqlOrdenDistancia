package com.sic; 

import java.io.FileWriter; 
import java.sql.*; public class App { public static void main(String[] args) { 
   int i=0; //control de indices del arregl a ordenar
   int key;//variable de control para almacenar un valor del arreglo
   int [] arreglo= new int [11]; //arreglo
   int indicemen=0; //guardara el indice del elemento menor
   // Try-Catch para manejo de errores 
   try { 
      // Generar un nuevo archivo csv 
       FileWriter csvWriter = new FileWriter("archivo.csv"); 
      // Llamada a la librería mysql conector 
       Class.forName("com.mysql.jdbc.Driver"); 
      // Nueva conexión conexión a localhost | nombre de bd | ususario mysql | 
      // contraseña 
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/distancia", "usuario", 
                          "pass1234"); 
      // Nuevo statement para llamada de datos 
       Statement stmt = con.createStatement(); 
      // Creación de Query | llamada a todos los datos de la tabla alumnos 
       ResultSet rs = stmt.executeQuery("select * from sensorDistanica"); 
      // Ciclo de todos los elementos obtenidos por el query 
       while (rs.next()){ 
          //Se guardan los valores obtenidos por el query en una línea nueva del archivo 
           csvWriter.append(String.valueOf(rs.getInt(1))+","); 
          // Impresión de los valores 
           System.out.println(rs.getInt(1));
           arreglo[i]=rs.getInt(1);//se llena el arreglo con los valores de la BBDD;
           i++;
       }
        
       //ciclo para ordenar los datos(selectsort)
       for (int j=0;j<11;j++){
          key=arreglo[j];
          indicemen=j;
          //comparacion de j hasta n
          for(int k=j+1;k<11;k++){
             if (arreglo[k]<key){
                indicemen=k;
             }
            }
         arreglo[j]=arreglo[indicemen];
         arreglo[indicemen]=key;
       }
       //Se imprimen los datos en orden
       System.out.println("Datos ordenados");
       for (int l=0; l<11 ; l++){
          System.out.println(arreglo[l]);
       }

      //Cerrar conexión de sql 
       con.close(); 
      //Cerrar archivo csv 
       csvWriter.flush(); 
       csvWriter.close(); 
      // Se cierra la conexión 
   } catch (Exception e) { 
     // Imprimir errores 
     System.out.println(e); 
   } 
}
} 