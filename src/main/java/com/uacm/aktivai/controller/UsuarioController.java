package com.uacm.aktivai.controller;

import com.uacm.aktivai.model.Rol;
import com.uacm.aktivai.model.Sucursal;
import com.uacm.aktivai.model.Usuario;

import com.uacm.aktivai.model.repository.RolRepository;
import com.uacm.aktivai.service.RolService;
import com.uacm.aktivai.service.SucursalService;
import com.uacm.aktivai.service.UsuarioService;
import com.uacm.aktivai.utilities.UtileriaImagenesAvatar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Value("${aktivai.ruta.avatars}")
    private String rutaAvatars;

    @Autowired
    private UsuarioService usuariosService;

    @Autowired
    private RolService rolService;

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private RolRepository rolRepository;

    /**
     * PARA USUARIOS EN GENERAL (SUPER USUARIO)
     */

    @GetMapping("/tablaUsuarios")
    public String tableUsers(Model model) {
        logger.info("******** Entrando al metodo tableUsers ********");
        model.addAttribute("usuarios", usuariosService.buscarTodosLosUsuarios());
        //model.addAttribute("roles", rolService.obtenerTodos());
        return "usuario/listaUsuarios";
    }

    @GetMapping("/tablaUsuariosPaginada")
    public String tablaUsuariosPaginada(Model model, Pageable pageable) {
        logger.info("******** Entrando al metodo  tablaUsuariosPaginada ********");
        Page<Usuario> usuariosPaginados = usuariosService.obtenerTodosLosUsuarioPaginados(pageable);
        int totalPages = 0;
        if((Integer)usuariosPaginados.getTotalPages() == null) {
        	model.addAttribute("totalPages", totalPages);
        } else {
        	totalPages = usuariosPaginados.getTotalPages(); 
        	model.addAttribute("totalPages", totalPages);
        }
        model.addAttribute("usuarios", usuariosPaginados);
        model.addAttribute("roles", rolService.obtenerTodos());
        return "listaUsuarios";
    }

    @GetMapping("/crear")
        public String crearUsuario(Usuario usuario) { // es necesario el parametro para manejar los errores
        logger.info("******** Entrando al metodo crearUsuario ********");
        return "usuarios/formularioUsuario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(Usuario usuario, BindingResult bindingResult, RedirectAttributes redirectAttributes, @RequestParam("imagenAvatarUsuario") MultipartFile multiPart) {

        logger.info("******** Entrando al metodo guardarUsuario ********");

        if (bindingResult.hasErrors()){ // si hay errores renderizamos el mismo formulario.
            bindingResult.getAllErrors().forEach(error -> {
                logger.error(error.getDefaultMessage());
            });
            return "tablaUsuariosPaginada";
        }

        if (!multiPart.isEmpty()) {
            String nombreImagen = UtileriaImagenesAvatar.guardarArchivo(multiPart, rutaAvatars);
            if (nombreImagen != null){ // valida si la imagen se subio o no
                usuario.setAvatar(nombreImagen);
            }
        } else {
            usuario.setAvatar("Image_avatar.png");
        }

        // Asignar fecha de creación obtenida del sistema.
        Date fechaSistema = new Date();
        usuario.setFechaCreacion(fechaSistema);
        // Encriptar contraseña de usuario.
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        // Asignar rol de usuario aparte del que ya se eligió.
        List<Rol> roles = usuario.getRoles();
        Rol rolUsuario = new Rol();
        rolUsuario.setId(1L);
        roles.add(rolUsuario);
        usuario.setRoles(roles);
        logger.info("Usuario: " + usuario);
        usuariosService.guardarUsuario(usuario);
        redirectAttributes.addFlashAttribute("msg", "Registro Guardado");

        return "redirect:tablaUsuariosPaginada";
    }

    @PostMapping("/actualizar")
    public String actualizarUsuario(Usuario usuario, BindingResult bindingResult, RedirectAttributes redirectAttributes, @RequestParam("imagenAvatarUsuario") MultipartFile multiPart) {

        logger.info("******** Entrando al metodo guardarUsuario ********");

        if (bindingResult.hasErrors()){ // si hay errores renderizamos el mismo formulario.
            bindingResult.getAllErrors().forEach(error -> {
                logger.error(error.getDefaultMessage());
            });
            return "editarUsuario";
        }

        if (!multiPart.isEmpty()) {
            String nombreImagen = UtileriaImagenesAvatar.guardarArchivo(multiPart, rutaAvatars);
            if (nombreImagen != null){ // valida si la imagen se subio o no
                usuario.setAvatar(nombreImagen);
            }
        }

        // Consultar fecha de creación antes de guardar el objeto actualizado.
        Usuario usuarioAux = usuariosService.buscarPorId(usuario.getId());
        usuario.setFechaCreacion(usuarioAux.getFechaCreacion());
        // Encriptar contraseña.
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        // Asignar rol Usuario por defult aparte del que se seleccionó.
        List<Rol> roles = usuario.getRoles();
        Rol rolUsuario = new Rol();
        rolUsuario.setId(1L);
        roles.add(rolUsuario);
        usuario.setRoles(roles);
        usuariosService.guardarUsuario(usuario);
        redirectAttributes.addFlashAttribute("msg", "Registro se ha actualizado corectamente");

        return "redirect:tablaUsuariosPaginada";
    }

    @GetMapping("/detalle/{id}")
    public String mostrarDetalleUsuario(@PathVariable("id") Long id, Model model) {
        logger.info("******** Entrando al metodo mostrarDetalleUsuario ********");
        Usuario usuario = usuariosService.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        logger.info("Usuario: " + usuario);
        return "detallesUsuario";
    }

    @GetMapping("/eliminar")
    public String eliminarUsuario(@RequestParam("id") int id) {
        logger.info("******** Entrando al metodo eliminarUsuario ********");
        usuariosService.eliminarUsuario(id);
        return "redirect:/usuario/tablaUsuariosPaginada";
    }

    @GetMapping("/editar/{id}")
    public String editarUsario(@PathVariable("id") long id, Model model) {
        logger.info("******** Entrando al metodo editarUsario ********");
        List<Rol> roles = rolService.obtenerTodos();
        model.addAttribute("roles", roles);
        logger.info("Roles: " + roles);
        Usuario usuario = usuariosService.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        logger.info("Usuario: " + usuario);

        return "editarUsuario";
    }

    @GetMapping(path = "/buscar")
    public String buscar(@Param("palabraClave") String palabraClave, Model model, Pageable pageable) {
        logger.info("Entrando al método buscar");
        Page<Usuario> usuarios = usuariosService.buscarPorPalabraClave(palabraClave, pageable);
        int totalPages = 0;
        if((Integer)usuarios.getTotalPages() == null) {
            model.addAttribute("totalPages", totalPages);
        } else {
            totalPages = usuarios.getTotalPages();
            model.addAttribute("totalPages", totalPages);
        }
        model.addAttribute("palabraClave", palabraClave);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", rolService.obtenerTodos());

        return "listaUsuarios";
    }

    /**
     * PARA USUARIO DE TIPO CLIENTE
     */

    @GetMapping("/carteraClientes")
    public String tablaClientes(Model model) {
        logger.info("******** Entrando al metodo tablaClinetes ********");
        List<Usuario> clientes = usuariosService.obtenerTodosLosUsuariosConRolCliente();
        logger.debug("clientes: " + clientes);
        model.addAttribute("clientes", clientes);
        return "listaClientes";
    }

    @GetMapping("/carteraClientes/detalle/{id}")
    public String mostrarDetalleCliente(@PathVariable("id") Long id, Model model) {
        logger.info("******** Entrando al metodo mostrarDetalleCliente ********");
        Usuario cliente = usuariosService.buscarPorId(id);
        model.addAttribute("cliente", cliente);
        logger.info("Cliente: " + cliente);
        return "detallesCliente";
    }

    @GetMapping("/carteraClientes/editar/{id}")
    public String editarCliente(@PathVariable("id") long id, Model model) {
        logger.info("******** Entrando al metodo editarCliente ********");
        //List<Rol> roles = rolService.obtenerTodos();
        //model.addAttribute("roles", roles);
        //logger.info("Roles: " + roles);
        Usuario cliente = usuariosService.buscarPorId(id);
        model.addAttribute("cliente", cliente);
        logger.info("Cliente: " + cliente);

        return "editarCliente";
    }

    @PostMapping("/carteraClientes/actualizar")
    public String actualizarCliente(Usuario cliente, BindingResult bindingResult, RedirectAttributes redirectAttributes, @RequestParam("imagenAvatarUsuario") MultipartFile multiPart) {

        logger.info("******** Entrando al metodo actualizarCliente ********");

        if (!multiPart.isEmpty()) {
            String nombreImagen = UtileriaImagenesAvatar.guardarArchivo(multiPart, rutaAvatars);
            if (nombreImagen != null){ // valida si la imagen se subio o no
                cliente.setAvatar(nombreImagen);
            }
        }

        // Consultar fecha de creación antes de guardar el objeto actualizado.
        Usuario clienteAux = usuariosService.buscarPorId(cliente.getId());
        cliente.setFechaCreacion(clienteAux.getFechaCreacion());
        logger.debug("cliente" + cliente);
        Rol rol = rolRepository.findByNombre("ROLE_CLIENTE");
        logger.debug("rol" + rol);
        List<Rol> roles = new ArrayList<>(1);
        roles.add(rol);
        cliente.setRoles(roles);
        usuariosService.guardarUsuario(cliente);
        redirectAttributes.addFlashAttribute("msg", "Registro se ha actualizado correctamente");

        return "redirect:/usuario/carteraClientes";
    }

    @GetMapping("/carteraClientes/formularioClientes")
    public String formularioClientes() {

        logger.info("******** Entrando al metodo formularioClientes ********");

        return "formularioClientes";
    }

    @PostMapping("/carteraClientes/guardar")
    public String guardarCliente(Usuario cliente, Model model,BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        logger.info("******** Entrando al metodo guardarCliente ********");

        if (bindingResult.hasErrors()){ // si hay errores renderizamos el mismo formulario.
            bindingResult.getAllErrors().forEach(error -> {
                logger.error(error.getDefaultMessage());
            });
            return "/formularioClientes";
        }
        // Asigna avatar por default
        cliente.setAvatar("Image_avatar.png");
        // Asignar fecha de creación obtenida del sistema.
        Date fechaSistema = new Date();
        cliente.setFechaCreacion(fechaSistema);
        // Estatus
        cliente.setHabilitado(true);
        // Encriptar contraseña de usuario.
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        // Asignar rol de de tipo Cliente.
        List<Rol> roles = cliente.getRoles();
        Rol rolCliente = new Rol();
        rolCliente.setId(5L);
        roles.add(rolCliente);
        cliente.setRoles(roles);
        logger.info("Cliente: " + cliente);
        // Creando sucursal temporal, para su llenado.
        Sucursal sucursalTemp = new Sucursal();
        sucursalTemp.setNombre("TEMPORAL");
        sucursalTemp.setCalle("TEMPORAL");
        sucursalTemp.setNumero("TEMPORAL");
        sucursalTemp.setColonia("TEMPORAL");
        sucursalTemp.setCodigoPostal("TEMPORAL");
        sucursalTemp.setAlcaldia("TEMPORAL");
        sucursalTemp.setTelefono("TEMPORAL");
        Usuario UsuarioTemp = usuariosService.guardarUsuario(cliente);
        sucursalTemp.setUsuario(cliente);
        sucursalService.guardarSucursal(sucursalTemp);
        redirectAttributes.addFlashAttribute("msg", "Registro Guardado");
        model.addAttribute("cliente", UsuarioTemp);
        logger.info("UsuarioTemp: " + UsuarioTemp);

        return "formularioSucursalNueva";
    }

    @PostMapping("/actualizarCliente")
    public String actualizarCliente(Usuario cliente) {
        logger.debug("******** Entrando al metodo actualizarCliente ********");
        logger.debug("Cliente = " + cliente);
        // Recuperar datos que ya tenía y no se reciben del formulario.
        Usuario clienteAux = usuariosService.buscarPorId(cliente.getId());
        logger.debug("ClienteAux = " + clienteAux);
        cliente.setAvatar(clienteAux.getAvatar());
        cliente.setHabilitado(clienteAux.isHabilitado());
        cliente.setFechaCreacion(clienteAux.getFechaCreacion());
        cliente.setRoles(clienteAux.getRoles());
        //cliente.setSucursal(clienteAux.getSucursal());
        usuariosService.guardarUsuario(cliente);
        return "redirect:/usuario/carteraClientes";
    }

    @GetMapping(path = "/buscarEnCarteClientes")
    public String buscarEnCarteraClientes(@Param("palabraClave") String palabraClave, Model model, Pageable pageable) {
        logger.info("Entrando al método buscarEnCarteraClientes");
        logger.info("Palabra clave a buscar: " + palabraClave);
        List<Usuario> clientes = usuariosService.buscarClientesPorPalabraClave(palabraClave);
        logger.info("clientes: " + clientes);
        model.addAttribute("palabraClave", palabraClave);
        model.addAttribute("clientes", clientes);
        return "listaClientes";
    }

}
