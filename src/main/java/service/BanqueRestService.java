package service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.FormParam;

import metier.IBanqueLocal;
import metier.entities.Compte;

@Stateless
@Path("/compte")
public class BanqueRestService {

	@EJB
	private IBanqueLocal metier;

	@POST
	@Path("/creer")
	@Produces(MediaType.APPLICATION_JSON)
	public Compte addCompte(@FormParam(value ="solde")double solde) {
		Compte compte = new Compte();
		compte.setSolde(solde);
		compte.setDateCreation(new Date());
		return metier.addCompte(compte);
	}

	@GET
	@Path("/get/{codeCte}")
	@Produces(MediaType.APPLICATION_JSON)
	public Compte getCompte(@PathParam(value ="codeCte") Long idCompte) {
		return metier.getCompte(idCompte);
	}

	@PUT
	@Path("/edit")
	@Produces(MediaType.APPLICATION_JSON)
	public Compte editCompte(
				@FormParam(value ="codeCte")Long idCompte,
				@FormParam("newSolde") double newSolde) {
		Compte newvalue = new Compte();
		newvalue.setSolde(newSolde);
		return metier.editCompte(idCompte, newvalue);
	}

	@DELETE
	@Path("/remove/{codeCte}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteCompte(@PathParam(value ="codeCte")Long idCompte) {
		metier.deleteCompte(idCompte);
	}

	@GET
	@Path("/list")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Compte> allComptes() {
		return metier.allComptes();
	}

	@PUT
	@Path("/versement")
	@Produces(MediaType.APPLICATION_JSON)
	//format du rendu dans le header client[application(/json or /xml)]
	public void verser(
						@FormParam(value ="codeCte")Long idCompte,
						@FormParam(value ="montant")double montant) {
		metier.verser(idCompte, montant);
	}

	@PUT
	@Path("/retrait")
	@Produces(MediaType.APPLICATION_JSON)
	public void retrait(
						@FormParam(value ="codeCte")Long idCompte,
						@FormParam(value ="montant")double montant) {
		metier.retrait(idCompte, montant);
	}

	@PUT
	@Path("/virement")
	@Produces(MediaType.APPLICATION_JSON)//retour sous format JSON
	public void virement(
						@FormParam(value ="codeCteSrc")Long idCompteSrc,
						@FormParam(value ="codeCteDest")Long idCompteDest,
						@FormParam(value ="montant")double montant) {
		metier.virement(idCompteSrc,idCompteDest,montant);
	}	
}
