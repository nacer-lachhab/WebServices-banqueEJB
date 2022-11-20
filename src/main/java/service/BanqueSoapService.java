package service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import metier.IBanqueLocal;
import metier.entities.Compte;

//WebService SOAP
@WebService
public class BanqueSoapService {

	@EJB //injection par conteneur d'ejb
	private IBanqueLocal metier;
	
	@WebMethod
	public void versement(
						@WebParam(name = "codeCompte") Long idCompte,
						@WebParam double montant) {
		metier.verser(idCompte, montant);
	}
	
	@WebMethod
	public void retrait(
						@WebParam(name = "codeCompte") Long idCompte,
						@WebParam double montant) {
		metier.retrait(idCompte, montant);
	}
	
	@WebMethod
	public void virement(
						@WebParam(name = "codeCompteSrc") Long idCompteSrc,
						@WebParam(name = "codeCompteDest") Long idCompteDest,
						@WebParam double montant) {
		metier.virement(idCompteSrc,idCompteDest,montant);
	}

	@WebMethod
	public Compte creerCompte(@WebParam(name = "solde")double solde) {
		Compte compte = new Compte();
		compte.setSolde(solde);
		compte.setDateCreation(new Date());
		return metier.addCompte(compte);
	}

	@WebMethod
	public Compte consulterCompte(@WebParam(name = "codeCompte")Long idCompte) {
		return metier.getCompte(idCompte);
	}

	@WebMethod
	public Compte modifierCompte(
							@WebParam(name = "codeCompte")Long idCompte,
							@WebParam(name = "valueCompte")Compte newvalue) {
		return metier.editCompte(idCompte, newvalue);
	}

	@WebMethod
	public void supprimerCompte(@WebParam(name = "codeCompte")Long idCompte) {
		metier.deleteCompte(idCompte);
	}

	@WebMethod
	public List<Compte> listerComptes() {
		return metier.allComptes();
	}
}
