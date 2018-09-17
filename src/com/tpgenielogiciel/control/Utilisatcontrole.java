package com.tpgenielogiciel.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Date;
//import java.util.List;
import java.util.Map;

import com.tpgenielogiciel.model.Utilisatmodel;

public class Utilisatcontrole {

	Utilisatmodel utilisateur = new Utilisatmodel();
	bdd based = new bdd();
	Statement stat;
	ResultSet resultat;
	boolean exist;

	// methode permettant de recherche un utilisateur
	public boolean existe(Utilisatmodel model) {
		String nom = model.getNom();
		String motpass = model.getPasswd();

		try {
			stat = based.connect().createStatement();
			resultat = stat.executeQuery(
					"select id from utilisateur where nom = '" + nom + "' and passwd = '" + motpass + "'");
			if (resultat.next())
				exist = true;
			else
				exist = false;

			System.out.println("voici le resultat");
		} catch (SQLException ex) {
			System.out.println("erreur controleur existence  " + ex.getMessage());
		}
		return exist;
	}
	// methode permettant de determiner l id d un utilisateur a partir de son nom et prennom
	public int idutilisat (String usernam,String motpass)
	{
		int id = 0;


		try {
			stat = based.connect().createStatement();
			resultat = stat.executeQuery("select id from utilisateur where nom = '"+usernam+"' and passwd = '"+motpass+"'");

			if(resultat.next())
				id = resultat.getInt("id");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return id;
	}
	//methode permettant de verifier si un utilisateur est enregistre ou pas

	public int utiliexist (String nom,String prenom)
	{
		int id = 0;


		try {
			stat = based.connect().createStatement();
			resultat = stat.executeQuery("select id from utilisateur where nom = '"+nom+"' and prenom = '"+prenom+"'");

			if(resultat.next()) {
				id = resultat.getInt("id");
				System.out.println("************"+resultat.getInt("id"));
			}



			System.out.println("************************ fonct"+id);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erreur utilexist"+e.getMessage());
		}
		return id;
	}
	// validation du nom
	public boolean existnom(Utilisatmodel model) {
		try {
			stat = based.connect().createStatement();
			resultat = stat.executeQuery("select id from utilisateur where nom ='" + model.getNom() + "'");
			if (resultat.next()) {
				exist = true;
				System.out.println("entrez dedans");

			} else
				exist = false;
		} catch (SQLException ex) {
			System.out.println("erreur de nom" + ex.getMessage());
		}
		return exist;
	}

	// validation du mot de passe
	public boolean existmotpass(Utilisatmodel model) {

		try {
			stat = based.connect().createStatement();
			resultat = stat.executeQuery("select id from utilisateur where passwd = '" + model.getPasswd() + "' ");
			if (resultat.next()) {
				exist = true;
				System.out.println("y a un problem");
			}

			else {
				exist = false;
				System.out.println("est le problem?????");
			}

		} catch (SQLException ex) {
			System.out.println("erreur de mot de pass" + ex.getMessage());
		}
		return exist;
	}

	// methode de determination de status

	public String role(Utilisatmodel model) {
		String fonct = "";
		try {
			stat = based.connect().createStatement();
			resultat = stat.executeQuery("select statut from utilisateur where id = " + model.getId() + " ");
			if (resultat.next())
				fonct = resultat.getString("statut");
		} catch (SQLException ex) {
			System.out.println("erreur statut " + ex.getMessage());
		}
		return fonct;
	}

	// methode d insertion d un nouveau client

	public void ajout(Utilisatmodel mode) {

		try {

			stat = based.connect().createStatement();
			stat.executeUpdate("INSERT INTO utilisateur(nom,prenom,lieunssce,statut,datenssce,access,passwd) values('"
					+ mode.getNom() + "','" + mode.getPrenom() + "','" + mode.getLieunssce() + "','" + mode.getStatut()
					+ "','"+mode.getDatenssce()+"'," + mode.isAccess() + ",'" + mode.getPasswd() + "')");
		} catch (SQLException ex) {
			System.out.println("erreur d insertion " + ex.getMessage());
		}
	}
	// control des longueurs des champs des prenom ou nom
	public boolean contrlnom (String nompr)
	{
		boolean test = false;

		if(nompr.length()<4)
			test = true;

		return test;
	}
	//  controle de la longueur du mot de passe
	public boolean contromotp (String motp)
	{
		boolean test = false;
		if(motp.length() < 6)
			test = true;

		return test;
	}

	// methode permettant de valider un nouveau abonne
	public void validationAbon (Utilisatmodel utili)
	{

		try {
			stat = based.connect().createStatement();
			stat.executeUpdate(" UPDATE utilisateur set access = '"+true+"' where id = "+utili.getId()+"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erreur validation abonnement");
		}
	}
	// methode qui va retourner la liste des abonnees en attendante d abonnement
	public ArrayList<Utilisatmodel> affichg()
	{
		String state = "Adherent";
		ArrayList<Utilisatmodel> arryu = new ArrayList<Utilisatmodel>();

		try {
			stat = based.connect().createStatement();
			resultat = stat.executeQuery("select id,nom,prenom,lieunssce,datenssce from utilisateur where access ='"+false+"' and statut = '"+state+"'");

			while(resultat.next())
			{
				Utilisatmodel utili = new Utilisatmodel();


				utili.setId(resultat.getInt("id"));
				utili.setNom(resultat.getString("nom"));
				utili.setPrenom(resultat.getString("prenom"));
				utili.setDatenssce(resultat.getString("datenssce"));
				utili.setLieunssce(resultat.getString("lieunssce"));

				arryu.add(utili);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arryu;
	}
	// methode permettant de recupere le statut d'un utilisation a partir de nom et mot de pass

	public String statutilisa (String nom,String preno)
	{
		String statu = null;
		try {
			stat = based.connect().createStatement();
			resultat = stat.executeQuery("select statut from utilisateur where nom = '"+nom+"' and passwd = '"+preno+"' ");
			if(resultat.next())
				statu = resultat.getString("statut");
		} catch (SQLException e) {
			System.out.println("erreur de recherche statut utilisateur "+e.getMessage());
		}
		return statu;
	}
	// methode permettant de connaitre le nom et le prenom d un user a partir de son id
	public Map<String,String> nompren (int id)
	{
		Map<String, String> enrg = new HashMap<String,String>();
		String req = "Select nom,prenom from utilisateur where id = ?";

		try {
			PreparedStatement ps = based.connect().prepareStatement(req);
			ps.setInt(1, id);

			resultat = ps.executeQuery();

			while(resultat.next())
			{
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");

				enrg.put("nom", nom);
				enrg.put("prenom", prenom);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();

			System.out.println("erreur nom et prenom ****"+e.getMessage());
		}
		return enrg;
	}
}


