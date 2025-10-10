package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;


import java.util.*;
public class ZonaFitApp {

    static Scanner sc = new Scanner(System.in);
    static IClienteDAO cliente = new ClienteDAO();

    public static void main(String[] args) {
        zonaFitApp();
    }

    public static void zonaFitApp(){
        int op = 0;
        while(op!=6){
            op = mostrarMenu();

            //opciones del menu
            switch (op){
                case 1 -> {
                    mostrarClientes();
                }
                case 2 ->{
                    buscarCliente();
                }
                case 3 -> {
                    agregarClienteMenu();
                }
                case 4 ->{
                    modificarClienteMenu();
                }
                case 5  -> {
                    eliminarClienteMenu();
                }
                case 6 ->{
                    System.out.println("Saliendo ...");
                }
            }


        }
    }

    public static int mostrarMenu(){

        System.out.println("""
                1. Mostrar Lista de clientes
                2. Buscar clientes por ID
                3. Agregar cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                """);
        int op;
        while(true) {
            op = validacion_Opciones("Elige tu opción deseada: ", Integer.class);
            if(op >= 1 && op <= 6) {
                return op;  // ← Solo retorna si es válido
            }
            System.out.println("Opción inválida. Debes ingresar un número entre 1 y 6\n");
        }
    }


    public static <T> T validacion_Opciones(String mensaje, Class<T> tipo) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("No puedes dejarlo vacío");
                continue;
            }

            try {
                if (tipo == Integer.class) {
                    return tipo.cast(Integer.parseInt(entrada));
                } else if (tipo == String.class) {
                    return tipo.cast(entrada);
                } else {
                    throw new IllegalArgumentException("Tipo no soportado");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ser un número válido");
            }
        }
    }


    public static void mostrarClientes(){

        cliente.listarCliente().forEach(System.out::println);
    }

    public static void buscarCliente(){
        int id = validacion_Opciones("Ingresa el id que quieres buscar: ",Integer.class);

        Cliente clienteAux = new Cliente(id);

        boolean encontrado= cliente.buscarClientePorId(clienteAux);
        if(encontrado){
            System.out.println("Cliente encontrado");
            System.out.println(clienteAux);
        }else{
            System.out.println("Error el cliente no existe");
        }
    }

    public static void agregarClienteMenu(){
        String nombre = validacion_Opciones("Ingresa el nombre del cliente: ", String.class);
        String apellido = validacion_Opciones("Ingresa el apellido del cliente: ",String.class);
        int membresia = validacion_Opciones("Ingresa la membresia del cliente: ", Integer.class);


        Cliente clienteAux = new Cliente(nombre, apellido, membresia);

        boolean agregado = cliente.agregarCliente(clienteAux);
        if(agregado){
            System.out.println("Agregado con exito");
        }else{
            System.out.println("Error al agregar al cliente");
        }
    }

    public static void modificarClienteMenu(){
        int idModificar = validacion_Opciones("Ingresa el id del cliente que quieres modificar: ", Integer.class);

        Cliente cliente_Aux = new Cliente(idModificar);
        boolean flag = cliente.buscarClientePorId(cliente_Aux);
        if(flag){
            System.out.println("Cliente encontrado");
            System.out.println(cliente_Aux);
            String nombre = validacion_Opciones("Ingresa el nombre del cliente: ", String.class);
            String apellido = validacion_Opciones("Ingresa el apellido del cliente: ",String.class);
            int membresia = validacion_Opciones("Ingresa la membresia del cliente: ", Integer.class);

            Cliente clienteAux = new Cliente(idModificar,nombre,apellido,membresia);
            boolean modificado = cliente.modificarCliente(clienteAux);
            System.out.println("Modificacion con exito");
        }else{
            System.out.println("Cliente no encontrado");

        }

    }

    public static void eliminarClienteMenu(){
        int idEliminar = validacion_Opciones("Ingresa el id del cliente que quieres eliminar: ", Integer.class);

        Cliente cliente_Aux = new Cliente(idEliminar);
        boolean flag = cliente.buscarClientePorId(cliente_Aux);
        if(flag){
            String confirmacion = validacion_Opciones("Estas seguro de eliminar si/no: ",String.class);
            if(confirmacion.equalsIgnoreCase("si")) {
                Cliente clienteAux = new Cliente(idEliminar);
                boolean eliminado = cliente.eliminarCliente(clienteAux);
                System.out.println("Eliminado con exito");
            }else{
                System.out.println("eliminacion cancelada");
                return;
            }
        }else{
            System.out.println("Cliente no encontrado");
        }
    }

}
