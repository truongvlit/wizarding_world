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
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import truongvl.ws.ChoiceOfUser;
/**
 *
 * @author Nero
 */
@Stateless
@Path("truongvl.ws.choiceofuser")
public class ChoiceOfUserFacadeREST extends AbstractFacade<ChoiceOfUser> {

    @PersistenceContext(unitName = "SE130651_XML_WSPU")
    private EntityManager em;

    public ChoiceOfUserFacadeREST() {
        super(ChoiceOfUser.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(ChoiceOfUser entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, ChoiceOfUser entity) {
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
    public ChoiceOfUser find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ChoiceOfUser> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ChoiceOfUser> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("/findByUserId/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public List<ChoiceOfUser> findByUserId(@PathParam("id") String id) {
        String queryString = "SELECT a FROM ChoiceOfUser a WHERE a.accountId.id = :id";
        TypedQuery<ChoiceOfUser> query = getEntityManager().createQuery(queryString, ChoiceOfUser.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    @GET
    @Path("/findByUserIdAndProductId/{userId}/{productId}")
    @Produces({MediaType.TEXT_PLAIN})
    public String findByUserIdAndProductId(@PathParam("userId") String userId, @PathParam("productId") Integer productId) {
        String queryString = "SELECT a FROM ChoiceOfUser a WHERE a.accountId.id = :userId AND a.productId.id = :productId";
        TypedQuery<ChoiceOfUser> query = getEntityManager().createQuery(queryString, ChoiceOfUser.class);
        query.setParameter("userId", userId);
        query.setParameter("productId", productId);
        return query.getSingleResult().getId().toString();
    }
    
    @POST
    @Path("/delete/{id}")
    @Produces({MediaType.TEXT_PLAIN})
    public Integer delete(@PathParam("id") Integer id) {
        return em.createQuery("DELETE FROM ChoiceOfUser c WHERE c.id = :id").setParameter("id", id).executeUpdate();
    }
}
