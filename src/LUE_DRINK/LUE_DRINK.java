package LUE_DRINK;

// Importamos las clases necesarias de la biblioteca Java
import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    private static ArrayList<Integer> carritoPrecios = new ArrayList<>();
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

        MenuInicio();
    }
    //opción de registrarse e iniciar sesión en la que el cajero tendrá que elegir

    private static void mostrarMenuPrincipal() throws AWTException {

        int opcion, dia, mes, anio;
        String dni, telefono, nombres, apellidoPaterno, apellidoMaterno, sexo, usuario;
     
        do {

            mensaje(verde + "-----------------------------------------------\n");
            mensaje("                 MENU DE INICIO                \n");
            mensaje(verde + "-----------------------------------------------\n");

            System.out.println("1. Registro \n2. Iniciar Sesion\nelija una opción: ");
            opcion = capturarnumero();

            // Evaluamos la opción seleccionada y llamamos al método correspondiente
            switch (opcion) {
                case 1 -> {
                    // Solicitar el DNI al usuario y validar que tenga 8 dígitos numerico    
                    do {
                        System.out.print("DNI: ");
                        dni = capturartexto();
                        if (!dni.matches("\\d{8}")) {
                            System.out.println("DNI invalido. Debe contener exactamente 8 dígitos numéricos. Intente nuevamente.");
                        }
                    } while (!dni.matches("\\d{8}"));
                    do {
                        System.out.print("Nombre: ");
                        nombres = capturartexto();
                        if (!nombres.matches("[a-zA-Z\\s]+")) {
                            System.out.println("Nombres invalidos. Debe contener solo letras y espacios. Intente nuevamente.");
                        }
                    } while (!nombres.matches("[a-zA-Z\\s]+"));

                    do {
                        System.out.print("Apellido Paterno: ");
                        apellidoPaterno = capturartexto();
                        if (!apellidoPaterno.matches("[a-zA-Z\\s]+")) {
                            System.out.println("Apellido Paterno invalido. Debe contener solo letras y espacios. Intente nuevamente.");
                        }
                    } while (!apellidoPaterno.matches("[a-zA-Z\\s]+"));

                    do {
                        System.out.print("Apellido Materno: ");
                        apellidoMaterno = capturartexto();
                        if (!apellidoMaterno.matches("[a-zA-Z\\s]+")) {
                            System.out.println("Apellido Materno invalido. Debe contener solo letras y espacios. Intente nuevamente.");
                        }
                    } while (!apellidoMaterno.matches("[a-zA-Z\\s]+"));

                    do {
                        System.out.print("Dia de nacimiento: ");
                        dia = capturarnumero();
                        if (dia <= 0 || dia > 31) {
                            System.out.println("Dia invalido. Debe ser un número entre 1 y 31. Intente nuevamente.");
                        }
                    } while (dia <= 0 || dia > 31);

                    do {
                        System.out.print("Mes de nacimiento: ");
                        mes = capturarnumero();
                        if (mes <= 0 || mes > 12) {
                            System.out.println("Mes invalido. Debe ser un número entre 1 y 12. Intente nuevamente.");
                        }
                    } while (mes <= 0 || mes > 12);

                    do {
                        System.out.print("Anio de nacimiento: ");
                        anio = capturarnumero();
                        if (anio < 1920 || anio > 2005) {
                            System.out.println("Anio invalido. Debe ser un número entre 1920 y 2005. Intente nuevamente.");
                        }
                    } while (anio < 1920 || anio > 2005);

                    do {
                        System.out.print("Sexo (F/M): ");
                        sexo = capturartexto().toUpperCase();  // Convertimos la entrada a mayúsculas para simplificar la comparación
                        if (!sexo.equals("F") && !sexo.equals("M")) {
                            System.out.println("Sexo inválido. Debe ser 'F' o 'M'. Intente nuevamente.");
                        }
                    } while (!sexo.equals("F") && !sexo.equals("M"));

                    do {
                        System.out.print("Telefono: +51 ");
                        telefono = capturartexto();
                        if (!telefono.matches("9\\d{8}")) {
                            System.out.println("Telefono invalido. Debe comenzar con 9 y tener 9 digitos. Intente nuevamente.");
                        }
                    } while (!telefono.matches("9\\d{8}"));

                    // Solicitar y validar el día de nacimiento
                    System.out.print("Usuario:");
                    usuario = scanner.nextLine();
                    // Verificar si el DNI o el nombre de usuario ya están registrados
                           try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Proyectos\\LUE_DRINK-main\\usuarios.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length > 10) {
                    if (datos[0].equals(dni)) {
                        System.out.println("DNI ya registrado. Intente con otro.");
                        MenuInicio();
                        return;
                    }
                    if (datos[9].equals(usuario)) {
                        System.out.println("Nombre de usuario ya registrado. Intente con otro nombre de usuario.");
                        MenuInicio();
                        return;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de usuarios: ");
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Proyectos\\LUE_DRINK-main\\usuarios.txt", true))) {
            writer.write(dni + "," + nombres + "," + apellidoPaterno + "," + apellidoMaterno + "," + dia + "," + mes + "," + anio + "," + sexo + "," + telefono + "," + usuario + "," + contrasena);
            writer.newLine();
            System.out.println("Usuario registrado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos del usuario: " );
        }
                }
                case 2 -> {
                    Robot r = new Robot();
                    int contador = 0;

                    do {
                        mensaje(verde + "-----------------------------------------------\n");
                        mensaje("                 iniciar seion                 \n");
                        mensaje(verde + "-----------------------------------------------\n");

                        System.out.println("Ingrese su nombre de usuario:");
                        usuario = scanner.nextLine();
                        System.out.println("Ingrese su contraseña:");
                        String contrasena = scanner.nextLine();

                        // Verificar si el nombre de usuario y la contraseña son correctos
                        boolean usuarioValido = false;
                        // Leer el archivo de usuarios para verificar las credenciales
                        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Proyectos\\LUE_DRINK-main\\usuarios.txt"))) {
                            String linea;
                            // Lee cada línea del archivo hasta que no haya más líneas.
                            while ((linea = reader.readLine()) != null) {
                                String[] datos = linea.split(","); // Divide la línea en partes separadas por comas y las almacena en un array.
                                // Verifica si la línea contiene al menos 11 elementos y si los elementos 10 y 11 coinciden con usuario y contraseña
                                if (datos.length > 10 && datos[9].equals(usuario) && datos[10].equals(contrasena)) {
                                    usuarioValido = true; // Establece el usuario como válido si coinciden usuario y contraseña.
                                    break; // Sale del bucle si se encuentra un usuario válido.
                                }
                            }
                        } catch (IOException e) {
                            mensaje("Error al leer el archivo de usuarios: "); // Informa sobre el error al leer el archivo.
                        }

                        // Si el usuario es válido, mostrar el menú de opciones
                        if (usuarioValido) {
                            System.out.println("Inicio de sesión exitoso.");
                            mostrarMenuOpciones(); // Llama al método para mostrar el menú de opciones.
                        } else {
                            // Si el usuario no es válido, incrementar el contador e intentar de nuevo
                            System.out.println("Usuario o contraseña incorrectos. Intente nuevamente.");
                            contador++; // Incrementa el contador de intentos fallidos.
                        }

                        // Si se alcanzan tres intentos fallidos, esperar 15 segundos
                        if (contador == 3) {
                            mensaje("Fallaste 3 intentos");
                            System.out.println("Espere unos 15 segundos.");
                            r.delay(15000); // Espera 15 segundos.
                            contador = 0; // Resetea el contador a 0.
                            MenuInicio(); // Llama al método MenuInicio para volver al menú de inicio.
                        }
                    } while (contador < 4); // Repite el bucle hasta que el contador sea menor que 4.
                }

                default ->
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

        } while (opcion != 2); // Repite el bucle hasta que la opción sea diferente de 2.
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
                    do {
                        mensaje(verde + "-----------------------------------------------\n");
                        mensaje("                 categorias                    \n");
                        mensaje(verde + "-----------------------------------------------\n");

                        mensaje("1. Gaseosas\n2. Aguas\n3. Jugos\n4. Bebidas energizantes\n5. Bebidas lacteas\n0. Retroceder\n");
                        mensaje("elija una opcion:");
                        opcion = capturarnumero();
                        switch (opcion) {
                            case 1:
                                do {
                                    System.out.println("Gaseosas:");
                                    System.out.println("1. Inca-Cola\n2. Coca-cola\n3. Fanta\n0. Retroceder a Menu de opciones\nelija una opcion:");
                                    opcion = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (opcion) {
                                        case 1:
                                            seleccionProducto("Inca-Cola", new String[]{"Botella 600ml Paquete 12un s/25",
                                                "Botella 2L Paquete 6un S/41", "Botella 3L Paquete 3un S/32"});
                                            break;
                                        case 2:
                                            seleccionProducto("Coca-cola", new String[]{"Botella 600ml Paquete 12un s/25",
                                                "Botella 1.25L Paquete 6un S/33", "Botella 3L Paquete 4un S/33"});
                                            break;
                                        case 3:
                                            seleccionProducto("Fanta", new String[]{"Botella 500ml Paquete 12un s/ 17",
                                                "Botella 2.25L Paquete 6un S/35", "Botella 3L Paquete 3un S/31"});
                                            break;
                                        case 0:
                                            mostrarMenuOpciones();
                                        default:
                                            System.out.println("Opcion no valida,Intentelo nuevamente");
                                    }
                                } while (opcion != 0);

                                break;
                            case 2:
                                do {
                                    System.out.println("Aguas:");
                                    System.out.println("1. Agua San Luis\n2. Aguas San Carlos\n3. Agua Cielo\n0. Retroceder a Menu de opciones\nelija una opcion:");
                                    opcion = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (opcion) {
                                        case 1:

                                            seleccionProducto("Agua San Luis", new String[]{"Botella 600ml Paquete 6un s/12",
                                                "Botella 600ml Paquete 15un S/16", "Botella 2.5L Paquete 6un S/ 15"});
                                            break;
                                        case 2:
                                            seleccionProducto("Agua San Carlos", new String[]{"Botella 500ml Paquete 28un s/25",
                                                "Botella 1L Paquete 6un S/15", "Botella 3L Paquete 4un S/17"});
                                            break;
                                        case 3:
                                            seleccionProducto("Agua Cielo", new String[]{"Botella 25ml Paquete 15un s/11",
                                                "Botella 1L Paquete 6un S/14.50", "Botella 2.5L Paquete 6un S/16"});
                                            break;
                                        case 0:
                                            mostrarMenuOpciones();
                                            return;
                                        default:
                                            System.out.println("Opcion no valida, Intentelo nuevamente");
                                    }
                                } while (opcion != 0);

                                break;
                            case 3:
                                do {
                                    mensaje("Jugos:");
                                    System.out.println("1. Bebidas PULP\n2. Frugos Fresh Tropical\n0. Retrocedera Menu de opciones\nelija una opcion:");
                                    opcion = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (opcion) {
                                        case 1:
                                            seleccionProducto("Bebida PULP", new String[]{"Botella 145ml Paquete 12un s/8",
                                                "Botella 315ml Paquete 6un S/7", "Botella 1.5L Paquete 6un S/ 12"});
                                            break;
                                        case 2:
                                            seleccionProducto("Frugos Fresh Tropical", new String[]{"Botella 200ml Paquete 12un s/7",
                                                "Botella 1L Paquete 6un S/10", "Botella 1.5L Paquete 6un S/13"});
                                            break;
                                        case 0:
                                            mostrarMenuOpciones();
                                            return;
                                        default:
                                            mensaje("Opcion no valida, Intentelo nuevamente");
                                    }
                                } while (opcion != 0);

                                break;
                            case 4:
                                do {
                                    mensaje("Energizantes:");
                                    mensaje("1. Red Bull\n2. Monster\n0. Retroceder a Menu de opciones\nelija una opcion:");
                                    System.out.print("elija un opcion:");
                                    opcion = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (opcion) {
                                        case 1:
                                            seleccionProducto("Red Bull", new String[]{"Lata 250ml Paquete 6un s/40",
                                                "Lata 355ml Paquete 6un S/50"});
                                            break;
                                        case 2:
                                            seleccionProducto("Monster", new String[]{"Lata 473ml Paquete 12un s/45",
                                                "Lata 355ml Paquete 6un S/35"});
                                            break;
                                        case 0:
                                            mostrarMenuOpciones();
                                            return;
                                        default:
                                            mensaje("Opcion no valida, Intentelo nuevamente");

                                    }
                                } while (opcion != 0);
                                break;
                            case 5:
                                do {
                                    mensaje("Bebidas Lacteas:");
                                    mensaje("1. Leche Gloria\n2. Leche Laive\n0. Retroceder a Menu de opciones\nelija una opcion:");
                                    opcion = scanner.nextInt();
                                    scanner.nextLine();

                                    switch (opcion) {
                                        case 1:
                                            seleccionProducto("Leche Gloria", new String[]{"Botella 400g Paquete 6un s/12",
                                                "Botella 1L Paquete 6un S/20.00", "Botella 3L Paquete 4un S/32"});
                                            break;
                                        case 2:
                                            seleccionProducto("Leche Laive", new String[]{"Botella 1L Paquete 6un s/15",
                                                "Botella 3L Paquete 4un S/33"});
                                            break;
                                        case 0:
                                            mostrarMenuOpciones();
                                            return;
                                        default:
                                            mensaje("Opcion no valida, Intentelo nuevamente");
                                    }

                                } while (opcion != 0);

                                break;
                            case 0:
                                mostrarMenuOpciones();

                                return;
                            default:
                                mensaje("Opcion invalida, Intente nuevamente");
                        }
                        // El bucle se repite hasta que el usuario elija retroceder   
                    } while (opcion != 0);

                    break;
                case 2:

                    mensaje(verde + "-----------------------------------------------\n");
                    mensaje("             carrito de compras                \n");
                    mensaje(verde + "-----------------------------------------------\n");
                    // Verificamos si el carrito está vacío 
                    if (carritoProductos.isEmpty()) {
                        System.out.println("El carrito está vacío");

                    } else {
                        int total = 0;
                        // Recorremos los productos en el carrito y mostramos la información
                        for (int i = 0; i < carritoProductos.size(); i++) {
                            // Mostramos la cantidad, el nombre y el precio de cada producto
                            System.out.println(carritoCantidad.get(i) + " x " + carritoProductos.get(i) + " - S/." + carritoPrecios.get(i));

                            // Calculamos el subtotal por producto y lo sumamos al total
                            double subtotalProducto = carritoCantidad.get(i) * carritoPrecios.get(i);
                            total += subtotalProducto;
                        }

                        // Mostramos el total del carrito
                        System.out.println("Total del carrito: S/. " + total);
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
                                    carritoProductos.clear();
                                    carritoCantidad.clear();
                                    carritoPrecios.clear();
                                    System.out.println("El carrito ha sido vaciado.");

                                    break;
                                default:
                                    mensaje("Opción no válida, inténtelo nuevamente");
                            }
                        } while (opcion != 2);
                    }
                    break;
                case 3:
                    mensaje("Nosotros");
                    mensaje("LuesDrink es una empresa dedicada a la venta de bebidas de todo tipo");
                    mensaje("Nuestro compromiso es ofrecer productos de alta calidad a nuestros clientes");
                    mensaje("Fundado en 2020, LuesDrink se ha convertido en un referente en el mercado de bebidas");
                    mensaje("");
                    mensaje("coloque cualquier tecla para salir");
                    String numLetra = scanner.nextLine();

                    break;
                case 4:
                    mensaje("Contactenos");
                    mensaje("Telefonos: 925 192 911, 951 360 586");
                    mensaje("Correo. luesdrink@gmail.com");
                    mensaje("Horario de atencion: Lunes a Viernes, de 9am a 6pm");
                    mensaje("");
                    mensaje("coloque cualquier tecla para salir");
                    numLetra = scanner.nextLine();

                    break;
                case 0:
                    mensaje("gracias por usar el programa, !hasta pronto!");
                    System.exit(0);
                    break;
                default:
                    mensaje("Opción no válida, Intente nuevamente.");
            }
            // El bucle se repite hasta que el usuario elija salir
        } while (opcion != 0);

    }

    private static void seleccionProducto(String nombreProducto, String[] opciones) {
        int opcion, cantidad;
        // Muestra el nombre del producto seleccionado    
        System.out.println(nombreProducto + ":");

        for (int i = 0; i < opciones.length; i++) {
            // Muestra las opciones disponibles del producto
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        // Opción para retroceder
        System.out.println((opciones.length + 1) + ". Retroceder\nelija una opcion:");

        opcion = 0;
        while (true) {
            try {
                // Lee la opción elegida por el usuario
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion > 0 && opcion <= opciones.length + 1) {
                    break;
                } else {
                    System.out.println("Opción inválida, intente nuevamente");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no valida, por favor ingrese un número.");
                break;
            }
        }

        if (opcion > 0 && opcion <= opciones.length) {
            // Obtiene la opción seleccionada
            String seleccion = opciones[opcion - 1];
            // Solicita al usuario que ingrese la cantidad
            System.out.println("Ingrese la cantidad");

            cantidad = 0;
            while (true) {
                try {
                    // Lee la cantidad ingresada por el usuario
                    cantidad = Integer.parseInt(scanner.nextLine());
                    if (cantidad > 0) {
                        break;
                    } else {
                        mensaje("Por favor ingrese un número mayor a 0.");
                        mensaje("intente nuevamente: ");
                    }
                } catch (NumberFormatException e) {
                    mensaje("Entrada no valida, por favor ingrese un numero.");
                    mensaje("intente nuevamente: ");
                    // break;: ......
                }
            }

            // Divide la cadena en torno a "S/"
            String[] partes = seleccion.split("S/");
            //la cadena se dividió correctamente en dos partes
            if (partes.length == 2) {
                try {
                    // Convierte el precio a enteros
                    int precio = Integer.parseInt(partes[1].trim());  // Corregido a Integer.parseInt()
                    // Obtiene la descripción del producto
                    String descripcion = partes[0].trim();
                    // Agrega el producto al carrito
                    carritoProductos.add(descripcion);
                    // Agrega la cantidad al carrito
                    carritoCantidad.add(cantidad);
                    // Calcula y agrega el precio total al carrito
                    carritoPrecios.add(precio * cantidad);

                    // Muestra el mensaje de producto añadido
                    System.out.println("Producto añadido: " + descripcion + ", Cantidad: " + cantidad + ", Precio total: S/" + (precio * cantidad));
                } catch (NumberFormatException e) {
                    System.out.println("No se pudo convertir el precio a un número válido.");
                }
            } else {
                System.out.println("No se pudo encontrar el precio en el formato esperado.");
            }
        }
        /*else if (opcion == opciones.length + 1) {
            System.out.println("numero no debe ser igual a tu seleccion");
            // Retrocede al menú anterior
            return;
        }*/

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
        int opcion, desicion, eleccion;

        do {
            mensaje("se realizo el pago?");
            mensaje("1.si\n2.no");
            opcion = capturarnumero();
            switch (opcion) {
                case 1:
                    do {
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
                                mensaje("* direccion: " + direccion + "                    ");
                                mensaje("* Referencia: " + referencia + "                 ");
                                mensaje("*preductos comprados:" + carritoCantidad + "X" + carritoProductos);
                                mensaje("*Importe Total:- S/." + carritoPrecios);
                                mensaje(verde + "****************");
                                carritoProductos.clear();
                                carritoCantidad.clear();
                                carritoPrecios.clear();
                                mostrarMenuOpciones();

                                break;

                            case 2:
                                mensaje(verde + "*****************\n");
                                mensaje("*preductos comprados:" + carritoCantidad + "X" + carritoProductos);
                                mensaje("*Importe Total:- S/." + carritoPrecios);
                                mensaje("");
                                mensaje("Entregar producto y pedir gracias por la compra");
                                mensaje(verde + "*****************\n");
                                carritoProductos.clear();
                                carritoCantidad.clear();
                                carritoPrecios.clear();
                                mostrarMenuOpciones();
                                break;

                            default:
                                mensaje("opcion no valido, intente nuevamente");

                        }
                    } while (opcion != 2);
                    break;
                case 2:
                    do {
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
                    } while (opcion != 2);

                    break;

                default:
                    System.out.println("opcion no valida, intente nuevamente");

            }

        } while (opcion != 2);
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
