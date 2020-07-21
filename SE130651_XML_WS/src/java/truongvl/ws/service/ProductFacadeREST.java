/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.ws.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import truongvl.ws.Product;

/**
 *
 * @author Nero
 */
@Stateless
@Path("truongvl.ws.product")
public class ProductFacadeREST extends AbstractFacade<Product> {

    private static final int PER_PAGE = 99;
    
    @PersistenceContext(unitName = "SE130651_XML_WSPU")
    private EntityManager em;

    public ProductFacadeREST() {
        super(Product.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Product entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Product entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Product find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Product> findAll() {
        return super.findAll();
    }

    @GET
    @Path("/findAllOfUser/{username}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Product> findAllOfUser(@PathParam("username") String username) {
        String queryString = "SELECT a FROM Product a "
                + " WHERE a.id NOT IN (SELECT b.productId.id FROM ChoiceOfUser b WHERE b.accountId.id = :username)";
        return em.createQuery(queryString, Product.class).setParameter("username", username).getResultList();
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Product> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("/getTrendingProduct")
    @Produces({MediaType.APPLICATION_XML})
    public List<Product> getTrendingProduct() {
        String queryString = "SELECT a FROM Product a WHERE a.point > 0 ORDER BY a.point DESC";
        return em.createQuery(queryString, Product.class).setMaxResults(20).getResultList();
    }
    
    @GET
    @Path("/getProductWithPagination/{page}/{order}/{username}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Product> getProductWithPagination(@PathParam("page") Integer page, @PathParam("order") String order, @PathParam("username") String username) {
        String queryString = "SELECT a FROM Product a "
                + " WHERE a.id NOT IN (SELECT b.productId.id FROM ChoiceOfUser b WHERE b.accountId.id = :username)"
                + " ORDER BY a." + order;
        return em.createQuery(queryString, Product.class).setParameter("username", username).setFirstResult((page - 1) * PER_PAGE).setMaxResults(PER_PAGE).getResultList();
    }
    
    @GET
    @Path("/getMaxPage")
    @Produces({MediaType.TEXT_PLAIN})
    public Integer getMaxPage() {
        String queryString = "SELECT COUNT(a.id) FROM Product a";
        return (int) Math.ceil((em.createQuery(queryString, Long.class).getSingleResult() + 0.0) / PER_PAGE);
    }
}
