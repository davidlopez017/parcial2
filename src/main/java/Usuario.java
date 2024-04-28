import java.util.Scanner;

public class Usuario {
    private String nombre;
    private String correo;
    private String contrasena;

    public Usuario(String nombre, String correo, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean validarNombre() {
        // Validar que el nombre solo contenga letras y espacios
        return nombre.matches("[a-zA-Z ]+");
    }

    public boolean validarCorreo() {
        // Validar un formato básico de correo electrónico
        return correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    public boolean validarContrasena() {
        // Validar la contraseña según ciertos criterios (mínimo 8 caracteres, al menos una letra mayúscula y un número)
        return contrasena.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");
    }
}

public class RegistroUsuarios {
    private static Usuario usuario;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Editar registro de usuario");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    registrarUsuario(sc);
                    break;
                case 2:
                    if (usuario != null) {
                        editarRegistro(sc);
                    } else {
                        System.out.println("No se ha registrado ningún usuario.");
                    }
                    break;
                case 3:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 3);

        sc.close();
    }

    private static void registrarUsuario(Scanner sc) {
        System.out.println("\nRegistro de usuario:");
        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese su correo electrónico: ");
        String correo = sc.nextLine();

        System.out.print("Ingrese su contraseña: ");
        String contrasena = sc.nextLine();

        // Crear un objeto Usuario con los datos ingresados
        usuario = new Usuario(nombre, correo, contrasena);

        // Validar los datos del usuario
        boolean nombreValido = usuario.validarNombre();
        boolean correoValido = usuario.validarCorreo();
        boolean contrasenaValida = usuario.validarContrasena();

        // Mostrar los resultados de la validación
        if (nombreValido && correoValido && contrasenaValida) {
            System.out.println("¡Registro exitoso!");
        } else {
            System.out.println("Registro fallido. Por favor, revise la información ingresada.");
            usuario = null; // Restablecer el usuario actual si el registro falla
        }
    }

    private static void editarRegistro(Scanner sc) {
        System.out.println("\nEdición de registro de usuario:");
        System.out.println("1. Nombre");
        System.out.println("2. Correo electrónico");
        System.out.println("3. Contraseña");
        System.out.print("Seleccione el campo que desea editar: ");
        int opcion = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer de entrada

        switch (opcion) {
            case 1:
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = sc.nextLine();
                usuario.setNombre(nuevoNombre);
                System.out.println("Nombre actualizado exitosamente.");
                break;
            case 2:
                System.out.print("Nuevo correo electrónico: ");
                String nuevoCorreo = sc.nextLine();
                usuario.setCorreo(nuevoCorreo);
                System.out.println("Correo electrónico actualizado exitosamente.");
                break;
            case 3:
                System.out.print("Nueva contraseña: ");
                String nuevaContrasena = sc.nextLine();
                usuario.setContrasena(nuevaContrasena);
                System.out.println("Contraseña actualizada exitosamente.");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
}
