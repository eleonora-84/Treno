package com.corso.treno.web.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import builder.TrenoBuilder;
import builder.FR.FRBuilder;
import builder.TN.TNBuilder;
import dao.TrenoDao;
import dao.UtenteDao;
import daoImpl.TrenoDaoImpl;
import daoImpl.UtenteDaoImpl;
import exception.Errori;
import treno.Treno;
import vagoni.Carrozza;

@Controller
public class HomeController {

	@RequestMapping(path = "/")
	public String boh(Model model, HttpServletRequest request) {
		TrenoDao trenoDAO = TrenoDaoImpl.getInstance();
		request.getSession().setAttribute("listaTreni", trenoDAO.listaTreni());
		return "Menu";
	}

	@RequestMapping(path = "/Home")
	public String home() {
		return "Home";
	}

	@RequestMapping(path = "/about")
	public String about(HttpServletRequest request, Model model) {
		int flag = 1;
		String username = (String) request.getSession().getAttribute("username");
		if (username != null) {
			model.addAttribute("flag", flag);
			return "about";
		}
		flag = 0;
		model.addAttribute("flag", flag);
		return "about";
	}

	@RequestMapping(path = "/CreazioneTreno")
	public String altraprova1() {
		return "CreazioneTreno";
	}

	@RequestMapping(path = "/register")
	public String register(@WebParam String username, @WebParam String password, Model model) {
		int flag=0;
		UtenteDao utenteDAO = UtenteDaoImpl.getInstance();
		model.addAttribute("Registrato", "Utente Registrato con Successo");
		model.addAttribute("erroreRegister", "Utente gia' registrato");
		try {
			utenteDAO.add(username, password);
			model.addAttribute("username", username);
			model.addAttribute("password", password);	
			flag=1;
			model.addAttribute("flag",flag);
		return "Home";
		}
		catch (Exception e) {
			e.getMessage();
			flag=2;
			model.addAttribute("flag",flag);
			return "Home";

		}

	}

	@RequestMapping(path = "/login")
	public String login(@WebParam String username, @WebParam String password, Model model, HttpServletRequest request) {
		request.getSession().setAttribute("username", username);
		UtenteDao utenteDAO = UtenteDaoImpl.getInstance();
		TrenoDao trenoDAO = TrenoDaoImpl.getInstance();
		request.getSession().setAttribute("listaTreni", trenoDAO.listaTreni());
		int flagLogin=0;
		model.addAttribute("erroreLogin", "Utente non registrato");
		int flag = 1;
		if (utenteDAO.findByUsernameEPassword(username,password)) {
			model.addAttribute("flag", flag);
			model.addAttribute("username", username);
			request.getSession().setAttribute(username, username);
			System.out.println(utenteDAO.findByUsername(username));
			return "Menu";
		} else {
			flagLogin=1;
			model.addAttribute("flagLogin", flagLogin);
			return "Home";
		}
			}

	@RequestMapping(path = "/Menu")
	public String Menu(@WebParam Model model, HttpServletRequest request) {
		int flag = 1;
		String username = (String) request.getSession().getAttribute("username");
		TrenoDao trenoDAO = TrenoDaoImpl.getInstance();
		request.getSession().setAttribute("listaTreni", trenoDAO.listaTreni());
	
		if (username != null) {
			model.addAttribute("flag", flag);
			return "Menu";
		}
		flag = 0;
		model.addAttribute("flag", flag);
		return "Menu";
	}

	@RequestMapping(path = "/costruisci")
	public String costruisci(@WebParam String sigla, Model model, HttpServletRequest request, @WebParam String tipo) throws IOException {		String username = (String) request.getSession().getAttribute("username");
		Errori e1 = new Errori(sigla);
		try {
			TrenoBuilder trenoTN = new TNBuilder();
			TrenoBuilder trenoFR = new FRBuilder();
			Treno treno = null;
			if (tipo.equals("TN")) {
				treno = trenoTN.costruisci(sigla);
			} else if (tipo.equals("FR")) {
				treno = trenoFR.costruisci(sigla);				
			}
			System.out.println(treno);
			List<Carrozza> v= new LinkedList<Carrozza>();
			v.add(treno.getLocomotiva());
			v.addAll(treno.getListaVagoni());
			TrenoDao trenoDAO = TrenoDaoImpl.getInstance();
			UtenteDao utenteDAO = UtenteDaoImpl.getInstance();
			trenoDAO.add(treno, utenteDAO.findByUsername(username), tipo);
			String locomotiva;
			String passeggeri;
			String ristorante;
			String cargo;	
			System.out.println(tipo);
			List<String> trenoSigla = new LinkedList<String>();
			if (tipo.equals("TN")) {
				System.out.println(tipo);
				locomotiva = "<img class='main-treno' src='./img/locomotiva.png' width='150'>";
				passeggeri = "<img class='main-treno' src='./img/passeggeri.png' width='150'>";
				ristorante = "<img class='main-treno' src='./img/ristorante.png' width='150' >";
				cargo = "<img class='main-treno' src='./img/cargo.png' width='150'>";	
			} else {
				locomotiva = "<img class='main-treno' src='./img/locomotivaFR.png' width='150'>";
				passeggeri = "<img class='main-treno' src='./img/passeggeriFR.png' width='150'>";
				ristorante = "<img class='main-treno' src='./img/ristoranteFR.png' width='150' >";
				cargo = "<img class='main-treno' src='./img/cargoFR.png' width='150'>";		
			}
			for (int i = 0; i < sigla.length(); i++) {
				switch (sigla.toUpperCase().charAt(i)) {
				case 'H':
					trenoSigla.add(locomotiva);
					break;
				case 'P':
					trenoSigla.add(passeggeri);
					break;
				case 'R':
					trenoSigla.add(ristorante);
					break;
				case 'C':
					trenoSigla.add(cargo);
					break;
				}
				model.addAttribute("trenoSigla", prova(trenoSigla));
				model.addAttribute("sigla", sigla);
				model.addAttribute("peso", treno.getPeso());
			}
		} catch (Exception e) {
			model.addAttribute("errore", e1.getMessage(sigla));
			model.addAttribute("siglaSuggerita", e1.siglaSuggerita(sigla));
		}
		return "CreazioneTreno";
	}

	@RequestMapping(path = "/treni")
	public String treni(Model model, HttpServletRequest request) {
		TrenoDao trenoDAO = TrenoDaoImpl.getInstance();
		request.getSession().setAttribute("listaTreni", trenoDAO.listaTreni());
		request.getSession().setAttribute("username", request.getSession().getAttribute("username"));
		return "treni";
	}
	
	@RequestMapping(path = "logout")
	public String logout(Model model, HttpServletRequest request) {
		request.getSession().invalidate();
		TrenoDao trenoDAO = TrenoDaoImpl.getInstance();
		request.getSession().setAttribute("listaTreni", trenoDAO.listaTreni());
		int flag = 0;
		model.addAttribute("flag", flag);
		model.addAttribute("username", "");
		return "Menu";
	}
	
	@RequestMapping(path = "/elimina")
	public String elimina(int id) {
		TrenoDao trenoDAO = TrenoDaoImpl.getInstance();
		trenoDAO.deleteTreno(id);
		return "elimina";
	}

	public static String prova(List list) {
		String finale = "";
		for (int i = 0; i < list.size(); i++) {
			finale = finale + "   " + list.get(i) + "   ";
		}
		return finale;
	}
}
