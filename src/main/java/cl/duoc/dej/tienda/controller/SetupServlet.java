package cl.duoc.dej.tienda.controller;

import cl.duoc.dej.tienda.entity.Categoria;
import cl.duoc.dej.tienda.entity.Cliente;
import cl.duoc.dej.tienda.entity.Pedido;
import cl.duoc.dej.tienda.entity.Producto;
import cl.duoc.dej.tienda.entity.Vendedor;
import cl.duoc.dej.tienda.service.CategoriaService;
import cl.duoc.dej.tienda.service.ClienteService;
import cl.duoc.dej.tienda.service.PedidoBuilder;
import cl.duoc.dej.tienda.service.PedidoService;
import cl.duoc.dej.tienda.service.ProductoService;
import cl.duoc.dej.tienda.service.VendedorService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SetupServlet", urlPatterns = {"/setup"})
public class SetupServlet extends HttpServlet {

    @EJB
    CategoriaService categoriaService;

    @EJB
    ProductoService productoService;
    
    @EJB
    VendedorService vendedorService;
    
    @EJB
    ClienteService clienteService;
    
    @EJB
    PedidoService pedidoService;
    
    @EJB
    PedidoBuilder pedidoBuilder;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> mensajes = new ArrayList<>();
        List<String> errores = new ArrayList<>();

        try {
            Vendedor vendedor = new Vendedor("Jesse Pinkman", 18358591, 'k', "jpikman", "1234");
            vendedorService.crearVendedor(vendedor);
            mensajes.add( String.format("Vendedor %s creado con ID %s", vendedor.getUsuario()+":"+vendedor.getContrasena(), vendedor.getId()) );
 
            Calendar fechaNacimiento = Calendar.getInstance();
            fechaNacimiento.add(Calendar.YEAR, -20);
            Cliente cliente = new Cliente("Paolo Silva", 12345678, '5', "San Carlos 1234", "Puente Alto", fechaNacimiento);
            clienteService.crearCliente(cliente);
            mensajes.add(String.format("Cliente %s creado con ID %s", cliente.getNombre(), cliente.getId()));

            String imagen = "https://www.dinafem.org/media/photologue/photos/cache/Dinafem_Moby_Dick_blog_cdn.jpg";
            String descripcion = "- Moby Dick\n"
                    + "- Una planta de gran porte, alta producción, cogollos duros, resistente a los hongos y aroma \n"
                    + " inconfundible a Haze e incienso. En 2010 Soft Secrets la consideró “Girl Of The Year”, y\n"
                    + " después de eso, esta Dinafem Girl ha ido acumulando un gran historial de premios en copas\n"
                    + " cannábicas. Cuando el breeder de Dinafem King Kush se atrevió en cruzar una White Widow, \n"
                    + " nacida de un clon de élite de 1997, con una Haze, no sabía que estaba creando una variedad de\n"
                    + " marihuana que se convirtió en poco tiempo en una planta de culto y un referente dentro del \n"
                    + " sector del cannabis mundial.\n"                    + " .";

            Categoria categoria = new Categoria("Indica", "");
            categoria = categoriaService.crearCategoria(categoria);
            mensajes.add( String.format("Categoría %s creada con ID %s", categoria.getNombre(), categoria.getId()) );
            
            categoria = new Categoria("Sativa", "");
            categoria = categoriaService.crearCategoria(categoria);
            mensajes.add( String.format("Categoría %s creada con ID %s", categoria.getNombre(), categoria.getId()) );

            Producto p = new Producto("Moby Dick", imagen, descripcion, 29990L, categoria);
            p = productoService.crearProducto(p);
            Producto anillas = p;
            mensajes.add( String.format("Producto %s creado con ID %s", p.getNombre(), p.getId()) );

            descripcion = "White Widow\n"
                    + "La semilla de marihuana White Widow ha supuesto, desde hace más de 20 años, un verdadero\n"
                    + "hito para muchos aficionados al cannabis. Este hecho no es casual y se debe en gran parte a sus\n"
                    + "reconocidas propiedades relajantes y terapéuticas. Se trata de una magnífica planta con una \n"
                    + "estructura compacta y vigorosa que se cultiva con facilidad, cuya floración es corta y que \n"
                    + "produce densos cogollos repletos de resina.";
            imagen = "https://www.dinafem.org/media/photologue/photos/cache/Dinafem_White_Widow_blog_cdn.jpg";
            p = new Producto("White Widow", imagen, descripcion, 199000L, categoria);
            p = productoService.crearProducto(p);
            Producto bicicleta = p;

            mensajes.add( String.format("Producto %s creado con ID %s", p.getNombre(), p.getId()) );
            
            
            Pedido pedido = pedidoBuilder.setCliente(cliente.getId())
                        .setVendedor(vendedor.getId())
                        .agregarProducto(anillas.getId(), 2)
                        .agregarProducto(bicicleta.getId(), 1)
                        .build();
            pedidoService.crearPedido(pedido);
            
        } catch (Exception e) {
            errores.add(e.getMessage());    
        }

        request.setAttribute("mensajes", mensajes);
        request.setAttribute("errores", errores);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

}
