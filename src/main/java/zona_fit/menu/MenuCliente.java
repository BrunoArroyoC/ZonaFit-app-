package zona_fit.menu;

import zona_fit.datos.ClienteDAO;
import zona_fit.dominio.Cliente;


import java.util.*;
public class MenuCliente {
    static Scanner sc;

    public MenuCliente (){
        sc = new Scanner(System.in);
    }

    public void menuClienteDAO(ClienteDAO zona_fit_app){
        int op = 0;
        while(op != 5){
            try {
                System.out.println("--- CONFIGURACION ZONA FIT APP ---");
                System.out.println("""
                        1. Listar Clientes
                        2. Agregar Clientes
                        3. Modificar Clientes
                        4. Eliminar Clientes
                        5. Salir
                        \n""");
                System.out.print("Ingresa la opcion deseada: ");
                op = sc.nextInt();
                sc.nextLine();

                if(op >5){
                    System.out.println("Ingresa una opcion valida");
                    continue;
                }


            }catch (InputMismatchException e){
                System.out.println("Ingresa una opcion valida");
                sc.nextLine();
            }

            switch (op){
                case 1 -> {
                    System.out.println(" *** Listar Cliente ***");
                    mostrarLista(zona_fit_app);
                }
                case 2 ->{
                    var agregado = agregarCliente(zona_fit_app);
                    if(agregado){
                        System.out.println("Agregado con exito");
                    }else{
                        System.out.println("Error al agregar");
                    }
                }
                case 3 -> {
                    var modificado = modificarClienteDB(zona_fit_app);
                    if(modificado){
                        System.out.println("Modificacion con exito");
                    }else{
                        System.out.println("Error al modificar");
                    }
                }
                case 4 -> {
                    var eliminado = eliminarClienteDB(zona_fit_app);
                    if(eliminado){
                        System.out.println("Eliminacion exitosa");
                    }else{
                        System.out.println("Error al eliminar");
                    }
                }
                case 5 -> {
                    System.out.println("Saliendo ...");

                }
            }



        }

    }

    public List mostrarLista(ClienteDAO cliente){
        var lista = cliente.listarCliente();
        return lista;
    }

    public boolean agregarCliente(ClienteDAO cliente){
        var nombreCliente = "";
        var apellidoCliente = "";
        var membresiaCliente = "";
        int intMembresiaCliente;
        do {
            System.out.print("Ingresa el nombre del cliente: ");
            nombreCliente = sc.nextLine().trim();
            if (nombreCliente.isEmpty()){
                System.out.println("Tienes que escrbir el nombre del cliente");
            }
        }while(nombreCliente.isEmpty());

        System.out.println();

        do {
            System.out.print("Ingresa el apellido del cliente: ");
            apellidoCliente = sc.nextLine().trim();
            if(apellidoCliente.isEmpty()){
                System.out.println("Tienes que escrbiri el apellido del cliente");
            }
        }while (apellidoCliente.isEmpty());

        System.out.println();

        while(true) {
            try {
                System.out.print("Ingresa el numero de membresia del cliente: ");
                membresiaCliente = sc.nextLine().trim();
                if (membresiaCliente.isEmpty()) {
                    System.out.println("Ingresa la membresia ");
                    continue;
                }

                intMembresiaCliente = Integer.parseInt(membresiaCliente);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Las letras no estan permitids en las membresias solo numeros");

            }

        }

        Cliente c1 = new Cliente(nombreCliente,apellidoCliente,intMembresiaCliente);

        return cliente.agregarCliente(c1);

    }


    public boolean modificarClienteDB (ClienteDAO cliente){
        cliente.listarCliente();

        var nombreCliente = "";
        var apellidoCliente = "";
        var membresiaCliente = "";
        int intMembresiaCliente;
        var idString = "";
        int idInt;
        do {
            System.out.print("Ingresa el nombre del cliente: ");
            nombreCliente = sc.nextLine().trim();
            if (nombreCliente.isEmpty()){
                System.out.println("Tienes que escrbir el nombre del cliente");
            }
        }while(nombreCliente.isEmpty());

        System.out.println();

        do {
            System.out.print("Ingresa el apellido del cliente: ");
            apellidoCliente = sc.nextLine().trim();
            if(apellidoCliente.isEmpty()){
                System.out.println("Tienes que escrbiri el apellido del cliente");
            }
        }while (apellidoCliente.isEmpty());

        System.out.println();

        while(true) {
            try {
                System.out.print("Ingresa el numero de membresia del cliente: ");
                membresiaCliente = sc.nextLine().trim();
                if (membresiaCliente.isEmpty()) {
                    System.out.println("Ingresa la membresia ");
                    continue;
                }

                intMembresiaCliente = Integer.parseInt(membresiaCliente);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Las letras no estan permitids en las membresias solo numeros");

            }

        }
        System.out.println();

        while(true) {
            try {
                System.out.print("Ingresa el numero de id del cliente: ");
                idString = sc.nextLine().trim();
                if (idString.isEmpty()) {
                    System.out.println("Debes ingresar el id ");
                    continue;
                }

                idInt = Integer.parseInt(idString);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Las letras no estan permitids en las membresias solo numeros");

            }

        }

        Cliente c = new Cliente(idInt,nombreCliente,apellidoCliente,intMembresiaCliente);
        var modificado = cliente.modificarCliente(c);

        return modificado;
    }

    public boolean eliminarClienteDB(ClienteDAO cliente){
        cliente.listarCliente();

        var idString = "";
        int idInt;

        while(true) {
            try {
                System.out.print("Ingresa el numero de id del cliente: ");
                idString = sc.nextLine().trim();
                if (idString.isEmpty()) {
                    System.out.println("Debes ingresar el id ");
                    continue;
                }

                idInt = Integer.parseInt(idString);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Las letras no estan permitids en los ids solo numeros");

            }

        }
        Cliente c = new Cliente(idInt);
        var eliminado = cliente.eliminarCliente(c);
        return eliminado;
    }




    public static void main(String[] args) {
        ClienteDAO cliente = new ClienteDAO();
        MenuCliente m1 = new MenuCliente();
        m1.menuClienteDAO(cliente);

    }




}
