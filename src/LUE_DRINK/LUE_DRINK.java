package LUE_DRINK;

// Importamos las clases necesarias de la biblioteca Java
import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;


public class LUE_DRINK {

    // Creamos variables para almacenar productos, cantidades y precios en el carrito
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> usuarios = new ArrayList<>();
    private static ArrayList<String> contrasenas = new ArrayList<>();
    private static ArrayList<String> carritoProductos = new ArrayList<>();
    private static ArrayList<Integer> carritoCantidad = new ArrayList<>();
    private static ArrayList<Double> carritoPrecios = new ArrayList<>();
    private static ArrayList<String> historialCompras = new ArrayList<>();

    // Definimos string para cambiar el color
    public static final String negro = "\033[30m";
    public static final String rojo = "\033[31m";
    public static final String verde = "\033[32m";
    public static final String amarillo = "\033[33m";
    public static final String azul = "\033[34m";
    public static final String morado = "\033[35m";
    public static final String cyan = "\033[36m";
    public static final String blanco = "\033[37m";

    public static void main(String[] args) throws AWTException {
        // llamamos a la funcion "cargarDatos" para Cargar los datos de usuario al iniciar
        cargarDatos(); 
        while (true) {
            mostrarMenuPrincipal();
        }
    }
    //opción de registrarse e iniciar sesión en la que el cajero tendrá que elegir
    private static void mostrarMenuPrincipal() throws AWTException {

        int opcion;
        
            mensaje(verde + "-----------------------------------------------\n");
            mensaje("                 MENU DE INICIO                \n");
            mensaje(verde + "-----------------------------------------------\n");

            System.out.println("1. Registro \n2. Iniciar Sesion\nelija una opción: ");
            opcion = capturarnumero();
            
            // Evaluamos la opción seleccionada y llamamos al método correspondiente
            switch (opcion) {
                case 1:
                    registrarUsuario();

                    break;
                case 2:
                    iniciarSesion();

                    break;

                default:
                    System.out.println("Opcion no valida. Intente nuevamente");
            }
    }

    // Función para registrar un nuevo usuario
    private static void registrarUsuario() {
        // Solicitar el DNI al usuario y validar que tenga 8 dígitos numerico    
        System.out.print("DNI:");
        String dni = scanner.nextLine();
        if (!dni.matches("\\d{8}")) {
            System.out.println("DNI inválido. Debe contener exactamente 8 dígitos numéricos.");
            return;
        }
        // Solicitar los nombres del usuario y validar que sean letras
        System.out.print("Nombres:");
        String nombres = scanner.nextLine();
        if (!nombres.matches("[a-zA-Z\\s]+")) {
            System.out.println("Nombres Invalidos, Intente nuevamente");
            return;
        }
        // Solicitar el apellido paterno del usuario y validar que sean letras
        System.out.print("Apellido Paterno:");
        String apellidoPaterno = scanner.nextLine();
        if (!apellidoPaterno.matches("[a-zA-Z\\s]+")) {
            System.out.println("Apellido Paterno invalido, Intente nuevamente");
            return;
        }
        // Solicitar el apellido materno del usuario y validar que sean letras
        System.out.print("Apellido Materno:");
        String apellidoMaterno = scanner.nextLine();
        if (!apellidoMaterno.matches("[a-zA-Z\\s]+")) {
            System.out.println("Apellido Materno invalido, Intente nuevamente");
            return;
        }
        // Solicitar y validar las fechas de nacimiento
        mensaje("Fecha de Nacimiento:\n");
        System.out.print("dia:");
        String dia = capturartexto();
        int diaInt = Integer.parseInt(dia);
        if (diaInt <= 0 || diaInt > 31) {
            mensaje("El dia debe ser mayor que 0 y menor que 30. Intente nuevamente.\n");
            return;
        }

        System.out.print("mes:");
        String mes = capturartexto();
        int mesInt = Integer.parseInt(mes);
        if (mesInt <= 0 || mesInt > 13) {
            mensaje("El mes debe ser mayor que 0 y menor que 13. Intente nuevamente.\n");
            return;
        }

        System.out.print("anio:");
        String anio = capturartexto();
        int anioInt = Integer.parseInt(anio);

        // Restricción de rango de año y mensajes de error específicos
        if (anioInt < 1920) {
            mensaje("Es muy viejo para registrarse. Intente nuevamente.\n");
            return;
        } else if (anioInt > 2005) {
            mensaje("Es menor de edad. No puede registrarse.\n");
            return;
        }

        mensaje("Sexo (F/M):");
        String sexo = scanner.nextLine();
        if (!sexo.matches("[MFmf]")) {
            System.out.println("Sexo invalido, Intente nuevamente");
            return;
        }

        mensaje("Telefono:\n");
        System.out.print(verde + "+51");
        String telefono = scanner.nextLine();
        if (!telefono.matches("9\\d{8}")) {
            mensaje("Teléfono inválido. Debe comenzar con 9 y tener 9 dígitos. Intente nuevamente.");
            return;
        }
        // Solicitar y validar el día de nacimiento
            System.out.print("Usuario:");
        String usuario = scanner.nextLine();

        // Verificar si el DNI o el nombre de usuario ya están registrados
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\JOSELOL\\Documents\\fundamentos Programacion\\practicadeprogramador\\primer ciclo\\mis proyectos\\proyectos completos\\LUE_DRINK\\usuarios.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length > 10) {
                    if (datos[0].equals(dni)) {
                        System.out.println("DNI ya registrado. Intente con otro.");
                        return;
                    }
                    if (datos[9].equals(usuario)) {
                        System.out.println("Nombre de usuario ya registrado. Intente con otro nombre de usuario.");
                        return;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de usuarios: " + e.getMessage());
            return;
        }

        // Solicitar y validar la contraseña
        System.out.println("Contraseña (más de 6 dígitos):");
        String contrasena = scanner.nextLine();
        if (contrasena.length() <= 6) {
            System.out.println("Contraseña inválida. Debe tener más de 6 dígitos. Intente nuevamente");
            return;
        }

        // Guardar los datos en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\JOSELOL\\Documents\\fundamentos Programacion\\practicadeprogramador\\primer ciclo\\mis proyectos\\proyectos completos\\LUE_DRINK\\usuarios.txt", true))) {
            writer.write(dni + "," + nombres + "," + apellidoPaterno + "," + apellidoMaterno + "," + dia + "," + mes + "," + anio + "," + sexo + "," + telefono + "," + usuario + "," + contrasena);
            writer.newLine();
            System.out.println("Usuario registrado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos del usuario: " + e.getMessage());
        }
    }
// Función para iniciar sesión
    private static void iniciarSesion() throws AWTException {
        Robot r = new Robot();
        int contador = 0;

        do {
            mensaje(verde + "-----------------------------------------------\n");
            mensaje("                 iniciar seion                 \n");
            mensaje(verde + "-----------------------------------------------\n");

            System.out.println("Ingrese su nombre de usuario:");
            String usuario = scanner.nextLine();
            System.out.println("Ingrese su contraseña:");
            String contrasena = scanner.nextLine();

            // Verificar si el nombre de usuario y la contraseña son correctos
            boolean usuarioValido = false;
            try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\JOSELOL\\Documents\\fundamentos Programacion\\practicadeprogramador\\primer ciclo\\mis proyectos\\proyectos completos\\LUE_DRINK\\usuarios.txt"))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length > 10 && datos[9].equals(usuario) && datos[10].equals(contrasena)) {
                        usuarioValido = true;
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo de usuarios: " + e.getMessage());
            }
            // Si el usuario es válido, mostrar el menú de opciones
            if (usuarioValido) {
                System.out.println("Inicio de sesión exitoso.");
                mostrarMenuOpciones();  
            } else {
                // Si el usuario no es válido, incrementar el contador e intentar de nuevo
                System.out.println("Usuario o contraseña incorrectos. Intente nuevamente.");
                contador++;
            }
            // Si se alcanzan tres intentos fallidos, esperar 15 segundos
            if (contador == 3) {
                System.out.println("espere unos segundos");
                r.delay(15000);
                contador = 0;
                mostrarMenuPrincipal();
            }
        } while (contador < 4);
    }

    // Función para cargar datos desde un archivo
    private static void cargarDatos() {
        // Intenta abrir y leer el archivo de usuarios
        try (Scanner fileScanner = new Scanner(new File("usuarios.txt"))) {
            // Lee cada línea del archivo hasta el final
            while (fileScanner.hasNextLine()) {
                String linea = fileScanner.nextLine();
                // Divide la línea en datos separados por comas
                String[] datos = linea.split(",");
                 // Verifica que hay suficientes datos en la línea
                if (datos.length >= 12) {
                    String usuario = datos[10];
                    String contrasena = datos[11];
                    // Añade el usuario y la contraseña a las listas
                    usuarios.add(usuario);
                    contrasenas.add(contrasena);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de datos no encontrado. Se creará uno nuevo al registrar un usuario.");
            e.printStackTrace();
        } 
       }
    
    //función para mostrar Menu de opciones
    private static void mostrarMenuOpciones() {

        int opcion;
        // Bucle para mantener el menú hasta que el usuario decida salir
        do {
            mensaje(verde + "-----------------------------------------------\n");
            mensaje("                 Menu de opciones               \n");
            mensaje(verde + "-----------------------------------------------\n");

            mensaje("1. Catalogo\n2. Carrito de Compras\n3. Nosotros\n4. Contactenos\n0. Salir\n");
            mensaje("elija una opcion:");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    mostrarCatalogo();
                    break;
                case 2:
                    mostrarCarrito();
                    break;
                case 3:
                    mostrarNosotros();
                    break;
                case 4:
                    mostrarContactenos();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    mensaje("Opción no válida. Intente nuevamente.");
            }
          // El bucle se repite hasta que el usuario elija salir
        } while (opcion != 0);

    }
    
    private static void mostrarNosotros() {
        mensaje("Nosotros");
        mensaje("LuesDrink es una empresa dedicada a la venta de bebidas de todo tipo");
        mensaje("Nuestro compromiso es ofrecer productos de alta calidad a nuestros clientes");
        mensaje("Fundado en 2020, LuesDrink se ha convertido en un referente en el mercado de bebidas");
        mensaje("");
        mensaje("coloque cualquiero numero o letra para salir");
        String numLetra = capturartexto();
        
    }

    private static void mostrarContactenos() {

        mensaje("Contactenos");
        mensaje("Telefonos: 925 192 911, 951 360 586");
        mensaje("Correo. luesdrink@gmail.com");
        mensaje("Horario de atencion: Lunes a Viernes, de 9am a 6pm");
        mensaje("coloque cualquiero numero o letra para salir");
        String numLetra = capturartexto();
        

    }

    private static void mostrarCatalogo() {

        int opcion;
        do {
            mensaje(verde + "-----------------------------------------------\n");
            mensaje("                 categorias                    \n");
            mensaje(verde + "-----------------------------------------------\n");

            mensaje("1. Gaseosas\n2. Aguas\n3. Jugos\n4. Bebidas energizantes\n5. Bebidas lacteas\n0. Retroceder\n");
            mensaje("elija una opcion:");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    mostrarGaseosas();
                    break;
                case 2:
                    mostrarAguas();
                    break;
                case 3:
                    mostrarJugos();
                    break;
                case 4:
                    mostrarBebidasEnergizantes();
                    break;
                case 5:
                    mostrarBebidasLacteas();
                    break;
                case 0:
                    mostrarMenuOpciones();

                    return;
                default:
                    mensaje("Opcion invalida. Intente nuevamente");
            }
          // El bucle se repite hasta que el usuario elija retroceder   
        } while (opcion != 0);

    }
    // función para mostrar los productos en el carrito y permitir procesar la compra
    private static void mostrarCarrito() {
        

        mensaje(verde + "-----------------------------------------------\n");
        mensaje("             carrito de compras                \n");
        mensaje(verde + "-----------------------------------------------\n");
       // Verificamos si el carrito está vacío 
       if (carritoProductos.isEmpty()) {
        System.out.println("El carrito está vacío");
    } else {
        double total = 0;
        // Recorremos los productos en el carrito y mostramos la información
        for (int i = 0; i < carritoProductos.size(); i++) {
             // Mostramos la cantidad, el nombre y el precio de cada producto
            System.out.println(carritoCantidad.get(i) + " x " + carritoProductos.get(i) + " - S/." + carritoPrecios.get(i));
            // Calculamos el subtotal por producto
            total = carritoCantidad.get(i) * carritoPrecios.get(i);
        }
        // Mostramos el total del carrito
        System.out.println("Total del carrito: S/. " + total);
        
        int opcion;
        // Bucle para permitir al usuario decidir si quiere realizar la compra
        do {
            mensaje("Desea realizar la compra?");
            mensaje("1. Sí");
            mensaje("2. No");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    procesoPago();
                    break;
                case 2:
                    // Si decide no comprar, también limpia el carrito
                    limpiarCarrito();
                    mostrarMenuOpciones();
                    break;
                default:
                    mensaje("Opción no válida, inténtelo nuevamente");
            }
        } while (opcion != 2);
    }  

    }
        private static void limpiarCarrito() {
    carritoProductos.clear();
    carritoCantidad.clear();
    carritoPrecios.clear();
    System.out.println("El carrito ha sido vaciado.");
} 
        private static void procesoPago() {
             int opcion;
    do {
        mensaje(verde + "-------------------------------------------\n");
                mensaje("        Elije el Proceso de Pago             ");
                mensaje("      que el cliente desea realizar          ");
        mensaje(verde + "-------------------------------------------\n");
        mensaje("1.Tarjeta/Debito \n2.Yape \n3.Plin \n4.Efectivo \n0.No hay opcion valida");
        opcion = capturarnumero();
        
        switch (opcion) {
            case 1:
                System.out.println("ingresar tarjeta a la maquina POS(Point Of Sale) y confirmar el pago");
                confirmarPagoYmetododeEnvio();
                break;

            case 2:
                System.out.println("Debe yapear al numero: 925192911");
                confirmarPagoYmetododeEnvio();
                break;
            case 3:
                System.out.println("debe plinear al numero: 925192911");
                confirmarPagoYmetododeEnvio();
                break;
            case 4:
                System.out.println("ingresar el efectivo a la caja");
                confirmarPagoYmetododeEnvio();
                break;
            case 0:
                mensaje("gracias por la visita");
                mostrarMenuOpciones();
                break;
            default:
                mensaje("opcion no valida, intentalo nuevamente");
        }
    } while (opcion != 0);
    
   
}
    


    private static void confirmarPagoYmetododeEnvio() {
        int opcion, desicion,eleccion;
       
       
        do{
        mensaje("se realizo el pago?");
        mensaje("1.si\n2.no");
        opcion = capturarnumero();
        switch (opcion) {
            case 1:
                do{
                mensaje("como desea la entrega ?");
                mensaje("1.Delivery\n2.Presencial");
                desicion = capturarnumero();
                switch (desicion) {
                    case 1:               
                        System.out.print("ingrese Direccion:");
                        String direccion = capturartexto();
                        System.out.print("ingrese una Referencia por donde vive:");
                        String referencia = capturartexto();
                mensaje(verde + "****************");
                        mensaje("* DESTINO DE LOS PRODUCTOS.                   ");
                        mensaje("* direccion: "+direccion+"                    ");
                        mensaje("* Referencia: "+referencia +"                 ");
                        mensaje("*preductos comprados:"+carritoCantidad + "X" + carritoProductos);
                        mensaje("*Importe Total:- S/." + carritoPrecios);
                mensaje(verde + "****************");
                        mostrarMenuOpciones();
                        
                        break;

                    case 2:
                mensaje(verde + "*****************\n");
                        mensaje("*preductos comprados:"+carritoCantidad + "X" + carritoProductos);
                        mensaje("*Importe Total:- S/." + carritoPrecios);
                        mensaje("");
                        mensaje("Entregar producto y pedir gracias por la compra");
                mensaje(verde + "*****************\n");
                        mostrarMenuOpciones();
                        break;

                    default:
                       mensaje("opcion no valido, intente nuevamente");


              
              }
                }while(opcion !=2 );
                break;
              case 2:
              do{    
               mensaje("desea realizar con otro metodo de pago?");
               mensaje("1.si \n2.no");
            eleccion = capturarnumero();
                  switch (eleccion) {
                      case 1:
                          procesoPago();
                          break;
                      case 2:
                          mensaje("decir gracias por la visita");
                          mostrarMenuOpciones();
                          break;     
                      default:
                         mensaje("opcion no valida, intente nuevamente");                         
                  }             
              }while(opcion !=2 );
              
                break;

              
              default:
                  System.out.println("opcion no valida, intente nuevamente");
              
              
            }

        } while (opcion != 2);
    }
    

    private static void mostrarGaseosas() {
        int opcion;

        do {
            System.out.println("Gaseosas:");
            System.out.println("1. Inca-Cola\n2. Coca-cola\n3. Fanta\n0. Retroceder");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    seleccionarProducto("Inca-Cola", new String[]{"Botella 600ml Paquete 12un s/ 25.90",
                        "Botella 2.25L Paquete 6un S/41.90", "Botella 3L Paquete 3un S/ 32.90"});
                    break;
                case 2:
                    seleccionarProducto("Coca-cola", new String[]{"Botella 600ml Paquete 12un s/ 25.50",
                        "Botella 1.25L Paquete 6un S/33.90", "Botella 3L Paquete 4un S/ 33.00"});
                    break;
                case 3:
                    seleccionarProducto("Fanta", new String[]{"Botella 500ml Paquete 12un s/ 17.90",
                        "Botella 2.25L Paquete 6un S/35.90", "Botella 3L Paquete 3un S/ 31.70"});
                    break;
                case 0:
                    mostrarCatalogo();
                    return;
                default:
                    System.out.println("Opcion no valida - Intentelo nuevamente");
            }
        } while (opcion != 0);
    }

    private static void mostrarAguas() {
        int opcion;
        do {
            System.out.println("Aguas:");
            System.out.println("1. Agua San Luis\n2. Aguas San Carlos\n3. Agua Cielo\n0. Retroceder");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:

                    seleccionarProducto("Agua San Luis", new String[]{"Botella 600ml Paquete 6un s/. 12",
                        "Botella 600ml Paquete 15un S/16.00", "Botella 2.5L Paquete 6un S/ 15.10"});
                    break;
                case 2:
                    seleccionarProducto("Agua San Carlos", new String[]{"Botella 500ml Paquete 28un s/ 25.50",
                        "Botella 1L Paquete 6un S/15.50", "Botella 3L Paquete 4un S/ 17.00"});
                    break;
                case 3:
                    seleccionarProducto("Agua Cielo", new String[]{"Botella 25ml Paquete 15un s/ 11.50",
                        "Botella 1L Paquete 6un S/14.50", "Botella 2.5L Paquete 6un S/ 16.10"});
                    break;
                case 0:
                    mostrarCatalogo();
                    return;
                default:
                    System.out.println("Opcion no valida - Intentelo nuevamente");
            }
        } while (opcion != 0);

    }

    private static void mostrarJugos() {

        int opcion;
        do {
            mensaje("Jugos:");
            System.out.println("1. Bebidas PULP\n2. Frugos Fresh Tropical\n0. Retroceder");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    seleccionarProducto("Bebida PULP", new String[]{"Botella 145ml Paquete 12un s/. 8.50",
                        "Botella 315ml Paquete 6un S/7.40", "Botella 1.5L Paquete 6un S/ 12.00"});
                    break;
                case 2:
                    seleccionarProducto("Frugos Fresh Tropical", new String[]{"Botella 200ml Paquete 12un s/ 7.90",
                        "Botella 1L Paquete 6un S/10.40", "Botella 1.5L Paquete 6un S/ 13.50"});
                    break;
                case 0:
                    mostrarCatalogo();
                    return;
                default:
                    mensaje("Opcion no valida - Intentelo nuevamente");
            }
        } while (opcion != 0);

    }

    private static void mostrarBebidasEnergizantes() {

        int opcion;
        do {
            mensaje("Energizantes:");
            mensaje("1. Red Bull\n2. Monster\n0. Retroceder");
            System.out.print("elija un opcion:");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    seleccionarProducto("Red Bull", new String[]{"Lata 250ml Paquete 6un s/. 40.00",
                        "Lata 355ml Paquete 6un S/50.00"});
                    break;
                case 2:
                    seleccionarProducto("Monster", new String[]{"Lata 473ml Paquete 12un s/ 45.00",
                        "Lata 355ml Paquete 6un S/35.00"});
                    break;
                case 0:
                    mostrarCatalogo();
                    return;
                default:
                    mensaje("Opcion no valida - Intentelo nuevamente");

            }
        } while (opcion != 0);

    }

    private static void mostrarBebidasLacteas() {
        int opcion;
        do {
            mensaje("Bebidas Lacteas:");
            mensaje("1. Leche Gloria\n2. Leche Laive\n0. Retroceder");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    seleccionarProducto("Leche Gloria", new String[]{"Botella 400g Paquete 6un s/. 12.00",
                        "Botella 1L Paquete 6un S/20.00", "Botella 3L Paquete 4un S/32.90"});
                    break;
                case 2:
                    seleccionarProducto("Leche Laive", new String[]{"Botella 1L Paquete 6un s/ 15.00",
                        "Botella 3L Paquete 4un S/33.00"});
                    break;
                case 0:
                    mostrarCatalogo();
                    return;
                default:
                    mensaje("Opcion no valida - Intentelo nuevamente");
            }

        } while (opcion != 0);

    }

    private static void seleccionarProducto(String nombreProducto, String[] opciones) {
     System.out.println(nombreProducto + ":");
    for (int i = 0; i < opciones.length; i++) {
        System.out.println((i + 1) + ". " + opciones[i]);
    }
    System.out.println((opciones.length + 1) + ". Retroceder");
    int opcion = scanner.nextInt();
    scanner.nextLine();

    if (opcion > 0 && opcion <= opciones.length) {
        String seleccion = opciones[opcion - 1];
        System.out.println("Ingrese la cantidad");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        // Mejora para extraer el precio de manera más fiable
        int precioIndex = seleccion.lastIndexOf(" S/");
        if (precioIndex != -1) {
            String descripcion = seleccion.substring(0, precioIndex).trim();
            // Extrae texto después de "S/"
            String precioTexto = seleccion.substring(precioIndex + 3).trim(); 
            
                double precio = Double.parseDouble(precioTexto); // Convierte el precio a double
                carritoProductos.add(descripcion);
                carritoCantidad.add(cantidad);
                carritoPrecios.add(precio * cantidad);

                historialCompras.add(cantidad + " x " + descripcion + " - S/." + (precio * cantidad));
                System.out.println("Producto aniadido: " + descripcion + ", Cantidad: " + cantidad + ", Precio total: S/." + (precio * cantidad));
             
        } else {
            System.out.println("No se pudo encontrar el precio en el formato esperado.");
        }
    } else if (opcion == opciones.length + 1) {
        return;  // Retrocede al menú anterior
    } else {
        System.out.println("Opción inválida, intente nuevamente");
    }
}

    private static void mensaje(String mensaje) {

        System.out.println(mensaje);
    }

    private static String capturartexto() {

        Scanner sc = new Scanner(System.in);
        String texto = sc.nextLine();
        return texto;
    }

    private static int capturarnumero() {

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        return num;
    }
    
 



}
